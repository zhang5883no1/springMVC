package cn.service.xinxidu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.dao.xinxidu.Freecms_InfoMapper;
import cn.entity.xinxidu.DBStringDto;
import cn.entity.xinxidu.DoubleOfficialDto;
import cn.entity.xinxidu.Freecms_Info;
import cn.entity.xinxidu.Freecms_InfoExample;
import cn.entity.xinxidu.OfficialDto3;
import cn.entity.xinxidu.Page;
import cn.entity.xinxidu.StringDto;
import cn.service.xinxidu.Freecms_InfoMapperService;

@Service("Freecms_InfoMapperServiceImpl")
public class Freecms_InfoMapperService{

	@Autowired
	private Freecms_InfoMapper freecms_InfoMapper;
	
	String sql = "";
	SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	
	//当type为guanwang时difference为hangqing
	public Page getOfficHangQing(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
		//sql="select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='8b18c1b9-10b9-4e74-b4ba-55562765997f' order by addtime desc ";
		Page page = new Page();
		int pageRows = 10;
		if(pagerow!=0){
			pageRows=pagerow;
		}
		try {
			if(type.equals(".guanwang")){
				type=".freecms";
			}
			//统计总行数
			//String sql_count = "select count(*) "+sql.substring(sql.indexOf("from"));
			long sumRows = 0;
			//总行数
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("8b18c1b9-10b9-4e74-b4ba-55562765997f");
			example.setOrderByClause("addtime desc");
			sumRows = freecms_InfoMapper.countByExample(example);
			//总页数
			int sumPages = ((int)sumRows+pageRows-1)/pageRows;
			//判断当前页
			if(indexPage>sumPages){
				indexPage = sumPages;
			}
			if(indexPage < 1){
				indexPage = 1;
			}
			//获取数据
			//String sql_select = sql + " limit"+(indexPage-1)*pageRows+","+pageRows;
			List<OfficialDto3> officlist1 = new LinkedList<OfficialDto3>();
			List<OfficialDto3> officlist2 = new LinkedList<OfficialDto3>();
			List<DoubleOfficialDto> doulist = new LinkedList<DoubleOfficialDto>();
			example.setOffset((indexPage-1)*pageRows);
			example.setSize(pageRows);
			List<Freecms_Info> freecms_Info_List= freecms_InfoMapper.selectByExampleWithBLOBs(example);
			for(Freecms_Info fi : freecms_Info_List){
				DoubleOfficialDto dod = new DoubleOfficialDto();
				dod.setId(fi.getId());
				dod.setChannel(fi.getChannel());
				dod.setAddtime(sf1.format(fi.getAddtime()));
				if(fi.getClicknum() == null){
					dod.setClickNum(0);
				}else{
					dod.setClickNum(fi.getClicknum());
				}
				dod.setTitle(fi.getTitle());
				dod.setShortTitle(fi.getShorttitle());
				dod.setImg(fi.getImg());
				dod.setDescription(fi.getDescription());
				dod.setContent(fi.getContent());
				dod.setUrl(fi.getUrl());
				dod.setAttchs(fi.getAttchs());
				doulist.add(dod);
			}
			
			for(DoubleOfficialDto dboff : doulist){
				officlist1.add(typeedit(dboff));
			}
			
			for(int i = 0;i < officlist1.size();i++){
				try {
					//letter-spacing:-0.1em行距
					String a=officlist1.get(i).getBody();
					
					if(a.indexOf("/strong>")!=-1){
						a = a.substring(a.indexOf("/strong>")+8,a.indexOf("/strong>")+58);
					}else{
						
					}
					if((officlist1.get(i).getId()+"").equals("1854")){
						a = a.substring(a.indexOf("/strong>")+8)+officlist1.get(i).getBody().substring(officlist1.get(i).getBody().indexOf("/strong>")+58, officlist1.get(i).getBody().indexOf("/strong>")+66);
					}else{
						
					}
					if(a.indexOf("<span style=\"font-size: 14px;\">")!=-1){
						a = a.substring(a.indexOf("<span style=\"font-size: 14px;\">")+31)+officlist1.get(i).getBody().substring(officlist1.get(i).getBody().indexOf("/strong>")+58, officlist1.get(i).getBody().indexOf("/strong>")+89);
					}else{
						
					}
					if(a.substring(0,1).equals("：")){
						a = a.substring(1);
					}else{
						
					}
					if(a.indexOf("&mdash;")!=-1){
						a = a.replaceAll("&mdash;", "");
					}else{
						
					}
					officlist1.get(i).setBody(a);
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
			}
			officlist2.add((OfficialDto3)officlist1.get(0));
	        String times = ((OfficialDto3)officlist1.get(0)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(0)).getSenddate().indexOf(" "));
	        if (times.equals(((OfficialDto3)officlist1.get(1)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(1)).getSenddate().indexOf(" ")))) {
	        	officlist2.add((OfficialDto3)officlist1.get(2));
	        	times = ((OfficialDto3)officlist1.get(2)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(2)).getSenddate().indexOf(" "));
	        	if (times.equals(((OfficialDto3)officlist1.get(3)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(3)).getSenddate().indexOf(" ")))) {
	        		officlist2.add((OfficialDto3)officlist1.get(4));
	        	} else {
	        		officlist2.add((OfficialDto3)officlist1.get(3));
	        	}
	        } else {
		        officlist2.add((OfficialDto3)officlist1.get(1));
		        times = ((OfficialDto3)officlist1.get(1)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(1)).getSenddate().indexOf(" "));
		        if (times.equals(((OfficialDto3)officlist1.get(2)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(2)).getSenddate().indexOf(" ")))) {
		          officlist2.add((OfficialDto3)officlist1.get(3));
		        } else {
		          officlist2.add((OfficialDto3)officlist1.get(2));
		        }
	        }
	          //封装数据
	        if (rp == 1)
	          page.setList(officlist2);
	        else {
	          page.setList(officlist1);
	        }
			//page.setList(officlist1);
			page.setSumPage(sumPages);
			page.setPageRows(pageRows);
			page.setIndexPage(indexPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	
	
	
	//当type为guanwang时difference为jiepan
		public Page getOfficJiePan(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			Freecms_InfoExample example = new Freecms_InfoExample();
			if(longdate==0){
				//sql = " select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='05fa22e7-ce0a-4b2f-b3d5-04fa2fb2af5e' order by addtime desc ";
				example.createCriteria().andChannelEqualTo("05fa22e7-ce0a-4b2f-b3d5-04fa2fb2af5e");
				example.setOrderByClause("addtime desc");
			}else{
				longdate = longdate/1000;
				long longdate1 = longdate+86399;
				//String time1 = sf1.format(new Date(longdate));
				//String time2 = sf1.format(new Date(longdate1));
				//sql = " select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='05fa22e7-ce0a-4b2f-b3d5-04fa2fb2af5e' and addtime>"+time1+" and addtime<"+time2+" order by addtime desc";
				example.createCriteria().andChannelEqualTo("05fa22e7-ce0a-4b2f-b3d5-04fa2fb2af5e").andAddtimeBetween(new Date(longdate), new Date(longdate1));;
				example.setOrderByClause("addtime desc");
			}
			Page page = new Page();
			int pageRows = 10;
			if(pagerow!=0){
				pageRows=pagerow;
			}
			try {
				if(type.equals(".guanwang")){
					type=".freecms";
				}
				//统计总行数
				//String sql_count = "select count(*) "+sql.substring(sql.indexOf("from"));
				long sumRows = 0;
				//sumRows = run.query(conn, sql_count,new ScalarHandler<Long>());
				sumRows = freecms_InfoMapper.countByExample(example);
				//总页数
				int sumPages = ((int)sumRows+pageRows-1)/pageRows;
				//判断当前页
				if(indexPage>sumPages){
					indexPage = sumPages;
				}
				if(indexPage < 1){
					indexPage = 1;
				}
				//获取数据
				//String sql_select = sql + " limit ?,?";
				List<OfficialDto3> officlist1 = new LinkedList<OfficialDto3>();
				List<DoubleOfficialDto> doulist = new LinkedList<DoubleOfficialDto>();
				//doulist = run.query(conn, sql_select, new BeanListHandler<DoubleOfficialDto>(DoubleOfficialDto.class),(indexPage-1)*pageRows, pageRows);
				example.setOffset((indexPage-1)*pageRows);
				example.setSize(pageRows);
				List<Freecms_Info> freecms_Info_List= freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					DoubleOfficialDto dod = new DoubleOfficialDto();
					dod.setId(fi.getId());
					dod.setChannel(fi.getChannel());
					dod.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dod.setClickNum(0);
					}else{
						dod.setClickNum(fi.getClicknum());
					}
					dod.setTitle(fi.getTitle());
					dod.setShortTitle(fi.getShorttitle());
					dod.setImg(fi.getImg());
					dod.setDescription(fi.getDescription());
					dod.setContent(fi.getContent());
					dod.setUrl(fi.getUrl());
					dod.setAttchs(fi.getAttchs());
					doulist.add(dod);
				}
				
				
				for(DoubleOfficialDto dboff : doulist){
					officlist1.add(typeedit(dboff));
				}
				for(int i = 0;i < officlist1.size();i++){
					try {
						String a=officlist1.get(i).getBody();
						if(a.indexOf("height=\"350\" width=\"550\"")!=-1){
							a = a.replaceAll("height=\"350\" width=\"550\"", "height=\"400\" width=\"600\"");
						}else if(a.indexOf("width=\"550\" height=\"350\"")!=-1){
							a = a.replaceAll("width=\"550\" height=\"350\"", "height=\"400\" width=\"600\"");
						}
						officlist1.get(i).setBody(a);
						
						if(a.indexOf("http://www.tudou.com")!=-1){
							int start=a.indexOf("http://www.tudou.com");
							int end=a.substring(start).indexOf(".swf")+4;
							String b=a.substring(start,start+end);
							String c[]=b.split("/");
							officlist1.get(i).setBody("http://www.tudou.com/programs/view/html5embed.action?type=0&code="+c[4]+"&lcode=&resourceId="+c[5].split("=")[1]);
						}else if(a.indexOf("http://player.youku.com/player.php")!=-1){
							int start=a.indexOf("http://player.youku.com/player.php");
							int end=a.substring(start).indexOf(".swf")+4;
							String b=a.substring(start,start+end);
							String c[]=b.split("/");
							officlist1.get(i).setBody("http://player.youku.com/embed/"+c[5]);
						}
					} catch (Exception e) {
						
					}
				}
				//封装数据
				page.setList(officlist1);
				page.setSumPage(sumPages);
				page.setPageRows(pageRows);
				page.setIndexPage(indexPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return page;
		}
		
		
		//当type为guanwang时difference为touzi
		public Page getOfficTouZi(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			//sql = "select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='bbdf09fe-0abe-428c-a96c-db2acdcc553f' order by addtime desc";
			Page page = new Page();
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("bbdf09fe-0abe-428c-a96c-db2acdcc553f");
			example.setOrderByClause("addtime desc");
			page = this.list(indexPage,pagerow,difference,type,example);
			return page;
		}
		
		
		
		
		//当type为guanwang时difference为gonggao
		public Page getOfficGongGao(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			//sql = "select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='12301af9-99f0-4293-ba4e-fbccd33e7f58' order by addtime desc";
			Page page = new Page();
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("12301af9-99f0-4293-ba4e-fbccd33e7f58");
			example.setOrderByClause("addtime desc");
			page = this.list(indexPage,pagerow,difference,type,example);
			return page;
		}
		
		
		//当type为guanwang时difference为qydt
		public Page getOfficQydt(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			Freecms_InfoExample example = new Freecms_InfoExample();
			//sql = "select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='a691c171-f9db-40ba-ac99-8a7c2b8512af' order by addtime desc";
			example.createCriteria().andChannelEqualTo("a691c171-f9db-40ba-ac99-8a7c2b8512af");
			example.setOrderByClause("addtime desc");
			Page page = new Page();
			int pageRows = 10;
			if(pagerow!=0){
				pageRows=pagerow;
			}
			try {
				if(type.equals(".guanwang")){
					type=".freecms";
				}
				//统计总行数
				//String sql_count = "select count(*) "+sql.substring(sql.indexOf("from"));
				long sumRows = 0;
				sumRows = freecms_InfoMapper.countByExample(example);
				//总页数
				int sumPages = ((int)sumRows+pageRows-1)/pageRows;
				//判断当前页
				if(indexPage>sumPages){
					indexPage = sumPages;
				}
				if(indexPage < 1){
					indexPage = 1;
				}
				//获取数据
				//String sql_select = sql + " limit ?,?";
				example.setOffset((indexPage-1)*pageRows);
				example.setSize(pageRows);
				List<OfficialDto3> officlist1 = new LinkedList<OfficialDto3>();
				List<DoubleOfficialDto> doulist = new LinkedList<DoubleOfficialDto>();
				//doulist = run.query(conn, sql_select, new BeanListHandler<DoubleOfficialDto>(DoubleOfficialDto.class),(indexPage-1)*pageRows, pageRows);
				List<Freecms_Info> freecms_Info_List= freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					DoubleOfficialDto dod = new DoubleOfficialDto();
					dod.setId(fi.getId());
					dod.setChannel(fi.getChannel());
					dod.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dod.setClickNum(0);
					}else{
						dod.setClickNum(fi.getClicknum());
					}
					dod.setTitle(fi.getTitle());
					dod.setShortTitle(fi.getShorttitle());
					dod.setImg(fi.getImg());
					dod.setDescription(fi.getDescription());
					dod.setContent(fi.getContent());
					dod.setUrl(fi.getUrl());
					dod.setAttchs(fi.getAttchs());
					doulist.add(dod);
				}
				
				for(DoubleOfficialDto dboff : doulist){
					officlist1.add(typeedit(dboff));
				}
				for(int i = 0;i < officlist1.size();i++){
					officlist1.get(i).setLitpic("http://www.xiduweb.com"+officlist1.get(i).getLitpic());
					String bodys = officlist1.get(i).getBody();
					bodys = bodys.replaceAll("src=\"/", "src=\"http://www.xiduweb.com/");
					officlist1.get(i).setBody(bodys);
					String dates = officlist1.get(i).getPubdate();
					dates = dates.substring(0, dates.indexOf(" "))+" "+dates.substring(dates.indexOf(" "),dates.indexOf(".0"));
					Date newdate = sf1.parse(dates);
					long timeStemp = newdate.getTime();
					officlist1.get(i).setPubdate(timeStemp/1000+"");
				}
				//封装数据
				page.setList(officlist1);
				page.setSumPage(sumPages);
				page.setPageRows(pageRows);
				page.setIndexPage(indexPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return page;
		}
		
		
		
		//当type为guanwang时difference为riping
		public Page getOfficRiPing(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			//Calendar cal = Calendar.getInstance();
			//sql = "select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='75614262-82de-4d33-ab9a-fd7b0f01bd9d' order by addtime desc";
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("75614262-82de-4d33-ab9a-fd7b0f01bd9d");
			example.setOrderByClause("addtime desc");
			Page page = new Page();
			int pageRows = 10;
			if(pagerow!=0){
				pageRows=pagerow;
			}
			try {
				if(type.equals(".guanwang")){
					type=".freecms";
				}
				//统计总行数
				//String sql_count = "select count(*) "+sql.substring(sql.indexOf("from"));
				long sumRows = 0;
				sumRows = freecms_InfoMapper.countByExample(example);
				//总页数
				int sumPages = ((int)sumRows+pageRows-1)/pageRows;
				//判断当前页
				if(indexPage>sumPages){
					indexPage = sumPages;
				}
				if(indexPage < 1){
					indexPage = 1;
				}
				//获取数据
				//String sql_select = sql + " limit ?,?";
				example.setOffset((indexPage-1)*pageRows);
				example.setSize(pageRows);
				List<OfficialDto3> officlist1 = new LinkedList<OfficialDto3>();
				List<OfficialDto3> officlist2 = new LinkedList<OfficialDto3>();
				List<DoubleOfficialDto> doulist = new LinkedList<DoubleOfficialDto>();
				List<Freecms_Info> freecms_Info_List= freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					DoubleOfficialDto dod = new DoubleOfficialDto();
					dod.setId(fi.getId());
					dod.setChannel(fi.getChannel());
					dod.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dod.setClickNum(0);
					}else{
						dod.setClickNum(fi.getClicknum());
					}
					dod.setTitle(fi.getTitle());
					dod.setShortTitle(fi.getShorttitle());
					dod.setImg(fi.getImg());
					dod.setDescription(fi.getDescription());
					dod.setContent(fi.getContent());
					dod.setUrl(fi.getUrl());
					dod.setAttchs(fi.getAttchs());
					doulist.add(dod);
				}
				
				for(DoubleOfficialDto dboff : doulist){
					if(null==dboff.getAttchs()||"".equals(dboff.getAttchs())){
						if(null==dboff.getUrl()||"".equals(dboff.getUrl())){
							DBStringDto dbs = new DBStringDto();
							//String lwsql = "select htmlIndexnum from freecms_info where id=?";
							Freecms_Info freecms_Info = freecms_InfoMapper.selectByPrimaryKey(dboff.getId());
							dbs.setHtmlIndexnum(freecms_Info.getHtmlindexnum());
							dboff.setUrl("http://www.xiduweb.com/erikan/info/2016/"+dbs.getHtmlIndexnum()+".html");
						}else{
							
						}
					}else{
						dboff.setUrl("http://www.xiduweb.com"+dboff.getAttchs());
					}
					
					officlist1.add(typeedit(dboff));
				}
				officlist2.add((OfficialDto3)officlist1.get(0));
	            String times = ((OfficialDto3)officlist1.get(0)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(0)).getSenddate().indexOf(" "));
	            if (times.equals(((OfficialDto3)officlist1.get(1)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(1)).getSenddate().indexOf(" ")))) {
	              officlist2.add((OfficialDto3)officlist1.get(2));
	              times = ((OfficialDto3)officlist1.get(2)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(2)).getSenddate().indexOf(" "));
	              if (times.equals(((OfficialDto3)officlist1.get(3)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(3)).getSenddate().indexOf(" ")))) {
	                officlist2.add((OfficialDto3)officlist1.get(4));
	              } else {
	                officlist2.add((OfficialDto3)officlist1.get(3));
	              }
	            } else {
	              officlist2.add((OfficialDto3)officlist1.get(1));
	              times = ((OfficialDto3)officlist1.get(1)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(1)).getSenddate().indexOf(" "));
	              if (times.equals(((OfficialDto3)officlist1.get(2)).getSenddate().substring(0, ((OfficialDto3)officlist1.get(2)).getSenddate().indexOf(" ")))) {
	                officlist2.add((OfficialDto3)officlist1.get(3));
	              } else {
	                officlist2.add((OfficialDto3)officlist1.get(2));
	              }

	            }

	            //封装数据
	            if (rp == 1){
	              page.setList(officlist2);
	            }else {
	              page.setList(officlist1);
	            }
				page.setSumPage(sumPages);
				page.setPageRows(pageRows);
				page.setIndexPage(indexPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return page;
		}
		
		
		//当type为guanwang时difference为zhouping
		public Page getOfficZhouPing(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			//sql = "select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='bf7d2e68-712f-48ce-a3d6-d04303954d10' order by addtime desc";
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("bf7d2e68-712f-48ce-a3d6-d04303954d10");
			example.setOrderByClause("addtime desc");
			Page page = new Page();
			int pageRows = 10;
			if(pagerow!=0){
				pageRows=pagerow;
			}
			try {
				if(type.equals(".guanwang")){
					type=".freecms";
				}
				//统计总行数
				//String sql_count = "select count(*) "+sql.substring(sql.indexOf("from"));
				long sumRows = 0;
				sumRows = freecms_InfoMapper.countByExample(example);
				//总页数
				int sumPages = ((int)sumRows+pageRows-1)/pageRows;
				//判断当前页
				if(indexPage>sumPages){
					indexPage = sumPages;
				}
				if(indexPage < 1){
					indexPage = 1;
				}
				//获取数据
				//String sql_select = sql + " limit ?,?";
				example.setOffset((indexPage-1)*pageRows);
				example.setSize(pageRows);
				List<OfficialDto3> officlist1 = new LinkedList<OfficialDto3>();
				List<DoubleOfficialDto> doulist = new LinkedList<DoubleOfficialDto>();
				List<Freecms_Info> freecms_Info_List= freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					DoubleOfficialDto dod = new DoubleOfficialDto();
					dod.setId(fi.getId());
					dod.setChannel(fi.getChannel());
					dod.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dod.setClickNum(0);
					}else{
						dod.setClickNum(fi.getClicknum());
					}
					dod.setTitle(fi.getTitle());
					dod.setShortTitle(fi.getShorttitle());
					dod.setImg(fi.getImg());
					dod.setDescription(fi.getDescription());
					dod.setContent(fi.getContent());
					dod.setUrl(fi.getUrl());
					dod.setAttchs(fi.getAttchs());
					doulist.add(dod);
				}
				
				for(DoubleOfficialDto dboff : doulist){
					if(null==dboff.getAttchs()||"".equals(dboff.getAttchs())){
						if(null==dboff.getUrl()||"".equals(dboff.getUrl())){
							DBStringDto dbs = new DBStringDto();
							//String lwsql = "select htmlIndexnum from freecms_info where id=?";
							Freecms_Info freecms_Info = freecms_InfoMapper.selectByPrimaryKey(dboff.getId());
							dbs.setHtmlIndexnum(freecms_Info.getHtmlindexnum());
							dboff.setUrl("http://www.xiduweb.com/ezhoukan/info/2016/"+dbs.getHtmlIndexnum()+".html");
						}
					}else{
						dboff.setUrl("http://www.xiduweb.com"+dboff.getAttchs());
					}
					officlist1.add(typeedit(dboff));
				}
				//封装数据
				page.setList(officlist1);
				page.setSumPage(sumPages);
				page.setPageRows(pageRows);
				page.setIndexPage(indexPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return page;
		}
		
		
		
		//当type为guanwang时difference为yueping
		public Page getOfficYuePing(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			//sql = "select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where channel='5c60a234-bb64-4388-be4f-885a6b603708' order by addtime desc";
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("5c60a234-bb64-4388-be4f-885a6b603708");
			example.setOrderByClause("addtime desc");
			Page page = new Page();
			int pageRows = 10;
			if(pagerow!=0){
				pageRows=pagerow;
			}
			try {
				if(type.equals(".guanwang")){
					type=".freecms";
				}
				//统计总行数
				//String sql_count = "select count(*) "+sql.substring(sql.indexOf("from"));
				long sumRows = 0;
				sumRows = freecms_InfoMapper.countByExample(example);
				//总页数
				int sumPages = ((int)sumRows+pageRows-1)/pageRows;
				//判断当前页
				if(indexPage>sumPages){
					indexPage = sumPages;
				}
				if(indexPage < 1){
					indexPage = 1;
				}
				//获取数据
				//String sql_select = sql + " limit ?,?";
				example.setOffset((indexPage-1)*pageRows);
				example.setSize(pageRows);
				List<OfficialDto3> officlist1 = new LinkedList<OfficialDto3>();
				List<DoubleOfficialDto> doulist = new LinkedList<DoubleOfficialDto>();
				List<Freecms_Info> freecms_Info_List= freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					DoubleOfficialDto dod = new DoubleOfficialDto();
					dod.setId(fi.getId());
					dod.setChannel(fi.getChannel());
					dod.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dod.setClickNum(0);
					}else{
						dod.setClickNum(fi.getClicknum());
					}
					dod.setTitle(fi.getTitle());
					dod.setShortTitle(fi.getShorttitle());
					dod.setImg(fi.getImg());
					dod.setDescription(fi.getDescription());
					dod.setContent(fi.getContent());
					dod.setUrl(fi.getUrl());
					dod.setAttchs(fi.getAttchs());
					doulist.add(dod);
				}
				
				for(DoubleOfficialDto dboff : doulist){
					if(null==dboff.getAttchs()||"".equals(dboff.getAttchs())){
						if(null==dboff.getUrl()||"".equals(dboff.getUrl())){
							DBStringDto dbs = new DBStringDto();
							//String lwsql = "select htmlIndexnum from freecms_info where id=?";
							Freecms_Info freecms_Info = freecms_InfoMapper.selectByPrimaryKey(dboff.getId());
							dbs.setHtmlIndexnum(freecms_Info.getHtmlindexnum());
							dboff.setUrl("http://www.xiduweb.com/eyuekan/info/2016/"+dbs.getHtmlIndexnum()+".html");
						}
					}else{
						dboff.setUrl("http://www.xiduweb.com"+dboff.getAttchs());
					}
					officlist1.add(typeedit(dboff));
				}
				//封装数据
				page.setList(officlist1);
				page.setSumPage(sumPages);
				page.setPageRows(pageRows);
				page.setIndexPage(indexPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return page;
		}
		
		
		
		//当type为guanwang时difference为jiaoyi
		public Page getOfficJiaoYi(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			//sql = " select body from dede_addonarticle where aid=21 ";
			//sql = " select content from freecms_info where channel='8278e576-cf53-4e8e-8757-f423a52c7d64' ";
			Page pager = new Page();
			List<String> err = new ArrayList<String>();
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("8278e576-cf53-4e8e-8757-f423a52c7d64");
			DoubleOfficialDto dboffi = new DoubleOfficialDto();
			StringDto body = new StringDto();
			type=".freecms";
			try {
				List<Freecms_Info> freecms_Info_List = freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					dboffi.setId(fi.getId());
					dboffi.setChannel(fi.getChannel());
					dboffi.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dboffi.setClickNum(0);
					}else{
						dboffi.setClickNum(fi.getClicknum());
					}
					dboffi.setTitle(fi.getTitle());
					dboffi.setShortTitle(fi.getShorttitle());
					dboffi.setImg(fi.getImg());
					dboffi.setDescription(fi.getDescription());
					dboffi.setContent(fi.getContent());
					dboffi.setUrl(fi.getUrl());
					dboffi.setAttchs(fi.getAttchs());
				}
				body = typeedit1(dboffi);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String bd = body.getBody();
			if(indexPage!=0){
				err.add("err");
			}else{
				err.add(bd);
			}
			pager.setList(err);
			return pager;
		}
		
		
		//当type为guanwang时difference为gongsi
		public Page getOfficGongSi(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			//sql = " select content from freecms_info where channel='30315049-c8be-4fd0-bc04-1cff3397e14e' ";
			Page pager = new Page();
			List<String> err = new ArrayList<String>();
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("30315049-c8be-4fd0-bc04-1cff3397e14e");
			DoubleOfficialDto dboffi = new DoubleOfficialDto();
			StringDto body = new StringDto();
			type=".freecms";
			try {
				List<Freecms_Info> freecms_Info_List = freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					dboffi.setId(fi.getId());
					dboffi.setChannel(fi.getChannel());
					dboffi.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dboffi.setClickNum(0);
					}else{
						dboffi.setClickNum(fi.getClicknum());
					}
					dboffi.setTitle(fi.getTitle());
					dboffi.setShortTitle(fi.getShorttitle());
					dboffi.setImg(fi.getImg());
					dboffi.setDescription(fi.getDescription());
					dboffi.setContent(fi.getContent());
					dboffi.setUrl(fi.getUrl());
					dboffi.setAttchs(fi.getAttchs());
				}
				body = typeedit1(dboffi);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String bd = body.getBody();
			if(indexPage!=0){
				err.add("err");
			}else{
				err.add(bd);
			}
			pager.setList(err);
			return pager;
		}
		
		
		//当type为guanwang时difference为lianxi
		public Page getOfficLianXi(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			//sql = " select content from freecms_info where channel='d33cee38-8d09-47ef-b1a4-6c81b69aaccb' ";
			Page pager = new Page();
			List<String> err = new ArrayList<String>();
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo("d33cee38-8d09-47ef-b1a4-6c81b69aaccb");
			DoubleOfficialDto dboffi = new DoubleOfficialDto();
			StringDto body = new StringDto();
			type=".freecms";
			try {
				List<Freecms_Info> freecms_Info_List = freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					dboffi.setId(fi.getId());
					dboffi.setChannel(fi.getChannel());
					dboffi.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dboffi.setClickNum(0);
					}else{
						dboffi.setClickNum(fi.getClicknum());
					}
					dboffi.setTitle(fi.getTitle());
					dboffi.setShortTitle(fi.getShorttitle());
					dboffi.setImg(fi.getImg());
					dboffi.setDescription(fi.getDescription());
					dboffi.setContent(fi.getContent());
					dboffi.setUrl(fi.getUrl());
					dboffi.setAttchs(fi.getAttchs());
				}
				body = typeedit1(dboffi);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String s = body.getBody();
			String a = "";
			a ="CompanyTel:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
			s = s.substring(s.indexOf("</s")+3);
			a = a+"CustomerHotline:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
			s = s.substring(s.indexOf("</s")+3);
			a = a+"JoinHotline:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
			s = s.substring(s.indexOf("</s")+3);
			a = a+"ComplaintsTel:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
			s = s.substring(s.indexOf("</s")+3);
			a = a+"CompanyAddress:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
			s = s.substring(s.indexOf("</s")+3);
			a = a+"HeadquartersAddress:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"));
			if(indexPage!=0){
				err.add("err");
			}else{
				err.add(a);
			}
			pager.setList(err);
			return pager;
		}
		
		
		//当type为guanwang时difference匹配不到时
		public Page getOfficOther(String type,String difference,int indexPage,int pagerow,long longdate,int rp){
			Page pager = new Page();
			List<String> err = new ArrayList<String>();
			err.add("err");
			pager.setList(err);
			return pager;
		}
		
		
		
		/**
		 * 根据id查询某个OfficialDto3类
		 * @param id
		 * @return
		 */
		public OfficialDto3 getDetOffic(String ids){
			OfficialDto3 offic = new OfficialDto3();
			DoubleOfficialDto dboffic = new DoubleOfficialDto();
			//sql = " select id,channel,addtime,clickNum,title,shortTitle,img,description,content,url,attchs from freecms_info where id=? ";
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andIdEqualTo(ids);
			try {
				List<Freecms_Info> freecms_Info_List = freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					dboffic.setId(fi.getId());
					dboffic.setChannel(fi.getChannel());
					dboffic.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dboffic.setClickNum(0);
					}else{
						dboffic.setClickNum(fi.getClicknum());
					}
					dboffic.setTitle(fi.getTitle());
					dboffic.setShortTitle(fi.getShorttitle());
					dboffic.setImg(fi.getImg());
					dboffic.setDescription(fi.getDescription());
					dboffic.setContent(fi.getContent());
					dboffic.setUrl(fi.getUrl());
					dboffic.setAttchs(fi.getAttchs());
				}
				offic = typeedit(dboffic);
				if(offic.getTypeid() != null){
					if(offic.getTypeid().equals("a691c171-f9db-40ba-ac99-8a7c2b8512af")){
						offic.setLitpic("http://www.xiduweb.com"+offic.getLitpic());
						String bodys = offic.getBody();
						bodys = bodys.replaceAll("src=\"/", "src=\"http://www.xiduweb.com/");
						offic.setBody(bodys);
						String dates = offic.getPubdate();
						dates = dates.substring(0, dates.indexOf(" "))+" "+dates.substring(dates.indexOf(" "),dates.indexOf(".0"));
						Date newdate = sf1.parse(dates);
						long timeStemp = newdate.getTime();
						offic.setPubdate(timeStemp/1000+"");
						return offic;
					}
				}
				
				String b0 = offic.getBody() != null ? offic.getBody() : "";
				if(b0.indexOf("src=\"/upload")!=-1){
					b0 = b0.replaceAll("src=\"/upload", "src=\"http://www.xiduoil.com/upload");
				}else{
					
				}
				offic.setBody(b0);
				
				
				if(offic.getTypeid() != null){
					if(offic.getTypeid().equals("8")){
						try {
							String a=offic.getBody();
							if(a.indexOf("height=\"350\" width=\"550\"")!=-1){
								a = a.replaceAll("height=\"350\" width=\"550\"", "height=\"400\" width=\"600\"");
							}else if(a.indexOf("width=\"550\" height=\"350\"")!=-1){
								a = a.replaceAll("width=\"550\" height=\"350\"", "height=\"400\" width=\"600\"");
							}
							offic.setBody(a);
							
							if(a.indexOf("http://www.tudou.com")!=-1){
								int start=a.indexOf("http://www.tudou.com");
								int end=a.substring(start).indexOf(".swf")+4;
								String b=a.substring(start,start+end);
								String c[]=b.split("/");
								offic.setBody("http://www.tudou.com/programs/view/html5embed.action?type=0&code="+c[4]+"&lcode=&resourceId="+c[5].split("=")[1]);
							}else if(a.indexOf("http://player.youku.com/player.php")!=-1){
								int start=a.indexOf("http://player.youku.com/player.php");
								int end=a.substring(start).indexOf(".swf")+4;
								String b=a.substring(start,start+end);
								String c[]=b.split("/");
								offic.setBody("http://player.youku.com/embed/"+c[5]);
							}
						} catch (Exception e) {
							
						}
					}else if(offic.getTypeid().equals("18")){
						
					}else if(offic.getTypeid().equals("7")){
						
					}
					return offic;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		
		
		
		
		public String getStr(String id){
			StringDto str = new StringDto();
			DoubleOfficialDto dboffic = new DoubleOfficialDto();
			//sql = " select content from freecms_info where channel=? ";
			Freecms_InfoExample example = new Freecms_InfoExample();
			example.createCriteria().andChannelEqualTo(id);
			try {
				List<Freecms_Info> freecms_Info_List = freecms_InfoMapper.selectByExampleWithBLOBs(example);
				for(Freecms_Info fi : freecms_Info_List){
					dboffic.setId(fi.getId());
					dboffic.setChannel(fi.getChannel());
					dboffic.setAddtime(sf1.format(fi.getAddtime()));
					if(fi.getClicknum() == null){
						dboffic.setClickNum(0);
					}else{
						dboffic.setClickNum(fi.getClicknum());
					}
					dboffic.setTitle(fi.getTitle());
					dboffic.setShortTitle(fi.getShorttitle());
					dboffic.setImg(fi.getImg());
					dboffic.setDescription(fi.getDescription());
					dboffic.setContent(fi.getContent());
					dboffic.setUrl(fi.getUrl());
					dboffic.setAttchs(fi.getAttchs());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = typeedit1(dboffic);
			String s = str.getBody();
			String a = "";
			if(id.equals("d33cee38-8d09-47ef-b1a4-6c81b69aaccb")){
				a ="CompanyTel:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
				s = s.substring(s.indexOf("</s")+3);
				a = a+"CustomerHotline:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
				s = s.substring(s.indexOf("</s")+3);
				a = a+"JoinHotline:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
				s = s.substring(s.indexOf("</s")+3);
				a = a+"ComplaintsTel:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
				s = s.substring(s.indexOf("</s")+3);
				a = a+"CompanyAddress:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"))+";";
				s = s.substring(s.indexOf("</s")+3);
				a = a+"HeadquartersAddress:" + s.substring(s.indexOf("：")+2,s.indexOf("</s"));
			}else{
				a = s ;
			}
			return a;
		}
	
	
		
		
		
		
		
		
	
	public StringDto typeedit1(DoubleOfficialDto doubleoffic){
		StringDto strdto = new StringDto();
		strdto.setBody(doubleoffic.getContent());
		return strdto;
	}
	
	
	
	
	
	public OfficialDto3 typeedit(DoubleOfficialDto doubleoffic){
		OfficialDto3 offic = new OfficialDto3();
		offic.setId(doubleoffic.getId());
		offic.setTypeid(doubleoffic.getChannel());
		offic.setSortrank(doubleoffic.getAddtime());
		offic.setClick(doubleoffic.getClickNum());
		offic.setTitle(doubleoffic.getTitle());
		offic.setShorttitle(doubleoffic.getShortTitle());
		offic.setLitpic(doubleoffic.getImg());
		offic.setPubdate(doubleoffic.getAddtime());
		offic.setSenddate(doubleoffic.getAddtime());
		offic.setKeywords(doubleoffic.getTitle());
		offic.setDescription(doubleoffic.getDescription());
		offic.setBody(doubleoffic.getContent());
		offic.setRedirecturl(doubleoffic.getUrl());
		return offic;
	}
	
	
	
	public Page list(int indexPage,int pagerow,String sql,String type,Freecms_InfoExample example,Object... args){
		Page page = new Page();
		int pageRows = 10;
		if(pagerow!=0){
			pageRows=pagerow;
		}
		try {
			if(type.equals(".guanwang")){
				type=".freecms";
			}
			//long a01 = System.currentTimeMillis();
			//long a02 = System.currentTimeMillis();
			//统计总行数
			//String sql_count = "select count(*) "+sql.substring(sql.indexOf("from"));
			long sumRows = 0;
			//long a03 = System.currentTimeMillis();
			if(args.equals(null)){
				//sumRows = run.query(conn, sql_count,new ScalarHandler<Long>());
				sumRows = freecms_InfoMapper.countByExample(example);
			}else{
				//sumRows = run.query(conn, sql_count,new ScalarHandler<Long>(),args);
			}
			//long a04 = System.currentTimeMillis();
			//总页数
			int sumPages = ((int)sumRows+pageRows-1)/pageRows;
			//判断当前页
			if(indexPage>sumPages){
				indexPage = sumPages;
			}
			if(indexPage < 1){
				indexPage = 1;
			}
			//获取数据
			//String sql_select = sql + " limit ?,?";
			List<OfficialDto3> officlist = new LinkedList<OfficialDto3>();
			List<DoubleOfficialDto> dbofficlist = new LinkedList<DoubleOfficialDto>();
			//dbofficlist = run.query(conn, sql_select, new BeanListHandler<DoubleOfficialDto>(DoubleOfficialDto.class),(indexPage-1)*pageRows, pageRows);
			example.setOffset((indexPage-1)*pageRows);
			example.setSize(pageRows);
			List<Freecms_Info> freecms_Info_List= freecms_InfoMapper.selectByExampleWithBLOBs(example);
			for(Freecms_Info fi : freecms_Info_List){
				DoubleOfficialDto dod = new DoubleOfficialDto();
				dod.setId(fi.getId());
				dod.setChannel(fi.getChannel());
				dod.setAddtime(sf1.format(fi.getAddtime()));
				if(fi.getClicknum() == null){
					dod.setClickNum(0);
				}else{
					dod.setClickNum(fi.getClicknum());
				}
				dod.setTitle(fi.getTitle());
				dod.setShortTitle(fi.getShorttitle());
				dod.setImg(fi.getImg());
				dod.setDescription(fi.getDescription());
				dod.setContent(fi.getContent());
				dod.setUrl(fi.getUrl());
				dod.setAttchs(fi.getAttchs());
				dbofficlist.add(dod);
			}
			
			for(DoubleOfficialDto dbof : dbofficlist){
				officlist.add(typeedit(dbof));
			}
			//封装数据
			page.setList(officlist);
			page.setSumPage(sumPages);
			page.setPageRows(pageRows);
			page.setIndexPage(indexPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return page;
	}
	

}
