<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link href="<spring:url value="static/css/style_dustin.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<spring:url value="http://www.openlayers.org/api/OpenLayers.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="http://www.openlayers.org/api/OpenStreetMap.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="static/js/osm.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="static/js/gpx.js" htmlEscape="true" />"></script>

		<!-- needed for Login-"Popup"-->
		<script type="text/javascript" src="<spring:url value="static/js/jquery-1.4.2.min.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="static/js/animation.js" htmlEscape="true" />"></script>
			  <title><fmt:message key="title"/></title>
	</head>

	<body>
		<div id="header">
			<span id="welcome">
				<fmt:message key="welcome"/>
			</span>
			<span id="login" class="login">
				<b><fmt:message key="login" /></b>
				<div class="events">
					<ul>
						<li>
							<span class="title"><fmt:message key="login.logintext" /></span>
							<span class="loginform">
								<form:form modelAttribute="user" method="POST">
									<table width="200">
										<tr><td><fmt:message key="user.eMail"/>:</td>
											<td><form:input path="eMail" size="50" />
												<form:errors cssStyle="color:red;" path="eMail"/>
											</td>
										</tr>
										<tr><td><fmt:message key="user.password"/>:</td>
											<td><form:password path="hashedPassword" showPassword="true" size="50" />
												<form:errors cssStyle="color:red;" path="hashedPassword"/>
											</td>
										</tr>
										<tr><td class = "login" colspan="2"><input type="submit" value="<fmt:message key="login.button" />"/></td></tr>
									</table>
								</form:form>
							</span>
						</li>
					</ul>
				</div>
			</span>
			<br />
		</div>
			<br />
