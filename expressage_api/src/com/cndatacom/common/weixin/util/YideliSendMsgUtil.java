package com.cndatacom.common.weixin.util;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cndatacom.common.weixin.util.FileReadUtils;
import com.cndatacom.common.weixin.util.ThreeDes;

/** 
 * 类名: YideliSendMsgUtil</br> 
 * 包名：com.cndatacom.yixin.util </br> 
 * 描述: 微信公众平台发送消息工具类</br>
 * 发布版本号：v1.0</br>
 * 开发人员： 陆培波</br>
 * 创建时间： Mar 7, 2014 
 */ 

public class YideliSendMsgUtil {
	
	public String getMessage(InputStream is) throws Exception {
		String responseXml = "";
		try {
			Map<String, Object> map = new ParseMsgUtil().parseToMap(is);
			String msgType = map.get("msgType").toString();
			if("text".equals(msgType)) {//文本消息
				responseXml = responseTextMsg(map);
			} else if("image".equals(msgType)) {//图片消息
			} else if("audio".equals(msgType)) {//语音消息
			} else if("video".equals(msgType)) {//视频消息
			} else if("location".equals(msgType)) {//地理位置消息
			} else if("event".equals(msgType)) {//事件消息
				String event = map.get("event").toString();
				Object eventKey = map.get("eventKey");
				System.out.println("event : " + event);
				if(eventKey == null && "subscribe".equals(event)) {//关注公众号事件
					responseXml = responseSubscribeEventMsg(map);
				} else if(eventKey == null && "unsubscribe".equals(event)) {//取消关注公众号事件
					//取消关注不做处理
				} else if("CLICK".equalsIgnoreCase(event)) {//点击菜单拉取消息时的事件推送
					responseXml = responseClickEventMsg(map);
				} else if("VIEW".equalsIgnoreCase(event)) {//点击菜单跳转链接时的事件推送
					
				} else if("LOCATION".equalsIgnoreCase(event)) {//获取地理信息位置事件
					responseLocationEventMsg(map);
				} else if("SCAN".equalsIgnoreCase(event)) {//扫描带参数二维码，用户已关注时的事件推送
					responseScanEventMsg(map);
				} else {//扫描带参数二维码，用户未关注时，关注后的事件推送
					responseXml = responseSubscribeScanEventMsg(map);
				}
			}
		} catch(Exception e) {
			throw new Exception("发送消息出错：" + e);
		}
		return responseXml;
	}

	private void responseLocationEventMsg(Map<String, Object> map) {
		System.out.println("获取地理信息位置事件");
	}

	private String responseSubscribeScanEventMsg(Map<String, Object> map) {
		String eventKey = map.get("eventKey").toString();
		System.out.println("记录新用户扫描的二维码场景ID，ID=" + eventKey.substring(eventKey.indexOf("_") + 1));
		String toUser = map.get("fromUserName").toString();
		String fromUser = map.get("toUserName").toString();
		String text = getDocument().getElementsByTagName("WelcomeMessage").item(0).getTextContent();
		return getTextMsg(toUser, fromUser, text);
	}

	private void responseScanEventMsg(Map<String, Object> map) {
		System.out.println("记录老用户扫描的二维码场景ID");
	}

	/** 
	 * 方法名: responseClickEventMsg</br>
	 * 详述: 响应菜单点击事件，发送文本消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 17, 2014</br>
	 * @param map：菜单点击事件相关数据
	 * @return 发送的消息内容
	 */ 
	private String responseClickEventMsg(Map<String, Object> map) throws Exception {
		String toUser = map.get("fromUserName").toString();
		String fromUser = map.get("toUserName").toString();
		String eventKey = map.get("eventKey").toString();
		NodeList nodes = getDocument().getElementsByTagName("Message");
		for(int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			String key = element.getAttribute("key");
			if(! key.equals(eventKey)) {
				continue;
			}
			String type = element.getAttribute("type");
			if("text".equalsIgnoreCase(type)) {
				return getTextMsg(toUser, fromUser, element);
			} else if ("pic".equalsIgnoreCase(type)) {
				return getPicMsg(toUser, fromUser, element);
			} else if("picText".equalsIgnoreCase(type)) {
				String appendUrl = element.getAttribute("appendUrl");
				if("true".equalsIgnoreCase(appendUrl)) {
					return getPicTextMsg(toUser, fromUser, element, "weixin_openid=" + ThreeDes.getDes(toUser));
				}
				String shareUrl = element.getAttribute("shareUrl");
				if("true".equalsIgnoreCase(shareUrl)){
					//加密opendId
					String opendId=ThreeDes.getDes(toUser);
					return getPicTextMsg(toUser, fromUser, element, "op=" + opendId);
				}
				return getPicTextMsg(toUser, fromUser, element);
			} else {
				throw new Exception("message.xml填写错误，回复消息类型不存在！");
			}
		}
		return "";
	}

