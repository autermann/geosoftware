<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


 <table align="center" style="height: 100%">

                <tr style="height: 25%">
                        <td></td>
                        <td><h3><fmt:message key="report.list"/></h3><br /> </td>
                        <td></td>
                </tr>
                <tr style="height: 33%">

                    <td width=20%></td>
                    <td width=40%>

                                 <table class="management_lists">
                                <tr>
                                        <th class="management_lists"><fmt:message key="report.id"/></th>
                                        <th class="management_lists"><fmt:message key="report.author"/></th>
                                        <th class="management_lists"><fmt:message key="report.observation"/></th>
                                        <th class="management_lists"><fmt:message key="report.description"/></th>
                                        <th class="management_lists"><fmt:message key="report.processed"/></th>
                                        <th class="management_lists"><fmt:message key="report.actions"/></th>
                                </tr>
                                <c:forEach var="report" items="${reports}">
                                        <tr>
                                                <td class="management_lists">${report.id}</td>
                                                <td class="management_lists"><a href="<s:url value="/u/${report.author.id}"/>">${report.author.id}</a></td>
                                                <td class="management_lists"><a href="<s:url value="/o/${report.observation.id}"/>">${report.observation.id}</a></td>
                                                <td class="management_lists">${report.description}</td>
                                                <td class="management_lists">
                                                        <c:choose>
                                                                <c:when test="${report.processed}"><fmt:message key="report.processed.true"/></c:when>
                                                                <c:otherwise><fmt:message key="report.processed.false"/></c:otherwise>
                                                        </c:choose>
                                                </td>
                                                <td class="management_lists">
                                                        <a href="<s:url value="/r/edit/${report.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="report.edit"/></s:escapeBody>"</a>
														<a href="<s:url value="/r/del/${report.id}"/>"><img src="<s:url value="/static/img/delete.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="report.delete"/></s:escapeBody>"></a>
                                                </td>
                                        </tr>
                                </c:forEach>
                        </table>



                    </td>
                    <td width=40%></td>


                </tr>
                <tr style="height: 41%">
                    <td></td>
                    <td align="center">

                        <a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a> <fmt:message key="copyright"/>
                    </td>
                    <td></td>
                </tr>
            </table>


                        -----------------

                        <%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="report.list"/></h2>
	<table width="50%" border="1">
		<tr>
			<th><fmt:message key="report.id"/></th>
			<th><fmt:message key="report.author"/></th>
			<th><fmt:message key="report.observation"/></th>
			<th><fmt:message key="report.description"/></th>
			<th><fmt:message key="report.processed"/></th>
			<th><fmt:message key="report.actions"/></th>
		</tr>
		<c:forEach var="report" items="${reports}">
			<tr>
				<td>${report.id}</td>
				<td><a href="<s:url value="/u/${report.author.id}"/>">${report.author.id}</a></td>
				<td><a href="<s:url value="/o/${report.observation.id}"/>">${report.observation.id}</a></td>
				<td>${report.description}</td>
				<td>
					<c:choose>
						<c:when test="${report.processed}"><fmt:message key="report.processed.true"/></c:when>
						<c:otherwise><fmt:message key="report.processed.false"/></c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="<s:url value="/r/edit/${report.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<fmt:message key="report.edit"/>"></a>
					<a href="<s:url value="/r/del/${report.id}"/>"><img src="<s:url value="/static/img/delete.png" htmlEscape="true" />" alt="<fmt:message key="report.delete"/>"></a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
