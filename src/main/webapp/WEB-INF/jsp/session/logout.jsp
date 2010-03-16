<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<%
session.invalidate();
response.sendRedirect("/sloth/");
%>



<%@ include file="/WEB-INF/jsp/footer.jsp" %>



