<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<!--  <link rel="stylesheet" type="text/css" href="css/form.css" /> -->
<link rel="stylesheet" type="text/css" href="css/button_css.css" />

<!-- <script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript"
	src="jquery_alert_dialogs/jquery.alerts.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery_alert_dialogs/jquery.alerts.css" />
<script type="text/javascript"
	src="jquery_alert_dialogs/jquery.ui.draggable.js"></script> -->
	
<script type="text/javascript" src="jquery/jquery-1.7.2.js"></script>

</head>
<font face="Calibri" size="3"><script type="text/javascript">
	function changeimg() {
		var myimg = document.getElementById("code");
		now = new Date();
		myimg.src = "YanZhenMa.jsp?code=" + now.getTime();
	}
	$(function() {
		var only = document.getElementById("only");
		if (only.value != "null") {
			alert(only.value);
		}
		var no = document.getElementById("factNo");
		if (no.length == 2) {
			no.disabled = "disabled";
		}
	});
	function init() {
		var qName = document.getElementById("user");
		//var pwd=document.getElementById("pwd");
		qName.focus();
		//pwd.focus();
	}
	function showDiv(){
	    document.getElementById("mydiv").style.display="block";
	}
	function checkFact() {
		var factNO = document.getElementById("factNo");
		var names = document.getElementById("user");
		var password = document.getElementById("pwd");
		if (factNO.value == 0 || names.value == "" || password.value == ""
		/* || mcode.value == "" */) {
			alert("請輸入完整的信息");
		} else {									　　   
           /*$.ajax方法*/ 
       $.ajax({
			type : "POST",
			dataType : "json",
			url : "userlogin",
			data : "webUsers.username=" + names.value + "&webUsers.pwd=" + password.value+"&factNo="+factNO.value,
			//$("#form").serialize(),/*提交表格數據*/
			success : function(data) {
				alert(data);
			},
			error:function(err){
			    alert(err);
			}
		}); 
	
		}
	}

	$(document).keyup(function(event) {

		if (event.keyCode == 13) {
			checkFact();
		}
	});
	
	
	
</script>
</font>
<jsp:useBean id="cookieName" class="util.Cookie" scope="page" />
<style>
.reset,.submit {
	width: 60px;
}

body {
	background-image:url(images/login_bg.png)
}

/* td {
	margin-top: 30px
} */
/* input,select,table{
	border: 2px solid #7A6F6F;
	border: 2px solid #7A6F6F \9; 
	height: 20px; 
	height: 20px \9; 
	padding-left: 5px;
	line-height: 20px \9; 
	-moz-border-radius: 3px; 
	-webkit-border-radius: 3px; 
	border-radius: 3px; 
	background-color: white;
	outline: none;
	border-color: #B6CDDC;

} */


.sharp{width:420;float:left;text-align:center;margin:0 auto;position:absolute;z-index:1;left:30%;top:25%;   
}
.sharp .content div{padding-left:15px;text-indent:2em;}
.content{height:300;
}
/*上圆角框通用设置样式，如果要运用多个不同颜色，以下6句不用重新变化--------------------------------*/
.b1,.b2,.b3,.b4,.b5,.b6,.b7,.b8{height:1px; font-size:1px; overflow:hidden; display:block;}
.b1,.b8{margin:0 5px;}
.b2,.b7{margin:0 3px;border-right:2px solid; border-left:2px solid;}
.b3,.b6{margin:0 2px;border-right:1px solid; border-left:1px solid;}
.b4,.b5{margin:0 1px;border-right:1px solid; border-left:1px solid; height:2px;}
.content {border-right:1px solid;border-left:1px solid;overflow:hidden;}
/*颜色方案一,蓝色边框----------------------------------------*/
/*下面第一、二句决定边框颜色，第三句决定背景颜色*/
/*边框色*/
.color1 .b2,.color1 .b3,.color1 .b4,.color1 .b5,.color1 .b6,.color1 .b7,.color1 .content{border-color:#CCCCCC;}
.color1 .b1,.color1 .b8{background:#CCCCCC;}
/*背景色*/
.color1 .b2,.color1 .b3,.color1 .b4,.color1 .b5,.color1 .b6,.color1 .b7,.color1 .content{background:url(images/login.png);}


/*遮罩層**/
#mydiv {
	width: 100%;
	height: 100%;
	top: 1px;
	position: absolute;
	/* background-color:rgb(100,200,300);  */
	filter: alpha(opacity=50);
	/* background-color:rgba(100,200,300,0.5); */
	background-color: black;
	opacity: 0.5;
	-moz-opacity:0.5;   
    -khtml-opacity: 0.5; 
	 display: none; 
	 z-index:99;
}

#mydiv p {
	position: relative;
	color:yellow;
	font-size: 14px;
	top: 50%;
	text-align:center;
}
</style>
<body>
	 <input type="hidden" value="<%=session.getAttribute("only")%>"
		id="only"> 
		
