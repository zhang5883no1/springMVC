package cn.base.util;  

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

public class SMSUtil {
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private static String account = "cf_yangcia";
	private static String pwd = "7654321";
	
	private String code="";
	private String msg="";

	/** 
	* @Title: send 
	* @Description: 发送短信方法 返回code=2位发送成功
	* @param @param mobile_code
	* @param @param mobile
	* @param @return
	* @return SMSUtil
	* @throws 
	*/ 
//	@SuppressWarnings("finally")
//	public SMSUtil send(String mobile_code ,String mobile) {
//		HttpClient client = new HttpClient();
//		PostMethod method = new PostMethod(Url);
//		client.getParams().setContentCharset("UTF-8");
//		method.setRequestHeader("ContentType",
//				"application/x-www-form-urlencoded;charset=UTF-8");
//		String content = new String("您好！感谢您的注册，您获取的手机验证码为：" + mobile_code
//		+ "，有效时间为3分钟。如非本人操作，请忽略此短信。");
//		NameValuePair[] data = {// 提交短信
//				new NameValuePair("account", account),
//				// new NameValuePair("password", pwd), //密码可以使用明文密码或使用32位MD5加密
//				new NameValuePair("password", MD5Util.MD5Encode(pwd)),
//				new NameValuePair("mobile", mobile),
//				new NameValuePair("content", content), };
//		method.setRequestBody(data);
//
//		try {
//			client.executeMethod(method);
//			String SubmitResult = method.getResponseBodyAsString();
//			Document doc = DocumentHelper.parseText(SubmitResult);
//			Element root = doc.getRootElement();
//
//			String code = root.elementText("code");
//			String msg = root.elementText("msg");
//			String smsid = root.elementText("smsid");
//
//			if(code.equals("4085")){
//				
//			}
//			
//			System.out.println(code);
//			System.out.println(mobile);
//			System.out.println(msg);
//			System.out.println(smsid);
//			this.setCode(code);
//			this.setMsg(msg);
//
//			if (code.equals("2")) {
//				System.out.println("短信提交成功");
//				return this;
//			}
//
//		} catch (HttpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return this;
//
//	}
	
	@SuppressWarnings("finally")
	public SMSUtil send(String mobile_code ,String mobile){
		HashMap<String, Object> result = null;
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		restAPI.init("app.cloopen.com", "8883");
		restAPI.setAccount("8a216da85708749401570cc4800d034d", "566be831b8f44344ad99dd400cf3e1d8");
		restAPI.setAppId("8a216da85708749401570cc481660354");
		result = restAPI.sendTemplateSMS(mobile,"138729" ,new String[]{""+mobile_code});
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			};
			this.setCode("2");
			this.setMsg("发送成功");
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			this.setCode("1");
			this.setMsg(result.get("statusMsg").toString());
		}
		
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
