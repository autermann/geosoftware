<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="observation.details.title"/> <c:out value="${observation.id}"/></h2>
	<table border="1">
		<tr>
			<td><fmt:message key="observation.id"/>:</td>
			<td>${observation.id}</td>
		</tr>
		<tr>
			<td><fmt:message key="observation.title"/>:</td>
			<td><s:escapeBody htmlEscape="true">${observation.title}</s:escapeBody></td>
		</tr>
		<tr>
			<td><fmt:message key="observation.description"/>:</td>
			<td><s:escapeBody htmlEscape="true">${observation.description}</s:escapeBody></td>
		<tr/>
		<tr>
			<td><fmt:message key="observation.user"/>:</td>
			<td>${observation.user}</td>
		<tr/>
		<tr>
			<td><fmt:message key="observation.creationDate"/>:</td>
			<td><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${observation.creationDate}"/></td>
		<tr/>
		<tr>
			<td><fmt:message key="observation.observationCategorie"/>:</td>
			<td><s:escapeBody htmlEscape="true">${observation.categorie}</s:escapeBody></td>
		<tr/>
		<tr>
			<td><fmt:message key="observation.coordinate"/>:</td>
			<td><c:out value="${observation.coordinate}" /></td>
		<tr/>

	</table>
			<p><a href="<s:url value="/observations"/>"><s:escapeBody htmlEscape="true"><fmt:message key="nav.back"/></s:escapeBody></a></p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
