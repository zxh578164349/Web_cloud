<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<style type="text/css">
  .div_border_green{
    width:100%;height:250px;overflow:auto;border:1px dashed green;text-align:left
  }
  .div_border_blue{
    width:100%;height:250px;overflow:auto;border:1px dashed blue;text-align:left
  }
  
#myTabContent ul{margin:0px;padding:0px}
.list{margin:0px 0px; width:127%;}  
.list li.columnhead,.list li.col_item,.list li.col_item4{font-size: 12px;font-weight:bold;}  
.list li,.list li.columnhead{  
    width:15%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
} 
.list li.col_item,.list li.col_item2{
   width:16%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}
.list li.col_item3,.list li.col_item4{
  height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}

</style>

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
                  <jsp:include page="WebFormulaSaveOrUpdate2_main.jsp" />
                </div>  
			</div>
			<div class="tab-pane fade" id="tab_webformaulaitems">			   
			         <div>
					   <jsp:include page="WebFormulaSaveOrUpdate2_items.jsp" />
				     </div>			    				
			</div>
			<div class="tab-pane fade" id="tab_webtabpom">
				<div>
					<jsp:include page="WebFormulaSaveOrUpdate2_pom.jsp" />
				</div>
			</div>
	  </div>
															     
	


<script type="text/javascript">
		
/*禁止空格輸入*/
jq(function(){
	goTrim();
});
function back(){	
	loadUrl("/Login/webformula_findPageBean3?backIndex=1");
}

</script>
</body>
</html>
