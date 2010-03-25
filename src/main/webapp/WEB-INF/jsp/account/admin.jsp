<%@include file="../includes.jsp" %>
<%@include file="../header.jsp" %>
<div>
	<ul>
		<li><a href="<spring:url value="/u" />"><fmt:message key="userManagment"/></a></li>
		<li><a href="<spring:url value="/o" />"><fmt:message key="observationManagment"/></a></li>
		<li><a href="<spring:url value="/c" />"><fmt:message key="categorieManagment"/></a></li>
		<li><a href="<spring:url value="/r" />"><fmt:message key="reportManagment"/></a></li>
	</ul>
</div>
<%@include file="../footer.jsp" %>