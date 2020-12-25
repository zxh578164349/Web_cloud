<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>My JSP 'managerUser.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>


<body>
	<jsp:include page="publicHead_webuser.jsp" />
	<hr />
	
	<div id="bodyid">
		<jsp:include page="table1/managerUser1.jsp" />
	</div>
	
<script type="text/javascript">
	
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "userfindPageBean3",
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

	function submis(subform) {				
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "userfindPageBean2",
			//data : "conditions=" + username.value+"&factNo="+factno.value,
			data:jq("#"+subform).serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
 
 function mydelete(id){
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){
       /* window.location.href="userdelete?id="+id;
       var loadi=layer.load("正在處理,請稍後...."); */    
       jq.ajax({
          type:"POST",
          dataType:"html",
          data:"id="+id,
          url:"userdelete",
          success:function(msg){
              //layer.close(loadi);
              jq("#bodyid").html(msg);
          },
          error:function(xhr){
              alert(xhr.reponseText);
          }
       });
    }
}

function loadjur(id,factNo){  
    loadUrl("userjurisdiction?id="+id+"&fact="+factNo);
}
function loaduser(id){	
	loadUrl("userinitialUpdate?id="+id);
}

function sendEmail(subform){
    var flag=confirm("確定要发送邮件嗎?");
    if(flag==true){         
       jq.ajax({
          type:"POST",
          dataType:"json",
          data: jq("#" + subform).serialize(),
          url:"usersendWeeklyreport",
          success:function(data){
              if(data=="1"){
                layer.msg("发送成功", 3, 1);
              }else{
                layer.msg("发送失敗", 3, 3);
              }
          },
          error:function(xhr){
              alert(xhr.reponseText);
          }
       });
    }
}
</script>	
</body>
</html>
