<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>審核備註</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />


<style type="text/css">
table.altrowstable {
	font-family: verdana,arial,sans-serif;
	font-size:14px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.altrowstable caption{
    font-size:22px;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}


</style>
<script type="text/javascript" src="page/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript">
  function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}

window.onload=function(){
	altRows('alternatecolor');
	alert("你好!備註時,不管有無備註內容,請記住按[備註]按鈕");
}

function goSubmit(){
   layer.load('正在處理,请稍等...');
   document.getElementById("sub").submit();
}

</script>
</head>

<body>
      <form action="vbm_remark" method="post" id="sub">
      
         <table  class="altrowstable" id="alternatecolor" align="center">
           <caption>審核備註</caption>
           
		    <tr>
		        <td>備註</td>
		        <td>
		          <textarea rows="4" cols="100%" name="memo"></textarea>
		        </td>
		    </tr>
		    <tr><td colspan="2"><input type="button" value="返回" onclick="javascript:history.go(-1)"/></td></tr>					    
		</table >
		<br>
		<center>
		  <input type="button" value="備註" onclick="goSubmit()">&nbsp;&nbsp;<input type="reset" value="重置">
		</center>
      </form>
		
<div style="width:100%;position:fixed;bottom:0" align="center" >
<font color="grey" style="font-size:10px;font-family: Arial, Helvetica, sans-serif;">
Copyright © 2014,加久企業股份有限公司,All Rights Reserved</font>
</div>			
			
</body>
</html>
