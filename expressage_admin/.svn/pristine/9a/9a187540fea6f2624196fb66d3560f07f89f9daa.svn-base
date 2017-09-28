var fields = ["contentId", "contentTitle", "contentTypeId",
			"contentKeyword", "contentImage", "contentAuthor", "contentStatus", "contentNote","isRecommend",
			"contentSource", {name:"expirationDate",mapping:"expirationDate",convert : function(v) {
	                    	if(v!=null){
	              				  return Date.parseDate(v, "Y-m-d").between(Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 1),Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 10))?"<font color='red'>"+v+"</font>":v;
	                    	}else{
	                    		return '';
	                    	}
           				 }}];

var contentInfoStore = new Ext.data.JsonStore({
				root : "result",
				url : path + "/iptv/manager/contentInfo!list.action",
				totalProperty : "totalCount",
				fields : fields
});

var contentInfoReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"contentId",mapping:"contentId"},
	{name:"contentTitle",mapping:"contentTitle"},
	{name:"contentTypeId",mapping:"contentTypeId"},
	{name:"contents",mapping:"contents"},
	{name:"contentKeyword",mapping:"contentKeyword"},
	{name:"contentImage",mapping:"contentImage"},
	{name:"contentAuthor",mapping:"contentAuthor"},
	{name:"contentStatus",mapping:"contentStatus"},
	{name:"contentNote",mapping:"contentNote"},
	{name:"contentSource",mapping:"contentSource"},
	{name:"expirationDate",mapping:"expirationDate"},
	{name:"isRecommend",mapping:"isRecommend"}
]);