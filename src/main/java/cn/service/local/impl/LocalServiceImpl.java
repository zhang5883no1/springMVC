package cn.service.local.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.local.CRM_Entity2Mapper;
import cn.entity.local.CRM_Entity2;
import cn.service.local.LocalService;


/** 
* @ClassName: UserServiceImpl2 
* @Description: 测试
* @author ZHANGCHENG
* @date 2016-9-2 下午2:10:09 
*  
*/ 
@Service
public class LocalServiceImpl implements LocalService{

	@Autowired
	private CRM_Entity2Mapper dao;
	
	@Override
	public void insertUser(CRM_Entity2 example) {
		// TODO Auto-generated method stub
		try {
			dao.insert(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
