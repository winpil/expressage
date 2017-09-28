var fields = [
"id",
"moduleName",
"logType",
"operAccount",
"operStatus",
"clientIp",
"createdtime"];

var sysLogStore = new Ext.data.JsonStore({
			root : "result",
			url : path+"/rbac/sysLog!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var sysLogReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
    {name:"id",mapping:"id"},
{name:"moduleName",mapping:"moduleName"},
{name:"logType",mapping:"logType"},
{name:"operAccount",mapping:"operAccount"},
{name:"operDetail",mapping:"operDetail",convert: function(v){
	return v.replace(/<br>/g,"\n");
}},
{name:"operStatus",mapping:"operStatus"},
{name:"clientIp",mapping:"clientIp"},
{name:"createdtime",mapping:"createdtime"}
]);