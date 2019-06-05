<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 04.06.19
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>

</head>
<body>
<a href="/admin/add-user">Add User</a>
<br><br>
<a href="/admin/add-item/existing">Add existing item to warehouse stock</a>
<br><br>
<a href="/admin/add-item/new">Add new item to warehouse stock</a>
<br><br>
<a href="/admin/all-transactions">All transactions</a>
<br><br>
<a href="/admin/add-warehouse">Add new warehouse</a>
<br><br>
<a href="/warehouse/">Access admin warehouse</a>

<br><br>
<form action="/logout" method="POST">
    <input type="submit" value="LOGOUT"/>
</form>
</body>
</html>
