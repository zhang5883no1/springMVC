package cn.xidu.app.service.banner;  

import cn.xidu.app.dto.banner.APP_BANNER_Dto;
import cn.xidu.app.dto.banner.QueryBannerDto;
import cn.xidu.app.entity.APP_BANNER;
  
public interface BannerService {

	
	public QueryBannerDto<APP_BANNER> query(QueryBannerDto<APP_BANNER> queryDto);
	
	public QueryBannerDto<APP_BANNER> jsonQuery(QueryBannerDto<APP_BANNER> queryDto);

	public APP_BANNER_Dto getBannerById(String id);

	public void saveOrUpdate(APP_BANNER_Dto dto);
}
