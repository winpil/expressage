
// Trim whitespace from left and right sides of s.
function trim(s) {
	return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}
function ltrim(str)
{
	return str.replace(/(^\s*)/g, "");
}
	
function rtrim(str)
{
	return str.replace(/(\s*$)/g, "");
}
/**
 * 特殊符号处理
 * @Desc:将页面中的特殊符号改为全角中文符号
 * @author:luxm
 * @version:V1.1
 * @Date:2007-11-08
 */
function replaceAll(value) {
	value = value.replace(/&/g,"&amp;").replace(/'/g,"&#39;").replace(/"/g,"&quot;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/ /g,"&nbsp;");
	return value;
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function confirmDelete(document,checkboxName,title) {   
    var msg = "您确定要删除选择的" + title + "吗?";
	var count = getSelectOptionCount(document,checkboxName);
	if(count==0){
		//alert("请选择要删除的"+title);
		parent.Ext.Msg.alert("系统提示", "请选择要删除的"+title);
		return false;
	}
	
    ans = confirm(msg);
    if (ans) {
        return true;
    } else {
        return false;
    }
}

function checkUpdate(document,checkboxName,title) {   
   	var count = getSelectOptionCount(document,checkboxName);
	if(count==0){
		//alert("请选择要修改的"+title);
		parent.Ext.Msg.alert("系统提示", "请选择要修改的"+title);
		return false;
	}
	if(count>1){
		//alert("一次只能修改一个"+title);
		parent.Ext.Msg.alert("系统提示", "一次只能修改一个"+title);
		return false;
	}
	return true;
}

/**
 * @author luxm
 */
function checkSelect(checkboxName,operate,title) {   
   	var count = getCheckedCount(checkboxName);
	if(count==0){
		alert("请选择要"+operate+"的"+title);
		return false;
	}
	if(count>1){
		alert("一次只能"+operate+"一个"+title);
		return false;
	}
	return true;
}

/**
 * @author luxm
 * @description 
 * @param checkboxName
 * @param operate
 * @param obj
 * @param msg
 */
function confirmOperate(checkboxName,operate,obj,msg) {   
    var msg = "确定要"+operate+"选择的" + obj + "吗?"+msg;
	var count = getCheckedCount(checkboxName);
	if(count==0){
		alert("请选择要"+operate+"的"+obj);
		return false;
	}
    ans = confirm(msg);
    if (ans) {
        return true;
    } else {
        return false;
    }
}


//get checkbox checked count
function getCheckedCount(document, checkboxName){
	var theCheckbox = document.getElementsByName(checkboxName); 
	var count = 0;
	if(theCheckbox!=null){
		for(var i = 0;i<theCheckbox.length;i++){
			if(theCheckbox[i].checked){
				count = count+1;
			}
		}
	}
	return count;
}

//get checkbox checked count
function getSelectOptionCount(document, checkboxName){
	var theCheckbox = document.getElementById(checkboxName); 
	var count = 0;
	if(theCheckbox){
		count = theCheckbox.options.length;
	}
	return count;
}

// Show the document's title on the status bar
window.defaultStatus=document.title;

