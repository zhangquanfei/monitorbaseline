
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>Fei's Web</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>


    </head>

    <body>



        <div class="page-container">
            <h1>注册</h1>
            <form action="${pageContext.request.contextPath}/user/register.action" method="POST">


                <input id="txtest" type="text" name="username" class="username" placeholder="用户名">
                    <span id="tip" class="username"></span>
                <input type="password" name="password" class="password" placeholder="密码">
                <button type="submit">注册</button>
                <div class="error"><span>+</span></div>
            </form>
           
        </div>
        <br/>


        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>



        <script type="text/javascript">
            $(function () {
                $("#txtest").bind("focus", function () {
                    $("#tip").html("");
                })



                $("#txtest").bind("blur", function () {

                    $.ajax( {
                        type    : "POST",
                        url     : "${pageContext.request.contextPath}/user/inspect.action?time="+new Date().getTime(),
                        data    : {"user":$("#txtest").val()},
                        success : function(backDate,textStatus,ajax){
                            $("#tip").html(backDate);
                        }
                    } );

                })
            });
        </script>




    </body>

</html>

