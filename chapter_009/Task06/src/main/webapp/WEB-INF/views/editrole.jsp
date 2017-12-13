<%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 12.12.2017
  Time: 6:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit role</title>
</head>
<body>

<table style="border: 1pt solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Role Id</th>
        <th>Role Name</th>
    </tr>
    <c:forEach items="${rolelist}" var="entry">
        <!-- Key = ${entry.key}, value = ${entry.value}<br> -->
        <tr>
            <td><c:out value="${entry.key}"></c:out></td>
            <td><c:out value="${entry.value.name}"></c:out></td>
        </tr>
    </c:forEach>
</table>

<form action='${pageContext.servletContext.contextPath}/editrole' method=post>
    <label><br>
        Role id:
        <input type='text' name='roleid' required>
    </label><br/>
    <br>
    <label><br>
        Role name:
        <input type='text' name='rolename' required>
    </label><br/>
    <br>
    <button type='submit'>Update role</button>
</form>

</body>
</html>
