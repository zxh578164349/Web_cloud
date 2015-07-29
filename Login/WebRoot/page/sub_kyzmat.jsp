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
	    var loadi=layer.load(0);
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzmat_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
			    layer.close(loadi);
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
	function submis() {
		var loadi=layer.load(0);
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
			url : "kyzmat_findPageBean2",
			data : "fromDate=" + begindate.value +"& endDate="+enddate.value+"& matNo="+matNo.value
			        +"& typeBno="+bNo.value+"& typeMno="+mNo.value+"& typeSno="+sNo.value
			        +"& matCname="+matcname.value,
			success : function(msg) {
			    layer.close(loadi);
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
    function showDiv(matno){   
    jq.layer({
    type: 1,   //0-4的选择,
    title: '物料資料所屬廠別',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    moveOut:false,
    moveType:1,    
    offset:['50px','200px'],
    area: ['500px', '300px'],
    page:{
      url:'subkyzmat_findfactnoByMatno?matNo='+matno
    }               
});
} 
function selectAll(){
  var cb_all=document.getElementById("cb_all");
  var cbs=jq("[name='cb_list']");
  if(cb_all.checked==true){
      for(var i=0;i<cbs.length;i++){
          cbs[i].checked=true;
      }
  }else{
      for(var i=0;i<cbs.length;i++){
          cbs[i].checked=false;
      }
  }
  }
  
  function backKyzmat(){
     layer.load("正在返回物料資料頁面....");
     window.location.href='subkyzmat_findPageBean';
  }
    		     
</script>

<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script> 	
<script type="text/javascript">
	//你确定要删除吗？	
	function isDelete(matNo) {
		jConfirm('你确定这么做吗?', '确认对话框', function(r) {
			if (r == true) {
				window.location.href = "kyzmat_delete?matNo=" + matNo; 
				//document.getElementById(mid).submit();
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
		<s:if test='#session.loginUser.userread!="1"'>
		<input value="添加" type="button" id="addbtn"
		onclick="javascript:location.href='saveAndUpdate/kyzmatSaveOrUpdate.jsp'" /></s:if>
		<input value="返回物料資料" type="button" id="search_forday" onclick="backKyzmat()"/>	
	<div id="bodyid">
		<jsp:include page="table1/sub_kyzmat1.jsp" />
	</div>

	


</body>
</html>
