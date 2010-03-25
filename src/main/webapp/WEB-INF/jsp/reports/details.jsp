h<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="report.details"/> <c:out value="${report.id}"/></h2>
	<table border="1">
		<tr>
			<td><fmt:message key="report.id"/>:</td>
			<td>${report.id}</td>
		</tr>
		<tr>
			<td><fmt:message key="report.description"/>:</td>
			<td>${report.description}</td>
		<tr/>
		<tr>
			<td><fmt:message key="report.author.mail"/>:</td>
			<td>${report.author.mail}</td>
		<tr/>
		<tr>
			<td><fmt:message key="report.observation"/>:</td>
			<td>${report.observation.title}</td>
		<tr/>
		<tr>
			<td><fmt:message key="report.creationDate"/>:</td>
			<td><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${report.creationDate}"/></td>
		<tr/>
		<tr>
			<td><fmt:message key="report.lastUpdateDate"/>:</td>
			<td><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${report.lastUpdateDate}"/></td>
		<tr/>

		<tr>
			<td><fmt:message key="report.processed"/>:</td>
			<c:choose>
				<c:when test="${report.processed}"><fmt:message key="report.processed.true"/></c:when>
				<c:otherwise><fmt:message key="report.processed.false"/></c:otherwise>
			</c:choose>
		<tr/>
	</table>
	<form:form method="POST" >
		<input type="submit" value="Submit" />

	</form:form>
	<p><a href="<spring:url value="/r"/>"><fmt:message key="nav.back"/></a></p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>