<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 01.06.19
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer Details</title>

</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Created</th>
        <c:if test="${transfer.updatedDate != null}">
            <th>updated</th>
        </c:if>
        <th>Source Warehouse</th>
        <th>Destination Warehouse</th>
        <th>Is Accepted</th>
        <c:if test="${transfer.acceptedDate != null}">
            <th>Accept Date</th>
        </c:if>
        <th>Is Challenged</th>
        <c:if test="${transfer.challengedDate != null}">
            <th>Challenge Date</th>
        </c:if>
    </tr>
    <tr>
        <td>${transfer.id}</td>
        <td>${transfer.createdDate}</td>
        <c:if test="${transfer.updatedDate != null}">
            <td>${transfer.updatedDate}</td>
        </c:if>
        <td>${transfer.sourceWarehouse.name}</td>
        <td>${transfer.destinationWarehouse.name}</td>
        <td>${transfer.isAccepted}</td>
        <c:if test="${transfer.acceptedDate != null}">
            <td>${transfer.acceptedDate}</td>
        </c:if>
        <td>${transfer.isChallenged}</td>
        <c:if test="${transfer.challengedDate != null}">
            <td>${transfer.challengedDate}</td>
        </c:if>
    </tr>
</table>
<br><br>
Item List:
<table border="1">
    <tr>
        <th>Item ID</th>
        <th>Item name</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${transfer.transferContents}" var="transfeContent">
        <tr>
            <td>${transfeContent.item.id}</td>
            <td>${transfeContent.item.name}</td>
            <td>${transfeContent.amount}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
