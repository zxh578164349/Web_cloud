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

 <LINK href="css/list.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>	
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />	
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
</head>
<script type="text/javascript">
	var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load(0);
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
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
	
var defaultColor="#97CBFF";
var clickColor="#CCFFFF";
function click_color(obj){
        var tbody=document.getElementById("tbody");
        var length=document.getElementById("tbody").rows.length;
        for(var i=0;i<length;i++){
            tbody.rows[i].style.backgroundColor=defaultColor;
        }
        obj.style.backgroundColor=clickColor;        
    }
    
function move(obj){
     obj.style.backgroundColor=defaultColor;
    }
   
/* jq(document).keyup(function(event){
    if(event.keyCode==13){
       submis();
    }
})  */
 
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
	layer.load(0);
	location.href="/Login/webtype_findById?factNo="+factno+"&typeNo="+typeno;
}
</script>

<body>
	<jsp:include page="publicHead_webtype.jsp" />
	<hr />
	<s:if test='#session.loginUser.userread!="1"'>	
		<input value="添加簽核類別" type="button" id="search_forday" onclick="javascript:location.href='saveAndUpdate/webTypeSaveOrUpdate.jsp'"/>
	</s:if>	
	<div id="bodyid">
		<jsp:include page="table1/webtype1.jsp" />
	</div>
</body>
</html>
