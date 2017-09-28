var fields = ["id", "userAccount", "ip", "userName", "mobile", "epgid", "address", "userType", "platformType", "status", "createdtime", "modifiedtime"]

var iptvUserStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/iptv/epg/iptvUser!list.action",
			totalProperty : "totalCount",
			fields : fields
});
		
var iptvUserReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"id",mapping:"id"},
	{name:"userAccount",mapping:"userAccount"},
	{name:"ip",mapping:"ip"},
	{name:"userName",mapping:"userName"},
	{name:"mobile",mapping:"mobile"},
	{name:"epgid",mapping:"epgid"},
	{name:"address",mapping:"address"},
	{name:"userType",mapping:"userType"},
	{name:"platformType",mapping:"platformType"},
	{name:"status",mapping:"status"},
	{name:"createdtime",mapping:"createdtime"},
	{name:"modifiedtime",mapping:"modifiedtime"}
]);