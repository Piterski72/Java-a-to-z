<%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 20.11.2017
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<table style="border: 1pt solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Create date</th>
    </tr>

    <jsp:useBean id="myDate" class="java.util.Date"/>

    <c:forEach items="${users}" var="entry">
        <!-- Key = ${entry.key}, value = ${entry.value}<br> -->
        <tr>
            <td><c:out value="${entry.key}"></c:out></td>
            <td><c:out value="${entry.value.name}"></c:out></td>
            <td><c:out value="${entry.value.login}"></c:out></td>
            <td><c:out value="${entry.value.email}"></c:out></td>
            <c:set target="${myDate}" property="time" value="${entry.value.createDate}"/>
            <td><c:out value="${myDate}"></c:out></td>
        </tr>

    </c:forEach>

</table>
<br>
<form action="${pageContext.servletContext.contextPath}/update">
    <button type='submit'>Edit user</button>
</form>
<br>
<form action="${pageContext.servletContext.contextPath}/delete">
    <button type='submit'>Delete user</button>
</form>
<br>
<p><a href="${pageContext.servletContext.contextPath}/create">Add new user</a></p>


</body>
</html>
