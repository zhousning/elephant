<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title><fmt:message key="expenses.index"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/expenses.css" rel="stylesheet">
<script src="static/javascripts/expenses.js"></script>

</head>
<body>
	<div id="circle">
		<div class="loader"></div>
	</div>
	<%@ include file="/WEB-INF/views/layouts/header.jsp"%>
	<div class="container-fluid body-container">
		<div class="row body-box">
			<div class="col-md-2 sidebar-container">
				<%@ include file="/WEB-INF/views/layouts/sidebar.jsp"%>
			</div>
			<div class="col-md-10 main-container">
				<form action="" method="POST">
					<input type="hidden" name="_method" value="DELETE" />
				</form>
				<div class="container-fluid">
					<div class="page-header">
						<h1>上传费用</h1>
					</div>
					<div class="row">
						<div class="col-md-8">
							<form action="expenses/uploadExcel" method="POST"
								class="form-inline" enctype="multipart/form-data">
								<div class="form-group">
									<select name="exacctThree" class="form-control">
										<c:forEach items="${exacctThrees }" var="exacct">
											<option value="${exacct.name}">${ exacct.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<input type="file" name="excel" />
								</div>
								<input type="submit" value="上传" class="btn btn-success" id="expense-upload-btn"/>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>