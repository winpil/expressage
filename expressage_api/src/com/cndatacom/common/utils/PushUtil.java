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
	 * ������: pushByPhone</br>
	 * ����: ��������</br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2015-4-23</br>
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
	 * ������: invokingSMS</br>
	 * ����: ����post����</br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2015-4-23</br>
	 * @param postData
	 * @param postUrl
	 * @return
	 */
	public static String invokingSMS(String postData, String postUrl) {
        try { 
            //����POST����
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

            //��ȡ��Ӧ״̬
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //��ȡ��Ӧ������
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
//		boolean b = pushByPhone("13660627043","��ã��й�����");
//		System.out.println("b=================="+b);
		//System.out.println(invokingSMS1(null,null));
		
		//���� POST ����
//        String sr=sendGet("http://m.kuaidi100.com/index_all.html", "type=yuantong&postid=700245485239");
		String str = invokingSMS("","");
        System.out.println(str);
	}
	
	
	/**
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
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
            // �򿪺�URL֮�������
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("���� POST ��������쳣��"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
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
