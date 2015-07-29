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
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>

</head>

<script>
   var jq=jQuery.noConflict();
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzpetty_findPageBean3",
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
		var loadi=layer.load(0);
		var factno = document.getElementById("factNo");
		var yymm=document.getElementById("year");
		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzpetty_findPageBean2",
			data : "factNo=" + factno.value +"& yymm="+yymm.value,
			success : function(msg) {
			    layer.close(loadi);
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
function showDiv(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '零用金支出',
    //title:false,
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    //move:'.testdiv',
    moveOut:false,
    moveType:1,
    offset:['10px','200px'],
    area: ['790px', '680px'],
    page:{
      url:'kyzpetty_findAllSecNoAndAcctNo'   
    }
            
});
    }
    
    function showDiv2(factno,billno){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '零用金支出',
    //title:false,
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    //move:'.testdiv',
    moveOut:false,
    moveType:1,
    offset:['50px','200px'],
    area: ['790px', '680px'],
    page:{
      url:'kyzpetty_findById?factNo='+factno+'& billNo='+billno   
    }
            
});
    }
    
    function showDiv_print(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '零用金支出報表',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    moveOut:false,
    moveType:0,
    offset:['50px','200px'],
    area: ['600px', '200px'],
    page:{
      url:'search/kyzpetty_search_print.jsp'   
    },
    close:function(){
       window.location.href='kyzpetty_findPageBean';
    }
            
});
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
	<jsp:include page="publicHead.jsp"/>
	<hr>	
	<!-- <input value="添加" type="button" id="addbtn"
		onclick="javascript:location.href='saveAndUpdate/kyzacctSaveOrUpdate.jsp'" /> -->
		<s:if test='#session.loginUser.userread!="1"'>
		<input value="添加" type="button" id="addbtn"
		onclick="javascript:showDiv()" /></s:if>
		<br>
	<span style="float:right;padding-bottom:0"> <img alt="" src="images/136.gif"><a
		href="javascript:showDiv_print()" 
		style="color:blue;text-decoration:underline;float:right;padding-right:30px">零用金報表打印</a>
	</span>	
	<div id="bodyid">
		<jsp:include page="table1/kyzpetty1.jsp" />
	</div>

	


</body>
</html>
