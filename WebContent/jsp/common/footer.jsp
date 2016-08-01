<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="resources.config" var="path" />
<fmt:setLocale value="${lang }" />
<fmt:setBundle basename="languages.Resources" />

<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<div id="footer">
		<div class="container">
			<p class="text-muted credit">
				<a href="#"><fmt:message key="footer.back" /></a>
				<fmt:message key="footer.copyright" />
			</p>
		</div>
	</div>
</body>
</html>
