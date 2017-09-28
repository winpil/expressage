<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>绑定微信账号</title>
        <link href="css/kuaidi.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<script src="js/zsy.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/jquery.js" ></script>
        <script type="text/javascript" src="js/md5.js"></script>
        <script>
        	//alert("1111111112222222222");
        	function getOpenId(code){
        		$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/getOpenId",
					data : "code="+code,
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code=='000000'){
							//alert("openId==="+data.response.openid);
							var Days = 30;
						    var exp = new Date();
						    exp.setTime(exp.getTime() + Days*24*60*60*1000);
						    document.cookie = "openId" + "="+ escape(data.response.openid) + ";expires=" + exp.toGMTString();
							window.location.href="login.jsp";
						}
					},error : function() {	
						alert("错误");
					}
				});
			}
        	
        	function GetRequest() {
			  var url = location.search; //获取url中"?"符后的字串
			   var theRequest = new Object();
			   if (url.indexOf("?") != -1) {
			      var str = url.substr(1);
			      strs = str.split("&");
			      for(var i = 0; i < strs.length; i ++) {
			         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
			      }
			   }
			   return theRequest;
			}
			var Request = new Object();
			Request = GetRequest();
			var code;
			code = Request["code"];
			//alert("code=="+code);
			//hash = hex_md5(password);
			//alert(hash+"==hash");
			getOpenId(code);
        </script>
    </head>
    <body>
    </body>
</html>