//全局变量，保存页面中所有需要验证的控件集合
var validateControlList=new Array();
var ajaxValidate = new Array();
//全局变量，保存最后的所有验证结果（如果为false，表单将无法提交）
var validateAllControlResult;

/**
 * 初始化页面上的验证控件
 * 根据type=validateHelper 找出页面上所有的验证控件
 * 遍历所有验证控件，找出其所需验证的对象，为其绑定focus事件、blur事件
 * 将所有需要验证的对象存放在validateControlList数组中，在表单submit前再对数组中的控件进行最后一次验证
 */
function initValaidateControl(){
    //遍历页面上的validateHelper对象
    $("div[type='validateHelper']").each(

        function() {
            //根据name属性，找出当前验证控件需要验证的页面控件对象列表
            var validateTargetName=$(this).attr("controlToValidate");
            if(!validateTargetName)return;
            //需要被验证的对象数组
            var targets=$("[name="+validateTargetName+"]");

            //填写正确时tip的样式名称
            var normalClassName=$(this).attr("normalClass");
            //焦点移入被验证控件时tip的样式名称
            var focusClassName=$(this).attr("focusClass");
            //填写错误时tip的样式名称
            var errorClassName=$(this).attr("errorClass");
            //填写规则
            var ruleInfo=$(this).attr("rule");
            //填写错误时的提示内容（可通过setErrorMessage动态修改）
            var errorInfo=$(this).attr("error");
            //验证方式：自定义验证脚本、正则表达式
            var validateType=$(this).attr("validateType");
            //验证脚本函数名称
            var validateFunction=$(this).attr("validateFunction");
            //验证正则表达式（正则表达式验证控件属性）
            var validationExpression=$(this).attr("validationExpression");
            //验证输放字符串长度
           var length=$(this).attr("length");
           
            var tip=$(this);
            //初始化tip，显示填写规则
            tip.html(ruleInfo);

            //################绑定验证对象的鼠标事件 start################
            targets.each(function(){
                //将需要验证的控件，放入页面验证控件列表
                validateControlList.push($(this));

                ///################焦点移入 start################
                $(this).bind("focus", function(){
                    //设置焦点移入样式
                    tip.attr("class",focusClassName);
                    //显示填写规则
                    tip.html(ruleInfo);
                });
                ///—————————焦点移入 end—————————

                ///################焦点移出 start########################
                $(this).bind("blur", function(){
                    //当前验证结果
                    var validateResult;
                     var validateLengthResult;
                    //对于不同的验证方式，选择不同的验证过程。默认是：自定义脚本验证
                    switch(validateType){
                        case "regularexpression":
                            validateResult=validateByRegularExpression(this,""+validationExpression);
                            break;
                        default:
                            try{
                                validateResult=eval(validateFunction+"(this)");
                              
                                if(length!='' && validateResult){
                                    validateLengthResult=eval(length);
                                    if(validateLengthResult==false){
                                        validateResult=false;
                                    }
                                }
                            }catch(err){
                                validateResult=true;
                            }
                            break;
                    }
                    //验证失败，显示错误提示信息
                    if(validateResult==false){
                        tip.attr("class",errorClassName);
                        validateAllControlResult=false;
                        
                       if( validateLengthResult==false){
                            tip.html("输入的字符串超出限制长度");
                       	}else{
                            tip.html(tip.attr("error"));
                        }
                    }else {
                        //验证成功，显示正确提示
                        tip.attr("class",normalClassName);
                        tip.html(ruleInfo);
                    }

                });
            ///—————————焦点移出 end—————————

            });
        //—————————绑定验证对象的鼠标事件 end—————————
        }
        );
}



/**
 * 验证对象的值是否为空
 *
 */
function chkIsNoEmpty(obj){
    if($.trim(obj.value).length==0){
        return false;
    }else{
        return true;
    }
}

/**
 * 验证下拉控件是否选择了合法选项
 */
function chkSelect(obj){
    if(obj.value==-1){
        return false;
    }else{
        return true;
    }
}

/**
 * 验证单选框、复选框是否至少选中一个选项
 */
