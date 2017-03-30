package cn.base.crm.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.base.crm.dto.CRM_INFO_DTO;
import cn.base.crm.webservice.WebServiceSoap_WebServiceSoap_Client;
import cn.base.util.PropertiesConfig;
import cn.dao.guanwang.GW_DEDE_MEMBERMapper;
import cn.dao.guanwang.GW_XiduWebMapper;
import cn.dao.test.TEST_YZM_MobileCodeMapper;
import cn.dao.xdchats.OPENDOORMapper;
import cn.entity.guanwang.GW_DEDE_MEMBER;
import cn.entity.guanwang.GW_DEDE_MEMBERExample;
import cn.entity.guanwang.GW_DEDE_MEMBERWithBLOBs;
import cn.entity.guanwang.GW_XiduWeb;
import cn.entity.guanwang.GW_XiduWebExample;
import cn.entity.guanwang.GW_XiduWebWithBLOBs;
import cn.entity.test.TEST_YZM_MobileCodeExample;
import cn.entity.test.TEST_YZM_MobileCodeWithBLOBs;
import cn.entity.xdchats.OPENDOORExample;
import cn.entity.xdchats.OPENDOORWithBLOBs;

/** 
* @ClassName: CrmService 
* @Description: Crm导入 
* @author ZHANGCHENG
* @date 2016-9-6 下午5:26:30 
*  
*/ 
public class CrmService {

	@Autowired
	private GW_DEDE_MEMBERMapper GW_DEDE_MEMEBER_Dao;
	@Autowired
	private OPENDOORMapper XD_Mobile_Dao;
	@Autowired
	private GW_XiduWebMapper XD_XiduWeb_Dao;
	@Autowired
	private TEST_YZM_MobileCodeMapper TEST_YZM_MobileCode_Dao;
	
	
	
	/** 
	* @Title: rsyncAllRegInfo 
	* @Description: 导入所有数据 不包括未成功
	* @param 
	* @return void
	* @throws 
	*/ 
	public void rsyncAllRegInfo() {
		rsync_DEDE_MEMBER();
		rsync_HT_APP_REG();
		rsync_XIDUWEB();
	}

	/** 
	* @Title: rsync_DEDE_MEMBER 
	* @Description: 导入guanwang.dede_member
	* @param 
	* @return void
	* @throws 
	*/ 
	private void rsync_DEDE_MEMBER() {
		int rsyncIndex=Integer.valueOf(new PropertiesConfig().readData("CRM.properties","rsync.guanwang.dedemember.index"));
		GW_DEDE_MEMBERExample example=new GW_DEDE_MEMBERExample();
		example.createCriteria().andLinkSourceNotLike("http://www.xiduoil.com/fzp%");
		example.createCriteria().andLinkSourceNotLike("http://zhibo.xiduoil.com%");
		example.createCriteria().andMidGreaterThan(rsyncIndex);
		example.setOrderByClause("ordery by mid desc");
		List<GW_DEDE_MEMBERWithBLOBs> list=GW_DEDE_MEMEBER_Dao.selectByExampleWithBLOBs(example);
		for(GW_DEDE_MEMBERWithBLOBs entity:list){
			CRM_INFO_DTO dto=new CRM_INFO_DTO(entity);
			new PropertiesConfig().writeData("CRM.properties","rsync.guanwang.xiduweb.index",entity.getMid().toString());
			invokeCrmWebService(dto);
		}
	}

	/** 
	* @Title: rsync_HT_APP_REG 
	* @Description: 导入xdchats.opendoor
	* @param 
	* @return void
	* @throws 
	*/ 
	private void rsync_HT_APP_REG() {
		int rsyncIndex=Integer.valueOf(new PropertiesConfig().readData("CRM.properties","rsync.xdchats.opendoor.index"));
		OPENDOORExample example=new OPENDOORExample();
		example.createCriteria().andIdGreaterThan(rsyncIndex);
		example.setOrderByClause("order by id desc");
		List<OPENDOORWithBLOBs> list=XD_Mobile_Dao.selectByExampleWithBLOBs(example);
		for(OPENDOORWithBLOBs entity:list){
			CRM_INFO_DTO dto=new CRM_INFO_DTO(entity);
			new PropertiesConfig().writeData("CRM.properties","rsync.xdchats.opendoor.index",entity.getId().toString());
			invokeCrmWebService(dto);
		}
	}

