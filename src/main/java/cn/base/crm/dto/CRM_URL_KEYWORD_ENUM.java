package cn.base.crm.dto;  
  
public enum CRM_URL_KEYWORD_ENUM {
	PC("http://www.xiduoil.com/simple/", "PC"), 
	MOBILE("http://www.xiduoil.com/mobile/reg/", "MOBILE"), 
	M_MOBILE("http://m.xiduoil.com/reg/", "MOBILE"), 
	HUITONG_ZHIBO("http://zhibo.xiduoil.com/?userId=htbbs","ZHIBO:策划组-汇通论坛"),
	NEWPC("http://www.xiduoil.com/bd/rjxz2/", "NEWPC"), 
	NEWMOBILE("http://www.xiduoil.com/bd/mb/rjxz2/", "NEWMOBILE"), 
	M_NEWMOBILE("http://m.xiduoil.com/rjxz/", "NEWMOBILE"), 
	ZHAPIAN("/fangzhapian/", "ZHAPIAN"), 
	ZHAPIANAPP("http://www.xiduoil.com/fangzhapianapp/", "ZHAPIANAPP"), 
	YXYHUODONG("http://yxy.xiduoil.com/huodongye/", "YXYHUODONG"), 
	YXYHUODONG2("http://yxy.xiduoil.com/huodongye2/", "YXYHUODONG2"), 
	YXYHUODONGAPP2("http://yxy.xiduoil.com/huodongye2app/", "YXYHUODONGAPP2"), 
	HUODONG3PC("http://www.xiduoil.com/huodong3/", "HUODONG3PC"), 
	HUODONG3WAP("http://www.xiduoil.com/huodong3wap/", "HUODONG3WAP"), 
	CHAXUN("http://www.ytx222.com/cxpt/", "CHAXUN"), 
	CHAXUNAPP("m.91xidu.com/zhuce/tzcl", "CHAXUNAPP"), 
	CHAXUNAPP_B("http://m.ytx222.com/cxpt/", "CHAXUNAPP"), 
	KAIXUAN("http://kx.ytx222.com", "凯旋直播间"), 
	JUNMA("http://jm.xiduoil.com", "骏马直播间"), 
	LAOJIN("http://lj.xiduoil.com", "捞金直播间"), 
	CHUANGSHOU("http://csd.51xidu.com", "创收直播间"), 
	LANGFENG("http://lfe.51xidu.com", "郎峰直播间"), 
	HD3("www.51xidu.com/dhbd/fzp", "HD3"), 
	HD3_B("www.51xidu.com/hd3", "HD3"), 
	HD3APP("m.51xidu.com/hd3t", "HD3APP"), 
	MONIJIN("kzxkdzt", "模拟金软件"), 
	CAIJINGRILI("cjrl", "财经日历"), 
	YAOYIYAOTOUZI("m.91xidu.com/zhuce/tzty", "摇一摇投资"), 
	YAOYIYAOTOUZI_B("m.91xidu.com/zhuce/tzcl", "摇一摇投资"), 
	ZHAPIANAPP_B("/fzp", "ZHAPIANAPP"), 
	HUODONG3WAP_B("/hd3/", "HUODONG3WAP"), 
	ZHIBO("http://zhibo.xiduoil.com",""),
	DEFAULT("",""),
	;
	
	
    // 成员变量
    private String key;
    private String value;

    // 构造方法
    private CRM_URL_KEYWORD_ENUM(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public static String getDomain(String url) {
        for (CRM_URL_KEYWORD_ENUM c : CRM_URL_KEYWORD_ENUM.values()) {
            if(url.indexOf(c.key)!=-1){
            	return c.value;
            }
        }
        return "";
    }
    
    // 覆盖方法
    @Override
    public String toString() {
        return this.key + "_" + this.value;
    }
    
}