function chkRadioGroup(obj){
    var check=false;
    $("input[name='"+obj.name+"']").each(
        function() {
            if($(this).attr("checked")){
                check= true;
            }
        });
    return check;
}

/**
 * 通过正则表达式验证对象的值
 */
function validateByRegularExpression(obj,expression){
    var testValue=obj.value;
    var reg =new RegExp(expression);
    return reg.test(testValue);
}

/**
 * 显示日历控件
 */
function showWdatePicker(objId,df){
    WdatePicker({
        el:objId,
        isShowClear:false,
        readOnly:true,
        highLineWeekDay:true,
        dateFmt:df
    });
}



/**
 * 弹出窗口，显示消息
 */
function showMessage(message){
    alert(message);
}

/**
 * 全选 checkbox
 */
function switchCheckAll(sender){
    var checkItem = $("[name=checkItem]");
    checkItem.each(function(){
        $(this).attr("checked", sender.checked);
    });
}

/**
 * 动态设置错误tip信息
 */
function setErrorMessage(target,message){
    $("[controlToValidate="+target.name+"]").attr("error",message);
}

/**
 *
 */
function showValidateError(target,error){
    var tip=$("[controlToValidate="+target.name+"]");
    tip.attr("error",error);
    tip.attr("class",tip.attr("errorClass"));
    tip.html(tip.attr("error"));
}

function showValidateMessage(target,message,normalClassName){
    var tip=$("[controlToValidate="+target.name+"]");
    tip.attr("class",normalClassName);
    tip.attr("rule",message);
    tip.html(tip.attr("rule"));
}

/**
 * 遍历所有需要验证的控件，看是否全部验证通过。（一般绑定在submit前）
 */
function valaidateAllControl(){
    validateAllControlResult=true;
    for(var i=0;i<validateControlList.length;i++){
        validateControlList[i].blur();
    }
    
    var ajaxFlag = true;
    
    for(var j in ajaxValidate){
    	if(!ajaxValidate[j]){
    		ajaxFlag = false;
    		break;
    	}
    }
    
   // alert(ajaxValidate[0]);
    
    return validateAllControlResult && ajaxFlag;
}

/**
 * 初始化缩略图控件
 */
function initImageThumb(){

}


function showInfoDlg(info){
    // function(title, message, timeout, onClose,divClassName,overlay)
    $.growlUI(info,"",3000);
// alert(info);
}
function showErrorDlg(error){
    // $.growlUI(error," ",0,null,"growlErrorUI").append("<button class='btn40'>确定</button>").click($.unblock);
    alert(error);
}
function showWarnDlg(warn){
    //$.growlUI(warn," ",0,null,"growlWarnUI",true).click($.unblockUI);
    alert(warn);
}

function showActionMessage(){
    //检查是否为“取消”按钮返回前一个页面；如果是，不显示上次提示
    //alert($.cookie("isGoBack"));
    if($.cookie("isGoBack")=="true"){
        //清空cookie
        $.cookie("isGoBack",null);
        return;
    }
    var message="";
    var error="";
    $("[class='actionMessage']").each(function(){
        $(this).find("span").each(function(){
            var info=$(this).html();
            if(info.indexOf("[message]")>-1){
                message+=$(this).html().replace("[message]", "");
            }
            if(info.indexOf("[error]")>-1){
                error+=$(this).html().replace("[error]", "");
            }
            if(info.indexOf("[refresh]")>-1){
                doRefreshFrame($(this).html().replace("[refresh]", ""));
            }
        });
    });
    if(message!=""){
        showInfoDlg(message);
    }
    if(error!=""){
        showErrorDlg(error);
    }
    //清空cookie
    $.cookie("isGoBack",null);
}

function goBack(){
    //设置cookie值，避免再次显示上次操作的提示信息
    $.cookie("isGoBack", "true");
    history.back();
}

function goBackEx(frameName){
    //设置cookie值，避免再次显示上次操作的提示信息
    $.cookie("isGoBack", "true");
    parent.frames[frameName].history.back();
}

