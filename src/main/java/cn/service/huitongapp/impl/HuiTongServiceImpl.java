package cn.service.huitongapp.impl;  

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.qdm0020530.DEDE_ADDONARTICLEMapper;
import cn.dao.qdm0020530.DEDE_ARCHIVESMapper;
import cn.dao.xdchats.XD_CustomerMapper;
import cn.dao.xdchats.ZXDYMapper;
import cn.dao.xinxidu.Freecms_InfoMapper;
import cn.entity.huitongapp.OfficialDto;
import cn.entity.huitongapp.OnlineQADto;
import cn.entity.qdm0020530.DEDE_ADDONARTICLE;
import cn.entity.qdm0020530.DEDE_ADDONARTICLEExample;
import cn.entity.qdm0020530.DEDE_ARCHIVES;
import cn.entity.qdm0020530.DEDE_ARCHIVESExample;
import cn.entity.xdchats.XD_Customer;
import cn.entity.xdchats.XD_CustomerExample;
import cn.entity.xdchats.ZXDY;
import cn.entity.xdchats.ZXDYExample;
import cn.entity.xinxidu.Freecms_Info;
import cn.entity.xinxidu.Freecms_InfoExample;
import cn.entity.xinxidu.OfficialDto3;
import cn.service.huitongapp.HuiTongService;
  
@Service
public class HuiTongServiceImpl implements HuiTongService{
	@Autowired
	private DEDE_ARCHIVESMapper archivesDao;
	
	@Autowired
	private DEDE_ADDONARTICLEMapper articleDao;
	
	@Autowired
	private ZXDYMapper zxdyDao;
	
	@Autowired
	private XD_CustomerMapper customerDao;
	
	@Autowired
	private Freecms_InfoMapper freecms_InfoMapper;
	
	
	SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	
	public List<OfficialDto> query_archives_article(DEDE_ARCHIVESExample archivesExample){
		//创建一个DEDE_ARCHIVES类的集合
		List<DEDE_ARCHIVES> archives = new ArrayList<DEDE_ARCHIVES>();
		//调用查询方法得到结果
		archives = archivesDao.selectByExample(archivesExample);
		//创建并得到DEDE_ARCHIVES类的集合的id属性的集合
		List<Integer> idlist = new ArrayList<Integer>();
		for(DEDE_ARCHIVES arc : archives){
			idlist.add(arc.getId());
		}
		//创建一个DEDE_ADDONARTICLE类的集合
		List<DEDE_ADDONARTICLE> article = new ArrayList<DEDE_ADDONARTICLE>();
		//创建查询类DEDE_ADDONARTICLEExample并把得到的id的集合赋值给它
		DEDE_ADDONARTICLEExample articleExample = new DEDE_ADDONARTICLEExample();
		articleExample.createCriteria().andAidIn(idlist);
		//调用查询方法得到结果
		article = articleDao.selectByExampleWithBLOBs(articleExample);
		
		//将结果得到的两个原始类转化成接口要求的类
		List<OfficialDto> officiallist = new ArrayList<OfficialDto>();
		officiallist = arch_arti_to_offic(archives, article);
		return officiallist;
	}
	
	public String query_article(int aid){
		//创建一个原始类
		DEDE_ADDONARTICLE article = new DEDE_ADDONARTICLE();
		//调用查询方法得到结果
		article = articleDao.selectByPrimaryKey(aid);
		//将结果得到的原始类转化成接口要求的类
		String body = "";
		body = article.getBody();
		return body;
	}
	
	public List<OfficialDto> query_archives(DEDE_ARCHIVESExample archivesExample){
		//创建一个原始类的集合
		List<DEDE_ARCHIVES> archives = new ArrayList<DEDE_ARCHIVES>();
		//调用查询方法得到结果
		archives = archivesDao.selectByExample(archivesExample);
		//将结果得到的原始类转化成接口要求的类
		List<OfficialDto> officiallist = new ArrayList<OfficialDto>();
		officiallist = arch_to_offic(archives);
		return officiallist;
	}
	
	public List<OnlineQADto> query_zxdy(ZXDYExample zxdyExample){
		//创建一个原始类的集合
		List<ZXDY> zxdy = new ArrayList<ZXDY>();
		//调用查询方法得到结果
		zxdy = zxdyDao.selectByExample(zxdyExample);
		//将结果得到的原始类转化成接口要求的类
		List<OnlineQADto> onlinelist = new ArrayList<OnlineQADto>();
		onlinelist = zxdy_to_online(zxdy);
		System.out.println(1);
		return onlinelist;
	}
	
