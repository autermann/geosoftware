<%@include file="../includes.jsp" %>
<%@include file="../header.jsp" %>
<div>
	<ul>
		<li><a href="<s:url value="/u/del" />"><fmt:message key="user.delete"/></a></li>
		<li><a href="<s:url value="/u/edit" />"><fmt:message key="user.edit"/></a></li>
		<li><a href="<s:url value="/logout" />"><fmt:message key="logout"/></a></li>
		<li><a href="<s:url value="/o/own" />"><fmt:message key="observations.own"/></a></li>
		<li><a href="<s:url value="/o" />"><fmt:message key="observations.all"/></a></li>
	</ul>
	<a href="<c:out value="${headerValues['referer'][0]}" default="/"/>" ><fmt:message key="nav.back"/></a>
</div>
<%@include file="../footer.jsp" %>