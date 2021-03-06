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
    </head>
    <body>
        <form id="editForm" name="editForm" method="post">
            <s:hidden name="id" />
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>日志基本信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                                <tr>
                                    <td class="gridtitle">模块名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="sysLog.moduleName" cssClass="inp ipt-normal" readonly="true"/>
                                        </div>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="gridtitle">操作账号：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="sysLog.operAccount" cssClass="inp ipt-normal" readonly="true"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">操作详情：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textarea onchange="limitTextLength(this,80)" name="sysLog.operDetail" theme="simple" cols="45" rows="8" readonly="true"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">操作状态：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="sysLog.operStatus" cssClass="inp ipt-normal" readonly="true" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">客户端IP：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="sysLog.clientIp" cssClass="inp ipt-normal" readonly="true"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">操作时间：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:date name="sysLog.createdtime" format="yyyy-MM-dd HH:mm:ss" nice="false"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <!--确定、取消按钮-->
                            <div class="ZB_BtnBox">
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