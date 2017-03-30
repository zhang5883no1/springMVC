package cn.xidu.app.service.active.impl;  

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.base.util.ValidateUtil;
import cn.xidu.app.dto.active.APP_ACTIVE_Dto;
import cn.xidu.app.dto.active.QueryActiveDto;
import cn.xidu.app.entity.APP_ACTIVE;
import cn.xidu.app.entity.APP_ACTIVEExample;
import cn.xidu.app.entity.APP_ACTIVEExample.Criteria;
import cn.xidu.app.mapper.APP_ACTIVEMapper;
import cn.xidu.app.service.active.ActiveService;
  
@Service
public class ActiveServiceImpl implements ActiveService{
	@Autowired 
	private APP_ACTIVEMapper activeMapper;
	
	@Override
	public QueryActiveDto<APP_ACTIVE> query(QueryActiveDto<APP_ACTIVE> queryDto) {
		//设置查询条件
		APP_ACTIVEExample example=new APP_ACTIVEExample();
		Criteria criteria=example.createCriteria();
		criteria.andDeleteFlagEqualTo(0);
		if(ValidateUtil.isEmpty(queryDto.getStatus())){
			criteria.andStatusEqualTo(Integer.valueOf(queryDto.getStatus()));
		}
		example.setOrderByClause(" SORT_NO desc ");
		
		//获取最大条数
		int totalCount=activeMapper.countByExample(example);
		//设置最大条数
		queryDto.setTotalCount(totalCount);
		//设置查询分页
		example.setLimitStart(queryDto.getStartLimit());
		example.setLimitEnd(queryDto.getPageSize());
		//查询分页结果
		List<APP_ACTIVE> resultList=activeMapper.selectByExample(example);
		//返回
		queryDto.setResultList(resultList);
		return queryDto;
	}

	@Override
	public APP_ACTIVE_Dto getActiveById(String id) {
		// TODO Auto-generated method stub  
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		APP_ACTIVE entity= null;
		APP_ACTIVE_Dto dto=new APP_ACTIVE_Dto();
		if(ValidateUtil.isEmpty(id)){
			entity = activeMapper.selectByPrimaryKey(Long.valueOf(id));
			dto.setActiveTimeString(sf.format(entity.getActiveTime()));
		}else{
			entity = new APP_ACTIVE();
		}
		BeanUtils.copyProperties(entity,dto);
		return dto;
	}

	@Override
	public void saveOrUpdate(APP_ACTIVE_Dto dto) {
		// TODO Auto-generated method stub  
		APP_ACTIVE entity = new APP_ACTIVE();
		BeanUtils.copyProperties(dto, entity);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			entity.setActiveTime(sf.parse(dto.getActiveTimeString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block  
		}
		
		if(ValidateUtil.isEmpty(dto.getId())){
			//update
			activeMapper.updateByPrimaryKeySelective(entity);
		}else{
			//save
			entity.setCreateDate(new Date());
			activeMapper.insertSelective(entity);
		}
	}

}
