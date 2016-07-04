<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("login.jsp");
	}
	if (request.getAttribute("mainPage") == null) {
		request.setAttribute("mainPage", "common/default.jsp");
	}
%>
<!-- 引入Css样式 -->
<link href="${pageContext.request.contextPath}/style/studentInfo.css" rel="stylesheet">
<!-- 都使用绝对路径 -->
<!--核心CSS文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.css">
<!--可选的Bootstrap主题文件（一般不引入）-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-responsive.css">
<!--Jquery文件，且必须在bootstrap.min.js之前引入-->
<script src="${pageContext.request.contextPath}/bootstrap3/js/jQuery.js"></script>
<!--核心JavaScript文件-->
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<jsp:include page="common/head.jsp"></jsp:include>
		<jsp:include page="common/menu.jsp"></jsp:include>
		<jsp:include page="common/nav.jsp"></jsp:include>
		<jsp:include page="${mainPage }"></jsp:include>
		<jsp:include page="common/foot.jsp"></jsp:include>
	</div>

</body>
</html>