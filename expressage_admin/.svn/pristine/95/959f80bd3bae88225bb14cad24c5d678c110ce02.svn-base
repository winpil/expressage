var billboardjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'billBoardid',mapping:"billBoardid"},
                    {name:'billBoardname',mapping:'billBoardname'},
                    {name:'billBoardTypeid',mapping:'billBoardTypeid'},
                    {name:'billBoardNote',mapping:'billBoardNote'},
                    {name:'bilLboardStatus',mapping:'bilLboardStatus'},
                    {name:'limitNum',mapping:'limitNum'},
                    {name:'periodsNumber',mapping:'periodsNumber'},
                   	{name:'billboardSlotId',mapping:'billboardSlot',convert : function(v) {
	                    	if(v!=null){
	              				  return v.billboardSlotId;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
           			},
                   	{name:'billboardSlotName',mapping:'billboardSlot',convert : function(v) {
	                    	if(v!=null){
	              				  return v.billboardSlotName;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
           			},
                   	{name:'billboardSlotCode',mapping:'billboardSlot',convert : function(v) {
	                    	if(v!=null){
	              				  return v.billboardSlotCode;
	                    	}else{
	                    		return '';
	                    	}
           				 }   
           			}
                ])
 var billboardStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../../musicManager/billboard!list.action'}),
                reader:billboardjsonread
            });
            
   