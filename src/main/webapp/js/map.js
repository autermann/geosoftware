var map, features, popup;

function setEditingFeature(lon, lat, description, selectedCategorieId, categories, title, action, lang, errors){
	//saving WGS84; Map needs SperMerc
	var LonLatSpherMerc = new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat));
	var LonLatWGS84 = new OpenLayers.LonLat(lon, lat);
	var form =  buildFormular(LonLatWGS84, description, selectedCategorieId, categories, title, lang, errors, action);
}
/*
 * errors.title
 * errors.description
 *
 * lang.title
 * lang.description
 * lang.categorie
 * categories[*][0] = id
 * categories[*][1] = title
 */
function buildFormular(lonlat, description, selectedCategorieId, categories, title, lang, errors, action) {
	var html ='<form id="observation" action="' + action + '" method="POST">\n\
	<table width="40%" border="0">\n<tr>\n<td>\n<table border="0" width="100%">\n\
	<tr>\n<td width="33%" align="right">' + lang.title + ':</td>\n\
	<td width="33%" align="left">\n\
	<input id="title" name="title" type="text" value="' + title + '"/>\n</td>\n\
	<td width="33%" align="right">' + errors.title + '</td>\n</tr>\n\
	<tr>\n<td width="33%" align="right">' + lang.description + ':</td>\n\
	<td width="33%" align="left">\n\
	<textarea id="description" name="description" value="' + description + '"/>\n</td>\n\
	<td width="33%" align="right">' + errors.description + '</td>\n</tr>\n\
	<tr>\n<td width="33%" align="right">' + lang.categorie + ':</td>\n\
	<td width="33%" align="left">\n<select id="categorie" name="categorie">\n';
	for (var i = 0; i < categories.length; i++) {
		html += '<option value="'+categories[i][0]+'"'
		if (categories[i][0] == selectedCategorieId)
			html += ' selected';
		html+= '>'+categories[i][1]+'</option>\n'
	}
	html+='</select>\n</td>\n</tr>\n<tr>\n<td align="center" colspan="2">\n\
	<input id="coordinate.longitude" name="coordinate.longitude" type="hidden" value="' + lonlat.lon + '"/></td>\n\
	<input id="coordinate.latitude" name="coordinate.latitude" type="hidden" value="' + lonlat.lat + '"/></td>\n\
	<input type="submit" value="Submit" />\n\
	</td>\n</tr>\n</table>\n</td>\n</tr>\n</table>\n</form>';
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
			new OpenLayers.Control.Permalink(),
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

	map.events.register('click', map, function(evt){
		if (popup != null) {
			popup.hide();
			map.removePopup(popup);
		}
		lonlat =  map.getLonLatFromViewPortPx(new OpenLayers.Pixel(evt.xy.x, evt.xy.y));
		popup = new OpenLayers.Popup.FramedCloud(
			"New Observation",
			lonlat,
			new OpenLayers.Size(300, 180),
			"Inhalt",
			null,
			true,
			function(){
				this.hide();
				map.removePopup(popup);
			});
			map.addPopup(popup);
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

function goTo(lon, lat, zoom) {map.setCenter(new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)), zoom);}
function Lon2Merc(lon) {return 20037508.34 * lon / 180;}
function Lat2Merc(lat) {var PI = 3.14159265358979323846;return 20037508.34 * (Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180)) / 180;}
