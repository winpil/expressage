var musictoneboxReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"toneboxId",mapping:"toneboxId"},
	{name:"toneboxCode",mapping:"toneboxCode"},
	{name:"toneboxName",mapping:"toneboxName"},
	{name:"toneboxPrice",mapping:"toneboxPrice"},
	{name:"expirationDate",mapping:"expirationDate",convert : function(v) {
            	if (v != null) {
							return Date.parseDate(v, "Y-m-d").
									between(Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 1),Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 10))
									?"<font color='red'>"+v+"</font>":v;
						} else {
							return '';
						}
					} 
	},
	{name:"toneboxNote",mapping:"toneboxNote"},
	{name:"toneboxStatus",mapping:"toneboxStatus"},
	{name:"createdDate",mapping:"createdDate"},
	{name:"modifiedDate",mapping:"modifiedDate"}
]);

var musictoneboxStore = new Ext.data.Store({
	proxy :new Ext.data.HttpProxy({url:path + "/musicManager/musicTonebox!list.action"}),
	reader : musictoneboxReader
});