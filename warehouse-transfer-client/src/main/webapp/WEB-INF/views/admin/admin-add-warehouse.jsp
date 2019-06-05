<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 04.06.19
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add warehouse</title>
</head>
<body>
<form:form modelAttribute="warehouse" method="POST" action="/warehouse/add">
    Warehouse name: <form:input path="name"/>
    <br><br>
    Warehouse manager: <form:select path="manager" items="${users}" itemValue="id" itemLabel="name"/>
   <br><br>
    <input type="submit" value="Add"/>
</form:form>
</body>
</html>
