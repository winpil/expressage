<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.cndatacom.rbac.pojo.SysGroup" %>
<%@ page import="com.cndatacom.rbac.pojo.SysUser"%>
<%@ page import="com.cndatacom.rbac.system.service.ISysGroupService" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.cndatacom.rbac.system.service.ISysUserService"%>
<%@ page import="com.cndatacom.rbac.system.service.ISysGroupService"%>
<%
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	SysUser loginUser = null;

	try{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		ISysUserService sysUserService = (ISysUserService)ctx.getBean("tsysUserService");

		loginUser = sysUserService.findUniqueByUsername(username);
	}catch(Exception e){
	}
	String groupId = loginUser.getSysGroup().getGroupId();

	try{
	    if(request.getParameter("groupId")!=null&&!"".equals(request.getParameter("groupId")))
	    {
			groupId = request.getParameter("groupId");
		}
	}catch(Exception e){
	}

	ISysGroupService sysGroupService = (ISysGroupService)ctx.getBean("sysGroupService");
	SysGroup sysGroup = sysGroupService.get(groupId);
%>

<html>
	<head>
		<title>组织架构管理</title>
		<jsp:include page="/common/common.jsp" />
		<script type='text/javascript'
			src='${pageContext.request.contextPath}/dwr/interface/sysGroupService.js'></script>
		<script type="text/javascript">
        var tree;
        Ext.onReady(function() {
            Ext.BLANK_IMAGE_URL = path + "/extjs/resources/images/default/s.gif";
            new Ext.Viewport({
                renderTo:"view-div",
                layout:"border",
                id:"view",
                items:[
                    {
                        region:"center", width:150, title:"组织架构菜单",
                        id:"menutree", split:true, titleCollapse:true
                    }
                ]
            });
            var treeLoader = new Ext.tree.DWRTreeLoader({
                dwrCall:sysGroupService.getSysGroupByParentId
            });

            var Tree = Ext.tree;
            tree = new Tree.TreePanel({
                el:"menutree", id:"showmenutree", autoScroll:true, animate:true,
                enableDD:true, containerScroll: true, loader:treeLoader
            });

            var root = new Ext.tree.AsyncTreeNode({
                text:'<%=sysGroup.getGroupName()%>',
                draggable:false,
                id:"<%=sysGroup.getGroupId()%>"
            });

            tree.on("click", function(node) {
                var menulist = parent.frames['menulist'];
                menulist.location.href = "sysGroup!list.action?parentId=" + node.id;
            });
            tree.setRootNode(root);
            tree.render();
            root.expand();
        });

        //重新加载系统菜单tree
        function reloadFunctionTree() {
            tree.root.reload();
        }
    </script>
	</head>
	<body>
		<div id="view-div"></div>
	</body>
</html>