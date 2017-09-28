<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="mailReceiverRelation!findByMailId.action">
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>邮件发送结果</span></a></li>
                            </ul>
                        </div>
                        <!--#########################数据列表#########################-->
                        <div class="ZB_FormWarp2">
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="ZB_List">
                                <!--#########################表头#########################-->
                                <s:hidden name="page.orderBy" /><s:hidden name="page.order" />
                                <s:hidden name="mailReceiverRelation.mailSent.id" value="%{mailReceiverRelation.mailSent.id}"/>
                                <thead>
                                    <tr>
                                    	<td width="80"><w:thead title="邮件编号" sortField="mailSent.id" /></td>
                                        <td width="150"><w:thead title="邮件标题" sortField="mailSent.title" /></td>
                                        <td width="80"><w:thead title="发件人" sortField="mailSent.sender" /></td>
                                        <td width="80"><w:thead title="收件人" sortField="mailReceiver" /></td>
                                        <td width="80"><w:thead title="发送时间" sortField="sendTime" /></td>
                                        <td width="80"><w:thead title="发送结果" sortField="sendResult"/></td>
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td><s:property value="mailSent.id" /></td>
                                            <td><s:property value="mailSent.title" /></td>
                                            <td><s:property value="mailSent.sender" /></td>
                                            <td>
	                                            <s:iterator value="mailSent.relations">
	                                            	<s:if test="regReceiver != null">
	                                            		<s:property value="regReceiver.name"/>;
	                                            	</s:if>
	                                            	<s:else>
	                                            		<s:property value="unregReceiver.nickname"/>;
	                                            	</s:else>
	                                            </s:iterator>
                                            </td>
                                            <td><s:date name="sendTime" format="yyyy-MM-dd HH:mm:ss" nice="false" /></td>
                                            <td style="text-align: center;">
                                            	<s:if test="sendResult == 1">成功</s:if>
                                            	<s:else>失败</s:else>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                        <!--#########################分页控件#########################-->
                        <div class="ZB_PageBox">
                            <div>
                                <w:Pagination id="Pagination1" currentPageNo="${page.pageNo}" pageSize="${page.pageSize}" totalCount="${page.totalCount}" totalPage="${page.totalPages}" />
                            </div>
                        </div>

                    </div>
                </div>
                <!--#########################页面内容部分 end#########################-->
            </div>
            <!--#########################信息输入#########################-->
            <div style="display: none"><s:actionmessage  /><s:actionerror  /></div>
        </form>
    </body>
</html>