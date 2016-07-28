
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>顯示窗口</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!--<LINK href="css/list.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">-->
<script type="text/javascript">
//var jq=jQuery.noConflict();
	
function showBills(){
   jq("#divBills").show();
}
function hideBills(){
   jq("#divBills").hide(300);
}
function hideBills2(){
   jq("#divBills2").hide(300);
}
function goHere(){
	
	loadUrl("/Login/vbm_findPageBean");
}
function findKyVisaBills_Int(){
   kyvisabillsjs.findKyVisaBills_Int(function(x){
	   var temp;
       if(x>0){               
         temp="<a href='javascript:goHere()'>你好，目前有"+x+"封函文需要審核!(可點擊進入)</a>"         
       }else{         
          temp="<b>你好，目前暂无函文需要審核!</b>";        
       }
       jq("#td_content").append(temp);
       jq("#divBills2").show();
   })
}
setTimeout("showBills(),findKyVisaBills_Int()",1000);
setTimeout("hideBills2()",15000); 	
</script>
<script type='text/javascript' src='/Login/dwr/interface/kyvisabillsjs.js'></script>
<!-- <script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script> -->

</head>
<body>
					
		<div  id="divBills2" >		  
		   <table>
		   <tr>
		   <th>
		      <img src="images/lamp.jpg"/>
		   </th>
		   <td id="td_content">		      
		   </td>
		   </tr>
		</table>
		</div>				  
	
</body>
</html>
