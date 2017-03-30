package cn.controller.page;  

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.base.redis.RedisUtil;
import cn.base.util.IPUtil;
import cn.base.util.ValidateUtil;
  
@Controller
@RequestMapping("c/static/wx")
public class WXCountController {
	@RequestMapping(value="initCount" ,method = RequestMethod.GET)
	@ResponseBody 
	public String wxCount(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String type=request.getParameter("type");
		String ip=new IPUtil().getRemortIP(request);
		String countnum=getCount(ip,type);
		JSONObject json=new JSONObject();
		json.accumulate("counts", countnum);
		String responseText = json.toString();
		String callback=request.getParameter("callback");
		if(ValidateUtil.isEmpty(callback)){
			return callback+"("+responseText+")";
		}else{
			return responseText;
		}
	}
	
	private String getCount(String ip,String type){
		String re=RedisUtil.getString(type+"."+ip);
		if(re==null||"".equals(re)){
			re=RedisUtil.getString(type);
			if(re==null||"".equals(re)){
				re="1";
			}else{
				if(Integer.valueOf(re)>1000000){
					re="1";
				}
			}
			re=(Integer.valueOf(re)+1)+"";
			RedisUtil.setString(type, re);
			RedisUtil.setString(type+"."+ip, re,5*60);
		}
		return re;
	}
	
	
}
