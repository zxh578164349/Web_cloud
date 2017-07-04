<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">	
</head>



<body>

	<jsp:include page="publicHead_kyzsec.jsp" flush="true" />
	<hr />
		
	<div id="bodyid">
		<jsp:include page="table1/kyzsec1.jsp" />
	</div>

<script type="text/javascript">

	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzsec_findPageBean3",
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
		var fact = document.getElementById("factNo");
		var secno=document.getElementById("secNo");
		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzsec_findPageBean2",
			data : "factNo=" + fact.value +"& secNo="+secno.value,
			success : function(msg) {
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
</script>	


</body>
</html>
