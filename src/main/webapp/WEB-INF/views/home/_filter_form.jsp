<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="form-inline" id="filterForm" action="##" method="post">
	<%-- <input type="hidden" name="subjectId" value="${subjectId }" /> --%>
	<div class="row">
		<div class="col-md-2">
			<div class="form-group">
				<div class="col-sm-6">
					<select class="form-control" name="departmentId">
						<c:forEach items="${departments}" var="department">
							<option value="${department.id }">${department.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="form-group">
				<div class="col-sm-6">
					<select class="form-control" name="exacctId">
						<c:forEach items="${exaccts}" var="exacct">
							<option value="${exacct.id }">${exacct.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="form-group">
				<input type="date" class="form-control" name="start" />
			</div>
		</div>
		<div class="col-md-2">
			<div class="form-group">
				<input type="date" class="form-control" name="end" />
			</div>
		</div>
		<div class="col-md-2">
			<button type="submit" id="filter-data"  data-url="statistics/filterdata" class="btn btn-success submit-btn">
				筛选</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-1">
			<button type="submit" id="staff-form"  data-url="statistics/staffcost" class="btn btn-primary submit-btn">
				员工花费报表</button>
		</div>
		<div class="col-md-1">
			<button type="submit" id="department-form" data-url="statistics/departmentcost" class="btn btn-info submit-btn">
				部门报表</button>
		</div>
		<div class="col-md-1">
			<button type="submit" id="allcost-form" data-url="statistics/allcost" class="btn btn-danger submit-btn">
				总花费报表</button>
		</div>
	</div>
</form>