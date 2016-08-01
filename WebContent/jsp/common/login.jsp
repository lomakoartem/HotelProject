<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="login.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/login.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
</head>
<body>
	
	<div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="login.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="index.jsp"><i class="icon-home"></i> <fmt:message
							key="login.home" /></a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div id="login-wraper">
			<form class="form login-form" method="POST" action="main">
				<legend>
					<fmt:message key="login.signin" />
				</legend>
				<div class="body">
					<input type="hidden" name="command" value="login" /> <label
						for="login"><fmt:message key="login.login" /></label> <input
						id="login" type="text" required name="login" value=""
						placeholder="<fmt:message key="login.login" />" /> <label
						for="pass"><fmt:message key="login.password" /></label><input
						id="pass" type="password" required name="password"
						placeholder="<fmt:message key="login.password" />" />
				</div>
				<div class="footer">
					<button class="btn btn-success" type="submit" name="return">
						<fmt:message key="login.enter" />
					</button>
				</div>
			</form>
		</div>
	</div>
	<c:if test="${not empty errorMessage }">
		<div class="alert alert-danger">${errorMessage}</div>
	</c:if>
	<footer class="white navbar-fixed-bottom">
		<form method="POST" action="main">
			<fmt:message key="login.already-reg" />
			<input type="hidden" name="command" value="page" /> <input
				type="hidden" name="page" value="registration">
			<button class="btn btn-primary" type="submit" name="login">
				<fmt:message key="login.reg" />
			</button>
		</form>
	</footer>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


</body>
</html>