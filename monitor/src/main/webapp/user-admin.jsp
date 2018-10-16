<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/29
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>user-admin</title>
</head>
<body>
Welcom:<shiro:principal></shiro:principal>
<br/>
<shiro:hasAnyRoles name="user">
    user
</shiro:hasAnyRoles>

<br/>
<shiro:hasAnyRoles name="admin">
    admin
</shiro:hasAnyRoles>

<br/>
<a href="/shiro-logout">退出</a>
</body>
</html>