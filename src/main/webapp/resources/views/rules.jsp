<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.08.2018
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; UTF-8;" language="java" %>

<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>"Правила сервера"</title>
    <link href="<spring:url value="/resources/styles/header_style.css"/>" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <meta charset="UTF-8" />
</head>
<body>
    <jsp:include page="/resources/templates/header.html" />

    <div class="rules-container">
        <c:forEach var="rule" items="${rules}">
            <div class="rule-container">
                <span class="rule-name">${rule.name}</span>

                <div class="rule-description">
                    ${rule.description}
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
