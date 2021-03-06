<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/layouts/jsp_header.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Model New</title>
<%@include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/models.css" rel="stylesheet">
<script src="static/javascripts/models.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/layouts/header.jsp"%>
	<div class="container-fluid body-container">
		<div class="row body-box">
			<div class="col-md-2 sidebar-container">
				<%@ include file="/WEB-INF/views/layouts/sidebar.jsp"%>
			</div>
			<div class="col-md-10 main-container">
				<div class="container-fluid">
					<div class="page-header">
						<h1>
							Model New
						</h1>
					</div>
					<div class="row">
						<div class="col-md-12">
							<%@ include file="/WEB-INF/views/models/_form.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>