var fields = ["userid", "username", "name", "email",
		"phone", "mobilePhone", "createUserid","extPhone","note","enName",
		"createdDate", "isUse"]

var globalSysUserStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/sysregister/globalSysUser!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var globalSysUserReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"userid",mapping:"userid"},
	{name:"username",mapping:"username"},
	{name:"name",mapping:"name"},
	{name:"email",mapping:"email"},
	{name:"phone",mapping:"phone"},
	{name:"mobilePhone",mapping:"mobilePhone"},
	{name:"createUserid",mapping:"createUserid"},
	{name:"createdDate",mapping:"createdDate"},
	{name:"extPhone",mapping:"extPhone"},
	{name:"enName",mapping:"enName"},
	{name:"note",mapping:"note"},
	{name:"isUse",mapping:"isUse"}
]);