<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="welcome"/></h2>
	<ul>
		<li><a href="<spring:url value="/users/new" htmlEscape="true" />"><fmt:message key="createNewUser"/></a></li>
	</ul>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
