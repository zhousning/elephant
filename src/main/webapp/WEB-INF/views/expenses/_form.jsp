<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form:form
	action="expenses/${expense.id != null ? 'update' : 'create' }"
	method="POST" modelAttribute="expense" class="form-horizontal">
	<c:if test="${expense.id != null }">
		<form:hidden path="id" />
	</c:if>

	<div class="form-group">
		<label for="date" class="col-sm-2 control-label"><fmt:message
				key="expense.date"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="date" class="form-control" path="date" />
			<form:errors path="date"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="sum" class="col-sm-2 control-label"><fmt:message
				key="expense.sum"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="number" class="form-control" path="sum" />
			<form:errors path="sum"></form:errors>
		</div>
	</div>

	<div class="form-group">
		<label for="exacctThree" class="col-sm-2 control-label">费用项目</label>
		<div class="col-sm-10 ">
			<c:if test="${!empty exacctThrees }">
				<form:select class="form-control" path="exacctThree.id"
					items="${exacctThrees}" itemLabel="name" itemValue="id"></form:select>
			</c:if>
		</div>
	</div>
	<div class="form-group">
		<label for="department" class="col-sm-2 control-label"><fmt:message
				key="department.name"></fmt:message></label>
		<div class="col-sm-10 ">
			<c:if test="${!empty departments }">
				<form:select class="form-control" path="department.id"
					items="${departments}" itemLabel="name" itemValue="id"></form:select>
			</c:if>
		</div>
	</div>

	<c:set value="${fn:split(expense.info, 'SplitLine')}" var="infos"></c:set>
	<c:forEach items="${fn:split(explains, 'SplitLine')}" var="explain"
		varStatus="status">
		<div class="form-group">
			<label for="department" class="col-sm-2 control-label">${explain}</label>
			<div class="col-sm-10 ">
				<input type="text" class="form-control" name="explainNames"
					value="${infos[status.index]}" />
			</div>
		</div>
	</c:forEach>


	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-1">
			<button type="submit" class="btn btn-success">
				<fmt:message key="btn.save"></fmt:message>
			</button>
		</div>
		<div class="col-sm-offset-1 col-sm-1">
			<a href="expenses/index" type="button" class="btn btn-warning"><fmt:message
					key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>