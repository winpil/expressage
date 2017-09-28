<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js"></script>
        <script type="text/javascript">
        	$(document).ready(function() {
        		var attachmentNameHiddens = $("input[name='attachmentName']");
        		if (attachmentNameHiddens) {
        			for (var i = 0; i < attachmentNameHiddens.length; i++) {
		        		var attachmentStr = $(attachmentNameHiddens[i]).val();
		        		if ($(attachmentNameHiddens[i]).val() != '') {
	        				var attachNameArray = attachmentStr.split(";");
	        				for (var j = 0; j < attachNameArray.length; j++) {
			        			var td = $(attachmentNameHiddens[i]).parent();
			        			var idHidden = td.children("input[name='id']");
			        			td.append("<a href='attachmentDownload.action?mailId=" + idHidden.val() 
			        				+ "&attachmentIndex=" + j + "'>" + attachNameArray[j] + "</a>;");
	        				}
		        		}
        			}
        		}
        	});
        </script>
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="mailSent!list.action">
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>已发送的邮件</span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################功能按钮#########################-->
                            <div class="ZB_FormFunction">
                                <div class="delete">
                                    <input type="hidden" id="ids" name="ids" />
                                    <a href="#" onclick="confirmDelete('mailSent!delete.action')">删除</a>
                                </div>
                            </div>

                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <w:simpleQueryHelper id="simpleQueryHelper1" name="simpleQueryHelper1" checkItems="${checkItems}" itemsValue="${itemsValue}" type="ddl" >
                                    <w:queryfield title="发件人" fieldname="sender" datatype="String"  matchtype="LIKE" />
                                    <w:queryfield title="标题" fieldname="title" datatype="String"  matchtype="LIKE" />
                                </w:simpleQueryHelper>
                            </div>
                        </div>
                        <!--#########################数据列表#########################-->
                        <div class="ZB_FormWarp2">
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="ZB_List">
                                <!--#########################表头#########################-->
                                <s:hidden name="page.orderBy" /><s:hidden name="page.order" />
                                <thead>
                                    <tr>
                                        <td width="20"><input type="checkbox"  name="checkItemAll" onclick="switchCheckAll(this)" /></td>
                                        <td width="60"><w:thead title="发件人" sortField="sender" /></td>
                                        <td width="100"><w:thead title="收件人" sortField="receivers" /></td>
                                        <td width="150"><w:thead title="邮件标题" sortField="title" /></td>
                                        <td width="90"><w:thead title="发送时间" sortField="sendTime" /></td>
                                        <td width="150"><w:thead title="附件" sortField="attachmentName" /></td>
                                        <td width="60"><w:thead title="发送结果" sortField=""/></td>
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="20" class="CNumber"><input type="checkbox" name="checkItem" value="<s:property value="id" />" class="checkItem" /></td>
                                            <td><s:property value="sender" /></td>
                                            <td>
	                                            <s:iterator value="relations">
	                                            	<s:if test="regReceiver != null">
	                                            		<s:property value="regReceiver.name"/>;
	                                            	</s:if>
	                                            	<s:else>
	                                            		<s:property value="unregReceiver.nickname"/>;
	                                            	</s:else>
	                                            </s:iterator>
                                            </td>
                                            <td><s:property value="title" /></td>
                                            <td><s:date name="sendTime" format="yyyy-MM-dd HH:mm:ss" nice="false" /></td>
                                            <td style="text-align: center;">
                                            	<s:if test="attachmentName != null && attachmentName != ''">
                                            		<s:hidden name="id"/>
                                            		<s:hidden name="attachmentName"/>
                                            	</s:if>
                                            	<s:else>无</s:else>
                                            </td>
                                            <td style="text-align: center;">
                                            	<a href='mailReceiverRelation!findByMailId.action?mailReceiverRelation.mailSent.id=<s:property value="id" />' title="查看发送结果">
                                            	<img src="../../images/rbac/table_16x16.gif" />
                                            	</a>
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