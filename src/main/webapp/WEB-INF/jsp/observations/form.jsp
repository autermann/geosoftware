<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<%-- form for creating and altering users --%>
<c:choose>
	<c:when test="${observation.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<div>
	<h2><fmt:message key="observation.new"/></h2>
	<form:form modelAttribute="observation" method="${method}">
		<table width="40%" border="1">
			<tr>
				<td>
					<table border="0" width="100%">
						<tr>
							<td width="20%" align="right"><fmt:message key="observation.title"/>:</td>
							<td width="40%" align="left"><form:input path="title"/></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="title"/></td>
						</tr>
                                                <tr>
							<td width="20%" align="right"><fmt:message key="observation.description"/>:</td>
							<td width="40%" align="left"><form:textarea path="description"/></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="description"/></td>
						</tr>
                                                <tr>
							<td width="20%" align="right"><fmt:message key="observation.categorie"/>:</td>
							<td width="40%" align="left">
                                                           
                                                        </td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="categorie"/></td>
						</tr>
                                                   <tr>
							<td width="20%" align="right"><fmt:message key="observation.coordinate"/>:</td>
							<td width="40%" align="left"><form:input path="coordinate"/></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="coordinate"/></td>
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
