var billboardSlotjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'billboardSlotId',mapping:"billboardSlotId"},
                    {name:'billboardSlotName',mapping:'billboardSlotName'},
                    {name:'billboardSlotCode',mapping:'billboardSlotCode'},
					{name:'billboardTypeid',mapping:'billboardTypeid'},
                    {name:'billboardSlotNote',mapping:'billboardSlotNote'},
					{name:'billboardSlotStatus',mapping:'billboardSlotStatus'},
					{name:'createdDate',mapping:'createdDate'}
                ])
 var billboardSlotStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../../musicManager/musicBillboardSlot!list.action'}),
                reader:billboardSlotjsonread
            });
            
   