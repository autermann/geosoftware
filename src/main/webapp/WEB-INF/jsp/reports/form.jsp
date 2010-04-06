<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td><h3><fmt:message key="report.details.title"/></h3></td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>
			<form:form modelAttribute="report" method="POST">
				<table class="management_lists">
					<tr>
						<td class="management_lists">
							<fmt:message key="report.description.title"/>:
						</td>
					</tr>
					<tr>
						<td class="management_lists">
							<form:errors cssStyle="color:red;" path="description"/>
						</td>
					</tr>
					<tr>
						<td class="management_lists">
							<form:textarea  path="description" rows="10" cssStyle="width:300px;height:15em" />
						</td>
					</tr>
					<c:if test="${sessionScope.LOGIN.userGroup == 'ADMIN'}">
						<tr>
							<td>
								<fmt:message key="report.processed.title"/>
								<form:checkbox path="processed"/>
							</td>
						</tr>
					</c:if>
					<tr>
						<td align="center" >
							<input type="submit" value="Submit" />
							<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/acc"></s:url>'">
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




