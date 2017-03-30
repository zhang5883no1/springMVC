package cn.controller.huitongapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import cn.dao.xinxidu.Freecms_InfoMapper;
import cn.entity.huitongapp.OfficialDto;
import cn.entity.huitongapp.OnlineQADto;
import cn.entity.qdm0020530.DEDE_ARCHIVESExample;
import cn.entity.qdm0020530.DEDE_ARCHIVESExample.Criteria;
import cn.entity.xdchats.ZXDYExample;
import cn.entity.xinxidu.Freecms_Info;
import cn.entity.xinxidu.Freecms_InfoExample;
import cn.entity.xinxidu.OfficialDto3;
import cn.service.huitongapp.HuiTongService;

/**
 * 控制层存储方法的工具类
 * @author LYB
 *
 */
public class BaseController {
	@Autowired
	HuiTongService huitongservice;
	
	SimpleDateFormat sf1=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	
	/**
	 * Official 系列接口通过判断参数然后查询得到带有数据的JSONObject并返回
	 * @param i	接口的代号
	 * @param indexPage	当前页数
	 * @param pageRows	每页显示行数
	 * @return
	 */
	protected JSONObject determine_official_parameters(int i, int indexPage, int pageRows){
		List<OfficialDto> official_list = new ArrayList<OfficialDto>();
		List<OfficialDto3> official3_list = new ArrayList<OfficialDto3>();
		List<Freecms_Info> freecms_Info_List = new ArrayList<Freecms_Info>();
		
		DEDE_ARCHIVESExample archivesExample = new DEDE_ARCHIVESExample();
		Criteria criteria=archivesExample.createCriteria();
		Freecms_InfoExample freecmsExample = new Freecms_InfoExample();
		cn.entity.xinxidu.Freecms_InfoExample.Criteria criteria_2 = freecmsExample.createCriteria();
		
		int sumPages = 0;
		int offset = 0;
		JSONObject json = new JSONObject();
		//	1————hangqing	2————jiepan	3————touzi	4————gonggao	5————riping	6————zhouping	7————yueping
		//各个接口各自不同的条件
		switch (i) {
		case 1:
			criteria.andTypeidEqualTo((short) 18);
			criteria.andArcrankNotEqualTo((short) -2);
			criteria.andLitpicNotEqualTo("");
			archivesExample.setOrderByClause("senddate desc");
			break;
		case 2:
			criteria.andTypeidEqualTo((short) 8);
			archivesExample.setOrderByClause("senddate desc");
			break;
		case 3:
			criteria_2.andChannelEqualTo("8b18c1b9-10b9-4e74-b4ba-55562765997f");
			break;
		case 4:
			criteria.andTypeidEqualTo((short) 30);
			criteria.andArcrankNotEqualTo((short) -2);
			archivesExample.setOrderByClause("pubdate desc");
			break;
		case 5:
			criteria_2.andChannelEqualTo("75614262-82de-4d33-ab9a-fd7b0f01bd9d");
			break;
		case 6:
			criteria_2.andChannelEqualTo("bf7d2e68-712f-48ce-a3d6-d04303954d10");
			break;
		case 7:
			criteria_2.andChannelEqualTo("5c60a234-bb64-4388-be4f-885a6b603708");
			break;
		default:
			break;
		}
		
		if (i==3||i==5||i==6||i==7) {
			freecmsExample.setSize(pageRows);
			freecmsExample.setOrderByClause("addtime desc");
			int sumRows = huitongservice.count_freecms(freecmsExample);
			sumPages = ((int)sumRows+pageRows-1)/pageRows;
			indexPage = getOffset(indexPage, sumPages);
			offset = (indexPage-1)*pageRows;
			freecmsExample.setOffset(offset);
		} else {
			//各个接口相同的条件
			archivesExample.setSize(pageRows);
			sumPages = huitongservice.count_official(archivesExample);
			indexPage = getOffset(indexPage, sumPages);
			offset = (indexPage-1)*pageRows;
			archivesExample.setOffset(offset);
		}
		freecms_Info_List = huitongservice.query_freecms(freecmsExample);
		official3_list = huitongservice.free_to_offic(freecms_Info_List);
		
		//各个接口调用不同的方法	1————hangqing	default————jiepan、touzi、gonggao、riping、zhouping、yueping
		switch (i) {
		case 1:
			official_list = huitongservice.query_archives_article(archivesExample);
			json = return_official("hangqing",official_list, sumPages, indexPage);
			break;
		case 3:
			json = return_hangqing("touzi",official3_list, sumPages, indexPage);
			break;
		case 5:
			json = return_hangqing("riping",official3_list, sumPages, indexPage);
			break;
		case 6:
			json = return_hangqing("zhouping",official3_list, sumPages, indexPage);
			break;
		case 7:
			json = return_hangqing("yueping",official3_list, sumPages, indexPage);
			break;
		default:
			official_list = huitongservice.query_archives(archivesExample);
			json = return_official("other",official_list, sumPages, indexPage);
			break;
		}
		return json;
	}
	
