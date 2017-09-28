var fields = [
"id",
"orderNum",
"userAccount",
"productId",
"productName",
"exchange",
"starteffecttime",
"endeffecttime",
"effectcount",
"isnext",
"createdtime",
"modifiedtime"];

var orderInfoStore = new Ext.data.JsonStore({
			root : "result",
			url : path+"/iptv/oms/orderInfo!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var orderInfoReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
    {name:"id",mapping:"id"},
{name:"orderNum",mapping:"orderNum"},
{name:"userAccount",mapping:"userAccount"},
{name:"productId",mapping:"productId"},
{name:"productName",mapping:"productName"},
{name:"exchange",mapping:"exchange"},
{name:"starteffecttime",mapping:"starteffecttime"},
{name:"endeffecttime",mapping:"endeffecttime"},
{name:"effectcount",mapping:"effectcount"},
{name:"isnext",mapping:"isnext"},
{name:"createdtime",mapping:"createdtime"},
{name:"modifiedtime",mapping:"modifiedtime"}
]);