<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Login</h1>
<form action="<c:url value="/login"/>" method="POST">
    <label for="username">User Name:</label>
    <input id="username" name="username" type="text"/>
    <label for="password">Password:</label>
    <input id="password" name="password" type="password"/>
    <input type="submit" value="Log In"/>
</form>
</body>
</html>