	/**
	 * 交易所简介、公司简介、联系我们   系列接口通过判断参数然后查询得到带有数据的JSONObject并返回
	 * @param i	接口的代号
	 * @return
	 */
	protected JSONObject determine_string_parameters(int i){
		JSONObject json = new JSONObject();
		String result = "";
		//	1————jiaoyi	3————gongsi	2————lianxi
		switch (i) {
		case 1:
			result = huitongservice.query_article(21);
			json = return_String("jiaoyi", result);
			break;
		case 3:
			result = huitongservice.query_article(23);
			json = return_String("gongsi", result);
			break;
		case 2:
			result = huitongservice.query_article(30);
			json = return_String("lianxi", result);
			break;
		default:
			break;
		}
		return json;
	}
	
	/**
	 * 在线答疑   系列接口通过判断参数然后查询得到带有数据的JSONObject并返回
	 * @param i	接口的代号
	 * @param indexPage	当前页数
	 * @param pageRows	每页显示行数
	 * @return
	 */
	protected JSONObject determine_online_parameters(int i, int indexPage, int pageRows){
		List<OnlineQADto> onlineQA_list = new ArrayList<OnlineQADto>();
		JSONObject json = new JSONObject();
		ZXDYExample zxdyExample = new ZXDYExample();
		cn.entity.xdchats.ZXDYExample.Criteria criteria = zxdyExample.createCriteria();
		int sumPages = 0;
//		1————dayi	2————answer
		switch (i) {
		case 1:
			criteria.andAtNotEqualTo("");
			break;
		case 2:
			criteria.andAtEqualTo("");
			break;
		default:
			break;
		}
		zxdyExample.setOrderByClause(" CREATE_DATE desc");
		zxdyExample.setSize(pageRows);
		sumPages = huitongservice.count_zxdy(zxdyExample);
		indexPage = getOffset(indexPage, sumPages);
		int offset = (indexPage-1)*pageRows;
		zxdyExample.setOffset(offset);
		onlineQA_list = huitongservice.query_zxdy(zxdyExample);
		
		json = return_onlineQA(onlineQA_list, sumPages, indexPage);
		return json;
	}
	
	/**
	 * 在线答疑提问接口  通过判断参数然后提交参数并得到返回之后返回带有数据的JSONObject
	 * @param id
	 * @param type
	 * @return
	 */
	protected JSONObject determine_insert_dayi(String type) {
		JSONObject json = new JSONObject();
		//判断提交问题的格式是否正确
		if (type.indexOf("*")==-1) {
			json = return_param_error();
		} else {
			//获取提问人的id以及提问的内容
			int result = 0;
			long question_id = 0;
			String a = type.substring(0,type.indexOf("*"));
			String b = type.substring(type.indexOf("*"));
			try {
				question_id = Long.parseLong(a);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				json = return_param_error();
			}
			String question_content = b;
			//把参数传递过去，得到返回的值
			result = huitongservice.insert_zxdy(question_id, question_content);
			if(result>0)
				json = return_insert_success();
			else
				json = return_insert_failed();
		}
		return json;
	}
	
	/**
	 * Official系列的详情接口
	 * @param id 详情的id
	 * @return JSONObject
	 */
	protected JSONObject determine_detail_official(int id) {
		JSONObject json = new JSONObject();
		List<OfficialDto> official_list = new ArrayList<OfficialDto>();
		official_list = huitongservice.query_archives_detail(id);
		json = return_official("", official_list, 0, 0);
		return json;
	}
	
