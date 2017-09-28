var pageSize = 20;

var fields = ["accountId", "account", "name",
			"email", "address","telephone","sex","mobile","vnetAccount","smsAccount","createdDate"];

var accountInfoStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/imusic/webmanager/member/accountInfo!list.action",
			baseParams : {
				limit : pageSize
			},
			totalProperty : "totalCount",
			fields : fields
});