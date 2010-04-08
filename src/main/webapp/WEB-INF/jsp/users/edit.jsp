<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td><h3><fmt:message key="user.edit.title"/>: ${userEditAction.id} </h3> </td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>
			<form:form modelAttribute="userEditAction">
				<table class="management_lists">
					<tr>
						<td>
							<form:errors cssStyle="color:red;"/>
							<table class="management_lists">
								<tr>
									<td  class="management_lists" width="20%" align="right"><fmt:message key="user.familyName.title"/>:</td>
									<td  class="management_lists" width="40%" align="left"><form:input path="newFamilyName"/></td>
									<td  class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="newFamilyName"/></td>
								</tr>
								<tr>
									<td  class="management_lists" width="20%" align="right"><fmt:message key="user.name.title"/>:</td>
									<td  class="management_lists" width="40%" align="left"><form:input path="newName" /></td>
									<td  class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="newName"/></td>
								</tr>
								<tr>
									<td  class="management_lists" width="20%" align="right"><fmt:message key="user.mail.title"/>:</td>
									<td  class="management_lists" width="40%" align="left"><form:input path="newMail" /></td>
									<td  class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="newMail"/></td>
								</tr>
								<%-- we need only enter our password editing ourself --%>
								<c:if test="${sessionScope.LOGIN.id == userEditAction.id}">
									<tr>
										<td class="management_lists" width="20%" align="right"><fmt:message key="user.actualPassword.title"/>:</td>
										<td class="management_lists" width="40%" align="left"><form:password path="actualPassword" /></td>
										<td class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="actualPassword"/></td>
									</tr>
								</c:if>
								<tr>
									<td class="management_lists" width="20%" align="right"><fmt:message key="user.newPassword.title"/>:</td>
									<td class="management_lists" width="40%" align="left"><form:password path="newPassword" /></td>
									<td class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="newPassword"/></td>
								</tr>
								<tr>
									<td class="management_lists"width="20%" align="right"><fmt:message key="user.newPasswordRepeat.title"/>:</td>
									<td class="management_lists" width="40%" align="left"><form:password path="newPasswordRepeat" /></td>
									<td class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="newPasswordRepeat"/></td>
								</tr>
								<%-- only Admins can change the group, except for themself --%>
								<c:if test="${sessionScope.LOGIN.userGroup == 'ADMIN' && sessionScope.LOGIN.id != userEditAction.id}">
									<tr>
										<td class="management_lists" width="20%" align="right"><fmt:message key="user.userGroup.title"/>:</td>
										<td class="management_lists" width="40%" align="left">
											<form:select path="newGroup">
												<form:option title="Admin" value="ADMIN"/>
												<form:option title="Users" value="USER"/>
											</form:select>
										</td>
										<td class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="newGroup"/></td>
									</tr>
								</c:if>
								<tr>
									<td align="center" colspan="2">
										<input type="submit" value="Update" />
										<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/acc"></s:url>'">
										<input type="button" value="Delete" onclick="window.location.href='<s:url value="/u/del/${userEditAction.id}"></s:url>'">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form:form>
		</td>
		<td width=30%></td>
	</tr>
	<tr style="height: 25%">
		<td></td>
		<td align="center">
			<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home.title"/></a> <fmt:message key="copyright.title"/>
		</td>
		<td></td>
	</tr>
</table>