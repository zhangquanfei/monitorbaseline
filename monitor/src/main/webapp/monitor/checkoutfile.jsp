<%@ taglib prefix="text-align" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                    $("#selectId").append("<option >请选择机器</option>");
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/monitor/index.jsp">监控系统</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/monitor/querymonitor.action">设置监控</a></li>
                <li ><a href="${pageContext.request.contextPath}/monitor/logmonitor.action">监控日志</a></li>
                <li><a href="${pageContext.request.contextPath}/monitor/querycopyfile.action">备份系统</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/monitor/querycheckoutfile.action">完整检测</a></li>
            </ul>
        </div>
    </div>
</nav>



<div >
    <form class="bs-example bs-example-form" role="form" action="${pageContext.request.contextPath}/monitor/checkoutfile.action" method="GET">
        <div class="input-group input-group-lg">
            <span class="input-group-addon">完整性校验</span>

            <span class="input-group-addon">
			   <input type="hidden"  name="sele" id="ss"/>
               <select  id="selectId" onchange="fei(); "></select>
			   </span>

            <input type="text" name="direction" class="form-control" placeholder="校验位置">
            <span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							Go!
						</button>
			</span>
        </div><br>
    </form>
</div>


<table class="table table-bordered">
    <thead>
    <tr>
        <th>序号</th>
        <th>机器ip</th>
        <th>检查结果</th>
        <th>时间</th>
    </tr>
    </thead>
<c:forEach items="${checkoutdatas}" var="s" varStatus="varSta">

    <tr class="success"> <td>${varSta.index}</td> <td>${s.ip}</td> <td>${s.DATA}</td> <td>${s.TIME}</td>
    </tr>

</c:forEach>
</table>



</body>
</html>