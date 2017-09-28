<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			html {
				height: 100%;
			}
			
			body {
				font-size: 12px;
				margin: 0px;
				padding: 0px;
				text-align: center;
			}
		</style>
		<script type="text/javascript">
            function getNavTitle(){
                return parent.getNavTitle();
            }
        </script>

	</head>
	<body style="height: 100%;">
		<div id="left" style="float: left; width: 80%; height: 100%">
			<iframe id="citylist" name="citylist" src="sysCity!list.action?id=1"
				style="width: 100%; height: 98%" frameborder="0"></iframe>
		</div>

		<div  id="right" style="float: right; width: 20%; height: 100%">
			<iframe id="citytree"  name="citytree" src="sysCity!cityTree.action" frameborder="0"
				style="width: 100%; height: 98%" />
		</div>
	</body>
</html>