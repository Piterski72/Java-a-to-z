<%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 22.11.2017
  Time: 6:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/update" method=post>
    <label>
        ID:
        <input type='text' name='id'>
    </label><br/>
    <br>
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
    <button type='submit'>Edit user</button>
</form>

</body>
</html>
