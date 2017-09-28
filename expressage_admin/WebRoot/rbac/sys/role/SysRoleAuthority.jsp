<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
	<head>
		<jsp:include page="/common/common.jsp" />
		<title>权限配置</title>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/extjs/checkboxtree/Ext.lingo.JsonCheckBoxTree.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/extjs/checkboxtree/Ext.lingo.JsonCheckBoxTree.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/store/sysRoleStore.js"></script>
		<script type="text/javascript">
		Ext.BLANK_IMAGE_URL = path + "/extjs/resources/images/default/s.gif";
		Ext.QuickTips.init();
		var pageSize = 20;
		var checkedSysAuthoritiesData;
		Ext.onReady(function() {
			updateAuthorites();
		});
		//配置权限
		function updateAuthorites() {
			var roleId = "${roleId}";
			var authorityTree = new Ext.tree.TreePanel( {
				autoScroll : true,
				animate : true,
				loader : new Ext.tree.CustomUITreeLoader( {
					dataUrl : path + '/rbac/sys/sysRole!getSysAuthorities.action',
					baseAttr : {
						uiProvider : Ext.tree.CheckboxNodeUI
					}
				}),
				enableDD : false,
				containerScroll : true,
				rootUIProvider : Ext.tree.CheckboxNodeUI,
				selModel : new Ext.tree.CheckNodeMultiSelectionModel(),
				rootVisible : false
			});
			authorityTree.getLoader().on('load', function(o, node) {
				if (node.isRoot) {
					authorityTree.expandAll();
				}
			});
			// 设置根节点
			var root = new Ext.tree.AsyncTreeNode( {
				text : 'root',
				draggable : false
			});
			authorityTree.setRootNode(root);
			var authorityWin = new Ext.Window(
					{
						title : '${sysRole.roleName}-角色权限配置',
						layout : 'fit',
						height : 400,
						width : 550,
						modal : true,
						closable : false,
						resizable : false,
						draggable : false,
						buttonAlign : "center",
						closeAction : 'hide',
						items : [ authorityTree ],
						buttons : [
								{
									text : "保存",
									// iconCls : 'save',
									handler : function() {
										var ids = '';
										selNodes = authorityTree.getChecked();
										
										Ext.each(selNodes, function(node) {
											if (ids.length > 0
													&& node.id.indexOf("node") < 0) {
												ids += ',';
											}
											if (node.id.indexOf("node") < 0) {
												ids += node.id;
											}
										});
		
										Ext.Ajax.request( {
													url : path + '/rbac/sys/sysRole!editSysAuthorities.action',
													success : function(resp) {
														var respText = Ext.util.JSON.decode(resp.responseText);
														top.Ext.Msg.alert(
																		"友情提示",
																		respText.msg+ "<br/>",
																		function() {
																			authorityTree.getLoader().load(authorityTree.getRootNode());
																			window.close();
																		});
													},
													failure : function() {},
													params : 'ids=' + ids + "&roleId="+ roleId
												});
									}
								}, {
									text : "取消",
									handler : function() {
										authorityTree.destroy();
										window.close();
									}
								} ]
					});
		
			authorityWin.on('hide', function() {
				authorityTree.destroy();
				authorityWin.close();
			});
		
			authorityTree.getLoader().baseParams.roleId = roleId;
			authorityWin.show();
		}
		</script>
	</head>
	<body>
		<div id="sysRoleGrid"></div>
	</body>
</html>