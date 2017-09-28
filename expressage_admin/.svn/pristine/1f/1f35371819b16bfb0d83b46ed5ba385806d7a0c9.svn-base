var singerHotjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'singerHotId',mapping:"singerHotId"},
                    {name:'singerHotSort',mapping:'singerHotSort'},
                    {name:'singerHotNote',mapping:'singerHotNote'},
                    {name:'singerHotStatus',mapping:'singerHotStatus'},
                    {name:'singerHotCount',mapping:'singerHotCount'},
                    {name:'singerName',mapping:'singer',convert:function(v){
                    	if(v){
                    		return v.singerName;
                    	}else{
                    		return "";
                    	}
                    
                    	
                    }},
                    {name:'singerId',mapping:'singer',convert:function(v){
                    	if(v){
                    		return v.singerId;
                    	}else{
                    		return "";
                    	}
                    
                    	
                    }
                    
                    }
                ])
 var singerHotStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'singerHot!list.action'}),
                reader:singerHotjsonread
            });
            
   