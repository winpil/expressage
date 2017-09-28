<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>网点管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
        
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="expressageBranch!list.action" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:网点管理</span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################功能按钮#########################-->
                            <div class="ZB_FormFunction">
                                	<div class="add"><a href='expressageBranch!input.action'>添加</a></div>
                                <div class="delete">
                                    <input type="hidden" id="ids" name="ids" />
                                     <a href="#" onclick="confirmDelete('expressageBranch!delete.action')">删除</a>
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
                                        <td width="10"><input type="checkbox"  name="checkItemAll" onclick="switchCheckAll(this)" /></td>
                                        <td width="20"><w:thead title="网点名称" sortField="branchName" /></td>
                                        <td width="20"><w:thead title="公司名称" sortField="companyId.companyName" /></td>
               							<td width="20"><w:thead title="描述" sortField="introduce" /></td>
               							<td width="20"><w:thead title="省份" sortField="provincialId.name" /></td>
               							<td width="20"><w:thead title="城市" sortField="cityId.name" /></td>
               							<td width="20"><w:thead title="区域" sortField="districtId.name" /></td>
               							<td width="20"><w:thead title="详细地址" sortField="addressId" /></td>
                                        <td width="20" style="text-align: center;">修 改</td>                                        
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="10" class="CNumber">
                                            <input type="checkbox" name="checkItem" value="<s:property value="branchId" />" class="checkItem" /></td>
                                            <td><s:property value="branchName" />&nbsp;</td>
                                            <td><s:property value="companyId.companyName" />&nbsp;</td>                                                                                        
                                            <td><s:property value="introduce" />&nbsp;</td>                                                                                      
                                            <td><s:property value="provincialId.name" />&nbsp;</td>                                                                          
                                            <td><s:property value="cityId.name" />&nbsp;</td>                                                                          
                                            <td><s:property value="districtId.name" />&nbsp;</td>                                                                  
                                            <td><s:property value="addressId" />&nbsp;</td>
                                          
                  			            <td style="text-align: center;">
                                           		     <a title="修 改" href='expressageBranch!input.action?branchId=<s:property value="branchId" />'>
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