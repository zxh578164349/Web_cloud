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
		<jsp:include page="table1/web_br_productitem1.jsp"/>
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
			jq("#bodyid").html(xhr.responseText);
		}
	});
}
function submis(public_form) {	
	var subform=jq("#"+public_form);
	jq.ajax({
		type : "POST",
		dataType : "Html",
		url : "webbrpro_findPageBean2",		
		data:subform.serialize(),
		success : function(msg) {
			jq("#bodyid").html(msg);
		},
		error : function(xhr) {
			jq("#bodyid").html(xhr.responseText);
		}
	});
}
function print(subform,action){
	var subform=jq("#"+subform);
	subform.attr("action",action);
	subform.attr("target","_blank");
	if(jq("#factNo").val()==""||(jq("input[name='yymmdd']").val()==""&&jq("input[name='yymmdd2']").val()=="")){
		layer.alert("請選擇廠別和截止日期");
		return false;
	}else{
		subform.submit();
	}	
}



</script>	
</body>
</html>
