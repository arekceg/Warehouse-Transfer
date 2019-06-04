<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 04.06.19
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
<form:form modelAttribute="user" method="POST">

    <form:select path="id" itemValue="id" itemLabel="name" items="${userList}"/>
    <input type="submit" value="login"/>

</form:form>

</body>
</html>
