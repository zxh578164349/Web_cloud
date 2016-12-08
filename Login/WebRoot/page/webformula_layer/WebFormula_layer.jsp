<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd-hh");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>	
	   <ul id="myTab" class="nav nav-tabs">
		 <li class="active"><a href="#tab_webformula" data-toggle="tab" id="a_webformula">配方主表</a></li>			  		 
		 <li><a href="#tab_webformaulaitems" data-toggle="tab" id="a_webformaulaitems">配方階段</a></li>	
		 <li><a href="#tab_webtabpom" data-toggle="tab" id="a_webtabpom">物性資料</a></li>		 
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="tab_webformula">				
                <div>
                  <jsp:include page="WebFormula_main_layer.jsp" />
                </div>  
			</div>
			<div class="tab-pane fade" id="tab_webformaulaitems">			   
			         <div>
					   <jsp:include page="WebFormula_items_layer.jsp" />
				     </div>			    				
			</div>
			<div class="tab-pane fade" id="tab_webtabpom">
				<div>
					<jsp:include page="WebFormula_pom_layer.jsp" />
				</div>
			</div>
	  </div>
															     
	


<script>window.jQuery|| document.write('<script src="jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script src="bootstrap/js/bootstrap.min.js"></script>	
<!--[if lt IE 9]>  
<script src="bootstrap/html5.js"></script>
<script src="bootstrap/respond.min.js"></script>
<![endif]-->
		
</body>
</html>
