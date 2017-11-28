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
        <input type='text' name='login'>
    </label><br/>
    <br>
    <label>
        Name:
        <input type='text' name='name'>
    </label><br/>
    <br>
    <label>
        Email:
        <input type='text' name='email'>
    </label><br/>
    <br>
    <button type='submit'>Create user</button>
</form>

</body>
</html>
