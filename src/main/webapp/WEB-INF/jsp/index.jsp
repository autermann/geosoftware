<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@page import="java.util.Date" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="title"/></title>
		<link href="<s:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="http://openlayers.org/api/OpenLayers.js"></script>
		<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
		<script type="text/javascript" src="<s:url value="/static/js/jquery-1.4.2.min.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<s:url value="/static/js/map.js" htmlEscape="true" />"></script>
		<%-- Add already existing features to the map --%>
		<script type="text/javascript">
			function fillMap(){
				<c:forEach var="o" items="${observations}">
						addMarker(${o.coordinate.longitude}, ${o.coordinate.latitude},"<b>${o.title}</b><br/>${o.description}<br/>Lon: ${o.coordinate.longitude}<br/>Lat: ${o.coordinate.latitude}", "<s:url value="/static/img/${o.categorie.iconFileName}"/>");</c:forEach>
					}
			</script>
		<c:if test="${sessionScope.LOGIN != null}"><script type="text/javascript">
				<%-- add formular data --%>
					setEditingFeature({
				<s:hasBindErrors name="observation">show: true,</s:hasBindErrors>
						lon: <c:out value="${observation.coordinate.longitude}" default="0"/>,
						lat: <c:out value="${observation.coordinate.latitude}" default="0"/>,
						selectedCategorieId: "<c:out value="${observation.categorie.id}"/>",
						description: "<c:out value="${observation.description}"/>",
						title: "<c:out value="${observation.title}"/>",
						action: "<s:url value="/"/>",
						lang: {
							title:"<fmt:message key="observation.title"/>",
							description:"<fmt:message key="observation.description"/>",
							categorie:"<fmt:message key="observation.categorie"/>"
						},
						errors: {
							description: "<s:bind path="observation.description" htmlEscape="true">${status.errorMessages[0]}</s:bind>",
							title: "<s:bind path="observation.title" htmlEscape="true">${status.errorMessages[0]}</s:bind>",
							categorie: "<s:bind path="observation.categorie" htmlEscape="true">${status.errorMessages[0]}</s:bind>"
						},
						categories:	[
							<c:forEach varStatus="status" var="categorie" items="${categories}">
								[${categorie.id}, "<s:escapeBody javaScriptEscape="true">${categorie.title}</s:escapeBody>"]
								<c:if test="${!status.last}">,</c:if>
							</c:forEach>
						]
					});
						</script></c:if>
					</head>
					<body onload="init();">
						<div align="right">
						<c:choose>
							<c:when test="${sessionScope.LOGIN != null}">
								<a href="<s:url value="/acc" />"><b>${sessionScope.LOGIN.mail}</b></a>
								<input type="submit" onclick="window.location='<s:url value="/logout"/>'" value="<fmt:message key="logout.button"/>" /> <br/>
								<fmt:message key="login.button"/>: <%= new Date(session.getCreationTime())%><br/>
							</c:when>
							<c:otherwise>
								<input type="submit" onclick="window.location='<s:url value="/login"/>'" value="<fmt:message key="login.button"/>" /> <br/>
							</c:otherwise>
						</c:choose>
					</div>
					<div>
						<h2><fmt:message key="welcome"/></h2>
					</div>
					<div>
						<div>
							<form action="<s:url value="/"/>" method="GET">
								<input type="text" name="q" value="Search..." onfocus="this.value=''"/>
								<input type="submit" value="Search"/>
							</form>
						</div>
						<div id="results">
							<c:forEach var="o" items="${observations}">
								<div class="observation">
									<span style="font-weight: bold;" class="observationTitle" id="obTitle${o.id}" ><s:escapeBody>${o.title}</s:escapeBody></span>
									<br/>
									<p id="obDesc${o.id}"><s:escapeBody>${o.description}</s:escapeBody></p>
									<a href="#" onclick="goTo(${o.coordinate.longitude}, ${o.coordinate.latitude}, 16);" class="goto"><fmt:message key="observation.viewInMap"/></a>
									<c:if test="${sessionScope.LOGIN != null}">
										<a href="<s:url value="/r/o/${o.id}/new"/>"><fmt:message key="observation.report"/></a>
										<c:if test="${sessionScope.LOGIN.userGroup == 'ADMIN' || o.user.id == sessionScope.LOGIN.id}">
											<a href="<s:url value="/o/edit/${o.id}"/>"><fmt:message key="observation.edit"/></a>
										</c:if>
									</c:if>
								</div>
								<br/>
							</c:forEach>
						</div>
					</div>
					<div id="map"></div>
					<div>
						<table class="footer">
							<tr>
								<td><a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a></td>
								<td align="right"><fmt:message key="copyright"/></td>
							</tr>
						</table>
					</div>
				</body>
			</html>
