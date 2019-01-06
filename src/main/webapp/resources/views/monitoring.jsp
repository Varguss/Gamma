<%@ page contentType="text/html; UTF-8;" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Gamma - Bans</title>

    <meta name="description" content="News and Updates for Eris Gamma." style/>
    <meta name="keywords" content="ss13, space station 13"/>
    <meta name="author" content="Valkyrie (https://github.com/r3valkyrie)"/>
    <meta http-equiv="Content-type" content="text/html" charset="UTF-8"/>
    <meta charset="UTF-8"/>

    <link rel="stylesheet" type="text/css" href="https://cdnjs.com/libraries/10up-sanitize.css"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/dropdown.css"/>"/>
</head>

<body>
<div class="flex-layout">
    <div class="layer1"></div>
    <div class="layer2"></div>

    <header>
        <div class="nav">
            <ul>
                <li><a href="<c:url value="/" />" class="lang" key="home">Главная</a></li>
                <li>
                    <div class="dropdown">
                        <a class="lang dropbtn" key="bans">Баны</a>
                        <div class="dropdown-content">
                            <a href="<c:url value="/banhub/gamma/player"/>">Режим "Игрок"</a>
                            <a href="<c:url value="/banhub/gamma/admin"/>">Режим "Админ"</a>
                        </div>
                    </div>
                </li>
                <li><a href="<c:url value="/crew" />" class="lang" id="active" key="monitor">Состав</a></li>
                <%--<li><a href="<c:url value="resources/views/wikipedia.html" />" class="lang" key="wiki">Вики</a></li>--%>
            </ul>
        </div>
    </header>

    <div class="flex-body">
        <div class="flex-sidebar"></div>

        <jsp:useBean id="gamma" scope="request" type="ru.gamma_station.domain.ServerStatus"/>
        <div class="server-wrapper">
            <div class="server-status">
                <div class="server-icon ${gamma.online ? "" : "offline"}">
                </div>
                <div class="server-info">
                    <h3>Gamma Station</h3>
                    <br>
                    <h3>PLAYERS: ${gamma.online ? gamma.fullPlayersCount : 0}</h3>
                    <%--<h3>MODE: ${gamma.online ? gamma.mode : "OFFLINE"}</h3>--%>
                    <h3>MAP: ${gamma.map != null ? gamma.map : "OFFLINE"}</h3>
                    <%--<h3>STATUS: <c:choose><c:when test="${gamma.online}">ONLINE</c:when><c:otherwise>OFFLINE</c:otherwise></c:choose></h3>--%>
                    <h3>DESCRIPTION: Сервер классической станции со своим билдом.</h3>
                </div>
                <div class="server-play">
                    <a href="https://discord.gg/V92ZHwY"><h3>DISCORD</h3></a>
                </div>
                <div class="server-play">
                    <a href="byond://5.9.12.156:2507"><h3>PLAY NOW</h3></a>
                </div>
            </div>

            <jsp:useBean id="eris" scope="request" type="ru.gamma_station.domain.ServerStatus"/>
            <div class="server-status">
                <div class="server-icon ${eris.online ? "" : "offline"}">
                </div>
                <div class="server-info">
                    <h3>Eris Station</h3>
                    <br>
                    <h3>PLAYERS: ${eris.online ? eris.fullPlayersCount : 0}</h3>
                    <%--<h3>STATUS: OFFLINE</h3>--%>
                    <%--<h3>MODE: OFFLINE</h3>--%>
                    <h3>MAP: ${eris.map != null ? eris.map : "OFFLINE"}</h3>
                    <h3>DESC: Сервер с уникальной графикой, картой и лором, стремящийся
                        создать нечто новое на основе нашей старой игры.</h3>
                </div>
                <div class="server-play">
                    <a href="https://discord.gg/CTW9A3Y"><h3>DISCORD</h3></a>
                </div>
                <div class="server-play">
                    <a href="byond://5.9.12.156:2511"><h3>PLAY NOW</h3></a>
                </div>
            </div>
        </div>

        <div class="flex-content">
            <div class="article" style="width:100%">
                <table class="ban-table" style="color:white;" border="3px" cellpadding="3px">
                    <tr>
                        <th>Director</th>
                    </tr>
                    <tr>
                        <c:forEach items="${crewMembers}" var="crewMember">
                            <c:if test="${crewMember.roles.contains(Director)}">
                                <td>${crewMember.name}</td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </table>
                <!-- Cut here -->
                <div class="lesser-manifest-container">
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Game Master</th>
                        </tr>
                        <tr>
                            <c:forEach items="${crewMembers}" var="crewMember">
                                <c:if test="${crewMember.roles.contains(GM)}">
                                    <td>${crewMember.name}</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Game Admin</th>
                        </tr>
                        <tr>
                            <c:forEach items="${crewMembers}" var="crewMember">
                                <c:if test="${crewMember.roles.contains(GA)}">
                                    <td>${crewMember.name}</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Admin</th>
                        </tr>
                        <tr>
                            <c:forEach items="${crewMembers}" var="crewMember">
                                <c:if test="${crewMember.roles.contains(Admin)}">
                                    <td>${crewMember.name}</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Admin Candidate</th>
                        </tr>
                        <tr>
                            <c:forEach items="${crewMembers}" var="crewMember">
                                <c:if test="${crewMember.roles.contains(Admin_Candidate)}">
                                    <td>${crewMember.name}</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Wiki</th>
                        </tr>
                        <c:forEach items="${crewMembers}" var="crewMember">
                            <c:if test="${crewMember.roles.contains(Wiki)}">
                                <td>${crewMember.name}</td>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
                <!-- And cut here -->
                <div class="lesser-manifest-container">
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Coder</th>
                        </tr>
                        <tr>
                            <c:forEach items="${crewMembers}" var="crewMember">
                                <c:if test="${crewMember.roles.contains(Coder)}">
                                    <td>${crewMember.name}</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Mapper</th>
                        </tr>
                        <tr>
                            <c:forEach items="${crewMembers}" var="crewMember">
                                <c:if test="${crewMember.roles.contains(Mapper)}">
                                    <td>${crewMember.name}</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Mentor</th>
                        </tr>
                        <tr>
                            <c:forEach items="${crewMembers}" var="crewMember">
                                <c:if test="${crewMember.roles.contains(Mentor)}">
                                    <td>${crewMember.name}</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Spriter</th>
                        </tr>
                        <c:forEach items="${crewMembers}" var="crewMember">
                            <c:if test="${crewMember.roles.contains(Spriter)}">
                                <td>${crewMember.name}</td>
                            </c:if>
                        </c:forEach>
                    </table>
                    <table class="ban-table lesser-manifest-table" style="color:white;" border="3px" cellpadding="3px">
                        <tr>
                            <th>Xenovisor</th>
                        </tr>
                        <tr>
                            <c:forEach items="${crewMembers}" var="crewMember">
                                <c:if test="${crewMember.roles.contains(Xenovisor)}">
                                    <td>${crewMember.name}</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="flex-sidebar-right"></div>
    </div>
</div>

<script type="text/javascript"
        src="<spring:url value="/resources/scripts/index_bundle.js?69a2bfd76050f2d39948" />"></script>
</body>
</html>