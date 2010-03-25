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
			<td>${observation.title}</td>
		</tr>
		<tr>
			<td><fmt:message key="observation.description"/>:</td>
			<td>${observation.description}</td>
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
			<td>${observation.categorie}</td>
		<tr/>
		<tr>
			<td><fmt:message key="observation.coordinate"/>:</td>
			<td><c:out value="${observation.coordinate}" /></td>
		<tr/>

	</table>
	<p><a href="<spring:url value="/observations"/>"><fmt:message key="nav.back"/></a></p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
