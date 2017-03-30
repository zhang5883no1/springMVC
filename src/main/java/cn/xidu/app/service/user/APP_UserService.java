package cn.xidu.app.service.user;  

import net.sf.json.JSONObject;
import cn.xidu.app.dto.user.APP_User_Dto;
import cn.xidu.app.dto.user.QueryUserDto;
import cn.xidu.app.entity.APP_USER;
  
public interface APP_UserService {

	
	public QueryUserDto<APP_USER> query(QueryUserDto<APP_USER> queryDto);

	public APP_User_Dto getUserById(String id);

	public APP_User_Dto getAPPUser(APP_USER user) ;

	public JSONObject reg(APP_USER user);

}
