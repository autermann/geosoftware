<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="tab.title"/></title>
		<link href="<s:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<table align="center" style="height: 100%">
			<tr style="height: 33%">
				<td>
					<a href="<s:url value="/" htmlEscape="true" />"><img src="<s:url value="/static/img/logo.png" htmlEscape="true"/>" align="right" alt="Logo" ></img></a>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr style="height: 33%">
				<td width=33%></td>
				<td width=33% class="error">
					<center>
						<h2 style="font-style: oblique;">Internal Error</h2>
							We are working on it...
						<br/>
						<br/>
						<br/>
						<input type="button" onclick="window.location=<s:url value="/"/>" value="Go Back" />
					</center>
				</td>
				<td width=33%></td>
			</tr>
			<tr style="height: 33%">
				<td></td>
				<td align="center">
					<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home.title"/></a> <fmt:message key="copyright.title"/>
				</td>
				<td></td>
			</tr>
		</table>
	</body>
</html>