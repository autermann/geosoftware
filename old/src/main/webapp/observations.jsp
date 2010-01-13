<%@page contentType="text" pageEncoding="UTF-8"%><%@page import="java.util.LinkedList"%><%
class Obs {
	double lat, lon;
	String title, description, icon;
	int iconSizeX, iconSizeY, iconOffX, iconOffY;
	Obs(double lat, double lon, String title, String description,
		String icon, int iconSizeX, int iconSizeY, int iconOffX,
		int iconOffY) {
		this.lat = lat;
		this.lon = lon;
		this.title = title;
		this.description = description;
		this.icon = icon;
		this.iconSizeX = iconSizeX;
		this.iconOffY = iconOffY;
		this.iconSizeY = iconSizeY;
		this.iconOffX = iconOffX;
	}
}
LinkedList<Obs> obs = new LinkedList<Obs>();
obs.add(new Obs(48.9459301, 9.6075669, "Title One", "Description one<br>Second line.<br><br>(click again to close)","./img/Ol_icon_blue_example.png", 24, 24, 0, -24));
obs.add(new Obs(48.9899851, 9.5382032, "Title Two", "<span style=\"color:red;\">Description two.</span>", "./img/Ol_icon_red_example.png", 16, 16, -8, -8));
%>lat	lon	title	description	icon	iconSize	iconOffset<% 
for (Obs o : obs) {
%>
<%=o.lat%>	<%=o.lon%>	<%=o.title%>	<%=o.description%>	<%=o.icon%>	<%=o.iconSizeX%>,<%=o.iconSizeY%>	<%=o.iconOffX%>,<%=o.iconOffY%><% }%>
<%%> <%%>

