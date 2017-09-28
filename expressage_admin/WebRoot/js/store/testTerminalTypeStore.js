var fields = ["id", "typeName", "sortIndex", "status","summary"];

var testTerminalTypeStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/terminal/testTerminalType.action",
			totalProperty : "totalCount",
			fields : ["id", "typeName", "sortIndex", "status","summary"]
});