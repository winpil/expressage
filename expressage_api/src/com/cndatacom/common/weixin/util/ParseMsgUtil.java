package com.cndatacom.common.weixin.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

/** 
 * 类名: ParseMsgUtil</br> 
 * 包名：com.cndatacom.yixin.util </br> 
 * 描述: 消息解析工具类</br>
 * 发布版本号：v1.0</br>
 * 开发人员： 陆培波</br>
 * 创建时间： Mar 7, 2014 
 */ 

public class ParseMsgUtil {
	
	/** 
	 * 方法名: getDocumnetBuilder</br>
	 * 详述: 获取DOM解析器</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @return DOM解析器
	 */ 
	private DocumentBuilder getDocumentBuilder() {
		//获得dom解析器工厂（工作的作用是用于创建具体的解析器）
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//获得具体的dom解析器
		try {
			return dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * 方法名: parseToMap</br>
	 * 详述: 解析XML消息，并转换成map格式</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param is：消息数据流
	 * @return 消息Map
	 * @throws Exception：解析出现异常时抛出
	 */ 
	public Map<String, Object> parseToMap(InputStream is) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Document document = getDocumentBuilder().parse(is);
			String msgType = document.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue();
			if("text".equals(msgType)) {//文本消息
				map = parseTextMsg(map, document);
			} else if("image".equals(msgType)) {//图片消息
				map = parseImageMsg(map, document);
			} else if("voice".equals(msgType)) {//语音消息
				map = parseAudioMsg(map, document);
			} else if("video".equals(msgType)) {//视频消息
				map = parseVideoMsg(map, document);
			} else if("location".equals(msgType)) {//地理位置消息
				map = parseLocationMsg(map, document);
			} else if("link".equals(msgType)) {//链接消息
				map = parseLinkMsg(map, document);
			} else if("event".equals(msgType)) {//事件消息
				map = parseEventMsg(map, document);
			}
			map.put("msgType", msgType);
		} catch(Exception e) {
			throw new Exception("消息解析出错：" + e);
		}
		return map;
	}
	
