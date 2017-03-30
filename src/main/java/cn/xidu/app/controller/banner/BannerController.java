package cn.xidu.app.controller.banner;  

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.base.util.FileUploadUtil;
import cn.base.util.JsonDateValueProcessor;
import cn.base.util.ValidateUtil;
import cn.xidu.app.dto.banner.APP_BANNER_Dto;
import cn.xidu.app.dto.banner.QueryBannerDto;
import cn.xidu.app.entity.APP_BANNER;
import cn.xidu.app.service.banner.BannerService;

  
@Controller
@RequestMapping("app/controller/banner")
public class BannerController {

	@Autowired
	private BannerService bannerService;
	
	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init_reg(Model model,HttpServletRequest request) throws IOException{
		QueryBannerDto<APP_BANNER> queryDto=new QueryBannerDto<APP_BANNER>();
		bannerService.query(queryDto);
		model.addAttribute("queryDto", queryDto);
		return "/app/banner/list";
	}
	
	@RequestMapping(value="query" ,method = RequestMethod.POST)
	public String queryCustom(QueryBannerDto<APP_BANNER> queryDto,Model model,HttpServletRequest request){
		bannerService.query(queryDto);
		model.addAttribute("queryDto", queryDto);
		return "/app/banner/list";
	}
	
	@RequestMapping(value="addOrEdit" ,method = RequestMethod.GET)
	public String addOrEdit(Model model,HttpServletRequest request) throws IOException{
		String id=request.getParameter("id");
		model.addAttribute("dto", bannerService.getBannerById(id));
		return "/app/banner/addOrEdit";
	}
	
	@RequestMapping("saveOrUpdate")  
    public String filesUpload(@RequestParam(value = "file", required = false) MultipartFile file,APP_BANNER_Dto dto, HttpServletRequest request) {  
        //保存文件  
        String filepath=FileUploadUtil.saveFile(file,request,"appupload/banner/"); 
        if(ValidateUtil.isEmpty(filepath)){
        	dto.setPicUrl(filepath);
        }
        bannerService.saveOrUpdate(dto);
        // 重定向  
        return "redirect:init";  
    }  
	
	
	@RequestMapping(value="query/json" ,method = RequestMethod.GET)
	@ResponseBody
	public String queryCustomFromHttpService(HttpServletRequest request){
		QueryBannerDto<APP_BANNER> queryDto=new QueryBannerDto<APP_BANNER>();
		
		queryDto.setDeletFlag("0");
		queryDto.setCurrentPage(1);	
		bannerService.query(queryDto);
		
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		JSONObject json = new JSONObject();
		json=json.fromObject(queryDto, jsonConfig) ;
		json.remove("pageString");
		String callback=request.getParameter("callback");
		if(ValidateUtil.isEmpty(callback)){
			return callback+"("+json.toString()+")";
		}else{
			return json.toString();
		}
	}
	
	@RequestMapping(value="detail/json" ,method = RequestMethod.GET)
	@ResponseBody
	public String findById(HttpServletRequest request){
		String id=request.getParameter("id");
		JSONObject json = new JSONObject();
		
		if(ValidateUtil.isEmpty(id)){
			APP_BANNER_Dto dto=bannerService.getBannerById(id);
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
			json=json.fromObject(dto, jsonConfig) ;
		}
		
		String callback=request.getParameter("callback");
		if(ValidateUtil.isEmpty(callback)){
			return callback+"("+json.toString()+")";
		}else{
			return json.toString();
		}
	}
	
}
