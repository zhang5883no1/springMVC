package cn.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author xidu-lyb
 * @Time   2015年7月10日上午11:02:06
 * 
 */
public class MsgData {
	public String GetMsg(String msg){
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("conf/msg.properties");
		Properties property = new Properties();
		try {
			property.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String keyword = property.getProperty(msg);
		return keyword;
	}
	public String GetCode(String code){
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("conf/code.properties");
		Properties property = new Properties();
		try {
			property.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String keyword = property.getProperty(code);
		return keyword;
	}
	/*public static void main(String[] args) {
		MsgData test = new MsgData();
		String ret = test.GetCode("reg.0");
		System.out.println(ret);
	}*/
}
