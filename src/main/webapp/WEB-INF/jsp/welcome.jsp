<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@page import="java.util.Date" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="title"/></title>
		<link href="<spring:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="http://openlayers.org/api/OpenLayers.js"></script>
		<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
		<script type="text/javascript" src="<spring:url value="/static/js/jquery-1.4.2.min.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="/static/js/map.js" htmlEscape="true" />"></script>
		<script type="text/javascript">
			function fillMap(){
			<c:forEach var="o" items="${observations}">
				addMarker(${o.coordinate.longitude}, ${o.coordinate.latitude},"<b>${o.title}</b><br/>${o.description}", "<spring:url value="/static/img/${o.categorie.iconFileName}"/>");</c:forEach>
			}
			<%-- TODO
				setEditingFeature(lon, lat, description, selectedCategorieId, categories, title, action, lang, errors)
			--%>
			</script>
		</head>
		<body onload="init();">
			<div align="left">
				<a href="#" onclick="goTo(7.62889,51.96080,10);">7.62889,51.96080</a>
			</div>
			<div align="right">
			<c:choose>
				<c:when test="${sessionScope.LOGIN != null}">
					<a href="<spring:url value="/acc" />"><b>${sessionScope.LOGIN.mail}</b></a>
					<input type="submit" onclick="window.location='<spring:url value="/logout"/>'" value="<fmt:message key="logout.button"/>" /> <br/>
					<fmt:message key="login.button"/>: <%= new Date(session.getCreationTime())%><br/>
				</c:when>
				<c:otherwise>
					<input type="submit" onclick="window.location='<spring:url value="/login"/>'" value="<fmt:message key="login.button"/>" /> <br/>
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			<h2><fmt:message key="welcome"/></h2>
		</div>
		<div>
			<div id="form">
				<form action="search" method="GET">
					<input id="searchBox" type="text" name="q" value="Search..."/>
					<input type="submit" value="Search"/>
				</form>
			</div>
			<div id="results">
				<c:forEach var="o" items="${observations}">
					<div class="observation">
						<span style="font-weight: bold;" class="observationTitle" id="obTitle${o.id}" >${o.title}</span>
						<br/>
						<p id="obDesc${o.id}">${o.description}</p>
						<a href="#" onclick="goTo(${o.coordinate.longitude}, ${o.coordinate.longitude}, 12);" class="goto"><fmt:message key="observation.viewInMap"/></a>
						<c:if test="${sessionScope.LOGIN != null}">
							<a href="<spring:url value="/r/o/${o.id}/new"/>"><fmt:message key="observation.report"/></a>
							<c:if test="${sessionScope.LOGIN.userGroup == 'ADMIN' || o.user.id == sessionScope.LOGIN.id}">
								<a href="<spring:url value="/o/edit/${o.id}"/>"><fmt:message key="observation.edit"/></a>
							</c:if>
						</c:if>
					</div>
					<br/>
				</c:forEach>
			</div>
		</div>
		<div id="map"></div>

		<div id="observationCreationBubble">
			<%@include file="creationBubble.jsp" %>
		</div>
		<div>
			<table class="footer">
				<tr>
					<td><a href="<spring:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a></td>
					<td align="right"><fmt:message key="copyright"/></td>
				</tr>
			</table>
		</div>
	</body>
</html>
