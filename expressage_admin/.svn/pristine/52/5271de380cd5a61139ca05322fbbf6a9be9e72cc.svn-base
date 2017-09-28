var musicSingerDistrictjsonread= new Ext.data.JsonReader({
                    root:'result',
                    totalProperty:'totalCount'
                }, [
                    {name:'singerDistrictid',mapping:"singerDistrictid"},
                    {name:'singerDistrictname',mapping:'singerDistrictname'}
                   
                ])
 var musicSingerDistrictStore = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url:'../../musicManager/singerDistrict!list.action'}),
                reader:musicSingerDistrictjsonread
            });
            
