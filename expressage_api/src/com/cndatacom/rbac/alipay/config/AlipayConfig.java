package com.cndatacom.rbac.alipay.config;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088212082200308";
	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALen/TWWbTYyK+qOHxnNXCwclG/pnUWhiAwQW7e847bxm7dBADARS0yMC/AtqLSg44yNq2mSqLcud7aEFL+bXTxfohhzkHuZa1Ji8+KgqYsIU7rfOTip4FcO5eOs1RxHAqHppmrow/bwWlSp/ucBafIlG6RcowsFfoK40HGjrrhPAgMBAAECgYBdzWZttVwofy2hCl/mLNZ0DVDfbCGQy/URvFUsHkTsTrDqwz9WTa749NqwHJH7V+WBB2bScD7N+OTaCtISH0dZzZ4crMhYJ9xifH6FWflZdZqkP4GMmuL3cJc5vcn4oM06U3rOh7XIUpgx2VePROX/5dWYWiRHbW6kURChM62zEQJBAOmYllWbUb6oWCOtbTHZqablWo/2sCvtig+FL/dtXgh6qmKygXvmzfq8FFA4RMaSaRjsjwuoS7oxl0MHp1rd5FkCQQDJRT6EfmA80c/ZC4JsRnNzAWDxeX2TNJQN5WghpCyRWCK3p2jEdBuA+an66kqcgwzI7oKZl4yFhhkJWCoSnYznAkAZdccU54veUuzgXpujLk0eYqsfO8zxzL8ad/LFbgcst2nwvfH89igHJw6zu55LYlPQIqBordwrHBKXfLMR5VNpAkBvrjGDeVBTTySizcUf6XO0M+aj9wLcBMfGci32xM9KEvqop4b1w2tjhKVyKkEeWYVllRr4uOPrTz5jNf2aeLuvAkEAv1ytdRISIm69WYjTgB8ahzRrAdcA2R+xO1L8CXGG50i+GAfNn0Fox0qE3OY9DJECp4GGQ3Mqxh413/Snr/cBpw==";
//	public static String private_key1 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOIgdOJ22TW8zk8lR6XbYJPPjEn0xu0qdHO0wjovPDw0rwTBhax2wnpJku+BSyBTIolNlWntnxhJQURlF7yeKmE99Za4Dgf1sx0LpB1m8NmLPp1pQAiD21kSijW+/d88P8HD/jlffL3Psu6yzLZxoKTfty1jh5+DPUMTzcE3r0GjAgMBAAECgYEAp2JfI0PWhXC9RtjRsHQziTaZIiWPGv/SM9QZ8fgFHovhAiQ+TuuaCetsWN3cPpFLf1duwnqiyddv+LDcYUKzGuPtWui3gT6Ummjlj9mey8+VjfK+iUb5PBPS/oQuxozR2ME0U6dqZqKbZJkJDPnxILO0Y55FXnl+5TnUTc4N+AECQQD3N9eHE/ozzjQpqh2JNGVBs1lLXscyc3X74IFN6A0S23TpWGk8p0hqPbQb5SqY7cypvlXZwCzu0SmfgDzd7HRJAkEA6ijQTJLTC2d6y6+8qXq8p5hIIC2acypnaP1dhckZcjaCHXMLF7LvlhSZjYt7np5wq7qTjkqBG7KF54DP0i8uiwJAX73zrW+SNaAFWaF1YN623FP+IA9rq+FFby/KRAa7kXMGexxvXTsX83VEHI2LCsEFwzNggj1H6ZF9wjTi3bumaQJAKshGNpdk4+KGNwzmxXhdW6mhE/ABFiWbOz1/huAVPB7lawgH8M6Ko7sjyw6/CV4da+Ls7sSCDlNxz7hjQB3KvQJBAMsRs6Vifh8wKOZdiPxtS0uqifYVVkpDOnJyGMc56DohwP2j5cPSGMxHjb/IWsI2aS7lZhghGJtlVsG1iaC2bZ0=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3p/01lm02Mivqjh8ZzVwsHJRv6Z1FoYgMEFu3vOO28Zu3QQAwEUtMjAvwLai0oOOMjatpkqi3Lne2hBS/m108X6IYc5B7mWtSYvPioKmLCFO63zk4qeBXDuXjrNUcRwKh6aZq6MP28FpUqf7nAWnyJRukXKMLBX6CuNBxo664TwIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
//	public static String log_path = FileUtil.getWebContent() + File.separator 
//			+ ElementConst.File_Target_Dir + File.separator + ElementConst.Ext_File;

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
