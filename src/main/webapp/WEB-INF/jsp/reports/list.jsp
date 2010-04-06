<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td><h3><fmt:message key="report.list.title"/></h3><br /> </td>
		<td></td>
	</tr>
	<tr style="height: 33%">
		<td width=20%></td>
		<td width=40%>
			<table class="management_lists">
				<tr>
					<th class="management_lists"><fmt:message key="report.id.title"/></th>
					<th class="management_lists"><fmt:message key="report.author.title"/></th>
					<th class="management_lists"><fmt:message key="report.observation.title"/></th>
					<th class="management_lists"><fmt:message key="report.description.title"/></th>
					<th class="management_lists"><fmt:message key="report.processed.title"/></th>
					<th class="management_lists"><fmt:message key="report.actions.title"/></th>
				</tr>
				<c:forEach var="report" items="${reports}">
					<tr>
						<td class="management_lists">${report.id}</td>
						<td class="management_lists"><a href="<s:url value="/u/${report.author.id}"/>">${report.author.id}</a></td>
						<td class="management_lists"><a href="<s:url value="/o/${report.observation.id}"/>">${report.observation.id}</a></td>
						<td class="management_lists">${report.description}</td>
						<td class="management_lists">
							<c:choose>
								<c:when test="${report.processed}"><fmt:message key="report.processed.true.title"/></c:when>
								<c:otherwise><fmt:message key="report.processed.false.title"/></c:otherwise>
							</c:choose>
						</td>
						<td class="management_lists">
							<a href="<s:url value="/r/edit/${report.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="report.edit.title"/></s:escapeBody>"</a>
							<a href="<s:url value="/r/del/${report.id}"/>"><img src="<s:url value="/static/img/delete.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="report.delete.title"/></s:escapeBody>"></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td width=40%></td>
	</tr>
	<tr style="height: 41%">
		<td></td>
		<td align="center">
			<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home.title"/></a> <fmt:message key="copyright.title"/>
		</td>
		<td></td>
	</tr>
</table>

