<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>马上下单</title>
        <link href="/expressage_api/express/css/kuaidi.css" rel="stylesheet" />
		<link href="/expressage_api/express/css/header.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="/expressage_api/express/css/tanchukuang.css"/>
		<script src="/expressage_api/express/js/jquery.js" ></script>
		<script src="/expressage_api/express/js/tanchukuang.js" type="text/javascript" charset="utf-8"></script>
		<script src="/expressage_api/express/js/zsy.js" type="text/javascript" charset="utf-8"></script>
        <script src="/expressage_api/express/js/time.js" type="text/javascript" charset="utf-8"></script>
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
        	
			function jMessage(){
				sbmWeixinOrder(3);
			}
			function sMessage(){
				sbmWeixinOrder(4);
			}
			function sbmWeixinOrder(type){
				var kdJid = $("#kdJid").text();
				var kdSid = $("#kdSid").text();
				var kdPackType = $("#kd-leibie").text();
				var kdPackWeight = $("input[name='packWeight']").val();
				var kdGatherDate = $("#Otime").text();
				var kdYid = $("#kdYid").text();
				var reservePrice = $("input[name='reservePrice']").val();
				if(null==kdPackType||""==kdPackType){
					kdPackType = "1";
				}
				if(null==kdGatherDate||""==kdGatherDate){
					kdGatherDate = "1";
				}
				//alert("kdJid=="+kdJid);
				//alert("kdSid=="+kdSid);
				//alert("kdPackType=="+kdPackType);
				//alert("kdPackWeight=="+kdPackWeight);
				//alert("kdGatherDate=="+kdGatherDate);
				window.location.href="/expressage_api/rbac/sys/expressageOrder/saveOrderByweixin?openId="+openId+"&sId="+kdSid+"&jId="+kdJid+"&packType="+kdPackType+"&packWeight="+kdPackWeight+"&gatherDate="+kdGatherDate+"&favorableId="+kdYid+"&type="+type+"&sta=2&reservePrice="+reservePrice;
			}
			function xd(){
			
				var kdPackType = $("#kd-leibie").text();
				var kdPackWeight = $("input[name='packWeight']").val();
				var kdGatherDate = $("#Otime").text();
				var reservePrice = $("input[name='reservePrice']").val();
				if(null==kdPackType||""==kdPackType){
					kdPackType = "1";
				}
				if(null==kdGatherDate||""==kdGatherDate){
					kdGatherDate = "1";
				}
				if(null==kdPackWeight||""==kdPackWeight){
					alert("请填写大概重量");
					return false;
				}
				if(null==reservePrice||""==reservePrice){
					alert("请填写预付价格");
					return false;
				}
				
				$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/createOrderWeixin",
					data : "openId="+openId+"&type=2&packType="+kdPackType+"&packWeight="+kdPackWeight+"&gatherDate="+kdGatherDate+"&reservePrice="+reservePrice+"&longitude="+longitude+"&latitude="+latitude,
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code=='000000'){
							alert("下单成功，请等待快递员上门收货！");
							WeixinJSBridge.call('closeWindow');
						}else{
							alert("信息填写不完整，请检查");
						}
					},error : function() {
						alert("错误");
					}
				});
			}
			if (navigator.geolocation){
					navigator.geolocation.getCurrentPosition(showPosition);
				}
			var longitude;
			var latitude;
			function showPosition(position){
			 	longitude = position.coords.longitude;
			    latitude = position.coords.latitude;
			    //longitude = "113.38539307504628";
			    //latitude = "23.130460219606043";
			}
			
        </script>
    </head>
    <body style="background-color: whitesmoke;">
		
		<div class="dx-dingdan">
			
			<div class="jjr">寄件人信息</div>
			<div class="jjr-mess"  onclick="jMessage();">
				<div class="jjr-content">
				<div class="jjr-name">${ec1.name}  ${ec1.phone}</div>				
				<div class="jjr-dizhi">${ja}</div>
				<div id="kdJid"style="display: none;">${ec1.commonId}</div>
				</div>
				<div class="right-icon2">
					<img src="/expressage_api/express/img/arrow-right.png"/>
				</div>
			</div>
			
			<div class="sjr">收件人信息</div>
			<div class="jjr-mess" onclick="sMessage();">
				<div id="sjr-text" style="display: block;">
				<div class="jjr-content" >
				<div class="jjr-name">${ec2.name} ${ec2.phone}</div>				
				<div class="jjr-dizhi">${sa}</div>
				<div id="kdSid"style="display: none;">${ec2.commonId}</div>
				</div>
				<div class="right-icon2" >
					<img src="/expressage_api/express/img/arrow-right.png"/>
				</div>
				</div>
				<div id="sjr-add" style="display: none;">
				<div class="sjr-addtext">请添加收件人信息</div>
				<div class="right-iconx">
					<img src="/expressage_api/express/img/arrow-right.png"/>
				</div>
				</div>
			</div>
			
		</div>
		
			<div class="kd">快件信息:</div>
			<div class="kd-xinxi">
			<div class="kd-leibie-content">
				<div class="leibie-text">快递品件</div>
				<div class="kd-leibie" onclick="kd_leibie(this)" id="1" style="border: 0.06rem solid orange;"><div class="text-ck">文件</div></div>
				<div class="kd-leibie" onclick="kd_leibie(this)" id="2"><div class="text-ck">包裹</div></div>
				<div class="kd-leibie" onclick="kd_leibie(this)" id="3"><div class="text-ck">其他</div></div>
			</div>
			<div id="kd-leibie" style="display: none;">${weixinOrder.packType}</div>
			<div id="line3"></div>
			<div class="kd-leibie-content">
				<div class="leibie-text">重量(kg)</div>
				<div class="weight-ts"><input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" style="ime-mode: disabled;" placeholder="快递员根据重量选择交通工具" name="packWeight" value="${weixinOrder.packWeight}"/></div>
			</div>
			<div id="line3"></div>
			<div class="kd-leibie-content">
				<div class="leibie-text">预定价格(元)</div>
				<div id="yuding-price"><input type="text" name="reservePrice" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" style="ime-mode: disabled;" value="${weixinOrder.reservePrice}"/></div></div>
				
				<div id="line3"></div>
				<div class="kd-leibie-content" id="yhq-shiyong" onclick="yhq_use2()">
				<div class="yhq-text" id="yhq-text">${favorableStr}</div>
				<div id="kdYid"style="display: none;">${expressageFavorable.favorableId}</div>
				<div class="right-icon4" id="arrow4">
					<img src="/expressage_api/express/img/arrow-right.png"/>
				</div>
				</div>
				<div id="line3"></div>
				<div class="kd-leibie-content">
				<div class="leibie-text">取件时间</div>
				<div id="qujian-sj" onclick="qujian_time()">马上</div>
				</div>
		</div>
		<div class="agree-tiaokuan">
			<img src="/expressage_api/express/img/checkbox.png" onclick="changep2()" id="pic"/>
		</div>
			<div class="i-text">我同意</div>
			<div class="tiaokuan"><a href="/expressage_api/express/xieyi.html">《快件运输契约条款》</a></div>
		</div>
		<div class="kdypj-btn">
			<button id="kkxd" name="xiadan" onclick="xd();" style="background-color: darkgray;">
				<div class="weizhi">马上下单</div>
			</button>
			
		</div>
		
		<div id="heimu"></div>
		<div id="tck" style="display: none;" class="tck">
			<div class="tck-text">选择优惠券，只能使用微信支付或支付宝支付方式，是否继续选择优惠券？</div>
			
			<div class="tck-btn">
				<div class="tck-yn" style="color: orange;float:left;" onclick="yhq_yes()">是</div>
				<div class="tck-line2" style="float:left;"></div>
				<div class="tck-yn" style="float:right;" onclick="yhq_fou(this)" id="fou">否</div>
			</div>
		</div>
		<div id="tck2" style="display: none;" class="tck">
			<div class="tck-text">您好！<p style="display: inline-block;">杨佳峰</p>快递员已下班，不能及时取件，是否继续下单</div>
			<div class="tck-line1"></div>
			<div class="tck-btn">
				<div class="tck-yn" style="color: orange;">是</div>
				<div class="tck-line2"></div>
				<div class="tck-yn">否</div>
			</div>
		</div>
		
		<div class="tck3" style="display: none;" id="qujian-time">
			<div class="kd-gs-yc">
				<div class="kd-gs-c" onclick="yhq_fou(this)" id="cancel">取消</div>
				<div class="kd-gs-y" onclick="sure2()">确定</div>
			</div>
			<div class="line4"></div>
			<div class="kd-company2" onclick="change(this)" id="0">上午</div>
			<div class="line4"></div>
			<div class="kd-company2" onclick="change(this)" id="1" style="color: orange;">马上</div>
			<div class="line4"></div>
			<div class="kd-company2" onclick="change(this)" id="2">下午</div>
			
		</div>
		<div id="Otime" style="display: none;">${weixinOrder.gatherDate}</div>
        <div class="wait-jiedan" style="display: none;"><img src="/expressage_api/express/img/wait-jiedan.png"/></div>
		<div class="wait-jiedan" style="display: none;"><img src="/expressage_api/express/img/wait-shoujian.png"/></div>
	</body>
	
		<script>
        	var xx = document.getElementById("yhq-text");
			if(xx.innerHTML==''||xx.innerHTML==null){
				xx.innerHTML = "选择优惠券";
			}
        </script>
</html>