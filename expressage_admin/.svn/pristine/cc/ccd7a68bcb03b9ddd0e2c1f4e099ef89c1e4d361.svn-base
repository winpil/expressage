<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>快递快滴管理平台</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/editform.js" ></script>
        <!--自定义脚本-->
        
        <link rel="stylesheet" href="../../js/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="../../js/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="../../js/kindeditor/kindeditor.js">
	</script>
	<script charset="utf-8" src="../../js/kindeditor/lang/zh_CN.js">
	</script>
	<script charset="utf-8" src="../../js/kindeditor/plugins/code/prettify.js">
	</script>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="expressagePayLog!save.action" enctype="multipart/form-data">
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
                                    <td class="gridtitle">用户名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressagePayLog.userId.userName" cssClass="inp ipt-normal" maxlength="20" readonly="true"/>
                                             <s:if test="expressagePayLog.payId != null && expressagePayLog.payId != '' ">
                                             <s:hidden name="expressagePayLog.payId"/> </s:if> 
                                             <s:hidden name="expressagePayLog.userId.userId" />
                                         
                                        </div>
                                    </td>
                                </tr>
                          	  	<tr>
                                    <td class="gridtitle">快递员名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressagePayLog.courierId.courierName" cssClass="inp ipt-normal" maxlength="20" readonly="true"/>
                                            <s:hidden name="expressagePayLog.courierId.courierId" />
                                        </div>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="gridtitle">支付方式：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<%-- <s:select list="#{'1':'现金支付','2':'微信支付','3':'支付宝支付','4':'提现','5':'奖励'}" listKey="key" listValue="value" name="type" disabled="disabled" /> --%>
                                        	<c:choose>
                                        		<c:when test="${expressagePayLog.type == 1 }">
                                        			<input type="text" value="现金支付" readonly="true"/>
												</c:when>
                                        		<c:when test="${expressagePayLog.type == 2 }">
                                        			<input type="text" value="微信支付" readonly="true"/>
                                        		</c:when>
                                        		<c:when test="${expressagePayLog.type == 3 }">
                                        			<input type="text" value="支付宝支付" readonly="true"/>
                                        		</c:when>
                                        		<c:when test="${expressagePayLog.type == 4 }">
													<input type="text" value="提现" readonly="true"/>
												</c:when>
												<c:when test="${expressagePayLog.type == 5 }">
													<input type="text" value="奖励" readonly="true"/>
												</c:when>
                                        	</c:choose>
                                        </div>
                                    </td>
                                </tr> 
                                
                                 <tr>
                                    <td class="gridtitle">金额：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressagePayLog.moneyNum" cssClass="inp ipt-normal" maxlength="20" readonly="true"/>
                                        </div>
                                    </td>
                                </tr>
             				     <tr>
                                    <td class="gridtitle">交易状态：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<c:choose>
                                        		<c:when test="${expressagePayLog.type == 4 && expressagePayLog.status == 2}">
                                        			<%-- <s:select list="#{'1':'成功','0':'失败','2':'进行中'}" listKey="key" listValue="value" name="status" readonly="true"/> --%>
                                        			<select name="status">
                                        				<option value="1">成功</option>
                                        				<option value="0">失败</option>
                                        				<option value="2" selected="selected">进行中</option>
                                        			</select>
                                        		</c:when>
                                        		<c:otherwise>
                                        			<c:choose>
                                        				<c:when test="${expressagePayLog.status == 1 }">
                                        					<input type="text" value="成功" readonly="true"/>
                                        					<input name="status" type="hidden" value="1"/>
                                        				</c:when>
                                        				<c:when test="${expressagePayLog.status == 0 }">
                                        					<input type="text" value="失败" readonly="true"/>
                                        					<input name="status" type="hidden" value="0"/>
                                        				</c:when>
                                        				<c:when test="${expressagePayLog.status == 2 }">
                                        					<input type="text" value="进行中" readonly="true"/>
                                        					<input name="status" type="hidden" value="6"/>
                                        				</c:when>
                                        			</c:choose>
                                        		</c:otherwise>
                                        	</c:choose>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">银行名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressagePayLog.bankName" cssClass="inp ipt-normal" maxlength="50" readonly="true"/>
                                            <s:hidden name="expressagePayLog.remark" />
                                            <s:hidden name="expressagePayLog.type" />
                                           
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">银行卡号：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressagePayLog.bankCode" cssClass="inp ipt-normal" maxlength="50" readonly="true"/>
                                           
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">创建时间：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<%-- <w:datepicker id="expressagePayLog.createDate"  value="${expressagePayLog.createDate}"/> --%>
                                        	<input type="text" name="expressagePayLog.createDate" value="${expressagePayLog.createDate}" readonly="true" />
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