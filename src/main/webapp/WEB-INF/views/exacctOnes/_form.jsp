<form:form action="exacctOnes/${exacctOne.id != null ? 'update' : 'create' }" method="POST" modelAttribute="exacctOne"
	class="form-horizontal"  >
	<c:if test="${exacctOne.id != null }">
		<form:hidden path="id" />
	</c:if>
	
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><fmt:message key="exacctOne.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:input  type="text" class="form-control" path="name"  />
			<form:errors path="name"></form:errors>
		</div>
	</div>

	
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-1">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-1 col-sm-1">
			<a href="exacctOnes/index" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>