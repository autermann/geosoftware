<%@include file="../includes.jsp" %>
<%@include file="../header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td class="admin_statement"><s:escapeBody htmlEscape="true"><fmt:message key="user.backend.title"/></s:escapeBody><br /><br /> </td>
		<td></td>
	</tr>
	<tr style="height: 33%">
		<td width=20%></td>
		<td width=40% class="admin_backend">
			<table>
				<tr>
					<td>
						<a href="<s:url value="/u/del" />">
							<img src="<s:url value="/static/img/userdelete.png" htmlEscape="true"/>" alt="" />
						</a>
					</td>
					<td>
						<a href="<s:url value="/u/del" />">
                                                    <s:escapeBody htmlEscape="true"><fmt:message key="user.delete.account.title"/></s:escapeBody>
						</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="<s:url value="/u/edit" />">
							<img src="<s:url value="/static/img/user_mng.png" htmlEscape="true"/>" alt="" />
						</a>
					</td>
					<td>
						<a href="<s:url value="/u/edit" />">
							<fmt:message key="user.edit.title"/>
						</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="<s:url value="/o/own" />">
							<img src="<s:url value="/static/img/observation_mng.png" htmlEscape="true"/>" alt="" />
						</a>
					</td>
					<td>
						<a href="<s:url value="/o/own" />">
							<fmt:message key="observations.own.title"/>
						</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="<s:url value="/o" />">
							<img src="<s:url value="/static/img/observation_mng.png" htmlEscape="true"/>" alt="" />
						</a>
					</td>
					<td>
						<a href="<s:url value="/o" />">
							<fmt:message key="observations.all.title"/>
						</a>
					</td>
				</tr>
			</table>
		</td>
		<td width=40%></td>
	</tr>
	<tr style="height: 41%">
		<td></td>
		<td align="center">
			<a href="<s:url value="/" htmlEscape="true" />"><s:escapeBody htmlEscape="true"><fmt:message key="nav.home.title"/></s:escapeBody></a> <fmt:message key="copyright.title"/>
		</td>
		<td></td>
	</tr>
</table>

