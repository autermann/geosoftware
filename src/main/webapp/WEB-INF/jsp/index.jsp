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
						addMarker(${o.coordinate.longitude}, ${o.coordinate.latitude},"<b><s:escapeBody htmlEscape="true" javaScriptEscape="true">${o.title}</s:escapeBody></b><br/><br /><s:escapeBody htmlEscape="true" javaScriptEscape="true">${o.description}</s:escapeBody><br/><br/>Koordinaten:<br/><small>Lon: ${o.coordinate.longitude} | Lat: ${o.coordinate.latitude} </small>", "<s:url value="/static/img/${o.categorie.iconFileName}"/>");</c:forEach>
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
								[${categorie.id}, "<s:escapeBody htmlEscape="true" javaScriptEscape="true">${categorie.title}</s:escapeBody>"]
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
								<a href="<s:url value="/acc" />"><b>${sessionScope.LOGIN.mail}</b></a> | 
								<a href="<s:url value="/logout"/>"><fmt:message key="logout.button"/></a>
								
                                                            </c:when>
                                                            <c:otherwise>
								<a href="<s:url value="/login"/>"> <fmt:message key="login.button"/></a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    <form action="<s:url value="/"/>" method="GET" style="display:inline">
                                                             | <input type="text" name="q" style="background: #EEEEEE; border: 1px solid silver;" value="Search..." onfocus="this.value=''"/>
                                                            <input type="submit" value="Search" style="font-size: 12px; font-weight: normal; background: #EEEEEE; border: 1px solid silver;"/>

							                                                
                                                     </form>
                                        </div>

                                                   
						
                                                    

                                <hr color="#CCCCEE"></hr>
                                            

                                <table width="30%"><tr><td>
						<h3><fmt:message key="welcome"/></h3>
                                                <h4><fmt:message key="begruessung"/></h4>
                                        </td></tr>
					</table>
					<div>
						


                             <table width="30%" cellspacing="5" cellpadding="4">
				<div id="results">
							<c:forEach var="o" items="${observations}">
								
                                                                    
                                                                    <tr>
                                                                        <td width="70%" class="observationlist_title">
                                                                                <img src="<s:url value="/static/img/${o.categorie.iconFileName}" htmlEscape="true" />" width="14px" height="14px" alt="${o.categorie.title}">
                                                                        <b><s:escapeBody>${o.title}</s:escapeBody></b><br />
                                                                            
                                                                            
                                                                       <p class="observationlist_description"><s:escapeBody>${o.description}</s:escapeBody></p>
                                                                            
                                                                            </td>
                                                                            <td width="30%">
                                                                                <a href="#" onclick="goTo(${o.coordinate.longitude}, ${o.coordinate.latitude}, 16);" class="goto"><img src="<s:url value="/static/img/show.png" htmlEscape="true" />" alt="<fmt:message key="observation.viewInMap"/>"></a>
                                                                                    <c:if test="${sessionScope.LOGIN != null}">
                                                                                        <a href="<s:url value="/r/o/${o.id}/new"/>"><img src="<s:url value="/static/img/report.png" htmlEscape="true" />" alt="<fmt:message key="observation.report"/>"></a>
                                                                                    <c:if test="${sessionScope.LOGIN.userGroup == 'ADMIN' || o.user.id == sessionScope.LOGIN.id}">
											<a href="<s:url value="/o/edit/${o.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<fmt:message key="observation.edit"/>"></a>
                                                                                    </c:if>
                                                                                    </c:if>
                                                                                 <br /><br />
                                                                            </td>
                                                                    </tr>
								
								
							</c:forEach>
						</div>
                            </table>
					</div>
					<div id="map"></div>
					<div align="center">
                                            <br />
						<table class="footer">
							<tr>
								<td><a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a></td>
								<td align="right"><fmt:message key="copyright"/></td>
							</tr>
						</table>
					</div>
				</body>
			</html>
