var musicboardType = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../sysType!list.action?typeId=1'}),
                reader:new Ext.data.JsonReader({
                root:'result',
                totalProperty:'totalCount',
                fields: [
                    {name:'billboardTypeId',mapping:'typeCode'},
                    {name:'billboradTypeName',mapping:'typeName'}
                ]
                })
            });
            
            

            
	function getInfo(v) {
			var returnStr = "未知类型";
			for (var i = 0; i < musicboardType.getCount(); i++) {
				var typeCode = musicboardType.getAt(i).get("billboardTypeId");
				if (typeCode == v) {
					returnStr = musicboardType.getAt(i).get("billboradTypeName");
					break;
				}
		
			}
		return returnStr;
		}
		
		
