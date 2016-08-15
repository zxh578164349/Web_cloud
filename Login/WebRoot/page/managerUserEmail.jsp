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
	<jsp:include page="publicHead_webuseremail.jsp" />
	<hr />
	
	<div id="bodyid">
		<jsp:include page="table1/managerUserEmail1.jsp" />
	</div>
	
<script type="text/javascript">
	
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webuseremail_findPageBean3",
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
		var factno=document.getElementById("factNo")
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webuseremail_findPageBean2",
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
	
 
 function mydelete(factNo,email,emailpwd){
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){           
       jq.ajax({
          type:"POST",
          dataType:"html",
          data:"factNo="+factNo+"&email="+email+"&emailpwd="+emailpwd,
          url:"webuseremail_delete",
          success:function(msg){
             jq("#bodyid").html(msg);
          },
          error:function(xhr){
             jq("#bodyid").html(xhr.responseText);
          }
       });
    }
}
function findById(factno,email,emailpwd){
	loadUrl("webuseremail_findById?factNo="+factno+"&email="+email+"&emailpwd="+emailpwd);
}

</script>	
</body>
</html>
