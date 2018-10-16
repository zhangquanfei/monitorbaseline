<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <meta charset="utf-8">
    <title>监控日志</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>



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
                <li ><a href="${pageContext.request.contextPath}/monitor/logmonitor.action">监控日志</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/monitor/querycopyfile.action">备份系统</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/querycheckoutfile.action">完整检测</a></li>
            </ul>
        </div>
    </div>
</nav>





<!--<button onclick="ajaxTest();">刷新</button>-->
<input type="button" onclick="ajaxTest();" value="备份" data-toggle="modal" data-target="#myModal" class="btn btn-default active">
<br/>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>序号</th>
        <th>机器ip</th>
        <th>原路径</th>
        <th>备份路径</th>
        <th>备份时间</th>
    </tr>
    </thead>
    <c:forEach items="${copyFiles}" var="s" varStatus="varSta">

        <tr class="success" height="10px">
            <td>${varSta.index}</td> <td>${s.ip}</td> <td>${s.originaldir}</td><td>${s.copydir}</td><td>${s.TIME}</td>

            <td>
                <div style="float:left;">
                <form action="${pageContext.request.contextPath}/monitor/deleteupdate.action" method="POST">
                    <button type="submit" class="btn btn-primarybtn btn-success" name = "updateposition" value=${varSta.index}>更新</button>
                </form>
                </div>

                <div style="float:left;">
                <form action="${pageContext.request.contextPath}/monitor/deleteupdate.action" method="POST">
                    <button type="submit" class="btn btn-danger" name = "deleteposition" value=${varSta.index}>删除</button>
                </form>
                </div>
            </td>

        </tr>

    </c:forEach>
</table>



<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">


            <form action="${pageContext.request.contextPath}/monitor/copyfile.action" method="POST">


                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加备份
                    </h4>
                </div>

                <div class="modal-body">

                    <input type="hidden"  name="sele" id="ss"/>
                    <span class="label label-info">机器地址</span> &nbsp <select  id="selectId" onchange="fei(); " class="form-control"></select><br/>

                    <span class="label label-info">原始路径</span> &nbsp <input type="text" name="originaldir" placeholder="原路径" class="form-control"><br/>

                    <span class="label label-info">存放路径</span> &nbsp <input type="text" name="copydir" placeholder="存放路径" class="form-control">
                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>

            </form>



        </div>
    </div>
</div>



</body>
</html>