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
<title><fmt:message key="roomadministration.title" /></title>
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
			<a class="brand"><fmt:message key="roomadministration.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="index.jsp"><i class="icon-home"></i> <fmt:message
							key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="main" method="POST">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="roomadministration.logout" />
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
				<fmt:message key="clientbill.return" />
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
					<th><fmt:message key="roomadministration.roomlist" /></th>
				</tr>
			</thead>
			<tr class="info">

				<td><span class="badge badge-inverse"><fmt:message
							key="roomadministration.roomnumber" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="roomadministration.nplace" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="roomadministration.cost" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="roomadministration.roomcategory" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="roomadministration.descriptionroom" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="roomadministration.status" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="roomadministration.action" /></span></td>
			</tr>
			<c:forEach var="room" items="${roomList}">
				<tr>
					<td><c:out value="${room.roomNumber}" /></td>
					<td><c:out value="${room.placeForSleep}" /></td>
					<td><c:out value="${room.costPerDay}" /></td>
					<td><c:out value="${room.category }" /></td>
					<td><c:out value="${room.description }" /></td>
					<td><c:out value="${room.status }" /></td>
					<td>
						<form action="main" method="POST">
							<select name="statusRoom">
								<option value="empty">
									<fmt:message key="roomadministration.empty" />
								</option>
								<option value="FREE">FREE</option>
								<option value="BUSY">BUSY</option>
							</select> <input type="hidden" name="command" value="setstatusroom" /> <input
								type="hidden" name="id_room" value="${room.id }" />
							<button type="submit" class="btn btn-warning">
								<fmt:message key="roomadministration.changestatusorder" />
							</button>
						</form>
					</td>
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