<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><fmt:message key="title"/></title>
		<link href="<s:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
	</head>
	<body>

		<table align="center" style="height: 100%">

			<tr style="height: 10%">
				<td>

					<a href="<s:url value="/" htmlEscape="true" />"><img src="<s:url value="/static/img/logo.png" htmlEscape="true"/>" align="right" alt="Logo" ></img></a>

				</td>
				<td></td>
				<td></td>
			</tr>
			<tr style="height: 80%">

				<td width=15%></td>
				<td width=70% class="error">


                                    <center>
                                        <h2 style="font-style: oblique;">Internal Error</h2>
                                    </center>
                                    <small>

                                        <%
                                                try {
                                                        // The Servlet spec guarantees this attribute will be available
                                                        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");

                                                        if (exception != null)
                                                                if (exception instanceof ServletException) {
                                                                        // It's a ServletException: we should extract the root cause
                                                                        ServletException sex = (ServletException) exception;
                                                                        Throwable rootCause = sex.getRootCause();
                                                                        if (rootCause == null)
                                                                                rootCause = sex;
                                                                        out.println("** Root cause is: " + rootCause.getMessage());
                                                                        rootCause.printStackTrace(new java.io.PrintWriter(out));
                                                                } else
                                                                        // It's not a ServletException, so we'll just show it
                                                                        exception.printStackTrace(new java.io.PrintWriter(out));
                                                        else
                                                                out.println("No error information available");

                                                        // Display cookies
                                                        out.println("\nCookies:\n");
                                                        Cookie[] cookies = request.getCookies();
                                                        if (cookies != null)
                                                                for (int i = 0; i < cookies.length; i++) {
                                                                        out.println(cookies[i].getName() + "=[" + cookies[i].getValue() + "]");
                                                                }

                                                } catch (Exception ex) {
                                                        ex.printStackTrace(new java.io.PrintWriter(out));
                                                }
                                            %>

                                            </small><br/><br/>
                                            <center><input type="button" onclick="window.location=<s:url value="/"/>" value="Go Back" /></center>
                                   

				</td>
				<td width=15%></td>


			</tr>
			<tr style="height: 10%">
				<td></td>
				<td align="center">

					<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a> <fmt:message key="copyright"/>
				</td>
				<td></td>
			</tr>
		</table>





	</body>
</html>