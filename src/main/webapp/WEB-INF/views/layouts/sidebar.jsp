<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="nav-side-menu">
	<div class="brand">
		<shiro:principal />
	</div>
	<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
		data-target="#menu-content"></i>

	<div class="menu-list">

		<ul id="menu-content" class="menu-content collapse out">
			<li><a href="home"> <i class="fa fa-users fa-lg"></i> 数据中心
			</a></li>
			<shiro:hasAnyRoles name="admin">
				<li data-toggle="collapse" data-target="#users"
					class="collapsed active"><a><i class="fa fa-gift fa-lg"></i>
						<fmt:message key="users.manage"></fmt:message> <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="users">
					<!-- <li class="active"><a href="#">CSS3 Animation</a></li> -->
					<li><a href="users/index"><fmt:message key="users.index"></fmt:message></a></li>
					<li><a href="users/new"><fmt:message key="users.new"></fmt:message></a></li>
				</ul>

				<!-- <li data-toggle="collapse" data-target="#roles"
					class="collapsed
			active"><a><i
						class="fa fa-gift fa-lg"></i> <fmt:message key="roles.manage"></fmt:message>
						<span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="roles">
					<li><a href="roles/index"><fmt:message key="roles.index"></fmt:message></a></li>
					<li><a href="roles/new"><fmt:message key="roles.new"></fmt:message></a></li>
				</ul>
				<li data-toggle="collapse" data-target="#permissions"
					class="collapsed
			active"><a><i
						class="fa fa-gift fa-lg"></i> <fmt:message
							key="permissions.manage"></fmt:message> <span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="permissions">
					<li><a href="permissions/index"><fmt:message
								key="permissions.index"></fmt:message></a></li>
					<li><a href="permissions/new"><fmt:message
								key="permissions.new"></fmt:message></a></li>
				</ul> -->
				<li data-toggle="collapse" data-target="#centers"
					class="collapsed
			active"><a><i
						class="fa fa-gift fa-lg"></i> <fmt:message key="centers.manage"></fmt:message>
						<span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="centers">
					<li><a href="centers/index"><fmt:message
								key="centers.index"></fmt:message></a></li>
					<li><a href="centers/new"><fmt:message key="centers.new"></fmt:message></a></li>
				</ul>

				<li data-toggle="collapse" data-target="#departments"
					class="collapsed
			active"><a><i
						class="fa fa-gift fa-lg"></i> <fmt:message
							key="departments.manage"></fmt:message> <span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="departments">
					<li><a href="departments/index"><fmt:message
								key="departments.index"></fmt:message></a></li>
					<li><a href="departments/new"><fmt:message
								key="departments.new"></fmt:message></a></li>
				</ul>

				<li data-toggle="collapse" data-target="#exaccts"
					class="collapsed
			active"><a><i
						class="fa fa-gift fa-lg"></i> <fmt:message key="exaccts.manage"></fmt:message>
						<span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="exaccts">
					<li><a href="exacctOnes/index"><fmt:message
								key="exacctOnes.index"></fmt:message></a></li>
					<li><a href="exacctTwos/index"><fmt:message
								key="exacctTwos.index"></fmt:message></a></li>
					<li><a href="exacctThrees/index"><fmt:message
								key="exacctThrees.index"></fmt:message></a></li>
				</ul>
				
				</shiro:hasAnyRoles>
				<li data-toggle="collapse" data-target="#expenses"
					class="collapsed
			active"><a><i
						class="fa fa-gift fa-lg"></i> <fmt:message key="expenses.manage"></fmt:message>
						<span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="expenses">
					<li><a href="expenses/index"><fmt:message
								key="expenses.index"></fmt:message></a></li>
					<li><a href="expenses/upload">费用上传</a></li>
					<!-- <li><a href="expenses/new"><fmt:message key="expenses.new"></fmt:message></a></li> -->
				</ul>
					<shiro:lacksRole name="admin">
				<li><i class="fa fa-dashboard fa-lg"></i> <a href="personals"><fmt:message
							key="user.center"></fmt:message></a></li>
			</shiro:lacksRole>
		</ul>
	</div>
</div>