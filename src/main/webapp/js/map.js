var map, features, popup;
var PI = 3.14159265358979323846;
var data;

function setEditingFeature(feature){
	data = feature;
}

function setPopUp2(lonlat){
	// lonlat is in SphericalMercator; don't convert
	var feature = new OpenLayers.Feature(features, lonlat);
	feature.popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
		panMapIfOutOfView: false,
		autoSize: true,
		minSize: new OpenLayers.Size(300, 180)
	});
	feature.closeBox = true;
	var wgs = Merc2WGS(lonlat);
	data.lon = wgs.lon;
	data.lat = wgs.lat;
	feature.data.popupContentHTML =  buildFormular(data);
	feature.data.overflow = "hidden";
	var marker = feature.createMarker();
	marker.events.register("click", feature, function(evt) {
		if (this.popup == null) {
			this.popup = this.createPopup(this.closeBox);
			map.addPopup(this.popup);
			this.popup.show();
		} else this.popup.toggle();
		OpenLayers.Event.stop(evt);
		// Only one Popup at the time
		if (popup != null)
			this.popup.hide();
		popup = this.popup;

	});
	features.addMarker(marker);
}


function buildFormular(data) {
	var html ='<div><form id="observation" action="' + data.action + '" method="POST">'+
	'<table width="40%" border="0"><tr><td><table border="0" width="100%">'+
	'<tr><td width="33%" align="right">' + data.lang.title + ':</td>'+
	'<td width="33%" align="left">'+
	'<input id="title" name="title" type="text" value="' + data.title + '"/></td>'+
	'<td width="33%" align="right">' + data.errors.title + '</td></tr>'+
	'<tr><td width="33%" align="right">' + data.lang.description + ':</td>'+
	'<td width="33%" align="left">'+
	'<textarea id="description" name="description">' + data.description + '</textarea></td>'+
	'<td width="33%" align="right">' + data.errors.description + '</td></tr>'+
	'<tr><td width="33%" align="right">' + data.lang.categorie + ':</td>'+
	'<td width="33%" align="left"><select id="categorie" name="categorie">';
	for (var i = 0; i < data.categories.length; i++) {
		html += '<option value="'+data.categories[i][0]+'"'
		if (data.selectedCategorieId != -1)
			if (data.categories[i][0] == data.selectedCategorieId)
				html += ' selected';
		html+= '>'+data.categories[i][1]+'</option>\n'
	}
	html+='</select></td></tr><tr><td align="center" colspan="2">'+
	'<input id="coordinate.longitude" name="coordinate.longitude" type="hidden" value="' + data.lon + '"/>'+
	'<input id="coordinate.latitude" name="coordinate.latitude" type="hidden" value="' + data.lat + '"/>'+
	'<input type="submit" value="Submit" /></td></tr></table></td></tr></table></form></div>';
	return html;
}

/* Initialize the Map */
function init(){
	OpenLayers.Lang.setCode('de');
	map = new OpenLayers.Map('map', {
		numZoomLevels: 18,
		maxResolution: 156543.0399,
		units: 'meters',
		maxExtent: new OpenLayers.Bounds(-20037508.34,-20037508.34,20037508.34,20037508.34),
		projection: new OpenLayers.Projection("EPSG:900913"),// Spherical Mercator
		displayProjection: new OpenLayers.Projection("EPSG:4326"),// WGS84
		controls: [
		new OpenLayers.Control.PanZoomBar(),
		new OpenLayers.Control.ScaleLine(),
		new OpenLayers.Control.MousePosition(),
		new OpenLayers.Control.LayerSwitcher(),
		new OpenLayers.Control.Navigation(),
		new OpenLayers.Control.OverviewMap()]
	});
	features = new OpenLayers.Layer.Markers("Observations", {
		projection: new OpenLayers.Projection("EPSG:4326"),
		visibility: true,
		displayInLayerSwitcher: false
	});
	map.addLayers([
		new OpenLayers.Layer.OSM.Mapnik("Mapnik"),
		new OpenLayers.Layer.OSM.Osmarender("Tiles@Home"),
		new OpenLayers.Layer.OSM.CycleMap("Cycle"),
		features]);
	fillMap();
	goTo(7.63095,51.96313,12);
	if (data != null)
		map.events.register('click', map, function(evt){
			setPopUp2(map.getLonLatFromViewPortPx(new OpenLayers.Pixel(evt.xy.x, evt.xy.y)));
		});
}

/* for adding existing observations */
function addMarker(lon, lat, content, iconPath) {
	var feature = new OpenLayers.Feature(features, new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)));
	if (iconPath != null)
		feature.data.icon = new OpenLayers.Icon(iconPath,new OpenLayers.Size(24,24) , new OpenLayers.Pixel(-12, -24));
	feature.popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
		panMapIfOutOfView: false,
		autoSize: true,
		minSize: new OpenLayers.Size(300, 180)
	});
	feature.closeBox = true;
	feature.data.popupContentHTML = content;
	feature.data.overflow = "hidden";
	var marker = feature.createMarker();
	marker.events.register("click", feature, function(evt) {
		if (this.popup == null) {
			this.popup = this.createPopup(this.closeBox);
			map.addPopup(this.popup);
			this.popup.show();
		} else this.popup.toggle();
		OpenLayers.Event.stop(evt);
	});
	features.addMarker(marker);
}

function goTo(lon, lat, zoom) {
	map.setCenter(new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)), zoom);
}
function Lon2Merc(lon) {
	return 20037508.34 * lon / 180;
}
function Lat2Merc(lat) {
	return 20037508.34 * (Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180)) / 180;
}

function WGS2Merc(ll){
	var lon = 20037508.34 * lon / 180;
	var lat = 20037508.34 * (Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180)) / 180;
	return new OpenLayers.LonLat(lon,lat);
}

function Merc2WGS(ll){
	var shift = 2 * PI * 6378137 / 2.0
	var lon = (ll.lon / shift) * 180.0
	var lat = 180 / PI * (2 * Math.atan( Math.exp(((ll.lat / shift) * 180.0) * PI /
		180.0)) - PI / 2.0)
	return new OpenLayers.LonLat(lon,lat)
}