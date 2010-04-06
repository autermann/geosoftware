<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<table align="center" style="height: 100%">

	<tr style="height: 25%">
		<td></td>
		<td><h3><s:escapeBody htmlEscape="true"><fmt:message key="observation.delete"/></s:escapeBody></h3> </td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>




                <form:form modelAttribute="observation">
		<table class="management_lists">
			<tr>
				<td class="management_lists"><fmt:message key="observation.id"/>:</td>
				<td class="management_lists">${observation.id}</td>
			</tr>
			<tr>
				<td class="management_lists"><fmt:message key="observation.title"/>:</td>
				<td class="management_lists"><s:escapeBody htmlEscape="true">${observation.title}</s:escapeBody></td>
			</tr>
			<tr>
				<td class="management_lists"><fmt:message key="observation.description"/>:</td>
				<td class="management_lists"><s:escapeBody htmlEscape="true">${observation.description}</s:escapeBody></td>
			<tr/>
			<tr>
				<td class="management_lists"><fmt:message key="observation.user"/>:</td>
				<td class="management_lists">${observation.user}</td>
			<tr/>
			<tr>
				<td class="management_lists"><fmt:message key="observation.creationDate"/>:</td>
				<td class="management_lists">${observation.creationDate}</td>
			<tr/>
			<td align="center" colspan="2">
				<input type="submit" value="Submit" />
				<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/o"></s:url>'">
			</td>
		</table>
	</form:form>



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