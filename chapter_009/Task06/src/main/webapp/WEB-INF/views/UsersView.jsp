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
<c:set var="rolename" scope="session" value="${sessionScope.rolename}"/>
<p>Login is:  <c:out value="${sessionScope.login}"/><p>
<p>Role is:  <c:out value="${sessionScope.rolename}"/><p>

<table style="border: 1pt solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Role</th>
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
            <td><c:out value="${entry.value.role}"></c:out></td>
            <td><c:out value="${entry.value.email}"></c:out></td>
            <c:set target="${myDate}" property="time" value="${entry.value.createDate}"/>
            <td><c:out value="${myDate}"></c:out></td>
        </tr>
    </c:forEach>
</table>
<fieldset>
    <legend>User management</legend>
    <form action="${pageContext.servletContext.contextPath}/update">
        <button type='submit'>Edit user</button>
    </form>

    <c:if test="${sessionScope.rolename=='admin'}">
    <form action="${pageContext.servletContext.contextPath}/delete">
        <button type='submit'>Delete user</button>
    </form>
    <form action="${pageContext.servletContext.contextPath}/create">
        <button type='submit'>Add new user</button>
    </form>
    <br>
</fieldset>
<fieldset>
    <legend>Role management</legend>
    <p><a href="${pageContext.servletContext.contextPath}/newrole">Create new role</a></p>
    <p><a href="${pageContext.servletContext.contextPath}/editrole">Edit role</a></p>
    <p><a href="${pageContext.servletContext.contextPath}/deleterole">Delete role</a></p>

</fieldset>
</c:if>

<p><a href="${pageContext.servletContext.contextPath}/logout">Log out</a></p>

</body>
</html>
