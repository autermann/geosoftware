<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<div>

	<div align="right">
                <form:form modelAttribute="login" method="POST">
                    <b><fmt:message key="login.button" /></b>
                    <form:input path="mail" value="user@sloth.de" />
			<form:errors cssStyle="color:red;" path="mail"/>
                    <form:password path="password" showPassword="true" value="Passwort"/>
			<form:errors cssStyle="color:red;" path="password"/>
			<input type="submit" value="<fmt:message key="login.button" />"/>
		
                    <input type="button" onClick="window.location='<spring:url value="/users/new"/>'" value="<fmt:message key="reg.button"/>" />

		</form:form>
	</div>


    	

	<h2><fmt:message key="welcome"/></h2>
	<ul>
		<li><a href="<spring:url value="/users" htmlEscape="true" />"><fmt:message key="userManagement"/></a></li>
                <li><a href="<spring:url value="/observations" htmlEscape="true" />"><fmt:message key="observationManagement"/></a></li>
	</ul>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
