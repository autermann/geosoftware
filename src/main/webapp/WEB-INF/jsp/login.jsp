<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="title"/></title>
		<link href="<spring:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
	</head>
        <body>
            <div id="login">
                <p style="padding-top: 15%">
            <table align="center" style="border: 1px solid #CCE5FF;" >
                <tr><td bgcolor="#CCE5FF" width="40%" align="center">
                        


			<c:if test="${sessionScope.loginUser == null}">
                            <form:form modelAttribute="login" method="POST" cssStyle="padding-top: 12%">
					<b><fmt:message key="login.button" /></b><br /><br />
					<form:input path="mail" value="E-mail" /><br />
					<form:errors cssStyle="color: red;" path="mail" />
					<form:password path="password" showPassword="true" value="password"/><br /><br />
					<form:errors cssStyle="color: red;" path="password"/>
					<form:errors cssStyle="color: red;"/>
					<input type="submit" value="<fmt:message key="login.button" />"/>
					<input type="button" onClick="window.location='<spring:url value="/users/new"/>'" value="<fmt:message key="reg.button"/>" />
				</form:form>
			</c:if>
                                   <br /><br />
                        
                    </td></tr></table></p>
		<div>
                    <table class="footer" align="center" style="padding-top: 10%">
				<tr>
					<td><a href="<spring:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a></td>
					<td align="right"><fmt:message key="copyright"/></td>
				</tr>
			</table>
		</div>
        </div>
	</body>
</html>
