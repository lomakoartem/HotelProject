<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setBundle basename="resources.config" var="path" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="registration.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/login.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form-validator.js"></script>
</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="registration.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="index.jsp"><i class="icon-home"></i> <fmt:message
							key="login.home" /></a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div id="register-wraper">
			<form class="form register-form" method="POST" action="main">
				<legend>
					<a class="brand"><fmt:message key="registration.title" /></a>
				</legend>
				<div class="body">
					<!-- Login validation -->
					<div>
						<label for="login"> <fmt:message key="registration.login" /></label>
						<input type="text" class="required" id="login" required autofocus
							name="login" data-validation="custom"
							data-validation-help="Login should start with a letter and consist of 5-20 characters"
							data-validation-regexp="^[а-яА-ЯёЁa-zA-Z]{5,20}$"
							placeholder="<fmt:message key="registration.login" />" />

					</div>
					<!--  Password validation -->
					<div class="control-group">
						<label for="password"><fmt:message
								key="registration.password" /></label><input type="password"
							id="password" required name="password" data-validation="custom"
							data-validation-regexp="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
							data-validation-help="Password should start with a letter and consist of 1-20 characters"
							placeholder="<fmt:message key="registration.password" />" />
					</div>
					<!--  Password confirmed validation -->
					<div>
						<label for="passwordagain"><fmt:message
								key="registration.passwordagain" /></label><input type="password"
							required name="passwordagain" id="passwordagain"
							data-validation="custom" data-validation-matches-match="password"
							data-validation-regexp="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
							data-validation-help="Must match email address entered above"
							placeholder="<fmt:message key="registration.passwordagain" />" />
					</div>
					<!-- Email validation -->
					<div>
						<label for="email"><fmt:message key="registration.email" /></label>
						<input type="text" required name="email" id="email"
							data-validation="custom"
							data-validation-regexp="^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z]{2,})$"
							data-validation-help="Insert your email there"
							placeholder="<fmt:message key="registration.email" />" />
					</div>
					<!-- Name validation -->
					<div>
						<label for="name"><fmt:message key="registration.name" /></label>
						<input type="text" required name="name" id="name"
							data-validation="custom"
							data-validation-regexp="^[а-яА-ЯёЁa-zA-Z]{1,20}$"
							data-validation-help="Name should consist of 1-20 characters"
							placeholder="<fmt:message key="registration.name" />" />
					</div>
					<!-- First name validation -->
					<div>
						<label for="firstname"><fmt:message
								key="registration.firstname" /></label> <input type="text" required
							name="firstname" id="firstname" data-validation="custom"
							data-validation-regexp="^[а-яА-ЯёЁa-zA-Z]{1,20}$"
							data-validation-help="Firstname should consist of 1-20 characters"
							placeholder="<fmt:message key="registration.firstname" />" />
					</div>
					<!--  Surname validation -->
					<div>
						<label for="surname"> <fmt:message
								key="registration.surname" /></label> <input type="text" required
							name="surname" id="surname" data-validation="custom"
							data-validation-regexp="^[а-яА-ЯёЁa-zA-Z]{1,20}$"
							data-validation-help="Surname should consist of 1-20 characters"
							placeholder="<fmt:message key="registration.surname" />" />
					</div>
					<!-- Age validation -->
					<div>
						<label for="age"> <fmt:message key="registration.age" /></label>
						<input type="text" id="age" required name="age"
							data-validation="custom" data-validation-regexp="\d{1,3}"
							data-validation-help="Age should consist only numbers"
							placeholder="<fmt:message key="registration.age" />" />
					</div>
				</div>
				<div class="footer">
					<input type="hidden" name="command" value="registration" />
					<div class="btnfirst">
						<button class="btn btn-access" type="submit" name="registration">
							<fmt:message key="registration.registration" />
						</button>
					</div>
				</div>
			</form>
			<c:if test="${not empty errorMessage }">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>
		</div>
	</div>
	<script>
		$.validate();
	</script>

	<footer class="white navbar-fixed-bottom">
		<jsp:include page="/jsp/common/footer.jsp" flush="true" />
	</footer>

</body>
</html>

