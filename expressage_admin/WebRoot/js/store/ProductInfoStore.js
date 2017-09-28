var fields = [
"id",
{
			name : "banName",
			mapping : "balanceType",
			convert : function(v) {
	            
				if (v) {
					return v.balanceName;
				} else {
					return "";
				}
			}
},
{
			name : "productTypeName",
			mapping : "productType",
			convert : function(v) {
				if (v) {
					return v.typeName;
				} else {
					return "";
				}
			}
},

"productName",
"iconPath",
"summary",
"status",
"createdtime",
"modifiedtime",
"starteffecttime",
"endeffecttime"];

var productInfoStore = new Ext.data.JsonStore({
			root : "result",
			url : path+"/iptv/product/productInfo!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var productTypeStore = new Ext.data.JsonStore({
			root : "result",
			url : path+"/iptv/product/productType!list.action",
			totalProperty : "totalCount",
			fields : ["id", "typeName"]
});
productTypeStore.load();

var balanceTypeStore = new Ext.data.JsonStore({
			root : "result",
			url : path+"/iptv/product/balanceType!list.action",
			totalProperty : "totalCount",
			fields : ["id", "balanceName"]
});
balanceTypeStore.load();


var productInfoReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
    {name:"id",mapping:"id"},

{
			name : "banID",
			mapping : "balanceType",
			convert : function(v) {
	            
				if (v) {
					return v.id;
				} else {
					return "";
				}
			}
},
{
			name : "productTypeID",
			mapping : "productType",
			convert : function(v) {
				if (v) {
					return v.id;
				} else {
					return "";
				}
			}
},
{name:"productName",mapping:"productName"},
{name:"iconPath",mapping:"iconPath"},
{name:"summary",mapping:"summary"},
{name:"status",mapping:"status"},
{name:"createdtime",mapping:"createdtime"},
{name:"modifiedtime",mapping:"modifiedtime"},
{name:"starteffecttime",mapping:"starteffecttime"},
{name:"endeffecttime",mapping:"endeffecttime"}
]);