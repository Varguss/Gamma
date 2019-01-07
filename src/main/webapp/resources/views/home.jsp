<html>
<%@ page contentType="text/html; UTF-8;" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title>Gamma-Eris Stations</title>

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
                <li><a href="<c:url value="/" />" class="lang" key="home" id="active">Главная</a></li>
                <li>
                    <div class="dropdown">
                        <a class="lang dropbtn" key="bans">Баны</a>
                        <div class="dropdown-content">
                            <a href="<c:url value="/banhub/gamma/player"/>">Поиск по игроку</a>
                            <a href="<c:url value="/banhub/gamma/admin"/>">Поиск по администратору</a>
                        </div>
                    </div>
                </li>
                    <%--<a href="<c:url value="/banhub/player" />" class="lang" key="bans">Баны</a></li>--%>
                <li><a href="<c:url value="/crew" />" class="lang" key="monitor">Состав</a></li>
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
            <jsp:useBean id="posts" scope="request" type="java.util.List<ru.gamma_station.domain.Post>"/>
            <jsp:useBean id="random" scope="request" type="java.util.Random" />
            <c:forEach items="${posts}" var="post">
                <div class="article article-margin announce">
                    <div class="user-wrapper">
                        <div class="avatar">
                            <img class="placeholder"
                                 src="http://tinygraphs.com/squares/${random.nextDouble()}?theme=seascape">
                        </div>
                        <h2>${post.author}</h2>
                        <br>
                        <h3>Опубликовано: <br>${post.publishedDate.toString().substring(0, post.publishedDate.toString().length() - 2)}</h3>
                        <h3>ID: ${post.id}</h3>
                    </div>

                    <div class="announce-content">
                            ${post.content}
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="flex-sidebar-right"></div>
    </div>
</div>

<script type="text/javascript" src="<spring:url value="/resources/scripts/index_bundle.js?69a2bfd76050f2d39948" />"></script>
</body>
</html>