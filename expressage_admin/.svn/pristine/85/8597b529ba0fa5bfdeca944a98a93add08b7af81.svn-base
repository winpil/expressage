var fields = ["adSlotId", "adSlotName", "adSlotCode", "adSlotNote",
		"adSlotStatus","adSlotWidth","adSlotHeight"];

var adSlotStore = new Ext.data.JsonStore({
			root : "result",
			url :path + "/imusic/webmanager/ad/adSlot!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var adSlotReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"adSlotId",mapping:"adSlotId"},
	{name:"adSlotName",mapping:"adSlotName"},
	{name:"adSlotCode",mapping:"adSlotCode"},
	{name:"adSlotNote",mapping:"adSlotNote"},
	{name:"adSlotHeight",mapping:"adSlotHeight"},
	{name:"adSlotWidth",mapping:"adSlotWidth"},
	{name:"adSlotStatus",mapping:"adSlotStatus"}
]);