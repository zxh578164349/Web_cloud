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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'addBackMat.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
	$(function() {
		var jj = jQuery.noConflict();
		var demo = jj("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			tipSweep : true,
			datatype : {
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my0-8": /^\d{0,8}(\.[0-9]{1,4})?$/
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";
	});

	
</script>
<script type='text/javascript' src='/Login/dwr/interface/kyzjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<style type="text/css">
table.gridtable {
	/* font-family: verdana,arial,sans-serif; */
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-style:solid;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	 border-width: 1px; 
	/* padding: 8px; */
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;	
	/* text-align:justify;
    text-justify:distribute-all-lines;
    -moz-text-align-last:justify; */
}
.bluecss{
   font-color:blue;
   border-color:blue;
   background-color:blue;
}
input[type="text"],select{
  width:180px;
}

</style>

</head>
<body >
   <div id="pop">
       <form action="visaflow_update" method="post" id="form">
		<table width="1080" align="center" cellspacing="0" cellpadding="0"  class="gridtable" >
		    <caption style="font-size:30;font-weight:bold">審核流程<br><br></caption>		    																 			
			<tbody id="kyzs_body">			 				 						
			   <tr>
			   <td>廠別</td>
			   <td><input type="text" value="<s:property value='flow.id.factNo'/>" readonly style="color:blue" name="flow.id.factNo"/></td>
			   <td>類別</td>
			   <td><input type="text" value="<s:property value='flow.id.visaSort'/>" readonly style="color:blue" name="flow.id.visaSort"/></td>
			   </tr>
			   <tr>
			   <td>姓名</td>
			   <td>
			   <input type="text" value="<s:property value='flow.id.purmanNo'/>" readonly style="color:blue" name="flow.id.purmanNo" />
			   
			   </td>
			   <td>項次</td>
			   <td><input type="text" value="<s:property value='flow.id.itemNo'/>" readonly style="color:blue" name="flow.id.itemNo"/></td>
			   </tr>
			   <tr>
			   <td>Email地址</td>
			   <td><input type="text" value="<s:property value='flow.visaSigner'/>"  datatype="e" name="flow.visaSigner"/></td>
			   <td>職務</td>
			   <td>
			   <input type="text" value="<s:property value='flow.visaRank'/>"  name="flow.visaRank"/>
			   <input type="hidden" value="<s:property value='flow.flowMk'/>"  name="flow.flowMk"/>
			   </td>
			   </tr>			          			  			 	  			
			</tbody>							    
		</table >
			<center>			    
				<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; 
				<input type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</center>
							
	</form>
	</div>
</body>
</html>
