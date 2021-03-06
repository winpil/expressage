<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>快递快滴管理平台</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/editform.js" ></script>
        <!--自定义脚本-->
        <script>
        	
        	function chkNickname(obj){
        		var value = obj.value;
        		if(value == ""){
        			setErrorMessage(obj,"昵称不能为空");
        			return false;
        		}
        		
        		$.post('mailReceiver!existNickname.action',{'mailReceiver.nickname':value},function(t){
        			if(t=="1"){
        				showValidateError(obj,"对不起，该昵称已存在，请重新输入");
        				return false;
        			}else{
        				//昵称不存在的情况下可以不做处理
        			}
        		});
        		
        		return true;
        	}
        	
        	function chkEmail(obj){
	        	var value = obj.value;
	        	if(value == ""){
        			setErrorMessage(obj,"邮箱地址不能为空");
        			return false;
        		}
        		
        		var rgExp = /^[a-zA-Z0-9_.]+@[a-zA-Z0-9_]+(.com|.cn|.net|.com.cn)$/;
        		if(value != "" && value.search(rgExp) == -1){
        			setErrorMessage(obj,"Email输入不合法，格式为：gdcn@123.com");
        			return false;
        		}
	        	return true;
        	}
        </script>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="mailReceiver!save.action">
            <s:hidden name="id" />
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>非系统注册用户基本信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                                <tr>
                                    <td class="gridtitle">昵称：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="mailReceiver.nickname" maxLength="64" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator id="validator2" controlToValidate="mailReceiver.nickname" validateFunction="chkNickname"
                                                     ruleMessage="填写昵称，不能超过64个字符" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">邮箱地址：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="mailReceiver.email" maxLength="64" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator id="validator5" controlToValidate="mailReceiver.email" validateFunction="chkEmail"
                                                     ruleMessage="填写邮箱地址，邮箱格式：gdcn@123.com" errorMessage="请填写正确的邮箱格式" nullable="false" />
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