<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 04.06.19
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<form:form modelAttribute="user" method="POST" >
    Name: <form:input path="name"/>
    <br><br>
    Login: <form:input path="username"/>
    <br><br>
    Password: <form:input path="password" type = "password"/>
    <br><br>
<%--    Warehouse: <form:select path="warehouse.id" items="${warehouses}" itemValue="id" itemLabel="name"/>--%>
<%--    <br><br>--%>
    <input type="submit" value="Add">
</form:form>

</body>
</html>
