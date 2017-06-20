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

	<jsp:include page="publicHead_webbussletter.jsp" flush="true" />
	<hr />
		
	<div id="bodyid">
		<jsp:include page="table1/webbussletter1.jsp" />
	</div>

	

<script>
	
	function pages(page) {	
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "bussletter_findPageBean3",
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
			url : "bussletter_findPageBean2",
			data : jq("#search_form").serialize(),
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
		var flag=confirm("確定要刪除嗎？");		
			if (flag == true) {			
				//document.getElementById(mid).submit();								
				jq.ajax({
				   type:"POST",
				   dataType:"html",
				   data:jq('#'+mid).serialize(),
				   url:"bussletter_delete",
				   success:function(msg){
				      jq("#bodyid").html(msg);
				   },
				   error:function(xhr){
				      jq("#bodyid").html(xhr.responseText);
				   }
				});
			}
		
	}*/
</script>
</body>
</html>