	/**
	 * freecms系列的详情接口
	 * @param id 详情的id
	 * @return JSONObject
	 */
	protected JSONObject determine_detail_freecms(String id) {
		JSONObject json = new JSONObject();
		List<OfficialDto3> official3_list = new ArrayList<OfficialDto3>();
		Freecms_Info freecms = new Freecms_Info();
		freecms = huitongservice.query_freecms_detail(id);
		List<Freecms_Info> freecms_list = new ArrayList<Freecms_Info>();
		freecms_list.add(freecms);
		official3_list = huitongservice.free_to_offic(freecms_list);
		json = return_hangqing("", official3_list, 0, 0);
		return json;
	}
	
	/**
	 * OnlineQADto系列的详情接口
	 * @param id 详情的id
	 * @return JSONObject
	 */
	protected JSONObject determine_detail_online(int id) {
		JSONObject json = new JSONObject();
		List<OnlineQADto> onlineQA_list = new ArrayList<OnlineQADto>();
		onlineQA_list = huitongservice.query_zxdy_detai(id);
		json = return_onlineQA(onlineQA_list, 0, 0);
		return json;
	}
	
	/**
	 * 判断传来的当前页数是否合理并返回合理的当前页
	 * @param indexPage
	 * @param sumPages
	 * @return
	 */
	protected int getOffset(int indexPage, int sumPages){
		if (indexPage>sumPages)
			indexPage = sumPages;
		if (indexPage<1) 
			indexPage = 1;
		return indexPage;
	}
	
	/**
	 * 返回表示参数错误的JSONObject对象
	 * @return JSONObject
	 */
	protected JSONObject return_param_error(){
		JSONObject json = new JSONObject();
		json.accumulate("flag", 0);
		json.accumulate("msg", PropertiesConfig.readData("2"));
		return json;
	}
	
	/**
	 * 返回表示在线答疑提交成功的JSONObject对象
	 * @return JSONObject
	 */
	protected JSONObject return_insert_success() {
		JSONObject json = new JSONObject();
		json.accumulate("data", true);
		json.accumulate("flag", 1);
		json.accumulate("msg", PropertiesConfig.readData("1"));
		return json;
	}

	/**
	 * 返回表示在线答疑提交失败的JSONObject对象
	 * @return JSONObject
	 */
	protected JSONObject return_insert_failed() {
		JSONObject json = new JSONObject();
		json.accumulate("data", false);
		json.accumulate("flag", 0);
		json.accumulate("msg", PropertiesConfig.readData("3"));
		return json;
	}
	
	/**
	 * 返回List<OfficialDto> 系列的接口（除去精选行情和投资策略）所需的数据，并按照其要求的格式
	 * @param offic_list 要存入的List<OfficialDto>
	 * @param sumPages 总页数
	 * @param indexPage 当前第几页
	 * @return JSONObject
	 */
	protected JSONObject return_official(String interName, List<OfficialDto> offic_list, int sumPages, int indexPage){
		JSONObject json = new JSONObject();
		if(null==offic_list||offic_list.size()==0){
			json.accumulate("flag", 0);
			json.accumulate("msg", PropertiesConfig.readData("3"));
		} else {
			json.accumulate("flag", 1);
			//如果是精选行情接口，对数据进行修整后在转化成JSON
			if("hangqing".equals(interName))
				offic_list = result_hangqing(offic_list);
			else
				offic_list = result_others(offic_list);
			json.accumulate("data",ToJson.official_json(offic_list));
			json.accumulate("indexPage", indexPage);
			json.accumulate("sumPage", sumPages);
			json.accumulate("msg", PropertiesConfig.readData("1"));
		}
		return json;
	}
	
	/**
	 * 返回精选行情和投资策略接口所需的JSON
	 * @param inter 接口的代号
	 * @param contents 要存入的内容
	 * @return JSONObject
	 */
	protected JSONObject return_hangqing(String interName, List<OfficialDto3> offic3_list, int sumPages, int indexPage) {
		JSONObject json = new JSONObject();
		if(null==offic3_list||offic3_list.size()==0){
			json.accumulate("flag", 0);
			json.accumulate("msg", PropertiesConfig.readData("3"));
		} else {
			json.accumulate("flag", 1);
			/*//如果是精选行情接口，对数据进行修整后在转化成JSON
			if("hangqing".equals(interName))
				offic3_list = result_hangqing(offic3_list);
			else
				offic3_list = result_touzi(offic3_list);*/
			json.accumulate("data",ToJson.official3_json(offic3_list));
			json.accumulate("indexPage", indexPage);
			json.accumulate("sumPage", sumPages);
			json.accumulate("msg", PropertiesConfig.readData("1"));
		}
		return json;
	}
	
