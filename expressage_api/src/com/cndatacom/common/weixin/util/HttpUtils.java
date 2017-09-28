package com.cndatacom.common.weixin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
/**
 * http请求工具类
 * @author lupb
 * @date 2012-10-17 创建
 */
public class HttpUtils {
	
	private static HttpClient httpClient;
	
	/**
	 * 描述:多线程http连接管理器，用于管理http连接
	 */
	private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
	
	private HttpUtils() {}
	
	/**
	 * @description 获取httpClient实例
	 * @author lupb
	 * @date Oct 17, 2012
	 * @return HttpClient实例
	 */
	public static HttpClient getHttpClient() {
		if(httpClient == null) {
			httpClient = new HttpClient(httpConnectionManager);
			//忽略Cookies
			httpClient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
			 //每主机最大连接数和总共最大连接数，通过hosfConfiguration设置host来区分每个主机  
			httpClient.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(8);
			httpClient.getHttpConnectionManager().getParams().setMaxTotalConnections(48);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);
			httpClient.getHttpConnectionManager().getParams().setTcpNoDelay(true);
			httpClient.getHttpConnectionManager().getParams().setLinger(1000);     
		    //失败的情况下会进行3次尝试,成功之后不会再尝试
			httpClient.getHttpConnectionManager().getParams().setParameter(
					HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		}
		return httpClient;
	}
	
	/**
	 * @description 向指定URL发送get请求
	 * @author lupb
	 * @date Oct 17, 2012
	 * @param url 请求url
	 * @return 成功：返回json对象，失败或异常：返回null
	 */
	public static JSONObject getMethodRequest(String url) {
		GetMethod getMethod = new GetMethod(url);
		try {
			//访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(getMethod);
			//返回成功状态码200
			if (statusCode == 200) {
				String str = getMethod.getResponseBodyAsString(9999);
				return JSONObject.fromObject(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();//释放连接
		}
		return null;
	}
	/**
	 * @author ssl
	 * @since 2012-12-7
	 * @param url
	 * @return 
	 */
	public static String getRequest(String url){
		GetMethod getMethod = new GetMethod(url);
		try {
			//访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(getMethod);
			//返回成功状态码200
			if (statusCode == 200) {
				String str = getMethod.getResponseBodyAsString(99999);
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();//释放连接
		}		
		return null;
	}
	
	/**
	 * @author ssl
	 * @since 2012-12-7
	 * @param url
	 * @return 
	 */
	public static JSONObject getPostRequest(String url){
		PostMethod postMethod = new PostMethod(url);
		try {
			//访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(postMethod);
			//返回成功状态码200
			if (statusCode == 200) {
				String str = postMethod.getResponseBodyAsString(9999);
				return JSONObject.fromObject(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();//释放连接
		}				
		return null;
	}
	
	/** 
	 * 方法名: String postMethodRequest(String url, String data)</br>
	 * 详述: 向指定url发出HttpPost请求，请求数据转换为流</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Jan 11, 2013</br>
	 * @param url 访问地址
	 * @param data 请求提交的数据
	 * @return 返回数据
	 */ 
	public static String postMethodRequest(String url, String data) {
		String responseStr = "";
		PostMethod postMethod = new PostMethod(url);
		try {
			RequestEntity en = new StringRequestEntity(data,
					"text/plain", "utf-8");
			postMethod.setRequestEntity(en);
			//访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(postMethod);
			//返回成功状态码200
			if (statusCode == 200) {
				responseStr = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();//释放连接
		}
		return responseStr;
	}
	
	public static String PostByHttpClient(String url,List<NameValuePair> basicNameValuePairs){
		try {
			HttpClient client=new HttpClient();
			PostMethod post=new PostMethod(url);
			post.getParams().setContentCharset("utf-8");
			if(basicNameValuePairs!=null)
			post.setRequestBody(basicNameValuePairs.toArray(new NameValuePair[basicNameValuePairs.size()]));
			//访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(post);
			//返回成功状态码200
			if (statusCode == 200) {
				String str = post.getResponseBodyAsString();
				post.releaseConnection();
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws ParseException{
		NameValuePair pair=new NameValuePair("tel", "18316552259");
		List<NameValuePair> pairs=new ArrayList<NameValuePair>();
		pairs.add(pair);
		String str=HttpUtils.PostByHttpClient("http://tcc.taobao.com/cc/json/mobile_tel_segment.htm", pairs);
		//HttpUtils.getRequest("http://gc.ditu.aliyun.com/geocoding?a=苏州市");
		//System.out.println(str+"------------");
		Long timestamp=System.currentTimeMillis()-new SimpleDateFormat("yyyy-MM-dd mm:hh:ss").parse("1971-01-01 00:00:00").getTime();
		System.out.println(timestamp);
	}
}