	private String responseSubscribeEventMsg(Map<String, Object> map) throws Exception {
		String toUser = map.get("fromUserName").toString();
		String fromUser = map.get("toUserName").toString();
		String text = "寄收不用等，一键“马上收”。发快递、收快递、查快递、管快递就用“马上收”。马上收微信公众号已经上线试运行。用户和快递员可通过微信公众号、安卓市场、豌豆荚等下载安卓版马上收用户端和快递员端app。IOS版app近期也将上线，敬请期待。";
		return getTextMsg(toUser, fromUser, text);
	}

	/** 
	 * 方法名: responseTextMsg</br>
	 * 详述: 分析文本消息内容，给出响应消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Apr 1, 2014</br>
	 * @param map：微信服务器推送包数据map
	 * @return 响应消息
	 * @throws Exception 异常时抛出
	 */ 
	private String responseTextMsg(Map<String, Object> map) throws Exception {
		String toUser = map.get("fromUserName").toString();
		String fromUser = map.get("toUserName").toString();
		String content = map.get("content").toString();
		NodeList nodes = getDocument().getElementsByTagName("Message");
		for(int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			String key = element.getAttribute("key");
			if(! key.equals(content.trim())) {
				continue;
			}
			String type = element.getAttribute("type");
			if("text".equalsIgnoreCase(type)) {
				return getTextMsg(toUser, fromUser, element);
			} else if ("pic".equalsIgnoreCase(type)){
				return getPicMsg(toUser, fromUser, element);
			} else if("picText".equalsIgnoreCase(type)) {
				return getPicTextMsg(toUser, fromUser, element);
			} else {
				throw new Exception("message.xml填写错误，回复消息类型不存在！");
			}
		}
		FileReadUtils uiFileReadUtils = new FileReadUtils();
		List<String> list = uiFileReadUtils.getReplyYDLTextList();
		String text = getDocument().getElementsByTagName("AlertMsg").item(0).getTextContent();
		for (int i = 0; i < list.size(); i++) {
			String[] strs = list.get(i).split("#");
			if(content.equals(strs[0])) {
				text = strs[1];
			}
		}
		return getTextMsg(toUser, fromUser, text);
	}
	
	private String getTextMsg(String toUser, String fromUser, Element element) {
		String text = element.getElementsByTagName("Text").item(0).getFirstChild().getNodeValue();
		text = text.replace("#n", "\n");
		return "<xml><ToUserName><![CDATA[" + toUser + "]]></ToUserName>" +
				"<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>" +
				"<CreateTime>" + System.currentTimeMillis() + "</CreateTime>" +
				"<MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA[" + text + "]]></Content>" +
				"</xml>";
	}
	
	private String getTextMsg(String toUser, String fromUser, String text) {
		text = text.replace("#n", "\n");
		return "<xml><ToUserName><![CDATA[" + toUser + "]]></ToUserName>" +
				"<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>" +
				"<CreateTime>" + System.currentTimeMillis() + "</CreateTime>" +
				"<MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA[" + text + "]]></Content>" +
				"</xml>";
	}
	
