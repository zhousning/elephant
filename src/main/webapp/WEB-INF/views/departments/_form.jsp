<form:form action="departments/${department.id != null ? 'update' : 'create' }" method="POST" modelAttribute="department"
	class="form-horizontal"  >
	<c:if test="${department.id != null }">
		<form:hidden path="id" />
	</c:if>
	
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><fmt:message key="department.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:input  type="text" class="form-control" path="name"  />
			<form:errors path="name"></form:errors>
		</div>
	</div>

	<div class="form-group">
		<label for="center" class="col-sm-2 control-label"><fmt:message
			key="center.name"></fmt:message></label>
		<div class="col-sm-10 ">
		<c:if test="${!empty centers }">
		<form:select class="form-control" path="center.id" items="${centers}" itemLabel="name" itemValue="id"></form:select>
		</c:if>
		</div>
	</div>
	
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-1">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-1 col-sm-1">
			<a href="departments/index" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>