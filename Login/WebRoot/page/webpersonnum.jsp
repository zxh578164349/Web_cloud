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
			url : "webpersonnum_findPageBean3",
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
		var fact = document.getElementById("factNo");
		var beginday=document.getElementById("beginday");
		var endday=document.getElementById("endday");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webpersonnum_findPageBean2",
			data : "factNo=" + fact.value + "&beginDay=" + beginday.value+"&endDay="+endday.value,
			success : function(msg) {
			    layer.close(loadi);
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
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
	
function showDiv(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '出勤報表',
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
    area: ['500px', '150px'],
    page:{
      url:'search/webpersonnum_search_print.jsp'   
    },
    close:function(){
       window.location.href='webpersonnum_findPageBean';
    }
            
});
    }
</script>

<body>
	<jsp:include page="publicHead_webPersonnum.jsp" flush="true" />
	<hr />
	<s:if test='#session.loginUser.userread!="1"'>
	<input value="添加" type="button" id="addbtn"
		onclick="javascript:location.href='saveAndUpdate/webpersonnumSaveOrUpdate.jsp'" />&nbsp;&nbsp;&nbsp;
		<s:if test="#session.factNo=='tw'">
		<br>
		<span style="float:right"> <img alt="" src="images/136.gif"><a
		href="javascript:showDiv()" 
		style="color:blue;text-decoration:underline;float:right;padding-right:30px">出勤統計報表</a>
	   </span>
		</s:if>
	</s:if>			
	<div id="bodyid">
		<jsp:include page="table1/webpersonnum1.jsp" />
	</div>
</body>
</html>
