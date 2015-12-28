
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
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />	
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
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
window.onload=getAllWebbrank;
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webbrankjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

</head>
<body>
  <div style="width:1200px">
  <form id="public_form" method="post">
	<table  border="0px">
		<tr>
			<td>物料名稱</td>
			<td>
			   <input type="text" name="pomName"/>
			</td>
			<td>品牌</td>
			<td>
			   <select name="brank" id="dwrWebbrank"></select>
			</td>
			<td>年月</td>
			<td>
			  開始日期:<input type="text" id="year" name="yymm" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/></br>
			  結束日期:<input type="text" id="year" name="yymm2" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>			
			</td>
			<td>
			 <input value="搜索" type="button" id="addbtn" onclick="javascript:submis()" />
			 <input value="導出Excel" type="button" id="search_forday" onclick="print('public_form')"/>	
			</td>
		</tr>
	</table>
	</form>
	</div>
</body>
</html>
