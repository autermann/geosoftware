<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="categorie.delete"/></h2>
	<form:form modelAttribute="categorie">
		<table border="1">
			<tr>
				<td><fmt:message key="categorie.id"/>:</td>
				<td>${categorie.id}"</td>
			</tr>
			<tr>
				<td><fmt:message key="categorie.title"/>:</td>
				<td>${categorie.title}</td>
			</tr>
			<tr>
				<td><fmt:message key="categorie.description"/>:</td>
				<td>${categorie.description}</td>
			<tr/>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="Delete" />
					<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/admin/categories"></s:url>'">
				</td>
			</tr>
		</table>
	</form:form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
