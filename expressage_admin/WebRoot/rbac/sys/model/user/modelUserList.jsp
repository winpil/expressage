<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>用户管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
        <script>
        	function exportUser(){
        		$('#listForm').attr("action", "modelUser!exportUsers.action").submit();
        		
        	}
        	function listUser(){
        		$('#listForm').attr("action", "modelUser!list.action").submit();
        		
        	}
        	
        	
        </script>
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="modelUser!list.action" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:用户管理</span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <s:textfield name="auName"  maxlength="20" cssStyle="width:100px"/>
                                <s:select list="#{'':'全部','name':'会员名称','nickname':'昵称','phone':'手机号码'}" listKey="key" listValue="value" name="searchName" />
                                <s:select list="#{'':'会员状态','1':'是','0':'否'}" listKey="key" listValue="value" name="status" />
                                <s:select list="#{'':'类别','1':'个人认证','2':'公司认证','3':'未认证'}" listKey="key" listValue="value" name="type" />
                                <s:select list="#{'':'审核状态','0':'未审核','1':'审核通过','2':'审核不通过'}" listKey="key" listValue="value" name="auditStatus" />
                                <s:select list="#{'':'账号状态','0':'锁定','1':'启用'}" listKey="key" listValue="value" name="isLocked" />
                                <input type="button" id="btnSubmit" value="筛选" class="ZB_Btn" onclick="listUser();"/>&nbsp;&nbsp;&nbsp;
                                <input type="button" id="btnSubmit1" value="导出" class="ZB_Btn" onclick="exportUser();"/>
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
                                        <td width="20"><w:thead title="名称" sortField="name" /></td>
                                        <td width="20"><w:thead title="手机" sortField="phone" /></td>
                                        <td width="20"><w:thead title="性别" sortField="gender" /></td>
                                        <td width="20"><w:thead title="类别" sortField="type" /></td>
                                        <td width="20"><w:thead title="是否会员" sortField="status" /></td>
                                        <td width="40"><w:thead title="会员过期时间" sortField="memberTime" /></td>
                                        <td width="20"><w:thead title="收到鲜花" sortField="receiveFlowers" /></td>
                                        <td width="20"><w:thead title="收到鸡蛋" sortField="receiveEggs" /></td>
                                        <td width="40"><w:thead title="创建时间" sortField="createDate" /></td>
                                        <td width="40"><w:thead title="账号状态" sortField="isLocked" /></td>
                                        <td width="20"><w:thead title="审核状态" sortField="auditStatus" /></td>
                                        <td width="20" style="text-align: center;">修 改</td>                                        
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="10" class="CNumber">
                                            <input type="checkbox" name="checkItem" value="<s:property value="userId" />" class="checkItem" /></td>
                                            <td><s:property value="name" />&nbsp;</td>
                                            <td><s:property value="phone" />&nbsp;</td>
                                            <td>
                                            <s:if test="gender==1">男</s:if>
                                            <s:else>女</s:else>
                                            </td>
                                            <td>
                                            <s:if test="type==1">个人认证</s:if>
                                            <s:elseif test="type==2">公司认证</s:elseif>
                                            <s:else>未认证</s:else>
                                            </td>
                                            <td>
                                            <s:if test="status==3||status==5">是</s:if>
                                            <s:else>否</s:else>
                                            </td>
                                            <td><s:property value="memberTime" />&nbsp;</td>
                                            <td><s:property value="receiveFlowers" />&nbsp;</td>
                                            <td><s:property value="receiveEggs" />&nbsp;</td>
                                            <td><s:property value="createDate" />&nbsp;</td>
                                            <td>
                                            <s:if test="isLocked==0">锁定</s:if>
                                            <s:else>启用</s:else>
                                            </td>
                                            <td>
                                            <s:if test="auditStatus==0">未审核</s:if>
                                            <s:elseif test="auditStatus==1">审核通过</s:elseif>
                                            <s:elseif test="auditStatus==2">审核不通过</s:elseif>
                                            <s:else></s:else>
                                            </td>
                                            <td style="text-align: center;">
                                           		 <a title="修 改" href='modelUser!input.action?id=<s:property value="userId" />'>
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