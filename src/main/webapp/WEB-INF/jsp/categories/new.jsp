<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<table align="center" style="height: 100%">
	<tr style="height: 25%">
		<td></td>
		<td><h3><fmt:message key="categorie.new.title"/></h3></td>
		<td></td>
	</tr>
	<tr style="height: 33%">
		<td width=20%></td>
		<td width=40%>
			<form:form modelAttribute="categorie" method="POST">
				<table class="management_lists">
					<tr>
						<td class="management_lists" width="20%" align="right"><fmt:message key="categorie.title.title"/>:</td>
						<td class="management_lists" width="40%" align="left"><form:input path="title"/></td>
						<td class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="title"/></td>
					</tr>
					<tr>
						<td class="management_lists" width="20%" align="right"><fmt:message key="categorie.description.title"/>:</td>
						<td class="management_lists" width="40%" align="left"><form:textarea path="description" /></td>
						<td class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="description"/></td>
					</tr>
					<tr>
					<tr>
						<td class="management_lists" width="20%" align="right"><fmt:message key="categorie.iconFileName.title"/>:</td>
						<td>
							<form:hidden path="iconFileName" />
							<img class="categorieImage" src="<s:url value="/static/img/flag_black.png" />" alt="" />
							<img class="categorieImage" src="<s:url value="/static/img/flag_blue.png" />" alt="" />
							<img class="categorieImage" src="<s:url value="/static/img/flag_green.png" />" alt="" />
							<img class="categorieImage" src="<s:url value="/static/img/flag_red.png"/>" alt="" />
							<img class="categorieImage" src="<s:url value="/static/img/trash.png"/>" alt="" />
							<img class="categorieImage" src="<s:url value="/static/img/attention.png"/>" alt="" />
						</td>
						<td class="management_lists" width="40%" align="right"><form:errors cssStyle="color:red;" path="iconFileName"/></td>
					</tr>
					<tr>
						<td class="management_lists" align="center" colspan="2">
							<input type="submit" value="Submit" />
							<input type="button" value="Cancel" onclick="window.location.href='<s:url value="/c"></s:url>'">
						</td>
					</tr>
				</table>
			</form:form>
		</td>
		<td width=40%></td>
	</tr>
	<tr style="height: 41%">
		<td></td>
		<td align="center">
			<a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home.title"/></a> <fmt:message key="copyright.title"/>
		</td>
		<td></td>
	</tr>
</table>
<script type="text/javascript">
	$('img.categorieImage').click(function(){
		$('img.categorieImage').removeClass('selected');
		$('#iconFileName').val($(this).attr('src'));
		$(this).addClass('selected');
	});
</script>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
