<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/editform.js" ></script>
        <!--自定义脚本-->
        <script>
        	function chkName(obj){
        		var value = obj.value;
        		if(value == ""){
        			setErrorMessage(obj,"过滤器名称不能为空");
        			return false;
        		}
        		return true;
        	}
        	
        	function chkToEmailFilter(obj) {
        		var tefIsBlur = $("#tefIsBlurSelect").val();
       			var value = obj.value;
        		if (tefIsBlur == 1) {
        			var rgExp = /^[a-zA-Z0-9_.@]+$/;
	        		// alert("value:" + value + "--");
	        		if(value != "") {
	        			var emailArray = value.split("|");
	        			// alert(emailArray);
	        			for (var i = 0; i < emailArray.length; i++) {
	        				// alert(emailArray[i].search(rgExp));
	        				if (emailArray[i] != "" && emailArray[i].search(rgExp) == -1) {
	        					setErrorMessage(obj,"收件人过滤输入不合法:" + emailArray[i]);
	        					return false;
	        				}
	        			}
	        		}
        			return true;
        		}
        		else {
	        		var rgExp = /^[a-zA-Z0-9_.]+@[a-zA-Z0-9_]+(.com|.cn|.net|.com.cn)$/;
	        		// alert("value:" + value + "--");
	        		if(value != "") {
	        			var emailArray = value.split("|");
	        			// alert(emailArray);
	        			for (var i = 0; i < emailArray.length; i++) {
	        				// alert(emailArray[i].search(rgExp));
	        				if (emailArray[i] != "" && emailArray[i].search(rgExp) == -1) {
	        					setErrorMessage(obj,"收件人Email输入不合法:" + emailArray[i]);
	        					return false;
	        				}
	        			}
	        		}
	        		return true;
        		}
        	}
        </script>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="sysMailFilter!save.action">
        	<s:hidden name="sysMailFilter.id" />
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>邮件过滤信息</span></a></li>
                            </ul>
                        </div>
                        <!-- 输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--  数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                               <tr>
                                    <td class="gridtitle">过滤器名称：</td>
                                    <td class="gridbody">
                                   		<div class="editField" style="width: 200px;">
                                        	<s:textfield name="sysMailFilter.name" id="name" maxLength="20" cssClass="inp ipt-normal" onchange="limitTextLength(this,20)" />
                                        </div>
                                        <w:validator id="validator1" controlToValidate="sysMailFilter.name" validateFunction="chkName"
                                        	ruleMessage="填写过滤器名称，不能超过20个字符" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">收件人过滤：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 400px;">
                                          	<s:textarea name="sysMailFilter.toEmailFilter" theme="simple" rows="4" cols="41" 
                                          		onchange="limitTextLength(this,128)" />
                                        </div>
                                        <w:validator id="validator2" controlToValidate="sysMailFilter.toEmailFilter" validateFunction="chkToEmailFilter"
                                      		ruleMessage="说明：长度不超过128个字符；多个过滤词以|竖号隔开" nullable="true"/>
                                        <br>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">收件人是否模糊匹配：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                        	<s:select id="tefIsBlurSelect" list="#{1:'是',0:'否'}" name="sysMailFilter.tefIsBlur" theme="simple" cssStyle="width:100px"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">标题过滤：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 400px;">
                                            <s:textarea id="titleFilter" name="sysMailFilter.titleFilter" theme="simple" rows="4" cols="41" 
                                          		onchange="limitTextLength(this,128)" />
                                            <br>说明：长度不超过128个字符；多个过滤词以|竖号隔开
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">标题是否模糊匹配：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                        	<s:select list="#{1:'是',0:'否'}" name="sysMailFilter.tfIsBlur" theme="simple" cssStyle="width:100px"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <!--确定、取消按钮-->
                            <div class="ZB_BtnBox">
                                <input type="submit"  name="button2" id="btnSubmit" value="保存" class="ZB_Btn" />
                                <input type="button" name="btnCancel" id="btnCancel" value="取消" onclick="goBack();" class="ZB_Btn"/>
                            </div>
                        </div>
                    </div>
                </div>
                <!--页面内容部分 end-->
            </div>
        </form>
    </body>

</html>