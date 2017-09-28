package com.cndatacom.common.weixin.inter;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cndatacom.common.utils.Configure;
import com.cndatacom.common.weixin.util.HttpUtils;
import com.cndatacom.common.weixin.util.TextFileUtil;
import com.cndatacom.common.weixin.util.WeixinConstants;

/** 
 * 类名: YideliMenu</br> 
 * 包名：com.cndatacom.weixin.inter </br> 
 * 描述: 微信公众平台菜单创建接口</br>
 * 发布版本号：v1.0</br>
 * 开发人员： 陆培波</br>
 * 创建时间： May 16, 2014 
 */ 

public class YideliMenu {
	
	private static Logger logger = LoggerFactory.getLogger(YideliMenu.class);
	
	/**
	 * 描述:创建自定义菜单接口url
	 */
	private static final String CREATED_URL = "https://" + WeixinConstants.WEIXIN_PLUS_IP + "/cgi-bin/menu/create";
	/**
	 * 描述:查询自定义菜单接口url
	 */
	private static final String QUERY_URL = "https://" + WeixinConstants.WEIXIN_PLUS_IP + "/cgi-bin/menu/get";
	/**
	 * 描述:删除自定义菜单接口url
	 */
	private static final String DELETE_URL = "https://" + WeixinConstants.WEIXIN_PLUS_IP + "/cgi-bin/menu/delete";
	
	/** 
	 * 方法名: createMenu</br>
	 * 详述: 创建菜单</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Apr 1, 2014</br>
	 */ 
	public void createMenu() {
		logger.info("---------创建微信公众号菜单--------");
		try {
			//获取访问凭证
			//String token = TextFileUtil.read(Configure.getSingleton().values("ydl_wx_token_filePath")).trim();
			String token = new GetAccessToken().execute();
			logger.info("获取到的access_token：" + token);
			//获取凭证失败，返回
			if(StringUtils.isBlank(token)) {
				logger.info("获取凭证失败");
				return;
			}
			String accessUrl = CREATED_URL + "?access_token=" + token;
			System.out.println(accessUrl);
			//生成自定义菜单
			String menu = TextFileUtil.read(Configure.getSingleton().values("yideli_weixin_menu"));
			logger.info(menu);
			//调用创建菜单接口
			String content = HttpUtils.postMethodRequest(accessUrl, menu);
			//处理返回结果
			if(StringUtils.isNotBlank(content)) {
				JSONObject json = JSONObject.fromObject(content);
				String message = json.getString("errmsg");//返回的结果信息
				logger.info(message);
			} else {
				logger.info("返回数据为空");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 方法名: getMenu</br>
	 * 详述: 查询菜单</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Apr 1, 2014</br>
	 * @return 菜单信息
	 */ 
	public static JSONObject getMenu() {
		logger.info("---------查询微信公众号菜单--------");
		JSONObject json = null;
		try {
			//获取访问凭证
			String token = new GetYideliAccessToken().execute();
			//获取凭证失败，返回
			if(StringUtils.isBlank(token)) {
				logger.info("获取凭证失败");
				return json;
			}
			String accessUrl = QUERY_URL + "?access_token=" + token;
			//调用查询菜单接口
			json = HttpUtils.getMethodRequest(accessUrl);
			//处理返回结果
			if(json != null) {
				logger.info(json.toString());//返回的菜单信息
			} else {
				logger.info("返回数据为空");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/** 
	 * 方法名: deleteMenu</br>
	 * 详述: 删除菜单</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Apr 1, 2014</br>
	 */ 
	public static void deleteMenu() {
		logger.info("---------删除微信公众号菜单--------");
		try {
			//获取访问凭证
			String token = new GetYideliAccessToken().execute();
			//获取凭证失败，返回
			if(StringUtils.isBlank(token)) {
				logger.info("获取凭证失败");
				return;
			}
			String accessUrl = DELETE_URL + "?access_token=" + token;
			//调用删除菜单接口
			JSONObject json = HttpUtils.getMethodRequest(accessUrl);
			//处理返回结果
			if(json != null) {
				String message = json.getString("errmsg");
				logger.info(message);
			} else {
				logger.info("返回数据为空");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}