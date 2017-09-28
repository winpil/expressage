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
        	
        	var flag = true;
        	function chkUsername(obj){
        		var value = obj.value;
        		var exp = /^[a-zA-Z][a-zA-Z0-9]{0,19}$/;
        		if(value == ""){
        			setErrorMessage(obj,"账号不能为空");
        			flag = false;
        		}
        		
        		if(value.search(exp)==-1){
        			setErrorMessage(obj,"账号必须以字母开头，只能包含字母数字，且不超过20位");
        			flag = false;
        		}
        		$.ajaxSetup({  
					async : false  
				}); 
        		$.post('sysUser!chkUsername.action',{'sysUser.username':value},function(t){
        			if(t=="1"){
        				showValidateError(obj,"对不起，该用户已存在，请重新输入");
        				flag =  false;
        			}else{
        				//用户不存在的情况下可以不做处理
        				flag = true;
        			}
        		});
        		return flag;
        	}
        	
        	function chkChinaName(obj){
        		var value = obj.value;
        		var exp=/^[\u4e00-\u9fa5]*$/;
        		if(value == ""){
        			setErrorMessage(obj,"中文名不能为空，请输入!");
        			return false;
        		}
        		if(!exp.test(value)){
        			setErrorMessage(obj,"请输入正确的中文名!");
        			return false;
        		}
        		return true;
        	}
        	
        	function chkPassword(obj){
        		var value = obj.value;
        		var exp = /^[\s\S]{6,20}$/;
        		if(value == ""){
        			setErrorMessage(obj,"密码不能为空，请输入!");
        			return false;
        		}
        		if(value.search(exp)==-1){
        			setErrorMessage(obj,"密码长度不能少于6位，不能超过16位!");
        			return false;
        		}
        		return true;
        	}
        	function chkPhone(obj){
        		var value = obj.value;
        		var exp = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
        		value = value.replace(/^\s+|\s+$/g,"");
        		if(value !="" && value.search(exp)==-1){
        			setErrorMessage(obj,"办公电话输入不合法，格式为：010-6666666");
        			return false;
        		}
        		return true;
        	}
        	function chkMobilePhone(obj){
        		var value = obj.value;
        		var exp = /^1\d{10}$/;
        		value = value.replace(/^\s+|\s+$/g,"");
        		if(value !="" && value.search(exp)==-1){
        			setErrorMessage(obj,"移动电话输入不合法，格式为：13988888888");
        			return false;
        		}
        		return true;
        	}
        	function chkEmail(obj){
	        	var value = obj.value;
	        		//var exp = /^[a-zA-Z]\w+@\w+[.com|.cn]+$/;
	        		var exp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	        		value = value.replace(/^\s+|\s+$/g,"");
	        		if(value !="" && value.search(exp)==-1){
	        			setErrorMessage(obj,"Email输入不合法，格式为：gdcn@123.com");
	        			return false;
	        		}
	        	return true;
        	}
        	function confirmPwd(obj){
        		var fpassword = document.getElementById("fpassword");
        		var spassword = document.getElementById("spassword");
        		if(spassword.value == ""){
        			setErrorMessage(obj,"确认密码不能为空，请输入!");
        			return false;
        		}
        		
        		if(fpassword.value != spassword.value){
        				setErrorMessage(obj,"两次密码输入不一致，请重新输入！");
	        			return false;
        		}
        		return true;
        	}
        	
        	function chkExpirationDate(obj){
        		var value = obj.value; 
        		if(value == ""){
        			setErrorMessage(obj,"账户过期时间不能为空");
                    return false;
        		}
        		
        		if(new Date(value.replace(/-/g,   "/ ")) < new Date()){
                    setErrorMessage(obj,"账户过期时间不能小于当前时间");
                    return false;
                }
                return true;
        	}
        </script>
    </head>
    <body>
