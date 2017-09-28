var fields = ["id","serviceTypeName","summary"];

var spServiceTypeStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/iptv/sp/spServiceType!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var spServiceTypeReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
    {name:"id",mapping:"id"},
{name:"serviceTypeName",mapping:"serviceTypeName"},
{name:"summary",mapping:"summary"}
]);