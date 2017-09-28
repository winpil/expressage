var musicRingjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'ringid',mapping:"ringid"},
                    {name:'ringCode',mapping:'ringCode'},
                    {name:'ringName',mapping:'ringName'},
                    {name:'ringPrice',mapping:'ringPrice'},
                    {name:'ringPlayurl',mapping:'ringPlayurl'},
                    {name:'ringFilesize',mapping:'ringFilesize'},
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
           			{name:"musicRelationId",mapping:"musicRelation",convert:function(v){
           				if(v){
           					return v.musicringid;
           				}else{
           					return "";
           				}
           			}},
           			{name:"musicRelationName",mapping:"musicRelation",convert:function(v){
           				if(v){
           					return v.musicRingName;
           				}else{
           					return "";
           				}
           			}},
                    {name:'expirationdate',mapping:'expirationdate'},
                    {name:'showExpirationdate',mapping:'expirationdate',convert : function(v) {
	                    	if(v!=null){
	              				  return Date.parseDate(v, "Y-m-d").between(Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 1),Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 10))?"<font color='red'>"+v+"</font>":v;
	                    	}else{
	                    		return '';
	                    	}
           				 }  },
                    {name:'ringnote',mapping:'ringnote'},
                    {name:'ringstatus',mapping:'ringstatus'},
                    {name:'createdDate',mapping:'createdDate'},
                    {name:'modifiedDate',mapping:'modifiedDate'},
                    {name : 'singerName', mapping : 'singerName'},
                    {name : 'copyrightStatus', mapping : 'copyrightStatus'}
                ])
 var musicRingStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../../musicManager/musicRing!list.action'}),
                reader:musicRingjsonread
            });
            
