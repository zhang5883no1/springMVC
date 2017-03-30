package cn.xidu.app.controller.login;  

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.base.util.ValidateUtil;
import cn.xidu.app.dto.login.LoginDto;

  
@Controller
@RequestMapping("app/controller/login")
public class LoginController {

	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init_reg(Model model,HttpServletRequest request) throws IOException{
		LoginDto loginDto =new LoginDto();
		model.addAttribute("loginDto", loginDto);
		return "/app/login/index";
	}
	
	@RequestMapping(value="dologin" ,method = RequestMethod.POST)
	public String queryCustom(LoginDto loginDto,Model model,HttpServletRequest request){
		HttpSession session=request.getSession();
		if(ValidateUtil.isEmpty(loginDto.getName())&&ValidateUtil.isEmpty(loginDto.getPwd())){
			session.setAttribute("flag", true);
			return "/app/main/index";
		}else{
			model.addAttribute("loginDto", loginDto);
			return "/app/login/index";
		}
	}
}
