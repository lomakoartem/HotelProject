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
<title><fmt:message key="createorder.addorder" /></title>
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
			<a class="brand"><fmt:message key="createorder.title" /></a>
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
		<form class="navbar-form pull-right" action="main" method="post">
			<input type="hidden" name="command" value="page"><input
				type="hidden" name="page" value="clientpage">
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
	<!-- Creating order -->
	<form action="main" method="POST">
		<div class="container pull-center" id="container-wraper">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key="createorder.dateinfo" /></th>
					</tr>
				</thead>
				<tr class="info">
					<td><span class="badge badge-inverse"><fmt:message
								key="createorder.checkdatein" /></span><br> <input type="date"
						name="dateIn"></td>
					<td><span class="badge badge-inverse"><fmt:message
								key="createorder.checkdateout" /></span><br> <input type="date"
						name="dateOut"></td>
				</tr>
			</table>
			<span class="badge badge-inverse"><fmt:message
					key="createorder.addorderinfo" /></span><br> <input type="text"
				name="description" size="100">
			<hr />
			<table class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key="clientorders.free-room-info" /></th>
					</tr>
				</thead>
				<tr class="info">
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.roomnumber" /></span></td>
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.nplace" /></span></td>
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.cost" /></span></td>
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.roomcategory" /></span></td>
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.descriptionroom" /></span></td>
					<td><span class="badge badge-inverse"><fmt:message
								key="createorder.check" /></span></td>
				</tr>
				<c:forEach var="room" items="${freeRoom}">
					<tr>
						<td><c:out value="${room.roomNumber}" /></td>
						<td><c:out value="${room.placeForSleep}" /></td>
						<td><c:out value="${room.costPerDay}" /></td>
						<td><c:out value="${room.category }" /></td>
						<td><c:out value="${room.description }" /></td>
						<td><input type="radio" name="id_room" value="${room.id}" /></td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="command" value="createOrder" />
			<button type="submit" class="btn btn-success btn-lg">
				<fmt:message key="createorder.createorder" />
			</button>
		</div>
	</form>
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