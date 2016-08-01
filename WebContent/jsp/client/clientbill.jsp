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
<title><fmt:message key="clientbill.title" /></title>
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
			<a class="brand"><fmt:message key="clientbill.title" /></a>
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
					<th><fmt:message key="clientbill.bills" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge badge-inverse"><fmt:message
							key="clientbill.status" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientbill.totalprice" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientbill.billDate" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientbill.nroom" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientbill.dateIn" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="clientbill.dateOut" /></span></td>
				<td><span class="badge badge-inverse"></span></td>
			</tr>
			<c:forEach var="bill" items="${clientBill}">
				<jsp:useBean id="bill" class="by.epam.hotel.entity.Bill" />
				<tr>
					<td><c:out value="${bill.status}" /></td>
					<td><c:out value="${bill.totalCost}" /></td>
					<td><c:out value="${bill.billDate}" /></td>
					<td><c:out value="${bill.order.room.roomNumber }" /></td>
					<td><c:out value="${bill.order.dateIn}" /></td>
					<td><c:out value="${bill.order.dateOut}" /></td>
					<td><form action="main" method="POST">
							<input type="hidden" name="command" value="payment" /> <input
								type="hidden" name="id_bill" value="${bill.id }" />
							<button class="btn btn-warning" type="submit">
								<fmt:message key="clientbill.payment" />
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