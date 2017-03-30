package cn.base.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
  
/** 
* @ClassName: AppendFile 
* @Description: 写任意目录下文件
* @author ZHANGCHENG
* @date 2016-9-5 下午2:50:48 
*  
*/ 
/** 
* @ClassName: AppendFile 
* @Description: TODO
* @author ZHANGCHENG
* @date 2016-9-5 下午2:51:56 
*  
*/ 
public class AppendFile {  
      
    protected static void method1(String file, String conent) {     
        BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));     
            out.write(conent);     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }     
    }     
    
    /**   
     * 追加文件：使用FileWriter   
     *    
     * @param fileName   
     * @param content   
     */    
    protected static void method2(String fileName, String content) {   
        FileWriter writer = null;  
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter(fileName, true);     
            writer.write(content);       
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(writer != null){  
                    writer.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
    }     
    
    /**   
     * 追加文件：使用RandomAccessFile   
     *    
     * @param fileName 文件名   
     * @param content 追加的内容   
     */    
    protected static void method3(String fileName, String content) {   
        RandomAccessFile randomFile = null;  
        try {     
            // 打开一个随机访问文件流，按读写方式     
            randomFile = new RandomAccessFile(fileName, "rw");     
            // 文件长度，字节数     
            long fileLength = randomFile.length();     
            // 将写文件指针移到文件尾。     
            randomFile.seek(fileLength);     
            randomFile.writeBytes(content);      
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally{  
            if(randomFile != null){  
                try {  
                    randomFile.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }    
  
    
    /** 
    * @Title: write 
    * @Description: 写文件
    * @param @param fileName 文件名
    * @param @param filePath 路径
    * @param @param content 内容
    * @return void
    * @throws 
    */ 
    public static void write(String fileName,String filePath,String content){
         try {
        	 File fp = new File(filePath);  
        	 if(!fp.exists()){
        		 fp .mkdirs(); 
        	 }
        	 
        	 File file = new File(filePath+"\\"+fileName);  
        	 if(!file.exists()){
        		 file.createNewFile(); 
        	 }
			method2(filePath+"\\"+fileName, content); 
		} catch (IOException e) {
			
		}  
    }
}  

