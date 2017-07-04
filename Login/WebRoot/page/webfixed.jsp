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
	<jsp:include page="publicHead_webfix.jsp" />
	<hr/>
	
	<div id="bodyid">
		<jsp:include page="table1/webfixed1.jsp" />
	</div>	
	
	
<script>
	
	function pages(page) {	    		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "fix_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("bodyid").html(xhr.responseText);
			}
		});
	}
	function submis(public_form) {		
		var fact = document.getElementById("factNo");
		var ym = document.getElementById("year");
		var ym_s=document.getElementById("year_s");
		var lostmk=document.getElementById("lostmk");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "fix_findPageBean2",
			//data : "factNo=" + fact.value + "&yymm=" + ym.value+"&yymm_s="+ym_s.value+"&lostmk="+lostmk.value,
			data:jq("#"+public_form).serialize(),
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
			if (flag == true) {
				//document.getElementById("mydiv").style.display = "block";
				//location.href = "fix_delete?id=" + mid;
				jq.ajax({
					type:"POST",
					dataType:"html",
					data:"id="+mid,
					url:"fix_delete",
					success:function(data){
						jq("#bodyid").html(data);
					},
					error:function(data){
						jq("#bodyid").html(data.responseText);
					}
				});
			}
		
	}


function print(public_form){
	var public_form=jq("#"+public_form);
	public_form.attr("action","fix_toExcel");
	public_form.attr("target","_blank");
	public_form.submit();
}
</script>		
</body>
</html>
