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
    <form:select path="destinationWarehouse.id" items="${warehouses}" itemValue="id" itemLabel="name"/>
    <form:errors path="destinationWarehouse"/>
    <form:hidden path="sourceWarehouse.id" value="${sourceWarehouse.id}"/>
    <form:errors path="transferContents"/>
    <br><br>
    <c:choose>
        <c:when test="${warehouseStockInfo.totalStock.size() < 1}">
            Warehouse has no current stock
        </c:when>
        <c:otherwise>
    <table border="1">
    <tr>
        <th>Item ID</th>
        <th>Item Name</th>
        <th>Total Stock</th>
        <th>Reserved Stock</th>
        <th>Available Stock</th>
        <th>Amount to transfer</th>
    </tr>
            <c:set var="availableStock" value="${warehouseStockInfo.availableStock}"/>
            <c:set var="reservedStock" value="${warehouseStockInfo.reservedStock}"/>
            <c:set var="totalStock" value="${warehouseStockInfo.totalStock}"/>
            <c:forEach begin="0" end="${totalStock.size()-1}" var="i">
                <tr>
                    <td>${totalStock[i].item.id}</td>
                    <td>${totalStock[i].item.name}</td>
                    <td>${totalStock[i].itemStock}</td>
                    <td>${reservedStock[i].itemStock}</td>
                    <td>${availableStock[i].itemStock}</td>
                    <td><input name="${totalStock[i].item.id}" type="number" min="0"
                               max="${availableStock[i].itemStock}"
                               value="0"/>
                    </td>
                        <%--            <td><form:input path="transferContents" name = "${stock.item.id}" type="number" min = "0" max="${stock.itemStock}"/></td>--%>
                        <%--            <td><form:hidden path="transferContents" value = "${stock.item.id}"/></td>--%>
                </tr>
            </c:forEach>

            </table>
            <input type="submit" value="submit"/>
        </c:otherwise>
    </c:choose>
</form:form>
</body>
</html>
