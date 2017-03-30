package cn.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/** 
* @ClassName: MainController 
* @Description: 测试
* @author ZHANGCHENG
* @date 2016-9-2 下午2:05:00 
*  
*/ 
@Controller
@RequestMapping("/2b")
public class MainController {

	
	/** 
	* @Title: index 
	* @Description: 测试方法
	* @param @return
	* @return String
	* @throws 
	*/ 
	
	
	@RequestMapping("index")
	public String index(){
		System.out.println("----------------开始-----------------------------------------------");  
		return "index";
	}
	
	
	@RequestMapping("uploadSimpleFile")
	public String index(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model){
		System.out.println("----------------开始-----------------------------------------------");  
		saveFile(file,request);
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+file.getOriginalFilename());  
		return "result";
	}
	
	@RequestMapping("filesUpload")  
    public String filesUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {  
        //判断file数组不能为空并且长度大于0  
        if(files!=null&&files.length>0){  
            //循环获取file数组中得文件  
            for(int i = 0;i<files.length;i++){  
                MultipartFile file = files[i];  
                //保存文件  
                saveFile(file,request);  
            }  
        }  
        // 重定向  
        return "result";  
    }  
	
	private boolean saveFile(MultipartFile file, HttpServletRequest request) {  
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
                // 文件保存路径  
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
                        + file.getOriginalFilename();  
                // 转存文件  
                file.transferTo(new File(filePath));  
                return true;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }  
	
}
