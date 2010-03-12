<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="observation.details.title"/> <c:out value="${observation.id}"/></h2>
	<table border="1">
		<tr>
			<td><fmt:message key="observation.id"/>:</td>
			<td><c:out value="${observation.id}" default="-" /></td>
		</tr>
		<tr>
			<td><fmt:message key="observation.title"/>:</td>
			<td><c:out value="${observation.title}" default="-" /></td>
		</tr>
		<tr>
			<td><fmt:message key="observation.description"/>:</td>
			<td><c:out value="${observation.description}" default="-" /></td>
		<tr/>
		<tr>
			<td><fmt:message key="observation.user"/>:</td>
			<td><c:out value="${observation.user}" default="-" /></td>
		<tr/>
		<tr>
			<td><fmt:message key="observation.creationDate"/>:</td>
			<td><fmt:formatDate value="${observation.creationDate}"/></td>
		<tr/>
                <tr>
			<td><fmt:message key="observation.observationCategorie"/>:</td>
			<td><fmt:formatDate value="${observation.observationCategorie}"/></td>
		<tr/>
                    <tr>
			<td><fmt:message key="observation.coordinate"/>:</td>
			<td>&nbsp;</td>
		<tr/>
           
	</table>
	<p>
		<a href="<spring:url value="/observations"/>"><fmt:message key="nav.back"/></a>
	</p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
