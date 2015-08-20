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

	
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
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
	function pages(page) {
	
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "vbm_findPageBean3",
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
		/* jQuery(function(jq) {
			 jq(document).ui_loading({
				overlay : true,
				opacity : 0.2,
				supportIframe : true,
				message : '請稍後!正在查詢中..'
			});			
		}); */
		var loadi=layer.load(0);
		var fact = document.getElementById("factNo");
		var billno=document.getElementById("billNo");
		var visaMk=document.getElementById("visaMk");
		var visaSort=document.getElementById("dwr_kytype");
		var yymmdd=document.getElementById("yymmdd");
		var yymmdd2=document.getElementById("yymmdd2");

		if(visaMk.checked==false){
		   visaMk.value="";
		}else{
		   visaMk.value="N";
		}				
		 jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "vbm_findPageBean2",
			data : "factNo=" + fact.value +"& billNo="+billno.value+"& visaMk="+visaMk.value+"& visaSort="+visaSort.value+"& yymmdd="+yymmdd.value+"& yymmdd2="+yymmdd2.value,
			 success : function(msg) {
			    layer.close(loadi);			    
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			} 			
		}); 
		//jq.post("vbm_findPageBean2",{"factNo":fact.value,"billNo":billno.value,"visaMk":visaMk.value},function(data){jq("#bodyid").html(data)});
	}
	
	
	//你确定要删除吗？
	function isDelete(mid) {
		jConfirm('你确定这么做吗?', '确认对话框', function(r) {
			if (r == true) {
				/* window.location.href = "backmat_delete?billNo=" + mid; */
				document.getElementById(mid).submit();
			}
		});
	}
	
	function check(visaSort,billNo,factNo){
	     jq.layer({
    type: 1,   //0-4的选择,
    title: '函文內容',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    //shade: [0.5, '#000'],
    shadeClose: false,
    border: [10, 0.3, '#000'],
   // btns:1,
    //fadeIn:300,
    shift:'top',
    offset:['40px',''],
    area: ['800px', '560px'],
    page:{
      url:'vbm_findById?visaSort='+visaSort+'&billNo='+billNo+'&factNo='+factNo    
    }                 
});
	}
</script>




<body>

	<jsp:include page="publicHead_kybillm.jsp" flush="true" />
	<hr />
	<!-- <input value="添加" type="button"
		onclick="javascript:location.href='saveAndUpdate/kyzVisaFlowSaveOrUpdate.jsp'" /> -->	
	<div id="bodyid">
		<jsp:include page="table1/visabillm1.jsp" />
	</div>

	


</body>
</html>
