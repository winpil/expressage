package com.cndatacom.common.weixin.inter;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cndatacom.common.utils.Configure;
import com.cndatacom.common.weixin.util.HttpUtils;
import com.cndatacom.common.weixin.util.TextFileUtil;
import com.cndatacom.common.weixin.util.WeixinConstants;

@Component("getAccessTokenSchedule")
public class GetAccessTokenSchedule {

	private static Logger logger = LoggerFactory.getLogger(GetAccessTokenSchedule.class);
	
	/**
	 * 描述:微信公众平台接口地址
	 */
	private static final String WX_URL = "https://" + WeixinConstants.WEIXIN_PLUS_IP + "/cgi-bin/token";
	
	/** 
	 * 方法名: executeSchedule</br>
	 * 详述: 调度入口方法</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Jul 16, 2014</br>
	 */ 
	public void executeSchedule() {
		try {
			//翼得利微信凭证
			getYdlWeixinToken();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/** 
	 * 方法名: getYdlWeixinToken</br>
	 * 详述: 获取翼得利微信公众平台接口访问凭证</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Jul 16, 2014</br>
	 * @throws Exception
	 */ 
	private void getYdlWeixinToken() throws Exception {
		logger.info("-------获取翼得利微信公众平台接口访问凭证-------");
		//请求参数
		String grant_type = "client_credential";//获取access_token填写client_credential
		String appid = "wxb29f8c9e9415156f";//第三方用户唯一凭证
		String secret = "b8c6f0635376b1dfead52faecfe9dfee";//第三方用户唯一凭证密钥
		String accessUrl = WX_URL + "?grant_type=" + grant_type + "&appid=" + appid + "&secret=" + secret;
		logger.info(accessUrl);
		JSONObject json = HttpUtils.getMethodRequest(accessUrl);
		if(json != null) {
			if(json.containsKey("errcode")) {//返回错误提示编码
				logger.info(json.toString());
			} else {
				String accessToken = json.getString("access_token");//获取到的凭证
				int expiresIn = json.getInt("expires_in");//凭证有效时间，单位：秒
				logger.info("获取到的凭证：" + accessToken + ", 有效时间：" + expiresIn/3600 + "小时");
				TextFileUtil.write(Configure.getSingleton().values("ydl_wx_token_filePath"), false, accessToken);
				//获取js sdk访问临时票据
				String jsUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
				logger.info(jsUrl);
				JSONObject jsonApi = HttpUtils.getMethodRequest(jsUrl);
				if(jsonApi!=null){
					if(json.containsKey("errcode")) {//返回错误提示编码
						logger.info(json.toString());
					} else {
						String ticket = jsonApi.getString("ticket");//获取到的凭证
						int expiresIn1 = jsonApi.getInt("expires_in");//凭证有效时间，单位：秒
						logger.info("获取到的js sdk凭证：" + ticket + ", 有效时间：" + expiresIn1/3600 + "小时");
						TextFileUtil.write(Configure.getSingleton().values("wx_jsapi_ticket"), false, ticket);
					}
				}
				
			}
		} else {
			logger.info("获取凭证失败");
		}
	}
	
	public static void main(String[] args) {
		try {
			new GetAccessTokenSchedule().getYdlWeixinToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
