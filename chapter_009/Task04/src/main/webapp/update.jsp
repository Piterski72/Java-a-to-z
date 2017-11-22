<%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 22.11.2017
  Time: 6:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form action='<%=request.getContextPath()%>/update' method=post>
    ID: <input type='text' name='id'><br/>
    <br>
    Login: <input type='text' name='login'><br/>
    <br>
    Name: <input type='text' name='name'><br/>
    <br>
    Email: <input type='text' name='email'><br/>
    <br>
    <button type='submit'>Edit user</button>
</form>

</body>
</html>
