package com.cndatacom.rbac.system.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.utils.Configure;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.common.web.struts2.Struts2Utils;
import com.cndatacom.common.weixin.util.Sha1;
import com.cndatacom.common.weixin.util.YideliSendMsgUtil;

/** 
 * 类名: YideliWeixinRegistAction</br> 
 * 包名：com.cndatacom.plus.action </br> 
 * 描述: 验证URL有效性成功后即接入生效，成为开发者。可以在公众平台网站可申请获得众多接口权限，以满足开发者需求。
	此后用户每次向公众号发送消息、或者产生自定义菜单点击事件时，响应URL将得到推送。</br>
 * 发布版本号：1.0</br>
 * 开发人员： 陆培波</br>
 * 创建时间： May 22, 2014 
 */ 

@Controller
@Action("yideliWeixinRegist")
@Scope("prototype")
@Namespace("/rbac/sys")
public class YideliWeixinRegistAction extends SimpleActionSupport{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Action(value = "/join")
	public void regist() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		
		logger.info("-----------微信公众号验证（响应）URL接口-----------");
		try {
			String signature = request.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
			String timestamp = request.getParameter("timestamp");//时间戳
			String nonce = request.getParameter("nonce");//随机数
			String echostr = request.getParameter("echostr");//随机字符串
			//String token = "929996411dac8008ab67f1b376c5f5a5";//YIDELI_WEIXIN，MD5方式加密
			String token =  Configure.getSingleton().values("wx_token");
			logger.info("-----获取的token>>>>>>>>>>>> " + token + " ----------");
			logger.info("上传参数： [ signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr=" + echostr + " ]");
			
			//将token、timestamp、nonce三个参数进行字典序排序
			List<String> list = new ArrayList<String>();
			list.add(token);
			list.add(timestamp);
			list.add(nonce);
			spellComparator(list);//字典序排序
			//将三个参数字符串拼接成一个字符串进行sha1加密
			StringBuilder builder = new StringBuilder();
			for(String str : list) {
				builder.append(str);
			}
			String encodeStr = Sha1.encode(builder.toString());
			//将加密串与signature对比，标识该请求来源于微信，其他来源不做响应
			if(signature.equalsIgnoreCase(encodeStr)) {
				logger.info("来源于微信公众号，通过验证...");
				//接收并解析XML数据包，返回响应结果
				if(echostr == null) {
					String responseXml = new YideliSendMsgUtil().getMessage(request.getInputStream());
					logger.info("响应XML=" + responseXml);
					response.getWriter().write(responseXml);
				//url验证，将echostr返回微信服务器
				} else {
					response.getWriter().write(echostr);
				}
				response.getWriter().flush();
				response.getWriter().close();
			}
			logger.info("微信公众号验证（响应）URL接口调用成功：200");
		} catch (Exception e) {
			logger.error("微信公众号验证（响应）URL接口调用失败：500。失败原因：" + e);
		}
	}
	
	/** 
	 * 方法名: spellComparator</br>
	 * 详述: 字典排序方法</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 5, 2014</br>
	 * @param list
	 */
	private void spellComparator(List<String> list) {
		Collections.sort(list, new Comparator() {
			public int compare(final Object o1, final Object o2) {
				try {
					// 取得比较对象的汉字编码，并将其转换成字符串
					final String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
					final String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
					// 运用String类的 compareTo（）方法对两对象进行比较
					return s1.compareTo(s2);
				} catch (final Exception e) { 
					e.printStackTrace();
				}
				return 0;
			}
		});
	}

	@Override
	protected Object createNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IBaseService getManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object obj) {
		// TODO Auto-generated method stub
		
	}
	
}