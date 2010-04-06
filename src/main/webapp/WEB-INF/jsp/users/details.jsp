<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="user.details.title"/> <c:out value="${user.id}"/></h2>
	<table border="1">
		<tr>
			<td><fmt:message key="user.id"/>:</td>
			<td>${user.id}</td>
		</tr>
		<tr>
			<td><fmt:message key="user.mail"/>:</td>
			<td>${user.mail}</td>
		</tr>
		<tr>
			<td><fmt:message key="user.name"/>:</td>
			<td>${user.name}</td>
		<tr/>
		<tr>
			<td><fmt:message key="user.familyName"/>:</td>
			<td>${user.familyName}</td>
		<tr/>
		<tr>
			<td><fmt:message key="user.creationDate"/>:</td>
			<td><fmt:formatDate value="${user.creationDate}"/></td>
		<tr/>
		<tr>
			<td><fmt:message key="user.userGroup"/>:</td>
			<td>${user.userGroup}</td>
		<tr/>
	</table>
	<p><a href="<s:url value="/u"/>"><fmt:message key="nav.back"/></a></p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
