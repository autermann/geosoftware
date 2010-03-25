<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="user.edit.title"/>: #${userEditAction.id}</h2>
	<form:form modelAttribute="userEditAction">
		<table width="40%" border="1">
			<tr>
				<td>
					<form:errors cssStyle="color:red;"/>
					<table border="0" width="100%">
						<tr>
							<td width="20%" align="right"><fmt:message key="user.familyName"/>:</td>
							<td width="40%" align="left"><form:input path="newFamilyName"/></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="newFamilyName"/></td>
						</tr>
						<tr>
							<td width="20%" align="right"><fmt:message key="user.name"/>:</td>
							<td width="40%" align="left"><form:input path="newName" /></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="newName"/></td>
						</tr>
						<tr>
							<td width="20%" align="right"><fmt:message key="user.mail"/>:</td>
							<td width="40%" align="left"><form:input path="newMail" /></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="newMail"/></td>
						</tr>
						<%-- we need only enter our password editing ourself --%>
						<c:if test="${sessionScope.loginUser.id == userEditAction.id}">
							<tr>
								<td width="20%" align="right"><fmt:message key="user.actualPassword"/>:</td>
								<td width="40%" align="left"><form:password path="actualPassword" /></td>
								<td width="40%" align="right"><form:errors cssStyle="color:red;" path="actualPassword"/></td>
							</tr>
						</c:if>
						<tr>
							<td width="20%" align="right"><fmt:message key="user.newPassword"/>:</td>
							<td width="40%" align="left"><form:password path="newPassword" /></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="newPassword"/></td>
						</tr>
						<tr>
							<td width="20%" align="right"><fmt:message key="user.newPasswordRepeat"/>:</td>
							<td width="40%" align="left"><form:password path="newPasswordRepeat" /></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="newPasswordRepeat"/></td>
						</tr>
						<%-- only Admins can change the group, except for themself --%>
						<c:if test="${sessionScope.loginUser.userGroup == 'ADMIN' && sessionScope.loginUser.id != userEditAction.id}">
							<tr>
								<td width="20%" align="right"><fmt:message key="user.userGroup"/>:</td>
								<td width="40%" align="left">
									<form:select path="newGroup">
										<form:option title="Admin" value="ADMIN"/>
										<form:option title="Users" value="USER"/>
									</form:select>
								</td>
								<td width="40%" align="right"><form:errors cssStyle="color:red;" path="newGroup"/></td>
							</tr>
						</c:if>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" value="Update" />
								<input type="button" value="Cancel" onclick="window.location.href='<spring:url value="/u"></spring:url>'">
								<input type="button" value="Delete" onclick="window.location.href='<spring:url value="/u/del/${user.id}"></spring:url>'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

