<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.sloth.util.Configuration"%>
<%@page import="org.sloth.util.Log"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./styles/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="./scripts/osm.js"></script>
		<script src="http://www.openlayers.org/api/OpenLayers.js"></script>
		<script src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
        <title>Sloth</title>
		<script type="text/javascript">
			var map;
			var lat = 48.94;
			var lon = 9.55;
			var zoom = 12;
		</script>



	</head>
    <body onload="init();">
		<div id="header">
			<span id="welcome">Willkommen zum <b>Sloth</b>-Geosoftware-Projekt</span>
			<span id="login"><b>Login</b></span><br>
		</div>
		<div id="list">
			<ul type="disc">
				<li>Hier</li>
				<li>kommt</li>
				<li>die</li>
				<li>Listenansicht</li>
				<li>hin</li>
			</ul>
		</div>
		<div id="map"></div>
    </body> 
</html>
