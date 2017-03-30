package cn.xidu.app.controller.room;

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
import cn.xidu.app.dto.room.APP_ROOM_Dto;
import cn.xidu.app.dto.room.QueryRoomDto;
import cn.xidu.app.entity.APP_ROOM;
import cn.xidu.app.service.room.RoomService;



@Controller
@RequestMapping("app/controller/room")
public class RoomController {	
	@Autowired
	private RoomService roomservice;
	
	
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
	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init_room(Model model,HttpServletRequest request) throws IOException{
		QueryRoomDto<APP_ROOM> queryDto=new QueryRoomDto<APP_ROOM>();
		roomservice.query(queryDto);
		model.addAttribute("queryDto", queryDto);
		return "/app/room/list";
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
	@RequestMapping(value="query" ,method = RequestMethod.POST)
	public String queryCustom(QueryRoomDto<APP_ROOM> queryDto,Model model,HttpServletRequest request){
		roomservice.query(queryDto);
		model.addAttribute("queryDto", queryDto);
		return "/app/room/list";
	}
	
	/** 
	* @Title: addOrEdit 
	* @Description: 点击新增或修改
	* @param @param model
	* @param @param request
	* @param @return
	* @param @throws IOException
	* @return String
	* @throws 
	*/ 
	@RequestMapping(value="addOrEdit" ,method = RequestMethod.GET)
	public String addOrEdit(Model model,HttpServletRequest request) throws IOException{
		String id=request.getParameter("id");
		model.addAttribute("dto", roomservice.getRoomById(id));
		return "/app/room/addOrEdit";
	}
	
	/** 
	* @Title: filesUpload 
	* @Description: 点击保存，保存或修改
	* @param @param file
	* @param @param dto
	* @param @param request
	* @param @return
	* @return String
	* @throws 
	*/ 
	@RequestMapping("saveOrUpdate")  
    public String saveOrUpdate(@RequestParam(value = "file", required = false) MultipartFile file,APP_ROOM_Dto dto, HttpServletRequest request) {  
        //保存文件  
        String filepath=FileUploadUtil.saveFile(file,request,"appupload/"); 
        if(ValidateUtil.isEmpty(filepath)){
        	dto.setPicurl(filepath);
        }
        roomservice.saveOrUpdate(dto);
        // 重定向  
        return "redirect:init";  
    }  
	
	
	/** 
	* @Title: queryCustomFromHttpService 
	* @Description: app查询接口
	* @param @param request
	* @param @return
	* @return String
	* @throws 
	*/ 
	@RequestMapping(value="query/json" ,method = RequestMethod.GET)
	@ResponseBody
	public String queryCustomFromHttpService(HttpServletRequest request){
		QueryRoomDto<APP_ROOM> queryDto=new QueryRoomDto<APP_ROOM>();
		String status=request.getParameter("status");
		String currentPage=request.getParameter("currentPage");
		String pageSize=request.getParameter("pageSize");
		
		if(ValidateUtil.isEmpty(status)){
			queryDto.setStatus(status);
		}
		if(ValidateUtil.isEmpty(currentPage)){
			queryDto.setCurrentPage(Integer.valueOf(currentPage));	
		}
		if(ValidateUtil.isEmpty(pageSize)){
			queryDto.setPageSize(Integer.valueOf(pageSize));
		}
		roomservice.query(queryDto);
		
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
	
	/** 
	* @Title: findById 
	* @Description: app详情接口
	* @param @param request
	* @param @return
	* @return String
	* @throws 
	*/ 
	@RequestMapping(value="detail/json" ,method = RequestMethod.GET)
	@ResponseBody
	public String findById(HttpServletRequest request){
		String id=request.getParameter("id");
		JSONObject json = new JSONObject();
		
		if(ValidateUtil.isEmpty(id)){
			APP_ROOM_Dto dto=roomservice.getRoomById(id);
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
