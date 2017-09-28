<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>附近快递员</title>
        <link href="css/kuaidi.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/height.css"/>
		<script src="js/kuaidi.js" type="text/javascript" charset="utf-8"></script>
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
			//alert("openId1==="+openId);
			//var openId = "ossxHxDyvgZ4g4N7YI0mXjewjx-k";
        	if(openId == null||openId==''||openId=='undefined'){
        		//var Days = 30;
				//var exp = new Date();
				//exp.setTime(exp.getTime() + Days*24*60*60*1000);
				//document.cookie = "openId" + "="+ escape("ossxHxDyvgZ4g4N7YI0mXjewjx-k") + ";expires=" + exp.toGMTString();
        		//alert("1111==="+openId);
        		window.location.href="login.jsp";
        	}else{
        		$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/getUserIdByOpenid",
					data : "openId="+openId,
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code!='000000'){
							var exp = new Date();
						    exp.setTime(exp.getTime() - 1);
							document.cookie= "openId" + "="+openId+";expires="+exp.toGMTString();
							window.location.href="login.jsp";
						}else{
							if (navigator.geolocation){
						    	navigator.geolocation.getCurrentPosition(showPosition);
						    }
			        		showPosition();
						}
					},error : function() {
						//alert("错误");
					}
				});
        	}
			//alert("222222222333");
			var longitude;
			var latitude;
			function showPosition(position){
			 	longitude = position.coords.longitude;
			    latitude = position.coords.latitude;
			    //longitude = "113.38539307504628";
			    //latitude = "23.130460219606043";
			    submitBD();
			}
			function submitBD(){
        		$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/getCouriersByNearby",
					data : "latitude="+latitude+"&longitude="+longitude+"&token="+openId+"&type=3",
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code!='000000'){
							alert(data.message);
							$("#couriers").html("");
						}else{
							var htmlStr = "";
	        				for(var i=0;i<data.response.length;i++){
	        					var f = parseFloat(data.response[i].juli);
	        					if(f==''){
	        						f=0;
	        					}
	        					f = Math.round(f*100)/100;
	        					
	        					htmlStr+="<div class='fujin' onclick=\"getCourier('"+data.response[i].courierId+"');\" ><div class='fujin-data-center'><div class='fujin-name'><div class='chuizhi-jz4'>"+data.response[i].courierName+"<span>("+data.response[i].companyName+")</span></div></div>";
	        					htmlStr+="<div class='fujin-tel'><div class='chuizhi-jz4'>"+data.response[i].phone+"</div></div></div><div class='fujin-data-right'>";
	        					htmlStr+="<div class='funjin-julit'>距离您"+f+"km左右</div><div class='ck'><img src='img/tel.png'/></div></div></div><div id='line'></div>";
	        				}	
	        				$('#couriers').html(htmlStr);  
						}
					},error : function() {
						//alert("错误");
					}
				});
			}
        	
        	function getCourier(courierId){
        		//window.location.href="/expressage_api/rbac/sys/expressageOrder/saveOrderByweixin?courierId="+courierId+"&type=2&openId="+openId;
        		window.location.href="kdydata.html?courierId="+courierId+"&latitude="+latitude+"&longitude="+longitude;
        	}
        	
        	function cykdy(){
        		//window.location.href="/expressage_api/rbac/sys/expressageOrder/saveOrderByweixin?courierId="+courierId+"&type=2&openId="+openId;
        		window.location.href="cykdy.html?latitude="+latitude+"&longitude="+longitude;
        	}
        	
        </script>
    </head>
    <body>
		<div class="fujin-head">附近快递员</div>
		<div id="couriers">
		</div>
		<div class="jiazai">
			</div>
			<div class="cykdy-btn">
				<button type="button" onclick="cykdy();">常用快递员</button>
			</div>
    </body>
    
</html>