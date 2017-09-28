var fields = ["userId", "username","password","name","phone","email","mobilePhone","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled"];

var sysUserStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/rbac/sysUser!list.action",
			totalProperty : "totalCount",
			fields : fields
});
		
var sysUserReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"userId",mapping:"userId"},
	{name:"username",mapping:"username"},
	{name:"name",mapping:"name"},
	{name:"phone",mapping:"phone"},
	{name:"email",mapping:"email"},
	{name:"mobilePhone",mapping:"mobilePhone"},
	{name:"enableStatus",mapping:"enableStatus"},
	{name:"expirationDate",mapping:"expirationDate"},
	{name:"accountStatus",mapping:"accountStatus"}
]);