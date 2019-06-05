<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 04.06.19
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All transfers</title>
</head>
<body>
<h3>Incoming transfers:</h3>
<br>
<table border="1">
    <tr>
        <th>Transfer ID</th>
        <th>Date Created</th>
        <th>Source Warehouse</th>
        <th>Destination Warehouse</th>
        <th>Is Accepted</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${incomingTransfers}" var="transfer">
        <tr>
            <td>${transfer.id}</td>
            <td>${transfer.createdDate}</td>
            <td>${transfer.sourceWarehouse.name}</td>
            <td>${transfer.destinationWarehouse.name}</td>
            <td>${transfer.isAccepted}</td>
            <td><a href="/transfer/details/${transfer.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<h3>Outgoing transfers:</h3>
<br>
<table border="1">
    <tr>
        <th>Transfer ID</th>
        <th>Date Created</th>
        <th>Source Warehouse</th>
        <th>Destination Warehouse</th>
        <th>Is Accepted</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${outgoingTransfers}" var="transfer">
        <tr>
            <td>${transfer.id}</td>
            <td>${transfer.createdDate}</td>
            <td>${transfer.sourceWarehouse.name}</td>
            <td>${transfer.destinationWarehouse.name}</td>
            <td>${transfer.isAccepted}</td>
            <td><a href="/transfer/details/${transfer.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>
<h1>DEBUG STOCK LIST</h1>
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
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
