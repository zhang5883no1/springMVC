package cn.service.guanwang.impl;  

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.base.util.SMSUtil;
import cn.base.util.ValidateUtil;
import cn.dao.guanwang.GW_DEDE_MEMBERMapper;
import cn.dao.test.TEST_YZM_MobileCodeMapper;
import cn.entity.guanwang.GW_DEDE_MEMBERWithBLOBs;
import cn.entity.test.TEST_YZM_MobileCodeExample;
import cn.entity.test.TEST_YZM_MobileCodeWithBLOBs;
import cn.service.guanwang.GW_Reg_Service;

@Service
public class GW_Reg_ServiceImpl implements GW_Reg_Service{

	@Autowired
	private TEST_YZM_MobileCodeMapper yzm_mobilecodeDao;
	@Autowired
	private GW_DEDE_MEMBERMapper gw_memberDao;
	
	/* (non-Javadoc)  
	 * @see cn.service.guanwang.GW_Reg_Service#SendCode(java.lang.String, java.lang.String, java.lang.String)  
	 */
	@Override
	public JSONObject SendCode(String mobile, String referer, String linksource) throws NumberFormatException, ParseException {
		SimpleDateFormat sf1=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject json=new JSONObject();
		
		//查询手机验证码是否存在
		TEST_YZM_MobileCodeExample code_example=new TEST_YZM_MobileCodeExample();
		code_example.createCriteria().andMobileEqualTo(mobile);
		List<TEST_YZM_MobileCodeWithBLOBs> code_list=yzm_mobilecodeDao.selectByExampleWithBLOBs(code_example);
		//验证码存在
		if(code_list!=null&&code_list.size()>0){
			//当天已发送过
			if(code_list.get(0).getDate().equals(sf1.format(new Date()))){
				//当前发送次数小于5
				if(Integer.parseInt(code_list.get(0).getSendindex())<5){
					//发送间隔小于2分钟
					if(new Date().getTime()-sf2.parse(code_list.get(0).getDate()).getTime()<2*60*1000L){
						json.accumulate("k", "1");
						json.accumulate("reasonNum", "1");
						json.accumulate("reason", "发送间隔小于2分钟");
					}else{
						json=UpdateGetSmsCode(mobile,json,Integer.parseInt(code_list.get(0).getSendindex())+1);
					}
				//当前发送次数大于5	
				}else{
					json.accumulate("k", "1");
					json.accumulate("reasonNum", "2");
					json.accumulate("reason", "当天发送次数已满5次");
				}
			//当天未发送过
			}else{
				json=UpdateGetSmsCode(mobile,json,1);
			}
		//第一次拿手机号码
		}else{
			TEST_YZM_MobileCodeWithBLOBs cc=new TEST_YZM_MobileCodeWithBLOBs();
			cc.setCode(getRandomCode());
			cc.setDate(sf2.format(new Date()));
			cc.setIsreg("0");
			cc.setLinksource(linksource);
			cc.setMobile(mobile);
			cc.setReferer(referer);
			cc.setSendindex("1");
			json=firstGetSmsCode(cc, json);
		}
		return json;
	}

	
	/** 
	* @Title: firstGetSmsCode 
	* @Description: 第一次获取手机验证码
	* @param @param cc
	* @param @param json
	* @param @return
	* @return JSONObject
	* @throws 
	*/ 
	private JSONObject firstGetSmsCode(TEST_YZM_MobileCodeWithBLOBs cc,JSONObject json){
		if(invokeSMS(cc.getMobile(), cc.getCode())){
			addSendIndex(cc);
			json.accumulate("k", "0");
			json.accumulate("reasonNum", "0");
			json.accumulate("reason", "发送成功");
		}else{
			json.accumulate("k", "1");
			json.accumulate("reasonNum", "2");
			json.accumulate("reason", "当天发送次数已满5次");
		}
		return json;
	}
	
	
	/** 
	* @Title: UpdateGetSmsCode 
	* @Description: 更新短信验证码
	* @param @param mobile
	* @param @param json
	* @param @param sendIndex
	* @param @return
	* @return JSONObject
	* @throws 
	*/ 
	private JSONObject UpdateGetSmsCode(String mobile,JSONObject json,int sendIndex){
		String rdCode=getRandomCode();
		if(invokeSMS(mobile, rdCode)){
			updateSendIndex(mobile,rdCode,sendIndex);
			json.accumulate("k", "0");
			json.accumulate("reasonNum", "0");
			json.accumulate("reason", "发送成功");
		}else{
			json.accumulate("k", "1");
			json.accumulate("reasonNum", "2");
			json.accumulate("reason", "当天发送次数已满5次");
		}
		return json;
	}
	
	
	/** 
	* @Title: getRandomCode 
	* @Description: 生成随机码
	* @param @return
	* @return String
	* @throws 
	*/ 
	private String getRandomCode() {
		int mobile_code = (int) ((Math.random() * 9D + 1.0D) * 100000D);
		return mobile_code+"";
	}
	
