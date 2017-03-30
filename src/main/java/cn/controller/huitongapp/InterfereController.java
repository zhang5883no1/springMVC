package cn.controller.huitongapp;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @ClassName: MainController 
* @Description: 汇通接口
* @author LYB
* @date 2016-9-5 下午3:18:56 
*  
*/ 
@Controller
@RequestMapping("/")
public class InterfereController extends BaseController{

	@RequestMapping("ZhuBan")
	public void  list(HttpServletRequest req, HttpServletResponse resp){
		//创建返回的工具类
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		//得到传递过来的参数
		String type = "";
		String defference = "";
		type = req.getParameter("type");
		defference = req.getParameter("defference");
			
		try {
			int indexPage = 0;
			out = resp.getWriter();
			int pageRows = 0;
			try {
				if(null==req.getParameter("indexPage")||"".equals(req.getParameter("indexPage"))){
					indexPage = 1;//不给，默认为1
				}else{
					indexPage = Integer.parseInt(req.getParameter("indexPage"));
				}
				if(null==req.getParameter("pageRows")||"".equals(req.getParameter("pageRows"))){
					pageRows = 10;//不给，默认为10
				}else{
					pageRows = Integer.parseInt(req.getParameter("pageRows"));
				}
			} catch (NumberFormatException e) {
				json = return_param_error();
				return ;
			}
			//判断参数条件，根据不同的参数创建不同的查询类，并把条件赋予查询类，然后把查询类作为参数传递给方法
			if (".guanwang".equals(type)) {
				
				if ("hangqing".equals(defference)) {
					json = determine_official_parameters(1,indexPage,pageRows);
				} else if ("jiepan".equals(defference)) {
					json = determine_official_parameters(2,indexPage,pageRows);
				} else if ("touzi".equals(defference)) {
					json = determine_official_parameters(3,indexPage,pageRows);
				} else if ("gonggao".equals(defference)) {
					json = determine_official_parameters(4,indexPage,pageRows);
				} else if ("riping".equals(defference)) {
					json = determine_official_parameters(5,indexPage,pageRows);
				} else if ("zhouping".equals(defference)) {
					json = determine_official_parameters(6,indexPage,pageRows);
				} else if ("yueping".equals(defference)) {
					json = determine_official_parameters(7,indexPage,pageRows);
				} else if ("jiaoyi".equals(defference)) {
					json = determine_string_parameters(1);
				} else if ("lianxi".equals(defference)) {
					json = determine_string_parameters(2);
				} else if ("gongsi".equals(defference)) {
					json = determine_string_parameters(3);
				} else {
					json = return_param_error();
				}
			} else if (".lybzhibo".equals(type)) {
				if ("dayi".equals(defference)) {
					json = determine_online_parameters(1,indexPage,pageRows);
				} else if ("answer".equals(defference)) {
					json = determine_online_parameters(2,indexPage,pageRows);
				} else {
					json = return_param_error();
				}
			} else {
				json = return_param_error();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			json = return_param_error();
			return ;
		} finally {
			//把数据返回出去
			String responseText = json.toString();
			if(null==req.getParameter("callback")||"".equals(req.getParameter("callback"))){
				out.print(responseText);
			}else{
				out.print(req.getParameter("callback")+"("+responseText+")");
			}
			out.flush();
			out.close();
		}
		
	}
	@RequestMapping("Detail")
	public void detail(HttpServletRequest req, HttpServletResponse resp){
		//创建返回的工具类
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		
		try {
			out = resp.getWriter();
			int id = 0;
			String sid = "";
			String type = "";
			sid = req.getParameter("id");
			if (sid.indexOf("-")!=-1) {
				json = determine_detail_freecms(sid);
			}else{
				try {
					if(!"".equals(sid)||null!=sid)
						id = Integer.parseInt(sid);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					json = return_param_error();
					return ;
				}
				//得到传递过来的参数
				type = req.getParameter("type");
				type = new String(type.getBytes("iso8859-1"),"utf-8");
				//通过判断参数来调用不同的方法得到返回数据：id=-1表示提交问题，>0则表示详情查询
				if (id==-1) {
					json = determine_insert_dayi(type);
				} else if (id>0) {
					if("OfficialDto".equals(type)){
						json = determine_detail_official(id);
					}else if("OnlineQADto".equals(type)){
						json = determine_detail_online(id);
					}
				} else {
					json = return_param_error();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			json = return_param_error();
			return ;
		} finally {
			//把数据返回出去
			String responseText = json.toString();
			if(null==req.getParameter("callback")||"".equals(req.getParameter("callback"))){
				out.print(responseText);
			}else{
				out.print(req.getParameter("callback")+"("+responseText+")");
			}
			out.flush();
			out.close();
		}
		
	}
	
	@RequestMapping("Team")
	public void Teacher(HttpServletRequest req, HttpServletResponse resp){
		//创建返回的工具类
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {
			out = resp.getWriter();
			String type = "";
			String name = "";
			if(!"".equals(req.getParameter("type"))||null!=req.getParameter("type"))
				type = req.getParameter("type");
			if(!"".equals(req.getParameter("name"))||null!=req.getParameter("name"))
				name = req.getParameter("name");
			
			if("list".equals(type)){
				if("none".equals(name)){
					json = determine_list_teacher();
				}else{
					json = return_param_error();
				}
			}else if("detail".equals(type)){
				json = determine_detail_teacher(name);
			}else{
				json = return_param_error();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//把数据返回出去
			String responseText = json.toString();
			if(null==req.getParameter("callback")||"".equals(req.getParameter("callback"))){
				out.print(responseText);
			}else{
				out.print(req.getParameter("callback")+"("+responseText+")");
			}
			out.flush();
			out.close();
		}
	}
	
}
