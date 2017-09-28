(function(angular){
    var app = angular.module('ctrlApp', ['ngSanitize']);
    app.controller("indexCtrl",["$scope",function($scope){
        $(".myFormNav li").on('click',function(){
            $(this).addClass("active").siblings().removeClass("active");
        });
        $("#title").html("个人中心");
        var userID = $.cookie("userID");
        
		if(!userID){
			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
		};
//		获取个人信息
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getUserInformation",
            type:"post",
            data:{
            	userId:userID
            },
            success:function(data){
            	data=JSON.parse(data);
            	console.log(data)
                if(data.code == "000000"){
             	   $scope.userMsg = data.response;
             	   
             	    var stars = $scope.userMsg.reputation;
     				var noStars = 5-stars;
     				var starsArr = [];
     				var noStarsArr = [];
     				for(var j = 0; j < stars; j++){
     					starsArr.push(j);
     				}
     				for(var k = 0; k < noStars; k++){
     					noStarsArr.push(k)
     				}
     				
     				$scope.starsArr = starsArr;
     				$scope.noStarsArr = noStarsArr;
             	   $scope.$apply();
                }else if(data.code == "100022"){
                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		
                }
            },
            error:function(){
            	
            }
        });
		
    	
//    	获取消息列表
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageMessage/getMessageNumByType",
            type:"post",
            data:{
            	channel:"wx",
            	type:1
         	   
            },
            success:function(data){
            	data=JSON.parse(data);
            	console.log(data)
                if(data.code == "000000"){
                	$scope.messageNum  = data.response.messageNum;
                	$scope.$apply();
                }else if(data.code == "100022"){
                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		
                }
            },
            error:function(){
            	
            }
        });
		
		
//        编辑个人信息
    	
//    	图片预览
       $(document.querySelector(".PaymentVoucherImg")).change(function(event) {
			
			var obj = $(this);
			var files = event.target.files, file; 
			if (files && files.length > 0) { 
				file = files[0]; 
				var URL = window.URL || window.webkitURL; 
				var imgURL = URL.createObjectURL(file); 
				obj.siblings(".uploadFile").attr("src",imgURL);
			} 
		});
    	
    	$scope.editUserMsg = function(){
    		
    		var formData = new FormData($("#userFormId")[0]);
    		formData.append("channel","wx");
    		$.ajax({
                url: "/expressage_api/rbac/sys/expressageUser/updateUserInformation",
                type: 'POST',  
	            data: formData,  
	            cache: false,  
	            contentType: false,  
	            processData: false,
                success:function(data){
                	data=JSON.parse(data);
                	console.log(data)
                    if(data.code == "000000"){
                    	location.href="index.html";
                    }else if(data.code == "100022"){
                    	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                    }else{
                    	layer.msg(data.message);
                    }
                },
                error:function(){
                	
                }
            });
    		
    	}
    	
    	
    	
        
    }]);
    
//    消息列表
    app.controller("newsCtrl",["$scope",function($scope){
    	var userID = $.cookie("userID");
   		if(!userID){
   			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
   		};
    	$("#title").html("消息");
    	
    	$.ajax({
    	    url: "/expressage_api/rbac/sys/expressageMessage/getMessageByType",
    	    type:"post",
    	    async:false,
    	     data: {
    	    	 channel:"wx",
    	    	 type:1,
    	    	 pageNo:0,
    	    	 pageSize:100
    	     },  
    	      success:function(data){
    	    	  data = JSON.parse(data)
    	      	   console.log(data)
    	          if(data.code == 0){
    	        	$scope.newList = data.response;
    	       		  
    	          }else if(data.code == "100022"){
                  	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                  }else{
    	          		
    	          }
    	      },
    	      error:function(){
    	      	
    	      }
    	  });

		
        
    }]);
    
//积分列表
    app.controller("jifenmallCtrl",["$scope",function($scope){
    	   var userID = $.cookie("userID");
   		if(!userID){
   			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
   		};
    	$("#title").html("积分商城");
    	
    	$.ajax({
    	    url: "/expressage_api/rbac/sys/expressageOrder/getProducts",
    	    type:"post",
    	    async:false,
    	     data: {
    	    	 pageNo:0,
    	    	 pageSize:10
    	     },  
    	      success:function(data){
    	    	  data = JSON.parse(data)
    	      	   console.log(data)
    	          if(data.code == 0){
    	        	$scope.productList = data.response;
    	       		  
    	          }else if(data.code == "100022"){
                  	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                  }else{
    	          		
    	          }
    	      },
    	      error:function(){
    	      	
    	      }
    	  });

		
        
    }]);
    
    
    
//  积分商品详情
    app.controller("productMsgCtrl",["$scope","$routeParams",function($scope,$routeParams){
    	var userID = $.cookie("userID");
   		if(!userID){
   			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
   		};
    	$("#title").html("积分商城");
    	var productId = $routeParams.type;
    	$scope.productId = productId;
//    	    获取商品列表
    	  $.ajax({
    	        url: "/expressage_api/rbac/sys/expressageOrder/getProducts",
    	        type:"post",
    	        async:false,
    	        data: {
    	        	pageNo:0,
       	    	 pageSize:10
    	        },  
    	        success:function(data){
    	        	data = JSON.parse(data)
    	          	   console.log(data)
    	              if(data.code == 0){
    	            	  $scope.productList = data.response;
    	           		  for(var i = 0; i< $scope.productList.length; i++){
    	           			  if($scope.productList[i].productId == productId){
    	           				  $scope.productMsg = $scope.productList[i];
    	           				  console.log($scope.productList[i])
    	           				  break;
    	           			  }
    	           		  }
    	           		  setTimeout(function(){
    	           			var mySwiper = new Swiper ('.swiper-container', {
        	            	    direction: 'horizontal',
        	            	    loop: true,
        	            	    // 如果需要分页器
        	            	    pagination: '.swiper-pagination',
        	            	    autoplay: 2000,
        	            	  }); 
    	           		  },0)
    	           		
    	              }else if(data.code == "100022"){
    	                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    	                }else{
    	              		
    	              }
    	       },
    	       error:function(){
    	          	
    	       }
    	  });

        
    }]);
    
