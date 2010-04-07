<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td><h3><fmt:message key="categorie.list.title"/></h3><br />
			<p align="right"><a href="<s:url value="/c/new"/>">
					<img src="<s:url value="/static/img/add.png" htmlEscape="true"/>" height="24" alt="" />
					<fmt:message key="categorie.new.title"/>
				</a></p>
		</td>
		<td></td>
	</tr>
	<tr style="height: 33%">
		<td width=20%></td>
		<td width=40%>
			<table class="management_lists">
				<tr>
					<th class="management_lists"><s:escapeBody htmlEscape="true"><fmt:message key="categorie.id.title"/></s:escapeBody></th>
					<th class="management_lists"><s:escapeBody htmlEscape="true"><fmt:message key="categorie.title.title"/></s:escapeBody></th>
					<th class="management_lists"><s:escapeBody htmlEscape="true"><fmt:message key="categorie.description.title"/></s:escapeBody></th>
					<th class="management_lists"><s:escapeBody htmlEscape="true"><fmt:message key="categorie.iconFileName.title" /></s:escapeBody></th>
					<th class="manegement_lists"><s:escapeBody htmlEscape="true"><fmt:message key="categorie.icon.title" /></s:escapeBody></th>
					<th class="management_lists"><s:escapeBody htmlEscape="true"><fmt:message key="categorie.action.title" /></s:escapeBody></th>
				</tr>
				<c:forEach var="categorie" items="${categories}">
					<tr>
						<td class="management_lists">${categorie.id}</td>
						<td class="management_lists"><s:escapeBody htmlEscape="true">${categorie.title}</s:escapeBody></td>
						<td class="management_lists"><s:escapeBody htmlEscape="true">${categorie.description}</s:escapeBody></td>
						<td class="management_lists">${categorie.iconFileName}</td>
						<td class="management_lists_image"><img src="${categorie.iconFileName}" alt="<s:escapeBody htmlEscape="true"><fmt:message key="categorie.icon.title" /></s:escapeBody>"></td>
						<td class="management_lists_image">
							<a href="<s:url value="/c/edit/${categorie.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="categorie.edit.title" /></s:escapeBody>"></a>
							<a href="<s:url value="/c/del/${categorie.id}"/>"><img src="<s:url value="/static/img/delete.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="categorie.delete.title" /></s:escapeBody>"></a>
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

			<a href="<s:url value="/" htmlEscape="true" />"><s:escapeBody htmlEscape="true"><fmt:message key="nav.home.title"/></s:escapeBody></a> <fmt:message key="copyright.title"/>
		</td>
		<td></td>
	</tr>
</table>
