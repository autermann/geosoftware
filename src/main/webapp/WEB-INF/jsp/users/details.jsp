<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="user.details.title"/> <c:out value="${user.id}"/></h2>
	<table border="1">
		<tr>
			<td><fmt:message key="user.id"/>:</td>
			<td><c:out value="${user.id}" default="-" /></td>
		</tr>
		<tr>
			<td><fmt:message key="user.mail"/>:</td>
			<td><c:out value="${user.mail}" default="-" /></td>
		</tr>
		<tr>
			<td><fmt:message key="user.name"/>:</td>
			<td><c:out value="${user.name}" default="-" /></td>
		<tr/>
		<tr>
			<td><fmt:message key="user.familyName"/>:</td>
			<td><c:out value="${user.familyName}" default="-" /></td>
		<tr/>
		<tr>
			<td><fmt:message key="user.creationDate"/>:</td>
			<td><fmt:formatDate value="${user.creationDate}"/></td>
		<tr/>
	</table>
	<p>
		<a href="<spring:url value="/users"/>"><fmt:message key="nav.back"/></a>
	</p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