	/** 
	* @Title: rsync_XIDUWEB 
	* @Description: 导入guanwang.xiduweb
	* @param 
	* @return void
	* @throws 
	*/ 
	private void rsync_XIDUWEB() {
		int rsyncIndex=Integer.valueOf(new PropertiesConfig().readData("CRM.properties","rsync.guanwang.xiduweb.index"));
		GW_XiduWebExample example=new GW_XiduWebExample();
		example.createCriteria().andMidGreaterThan(rsyncIndex);
		example.setOrderByClause("order by mid desc");
		List<GW_XiduWebWithBLOBs> list=XD_XiduWeb_Dao.selectByExampleWithBLOBs(example);
		for(GW_XiduWebWithBLOBs entity:list){
			CRM_INFO_DTO dto=new CRM_INFO_DTO(entity);
			new PropertiesConfig().writeData("CRM.properties","rsync.guanwang.xiduweb.index",entity.getMid().toString());
			invokeCrmWebService(dto);
		}
	}
	
	/** 
	* @Title: rsync_NoReg 
	* @Description: 导入未成功
	* @param 
	* @return void
	* @throws 
	*/ 
	public void rsync_NoReg() {
		List<String> regId_list=new ArrayList<String>();
		regId_list=GET_DEDE_MEMBER_REGID_YESTERDAY(regId_list);
		regId_list=GET_XIDUWEB_REGID_YESTERDAY(regId_list);
		TEST_YZM_MobileCodeExample example=new TEST_YZM_MobileCodeExample();
		example.createCriteria().andMobileNotIn(regId_list);
		List<TEST_YZM_MobileCodeWithBLOBs> list=TEST_YZM_MobileCode_Dao.selectByExampleWithBLOBs(example);
		for(TEST_YZM_MobileCodeWithBLOBs entity:list){
			CRM_INFO_DTO dto=new CRM_INFO_DTO(entity);
			invokeCrmWebService(dto);
		}
	}
	
	
	/** 
	* @Title: GET_DEDE_MEMBER_REGID_YESTERDAY 
	* @Description: 获取 guanwang.dede_member 前一天的数据
	* @param @param regId_list
	* @param @return
	* @return List<String>
	* @throws 
	*/ 
	private List<String> GET_DEDE_MEMBER_REGID_YESTERDAY(List<String> regId_list){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR, -12);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		Date toDay = ca.getTime();
		ca.add(Calendar.DATE, -1);// 日期减1
    	Date yesterDay = ca.getTime();
    	
    	GW_DEDE_MEMBERExample example=new GW_DEDE_MEMBERExample();
		example.createCriteria().andJointimeBetween(Integer.valueOf(yesterDay.getTime()/1000L+""), Integer.valueOf(toDay.getTime()/1000L+""));
		List<GW_DEDE_MEMBER> list=GW_DEDE_MEMEBER_Dao.selectByExample(example);
		
		for(GW_DEDE_MEMBER entity:list){
			regId_list.add(entity.getMid().toString());
		}
		return regId_list;
	}
	
	/** 
	* @Title: GET_XIDUWEB_REGID_YESTERDAY 
	* @Description: 获取 guanwang.xiduweb 前一天的数据
	* @param @param regId_list
	* @param @return
	* @return List<String>
	* @throws 
	*/ 
	private List<String> GET_XIDUWEB_REGID_YESTERDAY(List<String> regId_list){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR, -12);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		Date toDay = ca.getTime();
		ca.add(Calendar.DATE, -1);// 日期减1
    	Date yesterDay = ca.getTime();
    	
		GW_XiduWebExample example=new GW_XiduWebExample();
		example.createCriteria().andJointimeBetween(Integer.valueOf(yesterDay.getTime()/1000L+""), Integer.valueOf(toDay.getTime()/1000L+""));
		List<GW_XiduWeb> list=XD_XiduWeb_Dao.selectByExample(example);
		
		for(GW_XiduWeb entity:list){
			regId_list.add(entity.getMid().toString());
		}
		return regId_list;
	}
	
	
	/** 
	* @Title: invokeCrmWebService 
	* @Description: 调用crm webservice 接口
	* @param @param dto
	* @return void
	* @throws 
	*/ 
	private void invokeCrmWebService(CRM_INFO_DTO dto){
		System.out.println(dto.toString());
		WebServiceSoap_WebServiceSoap_Client.client(dto.getNewCrmJsonString());
	}
	
}
