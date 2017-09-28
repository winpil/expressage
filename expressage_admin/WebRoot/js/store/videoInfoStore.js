var fields = ["videoId", "videoName", "videoPrice", "fileTypeid","videoDownloadPath",
		"videoFileSize", "videoNote","thumbSavePath", "videoStatus", "singerName", "copyrightStatus", {
			name : "musicRingName",
			mapping : "musicRelation.musicRingName"
		}, {
			name : "musicringid",
			mapping : "musicRelation.musicringid"
		}, {
			name : "cpid",
			mapping : "cpinfo.cpid"
		}, {
			name : "cpname",
			mapping : "cpinfo.cpName"
		}, {
			name : "singerName",
			mapping : "musicRelation.singer.singerName"
		}, {name:"expirationDate",mapping:"expirationDate",convert : function(v) {
	                    	if(v!=null){
	              				  return Date.parseDate(v, "Y-m-d").between(Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 1),Date.parseDate(nowDate, "Y-m-d").add(Date.DAY, 10))?"<font color='red'>"+v+"</font>":v;
	                    	}else{
	                    		return '';
	                    	}
           				 }}];

var videoInfoStore = new Ext.data.JsonStore({
			root : "result",
			url : path + "/imusic/webmanager/video/videoInfo!list.action",
			totalProperty : "totalCount",
			fields : fields
		});

var videoInfoReader = new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCount"
		}, [{
					name : "videoId",
					mapping : "videoId"
				}, {
					name : "videoName",
					mapping : "videoName"
				}, {
					name : "videoPrice",
					mapping : "videoPrice"
				}, {
					name : "fileTypeid",
					mapping : "fileTypeid"
				}, {
					name : "videoFileSize",
					mapping : "videoFileSize"
				}, {
					name : "videoNote",
					mapping : "videoNote"
				}, {
					name : "newsAuthor",
					mapping : "newsAuthor"
				}, {
					name : "videoStatus",
					mapping : "videoStatus"
				}, {
					name : "expirationDate",
					mapping : "expirationDate"
				}, {
					name : "newsSource",
					mapping : "newsSource"
				}, {
					name : "expirationDate",
					mapping : "expirationDate"
				}, {
					name : "musicRingName",
					mapping : "musicRelation.musicRingName"
				}, {
					name : "musicringid",
					mapping : "musicRelation.musicringid"
				}, {
					name : "videoDownloadPath",
					mapping : "videoDownloadPath"
				},{
					name : "cpid",
					mapping : "cpinfo.cpid"
				}, {
					name : "cpname",
					mapping : "cpinfo.cpName"
				}, {
					name : "thumbSavePath",
					mapping : "thumbSavePath"
				}, {
					name : 'singerName', 
					mapping : 'musicRelation.singer.singerName'
                }, {
                	name : 'copyrightStatus', 
                	mapping : 'copyrightStatus'
                }]);