 var array=new Array();
 Ext.onReady(function() {
	  sysTypePageStore.on("load",function(){
	  		array=sysTypePageStore.data.items;
	  });
	  sysTypePageStore.load({params:{'filter_EQL_typeCategory:ty.typecategoryid':16}});

});

var siteSubjectTemplateItermjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    'templateitemId',
                    'templateareaId',
                    'templateitemName',
                    'templateitemCode',
                    'templateType',
                    {name:'templateTypeName',mapping:'templateType',convert:function(v){
                    	if(v!=null){
                    		var returnStr="未定义";
								for(var i=0;i<array.length;i++){
									if(array[i].get("typeCode")==v){
										returnStr=array[i].get("typeName");
									    break;
									}
								};
                    	}
                    	return returnStr;
                    }},
                    'wordLimit',
                    'imgWidth',
                    'imgHeight',
                    'templateitemNote'
                ])
 var siteSubjectTemplateItermStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:path+'/musicManager/siteSubjectTemplateIterm!list.action'}),
                reader:siteSubjectTemplateItermjsonread
            });
     
            
