<form:form action="permissions" method="POST" modelAttribute="permission"
	class="form-horizontal">
	<c:if test="${permission.id != null }">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><fmt:message key="permission.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" path="name" />
			<form:errors path="name"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="url" class="col-sm-2 control-label"><fmt:message key="permission.url"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" path="url" />
			<form:errors path="url"></form:errors>
		</div>
	</div>

	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-2 col-sm-4">
			<a href="permissions/index" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>