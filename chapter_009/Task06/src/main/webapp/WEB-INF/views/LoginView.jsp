<%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 01.12.2017
  Time: 6:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: crimson">
        <c:out value="${error}">
        </c:out>
    </div>
</c:if>

<form action="${pageContext.servletContext.contextPath}/signin" method=post>
    <label>
        Login:
        <input type='text' name='login'>
    </label><br/>
    <br>
    <label>
        Password:
        <input type='password' name='password'>
    </label><br/>
    <br>

    <br>
    <button type='submit'>Enter base</button>
</form>

</body>
</html>
