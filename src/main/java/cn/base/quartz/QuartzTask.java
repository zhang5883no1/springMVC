package cn.base.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.base.crm.service.CrmService;
import cn.base.util.PropertiesConfig;

/** 
* @ClassName: RsyncCrmInfoTask 
* @Description: 定时任务测试
* @author ZHANGCHENG
* @date 2016-9-2 下午2:05:31 
*  
*/ 
@Component
public class QuartzTask {

	 /** 
	* @Title: myTest 
	* @Description: 每5秒钟打印
	* @param 
	* @return void
	* @throws 
	*/ 
	@Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次  
     public void myTest(){  
//           System.out.println("进入测试");  
     } 
	 
	/** 
	* @Title: rsyncRegCrmInfo 
	* @Description: 导入已注册数据
	* @param 
	* @return void
	* @throws 
	*/ 
	@Scheduled(cron="0 0/5 *  * * ? ")
    public void rsyncRegCrmInfo(){  
//		 new CrmService().rsyncAllRegInfo();
    } 
	
	/** 
	* @Title: rsyncNoRegCrmInfo 
	* @Description: 导入未成功数据
	* @param 
	* @return void
	* @throws 
	*/ 
	@Scheduled(cron="0 0 2  * * ? ")
    public void rsyncNoRegCrmInfo(){  
//		 new CrmService().rsync_NoReg();
    } 
}
