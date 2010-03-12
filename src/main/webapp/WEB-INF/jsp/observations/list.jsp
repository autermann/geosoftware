<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="observation.list"/></h2>
	<table width="25%" border="1">
		<tr>
			<th><fmt:message key="observation.id"/></th>
			<th><fmt:message key="observation.title"/></th>
			<th><fmt:message key="observation.description"/></th>
		</tr>
		<c:forEach var="observation" items="${observations}">
			<tr>
				<td>
					<a href="<spring:url value="/observations/${observation.id}"/>">
						${observation.id}
					</a>
				</td>
				<td>${observation.title}</td>
				<td>${observation.description}</td>
			</tr>
		</c:forEach>
	</table>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
