package cn.base.crm.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.base.crm.dto.CRM_SOURCE_ENUM;
import cn.base.crm.dto.CRM_URL_KEYWORD_ENUM;

public class CRM_URL_TRANSLATE_Service {

	public static String translateUrl(String url) {
		if (url.length() < 10) {
			return url;
		}
		String channel = url.substring(url.indexOf("s=") + 2,
				url.indexOf("_cpc_word"));

		String domain = CRM_URL_KEYWORD_ENUM.getDomain(url);

		String keyword = "";
		if (url.indexOf("http://175.102.13.51:8080/XDSY/reg/reg.html") != -1) {
			int r_url_index = url.indexOf("linksource=");
			if (r_url_index != -1) {
				keyword = url.substring(r_url_index + 11, url.length());
			} else {
				keyword = "苹果";
			}
		} else {
			if (url.indexOf("&k=") != -1) {
				keyword = url.substring(url.indexOf("&k=") + 3, url.length())
						.split("&")[0];
			}
			if (url.indexOf("%26k=") != -1) {
				keyword = url.substring(url.indexOf("%26k=") + 5, url.length())
						.split("&")[0];
			}
			if (url.indexOf("?k=") != -1) {
				keyword = url.substring(url.indexOf("?k=") + 3, url.length())
						.split("&")[0];
			}
			if (url.indexOf("&amp;k=") != -1) {
				keyword = url.substring(url.indexOf("&amp;k=") + 7,
						url.length()).split("&amp;")[0];
			}
			try {
				keyword = URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
			}
		}

		return channel + "," + domain + ":" + keyword;
	}

	public static String translateSource(String url) {
		return CRM_SOURCE_ENUM.getSource(url)+"";
	}
	
	
	public static void main(String[] args) {
		System.out
				.println(CRM_URL_TRANSLATE_Service
						.translateUrl("http://www.51xidu.com/zhuce/kzxkdzt/?s=baidu_cpc_word&k=模拟交易软件"));
	}
}
