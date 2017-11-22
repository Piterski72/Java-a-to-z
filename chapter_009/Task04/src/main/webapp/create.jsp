<%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 22.11.2017
  Time: 6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action='<%=request.getContextPath()%>/create' method=post>
    Login: <input type='text' name='login'><br/>
    <br>
    Name: <input type='text' name='name'><br/>
    <br>
    Email: <input type='text' name='email'><br/>
    <br>
    <button type='submit'>Create user</button>
</form>

</body>
</html>
