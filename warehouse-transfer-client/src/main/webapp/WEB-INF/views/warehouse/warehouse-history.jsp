<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>History</title>
</head>
<body>
<h1>History</h1>
<h3>Accepted transfers:</h3>
<br>
<table border="1">
    <tr>
        <th>Transfer ID</th>
        <th>Date Created</th>
        <th>Source Warehouse</th>
        <th>Destination Warehouse</th>
        <th>Accepted</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${incomingTransfersHistory}" var="transfer">
        <tr>
            <td>${transfer.id}</td>
            <td>${transfer.createdDate}</td>
            <td>${transfer.sourceWarehouse.name}</td>
            <td>${transfer.destinationWarehouse.name}</td>
            <td>${transfer.acceptedDate}</td>
            <td><a href="/transfer/details/${transfer.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<h3>Sent transfers:</h3>
<br>
<table border="1">
    <tr>
        <th>Transfer ID</th>
        <th>Date Created</th>
        <th>Source Warehouse</th>
        <th>Destination Warehouse</th>
        <th>Accepted</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${outgoingTransfersHistory}" var="transfer">
        <tr>
            <td>${transfer.id}</td>
            <td>${transfer.createdDate}</td>
            <td>${transfer.sourceWarehouse.name}</td>
            <td>${transfer.destinationWarehouse.name}</td>
            <td>${transfer.acceptedDate}</td>
            <td><a href="/transfer/details/${transfer.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
