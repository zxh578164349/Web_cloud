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

<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>

 <!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->
</head>

<script>
	var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load(0);
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
	function pages(page) {	
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "bussletter_findPageBean3",
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
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "bussletter_findPageBean2",
			data : jq("#search_form").serialize(),
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
		jConfirm('你确定这么做吗?', '确认对话框', function(r) {
			if (r == true) {			
				//document.getElementById(mid).submit();								
				jq.ajax({
				   type:"POST",
				   dataType:"html",
				   data:jq('#'+mid).serialize(),
				   url:"bussletter_delete",
				   success:function(msg){
				      jq("#bodyid").html(msg);
				   },
				   error:function(xhr){
				      jq("#bodyid").html(xhr.responseText);
				   }
				});
			}
		});
	}
</script>




<body>

	<jsp:include page="publicHead_webbussletter.jsp" flush="true" />
	<hr />
	<s:if test='#session.loginUser.userread!="1"'>
	<input value="添加" type="button" id="addbtn"
		onclick="javascript:layer.load('請稍等');location.href='saveAndUpdate/webbussletterSaveOrUpdate.jsp'" /></s:if>
		<!-- <input type="image" src="images/add001.gif" onclick="javascript:location.href='saveAndUpdate/matterApplicationSaveOrUpdate.jsp'"> -->
	<div id="bodyid">
		<jsp:include page="table1/webbussletter1.jsp" />
	</div>

	


</body>
</html>
