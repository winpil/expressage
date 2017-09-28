<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>快递快滴后台管理平台</title>
        <jsp:include page="/common/framecommon.jsp" />
        <script src="../../js/frame/editform.js" ></script>
        <!--自定义脚本-->
        
        <link rel="stylesheet" href="../../js/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="../../js/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="../../js/kindeditor/kindeditor.js">
	</script>
	<script charset="utf-8" src="../../js/kindeditor/lang/zh_CN.js">
	</script>
	<script charset="utf-8" src="../../js/kindeditor/plugins/code/prettify.js">
	</script>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="expressageOrder!save.action" enctype="multipart/form-data">
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>基本信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
                            	<tr>
                                    <td class="gridtitle">订单号：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressageOrder.orderNo" cssClass="inp ipt-normal" maxlength="20" readonly="true"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">包裹类型：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressageOrder.packType" cssClass="inp ipt-normal" maxlength="20" />
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="gridtitle">重量：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.packWeight" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="gridtitle">用户名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.userId.userName" cssClass="inp ipt-normal" maxlength="20"/>
                                        	<s:hidden name="expressageOrder.userId.userId" />
                                        </div>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="gridtitle">快递员名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.courierId.courierName" cssClass="inp ipt-normal" maxlength="20"/>
                                        	<s:hidden name="expressageOrder.courierId.courierId" />
                                        	
                                        </div>
                                    </td>
                                </tr> 
                                <tr>
                                    <td class="gridtitle">寄件地址：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.jAddress" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">寄件人：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.jPhone" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">寄件人号码：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.jName" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">收件地址：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.sAddress" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">收件人：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.sName" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">收件人号码：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.sPhone" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">下单城市：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.area" cssClass="inp ipt-normal" />
                                        </div>
                                    </td>
                                </tr>
                                
                                 <tr>
                                    <td class="gridtitle">产品名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.productId.productName" cssClass="inp ipt-normal" maxlength="20"/>
                                        	 <s:hidden name="expressageOrder.productId.productId" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">兑换积分：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.integralNum" cssClass="inp ipt-normal" maxlength="20"/>
                                        	   <s:if test="expressageOrder.orderId != null && expressageOrder.orderId != '' ">
                                               <s:hidden name="expressageOrder.orderId"/> </s:if>                                            
                                        	   <s:hidden name="expressageOrder.longitude" />
                                        	   <s:hidden name="expressageOrder.latitude" />  
                                        	   <s:hidden name="expressageOrder.gatherDate" />
                                        	   <s:hidden name="expressageOrder.reservePrice" />
                                        	   <s:hidden name="expressageOrder.orderPrice" />
                                        	   <s:hidden name="expressageOrder.payId" />
                                        	   <s:hidden name="expressageOrder.favorableId" />
                                        	
                                        </div>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="gridtitle">状态：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:select list="#{'1':'待接单/待发货','2':'待支付/待签收','3':'完成','4':'用户取消','5':'已举报'}" listKey="key" listValue="value" name="expressageOrder.status" />
                                        	
                                        </div>
                                    </td>
                                </tr> 
                                 <tr>
                                    <td class="gridtitle">下单方式：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:select list="#{'1':'快递下单','2':'积分下单'}" listKey="key" listValue="value" name="expressageOrder.type" />
                                        </div>
                                    </td>
                                </tr> 
                                
                                 <tr>
                                    <td class="gridtitle">创建时间：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="expressageOrder.createDate" cssClass="inp ipt-normal" maxlength="20" readonly="true"/>
                                        </div>
                                    </td>
                                </tr>
                                 
                            </table>
                            <!--确定、取消按钮-->
                            <div class="ZB_BtnBox">
                                <input type="submit"  name="button2" id="btnSubmit" value="保存" class="ZB_Btn" />
                                <input type="button" name="button" id="btnCancel" value="取消" onclick="javascript:history.back();" class="ZB_Btn"/>
                            </div>
                        </div>
                    </div>
                </div>
                <!--页面内容部分 end-->
            </div>
        </form>
    </body>
</html>