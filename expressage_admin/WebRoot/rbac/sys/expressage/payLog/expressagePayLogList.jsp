<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>提现管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
        <script>
        	function exportUser(){
        		$('#listForm').attr("action", "expressagePayLog!exportUsers.action").submit();
        		
        	}
        	function listUser(){
        		$('#listForm').attr("action", "expressagePayLog!list.action").submit();
        		
        	}
        	
        	
        </script>
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="expressagePayLog!list.action" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:支付管理</span></a></li>
                            </ul>
                        </div>
                        <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <s:textfield name="auName"  maxlength="20" cssStyle="width:100px"/>
                                <s:select list="#{'':'全部','userId.userName':'用户名称','courierId.courierName':'快递员名称','moneyNum':'金额','bankName':'银行名称'}" listKey="key" listValue="value" name="searchName" />
                                <s:select list="#{'':'支付方式','1':'现金支付','2':'微信支付','3':'支付宝支付','5':'扣费','6':'微信充值','7':'扣费回退','8':'支付宝充值'}" listKey="key" listValue="value" name="type" />
                                <s:select list="#{'':'交易状态','1':'成功','0':'失败','2':'进行中'}" listKey="key" listValue="value" name="status" />
                                <input type="button" id="btnSubmit" value="筛选" class="ZB_Btn" onclick="listUser();"/>&nbsp;&nbsp;&nbsp;
                                <input type="button" id="btnSubmit1" value="导出" class="ZB_Btn" onclick="exportUser();"/>
                            </div>
                        </div>
                        <!--#########################高级搜索 start#########################-->
                        <div class="ZB_FormFunctionBox2" id="advSearchDiv" style="display: none;" >
                            <w:advQueryHelper id="advQueryHelper1" />
                        </div>
                        <!--#########################高级搜索 end#########################-->

                        <!--#########################数据列表#########################-->
                        <div class="ZB_FormWarp2">
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="ZB_List">
                                <!--#########################表头#########################-->
                                <s:hidden name="page.orderBy" /><s:hidden name="page.order" />
                                <thead>
                                    <tr>
                                        <td width="20"><input type="checkbox"  name="checkItemAll" onclick="switchCheckAll(this)" /></td>
                                        <td width="40"><w:thead title="用户名称" sortField="userId.userName" /></td>
                                        <td width="50"><w:thead title="快递员名称" sortField="courierId.courierName" /></td>
                                        <td width="50"><w:thead title="支付方式" sortField="type" /></td>
                                        <td width="30"><w:thead title="金额" sortField="moneyNum" /></td>
                                        <td width="50"><w:thead title="交易状态" sortField="status" /></td>
                                        <td width="50"><w:thead title="优惠券面值" sortField="bankCode" /></td>
                                        <td width="50"><w:thead title="创建时间" sortField="createDate" /></td>
    								    <td width="30" style="text-align: center;">修 改</td>                                        
                                    </tr>
                                </thead>
    
                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''"> <td width="10" class="CNumber">
                                            <input type="checkbox" name="checkItem" value="<s:property value="payId" />" class="checkItem" /></td>                                           
                                            <td><s:property value="userId.userName" />&nbsp;</td>
                                            <td><s:property value="courierId.courierName" />&nbsp;</td>                                            
                                            <td>
                                            <s:if test="type==1">现金支付</s:if>
                                            <s:elseif test="type==2">微信支付</s:elseif>
                                            <s:elseif test="type==3">支付宝支付</s:elseif>
                                            <s:elseif test="type==4">提现</s:elseif>
                                            <s:elseif test="type==5">扣费</s:elseif>
                                            <s:elseif test="type==6">微信充值</s:elseif>
                                            <s:elseif test="type==7">扣费回退</s:elseif>
                                            <s:elseif test="type==8">支付宝充值</s:elseif>
                                            <s:else></s:else>
                                            </td>
                                            
                                            <td><s:property value="moneyNum" />&nbsp;</td> 
                                            <td>
                                            <s:if test="status==1">成功</s:if>
                                            <s:elseif test="status==0">失败</s:elseif>
                                            <s:else>进行中</s:else>
                                            </td>                                         
                                           
                                            <td><s:property value="bankCode" />&nbsp;</td>
                                             <td><s:property value="createDate" />&nbsp;</td>
                                            <td style="text-align: center;">
                                           		 <a title="修 改" href='expressagePayLog!input.action?payId=<s:property value="payId" />'>
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