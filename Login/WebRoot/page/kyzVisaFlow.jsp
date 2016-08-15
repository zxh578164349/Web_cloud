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

	<jsp:include page="publicHead_visaFlow.jsp" flush="true" />
	<hr />
			
	<div id="bodyid">
		<jsp:include page="table1/kyzVisaFlow1.jsp" />
	</div>
	
<script>
 
	function pages(page) {	    
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "visaflow_findPageBean3",
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
		var ym = document.getElementById("visaSort");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "visaflow_findPageBean2",
			data : "factNo=" + fact.value + "&visaSort=" + ym.value,
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
					url:"visaflow_delete",
					data:jq("#"+mid).serialize(),
					success:function(data){
						jq("#bodyid").html(data);						
					},
					error:function(data){
						jq("#bodyid").html(data.responseText);
					}
				});
			}
		
	}*/
	
	function isDelete2(factno,visasort) {
		var flag=confirm("刪除申請人,就會刪除整個流程,确定要刪除吗?");
			if (flag == true) {								
				jq.ajax({
					type : "POST",
					dataType : "Html",
					url : "visaflow_deleteFirst",
					data : "factNo=" + factno + "&visaSort=" + visasort,
					success : function(msg) {
						jq("#bodyid").html(msg);
					},
					error : function(xhr) {
						//alert(xhr.responseText);
						jq("#bodyid").html(xhr.responseText);
					}
				}); 
			}
		
	}
 
</script>
</body>
</html>
