var fields = ["id","orderNum","iptvUserId","proId","exchange","starteffecttime","endeffecttime","effectcount","createdtime","modifiedtime"];

var orderHistoryStore = new Ext.data.JsonStore({
			root : "result",
			url : "orderHistory!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var orderHistoryReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
    {name:"id",mapping:"id"},
{name:"orderNum",mapping:"orderNum"},
{name:"iptvUserId",mapping:"iptvUserId"},
{name:"proId",mapping:"proId"},
{name:"exchange",mapping:"exchange"},
{name:"starteffecttime",mapping:"starteffecttime"},
{name:"endeffecttime",mapping:"endeffecttime"},
{name:"effectcount",mapping:"effectcount"},
{name:"createdtime",mapping:"createdtime"},
{name:"modifiedtime",mapping:"modifiedtime"}
]);