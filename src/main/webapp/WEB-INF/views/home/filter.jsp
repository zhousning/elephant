<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title><fmt:message key="home.index"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/home.css" rel="stylesheet">
<script src="static/javascripts/home.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/views/layouts/header.jsp"%>
	<div class="container-fluid body-container">
		<div class="row body-box">
			<div class="col-md-2 sidebar-container">
				<%@ include file="/WEB-INF/views/layouts/sidebar.jsp"%>
			</div>
			<div class="col-md-10 main-container">
				<div class="row">
					<div class="col-sm-12">
						<%@ include file="_random_form.jsp"%>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 filterdata-ctn">
						
					</div>
				</div>
				<%-- <div class="row body-box">
					<div class="col-md-10 main-container">
						<div class="container-fluid">
							<shiro:lacksRole name="admin">
								<div class="page-header">
									<h1>
										<fmt:message key="users.edit"></fmt:message>
									</h1>
								</div>
								<div class="row">
									<%@ include file="/WEB-INF/views/personals/_form.jsp"%>
								</div>
							</shiro:lacksRole>
						</div>
					</div>
				</div> --%>
			</div>
		</div>

	</div>
</body>
</html>