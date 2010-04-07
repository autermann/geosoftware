var map, layer;
var PI = 3.14159265358979323846;
var data;
// feature actual editing
var editingFeature;
// marker of editing feature
var editingMarker;
// actual shown popup
var popup;

function setEditingFeature(feature) {
	data = feature;
}

function createLoginInformation(lonlat) {

	var popup = new OpenLayers.Popup.FramedCloud("Login", lonlat,
			new OpenLayers.Size(300, 180),
			"<p>You have to be logged in to create Observations.<p>", null,
			true, function() {
				map.removePopup(this);
			});
	handlePopUps(popup);
	map.addPopup(popup);

}

function createFormular(lonlat) {
	if (editingMarker != null) {
		popup.hide();
		layer.removeMarker(editingMarker);
	}
	// lonlat is in SphericalMercator; don't convert
	editingFeature = new OpenLayers.Feature(layer, lonlat);
	editingFeature.popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud,
			{
				panMapIfOutOfView : false,
				autoSize : true,
				minSize : new OpenLayers.Size(300, 180)
			});
	editingFeature.closeBox = true;
	var wgs = Merc2WGS(lonlat);
	data.lon = wgs.lon;
	data.lat = wgs.lat;
	editingFeature.data.popupContentHTML = buildFormularContent(data);
	editingFeature.data.overflow = "hidden";

	editingMarker = editingFeature.createMarker();
	editingMarker.events.register("click", editingFeature, function(evt) {
		if (this.popup == null) {
			this.popup = this.createPopup(false);
			map.addPopup(this.popup);
			this.popup.show();
		} else
			this.popup.toggle();
		handlePopUps(this.popup);
		OpenLayers.Event.stop(evt);
	});
	if (editingFeature.popup == null) {
		editingFeature.popup = editingFeature.createPopup(false);
		map.addPopup(editingFeature.popup);

	}
	handlePopUps(editingFeature.popup);
	editingFeature.popup.show();
	layer.addMarker(editingMarker);
}

function handlePopUps(newPopup) {
	if (popup != null) {
		popup.hide();
		if (data != null)
			cancel();
	}
	popup = newPopup;
}

function cancel() {
	if (editingMarker != null) {
		popup.hide();
		data.description = "";
		data.title = "";
		data.selectedCategorieId = "";
		data.errors.categorie = "";
		data.errors.observation = "";
		data.errors.title = "";
		layer.removeMarker(editingMarker);
	}
}

function buildFormularContent(data) {
	var html = '<div><form id="observation" action="';
	html += data.action + '" method="POST"><table width="40%" border="0"><tr><td><table border="0" width="100%"><tr><td width="33%" align="right">';
	html += data.lang.title + ':</td><td width="33%" align="left"><input id="title" name="title" type="text" value="';
	html += data.title + '"/></td><td width="33%" align="right">';
	html += data.errors.title + '</td></tr><tr><td width="33%" align="right">';
	html += data.lang.description + ':</td><td width="33%" align="left"><textarea id="description" name="description">';
	html += data.description + '</textarea></td><td width="33%" align="right">';
	html += data.errors.description + '</td></tr><tr><td width="33%" align="right">';
	html += data.lang.categorie + ':</td><td width="33%" align="left"><select id="categorie" name="categorie">';
	for ( var i = 0; i < data.categories.length; i++) {
		html += '<option value="' + data.categories[i][0] + '"';
		if (data.categories[i][0] == data.selectedCategorieId)
			html += ' selected';
		html += '>' + data.categories[i][1] + '</option>';
	}
	html += '</select></td><td>';
	html += data.errors.categorie + '</td></tr><tr><td align="center" colspan="2"><input id="coordinate.longitude" name="coordinate.longitude" type="hidden" value="';
	html += data.lon + '"/><input id="coordinate.latitude" name="coordinate.latitude" type="hidden" value="';
	html += data.lat + '"/><input type="submit" value="Submit" /><input type="button" value="Cancel" onclick="cancel();" /></td></tr></table></td></tr></table></form></div>';
	return html;
}

/* Initialize the Map */
function init() {
	OpenLayers.Lang.setCode('de');
	map = new OpenLayers.Map('mapArea', {
		numZoomLevels : 18,
		maxResolution : 156543.0399,
		units : 'meters',
		maxExtent : new OpenLayers.Bounds(-20037508.34, -20037508.34,
				20037508.34, 20037508.34),
		projection : new OpenLayers.Projection("EPSG:900913"),// Spherical
																// Mercator
		displayProjection : new OpenLayers.Projection("EPSG:4326"),// WGS84
		controls : [ new OpenLayers.Control.PanZoomBar(),
				new OpenLayers.Control.ScaleLine(),
				new OpenLayers.Control.MousePosition(),
				new OpenLayers.Control.LayerSwitcher(),
				new OpenLayers.Control.Navigation(),
				new OpenLayers.Control.OverviewMap() ]
	});
	layer = new OpenLayers.Layer.Markers("Observations", {
		projection : new OpenLayers.Projection("EPSG:4326"),
		visibility : true,
		displayInLayerSwitcher : false
	});
	map.addLayers( [ new OpenLayers.Layer.OSM.Mapnik("Mapnik"),
			new OpenLayers.Layer.OSM.Osmarender("Tiles@Home"),
			new OpenLayers.Layer.OSM.CycleMap("Cycle"), layer ]);
	fillMap();
	goTo(7.63095, 51.96313, 12);

	map.events.register('click', map, function(evt) {
		var ll = map.getLonLatFromViewPortPx(new OpenLayers.Pixel(evt.xy.x,
				evt.xy.y));
		if (data != null)
			createFormular(ll);
		else {
			createLoginInformation(ll);
		}
	});

	if (data != null)
		if (data.show)
			createFormular(new OpenLayers.LonLat(Lon2Merc(data.lon),
					Lat2Merc(data.lat)));
}

/* for adding existing observations */
function addMarker(lon, lat, content, iconPath) {
	var feature = new OpenLayers.Feature(layer, new OpenLayers.LonLat(
			Lon2Merc(lon), Lat2Merc(lat)));
	if (iconPath != null)
		feature.data.icon = new OpenLayers.Icon(iconPath, new OpenLayers.Size(
				24, 24), new OpenLayers.Pixel(-12, -24));
	feature.popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
		panMapIfOutOfView : false,
		autoSize : true,
		minSize : new OpenLayers.Size(300, 180)
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
		} else
			this.popup.toggle();
		handlePopUps(this.popup);
		OpenLayers.Event.stop(evt);
	});
	layer.addMarker(marker);
}

function goTo(lon, lat, zoom) {
	map.setCenter(new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)), zoom);
}
function Lon2Merc(lon) {
	return 20037508.34 * lon / 180;
}
function Lat2Merc(lat) {
	return 20037508.34 * (Math.log(Math.tan((90 + lat) * PI / 360)) / (PI / 180)) / 180;
}

function WGS2Merc(ll) {
	var lon = 20037508.34 * lon / 180;
	var lat = 20037508.34 * (Math.log(Math.tan((90 + lat) * PI / 360)) / (PI / 180)) / 180;
	return new OpenLayers.LonLat(lon, lat);
}

function Merc2WGS(ll) {
	var shift = 2 * PI * 6378137 / 2.0
	var lon = (ll.lon / shift) * 180.0
	var lat = 180
			/ PI
			* (2 * Math.atan(Math.exp(((ll.lat / shift) * 180.0) * PI / 180.0)) - PI / 2.0)
	return new OpenLayers.LonLat(lon, lat)
}