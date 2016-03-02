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
<!--  <link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />-->
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
			url : "sumwebydata_findPageBean3",
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
	function submis(public_form) {
		var fact = document.getElementById("factNo");
		var begin_yymm = document.getElementById("begin_yymm");
		var end_yymm=document.getElementById("end_yymm");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "sumwebydata_findPageBean2",
			//data : "factNo=" + fact.value + "&begin_yymm=" + begin_yymm.value+"&end_yymm="+end_yymm.value,
			data:jq("#"+public_form).serialize(),
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
		   data:jq("#"+mid).serialize(),
		   url:"sumwebydata_delete",
		   success:function(data){
			 jq("#bodyid").html(data);  
		   },
		   error:function(err){
			   jq("#bodyid").html(err.responseText);
		   }
	   });
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
    type: 2,   //0-4的选择,
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
    iframe:{
      src:'page/sum_yiele_data.jsp'   
    }           
});
}
function showDiv_update(factNo,factCode,yymm){
   jq.layer({
      type:2,
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
      iframe:{
         src:'sumwebydata_findById?factNo='+factNo+'&factCode='+factCode+'&yymm='+yymm
      }
   });
   
   //layer.closeAll();
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
   /* function print(){
       var factNo=document.getElementById("factNo").value;
       var begin_yymm=document.getElementById("begin_yymm").value;
       var end_yymm=document.getElementById("end_yymm").value;
       location.href="sumwebydata_print?factNo="+factNo+"&begin_yymm="+begin_yymm+"&end_yymm="+end_yymm;
    } */
    function print(public_form){
    	var public_form=jq("#"+public_form);
    	public_form.attr("action","sumwebydata_print");
    	public_form.attr("target","_blank");
    	public_form.submit();
    }
</script>

<body>
	<jsp:include page="publicHead_sumydata.jsp" flush="true" />
	<hr />	
	<s:if test='#session.loginUser.userread!="1"'>
	<span style="float:right">
	  <input type="button" onclick="javascript:showDiv()" class="btn btn-link btn-sm" value="添加每月盤點數據"/>
	</span>
	</s:if>
	<span style="float:right"> <input type="button"
		onclick="javascript:layer.load(0);location.href='ydata_findPageBean'" 
		class="btn btn-link btn-sm" value="按日詳細查看"/>
	</span>
	<!-- <s:if test="#session.loginUser.username=='admin'">
	<span style="float:right"> <img alt="" src="images/136.gif"><a
		href="javascript:updateAll()" 
		style="color:blue;text-decoration:underline;float:right;padding-right:30px">更新所有</a>
	</span>
    </s:if> -->
	<div id="bodyid">
		<jsp:include page="table1/yDateforMonth1_1.jsp" />
	</div>
</body>
</html>
