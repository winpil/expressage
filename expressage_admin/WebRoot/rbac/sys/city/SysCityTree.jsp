<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@page import="com.cndatacom.rbac.pojo.SysCity" %>
<%@page import="com.cndatacom.rbac.system.service.ISysCityService" %>
<%
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ISysCityService sysCityService = (ISysCityService)ctx.getBean("sysCityService");
    SysCity rootCity = sysCityService.get("1");
%>

<html>
<head>
    <title>地市管理</title>
    <jsp:include page="/common/common.jsp" />
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/sysCityService.js'></script>
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
                        id:"citytree", 
                        split:true, 
                        titleCollapse:true
                    }
                ]
            });
            var treeLoader = new Ext.tree.DWRTreeLoader({
                dwrCall : sysCityService.getSysCityByParentId
            });

            var Tree = Ext.tree;
            tree = new Tree.TreePanel({
                el:"citytree", id:"showcitytree", autoScroll:true, animate:true,
                enableDD:true, containerScroll: true, loader:treeLoader
            });

            var root = new Ext.tree.AsyncTreeNode({
                text:'<%=rootCity.getCityName()%>',
                draggable:false,
                id:"<%=rootCity.getId()%>"
            });
            
            tree.on("click", function(node) {
                var citylist = parent.frames['citylist'];
                citylist.location.href = "sysCity!list.action?id="+node.id;
            });
            tree.setRootNode(root);
            tree.render();
            root.expand();
        });

        //重新加载系统地市tree  
        function reloadFunctionTree() {
            tree.root.reload();
        } 
    </script>
</head>
<body>
<div id="view-div"></div>
</body>
</html>