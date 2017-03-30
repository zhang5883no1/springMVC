package cn.controller.page;  

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.base.redis.RedisUtil;
import cn.base.util.IPUtil;
import cn.base.util.StringUtil;
import cn.base.util.ValidateUtil;
import cn.base.util.WXUtil;
import cn.xidu.app.dto.active.QueryActiveDto;
import cn.xidu.app.entity.APP_ACTIVE;
  
@Controller
@RequestMapping("page/wx")
public class WXPageController {
	@RequestMapping(value="page" ,method = RequestMethod.GET)
	public String initPage(Model model,HttpServletRequest request) throws IOException{
		String pageName=request.getParameter("page");
		return "/wxpage/"+pageName;
	}
	
}
