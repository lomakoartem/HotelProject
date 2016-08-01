<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="admin.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/user.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="admin.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="index.jsp"><i class="icon-home"></i> <fmt:message
							key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="main" method="POST">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="admin.logout" />
				</button>
			</form>
		</nav>
	</div>
	<!-- Control btn -->
	<div class="btn-group pull-right">
		<form class="navbar-form pull-right" action="main" method="POST">
			<input type="hidden" name="command" value="roomadministration" />
			<button class="btn btn-sm btn-default" type="submit">
				<i class="glyphicon glyphicon-star"></i>
				<fmt:message key="admin.roomadministration" />
			</button>
		</form>
		<form class="navbar-form pull-right" action="main" method="POST">
			<input type="hidden" name="command" value="orderadministration" />
			<button class="btn  btn-sm btn-info " type="submit">
				<fmt:message key="admin.orderadministration" />
			</button>
		</form>
		<form class="navbar-form pull-right" action="main" method="POST">
			<input type="hidden" name="command" value="useradministration" />
			<button class="btn btn-sm btn-warning " type="submit">
				<fmt:message key="admin.useradministration" />
			</button>
		</form>
	</div>

	<!-- Client info -->
	<div class="container pull-left" id="container-wraper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="admin.info" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.name" /></span></td>
				<td>${user.name}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message
							key="admin.firstname" /></span></td>
				<td>${user.firstName}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.surname" /></span></td>
				<td>${user.surname}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.login" /></span></td>
				<td>${user.login}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.email" /></span></td>
				<td>${user.email}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.age" /></span></td>
				<td>${user.age}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.access" /></span></td>
				<td>${user.access}</td>
			</tr>
		</table>
	</div>
	<!-- Date -->
	<div class="navbar-form pull-right wraper-date">
		<ht:today format="MMMM dd, yyyy" />
	</div>
	<footer class="white navbar-fixed-bottom">
		<jsp:include page="/jsp/common/footer.jsp" flush="true" />
	</footer>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>

