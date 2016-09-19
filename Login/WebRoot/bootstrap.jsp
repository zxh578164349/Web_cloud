<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
   <title>Bootstrap 实例 - 模态框（Modal）插件方法</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,Chrome=1">

<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<!--[if lt IE 9]>  
  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>

<h2>模态框（Modal）插件方法</h2>

<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" >
   开始演示模态框
</button>

<a href='kyz_findById_layer?billNo=EM63115082001&factNo=631' data-toggle="modal" data-target="#myModal">打开对话框</a>&nbsp;
<a href='success.html' data-toggle="modal" data-target="#myModal">打开对话框</a>&nbsp;
<a href="#myModal" data-toggle="modal">点击我</a>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               模态框（Modal）标题
            </h4>
         </div>
         <div class="modal-body">
           
         </div>
         
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<hr><hr>
<a href="http://172.17.18.173:8080/Login/vbm_findById_email?visaSort=C10&%20billNo=EM63115082001&%20factNo=631&%20email=kyinfo.David@yyin.yydg.com.cn">请点我</a>
<script>

   
function myHide(){
    $("#myModal").modal("hide");
}
</script>
<script >

	$(function() {
		$("#myModal").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});
		
		$("#myModal").on("show.bs.modal",function(){
		    $(".modal-body").load("kyz_findById_layer",{"billNo":"EM63115082001","factNo":"631"});
		    //$(this).modal({backdrop:'static'});
		});
	})
</script>




</body>
</html>

