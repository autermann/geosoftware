<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<table align="center" style="height: 100%">

	<tr style="height: 25%">
		<td></td>
		<td><h3><fmt:message key="observation.list"/></h3><br /><br /> </td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>
			<table class="management_lists">
				<tr>
					<th class="management_lists"><fmt:message key="observation.id"/></th>
					<th class="management_lists"><fmt:message key="observation.title"/></th>
					<th class="management_lists"><fmt:message key="observation.description"/></th>
					<th class="management_lists"><fmt:message key="observation.edit"/></th>
				</tr>
				<c:forEach var="observation" items="${observations}">
					<tr>
						<td class="management_lists">${observation.id}</td>
						<td class="management_lists"><s:escapeBody htmlEscape="true" javaScriptEscape="false">${observation.title}</s:escapeBody></td>
						<td class="management_lists"><s:escapeBody htmlEscape="true" javaScriptEscape="false">${observation.description}</s:escapeBody></td>
						<td class="management_lists">
							<c:if test="${sessionScope.LOGIN.id eq observation.user.id || sessionScope.LOGIN.userGroup eq 'ADMIN'}">
								<a href="<s:url value="/o/edit/${observation.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="observation.edit" /></s:escapeBody>"></a>
								<a href="<s:url value="/o/del/${observation.id}"/>"><img src="<s:url value="/static/img/delete.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="observation.delete" /></s:escapeBody>"></a>
								<a href="<s:url value="/r/o/${o.id}/new"/>"><img src="<s:url value="/static/img/report.png" htmlEscape="true" />" alt="<fmt:message key="observation.report"/>"/></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td width=30%></td>
	</tr>
	<tr style="height: 25%">
		<td></td>
		<td align="center">
			<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a> <fmt:message key="copyright"/>
		</td>
		<td></td>
	</tr>
</table>




