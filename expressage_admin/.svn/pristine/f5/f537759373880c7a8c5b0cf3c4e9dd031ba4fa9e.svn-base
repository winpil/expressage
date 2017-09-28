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
        <title>模特后台管理平台</title>
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
	<script>
        	function selectById(id){
        		var htmlStr='';
        		$.ajax({
        			url: "/gktf_admin/rbac/sys/gktfAudio!getInfoByIDS.action",
        		    type: "post",
        		    data: "&tbSubjectId="+id,
        		    dataType: "json",
        		    success: function (data) {
        				htmlStr+=("<select id='albumId' name='gktfAudio.albumId.id'>");
        				for(var i=0;i<data.length;i++){
        					htmlStr+=("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
        				}	
        				htmlStr+=("</select>");	
        				$('#albumId').html(htmlStr);   
        			}
        		});
        		
        	}
        	
        </script>
    </head>
    <body>
        <form id="editForm" name="editForm" method="post" action="modelUser!save.action" enctype="multipart/form-data">
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
                                    <td class="gridtitle">姓名：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.name" cssClass="inp ipt-normal" maxlength="20" readonly="true"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">昵称：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.nickname" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">性别：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:if test="modelUser.gender==2">
                                        	女
                                        	</s:if>
                                        	<s:else>
                                        	男
                                        	</s:else>
                                        </div>
                                    </td>
                                </tr>
                          	  	<tr>
                                    <td class="gridtitle">手机：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.phone" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">邮箱：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.mailbox" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">身高：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="modelUser.motelHeight" cssClass="inp ipt-normal" maxlength="20"/>
                                        	<s:hidden name="modelUser.gender" />
                                        	<s:hidden name="modelUser.userId" />
                                        	<s:hidden name="modelUser.cityId.cityId" />
                                        	<s:hidden name="modelUser.publicStatus" />
                                        	<s:hidden name="modelUser.reserve1" />
                                        	<s:hidden name="modelUser.contact" />
                                        	<s:hidden name="modelUser.icodeAvatar" />
                                        	<s:hidden name="modelUser.releaseNum" />
                                        	<s:hidden name="modelUser.type" />
                                        	<s:hidden name="modelUser.isLocked" />
                                        	<s:hidden name="modelUser.status" />
                                        	<s:hidden name="modelUser.avatar" />
                                        	<s:hidden name="modelUser.avatar" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">三围：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.modelBwh" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">体重：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.weight" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">地址：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.address" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">规模：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.scale" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">团队介绍：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.introduce" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">类别：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:if test="modelUser.type==2">
                                        	公司认证
                                        	</s:if>
                                        	<s:elseif test="modelUser.type==1">
                                        	个人认证
                                        	</s:elseif>
                                        	<s:else>
                                        	未认证
                                        	</s:else>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">账号状态：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:select list="#{'1':'启用','0':'锁定'}" listKey="key" listValue="value" name="isLocked" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">会员状态：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:if test="modelUser.status==5">
                                        	公司已认证会员
                                        	</s:if>
                                        	<s:elseif test="modelUser.type==2">
                                        	个人已认证非会员
                                        	</s:elseif>
                                        	<s:elseif test="modelUser.type==3">
                                        	个人已认证会员
                                        	</s:elseif>
                                        	<s:elseif test="modelUser.type==4">
                                        	公司已认证非会员
                                        	</s:elseif>
                                        	<s:else>
                                        	个人未认证
                                        	</s:else>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">会员过期时间：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<w:datepicker id="sysUser.expirationDate"  value="${modelUser.memberTime}" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">鲜花：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.myFlowers" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">鸡蛋：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.myEggs" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">收到鲜花：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.receiveFlowers" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">收到鸡蛋：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.receiveEggs" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">身份证或营业执照编号：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                            <s:textfield name="modelUser.icode" cssClass="inp ipt-normal" maxlength="20"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="gridtitle">审核状态：</td>
                                    <td class="gridbody">
                                        <div class="editField">
                                        	<s:textfield name="modelUser.auditStatus" cssClass="inp ipt-normal" maxlength="20"/>
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
		var editor1 = K.create('textarea[name="gktfAudio.content"]', {
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