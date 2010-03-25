<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2>
		<fmt:message key="report.details"/>
	</h2>
	<form:form modelAttribute="report" method="POST">
		<table border="1">
			<tr>
				<td>
					<fmt:message key="report.description"/>:
				</td>
			</tr>
			<tr>
				<td>
					<form:errors cssStyle="color:red;" path="description"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:textarea  path="description"/>
				</td>
			</tr>
			<c:if test="${sessionScope.loginUser.userGroup == 'ADMIN'}">
				<tr>
					<td>
						<fmt:message key="report.processed"/>
						<form:checkbox path="processed"/>
					</td>
				</tr>
			</c:if>
			<tr>
				<td align="center" >
					<input type="submit" value="Submit" />
				</td>
			</tr>
		</table>
	</form:form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
