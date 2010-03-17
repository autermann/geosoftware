<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

                <link href="<spring:url value="static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css" />
		 <script type="text/javascript" src="http://openlayers.org/api/OpenLayers.js"></script> 
		 <script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
		<script type="text/javascript" src="<spring:url value="static/js/osm.js" htmlEscape="true" />"></script> 
                <%-- script type="text/javascript" src="<spring:url value="static/js/gpx.js" htmlEscape="true" />"></script --%>
                <script type="text/javascript" src="<spring:url value="static/js/jquery-1.4.2.min.js" htmlEscape="true" />"></script>
               	<%--script type="text/javascript" src="<spring:url value="static/js/animation.js" htmlEscape="true" />"></script --%>

		<title><fmt:message key="title"/></title>
	</head>

        <body onload="init();">