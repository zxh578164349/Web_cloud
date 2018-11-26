<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
Calendar cal=Calendar.getInstance();
cal.setFirstDayOfWeek(Calendar.MONDAY);
cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//本周星期一日期
String sdate=sdf.format(cal.getTime());
 %>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'managerUser.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

 
</head>


<body>
	<br><jsp:include page="publicHead_webweeklyreport.jsp" />
	<hr />
		
	<div id="bodyid">
		<jsp:include page="table1/webnewproduct1.jsp" />
	</div>
	
<script type="text/javascript">
	
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webnewpro_findPageBean3",
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
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webnewpro_findPageBean2",
			data : jq("#public_form").serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
 function mydelete(billNo){
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){   	
       jq.ajax({
    	   type:"POST",
    	   dataType:"html",
    	   data:{billNo:billNo},
    	   url:"webnewpro_delete",
    	   success:function(data){
    		   jq("#bodyid").html(data);
    	   },
    	   error:function(err){
    		   jq("#bodyid").html(err.responseText);
    	   }
       });
    }
}
function findById(billNo){	
	loadUrl("webnewpro_findByBillNo?billNo="+billNo);
}
function recovery(subform){
	jq.ajax({
		type:"POST",
		dataType:"json",
		data:jq("#"+subform).serialize(),
		url:"webnewpro_recovery",
		success:function(data){
			if(data=="0"){
				//location.href="webnewpro_findPageBean3";
				layer.msg("回收成功",3,1);
			}else{
				layer.msg("回收失敗",3,3);
			}
		},
		error:function(error){
			alert(error.responseText);
		}
	});
	
}


</script>	
</body>
</html>
