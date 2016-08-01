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
<title><fmt:message key="useradministration.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/user.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/tabs.css" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="useradministration.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="index.jsp"><i class="icon-home"></i> <fmt:message
							key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="main" method="POST">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="useradministration.logout" />
				</button>
			</form>
		</nav>
	</div>
	<!-- Control btn -->
	<div class="btn-group pull-right">
		<form class="navbar-form pull-right" action="main" method="post">
			<input type="hidden" name="command" value="page"><input
				type="hidden" name="page" value="adminpage">
			<button class="btn btn-sm btn-success" type="submit" name="login">
				<fmt:message key="useradministration.return" />
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
	<!-- Room administration -->
	<div class="container pull-center" id="container-wraper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="useradministration.page" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.id" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.name" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.firstname" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.surname" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.age" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.login" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.email" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.accesslevel" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="useradministration.action" /></span></td>
			</tr>
			<c:forEach var="user" items="${userList}">
				<jsp:useBean id="user" class="by.epam.hotel.entity.User" />
				<tr>
					<td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.surname}" /></td>
					<td><c:out value="${user.age}" /></td>
					<td><c:out value="${user.login}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.access}" /></td>
					<td><form action="main" method="POST">
							<select name="accessLevel">
								<option value="empty">
									<fmt:message key="useradministration.empty" />
								</option>
								<option value="GUEST">BAN</option>
								<option value="ADMIN">ADMIN</option>
								<option value="CLIENT">CLIENT</option>
							</select> <input type="hidden" name="command" value="changeAccessLevel" />
							<input type="hidden" name="id_user" value="${user.id }" />
							<button type="submit" class="btn btn-warning">
								<fmt:message key="useradministration.changeaccesslevel" />
							</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>
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

