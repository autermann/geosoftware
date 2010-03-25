<%@include file="../includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Unauthorized Access</title>
		<link href="<spring:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="login" style="padding-top: 15%">
			<table align="center" style="border: 1px solid #CCE5FF;" >
				<tr>
					<td bgcolor="#CCE5FF" width="40%" align="center">
						<div style="padding-top: 20px">
							<h2 style="font-style: oblique;">Unauthorized Access</h2>
							Don't do that ;-)
							<br/>
							<br/>
							<br/>
							<input type="button" onclick="window.location=<spring:url value="/"/>" value="Go Back" />
						</div>
						<br/>
					</td>
				</tr>
			</table>
			<div>
				<table class="footer" align="center" style="padding-top: 10%">
					<tr>
						<td align="right">&copy; Sloth Inc.</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>

