package cn.controller.xinxidu;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.base.util.MsgData;
import cn.base.util.PropertiesConfig;
import cn.entity.xinxidu.BillDto;
import cn.entity.xinxidu.Freecms_InfoExample;
import cn.entity.xinxidu.OfficialDto3;
import cn.entity.xinxidu.OnlineQADto;
import cn.entity.xinxidu.Page;
import cn.entity.xinxidu.Reflex;
import cn.service.xinxidu.Freecms_InfoMapperService;

@Controller
@RequestMapping("/")
public class DBMaiBoardCodeController {
	
	@Autowired
	private Freecms_InfoMapperService freecms_InfoMapperService;
	
	private String sql;
	
	@RequestMapping("ZhuBan1")
	public void getOffic(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		List<OfficialDto3> officlist = new LinkedList<OfficialDto3>(); 
		List<BillDto> billlist = new LinkedList<BillDto>();
		List<OnlineQADto> onlinelist = new LinkedList<OnlineQADto>();
		Reflex rf = new Reflex();
		PrintWriter out = response.getWriter();
		
		PropertiesConfig config=new PropertiesConfig();
		
		JSONObject json = new JSONObject();
		
		//得到提交过来的请求的名字
		String type = request.getParameter("type");
		String defference = request.getParameter("defference");
		//********************************************//*
		String date = request.getParameter("date");
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		Date time = null;
		long longdate = 0;
		if(null==request.getParameter("date")||"".equals(request.getParameter("date"))){
			
		}else{
			try {
				time = d.parse(date);
				longdate = time.getTime();
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
		}
		//*******************************************//*
		int rp = 0;
		if(null==request.getParameter("rp")||"".equals(request.getParameter("rp"))){
			
		}else{
			try {
				rp = Integer.parseInt(request.getParameter("rp"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		int indexPage = 0;
		int pageRows = 0;
		try {
			if(null==request.getParameter("pageRows")||"".equals(request.getParameter("pageRows"))){
				pageRows=10;
			}else{
				pageRows = Integer.parseInt(request.getParameter("pageRows"));
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		try {
			if(null==request.getParameter("indexPage")||"".equals(request.getParameter("indexPage"))){
				indexPage=10;
			}else{
				indexPage = Integer.parseInt(request.getParameter("indexPage"));
			}
		} catch (NumberFormatException e) {
			type = "out";
			e.printStackTrace();	
		}
		if(type.equals(".guanwang")){
			//dosomething   查询数据，得到page对象数据
			Page page = null;
			if(defference.equals("hangqing")){
				page = freecms_InfoMapperService.getOfficHangQing(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("jiepan")){
				page = freecms_InfoMapperService.getOfficJiePan(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("touzi")){
				page = freecms_InfoMapperService.getOfficTouZi(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("gonggao")){
				page = freecms_InfoMapperService.getOfficGongGao(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("qydt")){
				page = freecms_InfoMapperService.getOfficQydt(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("riping")){
				page = freecms_InfoMapperService.getOfficRiPing(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("zhouping")){
				page = freecms_InfoMapperService.getOfficZhouPing(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("yueping")){
				page = freecms_InfoMapperService.getOfficYuePing(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("jiaoyi")){
				page = freecms_InfoMapperService.getOfficJiaoYi(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("gongsi")){
				page = freecms_InfoMapperService.getOfficGongSi(type,defference,indexPage,pageRows,longdate,rp);
			}else if(defference.equals("lianxi")){
				page = freecms_InfoMapperService.getOfficLianXi(type,defference,indexPage,pageRows,longdate,rp);
			}else{
				page = freecms_InfoMapperService.getOfficOther(type,defference,indexPage,pageRows,longdate,rp);
			}
			
			officlist = (List<OfficialDto3>)page.getList();
			//把得到的结果存入json
			if(officlist==null){
				json.accumulate("flag", 0);
				json.accumulate("msg", config.readData("msg.properties", "3"));
			}else if(officlist.toString().equals("[err]")){
				json.accumulate("flag", 0);
				json.accumulate("msg", config.readData("msg.properties", "2"));
			}else if((officlist.toString()).equals("["+freecms_InfoMapperService.getStr("d33cee38-8d09-47ef-b1a4-6c81b69aaccb")+"]")){   //lianxi
				String res = officlist.toString();
				String res1 = res.substring(res.indexOf("[")+1,res.indexOf("]"));
				json.accumulate("flag", 1);
				json.accumulate("data",rf.tostrings(res1));
				json.accumulate("msg", config.readData("msg.properties", "1"));
			}else if((officlist.toString()).equals("["+freecms_InfoMapperService.getStr("8278e576-cf53-4e8e-8757-f423a52c7d64")+"]")){   //jiaoyi
				String res = officlist.toString();
				String res1 = res.substring(res.indexOf("[")+1,res.indexOf("]"));
				json.accumulate("flag", 1);
				json.accumulate("data",rf.tostrings2(res1));
				json.accumulate("msg", config.readData("msg.properties", "1"));
			}else if((officlist.toString()).equals("["+freecms_InfoMapperService.getStr("30315049-c8be-4fd0-bc04-1cff3397e14e")+"]")){  //gongsi
				String res = officlist.toString();
				String res1 = res.substring(res.indexOf("[")+1,res.indexOf("]"));
				json.accumulate("flag", 1);
				json.accumulate("data",rf.tostrings3(res1));
				json.accumulate("msg", config.readData("msg.properties", "1"));
			}else{
				json.accumulate("flag", 1);
				json.accumulate("data",rf.toOffic3json(officlist));
				json.accumulate("indexPage", page.getIndexPage());
				json.accumulate("sumPage", page.getSumPage());
				json.accumulate("msg", config.readData("msg.properties", "1"));
			}
		}else{
			json.accumulate("flag", 0);
			json.accumulate("msg", config.readData("msg.properties", "2"));
		}
		
		//把数据返回出去
		String responseText = json.toString();
		out.print(responseText);
		out.flush();
		out.close();
	}
}