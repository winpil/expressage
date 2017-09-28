package com.cndatacom.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.cndatacom.common.weixin.util.Sha1;

import sun.misc.BASE64Encoder;


public class WxUtil {
	
	/**
	 * 
	 * ������: createSign</br>
	 * ����: ΢��ǩ���㷨</br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2016-8-3</br>
	 * @param packageParams
	 * @return
	 * @throws Exception
	 * ��˾ ���������ֺ�����Ƽ����޹�˾(http://www.aleven.cn/)</br>
	 */
	public static String createSign(SortedMap packageParams,String keyStr) throws Exception{
		StringBuffer sb = new StringBuffer();
		Set<Entry> es = packageParams.entrySet();
		Iterator<Entry> it = es.iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = entry.getValue() != null ? entry.getValue().toString() : null;
	
			if (null != v && !"".equals(v)&& !"sign".equals(k)&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + keyStr);

		return MD5Service.encryptString(sb.toString());
	}
	
	public static String createSign1(SortedMap packageParams) throws Exception{
		StringBuffer sb = new StringBuffer();
		Set<Entry> es = packageParams.entrySet();
		Iterator<Entry> it = es.iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = entry.getValue() != null ? entry.getValue().toString() : null;
	
			if (null != v && !"".equals(v)&& !"sign".equals(k)&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		return Sha1.encode(sb.toString().substring(0,sb.toString().length()-1));
	}
	
	/**
	 * 
	 * ������: invokingSMS</br>
	 * ����: ����post����</br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2016-8-3</br>
	 * @param postData
	 * @param postUrl
	 * @return
	 * ��˾ ���������ֺ�����Ƽ����޹�˾(http://www.aleven.cn/)</br>
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
	
	public static Document parseXMLDocument(String xmlString) {  
        if (xmlString == null) {  
            throw new IllegalArgumentException();  
        }  
        try {  
            return newDocumentBuilder().parse(  
                    new InputSource(new StringReader(xmlString)));  
        } catch (Exception e) {  
            throw new RuntimeException(e.getMessage());  
        }  
    }
	
	public static DocumentBuilder newDocumentBuilder()  
		    throws ParserConfigurationException {  
		return newDocumentBuilderFactory().newDocumentBuilder();  
	}
	
	public static DocumentBuilderFactory newDocumentBuilderFactory() {  
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        dbf.setNamespaceAware(true);  
        return dbf;  
    }
	
	public static DocumentBuilder getDocumentBuilder() {
		//���dom���������������������������ڴ�������Ľ�������
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//��þ����dom������
		try {
			return dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String callMapToXML(Map map) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        mapToXMLTest2(map, sb); 
        sb.append("</xml>");
        try {
        	System.out.println(sb.toString());
            return sb.toString();  
        } catch (Exception e) {  
        }  
        return null;  
    }  
	
	private static void mapToXMLTest2(Map map, StringBuffer sb) {  
        Set set = map.keySet();  
        for (Iterator it = set.iterator(); it.hasNext();) {  
            String key = (String) it.next();  
            Object value = map.get(key);  
            if (null == value)  
                value = "";  
            if (value.getClass().getName().equals("java.util.ArrayList")) {  
                ArrayList list = (ArrayList) map.get(key);  
                sb.append("<" + key + ">");  
                for (int i = 0; i < list.size(); i++) {  
                    HashMap hm = (HashMap) list.get(i);  
                    mapToXMLTest2(hm, sb);  
                }  
                sb.append("</" + key + ">");  
  
            } else {  
                if (value instanceof HashMap) {  
                    sb.append("<" + key + ">");  
                    mapToXMLTest2((HashMap) value, sb);  
                    sb.append("</" + key + ">");  
                } else {  
                    sb.append("<" + key + ">" + value + "</" + key + ">");  
                }  
  
            }  
        }  
    } 
	
}
