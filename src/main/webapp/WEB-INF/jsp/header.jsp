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

              <a href="<s:url value="/" htmlEscape="true" />"><img src="<s:url value="/static/img/logo.png" htmlEscape="true"/>" align="left" width="160" alt="Logo" ></img></a>

		<div align="right">
			<c:choose>
				<c:when test="${sessionScope.LOGIN != null}">
					<a href="<s:url value="/acc" />"><img src="<s:url value="/static/img/user_mng.png" htmlEscape="true"/>" alt="" height="18" /><b>${sessionScope.LOGIN.mail}</b></a> |
					<a href="<s:url value="/logout"/>"><fmt:message key="logout.button"/></a> <br/>
					
				</c:when>
				<c:otherwise>
					<input type="submit" onclick="window.location='<s:url value="/login"/>'" value="<fmt:message key="login.button"/>" /> <br/>
				</c:otherwise>
			</c:choose>
		</div>
              <hr style="border:solid #DDDDEE 1px"/><br/>