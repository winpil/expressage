<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>注销页面</title>
  </head>
  <body>
  	<form id="logout"></form>
    <script type="text/javascript">
    	var oLogin = document.getElementById("logout");

    	if(parent.window != window){
			with(oLogin) {
				action = '${pageContext.request.contextPath}/j_spring_security_logout';
				target = '_top';
				submit();
			}
        }
    </script>
  </body>
</html>
