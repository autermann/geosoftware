<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="user.list"/></h2>
	<table width="25%" border="1">
		<tr>
			<th><fmt:message key="user.id"/></th>
			<th><fmt:message key="user.mail"/></th>
			<th><fmt:message key="user.edit"/></th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><a href="<spring:url value="/u/${user.id}"/>">${user.id}</a></td>
				<td>${user.mail}</td>
				<td><a href="<spring:url value="/u/edit/${user.id}"/>">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
