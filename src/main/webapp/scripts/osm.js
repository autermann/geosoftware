function initMap(){
	OpenLayers.Lang.setCode('de');
	map = new OpenLayers.Map('map', {
		controls: [],
		maxExtent: new OpenLayers.Bounds(-20037508.34,-20037508.34,20037508.34,20037508.34),
		numZoomLevels: 19,
		maxResolution: 156543.0399,
		units: 'meters',
		projection: new OpenLayers.Projection("EPSG:900913"),
		displayProjection: new OpenLayers.Projection("EPSG:4326")
	});
	checkForPermalink();
	map.addControl(new OpenLayers.Control.PanZoomBar());
	map.addControl(new OpenLayers.Control.ScaleLine());
	map.addControl(new OpenLayers.Control.MousePosition());
	map.addControl(new OpenLayers.Control.LayerSwitcher());
	map.addControl(new OpenLayers.Control.Permalink());
	map.addControl(new OpenLayers.Control.Navigation());
	map.addControl(new OpenLayers.Control.OverviewMap());
	var layer_tah = new OpenLayers.Layer.OSM.Osmarender("Tiles@Home");
	var layer_mapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
	var layer_cycle = new OpenLayers.Layer.OSM.CycleMap("Cycle");
	map.addLayers([layer_mapnik,layer_tah,layer_cycle]);

/*
	var layer_gsat = new OpenLayers.Layer.Google("Google Sat", {
		type: G_SATELLITE_MAP,
		'sphericalMercator': true,
		numZoomLevels:19
	});
	map.addLayer(layer_gsat)
	*/

	/* reading markers from a file
	var pois = new OpenLayers.Layer.Text( "My Points",
	{
		location:"./observations.jsp",
		projection: map.displayProjection
	});
	map.addLayer(pois);
	*/

	/* reading a gps-track from a file
	var grenze = new OpenLayers.Layer.GPX("Grenze", "grenze.gpx", "#00FF00");
	map.addLayer(grenze);
	*/
}

function Lon2Merc(lon) {
	return 20037508.34 * lon / 180;
}

function Lat2Merc(lat) {
	var PI = 3.14159265358979323846;
	lat = Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180);
	return 20037508.34 * lat / 180;
}

function addMarker(layer, lon, lat, content, iconPath, iconWidth, iconHeight, iconOffset) {
	var ll = new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat));
	var feature = new OpenLayers.Feature(layer, ll);
    if (iconPath != null) {
      var size = new OpenLayers.Size(iconWidth,iconHeight);
      var offset = new OpenLayers.Pixel(-size.w/2, -size.h);
      var icon = new OpenLayers.Icon(iconPath,size,offset);
      feature.data.icon = icon;
    }
	feature.closeBox = true;
	feature.popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
		panMapIfOutOfView: true,
		autoSize: true,
		minSize: new OpenLayers.Size(300, 180)
	});

	feature.data.popupContentHTML = content;
	feature.data.overflow = "hidden";
	var marker = feature.createMarker();
	marker.events.register("mousedown", feature, function(evt) {
		if (this.popup == null) {
			this.popup = this.createPopup(this.closeBox);
			map.addPopup(this.popup);
			this.popup.show();
		} else {
			this.popup.toggle();
		}
		OpenLayers.Event.stop(evt);
	});
	layer.addMarker(marker);
}

function add(lon,lat,html) {
	addMarker(layer_markers, lon, lat, html,"./img/Ol_icon_blue_example.png",24,24,0,-24);
}

function getCycleTileURL(bounds) {
	var res = this.map.getResolution();
	var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
	var y = Math.round((this.maxExtent.top - bounds.top) / (res * this.tileSize.h));
	var z = this.map.getZoom();
	var limit = Math.pow(2, z);
	if (y < 0 || y >= limit){
		return null;
	} else {
		x = ((x % limit) + limit) % limit;
		return this.url + z + "/" + x + "/" + y + "." + this.type;
	}
}

function getParameters() {
	var url = document.URL;
	var parameterzeile = url.substr((url.indexOf("?")+1));
	var trennpos;
	var endpos;
	var paramname;
	var paramwert;
	var parameters = new Object();
	while (parameterzeile != "") {
		trennpos = parameterzeile.indexOf("=");
		endpos = parameterzeile.indexOf("&");
		if (endpos < 0) {
			endpos = 500000;
		}
		paramname = parameterzeile.substr(0,trennpos);
		paramwert = parameterzeile.substring(trennpos+1,endpos);
		parameters[paramname] = paramwert;
		parameterzeile = parameterzeile.substr(endpos+1);
	}
	return parameters;
}

function checkForPermalink() {
	var parameters = getParameters();
	if (parameters['zoom'] != null)
		zoom = parseInt(parameters['zoom']);
	if (parameters['lat'] != null)
		lat = parseFloat(parameters['lat']);
	if (parameters['lon'] != null)
		lon = parseFloat(parameters['lon']);
}

function goTo(lon, lat, zoom) {
	map.setCenter(new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)), zoom);
}
