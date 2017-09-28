var musicHotjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'musicHotId',mapping:"musicHotId"},
                    {name:'musicHotSort',mapping:'musicHotSort'},
                    {name:'musicHotNote',mapping:'musicHotNote'},
                    {name:'musicHotStatus',mapping:'musicHotStatus'},
                    {name:'musicHotCount',mapping:'musicHotCount'},
                    {name:'musicRingid',mapping:'musicRing',convert:function(v){
                    	if(v){
                    		return v.musicringid;
                    	}else{
                    		return "";
                    	}
                    
                    	
                    }},
                    {name:'musicRingName',mapping:'musicRing',convert:function(v){
                    	if(v){
                    		return v.musicRingName;
                    	}else{
                    		return "";
                    	}
                    
                    	
                    }
                    
                    }
                ])
 var musicHotStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'musicHot!list.action'}),
                reader:musicHotjsonread
            });
            
   