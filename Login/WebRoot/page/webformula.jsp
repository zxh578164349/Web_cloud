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
	<jsp:include page="publicHead_webformula.jsp" />
	<hr />
		
	<div id="bodyid">
		<jsp:include page="table1/webformula1.jsp" />
	</div>
	
<script>
function pages(page) {	
	jq.ajax({
		type : "POST",
		dataType : "Html",
		url : "webformula_findPageBean3",
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
		url : "webformula_findPageBean2",
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
	subform.attr("action","webformula_printlist");
	subform.attr("target","_blank");
	subform.submit();
}


jq.ajax({
	type:"get",
	dataType:"json",
	url:"webfact_findAllVwebfact",
	success:function(data){
		var item;
		jq("#factno").empty();
		jq("#factno").append("<option value=''>請選擇廠別</option>");
		jq.each(data,function(i,obj){
			item="<option value='"+obj[0]+"'>"+obj[1]+"("+obj[3]+")</option>";
			jq("#factno").append(item);
		});
	}
});
jq.ajax({
	type:"post",
	dataType:"json",
	url:"weberpbp_findObjOp1",
	success:function(data){
		var item;
		jq("#factcode").empty();
		jq("#factcode").append("<option value=''>請選擇製程類別</option>");
		jq.each(data,function(i,obj){
			item="<option value='"+obj[0]+"'>"+obj[2]+"</option>";
			jq("#factcode").append(item);
		});
	}
});
/*加載所有品牌*/
jq.ajax({
	type:"get",
	url:"weberpbp_findObjOp2",
	dataType:"json",
	success:function(data){
		jq("#dwrWebbrank").empty();
		jq("#dwrWebbrank").append("<option value=''>品牌選擇</option>");
		var item="";
		jq.each(data,function(i,obj){
			item+="<option value='"+obj[0]+"'>"+obj[2]+"</option>";					
		});
		jq("#dwrWebbrank").append(item);
	}
});

</script>	
</body>
</html>
