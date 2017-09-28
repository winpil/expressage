var sietsubjectitemReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"subjectitemId",mapping:"subjectitemId"},
	{name:"subjectId",mapping:"subjectId"},
	{name:"subjectitemCode",mapping:"subjectitemCode"},
	{name:"subjectitemNote",mapping:"subjectitemNote"},
	{name:"templateitemId",mapping:"templateitemId"},
	{name:"subjectitemImgPath",mapping:"subjectitemImgPath"},
	{name:"subjectitemText",mapping:"subjectitemText"},
	{name:"videoPath",mapping:"videoPath"},
	{name:"musicRingid",mapping:"musicRingid"},
	{name:"createdDate",mapping:"createdDate"},
	{name:"modifiedDate",mapping:"modifiedDate"}
]);

var sitesubjectitemStore = new Ext.data.Store({
	proxy :new Ext.data.HttpProxy({url:path + "/musicManager/siteSubjectItem!list.action"}),
	reader : sietsubjectitemReader
});