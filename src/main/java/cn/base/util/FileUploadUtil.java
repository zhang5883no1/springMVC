package cn.base.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	/** 
	* @Title: saveFile 
	* @Description: 上传文件 上传目录/static/upload/*
	* @param @param file 文件
	* @param @param request	
	* @param @param folder 上传目录，结尾带/
	* @param @return
	* @return String
	* @throws 
	*/ 
	public static String saveFile(MultipartFile file, HttpServletRequest request,String folder) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String rootPath= request.getSession().getServletContext().getRealPath("/");
				String filePath = "static/upload/"+folder ;
				String fileName=file.getOriginalFilename();
				File f = new File(rootPath+filePath);
				if (!f.exists()) {
					f.mkdirs();
				}
				// 转存文件
				file.transferTo(new File(rootPath+filePath+fileName ));
				return "/"+filePath+fileName;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
