<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="report.list"/></h2>
	<table width="50%" border="1">
		<tr>
			<th><fmt:message key="report.id"/></th>
			<th><fmt:message key="report.author"/></th>
			<th><fmt:message key="report.observation"/></th>
			<th><fmt:message key="report.description"/></th>
			<th><fmt:message key="report.processed"/></th>
			<th><fmt:message key="report.actions"/></th>
		</tr>
		<c:forEach var="report" items="${reports}">
			<tr>
				<td>${report.id}</td>
				<td><a href="<spring:url value="/u/${report.author.id}"/>">${report.author.id}</a></td>
				<td><a href="<spring:url value="/o/${report.observation.id}"/>">${report.observation.id}</a></td>
				<td>${report.description}</td>
				<td>
					<c:choose>
						<c:when test="${report.processed}"><fmt:message key="report.processed.true"/></c:when>
						<c:otherwise><fmt:message key="report.processed.false"/></c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="<spring:url value="/r/edit/${report.id}"/>"><fmt:message key="report.edit"/></a>
					<a href="<spring:url value="/r/del/${observation.id}"/>"><fmt:message key="report.delete"/></a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