	/**
	 * 返回 在线答疑 查询系列的接口所需的数据，并按照其要求的格式
	 * @param online_list 要存入的List<OnlineQADto>
	 * @return JSONObject
	 */
	protected JSONObject return_onlineQA(List<OnlineQADto> online_list, int sumPages, int indexPage){
		JSONObject json = new JSONObject();
		if (null==online_list||online_list.size()==0) {
			json.accumulate("flag", 0);
			json.accumulate("msg", PropertiesConfig.readData("3"));
		} else {
			json.accumulate("flag", 1);
			json.accumulate("data",ToJson.online_json(online_list));
			json.accumulate("indexPage", indexPage);
			json.accumulate("sumPage", sumPages);
			json.accumulate("msg", PropertiesConfig.readData("1"));		
		}
		return json;
	}
	
	/**
	 * 根据参数不同，返回 联系我们、交易所简介、公司简介 中的一个接口的数据，并按照其要求的格式
	 * @param inter 接口的代号
	 * @param contents 要存入的内容
	 * @return JSONObject
	 */
	protected JSONObject return_String(String inter, String content){
		JSONObject json = new JSONObject();
		json.accumulate("flag", 1);
		json.accumulate("msg", PropertiesConfig.readData("1"));
		if("lianxi".equals(inter)){
			json.accumulate("data",ToJson.string_json_lianxi(content));
		}else{
			json.accumulate("data",ToJson.string_json_other(inter, content));
		}
		return json;
	}
	
	/**
	 * 对精选行情接口得到的结果进行修改调整
	 * @param offic_list
	 * @return	List<OfficialDto>
	 */
	protected List<OfficialDto> result_hangqing(List<OfficialDto> offic_list) {
		List<OfficialDto> official_list = new ArrayList<OfficialDto>();
		
		for(OfficialDto offic : offic_list){
			System.out.println(offic.getPubdate());
			long pubtime = Long.parseLong(offic.getPubdate());
			long sendtime = Long.parseLong(offic.getSenddate());
			offic.setPubdate(sf1.format(new Date(pubtime*1000L)));
			offic.setSenddate(sf1.format(new Date(sendtime*1000L)));
			try {
				String a=offic.getBody();
				
				if (a.indexOf("&nbsp;") != -1)
	                a = a.replaceAll("&nbsp;", "");
				if(a.indexOf("/strong>")!=-1)
					a = a.substring(a.indexOf("/strong>")+8,a.indexOf("/strong>")+58);
				if(offic.getId()==1854)
					a = a.substring(a.indexOf("/strong>")+8)+offic.getBody().substring(offic.getBody().indexOf("/strong>")+58, offic.getBody().indexOf("/strong>")+66);
				if(a.indexOf("<span style=\"font-size: 14px;\">")!=-1)
					a = a.substring(a.indexOf("<span style=\"font-size: 14px;\">")+31)+offic.getBody().substring(offic.getBody().indexOf("/strong>")+58, offic.getBody().indexOf("/strong>")+89);
				if(a.indexOf("<span style=\"font-size:14px;\">")!=-1)
					a = a.substring(a.indexOf("<span style=\"font-size:14px;\">")+31)+offic.getBody().substring(offic.getBody().indexOf("/strong>")+58, offic.getBody().indexOf("/strong>")+89);
				if(a.substring(0,1).equals("："))
					a = a.substring(1);
				if(a.indexOf("&mdash;")!=-1)
					a = a.replaceAll("&mdash;", "");
				if (a.indexOf("<br />") != -1)
	                a = a.replaceAll("<br />", "<br/>");
	            if (a.indexOf("<br/>") != -1)
	                a = a.replaceAll("<br/>", "");
	            if (a.indexOf(" ") != -1)
	                a = a.replaceAll(" ", "");
	            a = a.replaceAll("[\n\r\t]", "");
	            
	            offic.setBody(a);
			} catch (Exception e) {
				continue;
			}
			official_list.add(offic);
		}
		
		return official_list;
	}
	
