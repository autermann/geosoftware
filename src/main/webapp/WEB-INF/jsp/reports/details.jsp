<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<table align="center" style="height: 100%">

	<tr style="height: 25%">
		<td></td>
		<td><h3><s:escapeBody htmlEscape="true"><fmt:message key="report.delete"/>: #<c:out value="${report.id}"/></s:escapeBody></h3> </td>
		<td></td>
	</tr>
	<tr style="height: 50%">
		<td width=10%></td>
		<td width=60%>

                <form:form modelAttribute="report">
                    <table class="management_lists">
		<tr>
			<td class="management_lists"><fmt:message key="report.id"/>:</td>
			<td class="management_lists">${report.id}</td>
		</tr>
		<tr>
			<td class="management_lists"><fmt:message key="report.description"/>:</td>
			<td class="management_lists">${report.description}</td>
		<tr/>
		<tr>
			<td class="management_lists"><fmt:message key="report.author.mail"/>:</td>
			<td class="management_lists">${report.author.mail}</td>
		<tr/>
		<tr>
			<td class="management_lists"><fmt:message key="report.observation"/>:</td>
			<td class="management_lists">${report.observation.title}</td>
		<tr/>
		<tr>
			<td class="management_lists"><fmt:message key="report.creationDate"/>:</td>
			<td class="management_lists"><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${report.creationDate}"/></td>
		<tr/>
		<tr>
			<td class="management_lists"><fmt:message key="report.lastUpdateDate"/>:</td>
			<td class="management_lists"><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${report.lastUpdateDate}"/></td>
		<tr/>

		<tr>
                    <td class="management_lists"><fmt:message key="report.processed"/>:</td>
			<td class="management_lists"><c:choose>
				<c:when test="${report.processed}"><fmt:message key="report.processed.true"/></c:when>
				<c:otherwise><fmt:message key="report.processed.false"/></c:otherwise>
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
			<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a> <fmt:message key="copyright"/>
		</td>
		<td></td>
	</tr>
</table>

                --------

<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="report.details"/> <c:out value="${report.id}"/></h2>
	<table border="1">
		<tr>
			<td><fmt:message key="report.id"/>:</td>
			<td>${report.id}</td>
		</tr>
		<tr>
			<td><fmt:message key="report.description"/>:</td>
			<td>${report.description}</td>
		<tr/>
		<tr>
			<td><fmt:message key="report.author.mail"/>:</td>
			<td>${report.author.mail}</td>
		<tr/>
		<tr>
			<td><fmt:message key="report.observation"/>:</td>
			<td>${report.observation.title}</td>
		<tr/>
		<tr>
			<td><fmt:message key="report.creationDate"/>:</td>
			<td><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${report.creationDate}"/></td>
		<tr/>
		<tr>
			<td><fmt:message key="report.lastUpdateDate"/>:</td>
			<td><fmt:formatDate pattern="dd. MM. yyyy - HH:mm" value="${report.lastUpdateDate}"/></td>
		<tr/>

		<tr>
			<td><fmt:message key="report.processed"/>:</td>
			<c:choose>
				<c:when test="${report.processed}"><fmt:message key="report.processed.true"/></c:when>
				<c:otherwise><fmt:message key="report.processed.false"/></c:otherwise>
			</c:choose>
		<tr/>
	</table>
	<form:form method="POST" >
		<input type="submit" value="Submit" />

	</form:form>
	<p><a href="<s:url value="/r"/>"><fmt:message key="nav.back"/></a></p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>