function getstatus(v){
		if(v==0){
        		return '删除';
        	}else if(v==1){
        		return '未审核';
        	}else if(v==2){
        		return '审核不通过';
        	}else if(v==3){
        		return '审核通过';
        	}else if(v==4){
        		return '关闭';
        	}else if(v==5){
        		return '开通';
        	}
        	
        	return '未定义';
        }
        
 function  setStatus(v){
 	var deletebtn = Ext.getCmp('deletebtn');
    var notpassbtn = Ext.getCmp('notpassbtn');
    var passbtn = Ext.getCmp('passbtn');
    var closebtn = Ext.getCmp('closebtn');
    var enablebtn  = Ext.getCmp('enablebtn');
 	
 	if(v==1){
 		if(deletebtn)
 		 deletebtn.setDisabled(false);
 		if(notpassbtn)
         notpassbtn.setDisabled(false);
        if(passbtn)
         passbtn.setDisabled(false);
        if(closebtn)
         closebtn.setDisabled(true);
        if(enablebtn)
         enablebtn.setDisabled(false);
 		
 	}else if(v==2){
 		if(deletebtn)
 		 deletebtn.setDisabled(false);
 		if(notpassbtn)
         notpassbtn.setDisabled(true);
        if(passbtn)
         passbtn.setDisabled(true);
        if(closebtn)
         closebtn.setDisabled(true);
        if(enablebtn)
         enablebtn.setDisabled(true);
 	
 	}else if(v==3){
 		if(deletebtn)
 		 deletebtn.setDisabled(false);
 		if(notpassbtn)
         notpassbtn.setDisabled(true);
        if(passbtn)
         passbtn.setDisabled(true);
        if(closebtn) 
         closebtn.setDisabled(false);
        if(enablebtn)
         enablebtn.setDisabled(false);
 	
 	}else if(v==4){
 		if(deletebtn)
 		 deletebtn.setDisabled(false);
 		if(notpassbtn)
         notpassbtn.setDisabled(true);
        if(passbtn)
         passbtn.setDisabled(true);
        if(closebtn) 
         closebtn.setDisabled(true);
        if(enablebtn)
         enablebtn.setDisabled(false);
 	
 	}else if(v==5){
 		if(deletebtn)
 		 deletebtn.setDisabled(false);
 		if(notpassbtn)
         notpassbtn.setDisabled(true);
        if(passbtn)
         passbtn.setDisabled(true);
        if(closebtn)
         closebtn.setDisabled(false);
        if(enablebtn)
         enablebtn.setDisabled(true);
 	}else{
 		if(deletebtn)
 		 deletebtn.setDisabled(false);
 		if(notpassbtn)
         notpassbtn.setDisabled(true);
        if(passbtn)
         passbtn.setDisabled(true);
        if(closebtn)
         closebtn.setDisabled(true);
        if(enablebtn)
         enablebtn.setDisabled(true);
 	}
 
 }    
 
 
 
   // 自定义VType类型，验证日期选择范围
        	Ext.apply(Ext.form.VTypes, {
        				// 验证方法
        				dateRange : function(val, field) {
        					if (field.dateRange) {

        						var beginId = field.dateRange.begin;
        						this.beginField = Ext.getCmp(beginId);

        						var endId = field.dateRange.end;
        						this.endField = Ext.getCmp(endId);

        						var beginDate = this.beginField.getValue();
        						var endDate = this.endField.getValue();
        					}

        					if (beginDate <= endDate) {
        						return true;
        					} else {
        						return false;
        					}

        				},
        				// 验证提示信息
        				dateRangeText : '开始日期不能大于结束日期!'
        	});

        	Ext.apply(Ext.form.VTypes,{
				// 验证方法
				imgVtype : function(val, field) {
				    try{
						if(/^.+\.(gif|jpg|jpeg|png)$/.test(val)){
							return true;
						}else{
							return false;
						}
				    }catch(e){
						return false;
					}

					return true;
				},
				// 验证提示信息
				imgVtypeText : '输入图片格式不正确，图片格式只能为(gif|jpg|jpeg|png)!'
			});