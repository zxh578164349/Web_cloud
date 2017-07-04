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
	<jsp:include page="publicHead_kpifact.jsp" flush="true" />
	<hr />
	

	<div id="bodyid">
		<jsp:include page="table1/kpifact1.jsp" />
	</div>
	
<script>
	
	function pages(page) {	
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kpifact_findPageBean3",
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
			url : "kpifact_findPageBean2",
			data : "factNo=" + fact.value + "&yyyy=" + ym.value,
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
					url:"kpifact_delete",
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
		var public_form=jq("#"+public_form);
		public_form.attr("action","kpifact_print");
		public_form.attr("target","_blank");
	    public_form.submit();
	    
	}
</script>	
</body>
</html>
