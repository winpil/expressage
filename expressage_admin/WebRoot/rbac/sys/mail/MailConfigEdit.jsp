<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <jsp:include page="/common/framecommon.jsp" />
        <script type="text/javascript" src="../../js/frame/editform.js" ></script>
        <script type="text/javascript">
        	function chkEmail(obj){
	        	var value = obj.value;
	        	if(value == ""){
        			setErrorMessage(obj,"Email不能为空，请输入!");
        			return false;
        		}
        		var exp = /^[a-zA-Z]\w+@\w+[.com|.cn]+$/;
        		if(value.search(exp)==-1){
        			setErrorMessage(obj,"Email输入不合法，格式为：gdcn@123.com");
        			return false;
        		}
	        	return true;
        	}
        	
        	function chkPassword(obj){
        		var value = obj.value;
        		var exp = /^[\s\S]{6,16}$/;
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
        	
        	function confirmPwd(obj){
        		var password = document.getElementById("password");
        		var confirm = document.getElementById("confirm");
        		if(confirm.value == ""){
        			setErrorMessage(obj,"确认密码不能为空，请输入!");
        			return false;
        		}
        		// if(confirm.value.search(exp)==-1){
        		// 	setErrorMessage(obj,"密码长度不能少于6位，不能超过16位!");
        		// 	return false;
        		// }
        		if(password.value != confirm.value){
        				setErrorMessage(obj,"两次密码输入不一致，请重新输入！");
	        			return false;
        		}
        		return true;
        	}
        	
        	function chkSmtp(obj){
        		var value = obj.value;
        		if(value == ""){
        			setErrorMessage(obj,"smtp不能为空");
        			return false;
        		}
        		// var exp = /^[a-zA-Z][a-zA-Z0-9]{0,29}$/;
        		if (value.length > 30) {
        			setErrorMessage(obj,"发送邮件服务器(SMTP)，不能超过30个字符");
        			return false;
        		}
        		return true;
        	}
        </script>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="sysMailConfig!save.action">
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!-- 导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>电子邮件配置参数基本信息</span></a></li>
                            </ul>
                        </div>

                        <!-- 输入字段-->
                        <div class="ZB_FormWarp2">
                            <!-- 数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                            	<s:actionmessage/>
                               <tr>
                                   <td class="gridtitle">电子邮件地址：</td>
                                   <td class="gridbody">
                                       <div class="editField" style="width: 200px;">
                                           <s:textfield name="address" maxLength="32" cssClass="inp ipt-normal"/>
                                       </div>
                                       <w:validator id="validator5" controlToValidate="address" validateFunction="chkEmail"
                                       		ruleMessage="填写电子邮件地址，邮箱格式：gdcn@123.com" />
                                   </td>
                               </tr>
                               <tr>
                                    <td class="gridtitle">密码：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:password name="password" id="password" maxLength="16" cssClass="inp ipt-normal"/>
                                        </div>
                                        <w:validator id="validator3" controlToValidate="password"  validateFunction="chkPassword"
                                        	ruleMessage="填写密码，不能超过16位"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">确认密码：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:password name="confirm" id="confirm"  maxLength="16" cssClass="inp ipt-normal"/>
                                        </div>
                                        <w:validator id="validator4" controlToValidate="confirm" validateFunction="confirmPwd"
                                        	ruleMessage="填写确认密码，不能超过16位" />
                                    </td>
                                </tr>
                               <tr>
                                    <td class="gridtitle">发送邮件服务器(SMTP)：</td>
                                    <td class="gridbody">
                                    	<div class="editField" style="width: 200px;">
                                        	<s:textfield name="smtp" id="smtp" maxLength="30" cssClass="inp ipt-normal"/>
                                        </div>
                                        <w:validator id="validator1" controlToValidate="smtp" validateFunction="chkSmtp"
                                        	ruleMessage="填写发送邮件服务器(SMTP)，不能超过30个字符" />
                                    </td>
                                </tr>
                            </table>
                            <!--确定、取消按钮-->
                            <div class="ZB_BtnBox">
                                <input type="submit"  name="button2" id="btnSubmit" value="保存" class="ZB_Btn" />
                                <!-- <input type="button" name="btnCancel" id="btnCancel" value="取消" onclick="goBack();" class="ZB_Btn"/>
                                <a href="mail/SendMail.jsp">发送邮件</a> -->
                            </div>
                        </div>
                    </div>
                </div>
                <!--页面内容部分 end-->
            </div>
        </form>
    </body>

</html>