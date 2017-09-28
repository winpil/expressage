var fields = ["id", 
			{
				name:"spId",mapping:"spInfo",convert : function(v) {
	                if(v!=null){
	          			return v.id;
	                }else{
	                	return '';
	                }
	   			}   
			}, {
				name:"spName",mapping:"spInfo",convert : function(v) {
                	if(v!=null){
          				  return v.spName;
                	}else{
                		return '';
                	}
   				 }   
			}, {
				name:"spServiceTypeId",mapping:"spServiceType",convert : function(v) {
                	if(v!=null){
          				  return v.id;
                	}else{
                		return '';
                	}
   				 }   
			}, {
				name:"serviceTypeName",mapping:"spServiceType",convert : function(v) {
                	if(v!=null){
          				  return v.serviceTypeName;
                	}else{
                		return '';
                	}
   				 }   
			},
		"serviceName", "linkmanName", "linkmanTel", "linkmanMobile", 
		"linkmanEmail", "managerType", "summary", "serviceUrl", "hostIp", 
		"instructionPath", "bussplanPath", "icpPath", "audipptPath", 
		"copyrightPath", "otherfile", "modifiedtime", "createdtime", "status"];

var spServiceInfoStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/iptv/sp/spServiceInfo!list.action",
			totalProperty : "totalCount",
			fields : fields
		});

var spServiceInfoReader = new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCount"
		}, [   
			{name:"id",mapping:"id"},
			{name:"spId",mapping:"spInfo",convert : function(v) {
                	if(v!=null){
          				  return v.id;
                	}else{
                		return '';
                	}
   				 }   
			},
			{name:"id",mapping:"id"},
			{name:"spName",mapping:"spInfo",convert : function(v) {
                	if(v!=null){
          				  return v.spName;
                	}else{
                		return '';
                	}
   				 }   
			},
			{name:"serviceTypeName",mapping:"spServiceType",convert : function(v) {
                	if(v!=null){
          				  return v.serviceTypeName;
                	}else{
                		return '';
                	}
   				 }   
			},
			{name:"spServiceTypeId",mapping:"spServiceType",convert : function(v) {
                	if(v!=null){
          				  return v.id;
                	}else{
                		return '';
                	}
   				 }   
			},
			{name:"serviceName",mapping:"serviceName"},
			{name:"linkmanName",mapping:"linkmanName"},
			{name:"linkmanTel",mapping:"linkmanTel"},
			{name:"linkmanMobile",mapping:"linkmanMobile"},
			{name:"linkmanEmail",mapping:"linkmanEmail"},
			{name:"managerType",mapping:"managerType"},
			{name:"summary",mapping:"summary"},
			{name:"serviceUrl",mapping:"serviceUrl"},
			{name:"hostIp",mapping:"hostIp"},
			{name:"instructionPath",mapping:"instructionPath"},
			{name:"bussplanPath",mapping:"bussplanPath"},
			{name:"icpPath",mapping:"icpPath"},
			{name:"audipptPath",mapping:"audipptPath"},
			{name:"copyrightPath",mapping:"copyrightPath"},
			{name:"otherfile",mapping:"otherfile"},
			{name:"modifiedtime",mapping:"modifiedtime"},
			{name:"createdtime",mapping:"createdtime"},
			{name:"status",mapping:"status"}
		]);