var cpstore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:path + '/imusic/cpmanager/cpInfo!list.action'}),
                reader:new Ext.data.JsonReader({
                root:'result',
                totalProperty:'totalCount',
                fields: [
                    {name:'cpid',mapping:'cpid'},
                    {name:'code',mapping:'cpCode'},
                    {name:'name',mapping:'cpName'},
                    {name:'businesslicensenumber',mapping:'cpBusinesslicensenumber'},
                    {name:'bankaccount',mapping:'cpTaxnumber'}
                ]})
});