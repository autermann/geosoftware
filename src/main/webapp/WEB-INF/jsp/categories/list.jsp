<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


 <table align="center" style="height: 100%">

                <tr style="height: 25%">
                        <td></td>
                        <td><h3><fmt:message key="categorie.list"/></h3><br />
                            <p align="right"><a href="<s:url value="/c/new"/>">
                                <img src="<s:url value="/static/img/add.png" htmlEscape="true"/>" height="24" alt="" />
                                    <fmt:message key="categorie.new"/>
                            </a></p>
                        </td>
                        <td></td>
                </tr>
                <tr style="height: 33%">

                    <td width=20%></td>
                    <td width=40%>

                             <table class="management_lists">
                                    <tr>
                                            <th class="management_lists"><fmt:message key="categorie.id"/></th>
                                            <th class="management_lists"><fmt:message key="categorie.title"/></th>
                                            <th class="management_lists"><fmt:message key="categorie.description"/></th>
                                            <th class="management_lists"><fmt:message key="categorie.iconFileName" /></th>
                                            <th class="management_lists"><fmt:message key="categorie.action" /></th>
                                    </tr>
                                    <c:forEach var="categorie" items="${categories}">
                                            <tr>
                                                    <td class="management_lists">${categorie.id}</td>
                                                    <td class="management_lists">${categorie.title}</td>
                                                    <td class="management_lists">${categorie.description}</td>
                                                    <td class="management_lists">${categorie.iconFileName}</td>
                                                    <td class="management_lists">
                                                        <a href="<s:url value="/c/edit/${categorie.id}"/>"><img src="<s:url value="/static/img/edit.png" htmlEscape="true" />" alt="<fmt:message key="categorie.edit" />"></a>
                                                            <a href="<s:url value="/c/del/${categorie.id}"/>"><img src="<s:url value="/static/img/delete.png" htmlEscape="true" />" alt="<fmt:message key="categorie.delete" />"></a>
                                                    </td>
                                            </tr>
                                    </c:forEach>
                        </table>



                    </td>
                    <td width=40%></td>


                </tr>
                <tr style="height: 41%">
                    <td></td>
                    <td align="center">

                        <a href="<s:url value="/" htmlEscape="true" />"><fmt:message key="nav.home"/></a> <fmt:message key="copyright"/>
                    </td>
                    <td></td>
                </tr>
            </table>


                                