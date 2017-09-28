package com.cndatacom.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.TargetedMessage;


/**
 * 类名: MiSenderUtils 
 * 包名：com.cndatacom.rbac.utils  
 * 描述: 
 * 发布版本号：v1.0
 * 开发人员： lvyq Justin
 * 创建时间： 2015-6-2
 */
public class MiSenderUtils {
	/**
	 * 在正式环境下使用push服务，启动时需要调用：Constants.useOfficial();
	 * 
	 * 在测试环境下使用push服务，启动时需要调用如下代码：Constants.useSandbox();
	 * 测试环境只提供对IOS支持，不支持Android。
	 */
	
	/**
	 * 	Bundle ID： dancige.com
		
		AppID： 2882303761517343534
		
		AppKey： 5571734364534
		
		AppSecret： V5AQAqGIKfgzdXbeQ7Lvjg==
		用户安卓
	 */
	
	private static final String APP_SECRET_KEY="1tI3ndgC8cZ1TYMdf92rOQ==";// APP_SECRET_KEY = appSecret 这东西(niaowanyi)是在开发者网站上注册时生成的, 可在应用详情下查看。
	private static final String MY_PACKAGE_NAME ="com.xgh.expressuser";//设置app的包名packageName。packageName必须和开发者网站上申请的结果一致
	
	private static final String messagePayload= "亲爱的用户，根据我们强大的搜索定位系统，您已在其他手机移动设备上登录过，请你重新登录~~~";//设置要发送的消息内容payload，不允许全是空白字符，长度小于4K，中英文均以一个计算。
	private static final String title = "系统消息";//设置在通知栏展示的通知的标题，不允许全是空白字符，长度小于16，中英文均以一个计算。	
	private static final String description = "您已在其他设备登录";//设置在通知栏展示的通知的描述，不允许全是空白字符，长度小于128，中英文均以一个计算。
	
	/**
	 * 方法名:  sendMessage
	 * 详述:  根据registrationId，发送消息到指定设备上 。
	 * 开发人员： lvyq Justin 
	 * 创建时间：2015-6-2 
	 * @param regId
	 * @throws Exception
	 */
	public static void sendMessage(String regId,String msg,String titl,String code) throws Exception {
			System.out.println("~~开始推送~~ regId ="+regId);
			if(msg == null){
				msg = messagePayload;
			}
			if(titl==null){
				titl = description;
			}
		//测试环境~~~~~~~~~~Constants.useSandbox();
	     //Constants.useOfficial(); 
			Constants.useSandbox();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     
	     Message message = new Message.Builder()
	                .title(title)
	                .description(titl).payload(msg)//设置要发送的消息内容payload，不允许全是空白字符，长度小于4K，中英文均以一个计算。
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .extra("iCode", code)
	                .build();	//根据前面的属性，生成message对象
	     
	     System.out.println("xiao_mi message ="+message.toString());
	     
	     
	     Result result =  sender.send(message, regId, 0); //根据regID，发送消息到指定设备上，不重试。
	     
	     System.out.println("result ="+result);
	     
	}

	public static void sendMessages() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     List<TargetedMessage> messages = new ArrayList<TargetedMessage>();
	     
	     TargetedMessage targetedMessage1 = new TargetedMessage();
	     targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias1");
	     Message message1 = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     
	     targetedMessage1.setMessage(message1);
	     messages.add(targetedMessage1);
	     
	     TargetedMessage targetedMessage2 = new TargetedMessage();
	     targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias2");
	   
	     Message message2 = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     
	     targetedMessage2.setMessage(message2);
	     messages.add(targetedMessage2);
	     
	     sender.send(messages, 0); //根据alias，发送消息到指定设备上，不重试。
	}
	/**
	 * 方法名:  sendMessageToAlias
	 * 详述: 根据alias，发送消息到指定设备上alias不允许全是空白字符，不能包含逗号，长度小于128，中英文均以一个计算。 
	 * 开发人员： lvyq Justin 
	 * 创建时间：2015-6-2 
	 * @throws Exception
	 */
	public static void sendMessageToAlias() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	   
	     String alias = "testAlias";    //alias非空白，不能包含逗号，长度小于128。
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.sendToAlias(message, alias, 0); //根据alias，发送消息到指定设备上，不重试。
	}
	/**
	 * 
	 * 方法名:  sendMessageToAliases
	 * 详述:  根据aliasList，发送消息到指定的一组设备上。
	 * aliasList中的每个元素不允许全是空白字符，不能包含逗号，长度小于128，中英文均以一个计算；元素的个数不得超过1000个。
	 * 开发人员： lvyq Justin 
	 * 创建时间：2015-6-2 
	 * @throws Exception
	 */
	public static void sendMessageToAliases() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	 
	     List<String> aliasList = new ArrayList<String>();
	     aliasList.add("testAlias1");  //alias非空白，不能包含逗号，长度小于128。
	     aliasList.add("testAlias2");  //alias非空白，不能包含逗号，长度小于128。
	     aliasList.add("testAlias3");  //alias非空白，不能包含逗号，长度小于128。
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.sendToAlias(message, aliasList, 0); //根据aliasList，发送消息到指定设备上，不重试。
	}

	/**
	 * 方法名:  send.Broadcast
	 * 详述:  根据topic，发送消息到指定一组设备上topic不允许全是空白字符，长度小于128，中英文均以一个计算。
	 * 开发人员： lvyq Justin 
	 * 创建时间：2015-6-2 
	 * @throws Exception
	 */
	public static void sendBroadcast() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	    
	     String topic = "testTopic";
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.broadcast(message, topic, 0); //根据topic，发送消息到指定一组设备上，不重试。
	}
	
	public static void sendMessage2(String regId) throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     Result result = sender.send(message, regId, 0); //Result对于sendToAlias()，broadcast()和send()调用方式完全一样
	     System.out.println("result"+result.toString());
	}
}
