<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>微信解绑</title>
        <link href="css/kuaidi.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<script src="js/zsy.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/jquery.js" ></script>
        <script type="text/javascript" src="js/md5.js"></script>
        <script>
        	var openId = getCookie("openId");
        	//var openId = "ossxHxDyvgZ4g4N7YI0mXjewjx-k";
        	if(confirm("确定要解除微信绑定吗？")){
			    $.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/logout",
					data : "token="+openId+"&type=3",
					type : "post",
					dataType : "json",
					success : function(data) {
						//var obj = eval("("+data+")");
						if(data.code=='000000'){
							
							//alert("openIdddd==="+openId);
							var exp = new Date();
						    exp.setTime(exp.getTime() - 1);
						    if(openId!=null){
						    	document.cookie= "openId" + "="+openId+";expires="+exp.toGMTString();
						    }
						    WeixinJSBridge.call('closeWindow');
						}else{
							alert(data.message);
						}
					},error : function() {
						alert("错误");
					}
				});
	            
			}else{
			    WeixinJSBridge.call('closeWindow');
			}
        	function getCookie(name)
			{
			    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
			    if(arr=document.cookie.match(reg))
			        return (arr[2]);
			    else
			        return null;
			}
        	
        	
        </script>
    </head>
    <body>
    </body>
</html>