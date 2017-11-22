<%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 22.11.2017
  Time: 6:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<form action='<%=request.getContextPath()%>/delete' method=post>

    ID: <input type='text' name='id'><br/>
    <br>
    <button type='submit'>Delete user</button>

</form>
</body>
</html>
