package cn.controller.website;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.base.util.PropertiesConfig;

@Controller
//@RequestMapping("fiveMinute")
public class FiveMinuteLine extends HttpServlet {
	
	@RequestMapping(value="fiveMinute" ,method = RequestMethod.GET)
	public String demo_kline(Model model,HttpServletRequest request) throws IOException{
		List<String> list = new ArrayList<String>();
        String reader = null;
//        BufferedReader in = new BufferedReader(new FileReader("D:\\fromtianyu\\test\\webAgXH.txt"));
        BufferedReader in = new BufferedReader(new FileReader(PropertiesConfig.readData("website.properties", "agxh.path")));

        while ((reader = in.readLine()) != null){
            list.add(reader);
        }

		model.addAttribute("priceList", list);
		return "/website/fiveMinuteLine";
	}

}
