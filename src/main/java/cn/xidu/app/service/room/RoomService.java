package cn.xidu.app.service.room;

import cn.xidu.app.dto.room.APP_ROOM_Dto;
import cn.xidu.app.dto.room.QueryRoomDto;
import cn.xidu.app.entity.APP_ROOM;


public interface RoomService {

	APP_ROOM_Dto getRoomById(String id);

	QueryRoomDto<APP_ROOM> query(QueryRoomDto<APP_ROOM> queryDto);

	void saveOrUpdate(APP_ROOM_Dto dto);

	
	

}
