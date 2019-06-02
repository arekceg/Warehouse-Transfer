<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 01.06.19
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer Form</title>
</head>
<body>
<form:form method="POST" modelAttribute="transfer">
Select transfer destination warehouse:<br>
    <form:select path="destinationWarehouse.id" items = "${warehouses}" itemValue="id" itemLabel="name"/>
    <form:errors path="destinationWarehouse"/>
    <form:hidden path="sourceWarehouse.id" value="${sourceWarehouse.id}"/>
<%--    <form:hidden path="transferContents[0]" value = "0"/>--%>
    <table border="1">
        <tr>
            <th>Item ID</th>
            <th>Item Name</th>
            <th>Current Stock</th>
            <th>Amount to transfer</th>
        </tr>
    <c:forEach items="${stockList}" var="stock">
        <tr>
            <td>${stock.item.id}</td>
            <td>${stock.item.name}</td>
            <td>${stock.itemStock}</td>
            <td><input name="${stock.item.id}" type="number" min = "0" max = "${stock.itemStock}"/></td>
<%--            <td><form:input path="transferContents" name = "${stock.item.id}" type="number" min = "0" max="${stock.itemStock}"/></td>--%>
<%--            <td><form:hidden path="transferContents" value = "${stock.item.id}"/></td>--%>
        </tr>
    </c:forEach>

    </table>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>
