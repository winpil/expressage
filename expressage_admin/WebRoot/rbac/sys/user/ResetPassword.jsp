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
            function chkPassword(obj){
                var value = obj.value;
                var exp = /^[\s\S]{6,20}$/;
                if(value == ""){
                	setErrorMessage(obj,"密码不能为空！");
                	return false;
                }
                if(value!="" && value.search(exp)==-1){
                	setErrorMessage(obj,"密码长度不能少于6位，不能超过20位!");
                	return false;
                }
                return true;
            }
            function chkConfirmPassword(obj){
            	var fpassword = document.getElementById("mpassword");
        		var spassword = document.getElementById("rpassword");
        		if(spassword.value == ""){
        			setErrorMessage(obj,"确认密码不能为空！");
                	return false;
        		}
        		if(fpassword.value != spassword.value){
       				setErrorMessage(obj,"两次密码输入不一致，请重新输入！");
        			return false;
        		}
        		return true;
            }
			
        </script>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="sysUser!resetPassword.action">
            <s:hidden name="userId" />
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>重置密码</span></a></li>
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
                                            <s:textfield name="sysUser.username" id="username"  disabled="true" cssClass="inp ipt-normal" />
                                        </div>                                       
                                    </td>
                                </tr>

                                                              
                                <tr>
                                    <td class="gridtitle">新密码：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:password name="mPassword" id="mPassword" maxLength="16" cssClass="inp ipt-normal" />
                                        </div> 
                                        <w:validator id="validator2" controlToValidate="mPassword" validateFunction="chkPassword"
                                                     ruleMessage="填写新密码，6-16位" errorMessage="必须项，请填写" />                                      
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="gridtitle">确认密码：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:password name="rPassword" maxLength="16"  cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator id="validator3" controlToValidate="rPassword" validateFunction="chkConfirmPassword"
                                                     ruleMessage="填写确认密码，6-16位" errorMessage="必须项，请填写" />                                          
                                    </td>
                                </tr>
                                
                                
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
            <!--#########################信息输入#########################-->
            <div style="display: none"><s:actionmessage  /><s:actionerror  /></div>
        </form>
    </body>
    <script>showActionMessage();</script>
</html>