<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="observation.list"/></h2>
        <p>
		<a href="<spring:url value="/observations/new"/>">
			<fmt:message key="observation.new"/>
		</a>
	</p>
	<table width="50%" border="1">
		<tr>
			<th><fmt:message key="observation.id"/></th>
			<th><fmt:message key="observation.title"/></th>
			<th><fmt:message key="observation.description"/></th>
                        <th><fmt:message key="observation.edit"/></th>
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
                                <td>
					<a href="<spring:url value="/observations/edit/${observation.id}"/>">
						Edit
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
