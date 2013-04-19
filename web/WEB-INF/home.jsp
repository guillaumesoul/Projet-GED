<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div id="header">
            <%@include file="/header.jsp" %>
        </div>
        <div id="nav">
            <%@include file="/nav.jsp" %>
        </div>
        <div id="project_tree">
            <%@include file="/project_tree.jsp" %>
        </div>
        <div id="list_doc">

        </div>
        <form method="get" action="disconnect">
            <input type="submit" value="Disconnect"/>
        </form>
    </body>
</html>