<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="title"/></title>
		<link href="<s:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
	</head>
	<body>

		<table align="center" style="height: 100%">

			<tr style="height: 33%">
				<td>

					<a href="<s:url value="/" htmlEscape="true" />"><img src="<s:url value="/static/img/logo.png" htmlEscape="true"/>" align="right" alt="Logo" ></img></a>

				</td>
				<td class="login_statement"><s:escapeBody htmlEscape="true"><fmt:message key="login.statement"/></s:escapeBody><br /><br /> </td>
				<td></td>
			</tr>
			<tr style="height: 33%">

				<td width=33%></td>
				<td width=33% class="loginform">


					<form:form name="login" modelAttribute="login" method="POST">
						<b><fmt:message key="login.button" /></b>
						<br/><br/>
						<table>
							<tr>
								<td>
									<s:escapeBody htmlEscape="true"><fmt:message key="user.mail"/>: </s:escapeBody>
								</td>

								<td>
									<form:input path="mail" value="E-mail" onfocus="this.value=''" size="30"/>
									<form:errors cssStyle="color: red;" path="mail" />
								</td>
							</tr>
							<tr>
								<td>
									<s:escapeBody htmlEscape="true"><fmt:message key="user.password"/>: </s:escapeBody>

								</td>

								<td>
									<form:password path="password" onfocus="this.value=''" size="30" />
									<form:errors cssStyle="color: red;" path="password"/>
								</td>
							</tr>
							<tr align="center">
								<form:errors cssStyle="color: red;"/>
								<td>
									<input type="submit" value="<s:escapeBody htmlEscape="true"><fmt:message key="login.button" /></s:escapeBody>"/>
								</td> <td>
									<input type="button" onClick="window.location='<s:url value="/signup"/>'" value="<s:escapeBody htmlEscape="true"><fmt:message key="reg.button"/></s:escapeBody>" />
								</td>
							</tr>
						</table>

					</form:form>





				</td>
				<td width=33%></td>


			</tr>
			<tr style="height: 33%">
				<td></td>
				<td align="center">

					<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a> <fmt:message key="copyright"/>
				</td>
				<td></td>
			</tr>
		</table>





	</body>
</html>
