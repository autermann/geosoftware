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

                         <img src="<s:url value="/static/img/logo.png" htmlEscape="true"/>" align="right" alt="Logo" ></img>

                        </td>
                        <td class="login_statement"><fmt:message key="login.statement"/><br /><br /> </td>
                        <td></td>
                </tr>
                <tr style="height: 33%">

                    <td width=33%></td>
                    <td width=33% class="loginform">


                        <form:form name="login" modelAttribute="login" method="POST">
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
