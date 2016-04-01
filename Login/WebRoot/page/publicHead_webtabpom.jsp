
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<LINK href="css/list.css" type="text/css" rel="stylesheet">

<script type="text/javascript">
 
//var jq=jQuery.noConflict();
jq(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});

function getAllWebbrank(){
	webbrankjs.findAll(function(x){
		dwr.util.addOptions("dwrWebbrank",x,"BNo","BName");
	})
}
//window.onload=getAllWebbrank;
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webbrankjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
<script type="text/javascript">
jq(function(){
	getAllWebbrank();
});
</script>
</head>
<body>
  <form id="public_form" method="post">
	<table id="tb_search">
		<tr>
			<td>物料名稱</td>
			<td>
			   <input type="text" name="pomName"/>
			</td>
			<td>品牌</td>
			<td>
			   <select name="brank" id="dwrWebbrank"></select>
			</td>
			<td>
			  <input type="text" id="year" name="yymm" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>至
			  <input type="text" id="year" name="yymm2" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>			
			</td>
			<td>
			 <input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" />
			 <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form')"/>	
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
