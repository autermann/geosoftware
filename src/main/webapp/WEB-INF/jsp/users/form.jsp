<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<%-- form for creating and altering users --%>
<c:choose>
	<c:when test="${user.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<div>
	<h2><fmt:message key="user.new"/></h2>
	<form:form modelAttribute="user" method="${method}">
		<table width="40%" border="1">
			<tr>
				<td>
					<table border="0" width="100%">
						<tr>
							<td width="20%" align="right"><fmt:message key="user.familyName"/>:</td>
							<td width="40%" align="left"><form:input path="familyName"/></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="familyName"/></td>
						</tr>
						<tr>
							<td width="20%" align="right"><fmt:message key="user.name"/>:</td>
							<td width="40%" align="left"><form:input path="name" /></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="name"/></td>
						</tr>
						<tr>
							<td width="20%" align="right"><fmt:message key="user.mail"/>:</td>
							<td width="40%" align="left"><form:input path="mail" /></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="mail"/></td>
						</tr>
						<tr>
							<td width="20%" align="right"><fmt:message key="user.password"/>:</td>
							<td width="40%" align="left"><form:password path="password" showPassword="true"/></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="password"/></td>
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
