<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'managerUser.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

 
</head>


<body>
	<jsp:include page="publicHead_webtype.jsp" />
	<hr />
		
	<div id="bodyid">
		<jsp:include page="table1/webtype1.jsp" />
	</div>

<script type="text/javascript">
	
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webtype_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}

	function submis() {		
		var factno=document.getElementById("factNo")
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webtype_findPageBean2",
			data : "factNo="+factno.value,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
 function mydelete(factNo,typeNo){
    var flag=confirm("警告,刪除類別,將同時刪除該類別的所有函文及流程,確定要刪除嗎?");
    if(flag==true){   	
       jq.ajax({
    	   type:"POST",
    	   dataType:"html",
    	   data:"factNo="+factNo+"&typeNo="+typeNo,
    	   url:"webtype_delete",
    	   success:function(data){
    		   jq("#bodyid").html(data);
    	   },
    	   error:function(err){
    		   jq("#bodyid").html(err.responseText);
    	   }
       });
    }
}
function findById(factno,typeno){	
	loadUrl("/Login/webtype_findById?factNo="+factno+"&typeNo="+typeno);
}
function recovery(subform){
	jq.ajax({
		type:"POST",
		dataType:"json",
		data:jq("#"+subform).serialize(),
		url:"webtype_recovery",
		success:function(data){
			if(data=="0"){
				//location.href="webtype_findPageBean3";
				layer.msg("回收成功",3,1);
			}else{
				layer.msg("回收失敗",3,3);
			}
		},
		error:function(error){
			alert(error.responseText);
		}
	});
	
}
</script>	
</body>
</html>
