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
        	function listExpressageCourier(){
        		$('#listForm').attr("action", "expressageCourier!list.action").submit();	
        	}

        </script>
        
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="expressageCourier!list.action" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:快递员管理</span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                     
                           <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <s:textfield name="auName"  maxlength="20" cssStyle="width:100px"/>
                                <s:select list="#{'':'全部','courierName':'名称','phone':'手机号码','area':'城市'}" listKey="key" listValue="value" name="searchName" />
                                <s:select list="#{'':'账号状态','1':'正常','0':'锁定'}" listKey="key" listValue="value" name="status" />
                                <s:select list="#{'':'认证状态','0':'待审核','1':'审核通过','2':'审核不通过','3':'未提交'}" listKey="key" listValue="value" name="isAuth" />
                                <input type="submit" id="btnSubmit" value="筛选" class="ZB_Btn" />&nbsp;&nbsp;&nbsp;
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
                                        <td width="30"><w:thead title="名称" sortField=" courierName" /></td>
                                        <td width="30"><w:thead title="手机" sortField=" phone" /></td>
                                        <td width="40"><w:thead title="信誉度" sortField="rank" /></td>
                                        <td width="30"><w:thead title="余额" sortField="balance" /></td>
                                        <td width="50"><w:thead title="公司名称" sortField="companyId.companyName" /></td>
                                        <td width="50"><w:thead title="服务城市" sortField="area" /></td>
                                        <td width="50"><w:thead title="抢单次数" sortField="isWork" /></td>
                                        <td width="50"><w:thead title="账号状态" sortField="status" /></td>
                                        <td width="50"><w:thead title="认证状态" sortField="isAuth" /></td>
                                        <td width="40" style="text-align: center;">修 改</td>                                        
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="20" class="CNumber">
                                            <input type="checkbox" name="checkItem" value="<s:property value="courierId" />" class="checkItem" /></td>
                                            <td><s:property value="courierName" />&nbsp;</td>
                                            <td><s:property value="phone" />&nbsp;</td>              
                                            <td><s:property value="rank" />&nbsp;</td>
                                            <td><s:property value="balance" />&nbsp;</td>
                                            <td><s:property value="companyId.companyName" />&nbsp;</td>
                                            <td><s:property value="area" />&nbsp;</td>
                                            <td><s:property value="isWork" />&nbsp;</td>
                                            <td><s:if test="status==1">正常</s:if>
                                                <s:else>锁定</s:else> </td>
                                            <td><s:if test="isAuth==0">待审核</s:if>
                                            	<s:elseif test="isAuth==1">审核通过</s:elseif>
                                            	<s:elseif test="isAuth==2">审核不通过</s:elseif>
                                                <s:else>未提交</s:else> </td>
                                            <td style="text-align: center;">
                                           		 <a title="修 改" href='expressageCourier!input.action?courierId=<s:property value="courierId" />'>
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