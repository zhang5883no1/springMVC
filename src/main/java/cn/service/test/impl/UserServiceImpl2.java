package cn.service.test.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.test.DayUserMapper;
import cn.entity.test.DayUser;
import cn.entity.test.DayUserExample;
import cn.service.test.UserService2;


/** 
* @ClassName: UserServiceImpl2 
* @Description: 测试
* @author ZHANGCHENG
* @date 2016-9-2 下午2:10:09 
*  
*/ 
@Service
public class UserServiceImpl2 implements UserService2{

	@Autowired
	private DayUserMapper userDAO;
	
	@Override
	public List<DayUser> insertUser(DayUserExample example) {
		// TODO Auto-generated method stub
		return userDAO.selectByExample(example);
	}

}
