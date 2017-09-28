package com.cndatacom.rbac.system.web.action;

import com.cndatacom.common.weixin.util.HttpUtils;


public class WeixinInterTest {
	
	private static String CX_IP = "14.146.224.103";
	
	//private static String IP = "14.29.1.105";
	private static String IP = "localhost:8080";

	public static void main(String[] args) {
		//创建微信菜单接口
		HttpUtils.getRequest("http://" + IP +"/expressage_api/rbac/sys/weixinCreatedMenu!execute1.action");
		//删除菜单接口
		//HttpUtils.getRequest("http://" + IP +"/plusYixin/yideliWeixinCreatedMenu!getMenu.action");
		//生成推广二维码接口
//		String url = HttpUtils.getRequest("http://14.31.15.27/plusYixin/plusYixinQrCode!execute.action?sceneId=" + 1);
//		System.out.println("推广二维码地址：" + url);
		//验证码短信下发测试
//		String sendMobile = ThreeDes.getDes("yixinPlus999" + "#" + "15322360261");
//		HttpUtils.getRequest("http://14.31.15.27/plusYixin/codeSender!execute.action?send_mobile=" + sendMobile);
	}

}