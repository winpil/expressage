var relationjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'musicringid',mapping:"musicringid"},
                    {name:'musicRingName',mapping:'musicRingName'},
                    {name:'musicLrcText',mapping:'musicLrcText'},
                    {name:'musicLrcPath',mapping:'musicLrcPath'},
                    {name:'expirationDate',mapping:'expirationDate'},
                    {name:'ringPrice',mapping:'ringPrice'},
                    {name:'musicTypeName',mapping:'musicType',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeName;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
					{name:'musicTypeId',mapping:'musicType',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeId;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
                    {name:'singerName',mapping:'singer',convert : function(v) {
	                    	if(v!=null){
	              				  return v.singerName;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
					{name:'singerId',mapping:'singer',convert : function(v) {
	                    	if(v!=null){
	              				  return v.singerId;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
					{name:'languageTypeName',mapping:'languageType',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeName;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
					{name:'languageTypeId',mapping:'languageType',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeId;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
                    {name:'musicringstatus',mapping:'musicringstatus'}
                ])
 var relationStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:path+'/musicManager/relation!list.action'}),
                reader:relationjsonread
            });
            
   