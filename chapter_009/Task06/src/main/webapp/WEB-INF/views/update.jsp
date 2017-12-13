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
        <input type='text' name='id' value="${sessionScope.id}" required>
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

    <c:if test="${sessionScope.rolename=='admin'}">
        <label>
            Login:
            <input type='text' name='login' required>
        </label><br/>
        <br>
        <label>
            Password:
            <input type='text' name='password' required>
        </label><br/>
        <label><br>
            Role:
            <br><br>
            <c:forEach items="${rolelist}" var="entry">

                <input type="radio" name="roleid" value="${entry.key}" required> ${entry.value.name}<br>

            </c:forEach>

        </label><br/>
        <br>

    </c:if>

    <button type='submit'>Edit user</button>
</form>

</body>
</html>
