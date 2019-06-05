<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.arek.warehousetransfer.utils.AttributeNames" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 01.06.19
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer List</title>
</head>
<body>

<h3>${transfersType} transfers:</h3>
<table border="1">
    <tr>
        <th>Transfer ID</th>
        <th>Date Created</th>
        <th>Source Warehouse</th>
        <th>Destination Warehouse</th>
        <th>Is Accepted</th>
        <th>Details</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${transfers}" var="transfer">
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
