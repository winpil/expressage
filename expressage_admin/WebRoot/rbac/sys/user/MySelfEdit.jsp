<%@page import="com.cndatacom.rbac.system.service.ISysUserService"%>
<%@page import="com.cndatacom.rbac.pojo.SysUser"%>
<%@page import="com.cndatacom.rbac.pojo.SysUser"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

            SysUser loginUser = null;

            try {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();

                ISysUserService sysUserService = (ISysUserService) ctx.getBean("tsysUserService");

                loginUser = sysUserService.findUniqueByUsername(username);
            } catch (Exception e) {
            }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>快递快滴管理平台</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/editform.js" ></script>
        <!--自定义脚本-->
        <script>
            function chkConfirmPwd(obj){
                var pwd=$("[name='sysUser.password']").val();
                var conPwd=$("[name='confirmPassword']").val();
                if(pwd!=conPwd){
                    return false;
                }
                return true;
            }
			function chkEmail(obj){
	        	var value = obj.value;
        		var exp = /^[a-zA-Z]\w+@\w+[.com|.cn]+$/;
        		if(value !="" && value.search(exp)==-1){
        			setErrorMessage(obj,"Email输入不合法，格式为：gdcn@123.com");
        			return false;
        		}
	        	return true;
        	}
        </script>
        <style>

        </style>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="sysUser!editMyself.action" enctype="multipart/form-data">
            <s:hidden name="userId" />
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>修改个人信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">

                                <tr>
                                    <td class="gridtitle">用户名：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.username" disabled="true" cssClass="inp ipt-normal" />
                                        </div>                                       
                                    </td>
                                </tr>

                                <tr>
                                    <td class="gridtitle">用户昵称：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.name" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator nullable="true" id="validator4" controlToValidate="sysUser.name" validateFunction="chkIsNoEmpty"
                                                     ruleMessage="填写用户昵称，不超过10个字" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">邮箱地址：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.email" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator nullable="true" id="validator5" controlToValidate="sysUser.email" validateFunction="chkEmail"
                                                     ruleMessage="填写邮箱地址，邮箱格式：gdcn@123.com" errorMessage="请填写正确的邮箱格式" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">联系电话：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.phone" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:RegularExpressionValidator nullable="true" validationExpression="^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$" id="validator6" controlToValidate="sysUser.phone"
                                                                      ruleMessage="填写联系电话，格式0791-8888888" errorMessage="电话号码格式不正确" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">联系手机：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysUser.mobilePhone" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:RegularExpressionValidator nullable="true" id="validator7" controlToValidate="sysUser.mobilePhone"  validationExpression="^\d{11}$"
                                                                      ruleMessage="填写联系手机，11位数字" errorMessage="手机号码格式不正确" />
                                    </td>
                                </tr>
                            </table>
                            <!--确定、取消按钮-->
                            <div class="ZB_BtnBox">
                                <input type="submit"  name="button2" id="btnSubmit" value="保存" class="ZB_Btn" />
                                <input type="reset" name="button" id="btnCancel" value="重置"  class="ZB_Btn"/>
                            </div>
                        </div>
                    </div>
                </div>
                <!--页面内容部分 end-->
            </div>
            <!--#########################信息输入#########################-->
            <div style="display: none"><s:actionmessage  /><s:actionerror  /></div>
        </form>
    </body>
    <script>showActionMessage();</script>
</html>