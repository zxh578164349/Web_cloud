<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>


<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>Web系統</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="images/icon/web_ico.ico" />


<style type="text/css">
#nprogress{
   position: fixed;
   top: 0;
   right: 0;
   bottom: 0;
   left: 0;
   background-color: #f7f7f7;
   z-index: 999;
}
.spinner-icon{
   display: none!important;
}
.splash {
   position:absolute;
   top:40%;
   left:0;
   right:0;
   margin: auto;
 }
 .splash img {
   display: block;
   margin-left: auto;
   margin-right: auto;
   height: 100px;
   width: 100px;
 }
 .card {
   background-color: #f7f7f7;
   padding: 20px 25px 15px;
   margin: 0 auto 25px;
   width: 380px;
 }
 .mybar {
   background: #29d;
   height:10px;
 }
 .progress {
   height: 10px;
   overflow: hidden;
 }
</style>
<!--[if !IE]><!-->
<link type="text/css" rel="stylesheet" href="loginpage/css/login_style_chrome.css" media="screen" />
 <!--<![endif]-->
<!--[if lt IE 9]>
<link type="text/css" rel="stylesheet" href="loginpage/css/login_style_ie.css" media="screen"> 
<![endif]-->
<link type="text/css" rel="stylesheet" href="loginpage/nprogress/nprogress.css" media="screen" />
</head>

<body>
<div id="bodyid" style="display:none"></div>
<jsp:include page="copyright_login.jsp"/>

<script type="text" id="splash-template">
           <div class="splash card">
               <div role="spinner">
                   <div class="spinner-icon"></div>
               </div>
               <p class="lead" style="text-align:center">請稍等...</p>
               <div class="progress">
                   <div class="mybar" role="bar">
                   </div>
               </div>
           </div>
</script>

<!-- <script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>  -->
<script>window.jQuery || document.write('<script src="loginpage/jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="loginpage/nprogress/nprogress.js"></script>
<script type="text/javascript" src="loginpage/jquery/layer/layer.min.js"></script>
<script type="text/javascript">
	var jq=jQuery.noConflict();
	//var loadi;
	jq(document).ajaxStart(function(){
		//loadi=layer.load("請稍等...");
		NProgress.configure({
			minimum: 0.08,
		    easing: 'ease',
		    speed: 200,
		    trickle: true,
		    trickleRate: 0.02,
		    trickleSpeed: 800,
		    showSpinner: true,
		    barSelector: '[role="bar"]',
		    spinnerSelector: '[role="spinner"]',
		    parent: 'body',
            template: jq('#splash-template').html()
               });
		NProgress.start();
		
	});
	jq(document).ajaxStop(function(){
		//layer.close(loadi);
		NProgress.done();
	});
	jq(function (){
		var agent = navigator.userAgent.toLowerCase();
		if (agent.indexOf("msie") > 0) {						
			jq("#bodyid").load("/Login/webfact_findAllWebfact");						
		}else {						
			jq("#bodyid").load("/Login/webfact_findAllWebfact2");
		}
		jq("#bodyid").fadeIn(1500);					
	});
	
	function checkFact(url) {
		var factNO = jq("#url_factno");
		var names = jq("#url_username_input");
		var pwd = jq("#url_pwd_input");
		if (factNO.val() == 0 || names.val() == "" || pwd.val() == "") {
			layer.msg("請輸入完整的信息");
		} else {
			jq("#submit1").attr("disabled",true);
			jq.ajax({
				type:"POST",
				dataType:"json",
				data:jq("#loginform").serialize(),
				url:url,
				success:function(data){
					 if(data=='0'){
						 location.href="main.jsp";
					  }
					  if(data=='1'){
						  layer.alert("當前賬號已註銷!");
						  jq("#submit1").removeAttr("disabled");
					  }
					  if(data=='2'){
						  layer.alert("廠別不正確!");
						  jq("#submit1").removeAttr("disabled");
					  }
					  if(data=='3'){
						  layer.alert("賬號或密碼不正確!");
						  jq("#submit1").removeAttr("disabled");
					  }
				},
				error:function(err){
					alert(err.responseText);
					 jq("#submit1").removeAttr("disabled");
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
</body >
</html>
