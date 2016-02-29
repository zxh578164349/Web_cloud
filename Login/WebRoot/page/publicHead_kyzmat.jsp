<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="dao.IKyFactDao"%>
<%@page import="services.impl.KyFactServicesImpl"%>
<%@page import="services.IKyFactServices"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="entity.*"%>
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
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />	
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<script type="text/javascript">
 

jq(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});

	function findallBN(){
	      kyzscmjs.findBN(function(x){
	          dwr.util.addOptions("bNo",x);
	      });                             
	}
	function findallMN(bNo){
	     document.getElementById("mNo").length=1;
	     document.getElementById("sNo").length=1;
	     kyzscmjs.findMN(bNo,function(x){
	     if(bNo!=""){
	        dwr.util.addOptions("mNo",x);
	     }	         
	     });
	}
	function findallSN(mNo){
	     document.getElementById("sNo").length=1;
	     kyzscmjs.findSN(mNo,function(x){
	     if(mNo!=""){
	        dwr.util.addOptions("sNo",x);
	     }	         
	     });
	}

	
	window.onload=function(){
	   findallBN();
	}
</script>
<script type='text/javascript' src='/Login/dwr/interface/kyzscmjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzmatjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzacctjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script> 
</head>
<body>
  <div style="width:1200px">
   <form  method="post" id="public_form">
	<table  border="0px">
		<tr>
		    <td>
		    <br>
		           物料編號
		    <input type="text" name="matNo" id="matNo"/><br>
		          中文名稱
		    <input type="text" name="matCname" id="matcname"/>
		    </td>			
			<td>
			<br>			 
			(開始日期)<input type="text" id="beginDate"  name="fromDate"  onClick="WdatePicker({minDate:'{%y-1}-%M-%d',maxDate:'#F{$dp.$D(\'endDate\',{d:-1})||\'%y-%M-{%d-1}\'}'})" readonly="readonly" class="Wdate"/><br>
		    (結束日期)<input type="text" id="endDate"  name="endDate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\',{d:1})}',maxDate:'%y-%M-%d'})" readonly="readonly" class="Wdate"/>
			</td>
			<td>
			<br>			
			 <select name="typeBno"  id="bNo" onclick="findallMN(this.value), makeMatNo()">
					     <option value="">大分類選擇</option>
			 </select>
			 <br>
			 <select name="typeMno" id="mNo" onclick="findallSN(this.value), makeMatNo()">
					         <option value="">中分類選擇</option>
			 </select>
			 <br>
			 <select name="typeSno"  id="sNo" onclick=" makeMatNo()">
					         <option value="">小分類選擇</option>
		     </select>	
			</td>			
			<td>
			<input value="搜索" type="button" id="addbtn" onclick="submis('public_form')" />
			<input value="導出Excel" type="button" id="search_forday"  onclick="print('public_form')"/>			
			</td>
		</tr>
	</table>
	</form>
	 	
	</div>
</body>
</html>
