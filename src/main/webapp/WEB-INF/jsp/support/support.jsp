<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" dir="ltr">
    <head>
        <title><s:escapeBody htmlEscape="true"><fmt:message key="tab.title"/></s:escapeBody></title>
		<link href="<s:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
		<table align="center" style="height: 100%">
			<tr style="height: 10%">
				<td width="30%">
					<a href="<s:url value="/" htmlEscape="true" />"><img src="<s:url value="/static/img/logo.png" htmlEscape="true"/>" align="right" alt="Logo" ></img></a>
				</td>
				<td width="40%"></td>
				<td width="30%"></td>
			</tr>
			<tr style="height: 80%">
				<td colspan="3">

			        <!--Support Information Table -->
						<table style="border-color: black; border-style: dashed; border-width: thin; width: 70%;">
							<tr>
								<th colspan="4">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.title"/></s:escapeBody>
								</th>
							</tr>
							<tr>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.name.fieldname"/></s:escapeBody>
								</td>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.name.value"/></s:escapeBody>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.street.fieldname"/>, <fmt:message key="support.contact.housenumber.fieldname"/></s:escapeBody>

								</td>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.street.value"/>, </s:escapeBody>
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.housenumber.value"/></s:escapeBody>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.city.fieldname"/>, <fmt:message key="support.contact.zip.fieldname"/></s:escapeBody>

								</td>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.city.value"/>, </s:escapeBody>
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.zip.value"/></s:escapeBody>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.www.fieldname"/></s:escapeBody>
								</td>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.www.value"/></s:escapeBody>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.email.fieldname"/></s:escapeBody>
								</td>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.email.value"/></s:escapeBody>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.phone.fieldname"/></s:escapeBody>
								</td>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.phone.value"/></s:escapeBody>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.fax.fieldname"/></s:escapeBody>
								</td>
								<td colspan="2">
									<s:escapeBody htmlEscape="true"><fmt:message key="support.contact.fax.value"/></s:escapeBody>
								</td>
							</tr>



						</table>
						<!--/Support Information Table-->


				</td>
			</tr>
			<tr style="height: 10%">
				<td></td>
				<td align="center">
					<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home.title"/></a> <fmt:message key="copyright.title"/>
				</td>
				<td></td>
			</tr>
		</table>
    </body>
</html>