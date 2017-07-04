<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webuseremail_findPageBean2",
			//data : "email=" + email.value+"&factNo="+factno.value,
			data:jq("#subform").serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	
 
 function mydelete(factNo,email,emailpwd,typeMk){
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){           
       jq.ajax({
          type:"POST",
          dataType:"html",
          data:"factNo="+factNo+"&email="+email+"&emailpwd="+emailpwd+"&typeMk="+typeMk,
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
function findById(factno,email,emailpwd,typeMk){
	loadUrl("webuseremail_findById?factNo="+factno+"&email="+email+"&emailpwd="+emailpwd+"&typeMk="+typeMk);
}

</script>	
</body>
</html>
