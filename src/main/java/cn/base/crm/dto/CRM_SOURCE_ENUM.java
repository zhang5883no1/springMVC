package cn.base.crm.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @ClassName: CRM_SOURCE_ENUM 
* @Description: CRM数据来源枚举类
* @author ZHANGCHENG
* @date 2016-9-6 下午1:16:01 
*  
*/ 
public enum CRM_SOURCE_ENUM {
	TZCL("m.91xidu.com/zhuce/tzcl", 201), 
	TZTY("m.91xidu.com/zhuce/tzty", 201),
	GSZG("m.91xidu.com/zhuce/gszg", 202), 
	YZNG("m.91xidu.com/zhuce/yzng", 202),
	KZXKDZT("/kzxkdzt/", 184),
	CJRL("/zhuce/cjrl", 185),
	FANGZHAPIAN("/fangzhapian", 160),
	FZP("/fzp", 160),
	YTX222("ytx222", 160),
	CXPT("/cxpt", 137),
	RJXZ("/rjxz", 137),
	SIMPLE("/simple", 137),
	REG("/reg", 137),
	HD3("/hd3", 137),
	HUODONG3("huodong3", 137),
	ZPCJ("/zpcj", 137),
	APPREG("reg.htm", 134),
	APP1("/app1", 134),
	APP2("/app2", 134),
	APP3("/app3", 134),
	ZHIBO_XIDUOIL("zhibo.xiduoil.com", 132),
	M_ZHIBO_XIDUOIL("m.xiduoil.com/zhibo", 132),
	LJ_XIDUOIL("lj.xiduoil.com/yd", 132),
	LJ_91XIDU("lj.91xidu.com/", 132),
	DEFAULT("", 142);
	
	
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private CRM_SOURCE_ENUM(String name, int index) {
        this.name = name;
        this.index = index;
    }

    
    /** 
    * @Title: getName 
    * @Description: 根据url获取来源代码
    * @param @param url
    * @param @return
    * @return int
    * @throws 
    */ 
    public static int getSource(String url) {
        for (CRM_SOURCE_ENUM c : CRM_SOURCE_ENUM.values()) {
            if(url.indexOf(c.name)!=-1){
            	return c.index;
            }
        }
        return CRM_SOURCE_ENUM.DEFAULT.index;
    }
    
    // 覆盖方法
    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }


	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}
    
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sf.parse("2017-04-01 00:00:00").getTime()/1000);
	}
}
