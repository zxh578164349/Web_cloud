
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/style.css">
	
<link rel="stylesheet" type="text/css" href="css/form.css" />

<!-- <script type="text/javascript" src="jquery/jquery-1.11.1.js"></script> -->
<!-- <script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script> -->

<style type="text/css">
body {
	margin: 0;
	padding: 0;	
	background-image:url(images/login_bg.png) 
}

.wrap {
	margin: 150px auto;
	width: 380px;
	 overflow: hidden; 
	/*  position:absolute;  */
}

.loginForm {
	box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px rgba(0, 0, 0, 0.2), 0
		3px 0 #fff, 0 4px 0 rgba(0, 0, 0, 0.2), 0 6px 0 #fff, 0 7px 0
		rgba(0, 0, 0, 0.2);
	position: absolute;
	z-index: 0;
	background-color: #FFF;
	border-radius: 4px;
	height: 300px;
	width: 380px;
	background: -webkit-gradient(linear, left top, left 24, from(#EEE),
		color-stop(4%, #FFF), to(#EEE) );
	background: -moz-linear-gradient(top, #EEE, #FFF 1px, #EEE 24px);
	background: -o-linear-gradient(top, #EEEEEE, #FFFFFF 1px, #EEEEEE 24px);
}

.loginForm:before {
	content: '';
	position: absolute;
	z-index: -1;
	border: 1px dashed #CCC;
	top: 5px;
	bottom: 5px;
	left: 5px;
	right: 5px;
	box-shadow: 0 0 0 1px #FFF;
}

.loginForm h1 {
	/* text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0
		rgba(0, 0, 0, .5); */
	text-transform: uppercase;
	text-align: center;
	color: #003366;
	line-height: 3em;
	margin: 16px 0 20px 0;
	letter-spacing: 4px;
	font: normal 26px/1 Microsoft YaHei, sans-serif;
	font-weigth:bold;
}

fieldset {
	border: none;
	padding: 10px 10px 0;
}

fieldset input[type=text] {
	background: url(style/default/images/user.png) 4px 5px no-repeat;
}

fieldset select {
	background: url(style/default/images/user.png) 4px 5px no-repeat;
}

fieldset input[type=password] {
	background: url(style/default/images/password.png) 4px 5px no-repeat;
}

fieldset input[type=text],fieldset input[type=password] {
	width: 100%;
	line-height: 2em;
	font-size: 12px;
	height: 24px;
	border: none;
	padding: 3px 4px 3px 2.2em;
	/* width: 300px; */
}

fieldset input[type=submit] {
	text-align: center;
	padding: 2px 20px;
	line-height: 2em;
	border: 1px solid #FF1500;
	border-radius: 3px;
	background: -webkit-gradient(linear, left top, left 24, from(#FF6900),
		color-stop(0%, #FF9800), to(#FF6900) );
	background: -moz-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
	background: -o-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800',
		endColorstr='#FF6900' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900')";
	height: 30px;
	cursor: pointer;
	letter-spacing: 4px;
	margin-left: 10px;
	color: #FFF;
	font-weight: bold;
}

fieldset input[type=button] {
	text-align: center;
	padding: 2px 20px;
	line-height: 2em;
	border: 1px solid #FF1500;
	border-radius: 3px;
	background: -webkit-gradient(linear, left top, left 24, from(#FF6900),
		color-stop(0%, #FF9800), to(#FF6900) );
	background: -moz-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
	background: -o-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800',
		endColorstr='#FF6900' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900')";
	height: 30px;
	cursor: pointer;
	letter-spacing: 4px;
	margin-left: 10px;
	color: #FFF;
	font-weight: bold;
}

fieldset input[type=reset] {
	text-align: center;
	padding: 2px 20px;
	line-height: 2em;
	border: 1px solid #FF1500;
	border-radius: 3px;
	background: -webkit-gradient(linear, left top, left 24, from(#FF6900),
		color-stop(0%, #FF9800), to(#FF6900) );
	background: -moz-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
	background: -o-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800',
		endColorstr='#FF6900' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900')";
	height: 30px;
	cursor: pointer;
	letter-spacing: 4px;
	margin-left: 10px;
	color: #FFF;
	font-weight: bold;
}

fieldset input[type=button]:hover {
	background: -webkit-gradient(linear, left top, left 24, from(#FF9800),
		color-stop(0%, #FF6900), to(#FF9800) );
	background: -moz-linear-gradient(top, #FF9800, #FF6900 0, #FF9800 24px);
	background: -o-linear-gradient(top, #FF6900, #FF6900 0, #FF9800 24px);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900',
		endColorstr='#FF9800' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900', endColorstr='#FF9800')";
}

fieldset input[type=submit]:hover {
	background: -webkit-gradient(linear, left top, left 24, from(#FF9800),
		color-stop(0%, #FF6900), to(#FF9800) );
	background: -moz-linear-gradient(top, #FF9800, #FF6900 0, #FF9800 24px);
	background: -o-linear-gradient(top, #FF6900, #FF6900 0, #FF9800 24px);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900',
		endColorstr='#FF9800' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900', endColorstr='#FF9800')";
}

fieldset input[type=reset]:hover {
	background: -webkit-gradient(linear, left top, left 24, from(#FF9800),
		color-stop(0%, #FF6900), to(#FF9800) );
	background: -moz-linear-gradient(top, #FF9800, #FF6900 0, #FF9800 24px);
	background: -o-linear-gradient(top, #FF6900, #FF6900 0, #FF9800 24px);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900',
		endColorstr='#FF9800' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900', endColorstr='#FF9800')";
}

.inputWrap {
	background: -webkit-gradient(linear, left top, left 24, from(#FFFFFF),
		color-stop(4%, #EEEEEE), to(#FFFFFF) );
	background: -moz-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px);
	background: -o-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px);
	border-radius: 3px;
	border: 1px solid #CCC;
	margin: 10px 10px 0;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE',
		endColorstr='#FFFFFF' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF')";
}

fieldset input[type=checkbox] {
	margin-left: 10px;
	vertical-align: middle;
}

fieldset a {
	color: blue;
	font-size: 12px;
	margin: 6px 0 0 10px;
	text-decoration: none;
}

fieldset a:hover {
	text-decoration: underline;
}

fieldset span {
	font-size: 12px;
}
#beautiful{
    width: 100%;
  height: 100%;
  overflow: hidden;
}
</style>

<script type="text/javascript" src="jquery/jquery-1.7.2.js"></script>	
<script type="text/javascript" src="jquery/jquery.particleground.min.js"></script>
<script type="text/javascript">
   var jq=jQuery.noConflict();
   jq(document).ready(function() {
    jq('#particles').particleground();
    });
     jq('.intro').css({
    'margin-top': -($('.intro').height() / 2)
  });
</script>

<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script> 	
</head>
<script type="text/javascript">
	document.createElement("section");
	document.createElement("header");
	document.createElement("footer");
	$(function() {
		var no = document.getElementById("factNo");
		if (no.length == 2) {
			no.disabled = "disabled";
		}
	});


	function checkFact() {
		var factNO = document.getElementById("factNo");
		var names = document.getElementById("uname");
		var pwd = document.getElementById("pwd");
		if (factNO.value == 0 || names.value == "" || pwd.value == "") {		
			jAlert("請輸入完整的信息", '提示信息');
		} else {
		    document.getElementById("mydiv").style.display="block";
			document.getElementById("loginform").submit();
		}
	}

	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			checkFact();
		}
	});

</script>

<body >
  <div id="particles" >
	<div class="intro">		
		<form id="loginform" action="userlogin2" method="post">
			<section class="loginForm"> 
			<h1>用戶登录</h1>			
			<div class="loginForm_content">
				<fieldset>
				<div style="float:left;"><img alt="" src="images/login_name.png"></div>	  
					<div class="inputWrap" >					   
						<input type="text" id="uname" name="webUsers.username"
							value="<s:property value='webUsers.username'/>" placeholder="請輸入賬號" autofocus
							required>
					</div>
					<div style="float:left"><img src="images/login_pwd.png"></div>
					<div class="inputWrap">
						<input type="password" name="webUsers.pwd" id="pwd"
							placeholder="请输入密碼" required>
					</div>
					<div class="inputWrap">
						<select style="width:100%" id="factNo" name="factNo"
							placeholder="请選擇" required>
							<s:if test="factNo!=null">
								<option value="<s:property value='factNo'/>">
									<s:property value="factname" />&nbsp;(<s:property value="factNo" />)																											
								</option>
							</s:if> 
							<option value="0">請選擇</option>
							<option value="tw">TW</option>
							<s:iterator value="#session.facts" id="temp">
								<option value="${temp[0]}">
									${temp[1]}&nbsp;(${temp[0]})</option>
							</s:iterator>
						</select><span id="sfact" style="color:red"><s:property
								value='factError' />
						</span>
					</div>
					<div>
						<span id="sfact"></span>
					</div>
				</fieldset>				
				<br>
				
				<fieldset
					style="border:0px solid black;width:190px; margin:0px 0px 0px 75px; ">
					<center style="width:190px">
					<input type="button" value="登录" onclick="checkFact();" />&nbsp;<input
						type="reset" value="重置">
						</center>
				</fieldset>
                
			</div>
			</section>
		</form>
	</div>	
	</div>
	
	<div id="mydiv">
		<p>
			<img alt="" src="images/loading005.gif"><br>
		</p>
	</div>
	
</body>
</html>
