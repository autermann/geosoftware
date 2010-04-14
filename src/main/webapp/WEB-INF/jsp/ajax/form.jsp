<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<form:form modelAttribute="observation" method="POST">
	<input type="hidden" id="longitude" value="${observation.coordinate.longitude}"/>
	<input type="hidden" id="latitude" value="${observation.coordinate.latitude}"/>
	<table border="0" width="100%">
		<tr>
			<td width="20%" align="right">
				<fmt:message key="observation.title.title"/>:
			</td>
			<td width="40%" align="left">
				<form:input path="title"/>
			</td>
			<td width="40%" align="right">
				<form:errors cssStyle="color:red;" path="title"/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">
				<fmt:message key="observation.description.title"/>:
			</td>
			<td width="40%" align="left">
				<form:textarea path="description"/>
			</td>
			<td width="40%" align="right">
				<form:errors cssStyle="color:red;" path="description"/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">
				<fmt:message key="observation.categorie.title"/>:
			</td>

			<td width="40%" align="left">
				<form:select path="categorie" items="${categories}" itemLabel="title" itemValue="id" />
			</td>
			<td width="40%" align="right">
				<form:errors cssStyle="color:red;" path="categorie"/>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<%-- function will be added per js --%>
				<input type="button" id="submit" value="Submit"/>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		$("#submit").click(function(evt){
			var lon = $("#longitude").val();
			var lat = $("#latitude").val();
			var ll = WGS2Merc(new OpenLayers.LonLat(lon,lat));
			OpenLayers.Event.stop(evt);
			$.post("ajax/bubble", {
				categorie	: $("#categorie").val(),
				title		: $("#title").val(),
				description : $("#description").val(),
				lon			: lon,
				lat			: lat
			}, function(data){
				createPopup(ll, data);
			},"html")
			createPopup(ll, loading);
		});
	</script>

</form:form>