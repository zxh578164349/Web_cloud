<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Yield_data.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
</head>
<script type="text/javascript">
	var j = jQuery.noConflict();
	function golook(){	
	document.getElementById("lookordown").value="look";	  
		 j("#form").Validform({
		 btnSubmit : "#btnlook",
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			callback : function(form) {
				//document.getElementById("mydiv").style.display = "block";				
				form[0].submit();
			}			
		});
		
	}
 	function godown(){	
 	document.getElementById("lookordown").value="down"; 
		 j("#form").Validform({
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			callback : function(form) {
				//document.getElementById("mydiv").style.display = "block";				
				form[0].submit();
			}			
		});
	}
	 

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea", x);
		});
	}

	function getFactArea2(mid) {
		document.getElementById("dwrFactArea2").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea2", x);
		});
	}

	
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<body>

	<form action="webpersonnum_print" method="post" id="form" target="_blank">
		      <table>
		        <tr>
		          <td>日期:</td>
		          <td><input type="text" name="yymmdd" onclick="WdatePicker()" datatype="*" class="Wdate"/>
		              <input type="hidden" name="lookordown" id="lookordown"/></td>
		          <td><input type="submit" value="查看" onclick="golook()" id="btnlook"/>&nbsp;<input type="submit" value="下載" onclick="godown()"/></td>
		        </tr>
		      </table>
		   </form>

	<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>

</body>

</html>
