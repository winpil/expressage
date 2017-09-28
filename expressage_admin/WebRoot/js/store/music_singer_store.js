var musicSingerjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'singerId',mapping:"singerId"},
                    {name:'singerName',mapping:'singerName'},
                    {name:'singerEnglishName',mapping:'singerEnglishName'},
                    {name:'singerInitial',mapping:'singerInitial'},
                    {name:'singerSex',mapping:'singerSex'},
                    {name:'singerNativePlace',mapping:'singerNativePlace'},
                    {name:'singerConstellation',mapping:'singerConstellation'},
                    {name:'singerBirthday',mapping:'singerBirthday'},
                    {name:'singerHeight',mapping:'singerHeight'},
                    {name:'singerWeight',mapping:'singerWeight'},
                    {name:'singerBloodType',mapping:'singerBloodType'},
                    {name:'singerEducation',mapping:'singerEducation'},
                    {name:'singerTypeName',mapping:'singerType',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeName;
	                    	}else{
	                    		return '';
	                    	}
           				 }  
           			},
           			 {name:'singerTypeid',mapping:'singerType',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeId;
	                    	}else{
	                    		return '';
	                    	}
           				 }  
           			},
                    
                    {name:'singerDistrictid',mapping:'singerDistrict',convert : function(v) {
	                    	if(v!=null){
	              				  return v.singerDistrictid;
	                    	}else{
	                    		return '';
	                    	}
           				 }  
           			},
                    {name:'singerDistrictname',mapping:'singerDistrict',convert : function(v) {
	                    	if(v!=null){
	              				  return v.singerDistrictname;
	                    	}else{
	                    		return '';
	                    	}
           				 }  
           			},
                    {name:'singerNote',mapping:'singerNote'},
                    {name:'singerBigpic',mapping:'singerBigpic'},
                    {name:'singerSmallpic',mapping:'singerSmallpic'},
                    {name:'createdDate',mapping:'createdDate'},
                    {name:'modifiedDate',mapping:'modifiedDate'}
                ])
 var musicSingerStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:path+'/musicManager/singer!list.action'}),
                reader:musicSingerjsonread
            });
            
