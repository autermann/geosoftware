<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><s:escapeBody htmlEscape="true"><fmt:message key="observation.delete"/></s:escapeBody></h2>
	<form:form modelAttribute="observation">
		<table border="1">
			<tr>
				<td><fmt:message key="observation.id"/>:</td>
				<td>${observation.id}</td>
			</tr>
			<tr>
				<td><fmt:message key="observation.title"/>:</td>
				<td><s:escapeBody htmlEscape="true">${observation.title}</s:escapeBody></td>
			</tr>
			<tr>
				<td><fmt:message key="observation.description"/>:</td>
				<td><s:escapeBody htmlEscape="true">${observation.description}</s:escapeBody></td>
			<tr/>
			<tr>
				<td><fmt:message key="observation.user"/>:</td>
				<td>${observation.user}</td>
			<tr/>
			<tr>
				<td><fmt:message key="observation.creationDate"/>:</td>
				<td>${observation.creationDate}</td>
			<tr/>
			<td align="center" colspan="2">
				<input type="submit" value="Submit" />
				<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/o"></s:url>'">
			</td>
		</table>
	</form:form>
	<p><a href="<s:url value="/o"/>"><fmt:message key="nav.back"/></a></p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
