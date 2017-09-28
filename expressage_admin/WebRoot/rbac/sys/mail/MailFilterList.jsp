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
        <form id="listForm" name="listForm" method="post" action="sysMailFilter!list.action">
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span id="navTitle"></span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################功能按钮#########################-->
                            <div class="ZB_FormFunction">
                                <div class="add"><a href="sysMailFilter!input.action"/>添加</a></div>
                                <div class="delete">
                                    <input type="hidden" id="ids" name="ids" />
                                    <a href="#" onclick="confirmDelete('sysMailFilter!delete.action')">删除</a>
                                </div>
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
                                        <td width="100"><w:thead title="过滤器名称" sortField="name" /></td>
                                        <td width="350"><w:thead title="收件人过滤" sortField="toEmailFilter" /></td>
                                        <td width="150"><w:thead title="收件人是否模糊匹配" sortField="tefIsBlur" /></td>
                                        <td width="350"><w:thead title="标题过滤" sortField="titleFilter" /></td>
                                        <td width="150"><w:thead title="标题是否模糊匹配" sortField="tfIsBlur" /></td>
                                        <td width="48" style="text-align: center;">修改</td>
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="20" class="CNumber"><input type="checkbox" name="checkItem" value="<s:property value="id" />" class="checkItem" /></td>
                                            <td><s:property value="name" />&nbsp;</td>
                                            <td><s:property value="toEmailFilter" />&nbsp;</td>
                                            <td><s:if test="tefIsBlur == 1">是</s:if><s:else>否</s:else></td>
                                            <td><s:property value="titleFilter" />&nbsp;</td>
                                            <td><s:if test="tfIsBlur == 1">是</s:if><s:else>否</s:else></td>
                                            <td style="text-align: center;">
                                            	<a href='sysMailFilter!input.action?id=<s:property value="id" />'>
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