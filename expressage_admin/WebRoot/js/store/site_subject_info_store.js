var subjectinfoReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"subjectId",mapping:"subjectId"},
	{name:"subjectTitle",mapping:"subjectTitle"},
	{name:"subjectNote",mapping:"subjectNote"},
	{name:"templateId",mapping:"template",convert:function(v){
		if(v){
			return v.templateId;
		}
		return "";
	}},
	{name:"templateName",mapping:"template",convert:function(v){
		if(v){
			return v.templateName;
		}
		return "";
	}},
	{name:"subjectImg",mapping:"subjectImg"},
	{name:"adContentStatus",mapping:"adContentStatus"},
	{name:"createdDate",mapping:"createdDate"},
	{name:"modifiedDate",mapping:"modifiedDate"},
	{name:"subjectLeftImg",mapping:"subjectLeftImg"},
	{name:"subjectCode",mapping:"subjectCode"},
	{name:"subjectUrl",mapping:"subjectUrl"}
]);

var subjectinfoStore = new Ext.data.Store({
	proxy :new Ext.data.HttpProxy({url:path + "/musicManager/siteSubjectInfo!list.action"}),
	reader : subjectinfoReader
});

