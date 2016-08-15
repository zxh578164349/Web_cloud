<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>My JSP 'kongweishu.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<jsp:include page="publicHead_kyzacct.jsp"/>
	<hr>		
			
	<div id="bodyid">
		<jsp:include page="table1/kyzacct1.jsp" />
	</div>

	
<script>

	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzacct_findPageBean3",
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
		var acctno = document.getElementById("acctno");
		var acctname=document.getElementById("acctname");
		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzacct_findPageBean2",
			data : "acctNo=" + acctno.value +"& acctName="+acctname.value,
			success : function(msg) {
				jq("#bodyid").html(msg);

			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
	/*function isDelete(mid) {
	    var flag=confirm("確定要刪除嗎?");		
			if (flag == true) {				
				jq.ajax({
					type:"POST",
					dataType:"html",
					data:jq("#"+mid).serialize(),
					url:"kyzacct_delete",
					success:function(data){
						jq("#bodyid").html(data);
					},
					error:function(error){
						jq("#bodyid").html(error.responseText);
					}
				});
			}
		
	}*/
</script>

</body>
</html>
