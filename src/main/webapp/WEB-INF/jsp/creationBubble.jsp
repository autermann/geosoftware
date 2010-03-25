<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<c:choose>
	<c:when test="${sessionScope.loginUser != null}">
		<form:form modelAttribute="observation" method="POST">
			<table width="40%" border="0">
				<tr>
					<td>
						<table border="0" width="100%">
							<tr>
								<td width="33%" align="right"><fmt:message key="observation.title"/>:</td>
								<td width="33%" align="left">
									<form:input path="title"/>
								</td>
								<td width="33%" align="right"><form:errors cssStyle="color:red;" path="title"/></td>
							</tr>
							<tr>
								<td width="33%" align="right"><fmt:message key="observation.description"/>:</td>
								<td width="33%" align="left">
									<form:textarea path="description"/>
								</td>
								<td width="33%" align="right"><form:errors cssStyle="color:red;" path="description"/></td>
							</tr>
							<tr>
								<td width="33%" align="right"><fmt:message key="observation.observationCategorie"/>:</td>
								<td width="33%" align="left">
									<form:select path="categorie" items="${categories}"  itemLabel="title" itemValue="id" />
								</td>
								<td width="33%" align="right"><form:errors cssStyle="color:red;" path="categorie"/></td>
							</tr>
							<tr>
								<td width="33%" align="right"><fmt:message key="observation.coordinate"/>:</td>
								<td width="33%" align="left">
												Lon:<form:input path="coordinate.longitude"/><br/>
												Lat:<form:input path="coordinate.latitude"/>
								</td>
								<td width="33%" align="right"><form:errors cssStyle="color:red;" path="coordinate"/></td>
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
	</c:when>
	<c:otherwise>
		<b>Sorry:</b><br/>
					You have to be logged in, to create Observations.<br/>
		<input type="button" onclick="window.location='<spring:url value="/login"/>'" value="<fmt:message key="login.button"/>" />
		<input type="button" onClick="window.location='<spring:url value="/signup"/>'" value="<fmt:message key="reg.button"/>" />
	</c:otherwise>
</c:choose>