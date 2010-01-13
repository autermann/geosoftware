<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.sloth.util.*"%>
<%@page import="org.sloth.core.*"%>
<%@page import="org.sloth.data.*"%>
<%
		ObservationManagement om = ObservationManagement.getInstance(session);
		UserManagement um = UserManagement.getInstance(session);
		int rights = ((User) session.getAttribute("user")).getRights();
		boolean adminActions = rights >= User.ADMIN_RIGHTS;

%>
<html>
    <head>
		<!-- TODO: Noch den ganzen Metadatenkram reinpacken... -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./styles/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="http://www.openlayers.org/api/OpenLayers.js"></script>
		<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
		<script type="text/javascript" src="./scripts/osm.js"></script>
		<script type="text/javascript" src="./scripts/GPX.js"></script>
		<title>Sloth</title>
		<script type="text/javascript">
			
			var layer_markers = new OpenLayers.Layer.Markers("Observations", {
				projection: new OpenLayers.Projection("EPSG:4326"),
				visibility: true,
				displayInLayerSwitcher: true
			});
			var map;
			function init(){
				initMap();
				map.addLayer(layer_markers);
				add(7.63095,51.96313, "<b>Test Marker</b>");
				goTo(
					 <%=Configuration.get("MAP_CENTER_LON")%>,
					 <%=Configuration.get("MAP_CENTER_LAT")%>,
					 <%=Configuration.get("MAP_CENTER_ZOOM")%>);
			}

			
		</script>
	</head>
    <body onload="init();">
		<div id="header">
			<span id="welcome">
				Willkommen zum <b>Sloth</b>-Geosoftware-Projekt
			</span>
			<span id="login">
				<b>Login</b>
			</span>
			<br>
		</div>
		<div id="list">
			<form name="search" action="">
				<input type="text" name="searchBox" value="Suchen..." id="searchBox"/>
				<input type="submit" value="Los geht's!"/>
			</form>
			<%-- Either JSP or JS... --%>
		</div>
		<div id="map"><%-- fully js-generated --%></div>
    </body> 
</html>
