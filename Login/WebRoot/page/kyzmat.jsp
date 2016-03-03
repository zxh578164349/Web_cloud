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



<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<!-- <link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" /> -->
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

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
			url : "subkyzmat_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
	function submis(public_form) {
		var begindate = document.getElementById("beginDate");
		var enddate=document.getElementById("endDate");
		var matNo=document.getElementById("matNo");
		var bNo=document.getElementById("bNo");
		var mNo=document.getElementById("mNo");
		var sNo=document.getElementById("sNo");
		var matcname=document.getElementById("matcname");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "subkyzmat_findPageBean2",
			/*data : "fromDate=" + begindate.value +"& endDate="+enddate.value+"& matNo="+matNo.value
			        +"& typeBno="+bNo.value+"& typeMno="+mNo.value+"& typeSno="+sNo.value
			        +"& matCname="+matcname.value,*/
			  data:jq("#"+public_form).serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
	/*function print() {
		var loadi=layer.load("正在生成....");
		var begindate = document.getElementById("beginDate");
		var enddate=document.getElementById("endDate");
		var matNo=document.getElementById("matNo");
		var bNo=document.getElementById("bNo");
		var mNo=document.getElementById("mNo");
		var sNo=document.getElementById("sNo");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzmat_print",
			data : "fromDate=" + begindate.value +"& endDate="+enddate.value+"& matNo="+matNo.value
			        +"& typeBno="+bNo.value+"& typeMno="+mNo.value+"& typeSno="+sNo.value,
			success : function() {
			    layer.close(loadi);
				//jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}*/
	      

    function gotoSubmat(){
      layer.load("正在跳轉物料資料管理頁面.....");
      window.location.href='kyzmat_findPageBean';
     
      
    }
    function print(public_form){
    	var public_form=jq("#"+public_form);
    	public_form.attr("action","kyzmat_print");
    	public_form.attr("target","_blank");
    	public_form.submit();
    }
    
</script>

<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script> 	
<script type="text/javascript">
	//你确定要删除吗？	
	function isDelete(mid) {
		jConfirm('你确定这么做吗?', '确认对话框', function(r) {
			if (r == true) {
				/* window.location.href = "backmat_delete?billNo=" + mid; */
				document.getElementById(mid).submit();
			}
		});
	}
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzsecjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzacctjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzpettyjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>




<body>
	<jsp:include page="publicHead_kyzmat.jsp"/>
	<hr>		
		
		<!--  <input value="物料資料管理" type="button" id="search_forday" onclick="gotoSubmat()"/>-->
		<span style="float:right"> <input type="button"
		onclick="gotoSubmat()"
		class="btn btn-link btn-sm" value="物料資料管理"/>
	    </span>	
	<div id="bodyid">
		<jsp:include page="table1/kyzmat1.jsp" />
	</div>

	


</body>
</html>
