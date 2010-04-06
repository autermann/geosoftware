<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td> <h3><s:escapeBody htmlEscape="true"><fmt:message key="categorie.delete.title"/></s:escapeBody></h3> </td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>
			<form:form modelAttribute="categorie">
				<table class="management_lists">
					<tr>
						<td class="management_lists"><fmt:message key="categorie.id.title"/>:</td>
						<td class="management_lists"><s:escapeBody htmlEscape="true">${categorie.id}</s:escapeBody></td>
					</tr>
					<tr>
						<td class="management_lists"><fmt:message key="categorie.title.title"/>:</td>
						<td class="management_lists"><s:escapeBody htmlEscape="true">${categorie.title}</s:escapeBody></td>
					</tr>
					<tr>
						<td class="management_lists"><fmt:message key="categorie.description.title"/>:</td>
						<td class="management_lists"><s:escapeBody htmlEscape="true">${categorie.description}</s:escapeBody></td>
					<tr/>
					<tr>
						<td align="center" colspan="2" class="management_lists">
							<input type="submit" value="Delete" />
							<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/c"></s:url>'">
						</td>
					</tr>
				</table>
			</form:form>
		</td>
		<td width=30%></td>
	</tr>
	<tr style="height: 25%">
		<td></td>
		<td align="center">
			<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home.title"/></a> <fmt:message key="copyright.title"/>
		</td>
		<td></td>
	</tr>
</table>




