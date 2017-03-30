package cn.xidu.app.dto.active;  

import cn.xidu.app.dto.base.dto.PageDto;
  
public class QueryActiveDto<T> extends PageDto<T>{

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
