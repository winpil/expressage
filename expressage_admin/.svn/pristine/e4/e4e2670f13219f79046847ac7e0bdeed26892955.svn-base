//限制图片上传格式
function chkIconPathFile(obj){
	var value = obj.value;
	var exp = /^.+\.(gif|jpg|jpeg|png)$/i;
	if(value != "" && value.search(exp)==-1){
		setErrorMessage(obj,"产品图标格式不正确，格式只能为(gif|jpg|jpeg|png)");
		return false;
	}
	return true;
}

//限制输入必须为数字且不为0
function chkTheSort(obj){
	if(isNaN(obj.value)){
		setErrorMessage(obj,"显示顺序必须为数字，请重新填写");
        return false;
	}else if(obj.value==0){
		setErrorMessage(obj,"显示顺序不能为0，请重新填写");
        return false;
	}
	return true;
}

//限制上传文件类型
function checkFile(obj) {
	var value = $(obj).val();
	var suffixIndex = value.lastIndexOf(".");
	if (suffixIndex != -1) {
		var suffix = value.substring(suffixIndex, value.length);
		// alert($(obj).attr("name") == "electFile");
		if ($(obj).attr("name") == "electFile") {
			if (suffix != ".doc") {
				alert("该文件类型" + suffix + "不允许上传！");
				$(obj).after($(obj).clone().val(''));
				$(obj).remove();
			}
		}else if ($(obj).attr("name") == "fileName") {
			if (suffix != ".jpg" && suffix != ".jpeg" && suffix != ".bmp" && suffix != ".gif"&& suffix != ".png") {
				alert("该文件类型" + suffix + "不允许上传！");
				$(obj).after($(obj).clone().val(''));
				$(obj).remove();
			}
		}
	}else {
		alert("该文件类型不允许上传！");
		$(obj).after($(obj).clone().val(''));
			$(obj).remove();
	}
}

//可以使用fame中的utils进行简化
function chkElectFile(obj){
	var value = obj.value;
	var exp = /^.+\.rar$/i;
	if(value != "" && value.search(exp)==-1){
		setErrorMessage(obj,"资料格式不正确，格式只能为*.rar");
		return false;
	}
}

//替换输入为数字格式
function replaceToNumer(obj){
	return obj.value.replace(/^(0|\s|\D)*|(\D|\s)*$/g,'');
}

//限制输入字数
 function limit(obj,limitCount){
    var text=obj.value;
    if(text.length>limitCount){
        obj.value=text.substring(0, limitCount);
    }
}
// 限制输入字数别名
function limitTextLength(obj,limitCount){
	limit(obj,limitCount);
}

//根据url弹出窗口
function openModalDialog(url){
    var rnd=Math.ceil(Math.random()*10000);
    var returnVal=window.open(url+"&rnd="+rnd,'','height=400,width=550,top='+(screen.height-400)/2+',left='+(screen.width-550)/2+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
}

//截取字数，并显示剩余字符数
/*
使用方式：
* 1.页面部分
<label>剩下<span id="featextCount">200</span></label><br />
<s:textarea name="testTerminalModel.feature"  onchange="limitTextLength(this,200)"
	 cols="50" rows="4" id="featextarea"></s:textarea>
  2.js部分
          $(document).ready(function(){
			bindLimit($("#sumtextarea"),$("#sumtextCount"),500);
		  });
*/
function bindLimit(textArea,textCount,leng){
	 subWord(textArea,textCount,leng);
	 textArea.keydown(function(){
        var curLength=textArea.val().length;
        if(curLength>leng){
            var num=textArea.val().substring(0,leng);
            textArea.val(num);
            alert("超过字数限制，多出的字将被截断！" );
        }else{
            textCount.text(leng-(textArea.val().length));
        }
	 });
	 textArea.change(function(){
        var curLength=textArea.val().length;
        if(curLength>leng){
            var num=textArea.val().substring(0,leng);
            textArea.val(num);
        }else{
            textCount.text(leng-(textArea.val().length));
        }
	 });
}

//bindLimit辅助方法
function subWord(textArea,textCount,leng){
        var curLength=textArea.val().length;
        if(curLength>leng){
            var num=textArea.val().substring(0,leng);
            textArea.val(num);
            alert("超过字数限制，多出的字将被截断！" );
        }else{
            textCount.text(leng-(textArea.val().length));
        }
 }

function showMessageWindow(message){
	var whatsNew = window.open('','','height=300,width=550,top='+(screen.height-400)/2+',left='+(screen.width-550)/2+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
	whatsNew.document.write('<p style="word-wrap:break-word;word-break:break-all;">'+message+'</p>');
	//whatsNew.document.write('<p align="right">'+'<a href="javascript:self.close()">关闭窗口</a></p>');
	whatsNew.document.close();
}


