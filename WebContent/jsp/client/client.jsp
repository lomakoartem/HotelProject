<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="languages.Resources" />

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="client.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/user.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="client.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="index.jsp"><i class="icon-home"></i> <fmt:message
							key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="main" method="POST">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="client.logout" />
				</button>
			</form>
		</nav>
	</div>
	<!-- Control btn -->
	<div class="btn-group pull-right">
		<form class="navbar-form pull-right" action="main" method="POST">
			<input type="hidden" name="command" value="showFreeRoom" />
			<button class="btn btn-sm btn-success" type="submit">
				<i class="glyphicon glyphicon-star"></i>
				<fmt:message key="client.createorder" />
			</button>
		</form>
		<form class="navbar-form pull-right" action="main" method="POST">
			<input type="hidden" name="command" value="showMyOrder" />
			<button class="btn  btn-sm btn-info" type="submit">
				<fmt:message key="client.showorder" />
			</button>
		</form>
		<form class="navbar-form pull-right" action="main" method="POST">
			<input type="hidden" name="command" value="showMyBill" />
			<button class="btn btn-sm btn-warning" type="submit">
				<fmt:message key="client.showbill" />
			</button>
		</form>
	</div>
	<!--  Message -->
	<c:if test="${not empty errorMessage }">
		<div class="alert alert-danger">${errorMessage}</div>
	</c:if>
	<c:if test="${not empty actionMessage }">
		<div class="alert alert-success">${actionMessage }</div>
	</c:if>
	<!-- Client info -->
	<div class="container pull-left" id="container-wraper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="client.info" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge"><fmt:message key="client.name" /></span></td>
				<td>${user.name}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message
							key="client.firstname" /></span></td>
				<td>${user.firstName}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message
							key="client.surname" /></span></td>
				<td>${user.surname}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="client.login" /></span></td>
				<td>${user.login}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="client.email" /></span></td>
				<td>${user.email}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="client.age" /></span></td>
				<td>${user.age}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="client.access" /></span></td>
				<td>${user.access}</td>
			</tr>
		</table>
	</div>
	<!-- Date -->
	<div class="navbar-form pull-right wraper-date calendar">
		<ht:today format="MMMM dd, yyyy" />
	</div>
	<!-- Footer -->
	<footer class="white navbar-fixed-bottom">
		<jsp:include page="/jsp/common/footer.jsp" flush="true" />
	</footer>
	<!-- JavaScript -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>

