<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setBundle basename="resources.config" var="path" />
<fmt:setLocale value="${lang }" />
<fmt:setBundle basename="languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="index.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/index.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="index.hotelname" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a
					href="${pageContext.request.contextPath}/jsp/common/contact.jsp"><i
						class="icon-phone"></i> <fmt:message key="index.contact" /></a></li>
			</ul>
			<div class="btn-group btn-group-sm pull-right">
				<form class="navbar-form pull-right" action="main" method="post">
					<input type="hidden" name="command" value="language"><input
						type="hidden" name="lang" value="ru_RU">
					<button class="btn btn-info btn-mini" type="submit" name="rus">
						<fmt:message key="index.russian" />
					</button>
				</form>
				<form class="navbar-form pull-right" action="main" method="post">
					<input type="hidden" name="command" value="language"><input
						type="hidden" name="lang" value="en_EN">
					<button class="btn  btn-info btn-mini" type="submit" name="eng">
						<fmt:message key="index.english" />
					</button>
				</form>
				<form class="navbar-form pull-right" action="main" method="post">
					<input type="hidden" name="command" value="page"><input
						type="hidden" name="page" value="login">
					<button class="btn btn-success" type="submit" name="login">
						<i class="icon-user icon-large"></i>
						<fmt:message key="index.login" />
					</button>
				</form>
				<c:if test="${not empty user }">
					<form class="navbar-form pull-right" action="main" method="post">
						<input type="hidden" name="command" value="page"><input
							type="hidden" name="page" value="cabinet">
						<button class="btn btn-warning" type="submit" name="login">
							<i class="icon-user icon-large"></i>
							<fmt:message key="index.to-personal-page" />
						</button>
					</form>
				</c:if>
			</div>

		</nav>
	</div>
	<div class="container">
		<header>
			<h1>
				<fmt:message key="index.hotelname" />
			</h1>
		</header>
		<article>
			<c:if test="${not empty errorMessage }">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>
			<c:if test="${not empty actionMessage }">
				<div class="alert alert-success">${actionMessage }</div>
			</c:if>
			<p>
				<fmt:message key="index.info1" />
			</p>
			<p>
				<fmt:message key="index.info2" />
			</p>
			<div class="carousel slide" id="myCarousel">
				<ol class="carousel-indicators">
					<li class="active" data-target="#myCarousel" data-slide-to="0"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
					<li data-target="#myCarousel" data-slide-to="3"></li>
					<li data-target="#myCarousel" data-slide-to="4"></li>
					<li data-target="#myCarousel" data-slide-to="5"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img src="${pageContext.request.contextPath}/images/bg_2.jpg">
						<div class="carousel-caption">
							<h4>
								<fmt:message key="index.slide-title-2" />
							</h4>
							<p>
								<fmt:message key="index.slide-body-2" />
							</p>
						</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/images/bg_3.jpg">
						<div class="carousel-caption">
							<h4>
								<fmt:message key="index.slide-title-3" />
							</h4>
							<p>
								<fmt:message key="index.slide-body-3" />
							</p>
						</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/images/bg_6.jpg">
						<div class="carousel-caption">
							<h4>
								<fmt:message key="index.slide-title-6" />
							</h4>
							<p>
								<fmt:message key="index.slide-body-6" />
							</p>
						</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/images/bg_7.jpg">
						<div class="carousel-caption">
							<h4>
								<fmt:message key="index.slide-title-7" />
							</h4>
							<p>
								<fmt:message key="index.slide-body-7" />
							</p>
						</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/images/bg_9.jpg">
						<div class="carousel-caption">
							<h4>
								<fmt:message key="index.slide-title-9" />
							</h4>
							<p>
								<fmt:message key="index.slide-body-9" />
							</p>
						</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/images/bg_10.jpg">
						<div class="carousel-caption">
							<h4>
								<fmt:message key="index.slide-title-10" />
							</h4>
							<p>
								<fmt:message key="index.slide-body-10" />
							</p>
						</div>
					</div>
				</div>
				<a class="carousel-control left" data-slide="prev"
					href="#myCarousel">&lsaquo;</a> <a class="carousel-control right"
					data-slide="next" href="#myCarousel">&rsaquo;</a>
			</div>
		</article>
	</div>

	<footer>
		<jsp:include page="/jsp/common/footer.jsp" flush="true" />
	</footer>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>