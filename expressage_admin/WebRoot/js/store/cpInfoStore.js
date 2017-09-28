var pageSize = 20;

var fields = ["cpid", "cpCode", "cpName", "cpBusinesslicensenumber",
			  "cpTaxnumber","cpBankaccount","cpAddress", "cpMobile",
			  "cpBusinessType", "cpCreditRatings", "cpServerPlace", 
			  "cpDomainName", "expirationDate", "cpStatus", "createdDate", 
			  "modifiedDate"];

var cpInfoStore = new Ext.data.JsonStore({
			root : "result",
			url :path + "/imusic/cpmanager/cpInfo!list.action",
			baseParams : {
				limit : pageSize
			},
			totalProperty : "totalCount",
			fields : fields
});

var cpInfoReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"cpid",mapping:"cpid"},
	{name:"cpCode",mapping:"cpCode"},
	{name:"cpName",mapping:"cpName"},
	{name:"cpBusinesslicensenumber",mapping:"cpBusinesslicensenumber"},
	{name:"cpTaxnumber",mapping:"cpTaxnumber"},
	{name:"cpBankaccount",mapping:"cpBankaccount"},
	{name:"cpAddress",mapping:"cpAddress"},
	{name:"cpMobile",mapping:"cpMobile"},
	{name:"cpBusinessType",mapping:"cpBusinessType"},
	{name:"cpCreditRatings",mapping:"cpCreditRatings"},
	{name:"cpServerPlace",mapping:"cpServerPlace"},
	{name:"cpDomainName",mapping:"cpDomainName"},
	{name:"expirationDate",mapping:"expirationDate"},
	{name:"cpStatus",mapping:"cpStatus"},
	{name:"createdDate",mapping:"createdDate"},
	{name:"modifiedDate",mapping:"modifiedDate"}
]);