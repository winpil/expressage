var fields = [
"id",
"typeName",
"summary"
];

var productTypeStore = new Ext.data.JsonStore({
			root : "result",
			url : path+"/iptv/product/productType!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var productTypeReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
    {name:"id",mapping:"id"},
{name:"typeName",mapping:"typeName"},
{name:"summary",mapping:"summary"}

]);