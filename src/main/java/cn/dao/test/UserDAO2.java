package cn.dao.test;

import cn.entity.User2;



/** 
* @ClassName: UserDAO2 
* @Description: 测试
* @author ZHANGCHENG
* @date 2016-9-2 下午2:07:31 
*  
*/ 
public interface UserDAO2 {

	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public int insertUser(User2 user);
	
	
}
