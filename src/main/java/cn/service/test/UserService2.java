package cn.service.test;

import java.util.List;

import cn.entity.test.DayUser;
import cn.entity.test.DayUserExample;



public interface UserService2 {

	public List<DayUser> insertUser(DayUserExample example);
}
