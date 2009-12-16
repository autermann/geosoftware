function init(){
	map = new OpenLayers.Map('map', {
		maxExtent: new OpenLayers.Bounds(
			-20037508.34, -20037508.34,
			20037508.34,  20037508.34),
		numZoomLevels: 19,
		maxResolution: 156543.0399,
		units: 'm',
		projection: new OpenLayers.Projection("EPSG:900913"),
		displayProjection: new OpenLayers.Projection("EPSG:4326")
	});
	var layerMapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");

	var layerTah = new OpenLayers.Layer.OSM.Osmarender("Tiles@Home");

	map.addLayers([layerMapnik,layerTah]);

	var pois = new OpenLayers.Layer.Text( "My Points",
	{
		location:"./observations.jsp",
		projection: map.displayProjection
	});
	map.addLayer(pois);

	map.addControl(new OpenLayers.Control.LayerSwitcher());

	var lonLat = new OpenLayers.LonLat(lon, lat).transform(map.displayProjection,  map.projection);
	if (!map.getCenter()) {
		map.setCenter (lonLat, zoom);
	}
}