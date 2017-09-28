<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>sysCity管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="sysCity!list.action?id=<s:property value="parentId"/>" >
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
                                <div class="add"><a href='sysCity!input.action?parentId=<s:property value="parentId" />'>添加</a></div>
                                <div class="delete">
                                    <input type="hidden" id="ids" name="ids" />
                                    <a href="#" onclick="confirmDelete('sysCity!delete.action')">删除</a>
                                </div>
                            </div>

                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <w:simpleQueryHelper id="simpleQueryHelper1" name="simpleQueryHelper1" checkItems="${checkItems}" itemsValue="${itemsValue}" type="ddl" >
                                   <w:queryfield title="全部" fieldname="" datatype=""  matchtype="" />
                                    <w:queryfield title="cityName" fieldname="cityName" datatype="String"  matchtype="LIKE" />
                                    <w:queryfield title="sort" fieldname="sort" datatype="Long"  matchtype="LIKE" />
                                    <w:queryfield title="cityDesc" fieldname="cityDesc" datatype="String"  matchtype="LIKE" />
                                    <w:queryfield title="parentId" fieldname="parentId" datatype="String"  matchtype="LIKE" />
                                <w:queryfield title="---模糊查询---" fieldname="" datatype="All"  matchtype="OR" />
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
                            <div class="ZB_FormFunction"><font style="color: red">状态：</font>参考ProductInfoList页面(enumList id="filter_EQL_status" enumInstance="productStatuEnum" )&nbsp;&nbsp;</div>
                        </div>-->

                        <!--#########################数据列表#########################-->
                        <div class="ZB_FormWarp2">
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="ZB_List">
                                <!--#########################表头#########################-->
                                <s:hidden name="page.orderBy" /><s:hidden name="page.order" />
                                <thead>
                                    <tr>
                                        <td width="20"><input type="checkbox"  name="checkItemAll" onclick="switchCheckAll(this)" /></td>
                                        <td width="80"><w:thead title="地市名称" sortField="cityName" /></td>
                                        <td width="80"><w:thead title="显示顺序" sortField="sort" /></td>
                                        <td width="80"><w:thead title="地市描述" sortField="cityDesc" /></td>
                                        <td width="48" style="text-align: center;">修改</td>                                        
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="20" class="CNumber"><input type="checkbox" name="checkItem" value="<s:property value="id" />" class="checkItem" /></td>
                                            <td><s:property value="cityName" />&nbsp;</td>
                                            <td><s:property value="sort" />&nbsp;</td>
                                            <td><s:property value="cityDesc" />&nbsp;</td>
                                            <td style="text-align: center;">
                                           		 <a title="修改" href='sysCity!input.action?id=<s:property value="id" />&parentId=<s:property value="parentId" />'>
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