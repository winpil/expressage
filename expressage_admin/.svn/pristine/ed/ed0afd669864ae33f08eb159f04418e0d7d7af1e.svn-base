<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib  prefix="w" uri="/WEB-INF/tlds/webpage-tags.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>接口管理</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/listform.js" ></script>
    </head>
    <body>
        <form id="listForm" name="listForm" method="post" action="sysBase!list.action" >
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
                                <div class="add"><a href='sysBase!input.action'>添加</a></div>
                            </div>                            
                        </div>
                        <!--#########################搜索框#########################-->
                        <div class="ZB_FormFunctionBox">
                            <div class="ZB_FormSearch">
                                <label for="first_name">关键字： </label>
                                <w:simpleQueryHelper id="simpleQueryHelper1" name="simpleQueryHelper1" checkItems="${checkItems}" itemsValue="${itemsValue}" type="ddl" >
                                    <w:queryfield title="接口名" fieldname="apiName" datatype="String"  matchtype="LIKE" />
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
                                        <td width="80"><w:thead title="接口名" sortField="apiName" /></td>
                                        <td width="80"><w:thead title="参数数量" sortField="parameterNum" /></td>   
                                        <td width="80"><w:thead title="参数1" sortField="paramenter1" /></td>
                                        <td width="80"><w:thead title="参数2" sortField="paramenter2" /></td>
                                        <td width="80"><w:thead title="参数3" sortField="paramenter3" /></td>
                                        <td width="80"><w:thead title="参数4" sortField="paramenter4" /></td>
                                        <td width="80"><w:thead title="参数5" sortField="paramenter5" /></td>
                                        <td width="80"><w:thead title="参数6" sortField="pages" /></td>
                                        <td width="80"><w:thead title="参数7" sortField="pageSize" /></td>
                                        <td width="80"><w:thead title="描述" sortField="depict" /></td>
                                        <td width="48" style="text-align: center;">修改</td>
                                    </tr>
                                </thead>

                                <!--#########################数据行#########################-->
                                <tbody>
                                    <s:iterator value="page.result" status="st">
                                        <tr onmouseover="this.style.backgroundColor='#DFE8F7'" onmouseout="this.style.backgroundColor=''">
                                            <td width="20" class="CNumber"><input type="checkbox" name="checkItem" value="<s:property value="baseId" />" class="checkItem" /></td>
                                            <td><s:property value="apiName" />&nbsp;</td>
                                            <td><s:property value="parameterNum" />&nbsp;</td>  
                                            <td><s:property value="paramenter1" />&nbsp;</td> 
                                            <td><s:property value="paramenter2" />&nbsp;</td> 
                                            <td><s:property value="paramenter3" />&nbsp;</td> 
                                            <td><s:property value="paramenter4" />&nbsp;</td> 
                                            <td><s:property value="paramenter5" />&nbsp;</td> 
                                            <td><s:property value="pages" />&nbsp;</td> 
                                            <td><s:property value="pageSize" />&nbsp;</td>
                                            <td><s:property value="depict" />&nbsp;</td>  
                                            <td style="text-align: center;"><a  title="修改" href='sysBase!input.action?baseId=<s:property value="baseId" />'><img src="../../images/icon_45.gif" /></a>&nbsp;</td>
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