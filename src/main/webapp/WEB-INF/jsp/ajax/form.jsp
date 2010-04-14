<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<form:form modelAttribute="observation" method="POST">
	<input type="hidden" id="longitude" value="${observation.coordinate.longitude}"/>
	<input type="hidden" id="latitude" value="${observation.coordinate.latitude}"/>
	<table border="0" width="100%">
		<tr>
			<td width="20%" align="right">
				<fmt:message key="observation.title.title"/>:
			</td>
			<td width="40%" align="left">
				<form:input path="title"/>
			</td>
			<td width="40%" align="right">
				<form:errors cssStyle="color:red;" path="title"/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">
				<fmt:message key="observation.description.title"/>:
			</td>
			<td width="40%" align="left">
				<form:textarea path="description"/>
			</td>
			<td width="40%" align="right">
				<form:errors cssStyle="color:red;" path="description"/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">
				<fmt:message key="observation.categorie.title"/>:
			</td>

			<td width="40%" align="left">
				<form:select path="categorie" items="${categories}" itemLabel="title" itemValue="id" />
			</td>
			<td width="40%" align="right">
				<form:errors cssStyle="color:red;" path="categorie"/>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<input type="button" id="submit" value="Submit" />
			</td>
		</tr>
	</table>
</form:form>