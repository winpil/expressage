<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>广东研究院IMS宽带多媒体终端测评系统</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/editform.js" ></script>
        <!--自定义脚本-->
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="sysCity!save.action" enctype="multipart/form-data">
            <s:hidden name="id" />
            <s:hidden name="parentId"/>
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>基本信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                                <tr>
                                    <td class="gridtitle">地市名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="sysCity.cityName" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                        <w:validator id="validator1" controlToValidate="sysCity.cityName" validateFunction="chkIsNoEmpty"
                                                     ruleMessage="填写地市名称" errorMessage="必填项，请填写" />
                                    </td>
                                </tr>
                                  <tr>
                                    <td class="gridtitle">上级地市：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="parentCity.cityName" cssClass="inp ipt-normal" disabled="true" />
                                        </div>
                                    </td>
                                </tr>
                          	  <tr>
                                    <td class="gridtitle">显示顺序：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="sysCity.sort" maxLength="9" cssClass="inp ipt-normal" />
                                        </div>
                                        <w:validator nullable="true" id="validator3" controlToValidate="sysMenu.theSort" validateFunction="chkTheSort"
                                                     ruleMessage="填写显示顺序，不能超过9位，显示顺序为0时不在菜单中显示" errorMessage="显示顺序格式不正确" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">地市描述：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textarea onchange="limitTextLength(this,500)" name="sysCity.cityDesc" theme="simple" cols="30" rows="3"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <!--确定、取消按钮-->
                            <div class="ZB_BtnBox">
                                <input type="submit"  name="button2" id="btnSubmit" value="保存" class="ZB_Btn" />
                                <input type="button" name="button" id="btnCancel" value="取消" onclick="javascript:history.back();" class="ZB_Btn"/>
                            </div>
                        </div>
                    </div>
                </div>
                <!--页面内容部分 end-->
            </div>
        </form>
    </body>

</html>