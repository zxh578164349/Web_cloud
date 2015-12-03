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
	    	loadi=layer.load("正在處理,請稍後....");
	    });
	    jq(document).ajaxStop(function(){
	    	layer.close(loadi);
	    });
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webuseremaila_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}

	function submis() {		
		var email = document.getElementById("email");
		var factno=document.getElementById("factNo");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webuseremaila_findPageBean2",
			data : "email=" + email.value+"&factNo="+factno.value,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
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
 
 function mydelete(factNo,email,emailpwd,visaSort){
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){
       //window.location.href="webuseremaila_delete?factNo="+factNo+"&email="+email+"&emailPwd="+emailpwd+"&visaSort="+visaSort;     
       //layer.load("正在處理,請稍後....");
       jq.ajax({
    	   type:"POST",
    	   dataType:"html",
    	   data:"factNo="+factNo+"&email="+email+"&emailPwd="+emailpwd+"&visaSort="+visaSort,
    	   url:"webuseremaila_delete",
    	   success:function(data){
    		   jq("#bodyid").html(data);
    	   },
    	   error:function(data){
    		   jq("#bodyid").html(data.responseText);
    	   }
       });
    }
}
function findById(factno,email,emailpwd,visasort){
	location.href="webuseremaila_findById?factNo="+factno+"&email="+email+"&emailPwd="+emailpwd+"&visaSort="+visasort;
	layer.load("正在跳轉,請稍等...");
}

</script>
<script type='text/javascript' src='/Login/dwr/interface/webuseremailajs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
<body>
	<jsp:include page="publicHead_webuseremail_A.jsp" />
	<hr />
	<s:if test='#session.loginUser.userread!="1"'>	
		<input value="添加知會人" type="button" id="search_forday" onclick="javascript:location.href='saveAndUpdate/webuseremailaSaveOrUpdate.jsp'"/>
	</s:if>	
	<div id="bodyid">
		<jsp:include page="table1/managerUserEmail1_A.jsp" />
	</div>
</body>
</html>
