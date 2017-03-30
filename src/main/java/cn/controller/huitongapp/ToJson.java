package cn.controller.huitongapp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.entity.huitongapp.OfficialDto;
import cn.entity.huitongapp.OnlineQADto;
import cn.entity.xinxidu.OfficialDto3;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ToJson {
	
	/**
	 * 根据接口的不同，按照不同的格式转化JSON并返回
	 * @param inter 接口的代号
	 * @param connet 传过来的联系我们的原内容
	 * @return JSONArray
	 */
	public static JSONArray string_json_lianxi(String connet){
		JSONArray jsonarray = new JSONArray();
		JSONObject json = new JSONObject();
		String newconnet = connet;
		newconnet ="CompanyTel:" + connet.substring(connet.indexOf("：")+2,connet.indexOf("</s"))+";";
		connet = connet.substring(connet.indexOf("</s")+3);
		newconnet = newconnet+"CustomerHotline:" + connet.substring(connet.indexOf("：")+2,connet.indexOf("</s"))+";";
		connet = connet.substring(connet.indexOf("</s")+3);
		newconnet = newconnet+"JoinHotline:" + connet.substring(connet.indexOf("：")+2,connet.indexOf("</s"))+";";
		connet = connet.substring(connet.indexOf("</s")+3);
		newconnet = newconnet+"ComplaintsTel:" + connet.substring(connet.indexOf("：")+2,connet.indexOf("</s"))+";";
		connet = connet.substring(connet.indexOf("</s")+3);
		newconnet = newconnet+"CompanyAddress:" + connet.substring(connet.indexOf("：")+2,connet.indexOf("</s"))+";";
		connet = connet.substring(connet.indexOf("</s")+3);
		newconnet = newconnet+"HeadquartersAddress:" + connet.substring(connet.indexOf("：")+2,connet.indexOf("</s"));
		String [] con = newconnet.split(";");
		/********************/
		con[0] = newconnet.substring(0, newconnet.indexOf(";"));
		newconnet = newconnet.substring(newconnet.indexOf(";") + 1);
		con[1] = newconnet.substring(0, newconnet.indexOf(";"));
		newconnet = newconnet.substring(newconnet.indexOf(";") + 1);
		con[2] = newconnet.substring(0, newconnet.indexOf(";"));
		newconnet = newconnet.substring(newconnet.indexOf(";") + 1);
		con[3] = newconnet.substring(0, newconnet.indexOf(";"));
		newconnet = newconnet.substring(newconnet.indexOf(";") + 1);
		con[4] = newconnet.substring(0, newconnet.indexOf(";"));
		newconnet = newconnet.substring(newconnet.indexOf(";") + 1);
		con[5] = newconnet;
		/********************/
		for(int i = 0;i < 5;i++){
			String [] det = con[i].split(":");
			if (det.length < 2) {
				json.accumulate(det[0].toString(), det[1].toString());
			}else{
				String[] det0 = { con[i].substring(0, con[i].indexOf(":")), con[i].substring(con[i].indexOf(":") + 1) };
				json.accumulate(det0[0].toString(), det0[1].toString());
			}
		}
		String last = con[5].substring(con[5].indexOf("<img")).toString();
		json.accumulate("HeadquartersAddress", last);
		jsonarray.add(json);
		return jsonarray;
	}
	
	public static JSONObject string_json_other(String inter, String connet){
		JSONObject json = new JSONObject();
		if ("jiaoyi".equals(inter)) {	//"交易所简介"的接口
			if(connet.indexOf("src=\"/upload")!=-1)
				connet = connet.replaceAll("src=\"/upload", "src=\"http://www.xiduoil.com/upload");
			json.accumulate("jiaoyi", connet);
		} else if ("gongsi".equals(inter)) {	//"公司简介"的接口
			if(connet.indexOf("src=\"/")!=-1)
				connet = connet.replaceAll("src=\"/", "src=\"http://175.102.13.51:8080/");
			json.accumulate("gongsi", connet);
		}
		return json;
	}
	
	
	/**
	 * 把传递过来的List<OfficialDto>转化为特定格式的JSON
	 * @param list 传递过来的OfficialDto的集合
	 * @return JSONArray
	 */
	public static JSONArray official_json(List<OfficialDto> list){
		JSONArray jsonarray = new JSONArray();
		Class<?> c = OfficialDto.class;
		Method[] ms = c.getDeclaredMethods();
		for(OfficialDto of : list){
			JSONObject json = new JSONObject();
			for(Method m:ms){
				String methodName=m.getName();//获取每一个方法名  
				//只得到具有get方法的属性，getClass除外  
				if(methodName.startsWith("get")&&!methodName.startsWith("getClass")){  
					String fieldName = methodName.substring(3, methodName.length());  
					try{  
						Object value=m.invoke(of, null); 
						json.accumulate(fieldName, value);
					}catch(Exception e){  
						e.printStackTrace();  
					} 	                  
				} 
			}
			jsonarray.add(json);
		}		
		return jsonarray;
	}
	
	/**
	 * 把传递过来的List<OfficialDto3>转化为特定格式的JSON
	 * @param list 传递过来的OfficialDto3的集合
	 * @return JSONArray
	 */
	public static JSONArray official3_json(List<OfficialDto3> list){
		JSONArray jsonarray = new JSONArray();
		Class<?> c = OfficialDto3.class;
		Method[] ms = c.getDeclaredMethods();
		for(OfficialDto3 of : list){
			JSONObject json = new JSONObject();
			for(Method m:ms){
				String methodName=m.getName();//获取每一个方法名  
				//只得到具有get方法的属性，getClass除外  
				if(methodName.startsWith("get")&&!methodName.startsWith("getClass")){  
					String fieldName = methodName.substring(3, methodName.length());  
					try{  
						Object value=m.invoke(of, null); 
						json.accumulate(fieldName, value);
					}catch(Exception e){  
						e.printStackTrace();  
					} 	                  
				} 
			}
			jsonarray.add(json);
		}		
		return jsonarray;
	}
	
	/**
	 * 把传递过来的List<OnlineQADto>转化为特定格式的JSON
	 * @param list 传递过来的OnlineQADto的集合
	 * @return JSONArray
	 */
	public static JSONArray online_json(List<OnlineQADto> list){
		JSONArray jsonarray = new JSONArray();
		Class<?> c = OnlineQADto.class;
		
		Method[] ms = c.getDeclaredMethods();
		for(OnlineQADto of : list){
			JSONObject json = new JSONObject();
			for(Method m:ms){  
	            String methodName=m.getName();//获取每一个方法名  
	            //只得到具有get方法的属性，getClass除外  
	            if(methodName.startsWith("get")&&!methodName.startsWith("getClass")){  
	                String fieldName = methodName.substring(3, methodName.length());  
	                try{  
	                    Object value=m.invoke(of, null); 
	                    json.accumulate(fieldName, value);
	                }catch(Exception e){  
	                    e.printStackTrace();  
	                } 	                  
	            } 
			}
			jsonarray.add(json);
		}		
		return jsonarray;
	}
	
	
	public static JSONArray teacher_list(List<String> list){
		JSONArray jsonarray = new JSONArray();
		for(String s : list){
			JSONObject json = new JSONObject();
			String ss [] = s.split("[*]");
			for(int i = 0 ;i < ss.length ;i++){
					String [] bb = ss[i].split("[：]");
					json.accumulate(bb[0].toString(), bb[1].toString());
			}
			jsonarray.add(json);
		}
		return jsonarray;
	}
}
