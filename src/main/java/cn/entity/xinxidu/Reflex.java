package cn.entity.xinxidu;

import java.lang.reflect.Method;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Author xidu-lyb
 * @Time   2015年7月14日上午8:47:45
 * 
 */
public class Reflex {
	
	public JSONArray toOfficjson(List<OfficialDto> list){
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
	
	
	
	public JSONArray toOffic2(List<OfficDto2> list) {
		JSONArray jsonarray = new JSONArray();
		Class<?> c = OfficDto2.class;
		Method[] ms = c.getDeclaredMethods();
		for (OfficDto2 of : list) {
			JSONObject json = new JSONObject();
			for (Method m : ms) {
				String methodName = m.getName();//获取每一个方法名  
	            //只得到具有get方法的属性，getClass除外  
				if (methodName.startsWith("get")&&!methodName.startsWith("getClass")){
					String fieldName = methodName.substring(3, methodName.length());
					try{
						Object value = m.invoke(of, null);
						json.accumulate(fieldName, value);
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}
			jsonarray.add(json);
		}
		return jsonarray;
	}	
	public JSONArray toOffic3json(List<OfficialDto3> list){
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
	public JSONArray toBilljson(List<BillDto> list){
		JSONArray jsonarray = new JSONArray();
		Class<?> c = BillDto.class;
		
		Method[] ms = c.getDeclaredMethods();
		for(BillDto of : list){
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
	public JSONArray toOnlinejson(List<OnlineQADto> list){
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
	public JSONArray tostrings(String connet){
		JSONArray jsonarray = new JSONArray();
		JSONObject json = new JSONObject();
		String newconnet = connet;
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
	public JSONObject tostrings2(String jiaoyi){
		JSONObject json = new JSONObject();
		json.accumulate("jiaoyi", jiaoyi);
		return json;
	}
	public JSONObject tostrings3(String gongsi){
		JSONObject json = new JSONObject();
		json.accumulate("gongsi", gongsi);
		return json;
	}
	
	public JSONArray tostrlist(List<String> list){
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
	
	public JSONObject tostr(String str){
		JSONObject json = new JSONObject();
		String ss[] = str.split("[*]");
		for(int i = 0; i < ss.length;i++){
			String [] bb = ss[i].split("：");
			json.accumulate(bb[0].toString(), bb[1].toString());
		}
		return json;
	}
	
}
