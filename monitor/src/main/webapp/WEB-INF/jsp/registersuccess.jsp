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
    <title>seccess</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
恭喜你，注册成功!
<script type="text/javascript">
    var t=4;//设定跳转的时间
    setInterval("refer()",1000); //启动1秒定时
    function refer(){
        if(t==0){
            location="${pageContext.request.contextPath}/index.jsp"; //#设定跳转的链接地址
        }
        document.getElementById('show').innerHTML=""+(t+1)+"秒后跳转到登录主页"; // 显示倒计时
        t--; // 计数器递减
        //本文转自：
    }
</script>
<span id="show"></span>
<br/>
若无法跳转请点击<a href="${pageContext.request.contextPath}/index.jsp"  >here</a>
</body>
</html>
