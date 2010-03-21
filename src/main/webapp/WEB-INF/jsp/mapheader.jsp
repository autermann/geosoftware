<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Map.Entry" %>
<%@page import="java.util.Collection" %>
<%@page import="org.sloth.model.Categorie" %>
<%@page import="org.sloth.model.Observation" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="title"/></title>
		<link href="<spring:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="http://openlayers.org/api/OpenLayers.js"></script>
		<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
		<script type="text/javascript" src="<spring:url value="/static/js/jquery-1.4.2.min.js" htmlEscape="true" />"></script>
		<%@include file="map.jsp" %>
	</head>
	<body onload="init();">
		<%@page import="java.util.Date" %>
		<div align="right">
			<c:choose>
				<c:when test="${sessionScope.loginUser != null}">
					<b>${sessionScope.loginUser.mail}</b>
					<input type="submit" onclick="window.location='<spring:url value="/logout"/>'" value="<fmt:message key="logout.button"/>" /> <br/>
					<fmt:message key="login.button"/>: <%= new Date(session.getCreationTime())%><br/>
				</c:when>
				<c:otherwise>
					<input type="submit" onclick="window.location='<spring:url value="/login"/>'" value="<fmt:message key="login.button"/>" /> <br/>
				</c:otherwise>
			</c:choose>
		</div>
