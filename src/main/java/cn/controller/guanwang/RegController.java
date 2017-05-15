package cn.controller.guanwang;  

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.base.crm.dto.CRM_INFO_DTO;
import cn.base.crm.webservice.WebServiceSoap_WebServiceSoap_Client;
import cn.base.util.HttpUtil;
import cn.base.util.IPUtil;
import cn.base.util.PropertiesConfig;
import cn.base.util.ValidateUtil;
import cn.entity.guanwang.GW_DEDE_MEMBERWithBLOBs;
import cn.entity.local.CRM_Entity2;
import cn.service.guanwang.GW_Reg_Service;
import cn.service.local.LocalService;


  
/** 
* @ClassName: RegController 
* @Description: guanwang 注册 
* @author ZHANGCHENG
* @date 2016-9-8 下午2:02:34 
*  
*/ 
@Controller
@RequestMapping("guangwang/reg")
public class RegController {
	
	@Autowired 
	private GW_Reg_Service gw_reg_service;
	@Autowired
	private LocalService service;

	/** 
	* @Title: init_reg 
	* @Description: 页面加载，初始化信息，绑定session信息
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws IOException
	* @return String
	* @throws 
	*/ 
	@RequestMapping(value="Init_Reg" ,method = RequestMethod.GET)
	@ResponseBody
	public String init_reg(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		String times=new Date().getTime()+"";
		session.setAttribute("flag", times);
		
		JSONObject json=new JSONObject();
		json.accumulate("ssid", session.getId());
		json.accumulate("times", times);
		String responseText = json.toString();
		
		String callback=request.getParameter("callback");
		if(ValidateUtil.isEmpty(callback)){
			return callback+"("+responseText+")";
		}else{
			return responseText;
		}
	}
	
