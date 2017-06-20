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

	<jsp:include page="publicHead_kyzsec.jsp" flush="true" />
	<hr />
		
	<div id="bodyid">
		<jsp:include page="table1/kyzsec1.jsp" />
	</div>

<script>

	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzsec_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
		function submis() {
		var fact = document.getElementById("factNo");
		var secno=document.getElementById("secNo");
		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzsec_findPageBean2",
			data : "factNo=" + fact.value +"& secNo="+secno.value,
			success : function(msg) {
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
/*function showDiv(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '費用組別',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    offset:['100px',''],
    area: ['750px', '300px'],
    page:{
      url:'saveAndUpdate/kyzsecSaveOrUpdate.jsp'   
    }
            
});
    }

function isDelete(mid) {
	var flag=confirm("確定要刪除嗎?");		
		if (flag == true) {
			document.getElementById(mid).submit();
			jq({
				type:"POST",
				dataType:"html",
				data:$("#"+mid).serialize(),
				url:"kyzsec_delete",
				success:function(data){
					jq("#bodyid").html(data);
				},
				error:function(error){
					jq("#bodyid").html(error.responseText);
				}
			});
		}
	
}*/
</script>	


</body>
</html>
