<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td><h3><fmt:message key="observation.edit.title"/>: #${observation.id}</h3> </td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>
			<form:form modelAttribute="observation" method="POST">
				<table class="management_lists">
					<tr>
						<td>
							<table border="0" width="100%">
								<tr>
									<td width="20%" align="right" class="management_lists"><fmt:message key="observation.title.title"/>:</td>
									<td width="40%" align="left" class="management_lists"><form:input path="title"/></td>
									<td width="40%" align="right" class="management_lists"><form:errors cssStyle="color:red;" path="title"/></td>
								</tr>
								<tr>
									<td width="20%" align="right" class="management_lists"><fmt:message key="observation.description.title"/>:</td>
									<td width="40%" align="left" class="management_lists"><form:textarea path="description"/></td>
									<td width="40%" align="right" class="management_lists"><form:errors cssStyle="color:red;" path="description"/></td>
								</tr>
								<tr>
									<td width="20%" align="right" class="management_lists"><fmt:message key="observation.categorie.title"/>:</td>

									<td width="40%" align="left" class="management_lists">
										<form:select path="categorie">
											<form:options items="${categories}" itemLabel="title" itemValue="id"/>
										</form:select>
									</td>
									<td width="40%" align="right" class="management_lists"><form:errors cssStyle="color:red;" path="categorie"/></td>
								</tr>
								<tr>
									<td width="20%" align="right" class="management_lists"><fmt:message key="observation.coordinate.title"/>:</td>
									<td width="40%" align="left" class="management_lists">
										<small>
											Lon:<form:input path="coordinate.longitude"/><form:errors cssStyle="color:red;" path="coordinate.longitude"/><br/>
											Lat:<form:input path="coordinate.latitude"/><form:errors cssStyle="color:red;" path="coordinate.latitude"/>
										</small>
									</td>
									<td width="40%" align="right" class="management_lists"><form:errors cssStyle="color:red;" path="coordinate"/></td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="submit" value="Submit" />
										<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/o"></s:url>'">
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