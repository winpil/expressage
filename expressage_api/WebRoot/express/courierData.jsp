<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>快递员详情</title>
        <link href="css/kuaidi.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<script src="js/zsy.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/jquery.js" ></script>
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <script>
			
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
			var courierId;
			courierId = Request["courierId"];
			submitBD(courierId);
			
			function submitBD(courierId){
        		$.ajax( {
					url : "/expressage_api/rbac/sys/sysBase/testBase",
					data : "baseId=getCourierDetails&paramenter1="+courierId,
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code!='000000'){
							alert(data.message);
						}else{
	        				$('#courier1').text(data.response.companyName);  
	        				$('#courier2').text(data.response.courierName); 
	        				$('#courier3').text(data.response.phone); 
	        				$('#courier4').text(data.response.courierName); 
						}
					},error : function() {
						alert("错误");
					}
				});
			}
        	
        </script>
    </head>
    <body>
		<div data-role = "page" id="page">
					
			<div class="suoshu-gs">
				<div class="suoshu-left"><img src="img/3.png"/></div>
				<div class="suoshu-center"><p>所属快递公司</p></div>
				<div class="suoshu-right">韵达快递</div>
			</div>
			<div id="line">
				
			</div>
			<div class="kdy-xinxi">
				<div class="xinxi-left"><img src="img/4.png"/></div>
				<div class="xinxi-center"><p>姓名</p></div>
				<div class="xinxi-right"><p>杨佳峰</p></div>
			</div>
			<div id="line">
				
			</div>
			<div class="kdy-xinxi">
				<div class="xinxi-left"><img src="img/5.png"/></div>
				<div class="xinxi-center"><p style="margin-top: 32%;">手机号码</p></div>
				<div class="xinxi-right"><p>213216541</p></div>
			</div>
			<div id="line">
				
			</div>
			<div class="kdy-xinxi">
				<div class="xinxi-left2"><img src="img/dizhi.png"/></div>
				<div class="xinxi-center"><p style="margin-top: 32%;">派送范围</p></div>
				<div class="xinxi-center2">海天楼，秦天大厦，棠下村</div>
			</div>
			<div id="line">
				
			</div>
			<div class="kdy-xinxi">
				<div class="xinxi-left"><img src="img/6.png" style="margin-top: -10%;"/></div>
				<div class="xinxi-center"><p style="margin-top: 32%;">评价等级</p></div>
				<div class="xinxi-right">
					<img src="img/pjx.png"/>
					<img src="img/pjx.png"/>
					<img src="img/pjx.png"/>
					<img src="img/pjx.png"/>
				</div>
			</div>
		
		<div class="kdy-fter" >
			<div class="kdy-foot" style="float: left;">
			<div class="kdy-sz">
			<img src="img/8.png"/>
				<span>下单</span>
			</div>
			</div>	
			<div class="line5"></div>
			<div class="kdy-foot" style="float: right;">
			<div class="kdy-sz">
				<img src="img/7.png"/>
				<span>设置为常用快递员</span>
			</div>
			</div>
		
		</div>
		</div>
    </body>
</html>