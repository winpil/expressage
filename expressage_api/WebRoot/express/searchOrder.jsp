<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>查询单号</title>
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <link href="css/kuaidi.css" rel="stylesheet" />
		<link href="css/header.css" rel="stylesheet" />
		<script src="js/jquery.js" ></script>
		<script src="js/zsy.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/tanchukuang.js" type="text/javascript" charset="utf-8"></script>
		
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
			var code = Request["code"];
			
        	function searchOrder(){
        		
        		var logisticsOrder=$("input[name='logisticsOrder']").val();
				var logisticsJc = $("#kd-gs-id").text();
				if(logisticsOrder==null||logisticsOrder==''){
					alert("请输入运单号");
					return false;
				}
				if(logisticsJc==null||logisticsJc==''){
					alert("请选择快递公司");
					return false;
				}
				document.getElementById("guodu").style.display = "block";
				$.ajax( {
					url : "/expressage_api/rbac/sys/expressageUser/getLogisticsByOrderNum",
					data : "logisticsJc="+logisticsJc+"&logisticsOrder="+logisticsOrder,
					type : "post",
					dataType : "json",
					success : function(data) {
						if(data.code!='000000'){
							alert(data.message);
							$("#wuliu").html("");
						}else{
							var htmlStr = "<div class='wuliu-text'>物流详情:</div>";
	        				for(var i=0;i<data.response.logisticsData.length;i++){
	        					htmlStr+="<div class='wuliu-data'><div class='wuliu-icon' style='margin-top: 0.7%;'><img src='img/1x.png'/ id='img1x'><br /><div class='line-wuliu2'></div></div>";
	        					htmlStr+="<div class='wuliu-date-text'>"+data.response.logisticsData[i].time+"  "+data.response.logisticsData[i].content+"<div class='line-wuliu'></div></div></div>";
	        				}	
	        				$('#wuliu').html(htmlStr);  
						}
						
						//alert(data.response.data.data[0].time);
						//alert(data.response.data.data[0].content);
						
						document.getElementById("wuliu").style.display = "block";
						document.getElementById("guodu").style.display = "none";
						
						
						
					},error : function() {
						document.getElementById("guodu").style.display = "none";
					}
				});
			}
        	
        </script>
    </head>
    <body>
       <div class="search-content">
			<div class="search-shuoming">输入单号，智能查快递</div>
			
				<div class="search"  style="border-right: 0;">
					<input type="text" placeholder="请输入快递单号" name="logisticsOrder" id="kyh" style="float: left;"/>
					<div class="search-start" onclick="searchOrder()" style="float: right;">
						<div class="chuizhi-jz3">查询</div>
					</div>	
					<div style="clear: both;"></div>
				</div>
				
				<div class="search" style="width: 94%;">
					<input type="text" placeholder="请选择快递公司" id="ky-gs" style="width: 80%;" class="1"/>
					<div class="search-icon" onclick="choice()" id="arrow1">
						<img src="img/arrow-down.png" />
					</div>					
				</div>
			
			<div class="search-shuoming">马上收已支持申通圆通、中通、汇通、顺丰、韵达、EMS德邦、全峰、天天快递等100多家快递公司的快递查询。</div>
		</div>
		<div id="kd-gs" style="display: none;">
											
			<div class="kd-allcompany">
			<div class="kd-company" onclick="change2(this)">德邦物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">EMS快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">汇通快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">申通快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">顺丰快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">圆通快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">韵达快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">中通快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">宅急送快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">AAE快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">安信达快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">安捷快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">新蛋物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">百福东方</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">包裹、平邮、挂号信</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">传喜物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">程光快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">东方快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">长通物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">城市之星物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">城市100快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">传志快递</div>
			<div class="line4"></div>
			
			<div class="kd-company" onclick="change2(this)">DHL快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">DPEX快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">递四方速递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">大田物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">大洋物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">D速快递</div>
			<div class="line4"></div>
			
			<div class="kd-company" onclick="change2(this)">FEDEX国际快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">FEDEX国内快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">飞康达快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">飞邦物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">飞豹快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">飞狐快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">共速达物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">国通快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">港中能达</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">天地华宇</div>
			<div class="line4"></div>
			
			<div class="kd-company" onclick="change2(this)">天天快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">恒路物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">汇强快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">华夏龙物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">昊盛物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">嘉里大通物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">佳怡物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">佳吉快运</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">加运美快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">京广快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">晋越快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">快捷快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">康力物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">龙邦快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">联昊通快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">乐捷递快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">立即送</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">民邦快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">民航快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">美国快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">OCS快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">平安达快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">全峰快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">全一快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">全晨快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">全日通快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">如风达快递</div>
			<div class="line4"></div>
			
			<div class="kd-company" onclick="change2(this)">速尔快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">山东海红快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">三态速递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">盛辉物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">盛丰物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">圣安物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">赛澳递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">TNT快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">通和天下物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">通成物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">UPS国际快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">优速快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">伟邦快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">微特派快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">万象物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">信丰物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">新邦物流</div>
			<div class="line4"></div>
			
		
			<div class="kd-company" onclick="change2(this)">一邦快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">运通快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">元智捷诚快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">原飞航快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">亚风快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">远成物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">越丰快递</div>
			<div class="line4"></div>
		
		
			<div class="kd-company" onclick="change2(this)">中铁快运</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">中邮物流</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">芝麻开门</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">郑州建华快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">中天万运快递</div>
			<div class="line4"></div>
			<div class="kd-company" onclick="change2(this)">京东快递</div>
			<div class="line4"></div>
			
			</div>
			
		
		
			</div>
		<div id="kd-gs-id"style="display:none;"></div>
		
		<div class="wuliu" style="margin-top: 15%;display: none;" id="wuliu">
		<div class="wuliu-text">物流详情:</div>
		
		</div>
		<div id="guodu" style="display: none;">正在为您努力加载信息</div>
    </body>
    <script>
			$("#kyh").val(code);
	 </script>
</html>