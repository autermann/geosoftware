<%@include file="includes.jsp" %>
<%@page contentType="text/javascript" pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.Map.Entry" %>
<%@page import="java.util.Collection" %>
<%@page import="org.sloth.model.Categorie" %>
<%@page import="org.sloth.model.Observation" %>
<%		Map<Categorie, Collection<Observation>> map = (Map<Categorie, Collection<Observation>>) jspContext.findAttribute("map");
		if (map == null) {%>
alert("No Attribute map found");

<%		} else {
			for (Entry<Categorie, Collection<Observation>> entry : map.entrySet()) {
				String icon = entry.getKey().getIconFileName();
				for (Observation o : entry.getValue()) {%>
addMarker(layer_markers, <%=o.getCoordinate().getLongitude()%>,  <%=o.getCoordinate().getLatitude()%>,<%=o.getTitle()%>, <%=icon%>, 48, 48, false);
<%				}
			}
		}%>