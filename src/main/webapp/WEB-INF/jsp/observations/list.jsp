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
				<td>${observation.id}</td>
				<td>${observation.title}</td>
				<td>${observation.description}</td>
				<td>
					<c:if test="${sessionScope.LOGIN.id eq observation.user.id || sessionScope.LOGIN.userGroup eq 'ADMIN'}">
						<a href="<s:url value="/o/edit/${observation.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<fmt:message key="observation.edit" />"></a>
						<a href="<s:url value="/o/del/${observation.id}"/>"><img src="<s:url value="/static/img/delete.png" htmlEscape="true" />" alt="<fmt:message key="observation.delete" />"></a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
