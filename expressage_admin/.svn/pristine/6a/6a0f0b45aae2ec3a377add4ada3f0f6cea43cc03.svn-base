var jsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'albumId',mapping:"albumId"},
                    {name:'albumName',mapping:'albumName'},
                    {name:'albumReleaseTime',mapping:'albumReleaseTime'},
                    {name:'albumMusicStyle',mapping:'albumMusicStyle'},
                    {name:'albumCompany',mapping:'albumCompany'},
                    {name:'albumBigpic',mapping:'albumBigpic'},
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
					{name:'languageTypeid',mapping:'languageType',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeId;
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
                    
                    {name:'albumSmallpic',mapping:'albumSmallpic'},
                    {name:'albumNote',mapping:'albumNote'},
                    {name:'albumRecommend',mapping:'albumRecommend'},
                    {name:'albumStatus',mapping:'albumStatus'},
                    {name:'createdDate',mapping:'createdDate'},
                    {name:'modifiedDate',mapping:'modifiedDate'},
                    {name:'musicCount',mapping:'musicCount'},
                    {name:'ringCount',mapping:'ringCount'}
                    ])
                
 var albumStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../../musicManager/album!list.action'}),
                reader:jsonread
            });
            
   