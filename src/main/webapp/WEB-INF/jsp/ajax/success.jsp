<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<img style="margin-top:42px; margin-left:42px" alt="Loading..." src="static/img/loading.gif"/>
<script type="text/javascript">
	// add the new observation to the top of the list
	$("#observationTable").prepend($("#tableItem").html());
	// create the marker
	addMarker(${observation.coordinate.longitude}, ${observation.coordinate.latitude}, $("#bubbleContent").html(), "${observation.categorie.iconFileName}");
	// remove loading popup
	handlePopups(null);
</script>

<!-- element which will prepended to the list -->
<div id="tableItem" style="display: none;">
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