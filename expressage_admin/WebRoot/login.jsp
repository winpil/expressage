<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter"%>
<%@ page import="org.springframework.security.core.AuthenticationException"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script language="javascript">
		
	// 让光标默认停留在用户名的输入框
	function setFocus() {
 		document.getElementById("j_username").focus();
	}
	
	// 检验用户名密码验证码是否为空
	function check(){
	
		var username = document.getElementById("j_username");
		var password = document.getElementById("j_password");
		var code = document.getElementById("random");
		var check = document.getElementById("check");
		session.setAttribute("username",username);
		if(username.value == "") {
			// alert("请输入用户名！");
			check.innerHTML = "请输入用户名！";
			username.focus();
			return false;
		}
		if(password.value == "") {
			// alert("请输入密码！");
			check.innerHTML = "请输入密码！";
			password.focus();
			return false;
		}
		if(code.value == "") {
			// alert("请输入验证码！");
			check.innerHTML = "请输入验证码！";
			code.focus();
			return false;
		}
		return true;
	}
	
	// 点击验证码进行刷新 
	function check_image(){ 
	    var img = document.getElementById("pic"); 
	    img.src = "image.jsp?" + Math.random(); 
	}
	
	// 图片的重置功能
	function remove() {
		document.forms['loginForm'].reset();
		document.getElementById("check").innerHTML = "";
		setFocus();
		return false;
	}
	
</script>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>系统登录页</title>
		<link href="${pageContext.request.contextPath}/style/gdcn.css" rel="stylesheet" type="text/css" />
	</head>

	<body class="CCS_loginBg" onload="setFocus();">
		<form id="login"></form>
		<script type="text/javascript">
    	var oLogin = document.getElementById("login");

    	if(parent.window != window){
			with(oLogin) {
				action = '${pageContext.request.contextPath}/login.jsp';
				target = '_top';
				submit();
			}
        }
    </script>
		<center>

			<form id="loginForm" name="loginForm" 
				action="${pageContext.request.contextPath }/j_spring_security_check"
				method="post">

				<div>
					<div class="CCS_loginBox">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							style="margin-top: 3px;">
							<tr>
								<td width="18%" height="35">
									<label>
										用户名：
									</label>
								</td>
								<td>
									<input type="text" id="j_username" name="j_username"
										class="CCS_input2" style="width: 300px;" value="${username}" />
								</td>
							</tr>

							<tr>
								<td height="35">
									<label>
										密码：
									</label>
								</td>
								<td>
									<input type="password" id="j_password" name="j_password"
										class="CCS_input2" style="width: 300px;" />
								</td>
							</tr>

							<tr>
								<td height="35">
									验证码：
								</td>
								<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="34%">
												<input type="text" id="random" name="random" maxlength=4
													class="CCS_input2" style="width: 110px;" size="10"
													onkeyup="value=value.replace(/[^\d]/g,'') "
													onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
											</td>
											<td width="19%">
												<img id="pic" border=1 src="image.jsp?,Math.random();" alt="验证码"
													onclick="return check_image();" title="看不清？点击更换">
											</td>
											<td width="47%" class="CCS_loginTip1">
												<a href="#" class="CCS_blue" onclick="return check_image();">
													点击这里更换验证码
												</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td colspan=2 height="20">
									<div id="check" style="color: red;">
										<%
											if (null != request.getAttribute("codeError")) {
										%>
											<%=request.getAttribute("codeError") %>
										<%
											} else if (session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
										%>
											<%=((AuthenticationException)session.getAttribute
													(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
										<%
											}
										%>
									</div>
								</td>
							</tr>

						</table>
						
						<div class="CCS_loginButton">
							<input type="image" name="image1" src="images/login/login_09.gif" onclick="return check();" />
						</div>
						<div class="CCS_loginButton">
							<input type="image" name="image2" src="images/login/login_10.gif" onclick="return remove()" />
						</div>
						
						<div class="CCS_loginCopyright">
							CopyRight&copy; 2011<strong> 中国广东电信研究院 </strong>版权所有
						</div>
					</div>
				</div>

			</form>
		</center>
	</body>
</html>
