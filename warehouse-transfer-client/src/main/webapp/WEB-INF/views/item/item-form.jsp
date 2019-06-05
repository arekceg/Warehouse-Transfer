<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.arek.warehousetransfer-client.utils.AttributeNames"%>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 28.05.19
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Item</title>
</head>
<body>
<form:form method="POST" modelAttribute="${AttributeNames.ITEM}">
    New name: <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
