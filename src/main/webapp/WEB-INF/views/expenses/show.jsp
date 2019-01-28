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
<title><fmt:message key="expenses.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/expenses.css" rel="stylesheet">
<script src="static/javascripts/expenses.js"></script>
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
							<fmt:message key="expenses.show"></fmt:message>
						</h1>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-1">
									<a href="expenses/${expense.id }/edit"
												class="btn btn-success"><fmt:message key="btn.edit"></fmt:message></a>
								</div>
								<div class="col-sm-1">
									<a href="expenses/index" type="button" class="btn btn-warning"><fmt:message
											key="btn.back"></fmt:message></a>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th><fmt:message key="expense.date"></fmt:message></th>
										<th>工号</th>
										<th>员工</th>
										<th>部门</th>
										<th>费用项目</th>
										<th><fmt:message key="expense.sum"></fmt:message></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${expense.date}</td>
										<td>${expense.staffid }</td>
										<td>${expense.staffname }</td>
										<td>${expense.exacctThree.name }
										<td>${expense.department.name }</td>
										<td>${expense.sum}</td>
									</tr>
								</tbody>
							</table>
							<c:set value="${fn:split(expense.info, 'SplitLine')}" var="infos"></c:set>
							<c:forEach items="${fn:split(explains, 'SplitLine')}" var="explain" varStatus="status">	
								<p>${explain} : ${infos[status.index]}</p>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>