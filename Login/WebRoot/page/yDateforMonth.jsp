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
<title>My JSP 'kongweishu.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>


<body>
	<jsp:include page="publicHead.jsp" flush="true" />
	<hr />
	
		<span style="float:right"><input type="button"
		onclick="findPageBean('ydata_findPageBean')" 
		class="btn btn-link btn-sm" value="按日詳細查看"/>
	</span>

	<div id="bodyid">
		<jsp:include page="table1/yDateforMonth1.jsp" />
	</div>
	
<script>	
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "ydata_findPageBean3ForMonth",
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
		var fact = document.getElementById("factNo");
		var ym = document.getElementById("year");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "ydata_findPageBean2ForMonth",
			data : "factNo=" + fact.value + "&yymm=" + ym.value,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}

</script>	
</body>
</html>
