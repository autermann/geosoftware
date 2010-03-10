<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<%-- form for creating and altering users --%>
<c:choose>
	<c:when test="${user.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<div>
	<h2><fmt:message key="createNewUser"/></h2>
	<form:form modelAttribute="user" method="${method}">
		<table width="25%" border="1">
			<tr>
				<td>
					<table border="0" width="100%">
						<tr>
							<td width="33%" align="right">Lastname: <form:errors cssStyle="color:red;" path="familyName"/></td>
							<td width="66%" align="left"><form:input path="familyName"/></td>
						</tr>
						<tr>
							<td width="33%" align="right">Name: <form:errors cssStyle="color:red;" path="name"/></td>
							<td width="66%" align="left"><form:input path="name" /></td>
						</tr>
						<tr>
							<td width="33%" align="right">E-Mail: <form:errors cssStyle="color:red;" path="eMail"/></td>
							<td width="66%" align="left"><form:input path="eMail" /></td>
						</tr>
						<tr>
							<td width="33%" align="right">Password: <form:errors cssStyle="color:red;" path="hashedPassword"/></td>
							<td width="66%" align="left"><form:input path="hashedPassword" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" value="Submit" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
