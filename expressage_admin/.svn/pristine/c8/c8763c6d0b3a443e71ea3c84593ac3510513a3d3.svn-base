var fields = [ "roleId", "roleName", "roleNote" ];

var sysRoleStore = new Ext.data.JsonStore( {
	root : "result",
	url : path + "/rbac/sysRole!list.action",
	totalProperty : "totalCount",
	fields : fields
});

var sysAuthorityReader = new Ext.data.JsonReader( {
	root : "result",
	totalProperty : "totalCount"
}, [ {
	name : "roleId",
	mapping : "roleId"
}, {
	name : "roleName",
	mapping : "roleName"
}, {
	name : "roleNote",
	mapping : "roleNote"
} ]);