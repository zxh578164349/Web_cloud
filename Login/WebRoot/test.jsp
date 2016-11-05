
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>Web系統</title>
<head>

<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
</head>
<style type="text/css">
.navPoint {
	COLOR: #ADADAD;
	CURSOR: hand;
	FONT-FAMILY: Webdings;
	FONT-SIZE:10px;
	font-weight:bold;
}

.a2 {
	BACKGROUND-COLOR: A4B6D7;
}

#top {
	margin-left: 5px;
	margin-top:10px;
}

#mydiv {
	width: 85%;
	height: 100%;
	/* position: fixed; */
	position:absolute;
	filter: alpha(opacity = 50);
	background: transparent;
	background: rgba(0, 0, 0, 1) none repeat scroll 0 0 !important;
	opacity: 0.5;
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
	display: none;
	left: 217px;
	top: 106px;
	
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#7F000000,
		endColorstr=#7F000000 );
	zoom: 1;
	
	
}

#mydiv p {
	position: relative;
	color: white;
	font-size: 14px;
	top: 40%;
	text-align: center;
}

</style>





	<!-- 低于IE10就會覆蓋上面的#mydiv樣式，而使用以下的#mydiv樣式 --> 
	<!--[if lt IE 10]>
	<style type="text/css">   	 			
	#mydiv {
	width: 102%;
	height: 100%;
	 position: absolute; 
	filter: alpha(opacity = 50);
	background: transparent;
	background: rgba(0, 0, 0, 0.5) none repeat scroll 0 0 !important;
	opacity: 0.5;
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
	display: none;
	left: 220px;
	top:-100px expression(eval(document.body.scrollTop + 50));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#7F000000,
		endColorstr=#7F000000 );
	zoom: 1;		
}
	</style>  
	<![endif]-->  



<script>			
		if (self != top) {
			top.location = self.location;
		}
		function switchSysBar() {				
			if (document.getElementById("switchPoint").innerHTML=='＜＜＜') {
			    $("#frmTitle").hide(250);
				document.getElementById("switchPoint").innerHTML ='＞＞＞';							
				document.getElementById("tab").width = "2200";
				document.getElementById("tab2").width = "2200";
				document.getElementById("tab3").style.width="2200";
			} else {
			    $("#frmTitle").show(250);
				document.getElementById("switchPoint").innerHTML = '＜＜＜';			    
				document.getElementById("tab").width = "100%";				
				document.getElementById("tab2").width = "100%";
				document.getElementById("tab3").style.width="100%";
			}
		}
		function showPop() {
			$("#mydiv").show();						
			//var loadi=layer.load("正在加載....");
			var ifr=document.getElementById("show");
			if (ifr.attachEvent){
			    ifr.attachEvent("onload", function(){
			        //layer.close(loadi);
			        $("#mydiv").hide();
			    });
			} else {
			    ifr.onload = function(){
			        //layer.close(loadi);
			        $("#mydiv").hide();
			    };
			}
		}
		
   var currentDate = new Date(<%=new java.util.Date().getTime()%>);   
   function run() {       
   currentDate.setSeconds(currentDate.getSeconds() + 1); 
   document.getElementById("currentTime").innerHTML = currentDate.toLocaleString(); 
   }     
    window.setInterval("run();", 1000); 
  
    
    function back(){   	
    	location.href="/Login/judge.jsp";   	
    }
</script>
<body >
	
 	<table style="BORDER-COLLAPSE:collapse" id="tab" cellSpacing=0
		cellPadding=0 width=100% border=0>		
		<TR>
			<TD align=center width="100%" height=80 bgcolor="#292929">	<!--  #48525E-->		
				<div id="top">
					<h1 style="color:white;">加久企業股份有限公司</h1>						    							
						<span style="font-size:12px;float:right;color:#CCCC99">
						   登录人：<s:property value="#session.loginUser.name" />(<s:if test="#attr.factNo=='tw'">所有數據</s:if>								
								 <s:else> <s:property value="#attr.factName" /></s:else> ),欢迎您 ！								
							     <a id="exit" href="javascript:back()" target="_parent" style="color:#FF6666">
							     退出登录</a>
						</span>
					    <span id="currentTime" style="width:300px;float:left;font-size:12px;color:#99CCCC;background:#292929"></span>	 				
					</div> 
					&nbsp;													
					</TD>
		</TR>
		<tr><td align="center" width="100" height="5px" bgcolor="#1E1E1E"></td></tr>
	</table>

	

	<table id="tab2" border="0" height="100%" cellPadding="0" cellSpacing="0" width="100%">		
		<tr>
			<td >
			<div id="frmTitle" style="height:100%">
			  <iframe
					frameBorder="0" id="carnoc" name="carnoc" scrolling=auto
					src="menu.jsp"
					style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 230px; Z-INDEX: 2;border:1px solid #ccc">
			  </iframe>
			</div>		
			</td>
			
			<td width="46" bgcolor="#FCFCFC" style="WIDTH:18px;">
				<table width="10px" height="100%" border="1px" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="200" style="HEIGHT: 100%">
							<font style="FONT-SIZE: 9pt; CURSOR: default; COLOR:#C6A300">
								<span class="navPoint" id="switchPoint" title="关闭/打开左栏" style="display:none">＜＜＜</span>
								<a href="javascript:switchSysBar()"><img src="images/icon/arrow.jpg" border=0px/></a>
						</font>
						</td>
					</tr>
				</table>
			</td>
			 
			<td width="100%"><iframe frameBorder="0" name="show" id="show"
					scrolling=auto src="right.jsp"
					style="HEIGHT:100%; VISIBILITY:inherit; WIDTH:100%; Z-INDEX: 2;border:1px solid #ccc">
				</iframe>
				<div id="mydiv">
					<p>
						<img alt="" src="images/loading004.gif"><br>
						Loading....
					</p>
				</div>				
		  </td>
		</tr>
	</table>
	
	   <br>		
	   <div style="width:100%;position:fixed;bottom:0" align="center" id="tab3">
	   <font color="grey" style="font-size:10px;font-family: Arial, Helvetica, sans-serif;">
	   Copyright © 2014,加久企業股份有限公司,All Rights Reserved
	   </font>
	   </div>
</body>

</html>