<%--        <form id="editForm" name="editForm" method="post" action="sysUser!save.action">--%>
        <form id="editForm" name="editForm" method="post" action="sysUser!save.action" enctype="multipart/form-data">
            <s:hidden name="userId" />
            <s:hidden name="groupId" />
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>用户基本信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                               <tr>
                                    <td class="gridtitle">账号：</td>
                                    <td class="gridbody">
                                    	<s:if test="userId==null">
                                    		<div class="editField" style="width: 200px;">
                                                    <s:textfield name="sysUser.username" id="username" maxLength="20" cssClass="inp ipt-normal"/>
                                        </div>
                                        <w:validator id="validator1" controlToValidate="sysUser.username" validateFunction="chkUsername"
                                                     ruleMessage="填写账号，不能超过20个字符" errorMessage="必填项，请填写" />
                                    	</s:if>
                                        <s:else>
                                        	<div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.username" id="username" disabled="true" cssClass="inp ipt-normal"/>
                                        </div>
                                        </s:else>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="gridtitle">中文名：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.name" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator id="validator2" controlToValidate="sysUser.name" validateFunction="chkChinaName"
                                                     ruleMessage="填写中文名，不能超过32个字符" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                <s:if test="userId == null">
                                	<tr>
                                    <td class="gridtitle">密码：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:password name="mPassword" id="fpassword" maxLength="16" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator id="validator3" controlToValidate="mPassword"  validateFunction="chkPassword"
                                                     ruleMessage="填写密码，不能超过16位" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="gridtitle">确认密码：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:password name="repassword" id="spassword"  maxLength="16" cssClass="inp ipt-normal"/>
                                        </div>
                                        <w:validator id="validator4" controlToValidate="repassword" validateFunction="confirmPwd"
                                                     ruleMessage="填写确认密码，不能超过16位" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                </s:if>
                                <s:else></s:else>
                                
                                <tr>
                                    <td class="gridtitle">邮箱地址：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.email" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator nullable="true" id="validator5" controlToValidate="sysUser.email" validateFunction="chkEmail"
                                                     ruleMessage="填写邮箱地址，邮箱格式：gdcn@123.com" errorMessage="请填写正确的邮箱格式" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">联系电话：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.phone" cssClass="inp ipt-normal" maxLength="20"/>
                                        </div>
                                        <w:validator nullable="true" id="validator6" controlToValidate="sysUser.phone" validateFunction="chkPhone"
                                                     ruleMessage="填写联系电话，格式为：010-6666666" errorMessage="联系电话格式不正确" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">联系手机：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.mobilePhone" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator nullable="true" id="validator7" controlToValidate="sysUser.mobilePhone" validateFunction="chkMobilePhone"
                                                     ruleMessage="填写联系手机，格式为：13988888888" errorMessage="联系手机格式不正确" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">账户过期时间：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <w:datepicker id="sysUser.expirationDate"  value="${sysUser.expirationDate}" />
                                        </div>
                                        <w:validator id="validator8" controlToValidate="sysUser.expirationDate" ruleMessage="请选择过期时间" validateFunction="chkExpirationDate"/>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="gridtitle">授权登陆：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:select list="enabledStatusList" listKey="key" listValue="value" name="sysUser.enableStatus" theme="simple" cssStyle="width:100px"/>
                                        </div>
                                    </td>
                                </tr>
                              
                                <tr>
                                    <td class="gridtitle">是否启用：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                        	<s:select list="accountStatusList" listKey="key" listValue="value" name="sysUser.accountStatus" theme="simple" cssStyle="width:100px"/>
                                        </div>
                                    </td>
                                </tr>
                                
<%--                                <tr>--%>
<%--                                    <td class="gridtitle">厂家：</td>--%>
<%--                                    <td class="gridbody">--%>
<%--                                        <div class="editField">--%>
<%--                                            <s:select list="manufacturesList"  listKey="id" listValue="manufacturersName" name='manufacId' cssStyle="width:200px"--%>
<%--                                            	headerKey="-1" headerValue="--请选择--" />--%>
<%--                                        </div>--%>
<%--                                        <!-- --%>
<%--                                        <w:validator id="validator3" controlToValidate="manufacId" validateFunction="chkSelect"--%>
<%--                                                     ruleMessage="选择厂家名称" errorMessage="请选择正确的厂家名称" nullable="true"/> -->--%>
<%--                                    </td>--%>
<%--                                </tr>--%>
                                
                            </table>
                            <!--确定、取消按钮-->
                            <div class="ZB_BtnBox">
                                <input type="submit"  name="button2" id="btnSubmit" value="保存" class="ZB_Btn" />
                                <input type="button" name="btnCancel" id="btnCancel" value="取消" onclick="goBackEx('menulist');" class="ZB_Btn"/>
                            </div>
                        </div>
                    </div>
                </div>
                <!--页面内容部分 end-->
            </div>
        </form>
    </body>

</html>