	/**
	 * 对投资策略接口得到的结果进行修改调整
	 * @param offic_list
	 * @return	List<OfficialDto>
	 */
	protected List<OfficialDto3> result_touzi(List<OfficialDto3> offic_list3) {
		List<OfficialDto3> official3_list = new ArrayList<OfficialDto3>();
		for(OfficialDto3 offic : offic_list3){
			long pubtime = Long.parseLong(offic.getPubdate());
			long sendtime = Long.parseLong(offic.getSenddate());
			offic.setPubdate(sf1.format(new Date(pubtime*1000L)));
			offic.setSenddate(sf1.format(new Date(sendtime*1000L)));
			official3_list.add(offic);
		}
		return official3_list;
	}
	
	/**
	 * 对精选行情和投资策略以外的接口得到的结果进行修改调整
	 * @param offic_list
	 * @return	List<OfficialDto>
	 */
	protected List<OfficialDto> result_others(List<OfficialDto> offic_list) {
		List<OfficialDto> official_list = new ArrayList<OfficialDto>();
		for(OfficialDto offic : offic_list){
			long pubtime = Long.parseLong(offic.getPubdate());
			long sendtime = Long.parseLong(offic.getSenddate());
			offic.setPubdate(sf1.format(new Date(pubtime*1000L)));
			offic.setSenddate(sf1.format(new Date(sendtime*1000L)));
			official_list.add(offic);
		}
		return official_list;
	}
	
	/**
	 * 对联系我门接口得到的结果进行修改调整
	 * @param offic_list
	 * @return	List<OfficialDto>
	 */
	protected String result_lianxi(String lianxi) {
		String a = "";
		a ="CompanyTel:" + lianxi.substring(lianxi.indexOf("：")+2,lianxi.indexOf("</s"))+";";
		lianxi = lianxi.substring(lianxi.indexOf("</s")+3);
		a = a+"CustomerHotline:" + lianxi.substring(lianxi.indexOf("：")+2,lianxi.indexOf("</s"))+";";
		lianxi = lianxi.substring(lianxi.indexOf("</s")+3);
		a = a+"JoinHotline:" + lianxi.substring(lianxi.indexOf("：")+2,lianxi.indexOf("</s"))+";";
		lianxi = lianxi.substring(lianxi.indexOf("</s")+3);
		a = a+"ComplaintsTel:" + lianxi.substring(lianxi.indexOf("：")+2,lianxi.indexOf("</s"))+";";
		lianxi = lianxi.substring(lianxi.indexOf("</s")+3);
		a = a+"CompanyAddress:" + lianxi.substring(lianxi.indexOf("：")+2,lianxi.indexOf("</s"))+";";
		return a;
	}
	
	
	/**
	 * 对在线答疑接口得到的结果进行修改调整
	 * @param offic_list
	 * @return	List<OnlineQADto> onlineQA_list
	 */
	protected List<OnlineQADto> result_online(List<OnlineQADto> online_list) {
		List<OnlineQADto> onlineQA_list = new ArrayList<OnlineQADto>();
		for(OnlineQADto online : online_list){
			long pubtime = Long.parseLong(online.getAntime());
			long sendtime = Long.parseLong(online.getQutime());
			online.setAntime(sf1.format(new Date(pubtime*1000L)));
			online.setQutime(sf1.format(new Date(sendtime*1000L)));
			onlineQA_list.add(online);
		}
		return onlineQA_list;
	}
	
