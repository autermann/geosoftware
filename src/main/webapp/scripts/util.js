function jumpTo(map, lon, lat, zoom) {
	map.setCenter(new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)), zoom);
	return false;
}

function Lon2Merc(lon) {
	return 20037508.34 * lon / 180;
}

function Lat2Merc(lat) {
	var PI = 3.14159265358979323846;
	lat = Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180);
	return 20037508.34 * lat / 180;
}