//    兑换
    app.controller("sureFormCtrl",["$scope","$routeParams",function($scope,$routeParams){
    	var userID = $.cookie("userID");
   		if(!userID){
   			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
   		};
    	$("#title").html("订单确认");
    	var productId = $routeParams.type;
    	    
//		获取个人信息
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getUserInformation",
            type:"post",
            data:{
            	userId:userID
            },
            success:function(data){
            	data=JSON.parse(data);
            	console.log(data)
                if(data.code == "000000"){
             	   $scope.userMsg = data.response;
             	   $scope.$apply();
                }else if(data.code == "100022"){
                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		
                }
            },
            error:function(){
            	
            }
        });
    	
//	    获取商品列表
  	  $.ajax({
  	        url: "/expressage_api/rbac/sys/expressageOrder/getProducts",
  	        type:"post",
  	        async:false,
  	        data: {
  	        	pageNo:0,
     	    	pageSize:10
  	        },  
  	        success:function(data){
  	        	data = JSON.parse(data)
  	          	   console.log(data)
  	              if(data.code == 0){
  	            	  $scope.productList = data.response;
  	           		  for(var i = 0; i< $scope.productList.length; i++){
  	           			  if($scope.productList[i].productId == productId){
  	           				  $scope.productMsg = $scope.productList[i];
  	           				  console.log($scope.productMsg)
  	           				  break;
  	           			  }
  	           		  }
  	           		 
  	              }else if(data.code == "100022"){
                  	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                  }else{
  	              		
  	              }
  	       },
  	       error:function(){
  	          	
  	       }
  	  });

//    	  兑换
    	  $scope.duihuan = function(orderId){
    		  console.log(orderId)
    		  
    		  var sPhone = $(".sureForm input[name=sName]").val();
    		  var sAddress = $(".sureForm input[name=sPhone]").val();
    		  var sName = $(".sureForm input[name=sAddress]").val();
    		  if(!sName){
    			  layer.msg('请填写收货地址');
    			  return;
    		  }
    		  $.ajax({
      	        url: "/expressage_api/rbac/sys/expressageOrder/createOrderByIntegral",
      	        type:"post",
      	        async:false,
      	        data: {
      	        	channel:"wx",
      	        	productId:$scope.productMsg.productId,
      	        	sPhone:sPhone,
      	        	sAddress:sAddress,
      	        	sName:sName
      	        },  
      	        success:function(data){
      	        	data = JSON.parse(data)
      	          	   console.log(data);
      	        	  
      	              if(data.code == "000000"){
      	            	location.href = "index.html";
      	              }else if(data.code == "100022"){
                      	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                      }else{
      	              		layer.msg(data.message)
      	              }
      	       },
      	       error:function(){
      	          	
      	       }
      	  });
    	  }
		
        
    }]);

    
//    选择当前所在城市
    app.controller("selectAddressCtrl",["$scope",function($scope){
    	$("#title").html("选择城市");
        $.ajax({
            url: "/expressage_api/rbac/sys/expressageMessage/getCitys",
            type:"post",
            async:false,
	        data: {},  
            success:function(data){
            	data = JSON.parse(data)
              	 console.log(data);
                 if(data.code == "000000"){
                	$scope.cityList = data.response;
               		  
                 }else if(data.code == "100022"){
                 	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                 }else{
                  		
                 }
            },
            error:function(){
              	
            }
       });
        
        $scope.selectCity=function(event){
        	var currentCity = $(event.target).val();
        	$(event.target).parent().addClass("active").siblings().removeClass("active");
        	console.log(currentCity);
        	$.cookie("currentCity",currentCity);
        	$.cookie("currentCityFlag",1);
        	window.location.href = "index.html#/downForm";
        }
        
     }]);