	/** 
	 * 方法名: parseEventMsg</br>
	 * 详述: 解析事件消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseEventMsg(Map<String, Object> map, 
			Document document) throws Exception {
		String event = document.getElementsByTagName("Event").item(0).getFirstChild().getNodeValue();
		String eventKey = null;
		try {
			eventKey = document.getElementsByTagName("EventKey").item(0).getFirstChild().getNodeValue();
		} catch (NullPointerException e) {
		}
		map.put("event", event);
		System.out.println("eventKey : " + eventKey);
		System.out.println("eventxiaozhu : " + event);
		if("subscribe".equalsIgnoreCase(event)) {// 关注事件
			System.out.println("11111111111111");
			return parseSubscribeMsg(map, document);
		} else if("unsubscribe".equalsIgnoreCase(event)) {// 取消关注事件
			return parseSubscribeMsg(map, document);
		} else if("CLICK".equalsIgnoreCase(event) || "VIEW".equalsIgnoreCase(event)) {//自定义菜单事件
			return parseClickMsg(map, document);
		} else {//扫描带参数二维码事件
			return parseYixinScanMsg(map, document);
		}
	}

	/** 
	 * 方法名: parseClickMsg</br>
	 * 详述: 解析扫描带参数二维码事件消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseYixinScanMsg(Map<String, Object> map, 
			Document document) throws Exception {
		System.out.println("4444444444444");
		//开发者微信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方帐号（一个OpenID）
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间 （整型）
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		//事件KEY值
		map.put("eventKey", document.getElementsByTagName("EventKey").item(0).getFirstChild().getNodeValue());
		//二维码的ticket，可用来换取二维码图片
		map.put("ticket", document.getElementsByTagName("Ticket").item(0).getFirstChild().getNodeValue());
		return map;
	}

	/** 
	 * 方法名: parseClickMsg</br>
	 * 详述: 解析自定义菜单事件消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseClickMsg(Map<String, Object> map,
			Document document) throws Exception {
		//开发者易信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方帐号（一个OpenID）
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间 （整型）
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		//事件KEY值，与自定义菜单接口中KEY值对应
		map.put("eventKey", document.getElementsByTagName("EventKey").item(0).getFirstChild().getNodeValue());
		return map;
	}

	/** 
	 * 方法名: parseSubscribeMsg</br>
	 * 详述: 解析关注/取消关注事件消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseSubscribeMsg(Map<String, Object> map, 
			Document document) throws Exception {
		System.out.println("222222222222222");
		//开发者微信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方帐号（一个OpenID）
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间 （整型）
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		System.out.println("3333333333333");
		return map;
	}
	
	/** 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Apr 1, 2014</br>
	 * @param map
	 * @param document
	 * @return
	 * @throws Exception
	 */ 
	private Map<String, Object> parseLinkMsg(Map<String, Object> map, 
			Document document) throws Exception {
		//接收方微信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方微信号，若为普通用户，则是一个OpenID
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		//消息标题
		map.put("title", document.getElementsByTagName("Title").item(0).getFirstChild().getNodeValue());
		//消息描述
		map.put("description", document.getElementsByTagName("Description").item(0).getFirstChild().getNodeValue());
		//消息链接
		map.put("url", document.getElementsByTagName("Url").item(0).getFirstChild().getNodeValue());
		//消息id，64位整型
		map.put("msgId", document.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
		return map;
	}

	/** 
	 * 方法名: parseLocationMsg</br>
	 * 详述: 解析地理位置消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseLocationMsg(Map<String, Object> map, 
			Document document) throws Exception {
		//开发者易信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方帐号（一个OpenID）
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间 （整型）
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		//地理位置维度
		map.put("locationX", document.getElementsByTagName("Location_X").item(0).getFirstChild().getNodeValue());
		//地理位置经度
		map.put("locationY", document.getElementsByTagName("Location_Y").item(0).getFirstChild().getNodeValue());
		//地图缩放大小
		map.put("scale", document.getElementsByTagName("Scale").item(0).getFirstChild().getNodeValue());
		//地理位置信息
		map.put("label", document.getElementsByTagName("Label").item(0).getFirstChild().getNodeValue());
		//消息id，64位整型
		map.put("msgId", document.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
		return map;
	}
	
	/** 
	 * 方法名: parseVideoMsg</br>
	 * 详述: 解析视频消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseVideoMsg(Map<String, Object> map, 
			Document document) throws Exception {
		//开发者易信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方帐号（一个OpenID）
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间 （整型）
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		//	语音消息媒体id，可以调用多媒体文件下载接口拉取数据
		map.put("mediaId", document.getElementsByTagName("MediaId").item(0).getFirstChild().getNodeValue());
		//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
		map.put("ThumbMediaId", document.getElementsByTagName("ThumbMediaId").item(0).getFirstChild().getNodeValue());
		//消息id，64位整型
		map.put("msgId", document.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
		return map;
	}
	
	/** 
	 * 方法名: parseAudioMsg</br>
	 * 详述: 解析音频消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseAudioMsg(Map<String, Object> map, 
			Document document) throws Exception {
		//开发者微信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方帐号（一个OpenID）
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间 （整型）
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		//语音格式，如amr，speex等
		map.put("format", document.getElementsByTagName("Format").item(0).getFirstChild().getNodeValue());
		//消息id，64位整型
		map.put("msgId", document.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
		//	 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
		map.put("mediaId", document.getElementsByTagName("MediaId").item(0).getFirstChild().getNodeValue());
		return map;
	}
	
	/** 
	 * 方法名: parseImageMsg</br>
	 * 详述: 解析图片消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseImageMsg(Map<String, Object> map, 
			Document document) throws Exception {
		//开发者易信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方帐号（一个OpenID）
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间 （整型）
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		//图片链接
		map.put("picUrl", document.getElementsByTagName("PicUrl").item(0).getFirstChild().getNodeValue());
		//消息id，64位整型
		map.put("msgId", document.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
		//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
		map.put("mediaId", document.getElementsByTagName("MediaId").item(0).getFirstChild().getNodeValue());
		return map;
	}

	/** 
	 * 方法名: parseTextMsg</br>
	 * 详述: 解析文本消息</br>
	 * 开发人员：陆培波</br>
	 * 创建时间：Mar 7, 2014</br>
	 * @param map：消息map
	 * @param document：DOM对象
	 * @return 消息map
	 * @throws Exception：解析异常时抛出
	 */ 
	private Map<String, Object> parseTextMsg(Map<String, Object> map, 
			Document document) throws Exception {
		//开发者易信号
		map.put("toUserName", document.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
		//发送方帐号（一个OpenID）
		map.put("fromUserName", document.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
		//消息创建时间 （整型）
		map.put("createTime", document.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
		//文本消息内容
		map.put("content", document.getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
		//消息id，64位整型
		map.put("msgId", document.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
		return map;
	}
}
