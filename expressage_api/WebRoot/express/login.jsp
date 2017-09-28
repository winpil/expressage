<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>绑定微信账号</title>
        <link href="css/kuaidi.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/height.css"/>
		<script src="js/kuaidi.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/zsy.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/jquery.js" ></script>
         <script type="text/javascript" src="js/md5.js"></script>
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <script>
        	//alert("444444444444");
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
			//alert("openId登录==="+openId);
			//var openId = "ossxHxDyvgZ4g4N7YI0mXjewjx-k";
        	if(openId == null||openId==''||openId=='undefined'){
	        	zc();
	        }
			
        	function bindingWeiXin(){
				window.location.href="phone_register.html";
			}
			
			function zc(){
				window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe8a02469a0ebe29a&redirect_uri=http://www.frdzw.com/expressage_api/express/bdSkip.jsp&response_type=code&scope=snsapi_base&state=1#wechat_redirect"
			}
			function submitBD(){
				//alert("11111111111");
				var phone=$("input[name='phone']").val();
				var password=$("input[name='password']").val();
				//alert(phone);
				//alert(password);
				hash = hex_md5(password);
				if(null==phone||""==phone){
					alert("请输入手机账号");
  					return false;
				}
				if(null==password||""==password){
					alert("请输入密码");
  					return false;
				}
				
        		$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/bindingWeiXin",
					data : "openId="+openId+"&phone="+phone+"&password="+hash,
					type : "post",
					dataType : "json",
					success : function(data) {
						//var obj = eval("("+data+")");
						if(data.code=='000000'){
							window.location.href="bangdingsuccess.html?userName="+phone;
						}else{
							alert(data.message);
						}
					},error : function() {
						alert("错误");
					}
				});
			}
        	
        </script>
    </head>
    <body>
		<div data-role = "content" class="content">
		<div class="login-zh">
		<div class="text-zh"><div class="userp-icon"><img src="img/name.png" /></div>		
		<input type="text"  placeholder="请输入您的登录名" name="phone" /></div>
		<div class="text-zh" style="margin-top: 0.08rem;"><div class="userp-icon"><img src="img/password.png" /></div>	
		<input type="password" placeholder="请输入密码" name="password" /></div>
		</div>
		<div class="wjmm">
		<p><a href="resetpassword.html">忘记密码 ?</a></p></div>
		<div class="foot">
			<button id="bd-btn" value="" onclick="submitBD();" style="background-color: orange;">绑定马上收会员</button>
			<div class="btn-fenge"></div>
			<button type="button" id="zc-btn" value="" onclick="bindingWeiXin();" >不是会员，去注册</button>
		</div>
		</div>
    </body>
</html>