//    快递用户下单
    app.controller("downFormCtrl",["$scope",function($scope){
    	var userID = $.cookie("userID");
  		if(!userID){
  			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
  		};
  		var currentCity = "";
    	$.ajax( {
			url : "/expressage_api/rbac/sys/expressageUser/wxConfig",
			data : {
				type:1
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				if(data.code=='000000'){
					wx.config({    
						debug: false,    
						appId: data.response.appId,    
						timestamp: data.response.timestamp,    
						nonceStr: data.response.noncestr,    
						signature: data.response.sign,    
						jsApiList: [    
							'checkJsApi',   
							'getLocation'
						]    
					}); 
				}else if(data.code == "100022"){
                 	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }
			},error : function() {	
				
			}
		});
  		
    	wx.ready(function(){
    		wx.getLocation({
    		    success: function (res) {
    		        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
    		        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
//    	  		给定当前经纬度
    		        console.log(longitude);
    		        console.log(latitude);
    	  		var point = new BMap.Point(longitude,latitude);
    	  		var gc = new BMap.Geocoder();
    	  	    gc.getLocation(point, function(rs){
    	  	       var addComp = rs.addressComponents;
    	  	       var cityName = addComp.city;
 	  			   currentCity = cityName;
 	  			   var addressFlag = $.cookie("currentCityFlag");
 	  			   if(addressFlag==1){
 	  				   currentCity=$.cookie("currentCity");
 	  				   $.cookie("currentCityFlag","0");
 	  			   }
 	  			   console.log(currentCity);
 	  			   if(currentCity){
 	  				   $scope.currentCity = currentCity;
 	  			   }else{
 	  				   $scope.currentCity = "韶关市";
 	  			   }
 	  			   $scope.$apply();
    	  	       
    	  	    })
    	  		
    		    },
    		    cancel: function (res) {
    		        alert('用户拒绝授权获取地理位置');
    		    }
    		});
    	
    	});
  		
//		获取个人信息
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getUserInformation",
            type:"post",
            data:{
            	userId:userID
            },
            success:function(data){
            	data=JSON.parse(data);
            	console.log(data)
                if(data.code == "000000"){
             	   $scope.userMsg = data.response;
             	   $scope.$apply();
                }else if(data.code == "100022"){
                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		
                }
            },
            error:function(){
            	
            }
        });
  		
  		
  	   $("#title").html("快递快滴");
  	    
       $scope.weightNum = "1";
       $scope.reduceWeight = function(){
    	   if($scope.weightNum>1){
    		   $scope.weightNum--;
    	   }
       }
       $scope.addWeight = function(){
    		   $scope.weightNum++;
       }
//       寄件地址
       $scope.jAddress = $.cookie("sendAddress");
//       收件地址
       $scope.sAddress = $.cookie("getAddress");
       $(".nextBtn").on('click',function(){
    	   if(!$scope.weightNum || !parseInt($scope.weightNum)){
    		   layer.msg("请输入货物重量");
    		   return false;
    	   }else if(!$scope.jAddress){
    		   layer.msg("请填写寄件地址");
    		   return false;
    	   }else if(!$scope.sAddress){
    		   layer.msg("请填写收件地址");
    		   return false;
    	   }
          $(".main").hide();
          $(".kd_footer").hide();
          $(".submitForm").show();
      });

       
       $scope.datestr = "立即取件";
       $(".now").on('click',function(){
    	   var dataId = $(this).attr("data-id");
    	   if(dataId=="1"){
    		   $scope.datestr = "立即取件";
    	   }
           $(".now").removeClass("active");
           $(this).addClass("active");
       });
       
//     预约取件初始化
       $('#endTime').date({theme:"datetime"},function(datestr){
    	   var showStr = "";
    	   console.log(datestr);
    	   $scope.datestr = datestr;
    	   var nowDate = new Date();
    	   var hour = parseInt(nowDate.getHours());
    	   var day = parseInt(nowDate.getDate());
    	   hour += 2;
    	   var index = $scope.datestr.indexOf(" ");
    	   var index2 = $scope.datestr.indexOf(":");
    	   submitHour = $scope.datestr.slice(index+1,index2);
    	   submitMinute = $scope.datestr.slice(index2+1);
    	   var oldStr = $scope.datestr;
    	   oldStr = oldStr.replace("-","");
    	   var index3 = oldStr.indexOf("-");
    	   submitHourDay = oldStr.slice(index3+1,index3+3);
    	   if(submitHourDay==day){
    		   showStr += "今天";
    	   }else{
    		   showStr += "明天";
    	   }
    	   showStr += submitHour+"时"+submitMinute+"分";
    	   
    	   if(submitHourDay==day&&submitHour<hour){
	   			layer.msg("预约时间需大于2小时以上");
	   			return;
	   		}else{
	   			$(".showTime").html(showStr);
	   		}
    	   
    	   
       },function(){
    	   $(".now").each(function(index,value){
    		   if(index==0){
    			   $(".now").removeClass("active");
    			   $(value).addClass("active");
    		   }
    	   });
    	   $scope.datestr = "立即取件";
    	   $(".showTime").html("立即预约");
       });

       
       
//       获取快递公司列表
       $.ajax({
           url: "/expressage_api/rbac/sys/expressageCourier/getCompanys",
           type:"post",
           async:false,
           data: {},  
             success:function(data){
             	 data = JSON.parse(data);
            	 console.log(data)
                 if(data.code == "000000"){
                	 $scope.kdList = data.response;
                 }else if(data.code == "100022"){
                 	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                 }else{
                 		
                 }
             },
             error:function(){
             	
             }
       })


       //提交快递订单	
       $scope.submit = function(){
    	   console.log(currentCity)
    	   var idStr = "";
    	   var flag = 0;
    	   if($("#allKd").attr("data-status")==1){
    		   $(".componys li").each(function(index,value){
        		   if(index!=0){
        			   if(flag==0){
        				   idStr += $(value).attr("data-companyid");
        				   flag++;
        			   }else{
        				   idStr += ","+$(value).attr("data-companyid")
        			   }
        			   
        		   }
        	   })
		   }else{
			   $(".componys li").each(function(index,value){
        		   if(index!=0){
        			   if($(value).attr("data-status")==1){
        				   if(flag==0){
            				   idStr += $(value).attr("data-companyid");
            				   flag++;
            			   }else{
            				   idStr += ","+$(value).attr("data-companyid")
            			   }
        			   }
        			   
        		   }
        	   })
		   }

    	  
    	   var phone_res = /^1[3|4|5|7|8][0-9]\d{8}$/;
    	   var regexp=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    	   		var formData = {};
    	   		formData.channel = "wx";
    	   		if(!$(".submitForm input[name=jAddress]").val()){
    	   			layer.msg("请输入寄件人地址");
    	   			return;
    	   		}else if(!$(".submitForm input[name=sAddress]").val()){
    	   			layer.msg("请输入收件人地址");
    	   			return;
    	   		}else if(!$(".submitForm input[name=jName]").val()){
    	   			layer.msg("请输入寄件人姓名");
    	   			return;
    	   		}else if(!$(".submitForm input[name=jPhone]").val()){
    	   			layer.msg("请输入寄件人电话");
    	   			return;
    	   		}else if(!phone_res.test($(".submitForm input[name=jPhone]").val())&&!regexp.test($(".submitForm input[name=jPhone]").val())){
                    layer.msg("请输入正确的手机号码");
                    return;
                }else if(!$(".submitForm input[name=sName]").val()){
    	   			layer.msg("请输入收件人姓名");
    	   			return;
    	   		}else if(!$(".submitForm input[name=sPhone]").val()){
    	   			layer.msg("请输入收件人电话");
    	   			return;
    	   		}else if(!phone_res.test($(".submitForm input[name=sPhone]").val())&&!regexp.test($(".submitForm input[name=sPhone]").val())){
                    layer.msg("请输入正确的手机号码");
                    return;
                }else if(!$(".submitForm input[name=packType]").val()){
    	   			layer.msg("请输入物品名称");
    	   			return;
    	   		}
    	   		
    	   		formData.packType = $(".submitForm input[name=packType]").val();
    	   		formData.packWeight = $scope.weightNum;
    	   		formData.gatherDate = $scope.datestr;
    	   		formData.jPhone = $(".submitForm input[name=jPhone]").val();
    	   		formData.jAddress = $(".submitForm input[name=jAddress]").val();
    	   		formData.jName = $(".submitForm input[name=jName]").val();
    	   		formData.sPhone = $(".submitForm input[name=sPhone]").val();
    	   		formData.sAddress = $(".submitForm input[name=sAddress]").val();
    	   		formData.sName = $(".submitForm input[name=sName]").val();
    	   		formData.companyIds = idStr;
    	   		formData.area = currentCity;
    	   		
        	   $.ajax({
                 url: "/expressage_api/rbac/sys/expressageOrder/createOrder",
                 type:"post",
                 async:false,
  	             data: formData,  
                 success:function(data){
                	   data = JSON.parse(data)
                   	   console.log(data)
                       if(data.code == "000000"){
                    	   location.href="index.html#/myform";
                       }else if(data.code == "100022"){
                       	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                       }else{
                    	   layer.msg(data.message)
                       }
                   },
                   error:function(){
                   	
                   }
               });

       }
       
       
       
       
     }]);
    
    app.directive('finishEd', function () {
        return {
            restrict: 'A',
            link: function(scope, elm, attr) {
                if (scope.$last === true) {
                	setTimeout(function(){
//                      选择快递公司
                	       $(".componys li").on('click',function(){
                	    	   var statusId = $(this).attr("data-id");
                	    	   var status = $(this).attr("data-status");
                	    	   if(!statusId){
                	    		   $(".componys li").eq(0).removeClass("active").attr("data-status",0);
                	    		   if(status==0){
                	    			   $(this).addClass("active");
                	    			   $(this).attr("data-status",1)
                	    		   }else{
                	    			   $(this).removeClass("active");
                	    			   $(this).attr("data-status",0)
                	    		   }
                	    	   }else{
                	    		   if(status==1){
                	    			   $(this).removeClass("active");
                	    			   $(this).attr("data-status",0);
                	    		   }else{
                	    			   $(this).addClass("active");
                	    			   $(this).siblings().removeClass("active").attr("data-status",0);
                	    			   $(this).attr("data-status",1);
                	    		   }
                	    		   
                	    	   }
                	       })
                	},0);
                	
                    
                }
            }
        };
    });
    
    
    
    
    
