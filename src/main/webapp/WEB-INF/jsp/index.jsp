<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@page import="java.util.Date" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" dir="ltr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><s:escapeBody htmlEscape="true"><fmt:message key="tab.title"/></s:escapeBody></title>
		<link href="<s:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="http://openlayers.org/api/OpenLayers.js"></script>
		<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
		<script type="text/javascript" src="<s:url value="/static/js/jquery-1.4.2.min.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<s:url value="/static/js/map.js" htmlEscape="true" />"></script>
		<%-- Add already existing features to the map --%>
		<script type="text/javascript">
			setNotLoggedInUserErrorLocalized("<p><s:escapeBody htmlEscape="true" javaScriptEscape="true"><fmt:message key="observation.new.notLoggedInError"/></s:escapeBody></p>")
			function fillMap(){
			<c:forEach var="o" items="${observations}">
					addMarker(${o.coordinate.longitude}, ${o.coordinate.latitude},"<b><s:escapeBody htmlEscape="true" javaScriptEscape="true">${o.title}</s:escapeBody></b><br/><br /><s:escapeBody htmlEscape="true" javaScriptEscape="true">${o.description}</s:escapeBody><br/><br/>Koordinaten:<br/><small>Lon: ${o.coordinate.longitude} | Lat: ${o.coordinate.latitude} </small>", "${o.categorie.iconFileName}");</c:forEach>
				}
		</script>
		<c:if test="${sessionScope.LOGIN != null}"><script type="text/javascript">
				<%-- add formular data --%>
					setEditingFeature({
				<s:hasBindErrors name="observation">show: true,</s:hasBindErrors>
						lon: <c:out value="${observation.coordinate.longitude}" default="0"/>,
						lat: <c:out value="${observation.coordinate.latitude}" default="0"/>,
						selectedCategorieId: "<c:out value="${observation.categorie.id}"/>",
						description: "<s:escapeBody htmlEscape="true" javaScriptEscape="true"><c:out value="${fn:escapeXml(observation.description)}"/></s:escapeBody>",
						title: "<s:escapeBody htmlEscape="true" javaScriptEscape="true">${observation.title}</s:escapeBody>",
						action: "<s:url value="/"/>",
						lang: {
							title:"<fmt:message key="observation.title.title"/>",
							description:"<fmt:message key="observation.description.title"/>",
							categorie:"<fmt:message key="observation.categorie.title"/>"
						},
						errors: {
							description: "<s:bind path="observation.description" htmlEscape="true">${status.errorMessages[0]}</s:bind>",
							title: "<s:bind path="observation.title" htmlEscape="true">${status.errorMessages[0]}</s:bind>",
							categorie: "<s:bind path="observation.categorie" htmlEscape="true">${status.errorMessages[0]}</s:bind>"
						},
						categories:	[
				<c:forEach varStatus="status" var="categorie" items="${categories}">
							[${categorie.id}, "<s:escapeBody htmlEscape="true" javaScriptEscape="true">${categorie.title}</s:escapeBody>"]
					<c:if test="${!status.last}">,</c:if>
				</c:forEach>
						]
					});
			</script></c:if>
		</head>
		<body onload="init();">
			<table id="maintable" class="maintable" style="height: 100%; width: 100%;">
				<tr>
					<td colspan="2" class="header">
					<a href="<s:url value="/" htmlEscape="true" />"><img src="<s:url value="/static/img/logo.png" htmlEscape="true"/>" align="left" width="160" alt="Logo" ></img></a>
						<c:choose>
							<c:when test="${sessionScope.LOGIN != null}">
							<a href="<s:url value="/acc" />"><img src="<s:url value="/static/img/user_mng.png" htmlEscape="true"/>" alt="" height="18"/><b>${sessionScope.LOGIN.mail}</b></a> |
							<a href="<s:url value="/logout"/>"><fmt:message key="logout.button.title"/></a>

						</c:when>
						<c:otherwise>
							<a href="<s:url value="/login"/>"> <fmt:message key="login.button.title"/></a>
						</c:otherwise>
					</c:choose>

					<!-- Searchbox -->
					<form action="<s:url value="/"/>" method="GET" style="display:inline">
				| <input type="text" name="q" value="Search..." class="search" onfocus="this.value=''"/>
						<input type="image" src="<s:url value="/static/img/search.png" htmlEscape="true" />" alt="Search" class="searchbutton"/>
					</form>
					<a href="<s:url value="/help" />"><img src="<s:url value="/static/img/help.png" htmlEscape="true"/>" alt="" height="18"/></a>
					<hr style="border:solid #DDDDEE 1px"/><br/>
					<br />
				</td>
			</tr>
			<!-- Beginning of Content-->
			<tr class="content">
				<td class="sidebar">
					<h3><s:escapeBody htmlEscape="true"><fmt:message key="welcome.title"/></s:escapeBody></h3>
					<h4><s:escapeBody htmlEscape="true"><fmt:message key="greeting.title"/></s:escapeBody></h4>

					<!-- Beginning of observationlist-->

					<div id="results" class="observationlist">
						<table class="observationlist">
							<c:forEach var="o" end="8" items="${observations}">
								<tr>
									<td width="70%" class="observationlist_title">
										<img src="${o.categorie.iconFileName}" width="20px" height="20px" alt="<s:escapeBody htmlEscape="true">${o.categorie.title}</s:escapeBody>"></img>
										<b class="obsTitle"><s:escapeBody>${o.title}</s:escapeBody></b>
										<a href="#" onclick="goTo(${o.coordinate.longitude}, ${o.coordinate.latitude}, 16);"><img src="<s:url value="/static/img/show.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="observation.viewInMap.title"/></s:escapeBody>" align="right" height="20px"/></a>
											<c:if test="${sessionScope.LOGIN != null}">
											<a href="<s:url value="/r/o/${o.id}/new"/>"><img src="<s:url value="/static/img/report.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="observation.report.title"/></s:escapeBody>" align="right" height="20px"></img></a>
												<c:if test="${sessionScope.LOGIN.userGroup == 'ADMIN' || o.user.id == sessionScope.LOGIN.id}">
												<a href="<s:url value="/o/edit/${o.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="observation.edit.title"/></s:escapeBody>" align="right" height="20px"></img></a>
												</c:if>
											</c:if>
										<p class="observationlist_description"><s:escapeBody htmlEscape="true" javaScriptEscape="false" >${o.description}</s:escapeBody></p>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</td>
				<!-- beginning of map -->
				<td class="mapArea">
					<div class="mapArea" id="mapArea">
					</div>
				</td>
				<!-- End of Content-->
		</tr>
		<!-- beginning of footer-->
		<tr>
			<td colspan="2">
				<div align="center" class="footer" id="footer">
					<table class="footer">
						<tr>
							<td><a href="<s:url value="/" htmlEscape="true" />"><s:escapeBody htmlEscape="true"><fmt:message key="nav.home.title"/></s:escapeBody></a></td>
							<td align="right"><fmt:message key="copyright.title"/></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
