package cn.xidu.app.controller.user;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.base.util.JsonDateValueProcessor;
import cn.base.util.ValidateUtil;
import cn.entity.xxdzhibo.XXD_Customer;
import cn.service.guanwang.GW_Reg_Service;
import cn.service.xxdzhibo.XxdZhiboService;
import cn.xidu.app.dto.user.APP_User_Dto;
import cn.xidu.app.dto.user.QueryUserDto;
import cn.xidu.app.entity.APP_USER;
import cn.xidu.app.service.user.APP_UserService;

/**
 * @ClassName: ActiveController
 * @Description: 热门活动服务
 * @author ZHANGCHENG
 * @date 2016-9-21 下午4:39:20
 * 
 */
@Controller
@RequestMapping("app/controller/user")
public class UserController {

	@Autowired
	private APP_UserService userService;
	@Autowired 
	private GW_Reg_Service gw_reg_service;
	@Autowired
	private XxdZhiboService xxd_zhibo_service;

	/**
	 * @Title: init_reg
	 * @Description: 初始服务
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @param @throws IOException
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public String init_reg(Model model, HttpServletRequest request)
			throws IOException {
		QueryUserDto<APP_USER> queryDto = new QueryUserDto<APP_USER>();
		userService.query(queryDto);
		model.addAttribute("queryDto", queryDto);
		return "/app/user/list";
	}

	/**
	 * @Title: queryCustom
	 * @Description: 查询
	 * @param @param queryDto
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public String queryCustom(QueryUserDto<APP_USER> queryDto, Model model,
			HttpServletRequest request) {
		userService.query(queryDto);
		model.addAttribute("queryDto", queryDto);
		return "/app/user/list";
	}

	/** 
	* @Title: findByNamePwd 
	* @Description: APP 登录
	* @param @param request
	* @param @return
	* @return String
	* @throws 
	*/ 
	@RequestMapping(value = "login/json", method = RequestMethod.GET)
	@ResponseBody
	public String findByNamePwd(HttpServletRequest request) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		JSONObject json = new JSONObject();

		APP_USER user=new APP_USER();
		user.setName(name);
		APP_User_Dto dto = userService.getAPPUser(user);
		if (ValidateUtil.isEmpty(dto.getId())) {
			if (dto.getPwd().equals(pwd)) {
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
				json.accumulate("key", "1");
				json.accumulate("msg", "登录成功");
				json.accumulate("userInfo", json.fromObject(dto, jsonConfig).toString());
			} else {
				json.accumulate("key", "0");
				json.accumulate("msg", "密码错误");
				json.accumulate("userInfo", "");
			}
		}else{
			json.accumulate("key", "0");
			json.accumulate("msg", "用户不存在");
			json.accumulate("userInfo", "");
		}

		String callback = request.getParameter("callback");
		if (ValidateUtil.isEmpty(callback)) {
			return callback + "(" + json.toString() + ")";
		} else {
			return json.toString();
		}
	}
	
	@RequestMapping(value = "reg/json", method = RequestMethod.GET)
	@ResponseBody
	public String reg(HttpServletRequest request){
		String orignTimes="";
		String mob="";
		try {
			orignTimes=request.getSession().getAttribute("times").toString();
			mob=request.getSession().getAttribute("mob").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		String times=request.getParameter("times");
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String source=request.getParameter("source");
		JSONObject json=new JSONObject();
		
		if(ValidateUtil.isEmpty(orignTimes)&&ValidateUtil.isEmpty(mob)&&ValidateUtil.isEmpty(name)&&ValidateUtil.isEmpty(pwd)){
			if(orignTimes.equals(times)){
				XXD_Customer customer=new XXD_Customer();
				customer.setUsername(name);
				if(xxd_zhibo_service.validCustomerExist(customer)){
					json.accumulate("key", "0");
					json.accumulate("msg", "用户名已存在");
				}else{
					APP_USER user=new APP_USER();
					user.setMob(mob);
					user.setName(name);
					user.setPwd(pwd);
					user.setSource(source);
					json=userService.reg(user);
					if("1".equals(json.get("key"))){
						XXD_Customer xxd_cu=new XXD_Customer();
						xxd_cu.setDefault();
						xxd_cu.setRoomno("2");
						xxd_cu.setUsername(user.getName());
						xxd_cu.setPwd(user.getPwd());
						xxd_cu.setNickname(user.getName());
						xxd_cu.setLinksource(source);
						xxd_zhibo_service.add(xxd_cu);
					}
				}
			}else{
				json.accumulate("key", "0");
				json.accumulate("msg", "验证错误");
			}
		}else{
			json.accumulate("key", "0");
			json.accumulate("msg", "信息错误");
		}

		String callback = request.getParameter("callback");
		if (ValidateUtil.isEmpty(callback)) {
			return callback + "(" + json.toString() + ")";
		} else {
			return json.toString();
		}
	}
	
	@RequestMapping(value = "getCode/json", method = RequestMethod.GET)
	@ResponseBody
	public String getCode(HttpServletRequest request) throws NumberFormatException, ParseException{
		String mob=request.getParameter("mob");
		String type=request.getParameter("type");
		String source=request.getParameter("source");
		JSONObject json=new JSONObject();
		
		if(ValidateUtil.isEmpty(mob)&&ValidateUtil.isEmpty(type)&&ValidateUtil.isEmpty(source)&&"appSMS".equals(type)){
			//进入发送短信业务
			json=gw_reg_service.SendCode(mob, source, source);
		}else{
			json.accumulate("k", "0");
			json.accumulate("reasonNum", "9");
			json.accumulate("reason", "信息错误");
		}

		String callback = request.getParameter("callback");
		if (ValidateUtil.isEmpty(callback)) {
			return callback + "(" + json.toString() + ")";
		} else {
			return json.toString();
		}
	}
	
	@RequestMapping(value = "validCode/json", method = RequestMethod.GET)
	@ResponseBody
	public String validCode(HttpServletRequest request){
		String mob=request.getParameter("mob");
		String type=request.getParameter("type");
		String code=request.getParameter("code");
		JSONObject json=new JSONObject();
		
		if(ValidateUtil.isEmpty(mob)&&ValidateUtil.isEmpty(type)&&ValidateUtil.isEmpty(code)&&"appSMS".equals(type)&&gw_reg_service.validCode(mob, code)){
			APP_USER user=new APP_USER();
			user.setMob(mob);
			APP_User_Dto dto = userService.getAPPUser(user);
			if(ValidateUtil.isEmpty(dto.getId())){
				json.accumulate("key", "0");
				json.accumulate("msg", "手机号码已存在");
			}else{
				json.accumulate("key", "1");
				json.accumulate("msg", "验证成功");
				long times=new Date().getTime();
				json.accumulate("times",times );
				request.getSession().setAttribute("times", times);
				request.getSession().setMaxInactiveInterval(300);
				request.getSession().setAttribute("mob", mob);
				request.getSession().setMaxInactiveInterval(300);
			}
		}else{
			json.accumulate("key", "0");
			json.accumulate("msg", "验证码错误");
		}

		String callback = request.getParameter("callback");
		if (ValidateUtil.isEmpty(callback)) {
			return callback + "(" + json.toString() + ")";
		} else {
			return json.toString();
		}
	}
}
