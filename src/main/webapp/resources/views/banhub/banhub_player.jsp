<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 25.08.2018
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; UTF-8;" language="java" %>

<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Банхаб - Пользовательский</title>
    <link rel="stylesheet" href="<spring:url value="/resources/styles/discord-button-style.css"/>" />
    <link rel="stylesheet" href="<spring:url value="/resources/styles/banhub-style.css"/>" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <meta charset="UTF-8" />

    <jsp:useBean id="now" class="java.util.Date"/>
</head>
<body>
    <jsp:include page="/resources/templates/header.html" />

    <article class="row">
        <section class="container col-5 text-center">
            <h2>Баны</h2>
            <table class="table table-light table-striped table-bordered table-hover table-sm">
                <tbody>
                    <c:if test="${bans != null}">
                        <c:forEach var="ban" items="${bans}">
                            <tr>
                                <th colspan="7" class="table-dark">BAN</th>
                            </tr>
                            <tr>
                                <th scope="col">Ckey</th>
                                <th scope="col">Admin</th>
                                <th scope="col">Duration</th>
                                <th scope="col" style="width: 15%">Bantime</th>
                                <th scope="col" style="width: 15%">Expiration Time</th>
                                <th scope="col">Adminwho</th>
                                <th scope="col">Type</th>
                            </tr>

                            <tr>
                                <td>${ban.ckey}</td>
                                <td>${ban.adminCkey}</td>
                                <td>${ban.durationTime}</td>
                                <td>${ban.banTime.toString().substring(0, ban.banTime.toString().length() - 2)}</td>
                                <td>${ban.expirationTime.toString().substring(0, ban.expirationTime.toString().length() - 2)}</td>
                                <td>${ban.adminsWasOnline}</td>
                                <td>${ban.permamentBan ? "Вечный" : "Временный"}</td>
                            </tr>

                            <tr>
                                <th colspan="7">Reason</th>
                            </tr>
                            <tr>
                                <td colspan="7">${ban.reason}</td>
                            </tr>

                            <tr>
                                <c:if test="${ban.expirationTime.before(now)  && !ban.permamentBan}">
                                    <th colspan="7" class="table-success">Бан уже прошел!</th>
                                </c:if>

                                <c:if test="${ban.expirationTime.after(now) && !ban.permamentBan}">
                                    <th colspan="7" class="table-danger">Бан еще не прошел!</th>
                                </c:if>

                                <c:if test="${ban.permamentBan}">
                                    <th colspan="7" class="table-danger">Бан - вечный, его может снять только администрация!</th>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </section>

        <section class="container col-5 text-center">
            <h2>Джоббаны</h2>
            <table class="table table-light table-striped table-bordered table-hover table-sm">
                <tbody>
                <c:if test="${jobbans != null}">
                    <c:forEach var="ban" items="${jobbans}">
                        <tr>
                            <th colspan="8" class="table-dark">JOBBAN</th>
                        </tr>
                        <tr>
                            <th scope="col">Ckey</th>
                            <th scope="col">Admin</th>
                            <th scope="col">Job</th>
                            <th scope="col">Duration</th>
                            <th scope="col" style="width: 15%">Bantime</th>
                            <th scope="col" style="width: 15%">Expiration Time</th>
                            <th scope="col">Adminwho</th>
                            <th scope="col">Type</th>
                        </tr>

                        <tr class="ban-row">
                            <td>${ban.ckey}</td>
                            <td>${ban.adminCkey}</td>
                            <td>${ban.job}</td>
                            <td>${ban.durationTime}</td>
                            <td>${ban.banTime.toString().substring(0, ban.banTime.toString().length() - 2)}</td>
                            <td>${ban.expirationTime.toString().substring(0, ban.expirationTime.toString().length() - 2)}</td>
                            <td>${ban.adminsWasOnline}</td>
                            <td>${ban.permamentBan ? "Вечный" : "Временный"}</td>
                        </tr>

                        <tr>
                            <th colspan="8">Reason</th>
                        </tr>
                        <tr>
                            <td colspan="8">${ban.reason}</td>
                        </tr>

                        <tr>
                            <c:if test="${ban.expirationTime.before(now)  && !ban.permamentBan}">
                                <th colspan="8" class="table-success">Бан уже прошел!</th>
                            </c:if>

                            <c:if test="${ban.expirationTime.after(now) && !ban.permamentBan}">
                                <th colspan="8" class="table-danger">Бан еще не прошел!</th>
                            </c:if>

                            <c:if test="${ban.permamentBan}">
                                <th colspan="8" class="table-danger">Бан - вечный, его может снять только администрация!</th>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </section>

        <section class="container col-2">
            <form class="container">
                <div class="form-group text-center">
                    <h2>Банхаб</h2>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control form-control-lg" placeholder="Enter Player Byond Key" name="ckey" />
                </div>

                <div class="form-group row align-items-center">
                    <label class="col-8" for="jobban-checkbox">Отображать джоббаны?</label>
                    <input class=" form-controlcol-4" id="jobban-checkbox" type="checkbox" name="jobban" />
                </div>

                <div class="form-group">
                    <input type="submit" class="btn btn-primary btn-block" value="Show" name="">
                </div>
            </form>
        </section>
    </article>
</body>
</html>