//    快递订单支付
    app.controller("payCtrl",["$scope","$routeParams",function($scope,$routeParams){
    	
    	$("#title").html("支付");
    	
    	$scope.orderId = $routeParams.type;
    	$scope.orderPrice = $routeParams.price;
    	$scope.payOrderPrice = "";
    	$scope.payType="4";
    	$scope.favorableId = "";
    	$scope.favorableName = 0;
//    	切换支付方式
    	 $(".tabPayType").on('click',function(){
    		 	var payStatus = $(this).find(".clickIcon").attr("data-type");
		        $(".clickIcon").removeClass("active");
		        $(this).find(".clickIcon").addClass("active");
		        $scope.payType = payStatus;
		 });
		 
    	
//    	获取可用优惠券
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getFavorables",
            type:"post",
            data:{
            	type:1
            },
            success:function(data){
            	data = JSON.parse(data)
            	console.log(data)
                if(data.code == "000000"){
                	$scope.ticketList = data.response;
                	if($scope.ticketList.length>0){
                		$scope.favorableName = $scope.ticketList[0].favorableName;
                		$scope.favorableId = $scope.ticketList[0].favorableId;
                		$scope.$apply();
                	}
                }else if(data.code == "100022"){
                 	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		layer.msg(data.message)
                }
            },
            error:function(){
            	
            }
        });

    	
//		 选择优惠券
    	$scope.toSelectTicket = function(){
    		$(".tickets").slideDown(300);
    	}
		$scope.selectTicket = function(event,favorableName,favorableId){
				$(event.target).addClass("active").attr("data-status",1);
				$(event.target).parent().siblings().find("span").removeClass("active").attr("data-status",0);
				$scope.favorableName = favorableName;
				$scope.favorableId = favorableId;
				
		}
		 
//		关闭优惠券列表
		$scope.closeTickets = function(){
			$(".tickets").slideUp(300);
		}
//		 支付提交
		 $scope.flag = false;
		 $scope.paying = function(){
    		 $.ajax({
                 url: "/expressage_api/rbac/sys/expressageOrder/payOrder",//支付
                 type:"post",
                 async:false,
  	             data: {
  	            	channel:"wx",
  	            	orderId:$scope.orderId,
  	            	type:$scope.payType,//1现金2微信支付3支付宝支付,
  	            	orderPrice:$scope.payOrderPrice,
  	            	favorableId:$scope.favorableId
  	             }, 
                   success:function(data){
                	   data = JSON.parse(data);
                   	   console.log(data);
                       if(data.code == 0){
                    	   layer.msg("支付成功");
                    	    $scope.data = data;
                    		if($scope.payType==4){
                    			$scope.flag = true;
                    		}
                    	   
                       }else if(data.code == "100022"){
                        	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                       }else{
                       		
                       }
                   },
                   error:function(){
                   	
                   }
               });

    	 }
		 
        
    }]);
    
