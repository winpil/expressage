var fields = ["newsId", "newsTitle", "newsTypeId", "newsContent",
			"newsKeyword", "newsImage", "newsAuthor", "newsStatus", "newsNote","isRecommend",
			"newsSource", {name:"expirationDate",mapping:"expirationDate",convert : function(v) {
	                    	if(v!=null){
	              				  return Date.parseDate(v, "Y-m-d").between(Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 1),Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 10))?"<font color='red'>"+v+"</font>":v;
	                    	}else{
	                    		return '';
	                    	}
           				 }}];

var newsInfoStore = new Ext.data.JsonStore({
				root : "result",
				url : path + "/imusic/webmanager/news/newsInfo!list.action",
				totalProperty : "totalCount",
				fields : fields
});

var newsInfoReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"newsId",mapping:"newsId"},
	{name:"newsTitle",mapping:"newsTitle"},
	{name:"newsTypeId",mapping:"newsTypeId"},
	{name:"newsContent",mapping:"newsContent"},
	{name:"newsKeyword",mapping:"newsKeyword"},
	{name:"newsImage",mapping:"newsImage"},
	{name:"newsAuthor",mapping:"newsAuthor"},
	{name:"newsStatus",mapping:"newsStatus"},
	{name:"newsNote",mapping:"newsNote"},
	{name:"newsSource",mapping:"newsSource"},
	{name:"expirationDate",mapping:"expirationDate"},
	{name:"isRecommend",mapping:"isRecommend"}
]);