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
        <title>快递快滴管理平台</title>
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
        <form id="editForm" name="editForm" method="post" action="expressageProduct!save.action" enctype="multipart/form-data">
            <div>
                <!--页面内容部分 start-->
                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
                    <div class="ZB_FormWarp">
                        <!--                        导航标题-->
                        <div class="ZB_FormNav">
                            <ul>
                                <li class="c"><a href="#"><span>产品基本信息</span></a></li>
                            </ul>
                        </div>

                        <!--                        输入字段-->
                        <div class="ZB_FormWarp2">
                            <!--                            数据表格-->
                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
    
                                 <tr>
                                    <td class="gridtitle">产品名称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	 <s:textfield name="expressageProduct.productName" cssClass="inp ipt-normal" />
                                        	 <s:if test="expressageProduct.productId!=null">
                                        	 	<s:hidden name="expressageProduct.productId" />
                                        	 	<s:hidden name="expressageProduct.createDate" />
                                        	 </s:if>
                                        	 <s:hidden name="expressageProduct.img1" />
                                         	 <s:hidden name="expressageProduct.img2" />
                                         	 <s:hidden name="expressageProduct.img3" />
                                         	 <s:hidden name="expressageProduct.img4" />
                                        </div>
                                    </td>
                                </tr>
                          	  	<tr>
                                    <td class="gridtitle">兑换积分：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="expressageProduct.integral" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="gridtitle">展示图片：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <img src="<s:property value="expressageProduct.img1" />" width="400" height="300" alt=""/>
                                            <input name="image1" type="file"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">轮播图1：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <img src="<s:property value="expressageProduct.img2" />" width="400" height="300" alt=""/>
                                            <input name="image2" type="file"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">轮播图2：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <img src="<s:property value="expressageProduct.img3" />" width="400" height="300" alt=""/>
                                            <input name="image3" type="file"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">轮播图3：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <img src="<s:property value="expressageProduct.img4" />" width="400" height="300" alt=""/>
                                             <input name="image4" type="file"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">产品内容：</td>
                                    <td class="gridbody">
                                        <textarea name="expressageProduct.productContent" id="txtAre"
										class="input01" cols="45" rows="5"
										style="height: 800px; width: 700px;" > ${expressageProduct.productContent} 
										</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">状态：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:select list="#{'1':'上架','0':'下架'}" listKey="key" listValue="value" name="expressageProduct.status" />
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
    <script type="text/javascript">

	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="expressageProduct.productContent"]', {
				uploadJson : '../../js/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '../../js/kindeditor/jsp/file_manager_json.jsp',
				urlType:'domain',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['editForm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['editForm'].submit();
					});
				}
			});
			prettyPrint();
	});
	</script>
</html>