//    获取发收货地址
    app.controller("getAddressCtrl",["$scope","$routeParams",function($scope,$routeParams){
    	
    	if($routeParams.type==1){
    		$("#title").html("寄件地址");
    	}else if($routeParams.type==2){
    		$("#title").html("收件地址");
    	}
    	var type = $routeParams.type;
//    	选定位置
    	$scope.sureAddress = function(event){
    		$scope.searchAddress = $(event.target).html();
    		$("#r-result").hide();
    	}
    	
    	$scope.toUp = function(address){
    		$scope.searchAddress = address;
    	}
    	
    	
//    	自动检索
    	var map = new BMap.Map("l-map");        
    	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
    	var options = {
    		onSearchComplete: function(results){
    			
    			// 判断状态是否正确
    			if (local.getStatus() == BMAP_STATUS_SUCCESS){
    				$("#r-result").show();
    				$scope.suggessList = results.ur;
    				$scope.$apply();
    			}
    		}
    	};
    	var local = new BMap.LocalSearch(map, options);
    	local.search("");
    	$("#searchAddress").on("input",function(){
    		var value = $(this).val();
    		local.search(value);
    	});
    	
//    	定位当前地址
//    	微信获取经纬度
    	$.ajax( {
			url : "/expressage_api/rbac/sys/expressageUser/wxConfig",
			data : {
				type:1
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				if(data.code=='000000'){
					wx.config({    
						debug: false,    
						appId: data.response.appId,    
						timestamp: data.response.timestamp,    
						nonceStr: data.response.noncestr,    
						signature: data.response.sign,    
						jsApiList: [    
							'checkJsApi',   
							'getLocation'
						]    
					}); 
				}else if(data.code == "100022"){
                 	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }
			},error : function() {	
				
			}
		});
	wx.ready(function(){
		wx.getLocation({
		    success: function (res) {
		        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
		        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
		        var speed = res.speed; // 速度，以米/每秒计
		        var accuracy = res.accuracy; // 位置精度
	    		var getLocation = function (successFunc, errorFunc,latitude,longitude) { //successFunc获取定位成功回调函数，errorFunc获取定位失败回调
	        	    //首先设置默认城市
	        	    var defCity = {
	        	        id: '000001',
	        	        name: '北京市',
	        	        date: ""//获取当前时间方法
	        	    };
	        	    //默认城市
	        	    $.cookie('VPIAO_MOBILE_DEFAULTCITY', JSON.stringify(defCity), { expires: 1, path: '/' });
	        	    var lon = longitude;//经度
	        	    var lat = latitude;//纬度
	        	    var point = new BMap.Point(lon, lat); // 创建点坐标
	        	    var gc = new BMap.Geocoder();
	        	    gc.getLocation(point, function (rs) {
	        	          var addComp = rs.addressComponents;
	        	          console.log(addComp);
	        	          var curCity = {
	        	                  id: '',
	        	                  name: addComp.province,
	        	                  date: ""
	        	          };
	        	          //当前定位城市
	        	          $.cookie('VPIAO_MOBILE_CURRENTCITY', JSON.stringify(curCity), { expires: 7, path: '/' });
	        	          if (successFunc != undefined){
	        	        	  successFunc(addComp);
	        	          }
	        	           
	        	    });
	        	 
	        	};
	        	
//	        	初始化当前位置
	        	getLocation(function(data){
	        		console.log(data)
	        		$scope.currentPosition = data.city+data.district+data.street;
	        		$scope.$apply();
	        	},function(data){
	        		console.log(data);
	        	},latitude,longitude);
		        
		        
		        
		    },
		    cancel: function (res) {
		        alert('用户拒绝授权获取地理位置');
		    }
		});
	
	});
    	
    	
    	

    		
    	
    	
    	
//    	历史地址
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getAddressList",//根据type获取订单
            type:"post",
            async:false,
	             data: {
	            	channel:"wx",
	            	type:type,//1寄2收
	             }, 
              success:function(data){
            	  data = JSON.parse(data)
              	   console.log(data)
                  if(data.code == "000000"){
                	$scope.type = type;
                	if(data.response.length>3){
                		$scope.addressList = data.response.slice(0,3);
                	}else{
                		$scope.addressList = data.response
                	}
                  }else if(data.code == "100022"){
                   	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                  }else{
                  		layer.msg(data.message)
                  }
              },
              error:function(){
              	
              }
          });
    	
    	$scope.oldAddress = function(event){
    		$scope.searchAddress = $(event.target).html();
    		
    	}
        $scope.type=$routeParams.type;
        $scope.backDownForm = function(){
        	if(type==1){
            	$.cookie("sendAddress",$scope.searchAddress);
        	}else if(type==2){
        		$.cookie("getAddress",$scope.searchAddress);
        	}

        	location.href="index.html#/downForm";
        }
        
    }]);
    
//    我的订单
    app.controller("myFormCtrl",["$scope","$routeParams",function($scope,$routeParams){
    	var userID = $.cookie("userID");
		if(!userID){
			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
		}
		$("#title").html("我的订单");
		
//		获取个人信息
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getUserInformation",
            type:"post",
            data:{
            	userId:userID
            },
            success:function(data){
            	data=JSON.parse(data);
            	console.log(data)
                if(data.code == "000000"){
             	   $scope.userMsg = data.response;
             	    var stars = $scope.userMsg.reputation;
     				var noStars = 5-stars;
     				var starsArr = [];
     				var noStarsArr = [];
     				for(var j = 0; j < stars; j++){
     					starsArr.push(j);
     				}
     				for(var k = 0; k < noStars; k++){
     					noStarsArr.push(k)
     				}
     				
     				$scope.starsArr = starsArr;
     				$scope.noStarsArr = noStarsArr;
             	   $scope.$apply();
                }else if(data.code == "100022"){
                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		
                }
            },
            error:function(){
            	
            }
        });
		
    	$scope.num1 = "";
    	$scope.num2 = "";
    	$scope.num3 = "";
    	$scope.num4 = "";
        $scope.getMyForm = function(type,event){
    		if(event){
    			$(event.target).parent().addClass("active").siblings().removeClass("active");
    		}
        	$.ajax({
                url: "/expressage_api/rbac/sys/expressageOrder/getOrdersByUser",//根据type获取订单
                type:"post",
                async:false,
 	             data: {
 	            	channel:"wx",
 	            	pageNo:0,
 	            	pageSize:10,
 	            	type:type,//1待抢单2待付款3待签收4完成
 	             }, 
                  success:function(data){
                	  data = JSON.parse(data)
                  	   console.log(data)
                      if(data.code == "000000"){
                    	$scope.type = type;
                   		$scope.dataList = data.response;
                   		if(type==1){
                   			$scope.num1 = data.response.length;
                   		}else if(type==2){
                   			$scope.num2 = data.response.length;
                   		}else if(type==3){
                   			$scope.num3 = data.response.length;
                   		}else if(type==4){
                   			$scope.num4 = data.response.length;
                   		}
                      }else if(data.code == "100022"){
                       	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                      }else{
                      		layer.msg(data.message)
                      }
                  },
                  error:function(){
                  	
                  }
              });
        }
        
        
//        初始化订单列表
        $scope.getMyForm(4);
        $scope.getMyForm(3);
        $scope.getMyForm(2);
        $scope.getMyForm(1);
//        弹出框
        $scope.sureGet = function(status,currentOrderId,orderPrice){
        	console.log(status);
        	$scope.status = status;
        	$scope.currentOrderId = currentOrderId;
        	$scope.orderPrice = orderPrice;
        	var timer3 = null;
        	timer3 = setTimeout(function(){
        		$(".shadow").fadeIn(200);
        		clearTimeout(timer3);
        	},0)
        };
//        弹出框消失
        $scope.hideShow = function(isSure){
        	if(isSure){//确认
        		$(".shadow").fadeOut(200);
        		var price = 0;
        		if(!$scope.orderPrice){
        			price = 0;
        		}else{
        			price = $scope.orderPrice;
        		}
//        		location.href="index.html#/pay/"+$scope.currentOrderId+"/"+price;
        		location.href="pay.html?orderId="+$scope.currentOrderId+"&price="+price;
        	}else{
        		$(".shadow").fadeOut(200);
        	}
        	
        };
    }]);
    
