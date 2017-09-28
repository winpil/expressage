var languageType = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../sysType!list.action?typeId=5'}),
                reader:new Ext.data.JsonReader({
                root:'result',
                totalProperty:'totalCount',
                fields: [
                    {name:'languageId',mapping:'typeId'},
                    {name:'languageName',mapping:'typeName'}
                ]
                })
            });