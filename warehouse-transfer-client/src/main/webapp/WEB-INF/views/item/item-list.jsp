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
    <title>Item list</title>
</head>
<body>
Add item:
<form:form method="POST" modelAttribute="${AttributeNames.ITEM}">
    <label for="addItem">Name:</label>
    <form:input path="name" type="text"/>
    <input type="submit" value="Add Item"/>
    <br>
    <form:errors path="name"/>
</form:form>
<table>
    <tr>
        <th>Item ID</th>
        <th>Item name</th>

    </tr>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
        </tr>
    </c:forEach>
</table>
<%--<table>--%>
<%--    <tr>--%>
<%--        <th>ID</th>--%>
<%--        <th>item name</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${items}" var="item">--%>
<%--        <tr>--%>
<%--            <td>${item.id}</td>--%>
<%--            <td>${item.name}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
</body>
</html>
