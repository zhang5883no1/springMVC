package cn.base.util;  

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
  
public class MD5Util {
	public static String str;
	public static final String EMPTY_STRING = "";

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/** 
	* @Title: byteArrayToHexString 
	* @Description: 数组转16进制
	* @param @param b
	* @param @return
	* @return String
	* @throws 
	*/ 
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 
	* @Title: MD5Encode 
	* @Description: md5加密
	* @param @param origin
	* @param @return
	* @return String
	* @throws 
	*/ 
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}
	
	
	public static boolean validKeyWithAPP(String mob,String key){
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
		String date=sf.format(new Date());
		String orignKey=MD5Encode(date+mob);
		return orignKey.equals(key);
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.MD5Encode(MD5Util.MD5Encode("123456")));
	}
}
