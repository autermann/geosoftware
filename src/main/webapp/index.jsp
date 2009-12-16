<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.sloth.util.Configuration"%>
<%@page import="org.sloth.util.Log"%>
<html>
    <head>
		<!-- TODO: Noch den ganzen Metadatenkram reinpacken... -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./styles/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="./scripts/osm.js"></script>
		<script type="text/javascript" src="./scripts/util.js"></script>
		<script type="text/javascript" src="http://www.openlayers.org/api/OpenLayers.js"></script>
		<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
        <title>Sloth</title>
		<script type="text/javascript">
			var map;
			var lat = <%=Configuration.get("MAP_CENTER_LAT")%>;
			var lon = <%=Configuration.get("MAP_CENTER_LON")%>;
			var zoom = <%=Configuration.get("MAP_CENTER_ZOOM")%>;
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
