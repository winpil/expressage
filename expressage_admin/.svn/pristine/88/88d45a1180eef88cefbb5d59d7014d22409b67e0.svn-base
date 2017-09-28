var musicType = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../sysType!list.action?typeId=2'}),
                reader:new Ext.data.JsonReader({
                root:'result',
                totalProperty:'totalCount',
                fields: [
                    {name:'musictypeid',mapping:'typeId'},
                    {name:'musictypename',mapping:'typeName'}
                ]
                })
            });