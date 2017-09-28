var siteSubjectTemplatejsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    'templateId',
                    'templateName',
                    'templateCode',
                    'templateNote',
                    'templateImgPath'
                ])
 var siteSubjectTemplateStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:path+'/musicManager/siteSubjectTemplate!list.action'}),
                reader:siteSubjectTemplatejsonread
            });
            
   