	public int count_official(DEDE_ARCHIVESExample archivesExample){
		int count = archivesDao.countByExample(archivesExample);
		int pageRows = archivesExample.getSize();
		System.out.println(pageRows);
		int sumPages = (count+archivesExample.getSize()-1)/archivesExample.getSize();
		return sumPages;
	}
	
	public int count_zxdy(ZXDYExample zxdyExample){
		int count = zxdyDao.countByExample(zxdyExample);
		int sumPages = (count+zxdyExample.getSize()-1)/zxdyExample.getSize();
		return sumPages;
	}
		
	public List<OfficialDto> query_archives_detail(int id){
		//创建返回的接口类的集合
		List<OfficialDto> officiallist = new ArrayList<OfficialDto>();
		//创建DEDE_ARCHIVES对象并根据id得到查询后的对象
		DEDE_ARCHIVES archives = new DEDE_ARCHIVES();
		archives = archivesDao.selectByPrimaryKey(id);
		//创建DEDE_ADDONARTICLE对象并根据id得到查询后的对象
		DEDE_ADDONARTICLE article = new DEDE_ADDONARTICLE();
		article = articleDao.selectByPrimaryKey(id);
		//将得到的对象转成对应的集合后再转成接口要求的集合
		List<DEDE_ADDONARTICLE> article_list = new ArrayList<DEDE_ADDONARTICLE>();
		List<DEDE_ARCHIVES> archives_list = new ArrayList<DEDE_ARCHIVES>();
		archives_list.add(archives);
		article_list.add(article);
		officiallist = arch_arti_to_offic(archives_list, article_list);
		return officiallist;
	}

	public List<OnlineQADto> query_zxdy_detai(long id){
		//创建返回的接口类的集合
		List<OnlineQADto> onlinelist = new ArrayList<OnlineQADto>();
		//创建一个ZXDY对象并根据id得到查询后的对象
		ZXDY zxdy = new ZXDY();
		zxdy = zxdyDao.selectByPrimaryKey(id);
		//将得到的对象转成对应的集合后再转成接口要求的集合
		List<ZXDY> zxdy_list = new ArrayList<ZXDY>();
		zxdy_list.add(zxdy);
		onlinelist = zxdy_to_online(zxdy_list);
		return onlinelist;
	}
	
	public List<OfficialDto> arch_to_offic(List<DEDE_ARCHIVES> archives_list){
		List<OfficialDto> officiallist = new ArrayList<OfficialDto>();
		for(int i = 0 ; i < archives_list.size() ; i++){
			OfficialDto official = new OfficialDto();
			
			official.setId(archives_list.get(i).getId());
			official.setTypeid(archives_list.get(i).getTypeid());
			official.setTypeid2(archives_list.get(i).getTypeid2());
			official.setKeywords(archives_list.get(i).getKeywords());
			official.setSenddate(archives_list.get(i).getSenddate()+"");
			official.setPubdate(archives_list.get(i).getPubdate()+"");
			official.setClick(archives_list.get(i).getClick());
			official.setDescription(archives_list.get(i).getDescription());
			official.setLitpic(archives_list.get(i).getLitpic());
			official.setTitle(archives_list.get(i).getTitle());
			official.setShorttitle(archives_list.get(i).getShorttitle());
			official.setSortrank(archives_list.get(i).getSortrank());
			
			officiallist.add(official);
		}
		return officiallist;
	}
	
	public List<String> arti_to_str(List<DEDE_ADDONARTICLE> article_list){
		List<String> stringlist = new ArrayList<String>();
		for(int i = 0 ; i < article_list.size() ; i++){
			String a = article_list.get(i).getBody();
			stringlist.add(a);
		}
		return stringlist;
	}
	
	public List<OfficialDto> arch_arti_to_offic(List<DEDE_ARCHIVES> archives_list, List<DEDE_ADDONARTICLE> article_list){
		List<OfficialDto> officiallist = new ArrayList<OfficialDto>();
		int length = archives_list.size()-1;
		for(int i = 0 ; i < archives_list.size() ; i++){
			OfficialDto official = new OfficialDto();
			
			official.setId(archives_list.get(i).getId());
			official.setTypeid(archives_list.get(i).getTypeid());
			official.setTypeid2(archives_list.get(i).getTypeid2());
			official.setKeywords(archives_list.get(i).getKeywords());
			official.setSenddate(archives_list.get(i).getSenddate()+"");
			official.setPubdate(archives_list.get(i).getPubdate()+"");
			official.setClick(archives_list.get(i).getClick());
			official.setDescription(archives_list.get(i).getDescription());
			official.setLitpic(archives_list.get(i).getLitpic());
			official.setTitle(archives_list.get(i).getTitle());
			official.setShorttitle(archives_list.get(i).getShorttitle());
			official.setSortrank(archives_list.get(i).getSortrank());
			official.setRedirecturl(article_list.get(length-i).getRedirecturl());
			official.setBody(article_list.get(length-i).getBody());
			
			officiallist.add(official);
		}
		return officiallist;
	}
	
