var fields = ["id","spName","spShortName","zipcode","website","fax","spTeltphone","spEmail","spAddress","corporation","corTelephone","corEmail","regAddress","summary","regCode","spType","regCapital","capital","regDate","limitedDate","buslicImgPath","buslicImgPath2","modifiedtime","createdtime","status"];

var spInfoStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/iptv/sp/spInfo!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var spInfoReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
    {name:"id",mapping:"id"},
{name:"spName",mapping:"spName"},
{name:"spShortName",mapping:"spShortName"},
{name:"zipcode",mapping:"zipcode"},
{name:"website",mapping:"website"},
{name:"fax",mapping:"fax"},
{name:"spTeltphone",mapping:"spTeltphone"},
{name:"spEmail",mapping:"spEmail"},
{name:"spAddress",mapping:"spAddress"},
{name:"corporation",mapping:"corporation"},
{name:"corTelephone",mapping:"corTelephone"},
{name:"corEmail",mapping:"corEmail"},
{name:"regAddress",mapping:"regAddress"},
{name:"summary",mapping:"summary"},
{name:"regCode",mapping:"regCode"},
{name:"spType",mapping:"spType"},
{name:"regCapital",mapping:"regCapital"},
{name:"capital",mapping:"capital"},
{name:"regDate",mapping:"regDate"},
{name:"limitedDate",mapping:"limitedDate"},
{name:"buslicImgPath",mapping:"buslicImgPath"},
{name:"buslicImgPath2",mapping:"buslicImgPath2"},
{name:"modifiedtime",mapping:"modifiedtime"},
{name:"createdtime",mapping:"createdtime"},
{name:"status",mapping:"status"}
]);