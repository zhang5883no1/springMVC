package cn.xidu.app.service.room.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.base.util.ValidateUtil;
import cn.xidu.app.dto.room.APP_ROOM_Dto;
import cn.xidu.app.dto.room.QueryRoomDto;
import cn.xidu.app.entity.APP_ROOM;
import cn.xidu.app.entity.APP_ROOMExample;
import cn.xidu.app.entity.APP_ROOMExample.Criteria;
import cn.xidu.app.mapper.APP_ROOMMapper;
import cn.xidu.app.service.room.RoomService;
@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired 
	private APP_ROOMMapper roomMapper;

	@Override
	public APP_ROOM_Dto getRoomById(String id) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		APP_ROOM entity= null;
		APP_ROOM_Dto dto=new APP_ROOM_Dto();
		if(ValidateUtil.isEmpty(id)){
			entity = roomMapper.selectByPrimaryKey(Long.valueOf(id));
		}else{
			entity = new APP_ROOM();
		}
		BeanUtils.copyProperties(entity,dto);
		return dto;
	}

	@Override
	public QueryRoomDto<APP_ROOM> query(QueryRoomDto<APP_ROOM> queryDto) {
		//设置查询条件
		APP_ROOMExample example=new APP_ROOMExample();
		Criteria criteria=example.createCriteria();
		criteria.andDeleteFlagEqualTo(0);
		if(ValidateUtil.isEmpty(queryDto.getStatus())){
			criteria.andStatusEqualTo(Integer.valueOf(queryDto.getStatus()));
		}
		example.setOrderByClause(" SORT_NO desc ");
		
		//获取最大条数
		int totalCount=roomMapper.countByExample(example);
		//设置最大条数
		queryDto.setTotalCount(totalCount);
		//设置查询分页
		example.setLimitStart(queryDto.getStartLimit());
		example.setLimitEnd(queryDto.getPageSize());
		//查询分页结果
		List<APP_ROOM> resultList=roomMapper.selectByExample(example);
		//返回
		queryDto.setResultList(resultList);
		return queryDto;
	}

	@Override
	public void saveOrUpdate(APP_ROOM_Dto dto) {
		APP_ROOM entity = new APP_ROOM();
		BeanUtils.copyProperties(dto, entity);
		if(ValidateUtil.isEmpty(dto.getId())){
			//update
			roomMapper.updateByPrimaryKeySelective(entity);
		}else{
			//save
			roomMapper.insertSelective(entity);
		}
	}


}

