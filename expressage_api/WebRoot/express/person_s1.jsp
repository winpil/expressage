<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>常用收件人</title>
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
        		window.location.href="login.jsp";
        	}else{
        		$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/getUserIdByOpenid",
					data : "openId="+openId,
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code!='000000'){
							alert(data.message);
						}else{
							submitBD(data.response.userId);
						}
					},error : function() {
						//alert("错误");
					}
				});
        	}
			
			function submitBD(sId){
        		$.ajax( {
					url : "/expressage_api/rbac/sys/sysBase/testBase",
					data : "baseId=getUserAddress&paramenter1=1&paramenter2="+sId,
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code!='000000'){
							alert(data.message);
						}else{
	        				var htmlStr = "";
	        				for(var i=0;i<data.response.length;i++){
	        					htmlStr+="<div class='person-send' style='padding-left: 1%;' ><div class='person-radio' >";
	        					htmlStr+="<img src='/expressage_api/express/img/ccb.png' onclick='changp3(this)'/></div><div class='person-ntel' onclick=\"commons('"+data.response[i].commonId+"');\">";
	        					htmlStr+="<div class='person-name' style='margin-left: 2%;'><div class='chuizhi-jz4'>"+data.response[i].name+"</div></div>";
	        					htmlStr+="<div class='person-tel'><div class='chuizhi-jz4'>"+data.response[i].phone+"</div></div>";
	        					htmlStr+="<div class='person-senddz'><div class='chuizhi-jz4'>"+data.response[i].provincialName;
	        					htmlStr+=" "+data.response[i].cityName+" "+data.response[i].districtName+" "+data.response[i].address+"</div></div>";
	        					htmlStr+="<div class='person-id' id='"+i+"' style='display: none;'>"+data.response[i].commonId+"</div></div>";
	        					htmlStr+="<a href='person_editor1.html?aId="+data.response[i].commonId+"'><div class='person-editor-icon'></div></a>";
	        					htmlStr+="<div style='clear: both;'></div></div><div id='line'></div>";
	        				}	
	        				$('#commons').html(htmlStr);  
						}
					},error : function() {
						alert("错误");
					}
				});
			}
			
			function G(id){
        	 return document.getElementById(id).innerHTML;
        	}
        	function deleteCourier(){
        		var Odiv = document.getElementsByClassName("person-radio");
        		var Osrc2 = "ccbx.png";	
        		
        		var arr2 = new Array();
        		for(var i=0;i<Odiv.length;i++){
        			var Oimg = Odiv[i].getElementsByTagName("img")[0];
        			arr = Oimg.src.split("/");
        			if(arr[arr.length-1]==Osrc2){ 
        				
        				arr2.push(G(i));
        			}
        			
        		}
        		
        		var comcourierId = arr2;
        		if(comcourierId != null&&comcourierId!=''&&comcourierId!='undefined'){
        			$.ajax( {
						url : "/expressage_api/rbac/sys/expressageUser/deleteCommonByid",
						data : "commonIds="+comcourierId+"&token="+openId+"&type=3",
						type : "post",
						dataType : "json",
						success : function(data) {
							if(data.code=='000000'){
								$.ajax( {
									url : "/expressage_api/rbac/sys/expressageUser/getUserIdByOpenid",
									data : "openId="+openId,
									type : "post",
									dataType : "json",
									success : function(data) {
										if(data.code!='000000'){
											alert(data.message);
										}else{
											submitBD(data.response.userId);
										}
									},error : function() {
										//alert("错误");
									}
								});
							}
						},error : function() {
							alert("错误");
						}
					});
        		}
        	}
			
        	function newAddress(){
				window.location.href="person_editor1.html";        	
        	}
        </script>
    </head>
    <body>
		<div id="commons">
		<div class="person-send" style="padding-left: 1%;" onclick="commons();">
			<div class="person-radio" >
				<img src="/expressage_api/express/img/ccb.png"/>
			</div>
			<div class="person-ntel">
				<div class="person-name" style="margin-left: 2%;"><div class="chuizhi-jz4"></div></div>
				<div class="person-tel"><div class="chuizhi-jz4"></div></div>
				<div class="person-senddz"><div class="chuizhi-jz4"></div></div>
			</div>
			
			<div class="person-editor-icon">
				<img src="/expressage_api/express/img/editor.png"/>
			</div>
			
		</div>
		
		<div id="line"></div>
		</div>
		<div class="send-foot">
			<button id="new-fhzd" value="" onclick="newAddress()" style="background-color: orange;">新增收件地址</button>
			<div class="btn-fenge"></div>
			<button  value="" onclick="deleteCourier()" id="delete">删除发件地址</button>
		</div>
    </body>
</html>