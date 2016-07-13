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
<script type="text/javascript">
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
	loadUrl("webuseremaila_findById?factNo="+factno+"&email="+email+"&emailPwd="+emailpwd+"&visaSort="+visasort);
}

</script>
<script type='text/javascript' src='/Login/dwr/interface/webuseremailajs.js'></script>
<!--  <script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>-->
<body>
	<jsp:include page="publicHead_webuseremail_A.jsp" />
	<hr />
	
	<div id="bodyid">
		<jsp:include page="table1/managerUserEmail1_A.jsp" />
	</div>
</body>
</html>
