<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/mapheader.jsp" %>

<div>
	<h2><fmt:message key="welcome"/></h2>
	<ul>
		<li><a href="<spring:url value="/users" htmlEscape="true" />"><fmt:message key="userManagement"/></a></li>
		<li><a href="<spring:url value="/observations" htmlEscape="true" />"><fmt:message key="observationManagement"/></a></li>
                <li><a href="<spring:url value="/categories" htmlEscape="true" />"><fmt:message key="categoryManagement"/></a></li>
	</ul>
</div> 
<div id="map"></div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>


