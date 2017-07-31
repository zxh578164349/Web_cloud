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
	<jsp:include page="publicHead_web_br_productitem.jsp" />
	<hr />
		
	<div id="bodyid">
		<jsp:include page="table1/web_br_proAndest1.jsp"/>
	</div>
	
<script>
function pages(page) {	
	jq.ajax({
		type : "POST",
		dataType : "Html",
		url : "webbrpro_findPageBean3",
		data : "page=" + page ,
		success : function(msg) {
			jq("#bodyid").html(msg);
		},
		error : function(xhr) {
			//alert(xhr.responseText);
			jq("#bodyid").html(xhr.responseText);
		}
	});
}
function submis(public_form) {
	var fact = document.getElementById("factNo");
	var ym = document.getElementById("year");
	var subform=jq("#"+public_form);
	jq.ajax({
		type : "POST",
		dataType : "Html",
		url : "webbrpro_findPageBean2",
		//data : "factNo=" + fact.value + "&yymm=" + ym.value,
		data:subform.serialize(),
		success : function(msg) {
			jq("#bodyid").html(msg);
		},
		error : function(xhr) {
			//alert(xhr.responseText);
			jq("#bodyid").html(xhr.responseText);
		}
	});
}
function print(subform){
	var subform=jq("#"+subform);
	subform.attr("action","webbrpro_printlist");
	subform.attr("target","_blank");
	subform.submit();
}






</script>	
</body>
</html>
