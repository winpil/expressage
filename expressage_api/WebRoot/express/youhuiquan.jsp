<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>优惠券</title>
        <link href="/expressage_api/express/css/kuaidi.css" rel="stylesheet" />
		<link href="/expressage_api/express/css/header.css" rel="stylesheet" />
		<script src="/expressage_api/express/js/zsy.js" type="text/javascript" charset="utf-8"></script>
        <script src="/expressage_api/express/js/jquery.js" ></script>
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
        		openId = "${weixinOrder.oId}";
        		if(openId == null||openId==''||openId=='undefined'){
        			window.location.href="login.jsp";
        		}
        	}
			
			var sId = "${userId}";
			
			submitBD(sId);
			function submitBD(sId){
        		$.ajax( {
					url : "/expressage_api/rbac/sys/sysBase/testBase",
					data : "baseId=getFavorablesByUserId&paramenter1="+sId,
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code!='000000'){
							alert(data.message);
						}else{
	        				var htmlStr = "";
	        				for(var i=0;i<data.response.length;i++){
	        					htmlStr+="<div class='yhq' onclick=\"commons('"+data.response[i].favorableId+"');\"><div class='yhq-qian' id='yhq-qian'>"+data.response[i].favorableName+"</div>";
	        					htmlStr+="<div class='yhq-level'>"+data.response[i].favorableName+"元优惠券</div><div id='yhq-shu'>"+data.response[i].num+"张</div>";
	        					htmlStr+="</div><div id='line'></div>";
	        				}	
	        				$('#yhgs').html(htmlStr);  
						}
					},error : function() {
						alert("错误");
					}
				});
			}
			function commons(favorableId){
				status = "${sta}";
				if(status=='2'){
					window.location.href="/expressage_api/rbac/sys/expressageOrder/saveOrderByweixin?type=6&favorableId="+favorableId+"&openId="+openId;
				}else{
					window.location.href="/expressage_api/rbac/sys/expressageOrder/saveOrderByweixin?type=1&favorableId="+favorableId+"&openId="+openId;
				}
				
			}
        	
        </script>
    </head>
    <body>
		<div id="yhgs">
			<div class="yhq">
				
				<div class="yhq-qian" id="yhq-qian"></div>
				
				<div class="yhq-level"></div>
				<div id="yhq-shu"></div>
				
			</div>	
		<div id="line"></div>
			<div class="yhq" onclick="yhq(this)" id="2">
				
				<div class="yhq-qian"></div>
				
				<div class="yhq-level"></div>
				<div id="yhq-shu"></div>
				
			</div>	
		</div>
    </body>
</html>