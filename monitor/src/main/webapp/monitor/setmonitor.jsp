<%@ taglib prefix="text-align" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <title>设置监控</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.1.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">

    <!--ajax参考这个http://blog.csdn.net/quincylk/article/details/51423297-->
    <script type="text/javascript">
        function ajaxTest(){
            $.ajax({
                data:"name="+$("#name").val(),
                type:"GET",
                dataType: 'json',
                url:"${pageContext.request.contextPath}/monitor/login1.action",
                error:function(data){
                    alert("出错了！！:"+data.msg);
                },
                success:function(data){
                    //alert("success:"+data.msg+" name:"+data.name+" age:"+data.age);
                   // alert("success:"+data);

                    $("#selectId").empty();
                    $("#selectId").append("<option >请选择</option>");
                    for(var k in data){
                       // alert(data[k]);
                        $("#selectId").append("<option value='"+data[k]+"'>"+data[k]+"</option>");
                    }
                }
            });
        }

        function fei(){
           // alert("-----");
           // alert($('#selectId').val());

            $('#ss').val($('#selectId').val());
           // alert($('#ss').val());
        }

    </script>

    <script language="javascript" type="text/javascript">

        function redirect()
        {
            // 以下方式直接跳转
            window.location.href='http://localhost:8087/';
        }

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
<body onload="ajaxTest();">
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
            <!--<a class="navbar-brand" onclick="redirect()">监控系统</a>-->
            <a class="navbar-brand" href="${pageContext.request.contextPath}/monitor/index.jsp">监控系统</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/monitor/querymonitor.action">设置监控</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/logmonitor.action">监控日志</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/querycopyfile.action">备份系统</a></li>
                <li ><a href="${pageContext.request.contextPath}/monitor/querycheckoutfile.action">完整检测</a></li>
            </ul>
        </div>
    </div>
</nav>








<!--<button onclick="ajaxTest();">刷新</button>-->
<input type="button"  value="添加监控" data-toggle="modal" data-target="#myModal" class="btn btn-default active">

<br/>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>序号</th>
        <th>机器ip</th>
        <th>监控路径</th>
        <th>监控事件</th>
        <th>排除路径</th>
        <th>状态</th>
    </tr>
    </thead>

<c:forEach items="${monitor}" var="s" varStatus="varSta">

        <tr class="success" height="10px">
            <td>${varSta.index}</td>
            <td>${s.ip}</td>
            <td>${s.directory}</td>
            <td>${s.event}</td>
            <td>${s.exclude}</td>
            <td>${s.state}</td>

            <td>
                    <form action="${pageContext.request.contextPath}/monitor/deletemonitor.action" method="POST">
                        <button type="submit" class="btn btn-danger" name = "position" value=${varSta.index}>删除</button>
                    </form>
            </td>

        </tr>

</c:forEach>

</table>



<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">


            <form action="${pageContext.request.contextPath}/monitor/addmonitor.action" method="POST">


                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加监控
                    </h4>
                </div>

                <div class="modal-body">

                    <input type="hidden"  name="sele" id="ss"/>
                    <span class="label label-info">机器地址</span> &nbsp <select  id="selectId" onchange="fei(); " class="form-control"></select><br/>

                    <span class="label label-info">监控目录</span> &nbsp <input type="text" name="directory" class="form-control" placeholder="请输入监控目录"><br/>

                    <span class="label label-info">选择事件</span> &nbsp <input type="checkbox" value="0" name="event" class="checkbox-inline">Create   <input type="checkbox" value="1" name="event"  class="checkbox-inline">Modify <input type="checkbox" value="2" name="event" class="checkbox-inline">Delete<br/>

                    <br/>
                    <span class="label label-info">排除目录</span> &nbsp <input type="text" name="exclude" class="form-control" placeholder="请输入排除目录">
                    <input type="hidden" name="Jszzdm" id="Jszzdm" value="@Model.Jszzdm" />
                    <script>
                        $('input[type=checkbox]').change(function(){
                            $('#Jszzdm').val($('input[type=checkbox]:checked').map(function(){return this.value}).get().join(','))
                        })
                    </script>
                    <!--<input type="button" value="GET" onclick="alert($('#Jszzdm').val())"/>-->
                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>

            </form>



        </div>
    </div>
</div>







</body></html>