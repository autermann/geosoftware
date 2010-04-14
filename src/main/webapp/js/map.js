var PI = 3.14159265358979323846;
var loading = '<img style="margin-top:42px; margin-left:42px" alt="Loading..." src="static/img/loading.gif"/>';
var popup;

function init() {
	OpenLayers.Lang.setCode('de');
	map = new OpenLayers.Map('mapArea', {
		numZoomLevels : 18,
		maxResolution : 156543.0399,
		units : 'meters',
		maxExtent : new OpenLayers.Bounds(-20037508.34, -20037508.34, 20037508.34,  20037508.34),
		projection : new OpenLayers.Projection("EPSG:900913"),
		displayProjection : new OpenLayers.Projection("EPSG:4326"),
		controls : [
		new OpenLayers.Control.PanZoomBar(),
		new	OpenLayers.Control.ScaleLine(),
		new OpenLayers.Control.MousePosition(),
		new OpenLayers.Control.LayerSwitcher(),
		new OpenLayers.Control.Navigation(),
		new OpenLayers.Control.OverviewMap()
		]
	});
	layer = new OpenLayers.Layer.Markers("Observations", {
		projection : new OpenLayers.Projection("EPSG:4326"),
		visibility : true,
		displayInLayerSwitcher : false
	});
	map.addLayers([
		new OpenLayers.Layer.OSM.Mapnik("Mapnik"),
		new OpenLayers.Layer.OSM.Osmarender("Tiles@Home"),
		new OpenLayers.Layer.OSM.CycleMap("Cycle"),
		layer]);
	goTo(7.63095, 51.96313, 12);
	map.events.register('click', map, function(evt) {
		clickCallback(map.getLonLatFromViewPortPx(new OpenLayers.Pixel(evt.xy.x, evt.xy.y)));
	});
}

function createPopup(ll, content){
	var popup = new OpenLayers.Popup.FramedCloud(
		"Login", ll, new OpenLayers.Size(100, 100),
		content, null, true, function() {
			handlePopups(null);
		});
	handlePopups(popup);
}

function clickCallback(ll){
	createPopup(ll, loading);
	var wgs = Merc2WGS(ll);
	$.get("ajax/bubble", {
		lon : wgs.lon,
		lat : wgs.lat
	}, function(data) {
		createPopup(ll, data);
		$("#submit").click(function(evt){
			OpenLayers.Event.stop(evt);
			$.post("ajax/bubble", {
				categorie	: $("#categorie").val(),
				title		: $("#title").val(),
				description : $("#description").val(),
				lon			: $("#longitude").val(),
				lat			: $("#latitude").val()
			}, function(data){
				createPopup(ll, data);
			},"html")
			createPopup(ll, loading);
		});
	}, "html");
}


function addMarker(lon, lat, content, iconPath) {
	var feature = new OpenLayers.Feature(layer,
		new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)));
	feature.data.icon = new OpenLayers.Icon(iconPath, new OpenLayers.Size(24, 24), new OpenLayers.Pixel(-12, -24));
	var marker = feature.createMarker();
	marker.events.register("click", feature, function(evt) {
		if (feature.popup == null){
			feature.popup = new OpenLayers.Popup.FramedCloud(
				"obs", feature.lonlat, new OpenLayers.Size(100, 100),
				content, feature.data.icon, true, function() {
					handlePopups(null);
				});
			handlePopups(feature.popup);
			OpenLayers.Event.stop(evt);
		}
		else {
			feature.popup = null;
			handlePopups(null);
		}
	});
	layer.addMarker(marker);
}

function goTo(lon, lat, zoom) {
	map.setCenter(new OpenLayers.LonLat(Lon2Merc(lon),Lat2Merc(lat)),zoom);
}

function Lon2Merc(lon) {
	return 20037508.34*lon/180;
}

function Lat2Merc(lat) {
	return 20037508.34*(Math.log(Math.tan((90+lat)*PI/360))/(PI/180))/180;
}

function WGS2Merc(ll) {
	return new OpenLayers.LonLat(
		20037508.34*ll.lon/180,
		20037508.34*(Math.log(Math.tan((90+ll.lat)*PI/360))/(PI/180))/180);
}

function Merc2WGS(ll) {
	var shift = 2*PI*6378137/2.0;
	return new OpenLayers.LonLat(
		(ll.lon/shift)*180.0,
		180/PI*(2*Math.atan(Math.exp(((ll.lat/shift)*180.0)*PI/180.0))-PI/2.0));
}

function handlePopups(newPopup){
	if (popup != null) {
		map.removePopup(popup);
		popup.hide();
		popup = null;
	}
	if (newPopup != null){
		popup = newPopup;
		map.addPopup(popup);
	}
}