	public List<OnlineQADto> zxdy_to_online(List<ZXDY> zxdy){
		List<OnlineQADto> onlinelist = new ArrayList<OnlineQADto>();
		try {
			for(int i = 0 ; i < zxdy.size() ; i++){
				OnlineQADto online = new OnlineQADto();
				online.setId(Integer.parseInt(zxdy.get(i).getId()+""));
				online.setA(zxdy.get(i).getAt());
				online.setAn(zxdy.get(i).getAn());
				online.setQ(zxdy.get(i).getQt());
				online.setQn(zxdy.get(i).getQn());
				online.setQutime(zxdy.get(i).getCreateDate().toString());
				online.setAntime(zxdy.get(i).getLastUpdateDate().toString());
				if(null==zxdy.get(i).getStatus()){
					online.setStatus(0);
				}else{
					online.setStatus(zxdy.get(i).getStatus());
				}
				
				onlinelist.add(online);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlinelist;
	}
	
	public List<OnlineQADto> zxdy_to_online_answer(List<ZXDY> zxdy){
		List<OnlineQADto> onlinelist = new ArrayList<OnlineQADto>();
		try {
			for(int i = 0 ; i < zxdy.size() ; i++){
				OnlineQADto online = new OnlineQADto();
				online.setId(Integer.parseInt(zxdy.get(i).getId()+""));
				online.setA(zxdy.get(i).getAt());
				online.setAn(zxdy.get(i).getAn());
				online.setQ(zxdy.get(i).getQt());
				online.setQn(zxdy.get(i).getQn());
				online.setQutime(zxdy.get(i).getCreateDate().toString());
				online.setAntime(zxdy.get(i).getLastUpdateDate().toString());
				online.setStatus(zxdy.get(i).getStatus());
				
				onlinelist.add(online);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlinelist;
	}
	
	public int insert_zxdy(long id, String content){
		int r = 0;
		//创建用户对象
		XD_Customer customer = new XD_Customer();
		//创建用户对象的查询对象，并把id赋值给它
		XD_CustomerExample customerExample = new XD_CustomerExample();
		customerExample.createCriteria().andIdEqualTo(id);
		//根据id查出用户对象
		customer = customerDao.selectByPrimaryKey(id);
		//得到提问人的名称
		String name = customer.getUsername();
		//得到提问的时间
		Date now = new Date();
		//创建ZXDY对象，并把得到的参数全部赋值给它
		ZXDY zxdy = new ZXDY();
		zxdy.setQt(content);
		zxdy.setQn(name);
		zxdy.setCreateDate(now);
		//通过复制后的对象得到查询结果并返回
		r = zxdyDao.insert(zxdy);
		return r;
	}
	
	public List<OfficialDto3> free_to_offic(List<Freecms_Info> freecms_list){
		List<OfficialDto3> official_list = new ArrayList<OfficialDto3>();
		
		for(Freecms_Info fi : freecms_list){
			OfficialDto3 offic = new OfficialDto3();
			
			offic.setId(fi.getId());
			offic.setTypeid(fi.getChannel());
			offic.setSortrank(sf1.format(fi.getAddtime()));
			if(fi.getClicknum() == null){
				offic.setClick(0);
			}else{
				offic.setClick(fi.getClicknum());
			}
			offic.setTitle(fi.getTitle());
			offic.setShorttitle(fi.getShorttitle());
			offic.setLitpic(fi.getImg());
			offic.setPubdate(sf1.format(fi.getAddtime()));
			offic.setSenddate(sf1.format(fi.getAddtime()));
			offic.setKeywords(fi.getTitle());
			offic.setDescription(fi.getDescription());
			offic.setBody(fi.getContent());
			offic.setRedirecturl(fi.getUrl());
			
			official_list.add(offic);
		}
		return official_list;
	}
	
	public List<Freecms_Info> query_freecms(Freecms_InfoExample freecmsExample){
		List<Freecms_Info> freecms_list = new ArrayList<Freecms_Info>();
		freecms_list = freecms_InfoMapper.selectByExampleWithBLOBs(freecmsExample);
		return freecms_list;
	}
	
	public int count_freecms(Freecms_InfoExample freecmsExample){
		int sum = 0;
		sum = freecms_InfoMapper.countByExample(freecmsExample);
		return sum;
	}
	
	public Freecms_Info query_freecms_detail(String id){
		Freecms_Info freecms = new Freecms_Info();
		freecms = freecms_InfoMapper.selectByPrimaryKey(id);
		return freecms;
	}
}
