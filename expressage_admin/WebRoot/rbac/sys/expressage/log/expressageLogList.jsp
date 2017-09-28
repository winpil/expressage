<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>用户日志管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
        
         <script>
        	function listExpressageLog(){
        		$('#listForm').attr("action", "expressageLog!list.action").submit();	
        	}

        </script>
        
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="expressageLog!list.action" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:用户日志管理</span></a></li>
                            </ul>
                        </div>
                    
                        <!--#########################数据列表#########################-->
                        <div class="ZB_FormWarp2">
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="ZB_List">
                                <!--#########################表头#########################-->
                                <s:hidden name="page.orderBy" /><s:hidden name="page.order" />
                                <thead>
                                    <tr>
                                        <td width="10"><input type="checkbox"  name="checkItemAll" onclick="switchCheckAll(this)" /></td>
                                        <td width="20"><w:thead title="方法名" sortField=" method" /></td>
                                        <td width="20"><w:thead title="操作内容" sortField="content" /></td>
                                        <td width="20"><w:thead title="操作状态" sortField=" status" /></td>
                                       	<td width="20"><w:thead title="用户名称" sortField="userId.userName" /></td>
                                        <td width="20"><w:thead title="快递员名称" sortField="courierId.courierName" /></td> 
                                        <td width="20"><w:thead title="创建时间" sortField="createDate" /></td>
                                        <td width="20" style="text-align: center;">修 改</td>                                        
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="10" class="CNumber">
                                            <input type="checkbox" name="checkItem" value="<s:property value="logId" />" class="checkItem" /></td>
                                            <td><s:property value="method" />&nbsp;</td>              
                                            <td><s:property value="content" />&nbsp;</td>
                                            <td><s:property value="status" />&nbsp;</td>
                                            <td><s:property value="userId.userName" />&nbsp;</td>
                                            <td><s:property value="courierId.courierName" />&nbsp;</td>
                                            <td><s:property value="createDate" />&nbsp;</td>
                                 			<td style="text-align: center;">
                                           		 <a title="修 改" href='expressageLog!input.action?logId=<s:property value="logId" />'>
                                          			 <img src="../../images/icon_45.gif" />
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