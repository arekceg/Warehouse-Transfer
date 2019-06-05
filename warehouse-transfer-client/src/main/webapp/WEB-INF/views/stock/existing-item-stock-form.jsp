<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 01.06.19
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stock Form</title>
</head>
<body>
<form:form modelAttribute="stock" method="POST">
    <c:set value="${stock.item}" var="item"/>
    Item name: <form:select path="item" items="${items}" itemLabel="name" itemValue="id"/>
    <br><br>
    Item amount: <form:input path="itemStock" type = "number"/>
    <br><br>
    Warehouse: <form:select path="warehouse.id" items="${warehouses}" itemLabel="name" itemValue="id"/>
    <br><br>
    <form:hidden path="stockType" value="AVAILABLE"/>
    <input type="submit" value="ADD"/>
</form:form>

</body>
</html>
