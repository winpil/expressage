var searchkeyjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'keywordId',mapping:"keywordId"},
                    {name:'keyword',mapping:'keyword'},
                    {name:'searchCount',mapping:'searchCount'},
                    {name:'searchBillboardSort',mapping:'searchBillboardSort'},
                    {name:'searchBillboardStatus',mapping:'searchBillboardStatus'}
                ])
 var seachkeyStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'searchKeyInfo!list.action'}),
                reader:searchkeyjsonread
            });
            
   