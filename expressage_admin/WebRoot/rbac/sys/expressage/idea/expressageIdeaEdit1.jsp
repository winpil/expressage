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
                            	<s:if test="expressageIdea.type == 1 ">
                                <tr>
                                    <td class="gridtitle">用户名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressageIdea.userId.userName" cssClass="inp ipt-normal"  readonly="true"/>
                                             <s:hidden name="expressageIdea.ideaId"/>
                                             <s:hidden name="expressageIdea.userId.userId" />
                                         
                                        </div>
                                    </td>
                                </tr>
                                </s:if>
                                <s:if test="expressageIdea.type == 4 ">
                                	<tr>
	                                    <td class="gridtitle">快递员名称：</td>
	                                    <td class="gridbody">
	                                        <div class="editField">
	                                            <s:textfield name="expressageIdea.courierId.courierName" cssClass="inp ipt-normal"  readonly="true"/>
	                                            <s:hidden name="expressageIdea.courierId.courierId" />
	                                        </div>
	                                    </td>
	                                </tr>
                                </s:if>
                                 <tr>
                                    <td class="gridtitle">意见内容：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageIdea.content" cssClass="inp ipt-normal"  readonly="true"/>
                                        </div>
                                    </td>
                                </tr> 
                                
                                 <tr>
                                    <td class="gridtitle">类型：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:if test="expressageIdea.type == 1 ">
                                            	用户反馈
			                                </s:if>
			                                <s:elseif test="expressageIdea.type == 2">
			                                	快递反馈
			                                </s:elseif>
			                                <s:else></s:else>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">创建时间：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<%-- <w:datepicker id="expressagePayLog.createDate"  value="${expressagePayLog.createDate}"/> --%>
                                        	<input type="text" name="expressageIdea.createDate" value="${expressageIdea.createDate}" readonly="true" />
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