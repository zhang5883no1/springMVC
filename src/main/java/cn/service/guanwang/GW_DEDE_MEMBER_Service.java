package cn.service.guanwang;

import java.util.List;

import cn.entity.guanwang.GW_DEDE_MEMBER;
import cn.entity.guanwang.GW_DEDE_MEMBERExample;
import cn.entity.guanwang.GW_DEDE_MEMBERWithBLOBs;

public interface GW_DEDE_MEMBER_Service {

	
	public List<GW_DEDE_MEMBER> QueryMemberToCrm(GW_DEDE_MEMBERExample example);
	
	
	public void update(GW_DEDE_MEMBERWithBLOBs record,GW_DEDE_MEMBERExample example);
}
