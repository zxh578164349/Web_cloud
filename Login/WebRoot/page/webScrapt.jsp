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
	<jsp:include page="publicHead_print.jsp" />
	<hr />
	
	<div id="bodyid">
		<jsp:include page="table1/webScrapt1.jsp" />
	</div>
	
<script>
	
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webScrapt_getList3",
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
			url : "webScrapt_getList2",
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
	//你确定要删除吗？
	/*function isDelete(mid) {
		 var flag=confirm("確定要刪除嗎?");		
			if (flag == true) {
				//document.getElementById(mid).submit();
				jq.ajax({
					type:"POST",
					dataType:"html",
					data:jq("#"+mid).serialize(),
					url:"webScrapt_delete2",
					success:function(data){
						jq("#bodyid").html(data);
					},
					error:function(data){
						jq("#bodyid").html(data.responseText);
					}
				});
			}
		
	}*/
	function print(public_form){
		var subform=jq("#"+public_form);
		subform.attr("action","webScrapt_print");
		subform.attr("target","_blank");
		subform.submit();
	}
</script>	
</body>
</html>