//  订单详情
    app.controller("formMsgCtrl",["$scope","$routeParams",function($scope,$routeParams){
    	var userID = $.cookie("userID");
		if(!userID){
			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
		}
    	$("#title").html("订单详情");
    	var type = $routeParams.type;
    	var orderId = $routeParams.orderId;
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getUserInformation",
            type:"post",
            data:{
            	userId:userID
            },
            success:function(data){
            	data=JSON.parse(data);
            	console.log(data)
                if(data.code == "000000"){
             	   $scope.userMsg = data.response;
             	    var stars = $scope.userMsg.reputation;
     				var noStars = 5-stars;
     				var starsArr = [];
     				var noStarsArr = [];
     				for(var j = 0; j < stars; j++){
     					starsArr.push(j);
     				}
     				for(var k = 0; k < noStars; k++){
     					noStarsArr.push(k)
     				}
     				
     				$scope.starsArr = starsArr;
     				$scope.noStarsArr = noStarsArr;
             	   $scope.$apply();
                }else if(data.code == "100022"){
                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		
                }
            },
            error:function(){
            	
            }
        });
    	
    	
        	$.ajax({
                url: "/expressage_api/rbac/sys/expressageOrder/getOrdersByUser",//根据type获取订单
                type:"post",
                async:false,
 	             data: {
 	            	channel:"wx",
 	            	pageNo:0,
 	            	pageSize:1000000,
 	            	type:type,//1待抢单2待付款3待签收4完成
 	             }, 
                  success:function(data){
                	  data = JSON.parse(data)
                  	   console.log(data)
                      if(data.code == "000000"){
                    	$scope.type = type;
                   		for(var i = 0; i < data.response.length; i++){
                   			if(orderId==data.response[i].orderId){
                   				$scope.formMsg = data.response[i];
                       			
                   				break;
                   			}
                   		}
                   		var bigDate = "";
                   		var bigTime = "";
                   		if($scope.formMsg.gatherDate == "立即取件"){
                   			var  formTime = $scope.formMsg.orderDate;
                   			var formTime = formTime.replace(/-/g, "/");
                   			bigDate = new Date(formTime);
                   			bigTime = bigDate.getTime()+30*60*1000;
                   		}else{
                   			var  formTime = $scope.formMsg.gatherDate;
                   			var formTime = formTime.replace(/-/g, "/");
                   			bigDate = new Date(formTime);//得到预约日期对象
                       		bigTime = bigDate.getTime();//得到预约时间
                   		}
                   		var timer = null;
                   		
                   		timer = setInterval(function(){
                   			var nowDate = new Date();//得到当前日期
                       		var nowTime = nowDate.getTime();//得到当前毫秒数
                       		var showTime = parseInt((bigTime-nowTime)/1000);//得到时间差
                       		if(showTime<=0){
                       			$scope.status2 = 0;
                       			$scope.$apply();
                       		   clearInterval(timer);
                       		   return false;
                       		}else{
                       			$scope.status2 = 1;
                       		}
                       		var h = Math.floor(showTime / 60 / 60);
                       		var m = Math.floor((showTime - h * 60 * 60) / 60);
                       		var s = Math.floor((showTime - h * 60 * 60 - m * 60));
                       		
                       		$scope.h = h>9?h:"0"+h;
                       		$scope.m = m>9?m:"0"+m;
                       		$scope.s = s>9?s:"0"+s;
                       		$scope.$apply();
                   		},1000)
                   		
                   		
                      }else if(data.code == "100022"){
                       	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                      }else{
                      		
                      }
                  },
                  error:function(){
                  	
                  }
              });
        	
        	
//      弹出框
        $scope.sureGet = function(status){
        	console.log(status)
        	$scope.status = status;
        	var timer2 = null;
        	timer2 = setTimeout(function(){
        		$(".shadow").fadeIn(200);
        		clearTimeout(timer2);
        	},0)
        };
//        弹出框消失
        $scope.hideShow = function(isSure,orderId,types){
        	if(isSure){//确认
        		if(types==1){//取消订单
        			$.ajax({
                        url: "/expressage_api/rbac/sys/expressageOrder/abolishOrder",//取消单
                        type:"post",
                        async:false,
         	             data: {
         	            	channel:"wx",
         	            	orderId:orderId,
         	             }, 
                          success:function(data){
                        	  data = JSON.parse(data)
                          	   console.log(data)
                              if(data.code == "000000"){
                            	  $(".shadow").fadeOut(200)
                            	  location.href="index.html#/myform";
                              }else if(data.code == "100022"){
                               	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                              }else{
                              	  layer.msg(data.message);
                              	$(".shadow").fadeOut(200)
                              }
                          },
                          error:function(){
                          	
                          }
                      });
        		}else if(types==2){//去支付
        			var price = 0;
        			if(!$scope.formMsg.orderPrice){
        				price = 0
        			}else{
        				price = $scope.formMsg.orderPrice
        			}
//        			 location.href="index.html#/pay/"+orderId+"/"+price;
        			location.href="pay.html?orderId="+orderId+"&price="+price;
        		}
        		
        		
        	}else{
        		$(".shadow").fadeOut(200)
        	}
        	
        };
        
//        确认收货
        $scope.getFood = function(orderId){
        	$.ajax({
                url: "/expressage_api/rbac/sys/expressageOrder/abolishOrder",//取消单
                type:"post",
                async:false,
 	             data: {
 	            	channel:"wx",
 	            	orderId:orderId,
 	             }, 
                  success:function(data){
                	  data = JSON.parse(data)
                  	   console.log(data)
                      if(data.code == "000000"){
                    	  layer.msg("已确认收货");
                    	  location.href="index.html#/myform";
                      }else if(data.code == "100022"){
                       	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                      }else{
                      	  layer.msg(data.message);
                      }
                  },
                  error:function(){
                  	
                  }
              });
        }
        
    }]);
    
