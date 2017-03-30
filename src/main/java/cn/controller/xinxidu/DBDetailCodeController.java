package cn.controller.xinxidu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
import cn.entity.xinxidu.OfficialDto3;
import cn.entity.xinxidu.Reflex;
import cn.service.xinxidu.Freecms_InfoMapperService;

@Controller
@RequestMapping("/")
public class DBDetailCodeController {
	
	@Autowired
	private Freecms_InfoMapperService freecms_InfoMapperService;
	
	@RequestMapping("Detail1")
	public void getOffic(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		Reflex rf = new Reflex();
		OfficialDto3 offic = new OfficialDto3();
		PropertiesConfig config=new PropertiesConfig();
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		String id = "";
		id = request.getParameter("id");
		String param = request.getParameter("type");
		param = URLDecoder.decode(param, "UTF-8");
		if(param.equals("OfficialDto")){
			offic = freecms_InfoMapperService.getDetOffic(id);
			List<OfficialDto3> offlist = new LinkedList<OfficialDto3>();
			offlist.add(offic);
			if(offic==null){
				json.accumulate("flag", 0);
				json.accumulate("msg", config.readData("msg.properties", "3"));
			}else{
				json.accumulate("flag", 1);
				json.accumulate("data", rf.toOffic3json(offlist));
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