<div id="left" class="sharp">	
	　　<div class="sharp color1">
        <b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b> 
        <div class="content"> 
         <div >	
        <center>	
		<form action="userlogin" method="post" id="form">                              
                  <table border="0" cellspacing="0" cellpadding="0"
					style="background-image:url(images/login.png)"
					width="390">
					<tr>
						<td colspan="2" align="center"><br> <span
							style="font-size:26;font-weight:bold;color:#003366">用户登錄</span><br>
						<br></td>
					</tr>
					<tr>
						<td width="110"><span style="margin-left:55;font-size:14px">用户名:</span>
						</td>
						<td><div style="margin-top:10"></div> <input id="user"
							value="" name="webUsers.username"
							style="width:200;margin-bottom:10px;border:0;background:#FAFFBD" />
						</td>
					</tr>
					<tr>
						<td><span style="margin-left:55;font-size:14px">密&nbsp;&nbsp;&nbsp;码:</span>
						</td>
						<td><div style="margin-top:10"></div> <input id="pwd"
							type="password" name="webUsers.pwd" style="width:200;margin-bottom:10px;border:0;background:#FAFFBD" />
						</td>
					</tr>
					<tr><td colspan="2"><br></td></tr>
					<tr>
						<td><span style="margin-left:55;font-size:14px">廠&nbsp;&nbsp;&nbsp;別:</span>
						</td>
						<td><div style="margin-top:-2px"></div>
						<span style="border:0; position:absolute; overflow:hidden;">
						 <select id="factNo"
							name="factNo" style="width:200;margin-bottom:10px;margin:-2px;background:#FAFFBD">
								<s:if test="factNo!=null">
									<option value="<s:property value='factNo'/>">
										<s:property value="factname" />
										&nbsp;(
										<s:property value="factNo" />
										)
									</option>
								</s:if>
								<option value="0">請選擇</option>
								<option value="tw">TW</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>
								</s:iterator>
						</select></span><br /> <span id="sfact" style="color:red"><s:property
									value='factError' /> </span>
						</td>
					</tr>
					<tr>
						<!-- <td><span style="margin-left:60;">验证码:</span></td>
						<td><div style="margin-top:10"></div><input type="text"
							name="certCode" id="mcode" style="width:200" /><br /><span
							id="yzm" style="color:red"><s:property value='yzm' />
						</span></td> -->
					</tr>
					<tr>
						<td colspan="2">
						<br>
						<br>
						<br>
						<center>
								<!-- <img id="code" src="YanZhenMa.jsp" style="margin-top:8">&nbsp;&nbsp;&nbsp;<a
									href="javascript:changeimg()">看不清，换一张 </a>
								&nbsp;&nbsp;&nbsp;&nbsp; -->
								<!-- <input type="button" class="submit" onclick="checkFact()"
									value="登陆" />&nbsp;&nbsp; <input class="reset" type="reset"
									value="重置" class="button" /> -->								
									<input type="button" onclick="checkFact()" value="登 錄" id="btn_go" onmouseover="this.style.background='url(images/btn_login_pink.gif)'" onmouseout="this.style.background='url(images/btn_login_black.gif)'"/>&nbsp;&nbsp;
									<input type="reset" value="重 置" id="btn_reset" onmouseover="this.style.background='url(images/btn_login_pink.gif)'" onmouseout="this.style.background='url(images/btn_login_black.gif)'"/>
							</center> <br> <br>
						</td>
					</tr>
				</table>           
		 </form> 
		 </center>		 		         	         	
	</div>
	</div>
	 <b class="b5"></b><b class="b6"></b><b class="b7"></b><b class="b8"></b> 
	</div>
	
	
	</div>	    
		<div id="mydiv">
		<p>
			<img alt="" src="images/loading006.gif" id="img_loading"><br> Loading....
		</p>		
	</div>
		
</body>
</html>
