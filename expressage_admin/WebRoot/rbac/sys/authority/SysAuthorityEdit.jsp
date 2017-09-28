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
        	function chkType(obj){
        		if(obj.value==0){
        			document.getElementById('authorityUrl').value="";
        			document.getElementById('authorityUrl').disabled=false;
        		}else{
        			document.getElementById('authorityUrl').value="";
        			document.getElementById('authorityUrl').disabled=true;
        		}
        	}
        	
        	function chkUrl(obj){
				var value = obj.value;
				var exp = /^\/.+$/;
				if(value == ""){
					setErrorMessage(obj,"权限URL不能为空");
                    return false;
				}
				
				if(value != "" && value.search(exp)==-1){
					setErrorMessage(obj,"URL格式形如：/abc/abc.jsp?abc=abc，请重新填写");
                    return false;
				}
				return true;
			}
        </script>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="sysAuthority!save.action">
            <s:hidden name="authorityId" />
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>权限基本信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                                
                                <tr>
                                    <td class="gridtitle">菜单列表：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:select list="sysMenuService.findLeafSysMenu()" listKey="id" listValue="menuName" name="sysMenuId" cssClass="ZB_FormSelect" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">权限名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="sysAuthority.authorityName" maxLength="32" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator id="validator2" controlToValidate="sysAuthority.authorityName" validateFunction="chkIsNoEmpty"
                                                     ruleMessage="填写权限名称，不能超过32个字符" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">权限类型：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:select list="allResults" listKey="typeCode" listValue="typeName" id="authorityType" name="authorityType" onchange="chkType(this)" cssClass="ZB_FormSelect"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">权限URL：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="sysAuthority.authorityUrl" maxLength="80" id="authorityUrl" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator  id="validator4" controlToValidate="sysAuthority.authorityUrl"  validateFunction="chkUrl"
                                                     ruleMessage="填写权限URL，以/开头" errorMessage="权限URL格式不正确" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">权限说明：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textarea onchange="limitTextLength(this,80)" name="sysAuthority.authorityNote" theme="simple" rows="4" cols="41"/>
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