<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<table align="center" style="height: 100%">

	<tr style="height: 25%">
		<td></td>
		<td><h3><s:escapeBody htmlEscape="true"><fmt:message key="report.delete.title"/>: #<c:out value="${report.id}"/></s:escapeBody></h3> </td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>

                <form:form modelAttribute="report">
                    <table class="management_lists">
		<tr>
			<td class="management_lists"><fmt:message key="report.id.title"/>:</td>
			<td class="management_lists">${report.id}</td>
		</tr>
		<tr>
			<td class="management_lists"><fmt:message key="report.description.title"/>:</td>
			<td class="management_lists">${report.description}</td>
		<tr/>
		<tr>
			<td class="management_lists"><fmt:message key="report.author.mail.title"/>:</td>
			<td class="management_lists">${report.author.mail}</td>
		<tr/>
		<tr>
			<td class="management_lists"><fmt:message key="report.observation.title"/>:</td>
			<td class="management_lists">${report.observation.title}</td>
		<tr/>
		<tr>
			<td class="management_lists"><fmt:message key="report.creationDate.title"/>:</td>
			<td class="management_lists"><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${report.creationDate}"/></td>
		<tr/>
		<tr>
			<td class="management_lists"><fmt:message key="report.lastUpdateDate.title"/>:</td>
			<td class="management_lists"><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${report.lastUpdateDate}"/></td>
		<tr/>

		<tr>
                    <td class="management_lists"><fmt:message key="report.processed.title"/>:</td>
			<td class="management_lists"><c:choose>
				<c:when test="${report.processed}"><fmt:message key="report.processed.true.title"/></c:when>
				<c:otherwise><fmt:message key="report.processed.false.title"/></c:otherwise>
                            </c:choose>
                        </td>
		<tr/>
	</table>
                        <br/>
                        
	
		<input type="submit" value="Delete" />
                <input type="button" value="Cancel" onclick="window.location.href='<s:url value="/acc"></s:url>'">

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