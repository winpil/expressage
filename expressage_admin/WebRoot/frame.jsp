<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cndatacom.rbac.pojo.SysMenu" %>
<%@ page import="com.cndatacom.rbac.pojo.SysUser"%>
<%@ page import="com.cndatacom.rbac.system.service.ISysMenuService" %>
<%@ page import="com.cndatacom.rbac.system.service.ISysRoleService" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.cndatacom.common.utils.SpringSecurityContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
            ISysMenuService sysMenuService = (ISysMenuService) ctx.getBean("sysMenuService");
            ISysRoleService sysRoleService = (ISysRoleService) ctx.getBean("sysRoleService");

            String menuConfig = sysMenuService.getAuthoritySysMenuConfig(session, "1");
        	// 登录用户
        	SysUser user = SpringSecurityContextUtils.getLoginUser();
        	//如果是leader就直接跳转到门户
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>快递快滴管理平台</title>

 <jsp:include page="/common/common.jsp"></jsp:include>

        <script>
            var navTitle="";
            var menuConfig=<%out.println(menuConfig);%>
            function getMenuConfig(){return menuConfig;}
            function switchSysBar(cols){document.getElementById("middleFrame").cols=cols;}
            //更新导航文字标题
            function setNavTitle(title){
                navTitle=title;
            }

            function getNavTitle(){
                return navTitle;
            }
            
        </script>
    </head>

    <frameset rows="85,*" cols="*" frameborder="no" border="0" framespacing="0">
        <frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
        <frameset  cols="168,*" frameborder="no" border="0" framespacing="0" id="middleFrame" name="middleFrame">
            <frame src="left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" />
            <frame src="main.jsp" name="centerpageid" id="centerpageid" scrolling="auto" style="overflow:visible;"/>
        </frameset>
        
    </frameset>
    <noframes>
    </noframes>
    <body>
    </body>
</html>