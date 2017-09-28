var jsonread= new Ext.data.JsonReader({
                    root:'root',
                    totalProperty:'total'
                }, [
                    {name:'vnetmusicid',mapping:"vnetmusicid"},
                    {name:'vnetmusiccode',mapping:'vnetmusiccode'},
                    {name:'vnetmusicname',mapping:'vnetmusicname'},
                    {name:'vnetmusicprice',mapping:'vnetmusicprice'},
                    {name:'vnetmusicurl',mapping:'vnetmusicurl'},
                    {name:'vnemusicfilesize',mapping:'vnemusicfilesize'},
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
	              				  return Date.parseDate(v, "Y-m-d").between(Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 0),Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 10))?"<font color='red'>"+v+"</font>":v;
	                    	}else{
	                    		return '';
	                    	}
           				 }  },
                    {name:'vnetmusicnote',mapping:'vnetmusicnote'},
                    {name:'vnetmusicstatus',mapping:'vnetmusicstatus'}
                ])
 var vmusicStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../../musicManager/vnet!list.action'}),
                reader:jsonread
            });
            
   