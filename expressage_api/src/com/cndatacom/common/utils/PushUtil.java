package com.cndatacom.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Encoder;

public class PushUtil {
	
	/**
	 * 
	 * 方法名: pushByPhone</br>
	 * 详述: 极光推送</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-4-23</br>
	 * @return
	 */
	public static boolean pushByPhone(String phone,String content){
		boolean b = false;
		try {
			if(StringUtils.isNotBlank(phone)&&StringUtils.isNotBlank(content)){
				StringBuilder sb=new StringBuilder();
				sb.append("{\"n_content\":\"");
				sb.append(content);
				sb.append("\"}");
				
				Random randomNum = new Random();
				int sendno = Math.abs(randomNum.nextInt());
				String appKey ="9b088f568ff10505906a1693";
				int receiverType =2;
				String receiverValue = phone;
				String masterSecret="e5e9310d9eeeb642012ad3e0";
				
				String input = String.valueOf(sendno) + receiverType + receiverValue + masterSecret;
				String verificationCode = MD5Service.encryptString(input).toUpperCase();
				System.out.println("sendno==="+sendno);
				System.out.println("receiverType==="+receiverType);
				System.out.println("receiverValue==="+receiverValue);
				System.out.println("masterSecret==="+masterSecret);
				System.out.println("verificationCode==="+verificationCode);
				
				
				String postStr="sendno="+sendno+"&app_key="+appKey+"&receiver_type="+receiverType+"&receiver_value="+receiverValue+"&verification_code="+verificationCode+
				"&msg_type="+1+"&msg_content="+sb.toString()+"&platform=android,ios";
				String postUrl="http://api.jpush.cn:8800/v2/push";
				String str = invokingSMS(postStr,postUrl);
				System.out.println(str);
				System.out.println("postStr==="+postStr);
				if(str.indexOf("Succeed")>0){
					b = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * 
	 * 方法名: invokingSMS</br>
	 * 详述: 发送post请求</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-4-23</br>
	 * @param postData
	 * @param postUrl
	 * @return
	 */
	public static String invokingSMS(String postData, String postUrl) {
        try { 
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
	
	public static void main(String[] args) {
//		boolean b = pushByPhone("13660627043","你好，中国！！");
//		System.out.println("b=================="+b);
		//System.out.println(invokingSMS1(null,null));
		
		//发送 POST 请求
//        String sr=sendGet("http://m.kuaidi100.com/index_all.html", "type=yuantong&postid=700245485239");
		String str = invokingSMS("","");
        System.out.println(str);
	}
	
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
	public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
	
	public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
	
}
