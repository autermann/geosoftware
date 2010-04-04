<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div>
	<h2><fmt:message key="categorie.new"/></h2>
	<form:form modelAttribute="categorie" method="POST">
		<table width="40%" border="1">
			<tr>
				<td>
					<table border="0" width="100%">
						<tr>
							<td width="20%" align="right"><fmt:message key="categorie.title"/>:</td>
							<td width="40%" align="left"><form:input path="title"/></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="title"/></td>
						</tr>
						<tr>
							<td width="20%" align="right"><fmt:message key="categorie.description"/>:</td>
							<td width="40%" align="left"><form:textarea path="description" /></td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="description"/></td>
						</tr>
						<tr>
						<tr>
							<td width="20%" align="right"><fmt:message key="categorie.iconFileName"/>:</td>
							<td>
								<form:hidden path="iconFileName" />
								<img class="categorieImage" src="<s:url value="/static/img/flag_black.png" />" alt="" />
								<img class="categorieImage" src="<s:url value="/static/img/flag_blue.png" />" alt="" />
								<img class="categorieImage" src="<s:url value="/static/img/flag_green.png" />" alt="" />
								<img class="categorieImage" src="<s:url value="/static/img/flag_red.png"/>" alt="" />
								<img class="categorieImage" src="<s:url value="/static/img/trash.png"/>" alt="" />
								<img class="categorieImage" src="<s:url value="/static/img/attention.png"/>" alt="" />
							</td>
							<td width="40%" align="right"><form:errors cssStyle="color:red;" path="iconFileName"/></td>
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
<script type="text/javascript">
	$('img.categorieImage').live('click', function(){
		$('img.categorieImage').removeClass('selected');
		$('#iconFileName').val($(this).attr('src'));
		$(this).addClass('selected');
	});
</script>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
