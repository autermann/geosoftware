<%@include file="../includes.jsp" %>
<%@include file="../header.jsp" %>
<div>
	<ul>
		<li><a href="<spring:url value="/admin/users" />"><fmt:message key="userManagment"/></a></li>
		<li><a href="<spring:url value="/admin/observations" />"><fmt:message key="observationManagment"/></a></li>
		<li><a href="<spring:url value="/admin/categories" />"><fmt:message key="categorieManagment"/></a></li>
	</ul>
</div>
<%@include file="../footer.jsp" %>