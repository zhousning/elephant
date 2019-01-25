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
<title><fmt:message key="exacctTwos.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/exacctTwos.css" rel="stylesheet">
<script src="static/javascripts/exacctTwos.js"></script>
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
							<fmt:message key="exacctTwos.show"></fmt:message>
						</h1>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-1">
									<a href="exacctTwos/${exacctTwo.id }/edit"
												class="btn btn-success"><fmt:message key="btn.edit"></fmt:message></a>
								</div>
								<div class="col-sm-1">
									<a href="exacctTwos/index" type="button" class="btn btn-warning"><fmt:message
											key="btn.back"></fmt:message></a>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th><fmt:message key="exacctTwo.name"></fmt:message></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${exacctTwo.name}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>