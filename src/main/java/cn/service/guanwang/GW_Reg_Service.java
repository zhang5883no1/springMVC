package cn.service.guanwang;  

import java.text.ParseException;

import cn.entity.guanwang.GW_DEDE_MEMBERWithBLOBs;

import net.sf.json.JSONObject;
  
public interface GW_Reg_Service {

	/** 
	* @Title: SendCode 
	* @Description: 获取短信验证码
	* @param @param mobile
	* @param @param referer
	* @param @param linksource
	* @param @return
	* @param @throws NumberFormatException
	* @param @throws ParseException
	* @return JSONObject  k=0 注册成功    
	* @throws 
	*/ 
	public JSONObject SendCode(String mobile, String referer, String linksource) throws NumberFormatException, ParseException;

	/** 
	* @Title: DoReg 
	* @Description: 注册  返回 code=0 数据有误;code=1 注册成功 ;code=2 验证码错误
	* @param @param record 注册信息
	* @param @param code 验证码
	* @param @return
	* @return JSONObject
	* @throws 
	*/ 
	public JSONObject DoReg(GW_DEDE_MEMBERWithBLOBs record,String code);
	
	public boolean validCode(String mobile,String code);
}
