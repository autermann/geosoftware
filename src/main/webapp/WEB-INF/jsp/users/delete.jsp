<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<table align="center" style="height: 100%">

	<tr style="height: 25%">
		<td></td>
		<td><h3><s:escapeBody htmlEscape="true"><fmt:message key="user.delete"/></s:escapeBody></h3> </td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>




             <form:form modelAttribute="user">
		<table class="management_lists">
			<tr>
				<td class="management_lists"><fmt:message key="user.id"/>:</td>
				<td class="management_lists">${user.id}</td>
			</tr>
			<tr>
				<td class="management_lists"><fmt:message key="user.mail"/>:</td>
				<td class="management_lists">${user.mail}</td>
			</tr>
			<tr>
				<td class="management_lists"><fmt:message key="user.name"/>:</td>
				<td class="management_lists">${user.name}</td>
			<tr/>
			<tr>
				<td class="management_lists"><fmt:message key="user.familyName"/>:</td>
				<td class="management_lists">${user.familyName}</td>
			<tr/>
			<tr>
				<td class="management_lists"><fmt:message key="user.creationDate"/>:</td>
				<td class="management_lists">${user.creationDate}</td>
			<tr/>
			<td align="center" colspan="2" class="management_lists">
				<input type="submit" value="Submit" />
				<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/acc"></s:url>'">
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