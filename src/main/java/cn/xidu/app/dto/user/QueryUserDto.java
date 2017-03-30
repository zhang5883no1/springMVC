package cn.xidu.app.dto.user;  

import cn.xidu.app.dto.base.dto.PageDto;
  
public class QueryUserDto<T> extends PageDto<T>{

	private String name;
	private String mobile;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
