package cn.base.util;  

import org.json.JSONObject;

import cn.base.redis.RedisUtil;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;  

public class WXUtil {

	private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String REDIS_ACCESSTOKEN=".accesstoken";
	private static final String JSAPI_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	private static final String REDIS_JSAPI=".jsapi";
	
	public static Map<String, String> initJSSDK(String appId,String appSecret,String url){
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtil.notEmpty(appId)&&StringUtil.notEmpty(appSecret)){
			String accessToken=getAccessToken(appId, appSecret);
			if(StringUtil.notEmpty(accessToken)){
				String jsapi=getJSApiToken(accessToken);
				if(StringUtil.notEmpty(jsapi)){
					map=sign(jsapi, url);
				}
			}
		}
		return map;
	}
	
	
	public static String getAccessToken(String appId,String appSecret){
		String access_token="";
		access_token=RedisUtil.getString(appId+REDIS_ACCESSTOKEN);
		if(StringUtil.notEmpty(access_token)){
			return access_token;
		}else{
			String _get_access_token_URL=ACCESS_TOKEN_URL.replace("APPID", appId).replace("APPSECRET", appSecret);
			String result=HttpUtil.httpGet(_get_access_token_URL);
			JSONObject json=new JSONObject(result);
			try {
				access_token=json.getString("access_token");
				RedisUtil.setString(appId+REDIS_ACCESSTOKEN, 7000, access_token);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(result);
				return null;
			}
			return access_token;
		}
	}
	
	public static String getJSApiToken(String accessToken){
		String jsapi="";
		jsapi=RedisUtil.getString(accessToken+REDIS_JSAPI);
		if(StringUtil.notEmpty(jsapi)){
			return jsapi;
		}else{
			String _get_jsapi_URL=JSAPI_URL.replace("ACCESS_TOKEN", accessToken);
			String result=HttpUtil.httpGet(_get_jsapi_URL);
			JSONObject json=new JSONObject(result);
			try {
				jsapi=json.getString("ticket");
				RedisUtil.setString(accessToken+REDIS_JSAPI, 7000, jsapi);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(result);
				return null;
			}
			return jsapi;
		}
	}
	
	public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    public static void main(String[] args) {
		String a=null;
		System.out.println(StringUtil.notEmpty(a));
	}
}
