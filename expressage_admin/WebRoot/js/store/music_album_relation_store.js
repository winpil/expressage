var albumrelationjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'id',mapping:"id"},
                    {name:'albumid',mapping:'albumid'},
                    {name:'billBoardSort',mapping:'billBoardSort'},
                    {name:'musicsort',mapping:'musicsort'},
                    {name:'musicName',mapping:'musicring',convert:function(v){
                    	if(v){
                    		return v.musicRingName;
                    	}else{
                    		return "";
                    	}
                    
                    }},
                    {name:'musicId',mapping:'musicring',convert:function(v){
                    	if(v){
                    		return v.musicringid;
                    	}else{
                    		return "";
                    	}
                    
                    }}
                    
                ])
 var albumrelationStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:path+'/musicManager/albumRelation!list.action'}),
                reader:albumrelationjsonread
            });
            
   