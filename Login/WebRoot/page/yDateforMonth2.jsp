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
			url : "sumwebydata_findPageBean3",
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
		var begin_yymm = document.getElementById("begin_yymm");
		var end_yymm=document.getElementById("end_yymm");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "sumwebydata_findPageBean2",
			data : "factNo=" + fact.value + "&begin_yymm=" + begin_yymm.value+"&end_yymm="+end_yymm.value,
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
	 document.getElementById(mid).submit();
	 }
	 });
	 }
	 
	 //更新
	 function update(sumform){
	    var flag=confirm("確定更新嗎?");
	    if(flag==true){	       
	       document.getElementById(sumform).submit();
	       layer.load("正在更新,請稍後...");
	    }
	 }
function showDiv(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '每月資料盤點',
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
    area: ['650px', '250px'],
    page:{
      url:'page/sum_yiele_data.jsp'   
    }           
});
}
function showDiv_update(factNo,factCode,yymm){
   jq.layer({
      type:1,
      title:'每月資料盤點_更新',
      closeBtn:[1,true],
      shade:[0],
      shadeClose:false,
      border:[10,0.3,'#000'],
      fadeIn:300,
      moveOut:false,
      moveType:1,
      offset:['10px','200px'],
      area:['650px','250px'],
      page:{
         url:'sumwebydata_findById?factNo='+factNo+'&factCode='+factCode+'&yymm='+yymm
      }
   })
}
    function updateAll(){
      layer.load("正在更新，請稍等.....");
      location.href="sumwebydata_updateAll";
    }
    function confirm_show(){
       var flag=confirm("注意!添加盤點資料後，該月所有產量資料不可修改和刪除.\r請確保數據正確完整後，再添加盤點資料.\r確定要添加嗎？");
       if(flag==true){
          this.showDiv();
       }
    }
    function print(){
       var factNo=document.getElementById("factNo").value;
       var begin_yymm=document.getElementById("begin_yymm").value;
       var end_yymm=document.getElementById("end_yymm").value;
       location.href="sumwebydata_print?factNo="+factNo+"&begin_yymm="+begin_yymm+"&end_yymm="+end_yymm;
    } 
</script>

<body>
	<jsp:include page="publicHead_sumydata.jsp" flush="true" />
	<hr />
	<!-- <s:if test='#session.loginUser.userread!="1"'>
	<input type="button" value="添加" id="addbtn"
		onclick="javascript:location.href='saveAndUpdate/Yield_data.jsp'" />&nbsp;&nbsp;&nbsp;
	</s:if>	 -->
	<br>
	<s:if test='#session.loginUser.userread!="1"'>
	<span style="float:right">
	  <img alt="" src="images/136.gif">
	  <a href="javascript:showDiv()" style="color:blue;text-decoration:underline;padding-right:30px">
	         添加每月盤點數據</a>
	</span>
	</s:if>
	<span style="float:right"> <img alt="" src="images/136.gif"><a
		href="javascript:location.href='ydata_findPageBean'" 
		style="color:blue;text-decoration:underline;float:right;padding-right:30px">按日詳細查看</a>
	</span>
	<s:if test="#session.loginUser.username=='admin'">
	<span style="float:right"> <img alt="" src="images/136.gif"><a
		href="javascript:updateAll()" 
		style="color:blue;text-decoration:underline;float:right;padding-right:30px">更新所有</a>
	</span>
    </s:if>
	<div id="bodyid">
		<jsp:include page="table1/yDateforMonth1_1.jsp" />
	</div>
</body>
</html>
