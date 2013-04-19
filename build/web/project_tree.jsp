<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Project Tree</h3>
<select name="project_tree">
    <c:forEach items="${projects}" var="project">
        <option><c:out value="${project.title}"/></option>
    </c:forEach>
</select>