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
	<br><jsp:include page="publicHead_webemailall.jsp" />
	<hr />
		
	<div id="bodyid">
		<jsp:include page="table1/webemailall1.jsp" />
	</div>
	
<script type="text/javascript">
	
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webemailall_findPageBean3",
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
			url : "webemailall_findPageBean2",
			data : jq("#public_form").serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
 function mydelete(rid){
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){   	
       jq.ajax({
    	   type:"POST",
    	   dataType:"html",
    	   data:{rid:rid},
    	   url:"webemailall_delete",
    	   success:function(data){
    		   jq("#bodyid").html(data);
    	   },
    	   error:function(err){
    		   jq("#bodyid").html(err.responseText);
    	   }
       });
    }
}
function findById(eid){	
	loadUrl("webemailall_findById?eid="+eid);
}
/*設置郵箱不可用*/
function goDisabled(eid){
var flag=confirm("確定要刪除嗎?");
if(flag==true){
 jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webemailall_goDisabled",
			data : {"eid":eid},
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {				
				jq("#bodyid").html(xhr.responseText);				
			}
		});
} 
}

 function mydelete(eid,email,ename){ 
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){   	
       jq.ajax({
    	   type:"POST",
    	   dataType:"html",
    	   data:{eid:eid,email:email,ename:ename},
    	   url:"webemailall_delete",
    	   success:function(data){
    		   jq("#bodyid").html(data);
    	   },
    	   error:function(err){
    		   jq("#bodyid").html(err.responseText);
    	   }
       });
    }
}


/*加載所有類型*/
jq.ajax({
	type:"get",
	url:"webemailall_findAllEmailTypes",
	dataType:"json",
	success:function(data){
		jq("#sel_emailtypes").empty();
		jq("#sel_emailtypes").append("<option value=''>類型選擇</option>");
		var item="";
		jq.each(data,function(i,obj){
			item+="<option value='"+obj.eid+"'>"+obj.ename+"</option>";					
		});
		jq("#sel_emailtypes").append(item);
	}
});


</script>	
</body>
</html>
