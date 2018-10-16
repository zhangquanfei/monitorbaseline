<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/27 0027
  Time: 下午 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
非常遗憾，注册失败!<a href="${pageContext.request.contextPath}/register.jsp"  >返回注册页面</a><br/>
<h3>可能原因：用户名已经存在！</h3>
</body>
</html>
