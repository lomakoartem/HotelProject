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
<title><fmt:message key="orderadministration.title" /></title>
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
			<a class="brand"><fmt:message key="orderadministration.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="index.jsp"><i class="icon-home"></i> <fmt:message
							key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="main" method="POST">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="orderadministration.logout" />
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

	<!-- Order administration -->
	<div class="container pull-center" id="container-wraper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="orderadministration.orders" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><fmt:message key="orderadministration.iduser" /></td>
				<td><fmt:message key="orderadministration.surnameuser" /></td>
				<td><fmt:message key="orderadministration.numberroom" /></td>
				<td><fmt:message key="orderadministration.categoryroom" /></td>
				<td><fmt:message key="orderadministration.orderdate" /></td>
				<td><fmt:message key="orderadministration.datein" /></td>
				<td><fmt:message key="orderadministration.dateout" /></td>
				<td><fmt:message key="orderadministration.totalcost" /></td>
				<td><fmt:message key="orderadministration.descriptionorder" /></td>
				<td><fmt:message key="orderadministration.statusorder" /></td>
				<td><fmt:message key="orderadministration.changestatusorder" /></td>
			</tr>
			<c:forEach var="order" items="${orderList}">
				<jsp:useBean id="order" class="by.epam.hotel.entity.Order" />
				<tr>
					<ht:diffday dateOut="${order.dateOut}" dateIn="${order.dateIn}" />
					<td><c:out value="${order.user.id}" /></td>
					<td><c:out value="${order.user.surname}" /></td>
					<td><c:out value="${order.room.roomNumber}" /></td>
					<td><c:out value="${order.room.category}" /></td>
					<td><c:out value="${order.dateOrder}" /></td>
					<td><c:out value="${order.dateIn}" /></td>
					<td><c:out value="${order.dateOut}" /></td>
					<td><ht:totalcost differenceDay="${diffDay}"
							costPerDay="${order.room.costPerDay}" /></td>
					<td><c:out value="${order.description}" /></td>
					<td><c:out value="${order.status}" /></td>
					<td><form action="main" method="POST">
							<select name="statusOrder">
								<option value="empty">
									<fmt:message key="orderadministration.empty" />
								</option>
								<option value="CONFIRMED">
									<fmt:message key="orderadministration.confirmed" />
								</option>
								<option value="PENDING">
									<fmt:message key="orderadministration.pending" />
								</option>
								<option value="DECLINED">
									<fmt:message key="orderadministration.declined" />
								</option>
							</select> <input type="hidden" name="command" value="setStatusOrder" /> <input
								type="hidden" name="id_order" value="${order.id }" />
							<button class="btn btn-warning" type="submit">
								<fmt:message key="orderadministration.changestatusorder" />
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