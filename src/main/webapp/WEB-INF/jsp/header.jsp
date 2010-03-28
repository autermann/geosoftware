<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@page import="java.util.Date" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="title"/></title>
		<link href="<s:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<s:url value="/static/js/jquery-1.4.2.min.js" htmlEscape="true" />"></script>
	</head>
	<body>
		<div align="right">
			<c:choose>
				<c:when test="${sessionScope.LOGIN != null}">
					<a href="<s:url value="/acc" />"><b>${sessionScope.LOGIN.mail}</b></a>
					<input type="submit" onclick="window.location='<s:url value="/logout"/>'" value="<fmt:message key="logout.button"/>" /> <br/>
					<fmt:message key="login"/>: <%= new Date(session.getCreationTime())%><br/>
				</c:when>
				<c:otherwise>
					<input type="submit" onclick="window.location='<s:url value="/login"/>'" value="<fmt:message key="login.button"/>" /> <br/>
				</c:otherwise>
			</c:choose>
		</div>