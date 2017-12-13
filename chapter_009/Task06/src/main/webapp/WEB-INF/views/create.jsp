<%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 22.11.2017
  Time: 6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/create' method=post>
    <label>
        Login:
        <input type='text' name='login' required>
    </label><br/>
    <br>
    <label>
        Name:
        <input type='text' name='name' required>
    </label><br/>
    <br>
    <label>
        Email:
        <input type='text' name='email' required>
    </label><br/>
    <br>
    <label>
        Password:
        <input type='text' name='password' required>
    </label><br/>
    <br>
    <label><br>
        Role:
        <br><br>
        <c:forEach items="${rolelist}" var="entry">

            <input type="radio" name="roleid" value="${entry.key}" required> ${entry.value.name}<br>

        </c:forEach>
        <br><br>
    </label><br/>

    <button type='submit'>Create user</button>
</form>

</body>
</html>
