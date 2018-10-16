
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <meta charset="utf-8">
    <title>监控系统</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>


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
            <a class="navbar-brand" href="#">监控系统</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/monitor/querymonitor.action">监控设置</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/logmonitor.action">监控日志</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/querycopyfile.action">备份系统</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/querycheckoutfile.action">完整检测</a></li>
            </ul>
        </div>
    </div>
</nav>
大数据系统的基线安全监控。这是一个分布式，高可靠，可扩展的大数据基线监控系统。<br/>
它包含四大模块：监控设置。监控日志。备份系统。完整性检测。<br/>
监控设置：指定监控的机器ip，监控事件，监控路径以及指定该路径下的排除监控的路径。<br/>
监控日志：每5秒自动检测是否有监控事件产生。若有监控事件产生就显示出该事件的相关信息。后期加通知功能。<br/>
备份系统：支持批量备份某一机器的文件至指定的路径。后期加权限验证功能。<br/>
完整性检测：包含两种检测。一种是批量检测，一种是精确检测。批量检测是检测吗，某一机器的某一路径下文件的变化，如哪些文件添加了。哪些文件缺失了。哪些文件改变了。
           精确检测是检测具体的文件变化的内部。因为检测的文件主要是配置文件，若仅仅同一个配置项在该文件和该配置文件对应的备份文件不同行，则认定该文件无变化。<br/>
</body>
</html>