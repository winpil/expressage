package com.cndatacom.common.weixin.inter;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cndatacom.common.weixin.util.HttpUtils;
import com.cndatacom.common.weixin.util.WeixinConstants;

/** 
 * 类名: GetYideliAccessToken</br> 
 * 包名：com.cndatacom.weixin.inter </br> 
 * 描述: 获取翼得利微信公众平台访问凭证</br>
 * 发布版本号：v1.0</br>
 * 开发人员： 陆培波</br>
 * 创建时间： May 16, 2014 
 */ 

public class GetYideliAccessToken {
	
	private static Logger logger = LoggerFactory.getLogger(GetYideliAccessToken.class);
	
	private static final String URL = "https://" + WeixinConstants.WEIXIN_PLUS_IP + "/cgi-bin/token";

	public String execute() {
		logger.info("-------获取翼得利微信公众平台访问凭证-------");
		//请求参数
		String grant_type = "client_credential";//获取access_token填写client_credential
		//翼得利开发者凭据
		String appid = "wxebcaab5d576a1b58";//第三方用户唯一凭证
		String secret = "54d8547740b49ade6e66db086c72caf4";//第三方用户唯一凭证密钥
		String accessUrl = URL + "?grant_type=" + grant_type + "&appid=" + appid + "&secret=" + secret;
		String accessToken = "";
		try {
			JSONObject json = HttpUtils.getMethodRequest(accessUrl);
			if(json != null) {
				accessToken = json.getString("access_token");//获取到的凭证
				int expiresIn = json.getInt("expires_in");//凭证有效时间，单位：秒
				logger.info("获取到的凭证：" + accessToken + ", 有效时间：" + expiresIn/3600 + "小时");
			} else {
				logger.info("获取凭证失败");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
}