<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>产品管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
        
         <script>
        	function listExpressageCourier(){
        		$('#listForm').attr("action", "expressageProduct!list.action").submit();	
        	}

        </script>
        
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="expressageProduct!list.action" >
            <div class="ZB_MidBox1">
                <!--#########################页面内容部分 start######################-->
                <div class="ZB_ListBox">
                    <div class="ZB_FormWarp">
                        <!--#########################导航标题#########################-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>当前位置:产品管理</span></a></li>
                            </ul>
                        </div>
                        
                        <!--#########################工具栏#########################-->
                           <!--#########################工具栏#########################-->
                        <div class="ZB_FormFunctionBox">
                            <!--#########################搜索框#########################-->
                            <div class="ZB_FormFunction">
	                                <div class="add"><a href='expressageProduct!input.action'>添加</a></div>
	                            </div> 
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <s:textfield name="auName"  maxlength="20" cssStyle="width:100px"/>
                                <s:select list="#{'':'全部','productName':'名称'}" listKey="key" listValue="value" name="searchName" />
                                <s:select list="#{'':'状态','1':'上架','0':'下架'}" listKey="key" listValue="value" name="status" />
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
                                        <td width="40"><w:thead title="产品名称" sortField="productName" /></td>
                                        <td width="60"><w:thead title="兑换积分数" sortField="integral" /></td>
                                        <td width="50"><w:thead title="展示图" sortField="img1" /></td>
                                        <td width="30"><w:thead title="状态" sortField="status" /></td>
                                        <td width="50"><w:thead title="创建时间" sortField="createDate" /></td>
                                        <td width="40" style="text-align: center;">修 改</td>                                        
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="20" class="CNumber">
                                            <input type="checkbox" name="checkItem" value="<s:property value="courierId" />" class="checkItem" /></td>
                                            <td><s:property value="productName" />&nbsp;</td>
                                            <td><s:property value="integral" />&nbsp;</td>
                                            <td><img src="<s:property value="img1" />" width="70" height="70" alt=""/>&nbsp;</td>
                                            <td><s:if test="status==1">上架</s:if>
                                                <s:else>下架</s:else> </td>
                                            <td><s:property value="createDate" />&nbsp;</td>
                                            <td style="text-align: center;">
                                           		 <a title="修 改" href='expressageProduct!input.action?productId=<s:property value="productId" />'>
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