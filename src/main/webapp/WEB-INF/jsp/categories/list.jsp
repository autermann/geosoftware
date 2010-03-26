<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="categorie.list"/></h2>
	<p>
		<a href="<spring:url value="/categories/new"/>">
			<fmt:message key="categorie.new"/>
		</a>
	</p>
	<table width="50%" border="1">
		<tr>
			<th><fmt:message key="categorie.id"/></th>
			<th><fmt:message key="categorie.title"/></th>
			<th><fmt:message key="categorie.description"/></th>
			<th><fmt:message key="categorie.iconFileName" /></th>
			<th><fmt:message key="categorie.action" /></th>
		</tr>
		<c:forEach var="categorie" items="${categories}">
			<tr>
				<td>${categorie.id}</td>
				<td>${categorie.title}</td>
				<td>${categorie.description}</td>
				<td>${categorie.iconFileName}</td>
				<td>
					<a href="<spring:url value="/c/edit/${categorie.id}"/>"><fmt:message key="categorie.edit" /></a>
					<a href="<spring:url value="/c/del/${categorie.id}"/>"><fmt:message key="categorie.delete" /></a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
