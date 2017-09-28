<%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.cndatacom.rbac.pojo.SysGroup"%>
<%@ page import="com.cndatacom.rbac.pojo.SysUser"%>
<%@ page import="com.cndatacom.rbac.system.service.ISysGroupService"%>
<%@ page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.cndatacom.rbac.system.service.ISysUserService"%>
<%@ page import="com.cndatacom.rbac.system.service.ISysGroupService"%>
<%
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	SysUser loginUser = null;
	try {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ISysUserService sysUserService = (ISysUserService) ctx.getBean("tsysUserService");
		loginUser = sysUserService.findUniqueByUsername(username);
	} catch (Exception e) {}
	
	String groupId = loginUser.getSysGroup().getGroupId();
	try {
		if (request.getParameter("groupId") != null
				&& !"".equals(request.getParameter("groupId"))) {
			groupId = request.getParameter("groupId");
		}
	} catch (Exception e) {}
	ISysGroupService sysGroupService = (ISysGroupService) ctx.getBean("sysGroupService");
	SysGroup sysGroup = sysGroupService.get(groupId);
%>
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
		function getNavTitle() {
			return parent.getNavTitle();
		}
		</script>

	</head>
	<body style="height: 100%;">
		<div id="left" style="float: left; width: 100%; height: 100%">
			<iframe id="menulist" name="menulist"
				src="sysUser!list.action?groupId=<%=groupId%>"
				style="width: 100%; height: 98%" frameborder="0"></iframe>
		</div>

		<%--		<div  id="right" style="float: right; width: 20%; height: 100%">
			<iframe id="menutree"  name="menutree" src="sysUser!groupTree.action" frameborder="0" style="width: 100%; height: 98%" />
		</div> --%>
	</body>
</html>