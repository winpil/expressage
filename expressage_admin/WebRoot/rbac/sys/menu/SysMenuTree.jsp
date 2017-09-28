<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cndatacom.rbac.pojo.SysMenu" %>
<%@ page import="com.cndatacom.rbac.system.service.ISysMenuService" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ISysMenuService sysMenuService = (ISysMenuService)ctx.getBean("sysMenuService");
    SysMenu rootMenu = sysMenuService.get("1");
%>

<html>
<head>
    <title>菜单管理</title>
    <jsp:include page="/common/common.jsp" />
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/sysMenuService.js'></script>
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
                        region:"center", 
                        title:"系统功能菜单",
                        id:"menutree", 
                        split:true, 
                        titleCollapse:true
                    }
                ]
            });
            var treeLoader = new Ext.tree.DWRTreeLoader({
                dwrCall:sysMenuService.getSysMenuByParentId
            });

            var Tree = Ext.tree;
            tree = new Tree.TreePanel({
                el:"menutree", id:"showmenutree", autoScroll:true, animate:true,
                enableDD:true, containerScroll: true, loader:treeLoader
            });

            var root = new Ext.tree.AsyncTreeNode({
                text:'<%=rootMenu.getMenuName()%>',
                draggable:false,
                id:"<%=rootMenu.getId()%>"
            });
            
            tree.on("click", function(node) {
                var menulist = parent.frames['menulist'];
                menulist.location.href = "sysMenu!list.action?id="+node.id;
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