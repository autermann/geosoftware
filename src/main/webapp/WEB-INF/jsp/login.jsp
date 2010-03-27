<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="title"/></title>
		<link href="<s:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="login" style="padding-top: 15%">
			<table align="center" style="border: 1px solid #CCE5FF;" >
                <tr>
					<td bgcolor="#CCE5FF" width="40%" align="center">
						<form:form name="login" modelAttribute="login" method="POST" cssStyle="padding-top: 12%">
							<b><fmt:message key="login.button" /></b>
							<br/><br/>
							<form:input path="mail" value="E-mail" />
							<form:errors cssStyle="color: red;" path="mail" />
							<br/>
							<form:password path="password" onfocus="this.value=''"/>
							<form:errors cssStyle="color: red;" path="password"/>
							<br/>
							<form:errors cssStyle="color: red;"/>
							<br/><br/>
							<input type="submit" value="<fmt:message key="login.button" />"/>
							<input type="button" onClick="window.location='<s:url value="/signup"/>'" value="<fmt:message key="reg.button"/>" />
						</form:form>
						<br/>
						<br/>
					</td>
				</tr>
			</table>
			<div>
				<table class="footer" align="center" style="padding-top: 10%">
					<tr>
						<td><a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a></td>
						<td align="right"><fmt:message key="copyright"/></td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>
