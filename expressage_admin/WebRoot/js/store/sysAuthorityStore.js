var fields = ["authorityId", "authorityType", "authorityUrl", "authorityName",
		"authorityNote"];

var sysAuthorityStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/rbac/sysAuthority!list.action",
			totalProperty : "totalCount",
			fields : fields
		});

var sysAuthorityReader = new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCount"
		}, [{
					name : "authorityId",
					mapping : "authorityId"
				}, {
					name : "authorityType",
					mapping : "authorityType"
				}, {
					name : "authorityUrl",
					mapping : "authorityUrl"
				}, {
					name : "sysMenuId",
					mapping : "sysMenu",
					convert : function(v) {
						if (v != null) {
							return v.id;
						} else {
							return '';
						}
					}
				}, {
					name : "authorityName",
					mapping : "authorityName"
				}, {
					name : "authorityNote",
					mapping : "authorityNote"
				}]);