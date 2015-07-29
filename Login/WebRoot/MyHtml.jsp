<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
  <head>
    <title>MyHtml.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=BIG5">
    
    <script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
    <style type="text/css">
      body{ 
margin: 0; 
font-size: 12px; 
line-height: 100%; 
font-family: Arial, sans-serif; 
} 
.background { 
display: block; 
width: 100%; 
height: 100%; 
opacity: 0.4; 
filter: alpha(opacity=40); 
background:while; 
position: absolute; 
top: 0; 
left: 0; 
z-index: 2000; 
} 
.progressBar { 
border: solid 2px #86A5AD; 
background: white url(progressBar_m.gif) no-repeat 10px 10px; 
} 
.progressBar { 
display: block; 
width: 148px; 
height: 28px; 
position: fixed; 
top: 50%; 
left: 50%; 
margin-left: -74px; 
margin-top: -14px; 
padding: 10px 10px 10px 50px; 
text-align: left; 
line-height: 27px; 
font-weight: bold; 
position: absolute; 
z-index: 2001; 
} 
    </style>

<script type="text/javascript">
  var ajaxbg = $("#background,#progressBar"); 
ajaxbg.hide(); 
$(document).ajaxStart(function () { 
ajaxbg.show(); 
}).ajaxStop(function () { 
ajaxbg.hide(); 
}); 
</script>
  </head>
  
  <body>
    <div id="background" class="background" style="display: none; "></div> 
    <div id="progressBar" class="progressBar" style="display: none; ">数据加载中，请稍等...</div> 
  </body>
</html>
