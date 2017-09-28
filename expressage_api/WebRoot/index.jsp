<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
      
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath%>jquery-1.7.2.js" type="text/javascript"></script>
    <script type="text/javascript">  
     				
		function xiaozhu(){
			alert("1");
			$.ajax( {
				url : "/expressage_api/rbac/sys/sysBase/testBase",
				data : "baseId=getCompanys",
				type : "post",
				dataType : "json",
				success : function(data) {
					alert(data);
				},error : function() {
					alert("错误");
				}
			});
		}
    </script>  
  </head>
  
  <body>
    <a href="#" onclick="xiaozhu();">点击事件</a>
  </body>
</html>
