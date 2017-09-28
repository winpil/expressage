var jsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'musicid',mapping:"musicid"},
                    {name:'musiccode',mapping:'musiccode'},
                    {name:'musicname',mapping:'musicname'},
                    {name:'musicprice',mapping:'musicprice'},
                    {name:'musicPlayTime',mapping:'musicPlayTime'},
                    {name:'musicfilesize',mapping:'musicfilesize'},
                    {name:'playTypeid',mapping:'playTypeid'},
                    {name:'musicdownloadpath',mapping:'musicdownloadpath'},
                    {name:'musicringid',mapping:'musicRelation',convert : function(v) {
	                    	if(v!=null){
	              				  return v.musicringid;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
					{name:'musicRingName',mapping:'musicRelation',convert : function(v) {
	                    	if(v!=null){
	              				  return v.musicRingName;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
                    
                    {name:'cpid',mapping:'cp',convert : function(v) {
	                    	if(v!=null){
	              				  return v.cpid;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
                    {name:'name',mapping:'cp',convert : function(v) {
	                    	if(v!=null){
	              				  return v.cpName;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
					},
                    {name:'filetypename',mapping:'filetype',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeName;
	                    	}else{
	                    		return '';
	                    	}
           				 }  
           			},
           			{name:'filetypeid',mapping:'filetype',convert : function(v) {
	                    	if(v!=null){
	              				  return v.typeId;
	                    	}else{
	                    		return '';
	                    	}
           				 }  
           			},
                    {name:'expirationdate',mapping:'expirationdate'},
                    {name:'showExpirationdate',mapping:'expirationdate',convert : function(v) {
	                    	if(v!=null){
	              				  return Date.parseDate(v, "Y-m-d").between(Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 1),Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 10))?"<font color='red'>"+v+"</font>":v;
	                    	}else{
	                    		return '';
	                    	}
           				 }  },
           				 
           			{name : 'singerName', mapping : 'musicRelation', convert : function(v) {
                    		if(v!=null) {
                    			return v.singer.singerName;
                    		} else {
                    			return '';
                    		}
                    	}
                    },
                    {name : 'copyrightStatus', mapping : 'musicNineskymusic', convert : function(v) {
                    		if(v!=null) {
                    			return v.copyrightStatus;
                    		} else {
                    			return '';
                    		}
                    	}
                    },
                    {name:'musicnote',mapping:'musicnote'},
                    {name:'musicstatus',mapping:'musicstatus'},
                    {name : 'copyrightStatus', mapping : 'copyrightStatus'}
                ])
 var musicStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../../musicManager/music!list.action'}),
                reader:jsonread
            });
            
   