<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="user.delete"/></h2>
	<form:form modelAttribute="user">
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
				<td>${user.creationDate}</td>
			<tr/>
			<td align="center" colspan="2">
				<input type="submit" value="Submit" />
				<input type="button" value="Cancel" onclick="window.location.href='<spring:url value="/acc"></spring:url>'">
			</td>
		</table>
	</form:form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
