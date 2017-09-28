

var fields = [
"id",
"balanceName",
"summary"
];

var balanceTypeStore = new Ext.data.JsonStore({
			root : "result",
			url : path+"/iptv/product/balanceType!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var balanceTypeReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
    {name:"id",mapping:"id"},
{name:"balanceName",mapping:"balanceName"},
{name:"summary",mapping:"summary"}

]);