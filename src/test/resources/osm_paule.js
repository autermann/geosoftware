var map, wfs;

var DeleteFeature = OpenLayers.Class(OpenLayers.Control, {
	initialize: function(layer, options) {
		OpenLayers.Control.prototype.initialize.apply(this, [options]);
		this.layer = layer;
		this.handler = new OpenLayers.Handler.Feature(
			this, layer, {
				click: this.clickFeature
			}
			);
	},

	clickFeature: function(feature) {
		// if feature doesn't have a fid, destroy it
		if(feature.fid == undefined) {
			this.layer.destroyFeatures([feature]);
		} else {
			feature.state = OpenLayers.State.DELETE;
			this.layer.events.triggerEvent("afterfeaturemodified",
			{
				feature: feature
			});
			feature.renderIntent = "select";
			this.layer.drawFeature(feature);
		}
	}
	,
	setMap: function(map) {
		this.handler.setMap(map);
		OpenLayers.Control.prototype.setMap.apply(this, arguments);
	},
	CLASS_NAME: "OpenLayers.Control.DeleteFeature"
});

var layer_markers = new OpenLayers.Layer.Markers("Observations", {
	projection: new OpenLayers.Projection("EPSG:4326"),
	visibility: true,
	displayInLayerSwitcher: true
});

function checkForPermalink() {
	var parameters = getParameters(),zoom,lon,lat;
	if (parameters['zoom'] != null)zoom = parseInt(parameters['zoom']);
	if (parameters['lat'] != null) lat = parseFloat(parameters['lat']);
	if (parameters['lon'] != null) lon = parseFloat(parameters['lon']);
	goTo(lon, lat, zoom);
}

function init(){
	OpenLayers.Lang.setCode('de');
	// Create new Openlayers-Layer with ID 'map'
	map = new OpenLayers.Map('map', {
		controls: [],
		maxExtent: new OpenLayers.Bounds(-20037508.34,-20037508.34,20037508.34,20037508.34),
		numZoomLevels: 19,
		maxResolution: 156543.0399,
		units: 'meters',
		projection: new OpenLayers.Projection("EPSG:900913"),
		displayProjection: new OpenLayers.Projection("EPSG:4326")
	});
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

	// die styles werden hier definiert!!
	var styles = new OpenLayers.StyleMap({
		"default": new OpenLayers.Style(null, {
			rules: [
			new OpenLayers.Rule({
				symbolizer: {
					"Point": {
						pointRadius: 8,
						graphicName: "circle",
						fillColor: "#ffff00",
						fillOpacity: 0.25,
						strokeWidth: 2,
						strokeOpacity: 1,
						strokeColor: "#ff0000"
					}
				}
			})
			]
		}),
		"select": new OpenLayers.Style({
			strokeColor: "#0000ff",
			strokeWidth: 4
		}),
		"temporary": new OpenLayers.Style(null, {
			rules: [
			new OpenLayers.Rule({
				symbolizer: {
					"Point": {
						pointRadius: 6,
						graphicName: "circle",
						fillColor: "#ff0000",
						fillOpacity: 0.25,
						strokeWidth: 2,
						strokeOpacity: 1,
						strokeColor: "#ff0000"
					}

				}
			})
			]
		})
	});

	// editierbare features
	var saveStrategy = new OpenLayers.Strategy.Save();
	wfs = new OpenLayers.Layer.Vector("Editable Features", {
		strategies: [new OpenLayers.Strategy.BBOX(), saveStrategy],
		projection: new OpenLayers.Projection("EPSG:4326"),
		styleMap: styles,
		protocol: new OpenLayers.Protocol.WFS({
			version: "1.1.0",
			srsName: "EPSG:4326",
			url: "http://demo.opengeo.org/geoserver/wfs",
			featureNS :  "http://opengeo.org",
			featureType: "roads",
			geometryName: "the_geom",
			schema: "http://demo.opengeo.org/geoserver/wfs/DescribeFeatureType?version=1.1.0&typename=og:roads"
		})
	});
	// wfs eingebunden

	map.addLayers([layer_mapnik,layer_tah,layer_cycle,wfs,layer_markers]);
	map.events.register('click', map, function(evt){
		addMarker(
			layer_markers,
			map.getLonLatFromViewPortPx(new OpenLayers.Pixel(evt.xy.x, evt.xy.y)),
			getCreationForm(),
			null,
			20,
			34,
			false);
	});

	goTo(7.63095,51.96313,12);
	checkForPermalink();

	// Tools hinzufügen oder per marker machen
	var panel = new OpenLayers.Control.Panel(
	{
		displayClass: '../css/style/Beispieltoolbar'
	}
	);
	var draw = new OpenLayers.Control.DrawFeature(
		wfs, OpenLayers.Handler.Point,
		{
			title: "Draw Feature",
			displayClass: "../css/style/BeispieltoolbarDrawFeaturePoint",
			handlerOptions: {
				multi: true
			}
		}
		);
	modify = new OpenLayers.Control.ModifyFeature(
		wfs, {
			displayClass: "../css/style/BeispieltoolbarModifyFeature"
		}
		);
	var del = new DeleteFeature(wfs, {
		title: "Delete Feature"
	});

	var save = new OpenLayers.Control.Button({
		title: "Save Changes",
		trigger: function() {
			if(modify.feature) {
				modify.selectControl.unselectAll();
			}
			saveStrategy.save();
		},
		displayClass: "../css/style/BeispieltoolbarSaveFeatures"
	});

	panel.addControls([
		new OpenLayers.Control.Navigation(),
		save, del, modify, draw
		]);

	panel.defaultControl = panel.controls[0];
	map.addControl(panel);
}

function getCreationForm() {
	return '<form name="create" action="createObservation">'
	+ '<input type="text" name="title" value="Titel" />'
	+ '<label for="category">Kategorie: </label><select name="category">'
	+ '<option value="ar">Abfall</option>'
	+ '<option value="br">Vandalismus</option>'
	+ '<option value="cl">was weiß ich</option>'
	+ '<option value="dk">wtf?</option>'
	+ '</select><br/>'
	+ '<textarea name="description" cols="50" rows="10"></textarea><br/>'
	+ '<input type="button" value="Abbrechen" />'
	+ '<input type="submit" value="OK" />'
	+ '</form>';
}


function Lon2Merc(lon) {
	return 20037508.34 * lon / 180;
}

function Lat2Merc(lat) {
	var PI = 3.14159265358979323846;
	lat = Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180);
	return 20037508.34 * lat / 180;
}

function addMarker(layer, lon, lat, content, iconPath, iconWidth, iconHeight,open) {
	addMarker(layer, new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)) , content, iconPath, iconWidth, iconHeight,open)
}

function addMarker(layer, ll, content, iconPath, iconWidth, iconHeight,open) {
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
	// layer.addMarker(marker);
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

function goTo(lon, lat, zoom) {
	map.setCenter(new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat)), zoom);
}
