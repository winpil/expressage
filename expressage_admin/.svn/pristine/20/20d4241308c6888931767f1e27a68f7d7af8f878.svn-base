var siteSubjectTemplateAreajsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    'templateareaId',
                    'templateId',
                    'templateareaName',
                    'templateareaCode',
                    'templateareaNote',
                    'checkSignerInvoicess'
                ])
 var siteSubjectTemplateAreaStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:path+'/musicManager/siteSubjectTemplateArea!list.action'}),
                reader:siteSubjectTemplateAreajsonread
            });
            
   