var map;
var layer_markers;

function init(){
	OpenLayers.Lang.setCode('de');

	layer_markers = new OpenLayers.Layer.Markers("Observations", {
		projection: new OpenLayers.Projection("EPSG:4326"),
		visibility: true,
		displayInLayerSwitcher: false
	});


	map = new OpenLayers.Map('map', {
		maxExtent: new OpenLayers.Bounds(-20037508.34,-20037508.34,20037508.34,20037508.34),
		numZoomLevels: 19,
		maxResolution: 156543.0399,
		units: 'meters',
		projection: new OpenLayers.Projection("EPSG:900913"),
		displayProjection: new OpenLayers.Projection("EPSG:4326"),
		controls: [
		new OpenLayers.Control.PanZoomBar(),
		new OpenLayers.Control.ScaleLine(),
		new OpenLayers.Control.MousePosition(),
		new OpenLayers.Control.LayerSwitcher(),
		new OpenLayers.Control.Permalink(),
		new OpenLayers.Control.Navigation(),
		new OpenLayers.Control.OverviewMap()]
	});
	var layer_tah = new OpenLayers.Layer.OSM.Osmarender("Tiles@Home");
	var layer_mapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
	var layer_cycle = new OpenLayers.Layer.OSM.CycleMap("Cycle");
	map.addLayers([layer_mapnik,layer_tah,layer_cycle,layer_markers]);


	map.events.register('click', map, function(evt){
		addMarker(
			map.getLonLatFromViewPortPx(new OpenLayers.Pixel(evt.xy.x, evt.xy.y)),
			getCreationForm(),
			null,
			true);
	});
	goTo(7.63095,51.96313,12);
}

function getCreationForm() {
	return document.getElementById('observationCreationBubble').innerHTML;
}

function addMarker(ll, content, iconPath) {
	addMarker(ll, content, iconPath, false);
}

function addMarker(ll, content, iconPath, open) {
	var feature = new OpenLayers.Feature(layer_markers, ll);

	if (iconPath != null) {
		var size = new OpenLayers.Size(24,24);
		var offset = new OpenLayers.Pixel(-size.w/2, -size.h);
		var icon = new OpenLayers.Icon(iconPath,size,offset);
		feature.data.icon = icon;
	}

	feature.popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
		panMapIfOutOfView: true,
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
		} else {
			this.popup.toggle();
		}
		OpenLayers.Event.stop(evt);
	});

	layer_markers.addMarker(marker);

	if (open){
		if (feature.popup == null) {
			feature.popup = feature.createPopup(feature.closeBox);
			map.addPopup(feature.popup);
			feature.popup.show();
		} else {
			feature.popup.toggle();
		}
	}
}

function lonlat(lon, lat){
	return new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat));
}

function goTo(lon, lat, zoom) {
	map.setCenter(lonlat(lon, lat), zoom);
}

function Lon2Merc(lon) {
	return 20037508.34 * lon / 180;
}

function Lat2Merc(lat) {
	var PI = 3.14159265358979323846;
	lat = Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180);
	return 20037508.34 * lat / 180;
}
