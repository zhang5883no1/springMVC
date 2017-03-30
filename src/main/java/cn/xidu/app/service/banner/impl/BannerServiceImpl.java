package cn.xidu.app.service.banner.impl;  

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.base.util.ValidateUtil;
import cn.xidu.app.dto.banner.APP_BANNER_Dto;
import cn.xidu.app.dto.banner.QueryBannerDto;
import cn.xidu.app.entity.APP_BANNER;
import cn.xidu.app.entity.APP_BANNERExample;
import cn.xidu.app.entity.APP_BANNERExample.Criteria;
import cn.xidu.app.mapper.APP_BANNERMapper;
import cn.xidu.app.service.banner.BannerService;
  
@Service
public class BannerServiceImpl implements BannerService{
	@Autowired 
	private APP_BANNERMapper bannerMapper;
	
	@Override
	public QueryBannerDto<APP_BANNER> query(QueryBannerDto<APP_BANNER> queryDto) {
		//设置查询条件
		APP_BANNERExample example=new APP_BANNERExample();
		Criteria criteria=example.createCriteria();
		criteria.andDeleteFlagIsNotNull();
		if(ValidateUtil.isEmpty(queryDto.getDeletFlag())){
			criteria.andDeleteFlagEqualTo(Integer.valueOf(queryDto.getDeletFlag()));
		}
		example.setOrderByClause(" SORT_NO desc ");
		
		//获取最大条数
		int totalCount=bannerMapper.countByExample(example);
		//设置最大条数
		queryDto.setTotalCount(totalCount);
		//设置查询分页
		example.setLimitStart(queryDto.getStartLimit());
		example.setLimitEnd(queryDto.getPageSize());
		//查询分页结果
		List<APP_BANNER> resultList=bannerMapper.selectByExample(example);
		//返回
		queryDto.setResultList(resultList);
		return queryDto;
	}
	
	@Override
	public QueryBannerDto<APP_BANNER> jsonQuery(QueryBannerDto<APP_BANNER> queryDto) {
		//设置查询条件
		APP_BANNERExample example=new APP_BANNERExample();
		Criteria criteria=example.createCriteria();
		criteria.andDeleteFlagNotEqualTo(1);
		if(ValidateUtil.isEmpty(queryDto.getDeletFlag())){
			criteria.andDeleteFlagEqualTo(Integer.valueOf(queryDto.getDeletFlag()));
		}
		example.setOrderByClause(" SORT_NO desc ");
		
		//获取最大条数
		int totalCount=bannerMapper.countByExample(example);
		//设置最大条数
		queryDto.setTotalCount(totalCount);
		//设置查询分页
		example.setLimitStart(queryDto.getStartLimit());
		example.setLimitEnd(queryDto.getPageSize());
		//查询分页结果
		List<APP_BANNER> resultList=bannerMapper.selectByExample(example);
		//返回
		queryDto.setResultList(resultList);
		return queryDto;
	}

	@Override
	public APP_BANNER_Dto getBannerById(String id) {
		// TODO Auto-generated method stub  
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		APP_BANNER entity= null;
		APP_BANNER_Dto dto=new APP_BANNER_Dto();
		if(ValidateUtil.isEmpty(id)){
			entity = bannerMapper.selectByPrimaryKey(Long.valueOf(id));
//			dto.setActiveTimeString(sf.format(entity.getBannerTime()));
		}else{
			entity = new APP_BANNER();
		}
		BeanUtils.copyProperties(entity,dto);
		return dto;
	}

	@Override
	public void saveOrUpdate(APP_BANNER_Dto dto) {
		// TODO Auto-generated method stub  
		APP_BANNER entity = new APP_BANNER();
		BeanUtils.copyProperties(dto, entity);
//		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		try {
//			entity.setActiveTime(sf.parse(dto.getBannerTimeString()));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block  
//		}
		
		if(ValidateUtil.isEmpty(dto.getId())){
			//update
			bannerMapper.updateByPrimaryKeySelective(entity);
		}else{
			//save
			entity.setCreateDate(new Date());
			bannerMapper.insertSelective(entity);
		}
	}

}
