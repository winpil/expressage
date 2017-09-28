<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>音频管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="gktfAudio!list.action?dtId=1" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:电台音频</span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################功能按钮#########################-->
                            <div class="ZB_FormFunction">
                                <div class="add"><a href='gktfAudio!input.action?dtId=1'>添加</a></div>
                                <div class="delete">
                                    <input type="hidden" id="ids" name="ids" />
                                    <a href="#" onclick="confirmDelete('gktfAudio!delete.action?dtId=1')">删除</a>
                                </div>
                            </div>

                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <w:simpleQueryHelper id="simpleQueryHelper1" name="simpleQueryHelper1" checkItems="${checkItems}" itemsValue="${itemsValue}" type="ddl" >
                                   <w:queryfield title="全部" fieldname="" datatype=""  matchtype="" />
                                    <w:queryfield title="音频名称" fieldname="name" datatype="String"  matchtype="LIKE" />
                                    <w:queryfield title="音频字幕" fieldname="content" datatype="String"  matchtype="LIKE" />
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
                                        <td width="10"><input type="checkbox"  name="checkItemAll" onclick="switchCheckAll(this)" /></td>
                                        <td width="50"><w:thead title="名称" sortField="name" /></td>
                                        <td width="20"><w:thead title="科目" sortField="" /></td>
                                        <td width="20"><w:thead title="专辑" sortField="albumId.id" /></td>
                                        <td width="20"><w:thead title="排序标识" sortField="rank" /></td>
                                        <td width="20"><w:thead title="下载数" sortField="download" /></td>
                                        <td width="20"><w:thead title="收藏数" sortField="collection" /></td>
                                        <td width="20"><w:thead title="分享数" sortField="share" /></td>
                                        <td width="20"><w:thead title="点赞数" sortField="praise" /></td>
                                        <td width="20"><w:thead title="状态" sortField="status" /></td>
                                        <td width="30"><w:thead title="创建时间" sortField="createTime" /></td>
                                        <td width="30"><w:thead title="上架时间" sortField="pushTime" /></td>
                                        <td width="20" style="text-align: center;">修 改</td>                                        
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                    	<s:if test="albumId.subjectId.id == 0">
                                    		<tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
	                                            <td width="10" class="CNumber">
	                                            <input type="checkbox" name="checkItem" value="<s:property value="id" />" class="checkItem" /></td>
	                                            <td width="10"><s:property value="name" />&nbsp;</td>
	                                            <td width="10"><s:property value="albumId.subjectId.name" />&nbsp;</td>
	                                            <td><s:property value="albumId.name" />&nbsp;</td>
	                                            <td><s:property value="rank" />&nbsp;</td>
	                                            <td><s:property value="download" />&nbsp;</td>
	                                            <td><s:property value="collection" />&nbsp;</td>
	                                            <td><s:property value="share" />&nbsp;</td>
	                                            <td><s:property value="praise" />&nbsp;</td>
	                                            <td>
	                                            <s:if test="status==1">上架</s:if>
	                                            <s:else>下架</s:else>
	                                            </td>
	                                            <td><s:property value="createTime" />&nbsp;</td>
	                                            <td><s:property value="pushTime" />&nbsp;</td>
	                                            <td style="text-align: center;">
	                                           		 <a title="修 改" href='gktfAudio!input.action?id=<s:property value="id" />&dtId=1'>
	                                          			 <img src="../../images/icon_45.gif" />
	                                          		 </a>
	                                            </td>
	                                        </tr>
                                    	</s:if>
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