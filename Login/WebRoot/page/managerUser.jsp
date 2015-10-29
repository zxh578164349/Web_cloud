<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>My JSP 'managerUser.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

 <LINK href="css/list.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>	
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />	
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
</head>
<script type="text/javascript">
	var jq=jQuery.noConflict();
	function pages(page) {
	    var loadi=layer.load(0);
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "userfindPageBean3",
			data : "page=" + page,
			success : function(msg) {
			    layer.close(loadi);
				jq("#bodyid").html(msg);
				/* jq("li").removeClass("active");
				jq("li").children("a").click(function(){jq(this).parent().attr("class","active")}); */
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}

	function submis() {
	    var loadi=layer.load(0);		
		var username = document.getElementById("conditions");
		var factno=document.getElementById("factNo")
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "userfindPageBean2",
			data : "conditions=" + username.value+"&factNo="+factno.value,
			success : function(msg) {
			    layer.close(loadi);
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
var defaultColor="#97CBFF";
var clickColor="#CCFFFF";
function click_color(obj){
        var tbody=document.getElementById("tbody");
        var length=document.getElementById("tbody").rows.length;
        for(var i=0;i<length;i++){
            tbody.rows[i].style.backgroundColor=defaultColor;
        }
        obj.style.backgroundColor=clickColor;        
    }
    
function move(obj){
     obj.style.backgroundColor=defaultColor;
    }
   
/* jq(document).keyup(function(event){
    if(event.keyCode==13){
       submis();
    }
})  */
 
 function mydelete(id){
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){
       window.location.href="userdelete?id="+id;
       layer.load("正在處理,請稍後....");
    }
}

function loadjur(id,factNo){
    layer.load("請稍等...");
    window.location.href="userjurisdiction?id="+id+"&fact="+factNo;
} 
</script>

<body>
	<jsp:include page="publicHead_webuser.jsp" />
	<hr />
	<s:if test='#session.loginUser.userread!="1"'>	
		<input value="添加新用戶" type="button" id="search_forday" onclick="javascript:location.href='saveAndUpdate/webuserSaveOrUpdate.jsp'"/>
	</s:if>	
	<div id="bodyid">
		<jsp:include page="table1/managerUser1.jsp" />
	</div>
</body>
</html>
