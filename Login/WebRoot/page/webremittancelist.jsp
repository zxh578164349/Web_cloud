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

	<jsp:include page="publicHead_matterApplication.jsp" flush="true" />
	<hr />
			
	<div id="bodyid">
		<jsp:include page="table1/webremittancelist1.jsp" />
	</div>

	

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
	/*function isDelete(mid) {
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
		
	}*/
	
function showDivList(billNo){	    	
	    jq.layer({
	    type: 2,   //0-4的选择,
	    title: '費用清單',
	    closeBtn: [1,true],
	    shade: [0],
	    shadeClose: false,
	    border: [10, 0.3, '#000'],	   
	    offset:['10px',''],
	    area:['600px','560px'],
	    //page:{url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo}                   
	    iframe:{src:'webremit_findById_layer2?billNo='+billNo,scrolling:'auto'}		                   
	});
	    }	
</script>
</body>
</html>
