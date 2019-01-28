<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="form-inline" id="randomForm" action="##" method="post">
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
			<button type="submit" class="btn btn-success">
				查询
			</button>
		</div>
	</div>
</form>