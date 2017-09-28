var billboardrelationjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'relationId',mapping:"relationId"},
                    {name:'billBoardid',mapping:'billBoardid'},
                    {name:'musicRingName',mapping:'musicRingName'},
                    {name:'billBoardSort',mapping:'billBoardSort'},
                    {name:'id',mapping:'id'},
                    {name:'tableType',mapping:'tableType'}
                ])
 var billboardrelationStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../../musicManager/billboard!getBillboardByMainId.action'}),
                reader:billboardrelationjsonread
            });
            
   