	/** 
	* @Title: Get_SMS_Code 
	* @Description: 调用发送短信接口
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException
	* @param @throws NumberFormatException
	* @param @throws ParseException
	* @return String
	* @throws 
	*/ 
	@RequestMapping(value="Get_SMS_Code" ,method = RequestMethod.GET)
	@ResponseBody
	public String Get_SMS_Code(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, ParseException {
		String mobile=request.getParameter("mob");
		String referer=request.getParameter("referer");
		String linksource=request.getParameter("linksource");
		String scode=request.getParameter("scode");
		
		//判断时间戳是否正确
		HttpSession session=request.getSession();
		if(!scode.equals(session.getAttribute("flag").toString())){
			return "";
		}
		//进入发送短信业务
		JSONObject json=new JSONObject();
		json=gw_reg_service.SendCode(mobile, referer, linksource);
		//刷新时间戳
		String times=new Date().getTime()+"";
		session.setAttribute("flag", times);
		json.accumulate("times", times);
		//返回数据
		String responseText = json.toString();
		String callback=request.getParameter("callback");
		if(ValidateUtil.isEmpty(callback)){
			return callback+"("+responseText+")";
		}else{
			return responseText;
		}
	}
	
	/** 
	* @Title: Put_Reg_Info 
	* @Description: 注册
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws UnsupportedEncodingException
	* @return String
	* @throws 
	*/ 
	@RequestMapping(value="Put_Reg_Info" ,method = RequestMethod.GET)
	@ResponseBody
	public String Put_Reg_Info(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		HttpSession session=request.getSession();
		String scode=request.getParameter("scode");
		if(!scode.equals(session.getAttribute("flag").toString())){
			return "";
		}
		
		String userName=URLDecoder.decode(request.getParameter("username"),"UTF-8");
		String mobile=request.getParameter("mobile");
		String code=request.getParameter("code");
		String source=request.getParameter("source");
		String reffer=request.getParameter("reffer");
		String ip=new IPUtil().getRemortIP(request);
		Long date=new Date().getTime()/1000L;
		
		GW_DEDE_MEMBERWithBLOBs record=new GW_DEDE_MEMBERWithBLOBs();
		record.setUname(userName);
		record.setUserid(mobile);
		record.setLinksource(source);
		record.setReferer(reffer);
		record.setJoinip(ip);
		record.setJointime(date.intValue());
		//调用注册方法
		JSONObject ob=null;
		ob=gw_reg_service.DoReg(record, code);
		//返回数据
		String times=new Date().getTime()+"";
		session.setAttribute("flag", times);
		ob.accumulate("times", times);
		
		//crm
		if("1".equals(ob.getString("code"))){
			CRM_INFO_DTO dto=new CRM_INFO_DTO(record);
			dto.setSource(code);
			dto.setQq(new IPUtil().getRemortIP(request));
			WebServiceSoap_WebServiceSoap_Client.client(dto.getNewCrmJsonString());
		}
		
		//返回数据
		String responseText=ob.toString();
		String callback=request.getParameter("callback");
		if(ValidateUtil.isEmpty(callback)){
			return callback+"("+responseText+")";
		}else{
			return responseText;
		}
	}
	
	@RequestMapping(value="getCrm" ,method = RequestMethod.GET)
	@ResponseBody
	public String getCrm(HttpServletRequest request, HttpServletResponse response){
		int a=Integer.valueOf(request.getParameter("i"));
		System.out.println("get info start");
		for(int i=a;i<7200;i++){
			System.out.println("get info page :"+i);
//			String getDate_json="{\"WorkingState\":\"\",\"CusID\":\"\",\"Source\":\"\",\"ResourcesState\":\"\",\"AllotNum\":-1,\"PageIndex\":"+i+",\"PageSize\":100,\"PageCount\":417552,\"Dell\":false,\"IsFenPang\":\"\",\"Name\":\"\",\"Phone\":\"\",\"TaskGuid\":\"b4026263-704e-4e12-a64d-f79cb42962cc\",\"DataType\":\"CustomerCompanyPages1\",\"Start_time\":\"1900-01-01T00:00:00\",\"End_time\":\"2900-01-01T00:00:00\",\"EmpGuid\":\"\",\"EmplName\":\"\",\"OrgId\":\"A3\",\"OrgName\":\"\",\"AllotStart\":\"1900-01-01T00:00:00\",\"AllotEnd\":\"2900-01-01T00:00:00\"}";
			String getDate_json="{\"TaskGuid\":\"b4026263-704e-4e12-a64d-f79cb42962cc\",\"DataType\":\"CustomerSellPages1\",\"_AcccountID\":\"\",\"Phone\":\"\",\"AllotNum\":-1,\"PageIndex\":"+i+",\"PageSize\":100,\"PageCount\":461024,\"WorkingState\":\"\",\"AllotStart\":\"1900-01-01T00:00:00\",\"AllotEnd\":\"2900-01-01T00:00:00\",\"BingDate\":\"1900-01-01T00:00:00\",\"EndDate\":\"2900-01-01T00:00:00\",\"BingMioao\":0,\"EndMiao\":0,\"AcccountID\":\"\",\"ID\":\"\",\"CusID\":\"\",\"CusName\":\"\",\"EmplGuid\":\"\",\"EmplName\":\"\",\"OrgID\":\"A3\",\"OrgName\":\"\",\"ReportDate\":\"2016-12-06T18:43:59.534494+08:00\",\"CreatDate\":\"0001-01-01T00:00:00\",\"PhoneState\":\"\",\"PlayFile\":null,\"Type\":\"\",\"ServiceEmpGuid\":\"\",\"ServiceEmpName\":\"\",\"Examine\":\"\",\"ResourcesState\":\"\",\"Source\":\"\"}";
			java.lang.String _setData__return = new WebServiceSoap_WebServiceSoap_Client().getData(getDate_json);
			org.json.JSONArray array=new org.json.JSONArray(_setData__return);
			for(int j=0;j<array.length();j++){
				System.out.println("--------------------------------------------");
				System.out.println(j);
				org.json.JSONObject json=(org.json.JSONObject)array.get(j);
				CRM_Entity2 entity=new CRM_Entity2();
				entity.setDate(json.getString("CreateDate"));
				entity.setMob(json.getString("Phone1"));
				entity.setName(json.getString("CusName"));
				entity.setSource(json.getString("Source"));
				entity.setCode(json.getString("CusID"));
				entity.setLurl(json.getString("l_url"));
				entity.setRurl(json.getString("r_url"));
				try {
					service.insertUser(entity);
				} catch (Exception e) {
					continue;
					// TODO: handle exception
				}
			}
		}
		return "begin";
	}
}
