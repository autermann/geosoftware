<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div>
	<h2><fmt:message key="user.list"/></h2>
	<table width="25%" border="1">
		<tr>
			<th>
				<fmt:message key="user.id"/>
			</th>
			<th>
				<fmt:message key="user.eMail"/>
			</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>
					<a href="<spring:url value="/users/${user.id}"/>">
						${user.id}
					</a>
				</td>
				<td>
					${user.eMail}
				</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="<spring:url value="/users/new"/>">
			<fmt:message key="user.new"/>
		</a>
	</p>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
