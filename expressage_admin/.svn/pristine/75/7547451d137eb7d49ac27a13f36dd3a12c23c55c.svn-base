var fields = ["adContentId", "adContentName", "adContentContent", "adTypeId",
		"adContentHint", "adContentWidth", "adContentOpenType",
		"adContentHeight", {
			name : "adSlotId",
			mapping : "adSlot",
			convert : function(v) {
				if (v) {
					return v.adSlotId;
				} else {
					return "";
				}
			}
		},{
			name:"adSlotName",mapping:"adSlot",
			convert:function(v){
				if(v){
					return v.adSlotName;
				}else{
					return "";
				}
			}
		}, "adContentLinkUrl", {name:"expirationDate",mapping:"expirationDate",convert : function(v) {
	                    	if(v!=null){
	              				  return Date.parseDate(v, "Y-m-d").between(Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 1),Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 10))?"<font color='red'>"+v+"</font>":v;
	                    	}else{
	                    		return '';
	                    	}
           				 }}, "adContentStatus", "adContentNote","adContentTypeId"]

var adContentStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/imusic/webmanager/ad/adContent!list.action",
			totalProperty : "totalCount",
			fields : fields
});
		
var adContentReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"adContentId",mapping:"adContentId"},
	{name:"adContentName",mapping:"adContentName"},
	{name:"adContentContent",mapping:"adContentContent"},
	{name:"adTypeId",mapping:"adTypeId"},
	{name:"adContentHint",mapping:"adContentHint"},
	{name:"adContentWidth",mapping:"adContentWidth"},
	{name:"adContentOpenType",mapping:"adContentOpenType"},
	{name:"adContentHeight",mapping:"adContentHeight"},
	{name:"adContentLinkUrl",mapping:"adContentLinkUrl"},
	{name:"expirationDate",mapping:"expirationDate"},
	{name:"adContentStatus",mapping:"adContentStatus"},
	{name:"adContentNote",mapping:"adContentNote"},
	{
			name : "adSlotId",
			mapping : "adSlot",
			convert : function(v) {
				if (v) {
					return v.adSlotId;
				} else {
					return "";
				}
			}
	},{
			name:"adSlotName",mapping:"adSlot",
			convert:function(v){
				if(v){
					return v.adSlotName;
				}else{
					return "";
				}
			}
	},{name:"adContentTypeId",mapping:"adContentTypeId"},
	{name:"adContentImage",mapping:"adContentImage"}
]);