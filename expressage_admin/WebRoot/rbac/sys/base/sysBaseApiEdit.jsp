<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>接口</title>
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
        <form id="editForm" name="editForm" method="post" action="sysBase!save.action" enctype="multipart/form-data">
            
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>接口基本信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                               <tr>
                                    <td class="gridtitle">接口名：</td>
                                    <td class="gridbody">
                                    	<div class="editField" style="width: 200px;">
                                                    <s:textfield name="sysBase.apiName" id="username" maxLength="20" cssClass="inp ipt-normal"/>
                                                    <s:if test="sysBase.baseId!=null">
                                                    	<s:hidden name="sysBase.baseId" />
                                                    </s:if>
                                                    
	                                        </div>
	                                        <w:validator id="validator1" controlToValidate="sysBase.apiName" validateFunction="chkUsername"
	                                                     ruleMessage="接口名" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="gridtitle">参数数量：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.parameterNum" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">参数1：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.paramenter1" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">参数2：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.paramenter2" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">参数3：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.paramenter3" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">参数4：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.paramenter4" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">参数5：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.paramenter5" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">页码：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.pages" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">页数：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.pageSize" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">描述：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textfield name="sysBase.depict" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">sql：</td>
                                    <td class="gridbody">
                                        <div class="editField" style="width: 200px;">
                                            <s:textarea name="sysBase.sqlName"  cssClass="inp ipt-normal" />
                                        </div>
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
        </form>
    </body>

</html>