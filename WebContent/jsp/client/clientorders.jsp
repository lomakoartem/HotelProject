<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setBundle basename="resources.config" var="path" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="clientorders.title" /></title>
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
			<a class="brand"><fmt:message key="clientorders.title" /></a>
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
	<!-- Bill info -->
	<div class="container pull-center" id="container-wraper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="clientorders.orders" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.numberroom" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.categoryroom" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.nplace" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.descriptionroom" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.orderdate" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.ndays" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.totalcost" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.descriptionorder" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientorders.statusorder" /></span></td>
			</tr>
			<c:forEach var="clientOrder" items="${clientOrder}">
				<jsp:useBean id="clientOrder" class="by.epam.hotel.entity.Order" />
				<tr>
					<ht:diffday dateOut="${clientOrder.dateOut}"
						dateIn="${clientOrder.dateIn}" />
					<td><c:out value="${clientOrder.room.roomNumber}" /></td>
					<td><c:out value="${clientOrder.room.category}" /></td>
					<td><c:out value="${clientOrder.room.placeForSleep}" /></td>
					<td><c:out value="${clientOrder.room.description }" /></td>
					<td><c:out value="${clientOrder.dateOrder}" /></td>
					<td><c:out value="${diffDay}" /></td>
					<td><ht:totalcost differenceDay="${diffDay}"
							costPerDay="${clientOrder.room.costPerDay}" /></td>
					<td><c:out value="${clientOrder.description}" /></td>
					<td><c:out value="${clientOrder.status}" /></td>
					<td><form action="main" method="POST">
							<input type="hidden" name="command" value="delorder" /> <input
								type="hidden" name="id_order" value="${clientOrder.id }" />
							<button type="submit" class="btn btn-danger">
								<i class="icon-remove-sign"></i>
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