	/**
	 * 对Official详情系列的接口得到的结果进行修改调整
	 * @param offic_list
	 * @return	List<OfficialDto>
	 */
	protected List<OfficialDto> result_official_detaill(List<OfficialDto> offic_list) {
		List<OfficialDto> official_list = new ArrayList<OfficialDto>();
		
		for(OfficialDto offic : offic_list){
			String b0 = offic.getBody();
			if(b0.indexOf("src=\"/upload")!=-1)
				b0 = b0.replaceAll("src=\"/upload", "src=\"http://www.xiduoil.com/upload");
			if(b0.indexOf("font-size:14px")!=-1){
				b0 = b0.replaceAll("font-size:14px", "font-size:28px;text-align:left;line-height: 2");
			}else{
				if(b0.indexOf("font-size: 14px")!=-1)
					b0 = b0.replaceAll("font-size: 14px", "font-size:28px;text-align:left;line-height: 2");
			}
			offic.setBody(b0);
			if(offic.getTypeid()==8){
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
			}else if(offic.getTypeid()==18){
				try {
					String a=offic.getBody();
					if(a.indexOf("width:650px")!=-1){
						a = a.replaceAll("width:650px", "width:100%");
					}else if(a.indexOf("width: 650px")!=-1){
						a = a.replaceAll("width: 650px", "width:100%");
					}
					if(a.indexOf("margin-left: 25px;")!=-1)
						a = a.replaceAll("margin-left: 25px;", "");
					if(a.indexOf("margin-right: 25px;")!=-1)
						a = a.replaceAll("margin-right: 25px;", "");
					if(a.indexOf("&mdash;")!=-1)
						a = a.replaceAll("&mdash;", "");
					offic.setBody(a);
				} catch (Exception e) {
					
				}
			}else if(offic.getTypeid()==7){
				if(b0.indexOf("font-size:9.5pt")!=-1)
					b0 = b0.replaceAll("font-size:9.5pt", "font-size:28px");
				if(b0.indexOf("font-size:16px")!=-1)
					b0 = b0.replaceAll("font-size:16px", "font-size:28px;text-align:left");
				if(b0.indexOf("font-size: 14px")!=-1)
					b0 = b0.replaceAll("font-size: 14px", "font-size:28px");
				if(b0.indexOf("line-height: 15.25pt")!=-1)
					b0 = b0.replaceAll("line-height: 15.25pt", "line-height: 2");
				if(b0.indexOf("color: black")!=-1)
					b0 = b0.replaceAll("color: black", "color: black;text-align:left;font-size:28px");
				if(b0.indexOf("<a href=\"http://www.xiduoil.com/\">西都石油</a>")!=-1)
					b0 = b0.replaceAll("<a href=\"http://www.xiduoil.com/\">西都石油</a>", "<span style=\"font-size:28px;text-align:left;\"><a href=\"http://www.xiduoil.com/\">西都石油</a></span>");
				offic.setBody(b0);
			}
			long ptime = Long.parseLong(offic.getPubdate());
			long stime = Long.parseLong(offic.getSenddate());
			offic.setPubdate(sf1.format(new Date(ptime*1000L)));
			offic.setSenddate(sf1.format(new Date(stime*1000L)));
			official_list.add(offic);
		}
		return official_list;
	}
	
	/**
	 * 名师团队列表接口
	 * @return	JSONObject
	 */
	public JSONObject determine_list_teacher(){
		JSONObject json = new JSONObject();
		List<String> list = new ArrayList<String>();
		int teacherSize = 0;
		try {
			String a = PropertiesConfig.readTeacher("teachernum");
			if(a==null||"".equals(a)){
				return null;
			}
			teacherSize = Integer.parseInt(a);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		for(int i = 1; i <= teacherSize; i++){
			String content = PropertiesConfig.readTeacher("teacher"+i);
			list.add(content); 
		}
		json.accumulate("data", ToJson.teacher_list(list));
		json.accumulate("flag", 1);
		json.accumulate("msg", PropertiesConfig.readData("1"));
		return json;
	}
	
	/**
	 * 名师团队详情接口
	 * @param name 老师id
	 * @return JSONObject
	 */
	public JSONObject determine_detail_teacher(String name){
		JSONObject json = new JSONObject();
		List<String> list = new ArrayList<String>();
		int teacherSize = 0;
		try {
			teacherSize = Integer.parseInt(PropertiesConfig.readTeacher("teachernum"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		for(int i = 1; i <= teacherSize; i++){
			String content = PropertiesConfig.readTeacher("teacher"+i);
			list.add(content); 
		}
		String detail = "";
		for(int i = 0;i < list.size();i++){
			String [] ss = list.get(i).split("[*]",2);
			String [] bb = ss[0].split("：");
			if(bb[1].equals(name)){
				detail = ss[1];
			}
		}
		if("".equals(detail)){
			return null;
		}else{
			
		}
		json.accumulate("data", detail);
		json.accumulate("flag", 1);
		json.accumulate("msg", PropertiesConfig.readData("1"));
		return json;
	}
}
