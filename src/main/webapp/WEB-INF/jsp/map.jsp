<%--
Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth, Christoph
					Fendrich, Christian Paluschek
	This program is free software: you can redistribute it and/or modify it
	under the terms of the GNU General Public License as published by the Free
	Software Foundation, either version 3 of the License, or (at your option)
	any later version.
	This program is distributed in the hope that it will be useful, but WITHOUT
	ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
	FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
	more details.
	You should have received a copy of the GNU General Public License along with
	this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@include file="include.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" href="./css/style.css" rel="stylesheet" />
		<script type="text/javascript" src="http://www.openlayers.org/api/OpenLayers.js"></script>
		<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/osm.js"></script>
		<title>Sloth</title>
	</head>

	<body onload="init()">
        <!-- the header layer will be modified by jsp-code-->
		<div id="header"></div>
		<!-- here the list layer can be written by jsp-code-->
		<div id="list"></div>
		<!-- get openlayers layer with id 'map'-->
		<div id="map"></div>
	</body>
</html>