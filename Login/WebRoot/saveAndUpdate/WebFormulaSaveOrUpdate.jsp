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
<link rel="stylesheet" type="text/css" href="css/form.css" />
<style type="text/css">
  .div_border_green{
    width:95%;height:250px;overflow:auto;text-align:left
  }
  .div_border_blue{
    width:95%;height:250px;overflow:auto;text-align:left
  }
  
#myTabContent_item ul{margin:0px;padding:0px}
.list_item{margin:0px 0px; width:100%;}  
.list_item li.columnhead,.list_item li.columnhead2{font-size: 12px;font-weight:bold;}  
.list_item li,.list_item li.columnhead{  
    width:16%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}
.list_item li.column2,.list_item li.columnhead2{  
    width:26%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}  
.list_item li input[type=text],.list_item li select{  
   width:100% ; 
}
.list_item li.column2 input[type=text]{
   width:93%;
}
   
  




</style>

</head>

<body>
	
	
	   <ul id="myTab" class="nav nav-tabs">
		 <li class="active"><a href="#tab_webformula" data-toggle="tab" id="a_webformula">配方資料</a></li>			  		 
		 <!-- <li><a href="#tab_webformaulaitems" data-toggle="tab" id="a_webformaulaitems">配方階段</a></li> -->	
		 <li style="display:none" id="li_webtabpom"><a href="#tab_webtabpom" data-toggle="tab" id="a_webtabpom">物性資料</a></li>		 
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="tab_webformula">				
                <div>
                  <jsp:include page="WebFormulaSaveOrUpdate_main.jsp" />
                </div>  
			</div>
			<!-- <div class="tab-pane fade" id="tab_webformaulaitems">			   
			         <div>
					   <jsp:include page="WebFormulaSaveOrUpdate_items.jsp" />
				     </div>			    				
			</div> -->
			<div class="tab-pane fade" id="tab_webtabpom">
				<div>
					<jsp:include page="WebFormulaSaveOrUpdate_pom.jsp" />
				</div>
			</div>
	  </div>
															     
	


<script type="text/javascript">
		
/*禁止空格輸入*/
jq(function(){
	goTrim();
});
function back(){	
	loadUrl("webformula_findPageBean3?backIndex=1");
}

</script>
</body>
</html>