	/** 
	* @Title: invokeSMS 
	* @Description: 调用短信服务，更新数据库
	* @param @param mobile
	* @param @param mobile_code
	* @param @return
	* @return boolean
	* @throws 
	*/ 
	private boolean invokeSMS(String mobile,String mobile_code){
		//调用短信服务
		SMSUtil sms=new SMSUtil();
		sms.send(mobile_code, mobile);
		//code = 2 为发送成功
		if(sms.getCode().equals("2")){
			return true;
		//code = 4085 表示发送次数过多，将数据库发送条数更新为最大值，禁止发送
		}else if(sms.getCode().equals("4085")){
			updateSendIndexToMax(mobile);
			return false;
		//其他错误
		}else{
			return false;
		}
	}
	
	
	/** 
	* @Title: updateSendIndex 
	* @Description: 更新发送条数
	* @param @param mobile
	* @param @param rdCode
	* @param @param sendIndex
	* @return void
	* @throws 
	*/ 
	private void updateSendIndex(String mobile, String rdCode, int sendIndex) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		TEST_YZM_MobileCodeWithBLOBs record=new TEST_YZM_MobileCodeWithBLOBs();
		record.setMobile(mobile);
		record.setCode(rdCode);
		record.setDate(sf.format(new Date()));
		
		TEST_YZM_MobileCodeExample example=new TEST_YZM_MobileCodeExample();
		example.createCriteria().andMobileEqualTo(mobile);
		
		yzm_mobilecodeDao.updateByExampleSelective(record, example);
	}
	
	/** 
	* @Title: updateSendIndexToMax 
	* @Description: 发送条数更新为最大
	* @param @param m
	* @return void
	* @throws 
	*/ 
	private void updateSendIndexToMax(String m) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		TEST_YZM_MobileCodeWithBLOBs record=new TEST_YZM_MobileCodeWithBLOBs();
		record.setSendindex("100");
		record.setDate(sf.format(new Date()));
		
		TEST_YZM_MobileCodeExample example=new TEST_YZM_MobileCodeExample();
		example.createCriteria().andMobileEqualTo(m);
		
		yzm_mobilecodeDao.updateByExampleSelective(record, example);	
	}
	
	/** 
	* @Title: addSendIndex 
	* @Description: 添加短信验证码
	* @param @param c
	* @return void
	* @throws 
	*/ 
	private void addSendIndex(TEST_YZM_MobileCodeWithBLOBs c) {
		yzm_mobilecodeDao.insertSelective(c);
	}


	/* (non-Javadoc)  
	 * @see cn.service.guanwang.GW_Reg_Service#DoReg(cn.entity.guanwang.GW_DEDE_MEMBERWithBLOBs, java.lang.String)  
	 */
	@Override
	public JSONObject DoReg(GW_DEDE_MEMBERWithBLOBs record, String code) {
		JSONObject json=new JSONObject();
		// TODO Auto-generated method stub  
		//验证手机格式，手机不为空，姓名不为空
		if(ValidateUtil.isMobile(record.getUserid())&&ValidateUtil.isEmpty(record.getUserid())&&ValidateUtil.isEmpty(record.getUname())){
			
		}else{
			json.accumulate("code", "0");
			return json;
		}
		
		//验证验证码
		if(validCode(record.getUserid(),code)){
			try {
				int result=gw_memberDao.insertSelective(record);
				json.accumulate("code", result+"");
			} catch (Exception e) {
				// TODO: handle exception
				json.accumulate("code", "9");
			}
		}else{
			json.accumulate("code", "2");
		}
		
		return json;
	}


	@Override
	public boolean validCode(String mobile, String code) {
		// TODO Auto-generated method stub 
		if(ValidateUtil.isEmpty(mobile)&&ValidateUtil.isEmpty(code)){
			//查询手机号码对应的验证码
			TEST_YZM_MobileCodeExample code_example=new TEST_YZM_MobileCodeExample();
			code_example.createCriteria().andMobileEqualTo(mobile);
			List<TEST_YZM_MobileCodeWithBLOBs> code_records=yzm_mobilecodeDao.selectByExampleWithBLOBs(code_example);
			//对应验证码存在
			if(code_records!=null&&code_records.size()>0&&code_records.get(0).getCode().equals(code)){
				//验证码正确
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
