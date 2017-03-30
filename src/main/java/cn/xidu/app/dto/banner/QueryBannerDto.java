package cn.xidu.app.dto.banner;  

import cn.xidu.app.dto.base.dto.PageDto;
  
public class QueryBannerDto<T> extends PageDto<T>{

	private String deletFlag;

	public String getDeletFlag() {
		return deletFlag;
	}

	public void setDeletFlag(String deletFlag) {
		this.deletFlag = deletFlag;
	}

}
