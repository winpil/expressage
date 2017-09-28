<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>sysLog管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="sysLog!list.action" >
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
                                <div class="delete">
                                    <input type="hidden" id="ids" name="ids" />
                                    <a href="#" onclick="confirmDelete('sysLog!delete.action')">删除</a>
                                </div>
                            </div>

                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <w:simpleQueryHelper id="simpleQueryHelper1" name="simpleQueryHelper1" checkItems="${checkItems}" itemsValue="${itemsValue}" type="ddl" >
                                    <w:queryfield title="模块名称" fieldname="moduleName" datatype="String"  matchtype="LIKE" />
                                    <w:queryfield title="操作账号" fieldname="operAccount" datatype="String"  matchtype="LIKE" />
                                    <w:queryfield title="操作状态" fieldname="operStatus" datatype="String"  matchtype="LIKE" />
                                    <w:queryfield title="客户端IP" fieldname="clientIp" datatype="String"  matchtype="LIKE" />
                                    <w:queryfield title="操作时间" fieldname="createdtime" datatype="Serializable"  matchtype="LIKE" />
                                </w:simpleQueryHelper>
                                <a href="#" class="ZB_blueDown" id="btnAdvSearch" onclick="switchAdvSearch();">高级搜索 </a>
                            </div>
                        </div>
                        <!--#########################高级搜索 start#########################-->
                        <div class="ZB_FormFunctionBox2" id="advSearchDiv" style="display: none;" >
                            <w:advQueryHelper id="advQueryHelper1" />
                        </div>
                        <!--#########################高级搜索 end#########################-->

                        <!--#########################状态过滤栏#########################-->
                        <!--<div class="ZB_FormFunctionBox">
                           <div class="ZB_FormFunction"><font style="color: red">状态：</font>参考ProductInfoList页面&nbsp;&nbsp;</div>
                       </div>-->

                        <!--#########################数据列表#########################-->
                        <div class="ZB_FormWarp2">
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="ZB_List">
                                <!--#########################表头#########################-->
                                <s:hidden name="page.orderBy" /><s:hidden name="page.order" />
                                <thead>
                                    <tr>
                                        <td width="20"><input type="checkbox"  name="checkItemAll" onclick="switchCheckAll(this)" /></td>
                                        <td width="80"><w:thead title="模块名称" sortField="moduleName" /></td>
                                        <td width="80"><w:thead title="操作账号" sortField="operAccount" /></td>
                                        <td width="80"><w:thead title="操作状态" sortField="operStatus" /></td>
                                        <td width="80"><w:thead title="客户端IP" sortField="clientIp" /></td>
                                        <td width="160"><w:thead title="操作时间" sortField="createdtime" /></td>
                                        <td width="48" style="text-align: center;">查看</td>
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="20" class="CNumber"><input type="checkbox" name="checkItem" value="<s:property value="id" />" class="checkItem" /></td>
                                            <td><s:property value="moduleName" />&nbsp;</td>
                                            <td><s:property value="operAccount" />&nbsp;</td>
                                            <td><s:property value="operStatus" />&nbsp;</td>
                                            <td><s:property value="clientIp" />&nbsp;</td>
                                            <td><s:date name="createdtime"  format="yyyy-MM-dd HH:mm:ss" nice="false" />&nbsp;</td>
                                            <td style="text-align: center;"><a href='sysLog!input.action?id=<s:property value="id" />'><img src="../../images/icon_45.gif" /></a></td>
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