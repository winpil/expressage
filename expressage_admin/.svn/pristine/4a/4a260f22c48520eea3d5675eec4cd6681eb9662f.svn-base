<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-tags.tld" prefix="s" %>
<%@taglib uri="/WEB-INF/tlds/webpage-tags.tld"  prefix="w" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <link href="/cezdcp/style/frame/ZB.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="/cezdcp/js/jquery/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="/cezdcp/js/jquery/easyui/themes/icon.css">
		
		<script type="text/javascript" src="/cezdcp/js/jquery/jquery-1.4.2.min.js"></script>
		<script type="text/javascript"  src="/cezdcp/js/jquery/jquery.cookie.js"></script>
        <script type="text/javascript" src="/cezdcp/js/jquery/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/cezdcp/js/frame/utils.js" ></script>
        <script type="text/javascript" src="/cezdcp/js/frame/editform.js" ></script>
        
        <!--自定义脚本-->
        <script type="text/javascript">
        	$(document).ready(function() {
        		var url = "/cezdcp/rbac/sys/sysMailConfig!getSenderAddress.action";
        		$.post(url, function(resp) {
        			if (resp.success == true) {
        				// alert(resp.result);
        				$("#sender").val(resp.result);
        			}
        		});
        		
        		// 获取最近联系人
        		getRecentContact();
        		// 获取全部系统注册用户
				getAllContact();
				// 获取全部非系统注册用户的email
				getUnregUserEmail();
        	});
        	
        	function addAttachment() {
        		var attachmentFiles = $("#attachmentsNode").find(":file");
        		// alert(attachmentFiles.length);
        		if (attachmentFiles.length < 3) {
	        		// $("#attachmentsNode").append("<div><input type='file' name='attachment'><input type='button' value='删除' onclick='delAttach(this)'><br></div>");
	        		$("#attachmentsNode").append('<div><s:file name="attachment" onchange="checkFile(this)"/><input type="button" value="删除" onclick="delAttach(this)"><br></div>');
        		}
        		else {
        			alert("附件数量不能超过三个");
        		}
        	}
        	
        	function delAttach(obj) {
        		var divNode = $(obj).parent();
        		divNode.remove();
        	}
	        // 获取最近联系人
	        function getRecentContact() {
	        	$("#recentEmail").empty();
	        	var url = "/cezdcp/rbac/sys/mailReceiverRelation!getRecentContact.action";
	        	$.post(url, function(resp) {
	        		if (resp.success == true) {
	        			if (resp.result && resp.result.length > 0) {
	        				for (var i = 0; i < resp.result.length; i++) {
        						$("#recentEmail").append("<p><a href='#' onclick='add2Receiver(this)' title='" 
        							+ resp.result[i][1] + "'>" + resp.result[i][0] 
        							+ "</a><input type='hidden' value='" + resp.result[i][1] + "'></p>");
	        				}
	        			}
	        		}
	        	});
			}
			
			function add2Receiver(obj) {
				var emailHidden = $(obj).parent().find(":hidden");
				var email = emailHidden.val();
				// alert(email);
				$("#receiver").val($("#receiver").val() == "" ? email : $("#receiver").val() + ";" + email);
        		$("#receiver").focus();
			}
			
			// 获取全部系统注册用户
			function getAllContact() {
	        	$("#regUserEmail").empty();
	        	var url = "/cezdcp/rbac/sys/sysUser!getAllUserEmail.action";
	        	$.post(url, function(resp) {
	        		if (resp.success == true) {
	        			if (resp.result && resp.result.length > 0) {
	        				for (var i = 0; i < resp.result.length; i++) {
	        					$("#regUserEmail").append("<p><a href='#' onclick='add2Receiver(this)' title='" 
	        						+ resp.result[i][1] + "'>" + resp.result[i][0] 
	        						+ "</a><input type='hidden' value='" + resp.result[i][1] + "'></p>");
	        				}
	        			}
	        		}
	        	});
			}
			// 获取全部非系统注册用户的email
			function getUnregUserEmail() {
				$("#unregUserEmail").empty();
	        	var url = "/cezdcp/rbac/sys/mailReceiver!listAll.action";
	        	$.post(url, function(resp) {
	        		if (resp.success == true) {
	        			if (resp.result && resp.result.length > 0) {
	        				for (var i = 0; i < resp.result.length; i++) {
	        					$("#unregUserEmail").append("<p><a href='#' onclick='add2Receiver(this)' title='"+
	        					+ resp.result[i][1] + "'>" + resp.result[i][0]
	        						+ "</a><input type='hidden' value='" + resp.result[i][1] + "'></p>");
	        				}
	        			}
	        		}
	        	});
			}
			
			
			function chkEmail(obj) {
	        	var value = obj.value;
        		if(value == "") {
        			setErrorMessage(obj,"必填项，请填写");
        		 	return false;
        		}
        		
        		var exp = /^\w+@\w+\.\w+$/;
        		var emailArray = value.split(";");
        		for (var i = 0; i < emailArray.length; i++) {
        			if (emailArray[i] != "" && emailArray[i].search(exp) == -1) {
        				setErrorMessage(obj,"Email输入不合法，格式为：gdcn@123.com");
        				return false;
        			}
        		}
	        	return true;
        	}
        	
        	function checkFile(obj) {
            	var value = $(obj).val();
            	var suffixIndex = value.lastIndexOf(".");
            	if (suffixIndex != -1) {
            		var suffix = value.substring(suffixIndex, value.length);
            		if (suffix != ".doc" && suffix != ".xls" && suffix != ".ppt") {
	            			alert("该文件类型" + suffix + "不允许上传！");
							$(obj).after($(obj).clone().val(''));
							$(obj).remove();
	            		}
            	}
            	else {
            		alert("该文件类型不允许上传！");
	            	$(obj).after($(obj).clone().val(''));
					$(obj).remove();
	            }
            }
        </script>
    </head>
    <body>
    	<div class="easyui-layout" style="width:100%;height:610px;">
		    <div region="center" title="编辑邮件" style="overflow:hidden;">
		        <form id="editForm" name="editForm" method="post" action="/cezdcp/rbac/sys/mailSent!save.action" enctype="multipart/form-data">
		            <div>
		                <!--页面内容部分 start-->
		                <div class="ZB_ListBox" style="padding-left: 4px;padding-right: 4px;">
		                    <div class="ZB_FormWarp">
		                        <!-- 输入字段-->
		                        <div class="ZB_FormWarp2">
		                            <!-- 数据表格-->
		                            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="ZB_FormTable">
		                            	<tr><td></td><td align="left"><s:actionerror/></td></tr>
		                            	<tr>
		                                    <td class="gridtitle">发件人：</td>
		                                    <td class="gridbody">
		                                        <div class="editField" style="width: 250px;">
		                                            <s:textfield id="sender" name="mailSent.sender" maxLength="64" cssClass="inp ipt-normal" readonly="true"/>
		                                        </div>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td class="gridtitle">收件人：</td>
		                                    <td class="gridbody">
		                                        <div class="editField" style="width: 600px;">
		                                            <s:textfield id="receiver" name="receiver" cssClass="inp ipt-normal" />
		                                        </div>
		                                        <w:validator id="validator1" nullable="false" controlToValidate="receiver" validateFunction="chkEmail"
		                                        	ruleMessage="填写收件人邮箱地址，如果是多个地址，请用英文分号分隔" errorMessage="收件人数量不能多于100个"/>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td class="gridtitle">主题：</td>
		                                    <td class="gridbody">
		                                        <div class="editField" style="width: 600px;">
		                                        	<s:if test="testNotice != null">
			                                            <s:textfield name="mailSent.title" maxLength="64" cssClass="inp ipt-normal" value="%{testNotice.title}"/>
		                                        	</s:if>
		                                            <s:else>
		                                           		<s:textfield name="mailSent.title" maxLength="64" cssClass="inp ipt-normal"/>
		                                            </s:else>
		                                        </div>
		                                        <w:validator id="validator2" controlToValidate="mailSent.title" validateFunction="chkIsNoEmpty" nullable="false"
		                                                     ruleMessage="填写主题，不能超过64个字符" errorMessage="必填项，请填写" />
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td class="gridtitle">正文：</td>
		                                    <td class="gridbody">
		                                        <div class="editField">
		                                        	<s:if test="testNotice != null">
		                                            	<s:textarea onchange="limitTextLength(this,2048)" name="mailSent.body" theme="simple" rows="12" cols="80"
		                                            		value="%{testNotice.noticeContent}"/>
		                                            </s:if>
		                                            <s:else>
		                                            	<s:textarea onchange="limitTextLength(this,2048)" name="mailSent.body" theme="simple" rows="12" cols="80"/>
		                                            </s:else>
		                                           	<w:validator id="validator3" controlToValidate="mailSent.body" validateFunction="chkIsNoEmpty" nullable="false"
		                                                     ruleMessage="填写正文内容，不能超过2048个字符" errorMessage="必填项，请填写" />
		                                        </div>
		                                    </td>
		                                </tr>
		                                <tr style="display: none">
		                                    <td class="gridtitle">
		                                    	<input type="button" value="添加附件" onclick="addAttachment();"/><br>
		                                    </td>
		                                    <td class="gridbody">
		                                        <div id="attachmentsNode" class="editField" style="width: 400px;">
		                                            <s:label value="（允许上传的文件类型：*.doc *.xls *.ppt）"></s:label>
		                                        </div>
		                                    </td>
		                                </tr>
		                            </table>
		                            <!--确定、取消按钮-->
		                            <div class="ZB_BtnBox">
		                                <input type="submit"  name="button2" id="btnSubmit" value="发送" class="ZB_Btn" />
		                                <input type="button" name="btnCancel" id="btnCancel" value="取消" onclick="goBack();" class="ZB_Btn"/>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <!--页面内容部分 end-->
		            </div>
		        </form>
			</div>
			<div region="east" title="邮件通讯录" split="true" style="width:220px;padding1:1px;overflow:hidden;">
				<div class="easyui-accordion" fit="true" border="false">
					<div id="recentEmail" title="最近联系人" selected="false" style="padding:10px;">
					</div>
					<div id="unregUserEmail" title="非系统注册用户" style="padding:10px;">
					</div>
					<div id="regUserEmail" title="系统注册用户" style="padding:10px;">
					</div>
				</div>
			</div>
		</div>
    </body>
</html>