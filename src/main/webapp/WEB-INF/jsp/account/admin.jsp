<%@include file="../includes.jsp" %>
<%@include file="../header.jsp" %>
<div>
	<ul>
		<li><a href="<spring:url value="/u" />"><fmt:message key="userManagement"/></a></li>
		<li><a href="<spring:url value="/o" />"><fmt:message key="observationManagement"/></a></li>
		<li><a href="<spring:url value="/c" />"><fmt:message key="categorieManagement"/></a></li>
		<li><a href="<spring:url value="/r" />"><fmt:message key="reportManagement"/></a></li>
	</ul>
</div>
<%@include file="../footer.jsp" %>