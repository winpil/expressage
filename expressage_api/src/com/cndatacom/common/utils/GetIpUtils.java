package com.cndatacom.common.utils;

import javax.servlet.http.HttpServletRequest;

public class GetIpUtils {

	/**
	 * 
	 * 方法名: GetIpUtils.java</br>
	 * 详述: 获取ip</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-5-5</br>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getIp(HttpServletRequest request) throws Exception {
		try {
			if (request.getHeader("x-forwarded-for") == null) { 
			    return request.getRemoteAddr(); 
			}else{
				return request.getHeader("x-forwarded-for");  
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String SplicePath(HttpServletRequest request,String url){
		String ctxPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/expressage_api";//外网地址,不用加项目名称，因为ckfinder保存路径时带项目名称
		if(url!=null&&url.length()>0 &&url.startsWith("http")){
			return url;
		}else if(url==null||url.length()==0 ){
			return url;
		}
		else{
			return ctxPath+url;
		}
	}
	
}
