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
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE xhtml PUBLIC "-//W3C//DTD XHTML 1.1//EN"
	"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" href="./css/style.css" rel="stylesheet" />
		<script type="text/javascript" src="http://www.openlayers.org/api/OpenLayers.js"></script>
		<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/osm.js"></script>
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
				var s = '<form name="create" action="createObservation">'
				s += '<input type="text" name="title" value="Titel" />';
				s += '<label for="category">Kategorie: </label><select name="category">';
				s += '<option value="ar">Argentinien</option>';
				s += '<option value="br">Brasilien</option>';
				s += '<option value="bg">Bulgarien</option>';
				s += '<option value="cl">Chile</option>';
				s += '<option value="dk">D&auml;nemark</option>';
				s += '</select><br/>';
				s += '<textarea name="description"  cols="50" rows="10"></textarea><br/>';
				s += '<input type="button" value="Abbrechen" />';
				s += '<input type="submit" value="OK" />';
				s += '</form>';
				add(7.63095,51.96313, s);
				goTo(7.63095,51.96313,12);
			}
		</script>
	</head>
	<body onload="init()">
		<div id="header"></div>
		<div id="list"></div>
		<div id="map"></div>
	</body>
</html>