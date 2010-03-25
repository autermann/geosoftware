<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="observation.list"/></h2>
	<table width="50%" border="1">
		<tr>
			<th><fmt:message key="observation.id"/></th>
			<th><fmt:message key="observation.title"/></th>
			<th><fmt:message key="observation.description"/></th>
			<th><fmt:message key="observation.edit"/></th>
		</tr>
		<c:forEach var="observation" items="${observations}">
			<tr>
				<td><a href="<spring:url value="/o/${observation.id}"/>">${observation.id}</a></td>
				<td>${observation.title}</td>
				<td>${observation.description}</td>
				<td>
					<c:if test="${sessionScope['loginUser'].id == observation.user.id || sessionScope['loginUser'].userGroup == ADMIN}">
						<a href="<spring:url value="/o/edit/${observation.id}"/>">Edit</a>
						<a href="<spring:url value="/o/del/${observation.id}"/>">Delete</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
