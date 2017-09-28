<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>文本/轮播管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
        
         <script>
        	function listExpressageCourier(){
        		$('#listForm').attr("action", "expressageText!list.action").submit();	
        	}

        </script>
        
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="expressageText!list.action" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:文本/轮播管理</span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                     
                           <!--#########################工具栏#########################-->
                      
                        <!--#########################数据列表#########################-->
                        <div class="ZB_FormWarp2">
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="ZB_List">
                                <!--#########################表头#########################-->
                                <s:hidden name="page.orderBy" /><s:hidden name="page.order" />
                                <thead>
                                    <tr>
                                        <td width="20"><input type="checkbox"  name="checkItemAll" onclick="switchCheckAll(this)" /></td>
                                        <td width="30"><w:thead title="类型" sortField="type" /></td>
                                        <td width="60"><w:thead title="轮播图排序" sortField="rank" /></td>
                                        <td width="50"><w:thead title="更新时间" sortField="createDate" /></td>
                                        <td width="30" style="text-align: center;">修 改</td>                                        
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td  width="20" class="CNumber">
                                            <input type="checkbox" name="checkItem" value="<s:property value="textId" />" class="checkItem" /></td>
                                            <td><s:if test="type==1">关于我们</s:if>
                                            	<s:elseif test="type==2">常见问题</s:elseif>
                                            	<s:elseif test="type==3">价格列表</s:elseif>
                                            	<s:elseif test="type==4">轮播图</s:elseif>
                                                <s:else></s:else> 
                                            </td>
                                            <td><s:property value="rank" />&nbsp;</td>
                                            <td><s:property value="createDate" />&nbsp;</td>
                                            <td style="text-align: center;">
                                           		 <a title="修 改" href='expressageText!input.action?textId=<s:property value="textId" />'>
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