<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>業務每週報告表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="loginpage/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="loginpage/css/form.css" /> 

<!-- <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
</head>

<body>
<div class="container">
<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
<h2>業務每週報告表 </h2>
<form action="webweekly_add" role="form" id="form" method="post">
  <div class="form-group">
    <label>業務：<s:property value="uname"/></label><br>
    <label>日期：<s:property value="sdate"/>-<s:property value="edate"/></label>           
    <input type="hidden" name="obj.webUser.id" value="<s:property value='uid'/>"/>     
    <input type="hidden" name="obj.SDate" value="<s:property value='sdate'/>">   
    <input type="hidden" name="obj.EDate" value="<s:property value='edate'/>">  
  </div>
  <div class="form-group">
    <label>品牌</label>
    <select class="form-control" id="dwrWebbrank" datatype="*" name="obj.webErpBrankProcess.id" onchange="checkreport()"></select>
  </div>
  
  <div id="bodyid">
		<jsp:include page="table1/webweeklyreport_email1.jsp"/>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" class="btn btn-default" id="sub">存檔</button>
      <button type="reset" class="btn btn-default" id="reset">清除</button>
    </div>
  </div>
</form>
</div>
</div>



 <script type="text/javascript" src="loginpage/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="loginpage/jquery/layer/layer.min.js"></script>
<script src="loginpage/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="loginpage/jquery/Validform_v5.3.2_min.js"></script>
<!--[if lt IE 9]>  
<script src="bootstrap/html5.js"></script>
<script src="bootstrap/respond.min.js"></script>
<![endif]-->  


<script type="text/javascript">
var jq=jQuery.noConflict();
var loadi;
		jq(document).ajaxStart(function() {
			loadi = layer.load("請稍等...");
		});
		jq(document).ajaxStop(function() {
			layer.close(loadi);
		});
jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true	,
			ajaxPost:true,
			postonce:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					//loadUrl("webweekly_findPageBean");
					demo.setStatus("posted");					
				}else{
					layer.msg("提交失敗!",3,3);
				}				
			}
		});	
		jq("#sub").click(function(){
		   if(demo.getStatus()=="posted"){
		      alert("不能二次提交，請重新選擇品牌");	
		   }		   	   
		});
		
		jq("#dwrWebbrank").change(function(){
		   demo.resetStatus();
		});
							
	}); 

/*加載所有品牌*/
jq.ajax({
	type:"get",
	url:"weberpbp_findObjOp2",
	dataType:"json",
	success:function(data){
		jq("#dwrWebbrank").empty();
		jq("#dwrWebbrank").append("<option value=''>品牌選擇</option>");
		var item="";
		jq.each(data,function(i,obj){		    
		    item+="<option value='"+obj[0]+"'>"+obj[2]+"</option>";		    							
		});
		jq("#dwrWebbrank").append(item);
	}
});
function checkreport(){
  if(jq("#dwrWebbrank").val()!=""){
     jq.ajax({
      type:"post",
      url:"webweekly_findByUidASdate_page",
      dataType:"html",
      data:{uid:jq("input[name='obj.webUser.id']").val(),sdate:jq("input[name='obj.SDate']").val(),bid:jq("#dwrWebbrank").val()},
      success:function(data){       
            jq("#bodyid").html(data);
                   
      },
      error:function(error){
          jq("#bodyid").html(error.responseText);
      }
    });
  }
  
}

</script>

<jsp:include page="../copyright.jsp"/>
</body>
</html>
