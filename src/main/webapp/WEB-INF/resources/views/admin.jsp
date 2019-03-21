<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 09.09.2018
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Админская</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <meta charset="UTF-8" />
</head>
<body>
    <form style="position: fixed; right: 1%; top: 1%;" action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Logout" />
    </form>

    <section class="staff-container">
        <h2>Управление администраторами сайта</h2>

        <h3>Текущие администраторы:</h3>

        <table border="6px" style="text-align: center;">
            <tr><th>NAME</th><th>RIGHTS</th></tr>
            <c:forEach items="${admins}" var="admin">
                <tr><td>${admin.username}</td><td><c:forEach items="${admin.authorities}" var="authority">${authority.authority} </c:forEach></td></tr>
            </c:forEach>
        </table>

        <h3>Добавить администратора</h3>
        <form class="add-admin" action="<c:url value="/admin/addAdmin"/>" method="POST">
            <input type="text" name="name" placeholder="Имя администратора" required maxlength="60" /> <br />
            <input type="password" name="password" placeholder="Пароль администратора" required maxlength="60" /> <br />

            <label>Управление администраторами сайта   <input type="checkbox" name="staff_management" /></label> <br />
            <label>Управление главной страницей   <input type="checkbox" name="posts_management" /></label> <br />
            <label>Управление правилами сервера   <input type="checkbox" name="rules_management" /></label> <br />
            <label>Управление составом сервера   <input type="checkbox" name="crew_management" /></label> <br />
            <label>Доступ к дистанционной выдаче банов (в разработке)   <input type="checkbox" name="ban_management" /></label> <br />

            <input type="submit" value="Добавить!"/>
        </form>
        <c:if test="${not empty param.addAdmin}">
            <c:choose>
               <c:when test="${param.addAdmin.equals('successful')}">
                   <h4 style="color: green; font-style: italic;">Успешное добавление администратора!</h4>
               </c:when>
               <c:otherwise>
                   <h4 style="color: red; font-style: italic;">Администратор уже существует!</h4>
               </c:otherwise>
            </c:choose>
        </c:if>

        <h3>Удалить администратора</h3>
        <form class="remove-admin" action="<c:url value="/admin/removeAdmin"/>" method="POST">
            <input type="text" name="name" placeholder="Имя администратора" required /> <br />
            <input type="submit" value="Удалить!">
        </form>
        <c:if test="${not empty param.removeAdmin}">
            <h4 style="color: green; font-style: italic;">Успешное удаление администратора!</h4>
        </c:if>

        <h3>Расширить доступ администратору</h3>
        <form class="add-authority" action="<c:url value="/admin/addAuthority"/>" method="POST">
            <input type="text" name="name" placeholder="Имя администратора" required /> <br />

            <label>Управление администраторами сайта   <input type="radio" value="staff_management" name="authority" /></label> <br />
            <label>Управление главной страницей   <input type="radio" value="posts_management" name="authority" /></label> <br />
            <label>Управление правилами сервера   <input type="radio" value="rules_management" name="authority" /></label> <br />
            <label>Управление составом сервера   <input type="radio" value="crew_management" name="authority" /></label> <br />
            <label>Доступ к дистанционной выдаче банов (в разработке)   <input type="radio" value="ban_management" name="authority" /></label> <br />

            <input type="submit" value="Расширить!">
        </form>
        <c:if test="${param.addAuthority.equals('successful')}">
            <h4 style="color: green; font-style: italic;">Успешно добавлено!</h4>
        </c:if>

        <h3>Понизить доступ администратору</h3>
        <form class="remove-authority" action="<c:url value="/admin/removeAuthority"/>" method="POST">
            <input type="text" name="name" placeholder="Имя администратора" required /> <br />

            <label>Управление администраторами сайта   <input type="radio" value="staff_management" name="authority" /></label> <br />
            <label>Управление главной страницей   <input type="radio" value="posts_management" name="authority" /></label> <br />
            <label>Управление правилами сервера   <input type="radio" value="rules_management" name="authority" /></label> <br />
            <label>Управление составом сервера   <input type="radio" value="crew_management" name="authority" /></label> <br />
            <label>Доступ к дистанционной выдаче банов (в разработке)   <input type="radio" value="ban_management" name="authority" /></label> <br />

            <input type="submit" value="Понизить!">
        </form>
        <c:if test="${param.removeAuthority.equals('successful')}">
            <h3 style="color: green; font-style: italic;">Успешно понижено!</h3>
        </c:if>
    </section>

    <section class="post-container">
        <h2>Управление главной страницей</h2>

        <h3>Добавить пост</h3>
        <form action="<c:url value="/admin/addPost"/>" method="POST">
            <input type="text" name="author" placeholder="Author Name" required/> <br />

            <textarea title="Содержимое поста" name="content" rows="36" cols="128" required></textarea> <br />

            <input type="submit" value="Опубликовать!">
        </form>

        <h3>Удалить пост</h3>
        <form action="<c:url value="/admin/removePost"/>" method="POST">
            <label>ID поста   <input type="number" name="postId" required></label> <br />
            <input type="submit" value="Удалить!"/>
        </form>

        <h3>Изменить пост</h3>
        <form action="<c:url value="/admin/editPost"/>" method="POST">
            <label>ID поста   <input type="number" value="postId" required /></label> <br />

            <textarea title="Новое содержимое поста" name="content" rows="36" cols="128" required></textarea> <br/>

            <input type="submit" value="Изменить!"/>
        </form>
    </section>

    <section class="rule-container">
        <h2>Управление правилами сервера</h2>

        <h3>Добавить правило</h3>
        <form action="<c:url value="/admin/addRule"/>" method="POST">
            <label>Название правила:  <input type="text" name="name" placeholder="Название правила" required maxlength="255" /></label> <br/>
            <label>Описание правила:  <input type="text" name="description" placeholder="Описание правила" required /></label> <br/>
            <input type="submit" value="Добавить!" />
        </form>

        <h3>Удалить правило</h3>
        <form action="<c:url value="/admin/removeRule"/>" method="POST">
            <label>Название правила:  <input type="text" name="name" placeholder="Название правила" required maxlength="255" /></label> <br/>
            <input type="submit" value="Удалить!" />
        </form>

        <h3>Изменить правило</h3>
        <form action="<c:url value="/admin/editRule"/>" method="POST">
            <label>Название правила:  <input type="text" name="name" placeholder="Название правила" required maxlength="255" /></label> <br/>
            <label>Новое описание правила:  <input type="text" name="description" placeholder="Описание правила" required /></label> <br/>
            <input type="submit" value="Изменить!" />
        </form>
    </section>

    <section class="crew-container">
        <h2>Управление составом сервера</h2>

        <h3>Текущий состав:</h3>

        <table border="6px" style="text-align: center;">
            <tr><th>NAME</th><th>ROLES</th></tr>
            <c:forEach items="${crewMembers}" var="crewMember">
                <tr><td>${crewMember.name}</td><td><c:forEach items="${crewMember.roles}" var="role">${role.name()} </c:forEach></td></tr>
            </c:forEach>
        </table>
        <!-- DIRECTOR, GM, GA, ADMIN, ADMIN_CANDIDATE, WIKI, CODER, MAPPER, MENTOR, SPRITER, XENO_VISOR -->
        <h3>Добавить участника</h3>
        <form action="<c:url value="/admin/addCrew"/>" method="POST">
            <label>Имя члена состава: <input type="text" required name="name" /></label> <br/>

            <label>Директор? <input type="checkbox" name="director" /></label> <br/>
            <label>Game Master? <input type="checkbox" name="gm" /></label> <br/>
            <label>Game Admin? <input type="checkbox" name="ga" /></label> <br/>
            <label>Admin? <input type="checkbox" name="admin" /></label> <br/>
            <label>Admin Candidate? <input type="checkbox" name="admin_candidate" /></label> <br/>
            <label>Wiki-редактор? <input type="checkbox" name="wiki" /></label> <br/>
            <label>Кодер? <input type="checkbox" name="coder" /></label> <br/>
            <label>Маппер? <input type="checkbox" name="mapper" /></label> <br/>
            <label>Ментор? <input type="checkbox" name="mentor" /></label> <br/>
            <label>Спрайтер? <input type="checkbox" name="spriter" /></label> <br/>
            <label>Ксеновизор? <input type="checkbox" name="xenovisor" /></label> <br/>

            <input type="submit" value="Добавить участника!" />
        </form>

        <h3>Удалить участника</h3>
        <form action="<c:url value="/admin/removeCrew"/>" method="POST">
            <label>Имя члена состава: <input type="text" required name="name" /></label> <br/>
            <input type="submit" value="Удалить!" />
        </form>

        <%--<h3>Добавить роль участника</h3>--%>
        <%--<form action="<c:url value="/admin/removeCrew"/>" method="POST">--%>
            <%--<label>Имя члена состава: <input type="text" required name="name" /></label> <br/>--%>

            <%--<label>Директор? <input type="radio" name="role" value="director"/></label> <br/>--%>
            <%--<label>Game Master? <input type="radio" name="role" value="gm"/></label> <br/>--%>
            <%--<label>Game Admin? <input type="radio" name="role" value="ga"/></label> <br/>--%>
            <%--<label>Admin? <input type="radio" name="role" value="admin"/></label> <br/>--%>
            <%--<label>Admin Candidate? <input type="radio" name="role" value="admin_candidate"/></label> <br/>--%>
            <%--<label>Wiki-редактор? <input type="radio" name="role" value="wiki"/></label> <br/>--%>
            <%--<label>Кодер? <input type="radio" name="role" value="coder"/></label> <br/>--%>
            <%--<label>Маппер? <input type="radio" name="role" value="mapper"/></label> <br/>--%>
            <%--<label>Ментор? <input type="radio" name="role" value="mentor"/></label> <br/>--%>
            <%--<label>Спрайтер? <input type="radio" name="role" value="spriter"/></label> <br/>--%>
            <%--<label>Ксеновизор? <input type="radio" name="role" value="xenovisor"/></label> <br/>--%>

            <%--<input type="submit" value="Добавить роль!" />--%>
        <%--</form>--%>

        <%--<h3>Удалить роль участника</h3>--%>
        <%--<form action="<c:url value="/admin/removeCrew"/>" method="POST">--%>
            <%--<label>Имя члена состава: <input type="text" required name="name" /></label> <br/>--%>

            <%--<label>Директор? <input type="radio" name="director" value="director"/></label> <br/>--%>
            <%--<label>Game Master? <input type="radio" name="role" value="gm"/></label> <br/>--%>
            <%--<label>Game Admin? <input type="radio" name="role" value="ga"/></label> <br/>--%>
            <%--<label>Admin? <input type="radio" name="role" value="admin"/></label> <br/>--%>
            <%--<label>Admin Candidate? <input type="radio" name="role" value="admin_candidate"/></label> <br/>--%>
            <%--<label>Wiki-редактор? <input type="radio" name="role" value="wiki"/></label> <br/>--%>
            <%--<label>Кодер? <input type="radio" name="role" value="coder"/></label> <br/>--%>
            <%--<label>Маппер? <input type="radio" name="role" value="mapper"/></label> <br/>--%>
            <%--<label>Ментор? <input type="radio" name="role" value="mentor"/></label> <br/>--%>
            <%--<label>Спрайтер? <input type="radio" name="role" value="spriter"/></label> <br/>--%>
            <%--<label>Ксеновизор? <input type="radio" name="role" value="xeno_visor"/></label> <br/>--%>

            <%--<input type="submit" value="Удалить роль!" />--%>
        <%--</form>--%>
    </section>

    <section class="statistics">
        <b>Всего посещений сайта: <c:out value="${visitsCount}" /></b><br/>

        <b><i>Посетители сайта:</i></b>

        <table border="6px">
            <tr><th>IP ADDRESS</th><th>LAST HOST</th><th>LAST VISIT</th><th>VISITS</th></tr>
            <c:forEach items="${visitors}" var="visitor">
                <tr><td>${visitor.ipAddress}</td><td>${visitor.lastHost}</td><td>${visitor.lastVisit}</td><td>${visitor.visits}</td></tr>
            </c:forEach>
        </table>
    </section>
</body>
</html>