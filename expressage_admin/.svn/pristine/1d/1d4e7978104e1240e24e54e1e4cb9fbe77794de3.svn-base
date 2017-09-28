var fields = ["businessId", "businessName", "scoreTypeid", "businessNote",
		"businessStatus", {
			name : "scoreRules",
			mapping : "scoreRules"
		}];

var scoreBusinessStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/imusic/score/scoreBusiness!list.action",
			totalProperty : "totalCount",
			fields : fields
});

var scoreBusinessReader = new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCount"
		}, [{
					name : "businessId",
					mapping : "businessId"
				}, {
					name : "businessName",
					mapping : "businessName"
				}, {
					name : "scoreTypeid",
					mapping : "scoreTypeid"
				}, {
					name : "businessNote",
					mapping : "businessNote"
				}, {
					name : "businessStatus",
					mapping : "businessStatus"
				}]
);