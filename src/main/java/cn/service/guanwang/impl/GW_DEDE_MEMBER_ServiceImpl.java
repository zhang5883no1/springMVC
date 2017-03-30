package cn.service.guanwang.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.guanwang.GW_DEDE_MEMBERMapper;
import cn.entity.guanwang.GW_DEDE_MEMBER;
import cn.entity.guanwang.GW_DEDE_MEMBERExample;
import cn.entity.guanwang.GW_DEDE_MEMBERWithBLOBs;
import cn.service.guanwang.GW_DEDE_MEMBER_Service;

/** 
* @ClassName: GW_DEDE_MEMBER_ServiceImpl 
* @Description: GW_DEDE_MEMEBER 逻辑接口
* @author ZHANGCHENG
* @date 2016-9-2 下午2:09:31 
*  
*/ 
@Service
public class GW_DEDE_MEMBER_ServiceImpl implements GW_DEDE_MEMBER_Service{

	@Autowired
	private GW_DEDE_MEMBERMapper GW_DEDE_MEMEBER_Dao;
	
	@Override
	public List<GW_DEDE_MEMBER> QueryMemberToCrm(GW_DEDE_MEMBERExample example) {
		// TODO Auto-generated method stub
		List<GW_DEDE_MEMBER> list=GW_DEDE_MEMEBER_Dao.selectByExample(example);
		return list;
	}

	@Override
	public void update(GW_DEDE_MEMBERWithBLOBs record,GW_DEDE_MEMBERExample example) {
		// TODO Auto-generated method stub  
		GW_DEDE_MEMEBER_Dao.updateByExampleSelective(record, example);
	}

	
}