//    快递查询
    app.controller("kdchaxunCtrl",["$scope",function($scope){
    	$("#title").html("快递查询");
    	
    	$scope.toSelectKuaiDi = function(){
    		window.location.href = "index.html#/kuaidiList";
    	}
    	var kdName = $.cookie("kdName");
    	var kdJiancheng = $.cookie("kdJiancheng");
//    	获取快递公司名称
    	$(".allKuaiDi li").on('click',function(){
    		$.cookie("kdName",$(this).find("div").text());
    		$.cookie("kdJiancheng",$(this).find("span").text());
    		window.location.href = "index.html#/kdchaxun";
    	})
    	if(kdName){
    		$(".kuaidiinquiry input[name=logisticsJc]").val(kdName);
    	}
    	if(kdJiancheng){
    		$(".kuaidiinquiry input[name=logisticsJc]").attr("data-id",kdJiancheng)
    	}
    	$scope.type=2;
        $scope.inquiry = function(){
        	var logisticsJc = $(".kuaidiinquiry input[name=logisticsJc]").attr("data-id");
            var logisticsOrder = $(".kuaidiinquiry input[name=logisticsOrder]").val();
            console.log(logisticsJc)
            console.log(logisticsOrder)
        	$.ajax({
                url: "/expressage_api/rbac/sys/expressageUser/getLogisticsByOrderNum",
                type:"post",
                data:{
                 logisticsJc:logisticsJc,
             	 logisticsOrder:logisticsOrder
             	   
                },
                success:function(data){
                	data = JSON.parse(data)
                	   console.log(data)
                    if(data.code == "000000"){
                    	$scope.type=1;
                  	    $scope.formMsg = data.response;
                  	    console.log($scope.type);
                  	    $scope.$apply();
                    }else if(data.code == "100022"){
                     	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                    }else{
                    		layer.msg(data.message)
                    }
                },
                error:function(){
                	
                }
            });
        }
     }]);
    
//    积分列表
    app.controller("jifenCtrl",["$scope",function($scope){
    	var userID = $.cookie("userID");
		if(!userID){
			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
		};
		$("#title").html("积分");
		
//		获取个人信息
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getUserInformation",
            type:"post",
            data:{
            	userId:userID
         	   
            },
            success:function(data){
            	data=JSON.parse(data);
            	console.log(data)
                if(data.code == "000000"){
             	   $scope.validIntegral = data.response.validIntegral;
                }else if(data.code == "100022"){
                 	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		
                }
            },
            error:function(){
            	
            }
        });
    	
    	
//    	获取积分列表
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getUserIntegrals",
            type:"post",
            data:{
            	channel:"wx",
            	pageNo:0,
            	pageSize:10
         	   
            },
            success:function(data){
            	data=JSON.parse(data);
            	console.log(data)
                if(data.code == "000000"){
              	  $scope.dataList = data.response;
              	  $scope.dataListLength = data.response.length;
              	  $scope.$apply()
             	   
                }else{
                		
                }
            },
            error:function(){
            	
            }
        });
    	
     }]);
   
//	关于我们
    app.controller("singlesCtrl2",["$scope","$routeParams",function($scope,$routeParams){
    	
    	if($routeParams.type==1){
    		$("#title").html("关于我们");
    	}else if($routeParams.type==2){
    		$("#title").html("常见问题");
    	}else if($routeParams.type==3){
    		$("#title").html("价格表");
    	}
    	
    	
    	var type = $routeParams.type;
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/eText",
            type:"post",
            data:{
         	   type:type //1关于我们2常见问题3价格列表4轮播图
            },
            success:function(data){
            	data = JSON.parse(data)
            	   console.log(data)
                if(data.code == "000000"){
                	$scope.contentData = data.response[0];
                	$scope.type = type;
                	$scope.$apply();
                }else if(data.code == "100022"){
                 	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		layer.msg(data.message)
                }
            },
            error:function(){
            	
            }
        });
    	
       
     }]);
    
//	反馈和投诉
    app.controller("singlesCtrl",["$scope","$routeParams",function($scope,$routeParams){
    	if($routeParams.type==1){
    		$("#title").html("反馈");
    	}else if($routeParams.type==2){
    		$("#title").html("投诉");
    	}
    	
    
       $scope.type = $routeParams.type;
    	
       $scope.textContent = "";
//       反馈
       $scope.backText = function(){
    	   $.ajax({
               url: "/expressage_api/rbac/sys/expressageUser/addIdea",
               type:"post",
               data:{
            	   channel:"wx",
            	   content:$scope.textContent,
            	   type:1 //1用户  2快递
               },
               success:function(data){
            	   data = JSON.parse(data);
            	   console.log(data)
                   if(data.code == "000000"){
                	   location.href= "index.html";
                	   
                   }else if(data.code == "100022"){
                	   layer.msg(data.message,function(){
                		   window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                	   })
                	   
                   }else{
                	   layer.msg(data.message)
                   }
               },
               error:function(){
               	
               }
           });
    	   
       };
    	
//       投诉
       $scope.complain = function(){
    	   var orderNo = $(".tousu input[name=orderNo]").val();
    	   console.log(orderNo)
    	   if(!orderNo){
    		   layer.msg("请填写订单号");
    		   return;
    	   }
    	   $.ajax({
               url: "/expressage_api/rbac/sys/expressageUser/addComplain",
               type:"post",
               data:{
            	   channel:"wx",
            	   content:$scope.textContent,
            	   orderNo:orderNo 
               },
               success:function(data){
            	   data=JSON.parse(data)
               	   console.log(data)
                   if(data.code == "000000"){
                	   location.href= "index.html";
                	
                   }else if(data.code == "100022"){
                	   layer.msg(data.message,function(){
                		   window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                	   })
                	   
                   }else if(data.code == "100004"){
                	   layer.msg("内容不能为空");
                   }else{
                   		layer.msg(data.message)
                   }
               },
               error:function(){
               	
               }
           });
    	   
       }
       
     }]);
    
