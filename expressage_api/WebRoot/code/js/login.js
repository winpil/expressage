$(function(){

var RandomStr//随机数


//   获取url参数
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
	

 //生成6位随机数           add

    function _getRandomString(len) {  
    len = len || 32;  
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1  
    var maxPos = $chars.length;  
    RandomStr = '';  
    for (i = 0; i < len; i++) {  
        RandomStr += $chars.charAt(Math.floor(Math.random() * maxPos));  
    }  
    return RandomStr;  
}  

//  加载生成随机数  add

$(".pitCode .pit-con-pic").attr("src","/expressage_api/rbac/sys/getRand?randomStr="+_getRandomString(32));

//  刷新随机数  add

$(".pitBtn").on("click", function () {

          $(".pitCode .pit-con-pic").attr("src","expressage_api/rbac/sys/getRand?randomStr="+_getRandomString(32));



})

//	登录
	$("#loginBtn").on('click',function(){
		
		var Request = new Object();
		Request = GetRequest();
		var code = Request["code"];
		
		
		
		 var phone = $(".login input[name=phone]").val();
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
		                if(data.code == "000000"){
		                	$.cookie("userID",data.response.userId,{expires:30,path: '/expressage_api'});
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
	});

//	注册
      //获取验证码
      var loading_ = true;
      var s = 60;
      $(".GetCode .codeBtn").click(function(){
    	  var type = $(this).attr("data-id");
          if(loading_ == true){
              var phone = $(".registerPage input[name=phone]").val();
              var phone_res = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
              var picCode=$(".pitCode input").val();                                        // add
              if(phone.length < 1){
                  layer.msg("请输入手机号码");
              }else if(!phone_res.test(phone)){
                  layer.msg("请输入正确的手机号码");
              }else if(picCode.length<1){                                          //add
                  layer.msg("请输入图形验证码");
              }else{
                  
                  $.ajax({
                      url:"/expressage_api/rbac/sys/expressageUser/smsCode",
                      type:"post",
                      async:false,
                      data:{
                          phone:phone,
                          type:type,	//1为注册,2为忘记密码
                          status:1,
                          "randomStr":RandomStr,
                          "rand":picCode
                      },
                      success:function(data){
                    	  data = JSON.parse(data)
                          console.log(data);
                          if(data.code == "000000"){
                        	  loading_ = false;
                              var timer = setInterval(function(){
                                  if(s > 1){
                                      s--;
                                      $(".codeBtn").text(s+"s");
                                  }else{
                                      loading_ = true;
                                      s =60;
                                      $(".codeBtn").text("获取验证码");
                                      clearInterval(timer);
                                  }
                              },1000);
                          }else{
                              layer.msg(data.message);
                          }
                      }
                  });
              }
          }
      });
      //注册提交
      $("#registUser").click(function(){
  		var Request = new Object();
		Request = GetRequest();
		var wxcode = Request["code"];
    	  
    	  
    	  var type =$(this).attr("data-id");
    	  var ulr = "";
          var phone = $(".registerPage input[name=phone]").val();
          var password = $(".registerPage input[name=password]").val();
          var code = $(".registerPage input[name=code]").val();
          var phone_res = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
          if(phone.length < 1){
              layer.msg("请输入您的手机号码");
          }else if(!phone_res.test(phone)){
              layer.msg("请输入正确的手机号码");
          }else if(password.length < 1){
              layer.msg("请输入您的登录密码");
          }else if(password.length < 6 || password.length > 16){
              layer.msg("请输入6-20位的登录密码");
          }else if(code.length < 1){
              layer.msg("请输入手机验证码");
          }else if(!wxcode){
		    	layer.msg("无法获取code");
		    }else{
            var hash = $.md5(password);
            var data = {};
            if(type==1){//注册
            	url = "/expressage_api/rbac/sys/expressageUser/saveUserWX";
            	data = {
                        phone:phone,
                        password:hash,
                        code:code,
                        wxcode:wxcode
                    };
            }else{//修改密码
            	url = "/expressage_api/rbac/sys/expressageUser/changePassward";
            	data={
                        phone:phone,
                        password:hash,
                        code:code,
                        type:1
                    };
            }
              $.ajax({
                  url: url,
                  type:"post",
                  data:data,
                  success:function(data){
                	  data = JSON.parse(data);
                      if(data.code == "000000"){
                    	  layer.msg(data.message);
                    	  setTimeout(function(){
                    		  window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                          },1500);
                      }else{
                    	  layer.msg(data.message);
                      }
                  },
                  error:function(){
                  	
                  }
              });
          }
      });
      //修改密码
      $("#changepwbtn").on('click',function(){
    	  var Request = new Object();
  		  Request = GetRequest();
  		  var wxcode = Request["code"];
    	  var password = $(".registerPage input[name=oldPw]").val();
  		  var newPassword = $(".registerPage input[name=password]").val();
  		if(!wxcode){
            layer.msg("无法获取code");
        }else if(password.length < 1 || newPassword.length<1){
            layer.msg("请输入密码");
        }else if(password.length < 6 || password.length > 16 || newPassword.length < 6 || newPassword.length > 16){
            layer.msg("请输入6-20位的登录密码");
        }else{
            password = $.md5(password);
        	newPassword = $.md5(newPassword);
        	$.ajax({
                url: "/expressage_api/rbac/sys/expressageUser/updatePassword",
                type:"post",
                data:{
          			channel:"wx",
          			password:password,
          			type:"1",
          			code:wxcode,
          			newPassword:newPassword
          	    },
                dataType:"json",
                success:function(data){
                	  console.log(data);
                    if(data.code == "000000"){
                  	  layer.msg("密码修改成功,请登录");
                  	  setTimeout(function(){
                  		  window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb29f8c9e9415156f&redirect_uri=http://www.kuaidikd.com/expressage_api/code/login.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                        },1500);
                    }else{
                  	  layer.msg(data.message);
                    }
                },
                error:function(){
                	
                }
            });
        }
  		
    	  
    	  
      })
      
//      注销登录
      $("#logout").click(function(){
              $.ajax({
                  url: "/expressage_api/rbac/sys/expressageUser/logout",
                  type:"post",
                  data:{},
                  success:function(data){
                	  data = JSON.parse(data);
                  	  console.log(data)
                      if(data.code == 000000){
                    	$.cookie("userID",null,{path: '/expressage_api',expires: -1});
      					location.href="login.html";
                      }else{
                      		
                      }
                  },
                  error:function(){
                  	
                  }
              });
      });
      
      
//      忘记密码
      
      
      
})
