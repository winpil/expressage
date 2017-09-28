var fields = ["complainId", "complainContent", "complainLevel",
			"complainPhone", "complainNote", "complainStatus","resolveStatus","handleName",
			"complainAccountName", {
				name : "username",
				convert : function(v) {
					if (v) {
						return "<font color='red'>" + v + "</font>";
					} else {
						return "未锁定"
					}
				}
			}, "createdDate"];

var complainInfoStore = new Ext.data.JsonStore({
				root : "result",
				url : path + "/imusic/customservice/complain/complainInfo!list.action",
				totalProperty : "totalCount",
				fields : fields
			});
		
var complainInfoReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[{name:"complainId",mapping:"complainId"},{name:"complainContent",mapping:"complainContent"},{name:"complainLevel",mapping:"complainLevel"},
			{name:"complainPhone",mapping:"complainPhone"},{name:"complainNote",mapping:"complainNote"},{name:"complainStatus",mapping:"complainStatus"},{name:"resolveStatus",mapping:"resolveStatus"},{name:"handleName",mapping:"handleName"}
			,{name:"complainAccountName",mapping:"complainAccountName"},{name:"username",mapping:"username"},{name:"createdDate",mapping:"createdDate"}]);