<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" rel="stylesheet" href="style.css" />
        <title>Connexion</title>
    </head>
    <body>
        <form method="post" action="connexion">
            <h1>Connexion</h1>
            <label for="username">Username</label>
            <input type="text" id="username" name="username" value="<c:out value="${param.username}"/>" size="20" maxlength="50" />
            <span class="error"><c:out value="${ auth.errors['username'] }"/></span>
            <br/>
            <label for="username">Password</label>
            <input type="password" id="password" name="password" value="" size="20" maxlength="50" />
            <span class="error"><c:out value="${ auth.errors['password'] }"/></span>
            <br/>
            <input type="submit" value="Connexion" id="btn_connect"/>
            <br/>
            <p class="error"><c:out value="${auth.result}"/></p>
            <p class="error"><c:out value="${auth.errors['sql_error']}"/></p>
        </form>
    </body>
</html>
