package cn.xidu.app.service.active;  

import cn.xidu.app.dto.active.APP_ACTIVE_Dto;
import cn.xidu.app.dto.active.QueryActiveDto;
import cn.xidu.app.entity.APP_ACTIVE;
  
public interface ActiveService {

	
	public QueryActiveDto<APP_ACTIVE> query(QueryActiveDto<APP_ACTIVE> queryDto);

	public APP_ACTIVE_Dto getActiveById(String id);

	public void saveOrUpdate(APP_ACTIVE_Dto dto);
}
