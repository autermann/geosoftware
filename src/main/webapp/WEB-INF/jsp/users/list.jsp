<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


 <table align="center" style="height: 100%">

                <tr style="height: 25%">
                        <td></td>
                        <td><h3><fmt:message key="user.list.title"/></h3><br /><br /> </td>
                        <td></td>
                </tr>
                <tr style="height: 33%">

                    <td width=20%></td>
                    <td width=40%>

                            <table width="25%" class="management_lists">
                                <tr>
                                        <th class="management_lists"><fmt:message key="user.id.title"/></th>
                                        <th class="management_lists"><fmt:message key="user.mail.title"/></th>
                                        <th class="management_lists"><fmt:message key="user.edit.title"/></th>
                                </tr>
                                        <c:forEach var="user" items="${users}">
                                        <tr>
                                                <td  class="management_lists"><a href="<s:url value="/u/${user.id}"/>">${user.id}</a></td>
                                                <td  class="management_lists">${user.mail}</td>
                                                <td  class="management_lists"><a href="<s:url value="/u/edit/${user.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<s:escapeBody htmlEscape="true"><fmt:message key="user.edit.title"/></s:escapeBody>"></a></td>
                                        </tr>
                                </c:forEach>
                            </table>



                    </td>
                    <td width=40%></td>


                </tr>
                <tr style="height: 41%">
                    <td></td>
                    <td align="center">

                        <a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a> <fmt:message key="copyright.title"/>
                    </td>
                    <td></td>
                </tr>
            </table>

              