<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@page import="org.sloth.model.*"%>
<%@page import="org.sloth.service.*"%>
<%@page import="org.sloth.service.impl.*"%>
<%@page import="org.sloth.persistence.*"%>
<%@page import="org.sloth.persistence.impl.*"%>
<%
		// Beispiele wie man den http get / post auslesen kann
		// http get: wird in der URL angezeigt (suche)
		// http post: wird nicht in der url angezeigt (login)
	String email = request.getParameter("loginform_email");
	String passwd = request.getParameter("loginform_password");
	
	if (((email != null) & (passwd != null))){
		if ((!email.isEmpty()) | (!passwd.isEmpty()) ){


			// führe login operation durch.



		}
	}

	String searchBoxValue = request.getParameter("searchBox");
	String searchBoxDefaultString = "Suchen...";
		if ((searchBoxValue != null) && (!searchBoxValue.isEmpty()) && (!searchBoxValue.equalsIgnoreCase(searchBoxDefaultString))){


			// Rufe die Suche auf.
			searchBoxDefaultString = "danke für's suchen!";

			}

%>

<html>
    <head>
		<!-- TODO: Noch den ganzen Metadatenkram reinpacken... -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<spring:url value="static/css/style_dustin.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<spring:url value="http://www.openlayers.org/api/OpenLayers.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="http://www.openlayers.org/api/OpenStreetMap.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="static/js/osm.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="static/js/gpx.js" htmlEscape="true" />"></script>

		<!-- needed for Login-"Popup"-->
		<script type="text/javascript" src="<spring:url value="static/js/jquery-1.4.2.min.js" htmlEscape="true" />"></script>
		<script type="text/javascript" src="<spring:url value="static/js/animation.js" htmlEscape="true" />"></script>

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
				add(7.63095,51.96313, "<b>Test Marker</b>");
			}


		</script>
	</head>
    <body onload="init();">
		<div id="header">
			<span id="welcome">
				Willkommen zum <b>Sloth</b>-Geosoftware-Projekt
			</span>
			<span id="login" class="login">
				<b>Login</b>		
					<div class="events">
						<ul>
						<li>
							<span class="title">Please Log In</span>
							<span class="loginform">
								<form name="loginform" action="" method="post">
									<table>
										<tr><td>eMail:</td>
											<td><input type="text" name="loginform_email"/></td>
										</tr>
										<tr><td>password:</td>
											<td><input type="password" name="loginform_password"/></td>
										</tr>
										<tr><td colspan="0"><input type="submit" name="loginform_loginbutton" value="submit" onclick ="value='thanks'"></tr>
									</table>
								</form>
							</span>
						</li>
						</ul>
					</div>
			</span>
			<br>
		</div>
		<div id="list">
			<form name="search" action="">
				<input type="text" name="searchBox" value="<%=searchBoxDefaultString %>" id="searchBox" onfocus="searchBox.value=''" onblur="searchBox.value='<%=searchBoxDefaultString %>'"/>
				<input type="submit" value="Los geht's!"/>
			</form>
			<%-- Either JSP or JS... --%>
		</div>
		<div id="map"><%-- fully js-generated --%></div>
    </body> 
</html>
