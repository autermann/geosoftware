<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<img style="margin-top:42px; margin-left:42px" alt="Loading..." src="static/img/loading.gif"/>
<script type="text/javascript">
	$("div.observationList:first").before($("#tableItem").html());
	addMarker(${observation.coordinate.longitude}, ${observation.coordinate.latitude}, $("#bubbleContent").html(), "${observation.categorie.iconFileName}");
	handlePopups(null);
</script>

<%--
	element which will prepended to the list
	need to create a new table... don't know why but
	only a new row doesn't shows up appropiate
--%>
<div id="tableItem" style="display: none;">
	<div id="results" class="observationlist">
		<table class="observationlist">
			<tr class="entry">
				<td class="observationlist_title">
					<img src="${observation.categorie.iconFileName}" width="20px" height="20px"
						 alt="<s:escapeBody htmlEscape="true">${observation.categorie.title}</s:escapeBody>"/>
					<b class="obsTitle">
						<s:escapeBody>${observation.title}</s:escapeBody>
					</b>
					<a href="#" onclick="goTo(${observation.coordinate.longitude}, ${observation.coordinate.latitude}, 14);">
						<img src="<s:url value="/static/img/show.png" htmlEscape="true" />" align="right" height="20px"
							 alt="<s:escapeBody htmlEscape="true"><fmt:message key="observation.viewInMap.title"/></s:escapeBody>"/>
					</a>
					<a href="<s:url value="/r/o/${observation.id}/new"/>">
						<img src="<s:url value="/static/img/report.png" htmlEscape="true" />" align="right" height="20px"
							 alt="<s:escapeBody htmlEscape="true"><fmt:message key="observation.report.title"/></s:escapeBody>" />
					</a>
					<a href="<s:url value="/o/edit/${observation.id}"/>">
						<img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" align="right" height="20px"
							 alt="<s:escapeBody htmlEscape="true"><fmt:message key="observation.edit.title"/></s:escapeBody>" />
					</a>
					<p class="observationlist_description">
						<s:escapeBody htmlEscape="true" javaScriptEscape="false" >
							${observation.description}
						</s:escapeBody>
					</p>
				</td>
			</tr>
		</table>
	</div>
</div>

<!-- content of the popup -->
<div id="bubbleContent" style="display: none;">
	<b>
		<s:escapeBody htmlEscape="true">
			${observation.title}
		</s:escapeBody>
	</b>
	<br/><br/>
	<s:escapeBody htmlEscape="true">
		${observation.description}
	</s:escapeBody>
	<br/><br/>
	Koordinaten:
	<br/>
	<small>
		Lon: ${observation.coordinate.longitude} | Lat: ${observation.coordinate.latitude}
	</small>
</div>