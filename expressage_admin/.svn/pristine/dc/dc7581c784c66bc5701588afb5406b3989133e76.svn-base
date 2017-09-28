/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


///页面加载后绑定事件
$(document).ready(function(){
    initValaidateControl();
    $("#editForm").bind("submit", function(){return valaidateAllControl();});
});

function limitTextLength(obj,limitCount){
    var text=obj.value;
    if(text.length>limitCount){
        obj.value=text.substring(0, limitCount);
    }
}