	private String getPicMsg(String toUser, String fromUser, Element element) throws Exception {
		int articleCount = Integer.parseInt(element.getElementsByTagName("ArticleCount").item(0).getFirstChild().getNodeValue());
		StringBuilder builder = new StringBuilder();
		builder.append("<xml><ToUserName><![CDATA[" + toUser + "]]></ToUserName>" +
				"<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>" +
				"<CreateTime>" + System.currentTimeMillis() + "</CreateTime>" +
				"<MsgType><![CDATA[news]]></MsgType>" +
				"<ArticleCount>" + articleCount + "</ArticleCount><Articles>");
		for(int i = 0; i < articleCount; i++) {
			NodeList list = element.getElementsByTagName("Item").item(i).getChildNodes();
			builder.append("<item>");
			for(int j = 0; j < list.getLength(); j ++) {
				String nodeName = list.item(j).getNodeName();
				String nodeValue = list.item(j).getTextContent();
				if(StringUtils.isBlank(nodeValue)) {
					continue;
				}
				builder.append("<" + nodeName+ "><![CDATA[" + nodeValue + "]]></" + nodeName + ">");
			}
			builder.append("</item>");
		}
		builder.append("</Articles></xml>");
		return builder.toString();
	}
	
	private String getPicTextMsg(String toUser, String fromUser, Element element) throws Exception {
		int articleCount = Integer.parseInt(element.getElementsByTagName("ArticleCount").item(0).getFirstChild().getNodeValue());
		StringBuilder builder = new StringBuilder();
		builder.append("<xml><ToUserName><![CDATA[" + toUser + "]]></ToUserName>" +
				"<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>" +
				"<CreateTime>" + System.currentTimeMillis() + "</CreateTime>" +
				"<MsgType><![CDATA[news]]></MsgType>" +
				"<ArticleCount>" + articleCount + "</ArticleCount><Articles>");
		for(int i = 0; i < articleCount; i++) {
			NodeList list = element.getElementsByTagName("Item").item(i).getChildNodes();
			builder.append("<item>");
			for(int j = 0; j < list.getLength(); j ++) {
				String nodeName = list.item(j).getNodeName();
				String nodeValue = list.item(j).getTextContent();
				if(StringUtils.isBlank(nodeValue)) {
					continue;
				}
				if("Url".equals(nodeName)) {
					if(nodeValue.startsWith("http://mp.weixin.qq.com")) {
						nodeValue = nodeValue.replace("$", "&");
					}
				}
				builder.append("<" + nodeName+ "><![CDATA[" + nodeValue + "]]></" + nodeName + ">");
			}
			builder.append("</item>");
		}
		builder.append("</Articles></xml>");
		return builder.toString();
	}
	
	private String getPicTextMsg(String toUser, String fromUser, Element element, String appendUrl) throws Exception {
		int articleCount = Integer.parseInt(element.getElementsByTagName("ArticleCount").item(0).getFirstChild().getNodeValue());
		StringBuilder builder = new StringBuilder();
		builder.append("<xml><ToUserName><![CDATA[" + toUser + "]]></ToUserName>" +
				"<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>" +
				"<CreateTime>" + System.currentTimeMillis() + "</CreateTime>" +
				"<MsgType><![CDATA[news]]></MsgType>" +
				"<ArticleCount>" + articleCount + "</ArticleCount><Articles>");
		for(int i = 0; i < articleCount; i++) {
			NodeList list = element.getElementsByTagName("Item").item(i).getChildNodes();
			builder.append("<item>");
			for(int j = 0; j < list.getLength(); j ++) {
				String nodeName = list.item(j).getNodeName();
				String nodeValue = list.item(j).getTextContent();
				if(StringUtils.isBlank(nodeValue)) {
					continue;
				}
				if("Url".equals(nodeName)) {
					nodeValue = appendUrl(nodeValue, appendUrl);
				}
				builder.append("<" + nodeName+ "><![CDATA[" + nodeValue + "]]></" + nodeName + ">");
			}
			builder.append("</item>");
		}
		builder.append("</Articles></xml>");
		return builder.toString();
	}
	
	private String appendUrl(String url, String appendUrl) {
		//url包含有参数
		if(url.indexOf("?") != -1) {
			return url + "&" + appendUrl;
		}
		//url中没有参数
		return url + "?" + appendUrl;
	}

	private Document getDocument() {
		//获得dom解析器工厂（工作的作用是用于创建具体的解析器）
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//获得具体的dom解析器
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = YideliSendMsgUtil.class.getResourceAsStream("/yideliWeixinMessage.xml");
			return db.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
