<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>我的地盘</title>
        <link href="css/kuaidi.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/height.css"/>
		<script src="js/zsy.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/jquery.js" ></script>
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <script>
        	
        	//alert("11111111");
        	function getCookie(name)
			{
			    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
			    if(arr=document.cookie.match(reg))
			        return (arr[2]);
			    else
			        return null;
			}
			var openId = getCookie("openId");
			//var openId = "ossxHxDyvgZ4g4N7YI0mXjewjx-k";
        	if(openId == null||openId==''||openId=='undefined'){
        		window.location.href="login.jsp";
        	}else{
        		//alert("2222222");
        		submitBD();
        	}
			
			
			function submitBD(){
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
							$.ajax( {
								url : "/expressage_api/rbac/sys/sysBase/testBase",
								data : "baseId=getUserDetails&paramenter1="+data.response.userId+"&paramenter2="+data.response.userId,
								type : "post",
								dataType : "json",
								success : function(data1) {
									if(data1.code!='000000'){
										var exp = new Date();
						    			exp.setTime(exp.getTime() - 1);
										document.cookie= "openId" + "="+openId+";expires="+exp.toGMTString();
										window.location.href="login.jsp";
									}else{
										var htmlStr = "";
				        				$('#person-name').html(data1.response.userName);  
				        				
				        				$('#person-tel').html("账号:"+data1.response.phone);
				        				$('#person-tel1').html(data1.response.phone);
				        				$('#yhq').html(data1.response.couponNum);
				        				if(data1.response.avatar!=null&&data1.response.avatar!=""){
				        					$('#person-toux').html("<img src='/expressage_api"+data1.response.avatar+"' />");
				        				}
									}
								},error : function() {
									//alert("错误");
								}
							});
						}
					},error : function() {
						//alert("错误");
					}
				});
			}
        	
        	function lskj(){
        		var phone = $("#person-tel1").text();
        		//window.location.href="/expressage_api/rbac/sys/expressageOrder/saveOrderByweixin?courierId="+courierId+"&type=2&openId="+openId;
        		window.location.href="historyfsj.html?phone="+phone;
        	}
        	
        	var longitude;
			var latitude;
			function showPosition(position){
			 	longitude = position.coords.longitude;
			    latitude = position.coords.latitude;
			    window.location.href="cykdy.html?latitude="+latitude+"&longitude="+longitude;
			}
        	function cykdy(){
        		if (navigator.geolocation){
			    	navigator.geolocation.getCurrentPosition(showPosition);
			    }
        		//window.location.href="/expressage_api/rbac/sys/expressageOrder/saveOrderByweixin?courierId="+courierId+"&type=2&openId="+openId;
        	}
        	
        	function cydz(){
        		window.location.href="person_dizhi.html";
        	}
        	function yhq(){
        		window.location.href="youhuiquan1.jsp";
        	}
        	function problem(){
        		window.location.href="problem.html";
        	}
        </script>
    </head>
    <body>
		<div class="perosn-header">
			<div id="person-toux">
			</div>
			<div id="person-name"></div>
			<div id="person-tel"></div>
			<div id="person-tel1" style="display: none;"></div>
		</div>
		<div class="person-content">
		<div class="person-data" onclick="lskj();">
			
			<div class="person-left">
			<img src="img/historykj.png" />
			</div>
			<div class="person-center">历史快件</div>
			<div class="person-right">
				<img src="img/arrow-right.png"/>
			</div>
		</div>	
			
			<div class="person-data" onclick="yhq();">
			<div class="person-left">
			<img src="img/yhq.png" /></div>			
			<div class="person-center">我的优惠劵</div>
			<div class="person-right" id="yhq"></div>
			
			</div>
			<div class="person-data" onclick="cydz();">
			<div class="person-left">
			<img src="img/dizhi.png" /></div>			
			<div class="person-center">常用地址</div>
			<div class="person-right"><img src="img/arrow-right.png"/></div>
			</div>
			<div class="person-data" onclick="cykdy();">
			<div class="person-left">
			<img src="img/kdy.png" /></div>			
			<div class="person-center">常用快递员</div>
			<div class="person-right"><img src="img/arrow-right.png"/></div>
			</div>
			<div class="person-data" onclick="problem();">
			<div class="person-left">
			<img src="img/problem.png" /></div>			
			<div class="person-center">常见问题</div>
			<div class="person-right"><img src="img/arrow-right.png"/></div>
			</div>
		
		</div>
    </body>
</html>