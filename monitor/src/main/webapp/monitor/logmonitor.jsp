<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <meta charset="utf-8">
    <title>监控日志</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script language="JavaScript">
        function myrefresh()
        {
            window.location.reload();
        }
        setTimeout('myrefresh()',5000); //指定1秒刷新一次
    </script>

</head>
<style type="text/css">
    body
    {
        background: #00ff00 url('/src/mei.jpg') no-repeat fixed center;
        background-size: 100% 100%;
        background-position: top center;
    }
</style>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/monitor/index.jsp">监控系统</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/monitor/querymonitor.action">设置监控</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/monitor/logmonitor.action">监控日志</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/querycopyfile.action">备份系统</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/querycheckoutfile.action">完整检测</a></li>
            </ul>
        </div>
    </div>
</nav>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>序号</th>
        <th>机器ip</th>
        <th>触发事件</th>
        <th>触发路径</th>
        <th>触发时间</th>
    </tr>
    </thead>
<c:forEach items="${listlogs}" var="s" varStatus="varSta">

    <tr class="success"><td>${varSta.index}</td> <td>${s.ip}</td> <td>${s.EVENT}</td><td>${s.direction}</td><td>${s.TIME}</td></tr>

</c:forEach>
</table>

</body>
</html>