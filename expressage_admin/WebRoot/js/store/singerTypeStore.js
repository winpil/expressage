var fileStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../sysType!list.action?typeId=7'}),
                reader:new Ext.data.JsonReader({
                root:'result',
                totalProperty:'totalCount',
                fields: [
                    {name:'filetypeid',mapping:'typeId'},
                    {name:'filetypename',mapping:'typeName'}
                ]
                })
            });