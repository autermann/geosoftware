<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<div align="right">
		<c:choose>
			<c:when test="${sessionScope.loginUser == null}">
				<form:form modelAttribute="login" method="POST">
					<b><fmt:message key="login.button" /></b>
					<form:input path="mail"/>
					<form:errors cssStyle="color: red;" path="mail" />
					<form:password path="password" showPassword="true"/>
					<form:errors cssStyle="color: red;" path="password"/>
					<form:errors cssStyle="color: red;"/>
					<input type="submit" value="<fmt:message key="login.button" />"/>
					<input type="button" onClick="window.location='<spring:url value="/users/new"/>'" value="<fmt:message key="reg.button"/>" />
				</form:form>
			</c:when>
			<c:otherwise>
				<b>${sessionScope.loginUser.mail}</b>
			</c:otherwise>
		</c:choose>
	</div>
	<h2><fmt:message key="welcome"/></h2>
	<ul>
		<li><a href="<spring:url value="/users" htmlEscape="true" />"><fmt:message key="userManagement"/></a></li>
		<li><a href="<spring:url value="/observations" htmlEscape="true" />"><fmt:message key="observationManagement"/></a></li>
	</ul>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
