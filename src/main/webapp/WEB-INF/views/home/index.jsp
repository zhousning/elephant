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
<script src="static/jslib/Chart.min.js"></script>

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
					<div class="col-sm-9">
						<%@ include file="_random_form.jsp"%>
					</div>
					<div class="col-sm-3">
						<div class="row">
						<shiro:hasAnyRoles name="admin, leader, staff, depManage">
						<div class="col-sm-4">
							<div class="form-group">
									<button type="button" id="department-form"
										data-url="statistics/departmentcost"
										class="btn btn-info submit-btn">部门报表</button>
								</div>
							</div>
						</shiro:hasAnyRoles>
						<shiro:hasAnyRoles name="admin, leader">
							<div class="col-sm-4">
								<div class="form-group">
									<button type="button" id="staff-form"
										data-url="statistics/staffcost"
										class="btn btn-primary submit-btn">员工花费报表</button>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<button type="button" id="allcost-form"
										data-url="statistics/allcost"
										class="btn btn-danger submit-btn">总花费报表</button>
								</div>
							</div>
							</shiro:hasAnyRoles>
						</div>
					</div>
				</div>
				<div class="row">
				<shiro:hasAnyRoles name="admin, staff, depManage">
					<div class="col-sm-12 chart-ctn" data-tag="depSumCostPerMonth">
						<canvas id="depSumCostPerMonth"></canvas>
					</div>
					<div class="col-sm-12 chart-ctn" data-tag="depExacctCostPerMonth">
						<canvas id="depExacctCostPerMonth"></canvas>
					</div>
				</shiro:hasAnyRoles>
				<shiro:hasAnyRoles name="admin, leader">
					<div class="col-sm-12 chart-ctn" data-tag="allDepExacctCostPerMonth">
						<canvas id="allDepExacctCostPerMonth"></canvas>
					</div>
					<div class="col-sm-12 chart-ctn" data-tag="allCostPerMonth">
						<canvas id="allCostPerMonth"></canvas>
					</div>
					<div class="col-sm-12 chart-ctn" data-tag="allCostPerDepByMonth">
						<canvas id="allCostPerDepByMonth"></canvas>
					</div>
					<div class="col-sm-12 chart-ctn" data-tag="allCostPerDepByMonthAndExacct">
						<canvas id="allCostPerDepByMonthAndExacct"></canvas>
					</div>
				</shiro:hasAnyRoles>
				</div>
			</div>
		</div>

	</div>
</body>
</html>