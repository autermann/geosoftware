<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td class="register_statement"><fmt:message key="register.statement.title"/><br /><br /> </td>
		<td></td>
	</tr>
	<tr style="height: 33%">
		<td width=33%></td>
		<td width=33% class="registerform">
			<form:form modelAttribute="user" method="POST">
				<table border="0" width="100%" class="registerform">
					<tr>
						<td width="20%" align="right"><fmt:message key="user.name.title"/>:</td>
						<td width="40%" align="left"><form:input path="name"/></td>
						<td width="40%" align="right"><form:errors cssStyle="color:red;" path="name"/></td>
					</tr>
					<tr>
						<td width="20%" align="right"><fmt:message key="user.familyName.title"/>:</td>
						<td width="40%" align="left"><form:input path="familyName"/></td>
						<td width="40%" align="right"><form:errors cssStyle="color:red;" path="familyName"/></td>
					</tr>
					<tr>
						<td width="20%" align="right"><fmt:message key="user.mail.title"/>:</td>
						<td width="40%" align="left"><form:input path="mail" /></td>
						<td width="40%" align="right"><form:errors cssStyle="color:red;" path="mail"/></td>
					</tr>
					<tr>
						<td width="20%" align="right"><fmt:message key="user.mailRepeat.title"/>:</td>
						<td width="40%" align="left"><form:input path="mailRepeat" /></td>
						<td width="40%" align="right"><form:errors cssStyle="color:red;" path="mailRepeat"/></td>
					</tr>
					<tr>
						<td width="20%" align="right"><fmt:message key="user.password.title"/>:</td>
						<td width="40%" align="left"><form:password path="password"/></td>
						<td width="40%" align="right"><form:errors cssStyle="color:red;" path="password"/></td>
					</tr>
					<tr>
						<td width="20%" align="right"><fmt:message key="user.passwordRepeat.title"/>:</td>
						<td width="40%" align="left"><form:password path="passwordRepeat"/></td>
						<td width="40%" align="right"><form:errors cssStyle="color:red;" path="passwordRepeat"/></td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input type="submit" value="Submit" />
						</td>
					</tr>
				</table>
			</form:form>
		</td>
		<td width=41%></td>
	</tr>
	<tr style="height: 33%">
		<td></td>
		<td align="center">
			<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home.title"/></a> <fmt:message key="copyright.title"/>
		</td>
		<td></td>
	</tr>
</table>



