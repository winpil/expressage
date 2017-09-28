var fields = ["subsystemId", "subsystemName", "subsystemUrl", "subsystemIcon",
			  "subsystemWebserviceurl","subsystemStatus","subsystemAuthName","subsystemAuthPassword","subSystemShortCode","subsystemPassPasswordtype"];

var subsystemStore = new Ext.data.JsonStore({
			root : "result",
			url :path + "/sysregister/subsystem!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var subsystemReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"subsystemId",mapping:"subsystemId"},
	{name:"subsystemName",mapping:"subsystemName"},
	{name:"subsystemUrl",mapping:"subsystemUrl"},
	{name:"subsystemIcon",mapping:"subsystemIcon"},
	{name:"subsystemWebserviceurl",mapping:"subsystemWebserviceurl"},
	{name:"subsystemAuthName",mapping:"subsystemAuthName"},
	{name:"subsystemAuthPassword",mapping:"subsystemAuthPassword"},
	{name:"subsystemPassPasswordtype",mapping:"subsystemPassPasswordtype"},
	{name:"subSystemShortCode",mapping:"subSystemShortCode"},
	{name:"subsystemStatus",mapping:"subsystemStatus"}
]);