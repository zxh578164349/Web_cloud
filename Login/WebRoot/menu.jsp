<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>My JSP 'menu.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

 <!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->

<script type="text/javascript">
	function showDiv(index) {
		var sts = document.getElementById("a" + index).innerHTML;
		if (sts.indexOf("退出管理") == 0) {
			//layer.load("正在退出,请稍等...");
			parent.location.href = "judge.jsp?bj=1";
		}
		var divName = document.getElementById(index);
		var img = document.getElementById("img" + index);
		if (divName.style.display == "none") {
			$("#"+index).show();
			img.src = "image/folderopen.gif";
		} else {
			$("#"+index).hide(100);
			img.src = "image/folder.gif";
		}
	}
$(document).ready(function(){
   $("a[name='alink']").click(function(){
   $("a[name='alink']").removeClass("linkbg");
   $(this).addClass("linkbg");
});
})	
	
</script>
</head>
<style>


/*通用样式--容器宽度值*/
.bigfont{font-size:18px;font-weight:bold;}
.linkbg{background:#F38630}
.smenu_font{font-size:12px}
.mmenu_font{font-size:14px}
a{vertical-align:bottom}
a:link{color:#335AA4;text-decoration:none;}
a:visited{color:#335AA4; text-decoration:none} 
a:hover{text-decoration:none; border-bottom:1px red solid;color:red;}
/*a:active {color: #0000FF} */ 


</style>
<body>		
	
        <div class="panel panel-info">
          <div class="panel-heading">
              <a href="right.jsp" style="border-bottom:0px" title="返回首頁" target="show" onclick="window.parent.showPop()">					      
					        <img alt="返回首頁" src="images/files.gif" style="border:0px"></a>				
					        <a href="right.jsp" style="color:black;" title="返回首頁" target="show" onclick="window.parent.showPop()">
						              網站首頁</a>
          </div>              
            <div class="panel-body">            　　　　　　　　　　
              　　　　
			<s:iterator value="#session.loginUser.webJurisdictions" status="x"
				id="menu">
				<div>
					<img style="border: 0px;" id="img${x.index}" src="image/folder.gif">							
					<a href="javascript:showDiv(${x.index})" class="mmenu_font">						
						<span id="a${x.index}"><s:property value="webMenu.menuname" /></span>
					</a>									
						<div id="${x.index}" style="display:none;height:auto; ">														
								<s:iterator value="webSubmenus" status="x">																		
										  <div style="height:24px;overflow:hidden">
											<a href="<s:property value="address"/>?type=<s:property value='subtype'/>" 
											style="border-bottom:0px" target="show" onclick="window.parent.showPop()">
											  <img style="border:0px;" src="images/files.gif"> 												
											</a>
											<a name="alink"
												href="<s:property value="address"/>?type=<s:property value='subtype'/>"
												  target="show"  class="smenu_font"
												onclick="window.parent.showPop()" title="<s:property value='submenuname'/>">												
												(${x.index+1})<s:property value="submenuname"/>																																			
											</a>
										  </div>																												
								</s:iterator>
							
						</div>
				</div>
			</s:iterator>
		
              
              </div>
        </div>
    
</body>
</html>
