package cn.xidu.app.service.user.impl;  

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.base.util.ValidateUtil;
import cn.xidu.app.dto.user.APP_User_Dto;
import cn.xidu.app.dto.user.QueryUserDto;
import cn.xidu.app.entity.APP_USER;
import cn.xidu.app.entity.APP_USERExample;
import cn.xidu.app.entity.APP_USERExample.Criteria;
import cn.xidu.app.mapper.APP_USERMapper;
import cn.xidu.app.service.user.APP_UserService;
  
@Service
public class APP_UserServiceImpl implements APP_UserService{
	@Autowired 
	private APP_USERMapper userMapper;
	
	@Override
	public QueryUserDto<APP_USER> query(QueryUserDto<APP_USER> queryDto) {
		//设置查询条件
		APP_USERExample example=new APP_USERExample();
		Criteria criteria=example.createCriteria();
		if(ValidateUtil.isEmpty(queryDto.getName())){
			criteria.andNameEqualTo(queryDto.getName());
		}
		if(ValidateUtil.isEmpty(queryDto.getMobile())){
			criteria.andMobEqualTo(queryDto.getMobile());
		}
		example.setOrderByClause(" CREATE_DATE desc ");
		
		//获取最大条数
		int totalCount=userMapper.countByExample(example);
		//设置最大条数
		queryDto.setTotalCount(totalCount);
		//设置查询分页
		example.setLimitStart(queryDto.getStartLimit());
		example.setLimitEnd(queryDto.getPageSize());
		//查询分页结果
		List<APP_USER> resultList=userMapper.selectByExample(example);
		//返回
		queryDto.setResultList(resultList);
		return queryDto;
	}

	@Override
	public APP_User_Dto getUserById(String id) {
		// TODO Auto-generated method stub  
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		APP_USER entity= null;
		APP_User_Dto dto=new APP_User_Dto();
		if(ValidateUtil.isEmpty(id)){
			entity = userMapper.selectByPrimaryKey(Long.valueOf(id));
		}else{
			entity = new APP_USER();
		}
		BeanUtils.copyProperties(entity,dto);
		
		if(ValidateUtil.isEmpty(entity.getId())){
			entity.setLoginTime(new Date());
			userMapper.updateByPrimaryKeySelective(entity);
		}
		
		return dto;
	}


	@Override
	public APP_User_Dto getAPPUser(APP_USER user) {
		// TODO Auto-generated method stub  
		APP_USERExample example=new APP_USERExample();
		APP_User_Dto dto=new APP_User_Dto();
		Criteria criteria=example.createCriteria();
		if(ValidateUtil.isEmpty(user.getId())){
			criteria.andIdEqualTo(user.getId());
		}
		if(ValidateUtil.isEmpty(user.getMob())){
			criteria.andMobEqualTo(user.getMob());
		}
		if(ValidateUtil.isEmpty(user.getName())){
			criteria.andNameEqualTo(user.getName());
		}
		
		List<APP_USER> list=userMapper.selectByExample(example);
		
		if(list.size()>0){
			BeanUtils.copyProperties(list.get(0),dto);
		}
		
		return dto;
	}

	@Override
	public JSONObject reg(APP_USER user) {
		// TODO Auto-generated method stub  
		JSONObject json=new JSONObject();
		user.setCreateDate(new Date());
		user.setLoginTime(new Date());
		int result=userMapper.insertSelective(user);
		if(result==1){
			json.accumulate("key", "1");
			json.accumulate("msg", "注册成功");
		}else{
			json.accumulate("key", "0");
			json.accumulate("msg", "用户名已存在");
		}
		return json;
	}

}
