<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div>
	<div>
		<form:form modelAttribute="user" method="POST">
			<fmt:message key="user.eMail"/>:<form:input path="eMail" />
			<form:errors cssStyle="color:red;" path="eMail"/>
			<fmt:message key="user.password"/>:<form:password path="hashedPassword" showPassword="true"/>
			<form:errors cssStyle="color:red;" path="hashedPassword"/>
			<input type="submit" value="<fmt:message key="login.button" />"/>
		</form:form>
	</div>
	<h2><fmt:message key="welcome"/></h2>
	<ul>
		<li><a href="<spring:url value="/users" htmlEscape="true" />"><fmt:message key="userManagement"/></a></li>
	</ul>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
