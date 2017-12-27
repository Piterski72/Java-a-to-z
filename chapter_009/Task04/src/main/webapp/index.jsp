<%@ page import="ru.User" %>
<%@ page import="ru.UserStore" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: pit
  Date: 20.11.2017
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <% for (Map.Entry entry : UserStore.getBase().showUsers().entrySet()) {%>
    <% int id = (int) entry.getKey();%>
    <% User user = (User) entry.getValue();%>
    <tr>
        <td><%=id%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=new Date(user.getCreateDate())%>
        </td>
    </tr>
    <% } %>
</table>
<br>
<form action="<%=request.getContextPath()%>/update.jsp">
    <button type='submit'>Edit user</button>
</form>
<br>
<form action="<%=request.getContextPath()%>/delete.jsp">
    <button type='submit'>Delete user</button>
</form>
<br>
<p><a href="<%=request.getContextPath()%>/create.jsp">Add new user</a></p>


</body>
</html>
