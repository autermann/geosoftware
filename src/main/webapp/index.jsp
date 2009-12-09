<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.sloth.util.Configuration"%>
<%@page import="org.sloth.util.Log"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./styles/style.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
		<div id="header">
			header
		</div>
		<p id="greeting">
			Das wird einmal unser Web-Frontend ;-)<br>
			<%=
				Configuration.get("LOG_FILE")
			%>
		</p>
    </body> 
</html>
