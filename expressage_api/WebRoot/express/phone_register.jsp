<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>注册</title>
        <link href="css/kuaidi.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/height.css"/>
		<script src="js/zsy.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/jquery.js" ></script>
         <script type="text/javascript" src="js/md5.js"></script>
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <script>
        	function getCookie(name)
			{
			    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
			    if(arr=document.cookie.match(reg))
			        return (arr[2]);
			    else
			        return null;
			}
			var openId = getCookie("openId");
			//var openId = "${openId}";
			//alert("openId==="+openId);
			//var openId = "ossxHxDyvgZ4g4N7YI0mXjewjx-k";
			
			if(openId == null||openId==''||openId=='undefined'){
	        	if(confirm("需要获取您的公开信息（openid、地理位置信息）")){
		          	bindingWeiXin();
			    }else{
			        WeixinJSBridge.call('closeWindow');
			        window.opener=null;
					window.open('','_self');
					window.close();
			    }
	        }
			
			function bindingWeiXin(){
				window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe8a02469a0ebe29a&redirect_uri=http://www.frdzw.com/expressage_api/express/bdSkip1.jsp&response_type=code&scope=snsapi_base&state=1#wechat_redirect"
			}
			
        	function sms(){
        		var phone=$("input[name='phone']").val();
        		if(null==phone||""==phone){
					alert("请输入手机账号");
  					return false;
				}
        		$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/smsCode",
					data : "type=1&phone="+phone+"&status=1",
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code!='000000'){
							alert(data.message);
						}else{
							alert("短信下发成功");
						}
					},error : function() {
						alert("错误");
					}
				});
			}
			
			function submitZC(){
				var phone=$("input[name='phone']").val();
				var password=$("input[name='password']").val();
				var password1=$("input[name='password1']").val();
				var code=$("input[name='code']").val();
				var referrerPhone=$("input[name='referrerPhone']").val();
				hash = hex_md5(password);
				if(null==phone||""==phone){
					alert("请输入手机账号");
  					return false;
				}
				if(null==password||""==password){
					alert("请输入密码");
  					return false;
				}
				if(null==code||""==code){
					alert("请输入验证码");
  					return false;
				}
				if(password1!=password){
					alert("2次密码输入不相同");
  					return false;
				}
        		$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/saveUser",
					data : "token="+openId+"&phone="+phone+"&password="+hash+"&type=3&code="+code+"&referrerPhone="+referrerPhone,
					type : "post",
					dataType : "json",
					success : function(data) {
						//var obj = eval("("+data+")");
						if(data.code=='000000'){
							window.location.href="bangdingsuccess.html";
						}else{
							alert(data.message);
						}
					},error : function() {
						alert("错误");
					}
				});
			}
			
			function skipLogin(){
        		window.location.href="login.jsp";
			}
        	
        </script>
    </head>
    <body>
		<form action="#" method="post">
		
		<div class="login-zh" style="margin-bottom: 1%;">
		<div class="text-zh">
		<div class="userp-icon"><img src="img/phone.png" /></div>		
		<input type="text"  placeholder="请输入您的手机号码" name="phone" />
		</div>
		</div>
		
		<div class="login-zh">
		 <div class="yzm-zh">
		 <div id="yzm-icon"><img src="img/bankcard.png" /></div>	
		 <input type="text" placeholder="请输入验证码"  name="code"/>
		 <div id="send-yzm"><div class="chuizhi-jz" onclick="sms();">发送验证码</div></div>
		</div>	
		</div>
		<div class="login-zh" style="margin-top: -1%;">
		<div class="text-zh" ><div class="userp-icon"><img src="img/password.png" /></div>	
		<input type="password" placeholder="请输入密码" name="password"/></div>
		</div>
		<div class="login-zh" style="margin-top: 2%;">
		<div class="text-zh" ><div class="userp-icon"><img src="img/password.png" /></div>	
		<input type="password" placeholder="请再次输入密码"  name="password1"/></div>
		</div>
		<div class="login-zh">
		<div class="tel-zh">
			<input type="tel" name="referrerPhone"  placeholder="如果是朋友推荐，请输入推荐人手机号(选填)" />
		</div>
		</div>
       
		
		
		<div class="foot">
			<div class="btn-fenge"></div>
			<button type="button" class="btn" value="" onclick="submitZC();">注册</button>
			<div class="btn-fenge"></div>
			<button type="button" class="btn" value=""  onclick="skipLogin();">已是会员，去登录</button>
		</div>
			</form>
    </body>
</html>