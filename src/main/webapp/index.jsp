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
<title>Home</title>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Favicon -->
<link href="img/favicon.ico" rel="shortcut icon" />

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i,800,800i"
	rel="stylesheet">

<!-- Stylesheets -->
<link rel="stylesheet" href="static/jslib/webuni/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="static/jslib/webuni/css/font-awesome.min.css" />
<link rel="stylesheet" href="static/jslib/webuni/css/owl.carousel.css" />
<link rel="stylesheet" href="static/jslib/webuni/css/style.css" />


<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header section -->
	<header class="header-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-3">
					<div class="site-logo">
						<a href="index.jsp"><img src="img/logo.png" alt=""></a>
					</div>
					<div class="nav-switch">
						<i class="fa fa-bars"></i>
					</div>
				</div>
				<div class="col-lg-9 col-md-9">
				<%-- 	<shiro:guest>
						<a class="site-btn header-btn" href="users/sign_in">Sign In </a>
					</shiro:guest>
					<shiro:user>
						<shiro:principal />
						<a class="site-btn header-btn" href="shiro/logout">退出</a>
					</shiro:user> --%>
					<nav class="main-menu">
						<ul>
							<!-- <li><a href="index.jsp">Home</a></li>
							<li><a href="#">About us</a></li> -->
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</header>
	<!-- Header section end -->


	<!-- Hero section -->
	<section class="hero-section set-bg" data-setbg="img/bg.jpg">
		<div class="container">
			<div class="hero-text text-white">
				<h2>经营数据管理信息系统</h2>
			</div>
			<div class="row">
				<shiro:guest>
				<div class="col-sm-6">
					<a class="site-btn header-btn" href="users/sign_in">登录 </a>
				</div>
				<div class="col-sm-3">	
					<a class="site-btn" href="users/sign_up">注册 </a>
				</div>
				</shiro:guest>
				<shiro:user>
				<div class="col-sm-6">	
					<a class="site-btn" href="home">系统中心 </a>
					</div>
					<div class="col-sm-3">	
					<a class="site-btn header-btn" href="shiro/logout">退出</a>
					</div>
				</shiro:user>
			</div>
		</div>
	</section>
	<!-- Hero section end -->



	<!--====== Javascripts & Jquery ======-->
	<script src="static/jslib/webuni/js/jquery-3.2.1.min.js"></script>
	<script src="static/jslib/webuni/js/bootstrap.min.js"></script>
	<script src="static/jslib/webuni/js/mixitup.min.js"></script>
	<script src="static/jslib/webuni/js/circle-progress.min.js"></script>
	<script src="static/jslib/webuni/js/owl.carousel.min.js"></script>
	<script src="static/jslib/webuni/js/main.js"></script>
</html>