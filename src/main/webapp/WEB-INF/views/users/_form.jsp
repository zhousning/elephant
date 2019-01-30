<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form:form action="users" method="POST" modelAttribute="user"
	class="form-horizontal">
	
	<c:if test="${user.id != null }">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<div class="col-md-4 col-md-offset-2">
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label"><fmt:message
					key="user.name"></fmt:message></label>
			<div class="col-sm-10">
				<form:input type="text" class="form-control user-control" id="name"
					placeholder="name" path="name" autocomplete="off" />
				<form:errors path="name"></form:errors>
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-sm-2 control-label"><fmt:message
					key="user.phone"></fmt:message></label>
			<div class="col-sm-10">
				<form:select class="form-control user-control" items="${departments }" itemLabel="name" itemValue="id"
					path="department.id" />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label"><fmt:message
					key="user.email"></fmt:message></label>
			<div class="col-sm-10">
				<form:input class="form-control user-control" id="email"
					 path="email" autocomplete="off" />
				<form:errors path="email"></form:errors>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label"><fmt:message
					key="user.password"></fmt:message></label>
			<div class="col-sm-10">
				<form:input type="password" class="form-control user-control" id="password"
					path="password"  autocomplete="new-password" />
				<form:errors path="password"></form:errors>
			</div>
		</div>
			<div class="form-group">
		<label for="roles" class="col-sm-2 control-label">分配权限</label>
		<div class="col-sm-10">
			<form:checkboxes  path="roleIds"  items="${roles}" itemLabel="label" itemValue="id"/>
		</div>
	</div>
		<div class="form-group">
		<!--<div class="col-sm-offset-2 col-sm-4">
			<button type="button" id="js-edit-userinfo" class="btn btn-primary"><fmt:message key="btn.edit"></fmt:message></button>
			 <a href="users" type="button" class="btn btn-warning"><fmt:message
						key="btn.back"></fmt:message></a>
			</div> -->
			<div class="col-sm-offset-2 col-sm-4">
				<button type="submit" class="btn btn-success">
					<fmt:message key="btn.save"></fmt:message>
				</button>
			</div>	
		</div>
	</div>

</form:form>