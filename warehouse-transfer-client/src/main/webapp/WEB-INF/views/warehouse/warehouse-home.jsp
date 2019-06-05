<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 04.06.19
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<form action="/logout" method="POST">
    <input type="submit" value="LOGOUT"/>
</form>
<h1>Stock List</h1>
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
                <c:if test="${reservedStock[i].itemStock != 0 || availableStock[i].itemStock != 0}">
                    <tr>
                        <td>${totalStock[i].item.id}</td>
                        <td>${totalStock[i].item.name}</td>
                        <td>${totalStock[i].itemStock}</td>
                        <td>${reservedStock[i].itemStock}</td>
                        <td>${availableStock[i].itemStock}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<br><br>
<%--<form method="GET" action="/transfer/new/" class="inline">--%>
<%--    <button type="submit" name="sourceWarehouseId" value="${warehouseStockInfo.warehouse.id}" class="link-button">--%>
<%--        Add new transfer--%>
<%--    </button>--%>
<%--</form>--%>
<a href="/transfer/new">Add new transfer</a>
<br>
<a href="/warehouse/history">Transfer History</a>
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
        <th>Accept</th>
    </tr>
    <form:form modelAttribute="transferId" method="POST" action="/transfer/accept/">
        <c:forEach items="${incomingTransfers}" var="transfer">
            <tr>
                <td>${transfer.id}</td>
                <td>${transfer.createdDate}</td>
                <td>${transfer.sourceWarehouse.name}</td>
                <td>${transfer.destinationWarehouse.name}</td>
                <td>${transfer.isAccepted}</td>
                <td><a href="/transfer/details/${transfer.id}">Details</a></td>
                <td><form:hidden path="id" value="${transfer.id}"/><input type="submit" value="Accept"/></td>
            </tr>
        </c:forEach>
    </form:form>
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
            <form:form modelAttribute="transferId" method="POST" action="/transfer/delete/">
                <td><form:hidden path="id" value="${transfer.id}"/><input type="submit" value="Delete"/></td>
            </form:form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