//    我的优惠券
app.controller("ticketCtrl",["$scope","$routeParams",function($scope,$routeParams){
	    var userID = $.cookie("userID");
	    if(!userID){
	    	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
	    };
    	$("#title").html("优惠券");
    	$.ajax({
            url: "/expressage_api/rbac/sys/expressageUser/getFavorables",
            type:"post",
            data:{
            	type:2
            },
            success:function(data){
            	data = JSON.parse(data)
            	   console.log(data)
                if(data.code == "000000"){
                	$scope.ticketList = data.response;
                	$scope.$apply();
                }else if(data.code == "100022"){
                 	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                }else{
                		layer.msg(data.message)
                }
            },
            error:function(){
            	
            }
        });
    	
       
     }]);


//支付成功分享
app.controller("paySuccessCtrl",["$scope","$routeParams",function($scope,$routeParams){
	    var userID = $.cookie("userID");
	    if(!userID){
	    	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
	    };
    	$("#title").html("支付成功");
    	
    	    var orderId  = $.cookie("orderId");
    		var payPrice = $.cookie("payPrice");
    		var payTime = $.cookie("datestr");
    		var orderNo = "";
    		//获取订单号
    		$.ajax({
    	         url: "/expressage_api/rbac/sys/expressageOrder/getOrdersByUser",//根据type获取订单
    	         type:"post",
    	         async:false,
    	           data: {
    	          	channel:"wx",
    	          	pageNo:0,
    	          	pageSize:10,
    	          	type:4,//1待抢单2待付款3待签收4完成
    	           }, 
    	           success:function(data){
    	         	  data = JSON.parse(data)
    	           	   console.log(data)
    	               if(data.code == "000000"){
    	            		for(var i =0; i < data.response.length; i++){
    	            			var item =  data.response[i];
    	            			if(item.orderId==orderId){
    	            				 orderNo = item.orderNo;
    	            				 break;
    	            			}
    	            		}
    	            		
    	            		var htmlStr = "";
    	            		htmlStr += '<div>支付金额：'+payPrice+' </div>';
    	            		htmlStr += '<div>订单号： '+orderNo+'</div>'
    	            	    htmlStr += '<div>支付时间：'+payTime+'</div>'
    	            		$(".payMsg").html(htmlStr);
    	               }else if(data.code == "100022"){
    	                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    	               }else{
    	               		layer.msg(data.message)
    	               }
    	           },
    	           error:function(){
    	           	
    	           }
    	       });
    		
    	
    wx.ready(function(){    
    	// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口  
    	wx.onMenuShareTimeline({  
    	 title: '快递快滴',  
    	 desc: '进入快递快滴，惊喜不断',  
    	 link: url,  
    	 imgUrl: '',  
    	 trigger: function (res) {  
    	   //alert('用户点击分享到朋友圈');  
    	 },  
    	 success: function (res) {  
    	   //分享成功的回调
    		 $.ajax({
    	         url: "/expressage_api/rbac/sys/expressageUser/setFavorable",//生成优惠券
    	         type:"post",
    	         async:false,
    	           data:{
    	          	orderId:orderId
    	           }, 
    	           success:function(data){
    	         	  data = JSON.parse(data)
    	               if(data.code == "000000"){
    	            	   window.location.href = "index.html";
    	               }else if(data.code == "100022"){
    	                	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    	               }else if(data.code=="100024"){
    	            	   layer.msg("很遗憾，谢谢！");
    	               }else{
    	               		layer.msg(data.message);
    	               }
    	           }
    	       }); 
    	 },  
    	 cancel: function (res) {  
    	   alert('已取消');  
    	 },  
    	 fail: function (res) {  
    	   alert("失败");  
    	 }  
    	});  
    	wx.error(function (res) {  
    	  //alert(res.errMsg); 
    	  //alert("调用接口错误")
    	});  
    	});  
    	
       
     }]);


//设置
app.controller("sheZhiCtrl",["$scope","$routeParams",function($scope,$routeParams){

    var userID = $.cookie("userID");
    if(!userID){
    	return;
    	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    };
	$("#title").html("设置");
	
	
	
   
 }]);

//修改密码
app.controller("xiugaiCtrl",["$scope","$routeParams",function($scope,$routeParams){
    var userID = $.cookie("userID");
    if(!userID){
    	return;
    	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    };
	$("#title").html("修改密码");
	$scope.changePw = function(){
		var password = $(".login input[name=password]").val();
	    if(phone.length < 1){
	        layer.msg("请输入手机号码");
	    }else if(password.length < 1){
	        layer.msg("请输入密码");
	    }else if(!code){
	    	layer.msg("无法获取code");
	    }else{
	    	console.log(password)
	    	var hash = $.md5(password);
	        $.ajax({
	            url:"/expressage_api/rbac/sys/expressageUser/loginUserWX",
	            type:"post",
	            data:{
	                phone:phone,
	                password:hash,
	                wxcode:code
	            },
	            success:function(data){
	            	data = JSON.parse(data)
	                console.log(data);
	                if(data.code == "000000"){
	                  $.cookie("userID",data.response.userId,{path: '/expressage_api'});
	                  
              		  setTimeout(function(){
                            location.href = "index.html";
                        },500);
	                }else{
	                    layer.msg(data.message);
	                }
	            },
	            error:function(){
	                
	            }
	        });
	    }
		
		
	}
	
	
   
 }]);


//退出登录
app.controller("layoutCtrl",["$scope","$routeParams",function($scope,$routeParams){
    var userID = $.cookie("userID");
    if(!userID){
    	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    };
	$("#title").html("退出");
	$.ajax({
        url: "/expressage_api/rbac/sys/expressageUser/logoutWX",
        type:"post",
        data:{},
        success:function(data){
      	    data = JSON.parse(data);
        	console.log(data)
            if(data.code == "000000"){
            	
          	$.cookie("userID",null,{path: '/expressage_api',expires: -1});
          	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
            }else if(data.code == "100022"){
             	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
            }else{
            	
            }
        },
        error:function(){
        	
        }
    });
	
   
 }]);

})(angular);