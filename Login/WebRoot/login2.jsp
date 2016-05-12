<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:useBean id="cookie" class="util.Cookie" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<!-- General meta information -->
<title>Web系統登錄</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="robots" content="index, follow" />
<meta charset="utf-8" />
<!-- // General meta information -->

<!--  <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>-->
<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="loginpage/jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="loginpage/jquery/layer/layer.min.js"></script>

<!-- Load stylesheets -->
<link type="text/css" rel="stylesheet" href="loginpage/css/login_style_chrome.css" media="screen" />	
<!-- // Load stylesheets -->

<script>
var jq=jQuery.noConflict();
var loadi;
jq(document).ajaxStart(function(){
	loadi=layer.load("正在登錄,請稍等...");
});
jq(document).ajaxStop(function(){
	layer.close(loadi);
});
	jq(document).ready(function() {
		jq("#submit1").hover(function() {
			jq(this).animate({
				"opacity" : "0"
			}, "slow");
		}, function() {
			jq(this).animate({
				"opacity" : "1"
			}, "slow");
		});
	});
	function checkFact() {
		var factNO = document.getElementById("url_factno");
		var names = document.getElementById("url_username_input");
		var pwd = document.getElementById("url_pwd_input");
		if (factNO.value == 0 || names.value == "" || pwd.value == "") {
			alert("請輸入完整的信息", '提示信息');
		} else {						
			jq.ajax({
				type:"POST",
				dataType:"json",
				data:jq("#loginform").serialize(),
				url:"userlogin",
				success:function(data){
					 if(data=='0'){
						 location.href="main.jsp";
					  }
					  if(data=='1'){
						  alert("當前賬號已註銷!");
					  }
					  if(data=='2'){
						  alert("廠別不正確!");
					  }
					  if(data=='3'){
						  alert("賬號或密碼不正確!");
					  }
				},
				error:function(err){
					alert(err.responseText);
				}
			});
		}
	}

	jq(document).keyup(function(event) {
		if (event.keyCode == 13) {
			checkFact();
		}
	});
	

</script>

</head>
<body >
   
	<div id="wrapper">
		<div id="wrappertop"></div>

		<div id="wrappermiddle">
			<form  method="post" id="loginform">
				<h2>Web系統登錄  <s:property value='userName'/></h2>
				<div id="username_input">
					<div id="username_inputleft"></div>
					<div id="username_inputmiddle">
						<input type="text" name="webUsers.username" id="url_username_input" value="<%=cookie.getCookName() %>" onclick="this.value = ''" />
						<img id="url_user" src="loginpage/images/login2/username_icon.jpg" alt="user" title="賬號" />							
					</div>
					<div id="username_inputright"></div>
				</div>
				<div id="password_input">
					<div id="password_inputleft"></div>
					<div id="password_inputmiddle">
						<input type="password" name="webUsers.pwd" id="url_pwd_input" value="" onclick="this.value = ''" />
						<img id="url_password" src="loginpage/images/login2/pwd_icon.jpg" alt="pwd" title="密碼"/>							
					</div>
					<div id="password_inputright"></div>
				</div>
				<div id="factno_select">
					<div id="factno_selectleft"></div>
					<div id="factno_selectmiddle">
						<select id="url_factno" name="factNo">
						    <s:if test="factNo!=null">
								<option value="<s:property value='factNo'/>">
									<s:property value="factname" />&nbsp;(<s:property value="factNo" />)																											
								</option>
							</s:if>
							<option value="<%=cookie.getCookFactNo() %>"><%=cookie.getCookFactNo() %></option> 
							<option value=0>請選擇廠別</option>
							<option value="tw">TW</option>
							<s:iterator value="#session.facts" id="temp">
								<option value="${temp[0]}">
									${temp[1]}&nbsp;(${temp[0]})</option>
							</s:iterator>
						</select>
						<img id="url_password" src="loginpage/images/login2/factno1.png" alt="fa" title="廠別" />																			
					</div>
					<div id="factno_selectright"></div>
					<br/>
					<span id="sfact"><s:property value='factError' /></span>
				</div>
				<br/>
				<div id="submit">
					
					<input type="button" value="登  錄" id="submit1" onclick="checkFact()"/>
					<input type="button" value="登  錄" id="submit2" onclick="checkFact()"/>						
				</div>
				<div id="remembered"><input type="checkbox" value="remembered" name="remembered"/>記住賬號與廠別</div>
			</form>		
		</div>
		<div id="wrapperbottom"></div>
		
	</div>
	
	<jsp:include page="copyright_login.jsp"/>
</body>
</html>