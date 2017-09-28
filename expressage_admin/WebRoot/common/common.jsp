<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/tld/NongLiJstlFunction.tld" prefix="mytag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/extjs/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/resource.css"/>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/style/ext-patch.css"  type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/mystyle.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/extjs/ext-all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/extjs/ext-lang-zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/extjs/DWRTreeLoader.js"></script>
<script src="${pageContext.request.contextPath}/dwr/engine.js" ></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type="text/javascript">
	var path="${pageContext.request.contextPath}";
	var nowDate="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>";
	var resourcePath='${mytag:getResourcePath()}';
	var tomorrow = "${mytag:getTomorrow()}";
</script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fckeditor/fckeditor.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fckeditor/ext_fckeditor.js"></script>