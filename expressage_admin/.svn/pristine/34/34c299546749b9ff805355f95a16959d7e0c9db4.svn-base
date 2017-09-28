var sysTypeStore = new Ext.data.JsonStore({
		url : path + "/imusic/sysType!listByTypeId.action",
		fields : ["typeName", "typeCode"]
});

var sysTyperead= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, ["typeName", "typeCode",'typeId','typeNote',
                    {name:'typecategoryid',mapping:'typeCategory',convert:function(v){
                    	if(v){
                    		return v.typecategoryid;
                    	}else{
                    		return "";
                    	}
                    
                    	
                    }},
                    {name:'typecodename',mapping:'typeCategory',convert:function(v){
                    	if(v){
                    		return v.typecodename;
                    	}else{
                    		return "";
                    	}
                    
                    	
                    }}
                    
                    ]);

var sysTypePageStore = new Ext.data.JsonStore({
			url : path + "/imusic/sysType!listForPage.action",
			root : 'result',
			totalProperty : 'totalCount',
			fields : ["typeName", "typeCode", 'typeId', 'typeNote']
		});