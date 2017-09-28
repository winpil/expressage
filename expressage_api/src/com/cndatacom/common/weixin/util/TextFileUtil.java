package com.cndatacom.common.weixin.util;  
  
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
  
/** 
 * 类名: TextFileUtil</br> 
 * 包名：com.cndatacom.common.util </br> 
 * 描述: 此工具类用于文本文件的读写 </br>
 * 发布版本号：v1.0</br>
 * 开发人员： 陆培波</br>
 * 创建时间： Mar 12, 2014 
 */ 

public class TextFileUtil {
	
    // 读取指定路径文本文件  
    public static String read(String filePath) {  
        StringBuilder str = new StringBuilder();  
        BufferedReader in = null;  
        try {  
            in = new BufferedReader(
            		new InputStreamReader(new FileInputStream(filePath),"gbk"));  
            String s;  
            try {  
                while ((s = in.readLine()) != null)  
                    str.append(s + "\n");  
            } finally {  
                in.close();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return str.toString();  
    }  
  
    // 写入指定的文本文件，append为true表示追加，false表示重头开始写，  
    //text是要写入的文本字符串，text为null时直接返回  
    public static void write(String filePath, boolean append, String text) { 
    	File file=new File(filePath);
        if (text == null)  
            return;  
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file,  
                    append));  
            try {  
                out.write(text+"\n");  
                out.flush();
            } finally {  
                out.close();
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  