<form:form action="exacctTwos/${exacctTwo.id != null ? 'update' : 'create' }" method="POST" modelAttribute="exacctTwo"
	class="form-horizontal"  >
	<c:if test="${exacctTwo.id != null }">
		<form:hidden path="id" />
	</c:if>
	
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><fmt:message key="exacctTwo.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:input  type="text" class="form-control" path="name"  />
			<form:errors path="name"></form:errors>
		</div>
	</div>

	<div class="form-group">
		<label for="exacctOne" class="col-sm-2 control-label"><fmt:message
			key="exacctOne.name"></fmt:message></label>
		<div class="col-sm-10 ">
		<c:if test="${!empty exacctOnes }">
		<form:select class="form-control" path="exacctOne.id" items="${exacctOnes}" itemLabel="name" itemValue="id"></form:select>
		</c:if>
		</div>
	</div>
	
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-1">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-1 col-sm-1">
			<a href="exacctTwos/index" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>