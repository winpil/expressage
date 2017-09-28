<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>快递员管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
        
         <script>
        	function listExpressageOrder(){
        		$('#listForm').attr("action", "expressageOrderLog!list.action").submit();	
        	}
			
			function exportExpressageOrder(){
        		$('#listForm').attr("action", "expressageOrder!exportOrders.action").submit();
        		
        	}
        </script>
        
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="expressageOrderLog!list.action" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:订单管理</span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                     
                           <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <s:textfield name="auName"  maxlength="20" cssStyle="width:100px"/>
                                <s:select list="#{'':'全部','orderId':'订单号'}" listKey="key" listValue="value" name="searchName" />
                                <input type="button" id="btnSubmit" value="筛选" class="ZB_Btn" onclick="listExpressageOrder();"/>&nbsp;&nbsp;&nbsp;
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
                                        <td width="40"><w:thead title="订单号" sortField=" orderId" /></td>
                                        <td width="50"><w:thead title="日志内容" sortField="content" /></td>
               							<td width="40"><w:thead title="方法名称" sortField="method" /></td>
                                        <td width="50"><w:thead title="创建时间" sortField="createDate" /></td>
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="20" class="CNumber">
                                            <input type="checkbox" name="checkItem" value="<s:property value="orderLogId" />" class="checkItem" /></td>
                                            <td><s:property value="orderId" />&nbsp;</td>
                                            <td><s:property value="content" />&nbsp;</td>                                                                                        
                                            <td><s:property value="method" />&nbsp;</td>
                                            <td><s:property value="createDate" />&nbsp;</td>
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