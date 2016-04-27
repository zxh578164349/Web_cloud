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

<script>

	function pages(page) {	
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webremit_findPageBean3",
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
		var visasort = document.getElementById("visaSort");
		var billno=document.getElementById("billNo");
		var yymmdd=document.getElementById("yymmdd");
		var yymmdd2=document.getElementById("yymmdd2");
		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webremit_findPageBean2",
			data : "factNo=" + fact.value + "& visaSort=" + visasort.value+"& billNo="+billno.value+"& yymmdd="+yymmdd.value+"& yymmdd2="+yymmdd2.value,
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
	function isDelete(mid) {
	    var flag=confirm("確定要刪除嗎?");
		
			if (flag == true) {//if			   
				jq.ajax({
				  type:"POST",
				  dataType:"Html", 
				  url:"webremit_delete",
				  data:jq('#'+mid).serialize(),
				  success : function(msg) {
				  jq("#bodyid").html(msg);
			      },
			      error : function(xhr) {
				     jq("#bodyid").html(xhr.responseText);
			      }
				 });
				//document.getElementById(mid).submit();
			}//if
		
	}
</script>




<body>

	<jsp:include page="publicHead_matterApplication.jsp" flush="true" />
	<hr />
			
	<div id="bodyid">
		<jsp:include page="table1/webremittancelist1.jsp" />
	</div>

	


</body>
</html>
