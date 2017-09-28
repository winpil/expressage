package com.cndatacom.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.TargetedMessage;


/**
 * ����: MiSenderUtils 
 * ������com.cndatacom.rbac.utils  
 * ����: 
 * �����汾�ţ�v1.0
 * ������Ա�� lvyq Justin
 * ����ʱ�䣺 2015-6-2
 */
public class MiSenderUtils {
	/**
	 * ����ʽ������ʹ��push��������ʱ��Ҫ���ã�Constants.useOfficial();
	 * 
	 * �ڲ��Ի�����ʹ��push��������ʱ��Ҫ�������´��룺Constants.useSandbox();
	 * ���Ի���ֻ�ṩ��IOS֧�֣���֧��Android��
	 */
	
	/**
	 * 	Bundle ID�� dancige.com
		
		AppID�� 2882303761517343534
		
		AppKey�� 5571734364534
		
		AppSecret�� V5AQAqGIKfgzdXbeQ7Lvjg==
		�û���׿
	 */
	
	private static final String APP_SECRET_KEY="1tI3ndgC8cZ1TYMdf92rOQ==";// APP_SECRET_KEY = appSecret �ⶫ��(niaowanyi)���ڿ�������վ��ע��ʱ���ɵ�, ����Ӧ�������²鿴��
	private static final String MY_PACKAGE_NAME ="com.xgh.expressuser";//����app�İ���packageName��packageName����Ϳ�������վ������Ľ��һ��
	
	private static final String messagePayload= "�װ����û�����������ǿ���������λϵͳ�������������ֻ��ƶ��豸�ϵ�¼�����������µ�¼~~~";//����Ҫ���͵���Ϣ����payload��������ȫ�ǿհ��ַ�������С��4K����Ӣ�ľ���һ�����㡣
	private static final String title = "ϵͳ��Ϣ";//������֪ͨ��չʾ��֪ͨ�ı��⣬������ȫ�ǿհ��ַ�������С��16����Ӣ�ľ���һ�����㡣	
	private static final String description = "�����������豸��¼";//������֪ͨ��չʾ��֪ͨ��������������ȫ�ǿհ��ַ�������С��128����Ӣ�ľ���һ�����㡣
	
	/**
	 * ������:  sendMessage
	 * ����:  ����registrationId��������Ϣ��ָ���豸�� ��
	 * ������Ա�� lvyq Justin 
	 * ����ʱ�䣺2015-6-2 
	 * @param regId
	 * @throws Exception
	 */
	public static void sendMessage(String regId,String msg,String titl,String code) throws Exception {
			System.out.println("~~��ʼ����~~ regId ="+regId);
			if(msg == null){
				msg = messagePayload;
			}
			if(titl==null){
				titl = description;
			}
		//���Ի���~~~~~~~~~~Constants.useSandbox();
	     //Constants.useOfficial(); 
			Constants.useSandbox();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     
	     Message message = new Message.Builder()
	                .title(title)
	                .description(titl).payload(msg)//����Ҫ���͵���Ϣ����payload��������ȫ�ǿհ��ַ�������С��4K����Ӣ�ľ���һ�����㡣
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // ʹ��Ĭ����ʾ����ʾ
	                .extra("iCode", code)
	                .build();	//����ǰ������ԣ�����message����
	     
	     System.out.println("xiao_mi message ="+message.toString());
	     
	     
	     Result result =  sender.send(message, regId, 0); //����regID��������Ϣ��ָ���豸�ϣ������ԡ�
	     
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
	                .notifyType(1)     // ʹ��Ĭ����ʾ����ʾ
	                .build();
	     
	     targetedMessage1.setMessage(message1);
	     messages.add(targetedMessage1);
	     
	     TargetedMessage targetedMessage2 = new TargetedMessage();
	     targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias2");
	   
	     Message message2 = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // ʹ��Ĭ����ʾ����ʾ
	                .build();
	     
	     targetedMessage2.setMessage(message2);
	     messages.add(targetedMessage2);
	     
	     sender.send(messages, 0); //����alias��������Ϣ��ָ���豸�ϣ������ԡ�
	}
	/**
	 * ������:  sendMessageToAlias
	 * ����: ����alias��������Ϣ��ָ���豸��alias������ȫ�ǿհ��ַ������ܰ������ţ�����С��128����Ӣ�ľ���һ�����㡣 
	 * ������Ա�� lvyq Justin 
	 * ����ʱ�䣺2015-6-2 
	 * @throws Exception
	 */
	public static void sendMessageToAlias() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	   
	     String alias = "testAlias";    //alias�ǿհף����ܰ������ţ�����С��128��
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // ʹ��Ĭ����ʾ����ʾ
	                .build();
	     sender.sendToAlias(message, alias, 0); //����alias��������Ϣ��ָ���豸�ϣ������ԡ�
	}
	/**
	 * 
	 * ������:  sendMessageToAliases
	 * ����:  ����aliasList��������Ϣ��ָ����һ���豸�ϡ�
	 * aliasList�е�ÿ��Ԫ�ز�����ȫ�ǿհ��ַ������ܰ������ţ�����С��128����Ӣ�ľ���һ�����㣻Ԫ�صĸ������ó���1000����
	 * ������Ա�� lvyq Justin 
	 * ����ʱ�䣺2015-6-2 
	 * @throws Exception
	 */
	public static void sendMessageToAliases() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	 
	     List<String> aliasList = new ArrayList<String>();
	     aliasList.add("testAlias1");  //alias�ǿհף����ܰ������ţ�����С��128��
	     aliasList.add("testAlias2");  //alias�ǿհף����ܰ������ţ�����С��128��
	     aliasList.add("testAlias3");  //alias�ǿհף����ܰ������ţ�����С��128��
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // ʹ��Ĭ����ʾ����ʾ
	                .build();
	     sender.sendToAlias(message, aliasList, 0); //����aliasList��������Ϣ��ָ���豸�ϣ������ԡ�
	}

	/**
	 * ������:  send.Broadcast
	 * ����:  ����topic��������Ϣ��ָ��һ���豸��topic������ȫ�ǿհ��ַ�������С��128����Ӣ�ľ���һ�����㡣
	 * ������Ա�� lvyq Justin 
	 * ����ʱ�䣺2015-6-2 
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
	                .notifyType(1)     // ʹ��Ĭ����ʾ����ʾ
	                .build();
	     sender.broadcast(message, topic, 0); //����topic��������Ϣ��ָ��һ���豸�ϣ������ԡ�
	}
	
	public static void sendMessage2(String regId) throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // ʹ��Ĭ����ʾ����ʾ
	                .build();
	     Result result = sender.send(message, regId, 0); //Result����sendToAlias()��broadcast()��send()���÷�ʽ��ȫһ��
	     System.out.println("result"+result.toString());
	}
}
