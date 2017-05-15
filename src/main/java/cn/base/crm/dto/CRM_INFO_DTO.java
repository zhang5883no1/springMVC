package cn.base.crm.dto;  

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.base.crm.service.CRM_URL_TRANSLATE_Service;
import cn.entity.guanwang.GW_DEDE_MEMBERWithBLOBs;
import cn.entity.guanwang.GW_XiduWebWithBLOBs;
import cn.entity.test.TEST_YZM_MobileCodeWithBLOBs;
import cn.entity.xdchats.OPENDOORWithBLOBs;

public class CRM_INFO_DTO {

	private String date;
	private String referer;
	private String linksource;
	private String phone;
	private String name;
	private String source;
	private String key;
	private String qq;
	
	
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getLinksource() {
		return linksource;
	}
	public void setLinksource(String linksource) {
		this.linksource = linksource;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getNewCrmJsonString(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d=sf.format(new Date(Long.valueOf(this.date)*1000L)).replace(" ", "T");
		String str_json = "{\"TaskGuid\":\"b4026263-704e-4e12-a64d-f79cb42962cc\",\"DataType\":\"CustomerWebInport\",\"InitialMoney\":0.0,"
			       + "\"AdditionalMoney\":0.0,\"CleanMoney\":0.0,\"NickName\":\"\",\"Age\":\"23\",\"AgeName\":\"3\",\"l_url\":\""+this.referer+"\","
			       + "\"r_url\":\""+this.linksource+"\",\"WorkingState\":\"120\",\"WorkingStateName\":\"\",\"PhoneState\":\"80\",\"PhoneStateName\":\"\","
			       + "\"Source\":\""+this.source+"\",\"SourcesName\":\"\",\"ResourcesState\":\"160\",\"ResourcesStateName\":\"\",\"SystemName\":\"\","
			       + "\"SystemGuid\":\"\",\"FenPeiNumber\":0,\"IsKaiHu\":\"\",\"Check\":false,\"Phone1\":\""+this.phone+"\","
			       + "\"Phone2\":\"\",\"QQ\":\""+this.qq+"\",\"Remark\":\"gasdg\",\"Phone3\":\"\",\"Address\":\"asdfasdfasd\",\"OrgID\":\"A3A1\","
			       + "\"EmplGuid\":\"\",\"Sex\":\"\",\"Dell\":false,\"CreateDate\":\""+d+".8610143+08:00"+"\","
			       + "\"FenPeiDate\":\"1900-01-01T00:00:00\",\"CusID\":\"\",\"CusName\":\""+this.name+"\",\"Keys\":\""+this.key+"\"}";
		return str_json;
	}
	
	
	@Override
	public String toString() {
		return "CRM_INFO_DTO [date=" + date + ", referer=" + referer
				+ ", linksource=" + linksource + ", phone=" + phone + ", name="
				+ name + ", source=" + source + ", key=" + key + "]";
	}
	public CRM_INFO_DTO(GW_DEDE_MEMBERWithBLOBs entity){
		this.date=entity.getJointime()+"";
		this.linksource=entity.getLinksource();
		this.name=entity.getUname();
		this.phone=entity.getUserid()+"";
		this.referer=entity.getReferer();
//		this.source=CRM_URL_TRANSLATE_Service.translateSource(this.linksource);
		this.source="160";
//		this.key=CRM_URL_TRANSLATE_Service.translateUrl(this.linksource);
		this.key="";
		this.qq="";
	}
	
	public CRM_INFO_DTO(OPENDOORWithBLOBs entity){
		SimpleDateFormat sf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.date=sf1.parse(entity.getCreatetime()).getTime()/1000L+"";
		} catch (ParseException e) {
			// TODO Auto-generated catch block  
			this.date="";
		}
		this.linksource="xidu".equals(entity.getSource())?"ios":entity.getSource();
		this.name=entity.getUsername();
		this.phone=entity.getMobile();
		this.referer="";
		this.source=CRM_SOURCE_ENUM.APPREG.getIndex()+"";
		this.key=CRM_URL_TRANSLATE_Service.translateUrl(this.linksource);
	}
	
	public CRM_INFO_DTO(GW_XiduWebWithBLOBs entity){
		this.date=entity.getJointime()+"";
		this.linksource=entity.getLinksource();
		this.name=entity.getUname();
		this.phone=entity.getUserid()+"";
		this.referer=entity.getReferer();
		this.source=CRM_URL_TRANSLATE_Service.translateSource(this.linksource);
		this.key=CRM_URL_TRANSLATE_Service.translateUrl(this.linksource);
	}
	
	public CRM_INFO_DTO(TEST_YZM_MobileCodeWithBLOBs entity){
		SimpleDateFormat sf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.date=sf1.parse(entity.getDate()).getTime()/1000L+"";
		} catch (ParseException e) {
			// TODO Auto-generated catch block  
			this.date="";
		}
		this.linksource=entity.getLinksource();
		this.name="未成功";
		this.phone=entity.getMobile();
		this.referer=entity.getReferer();
		this.source=CRM_URL_TRANSLATE_Service.translateSource(this.linksource);
		this.key=CRM_URL_TRANSLATE_Service.translateUrl(this.linksource);
	}
	
	public static void main(String[] args) {
		String s="%25E6%258D%259E%25E9%2587%2591%25E7%259B%25B4%25E6%2592%25AD%25E9%2597%25B4";
		System.out.println(URLDecoder.decode(URLDecoder.decode(s)));
	}
	
}
