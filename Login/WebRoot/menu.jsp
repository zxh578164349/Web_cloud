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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	function showDiv(index) {
		var sts = document.getElementById("a" + index).innerHTML;
		if (sts.indexOf("退出管理") == 0) {
			parent.location.href = "judge.jsp?bj=1";
		}
		var divName = document.getElementById(index);
		var img = document.getElementById("img" + index);
		if (divName.style.display == "none") {
			//divName.style.display = "block";
			$("#"+index).show(300);
			img.src = "image/folderopen.gif";
		} else {
			//divName.style.display = "none";
			$("#"+index).hide(300);
			img.src = "image/folder.gif";
		}
	}
$(document).ready(function(){
   $("a[name='alink']").click(function(){
   $("a[name='alink']").attr("class", "");
   $(this).attr("class", "bigfont");
});
})	

	
</script>
</head>
<style>


/*通用样式--容器宽度值*/
.bigfont{font-size:16px;font-weight:bold;color:green}
.sharp{width:100%;float:left;text-align:center}
.sharp .content div{padding-left:15px;text-indent:2em;}
content{height:auto;}
a:link{color:#335AA4;text-decoration:none}
a:visited{color:#335AA4; text-decoration:none;} 
a:hover{text-decoration:none; border-bottom:1px red solid;color:red;}
/* a:active {color: #0000FF} */   

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
.color1 .b2,.color1 .b3,.color1 .b4,.color1 .b5,.color1 .b6,.color1 .b7,.color1 .content{background:#F8FAFD;}
</style>
<body>		
	<div id="left" class="sharp">	
	　　<div class="sharp color1">
        <b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b> 
        <div class="content">              
              <div>            　　　　　　　　　　
              　　　　　　　<table width="155px" style="margin-left:0px; line-height:28px">
			         <thead style="font-weight:bolder;font-size:14px">
				       <tr>
					     <td colspan="2">
					        <a href="right.jsp" style="border-bottom:0px"
						      title="返回首頁" target="show">
					        <img alt="返回首頁" src="images/files.gif" style="border:0px"></a>&nbsp;&nbsp;					
					        <a href="right.jsp" style="color:black;"
						      title="返回首頁" target="show">網站首頁</a>				
					</td>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="#session.loginUser.webJurisdictions" status="x"
				id="menu">
				<tr>
					<td colspan="2" style="padding-left:2px;">
					<img style="border: 0px;" id="img${x.index}" src="image/folder.gif">							
					<a
						href="javascript:showDiv(${x.index})" style="color:#335AA4;">
							<span id="a${x.index}"><s:property
									value="webMenu.menuname" /> </span> </a>
						<div id="${x.index}"
							style="display:none;width:150px;height:auto; padding-left:2px; ">
							<table style="line-height: 20px;">
								<s:iterator value="webSubmenus" status="x">
									<s:if test='address=="fix_findPageBean"'>
										<tr>
											<td>
											<a href="<s:property value="address"/>?type=<s:property value='subtype'/>" 
											style="border-bottom:0px" target="show" onclick="window.top.showPop()">
											  <img style="border:0px;" src="images/files.gif"> 												
											</a>
											<a name="alink"
												href="<s:property value="address"/>?type=<s:property value='subtype'/>"
												 style="font-size:12px" target="show"
												onclick="window.top.showPop()">(${x.index+1})<s:property
														value="submenuname" /> </a>
											</td>
										</tr>
									</s:if>
									<s:else>
										<tr>
											<td>
											<a href="<s:property value="address"/>?type=<s:property value='subtype'/>" 
											style="border-bottom:0px" target="show">
											<img style="border: 0px;" src="images/files.gif"></a>													
											<a name="alink"
												href="<s:property value="address"/>?type=<s:property value='subtype'/>"
												style="font-size:12px" target="show"
												>(${x.index+1}) <s:property
														value="submenuname" /> </a>
											</td>
										</tr>
									</s:else>

								</s:iterator>
							</table>
						</div></td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
              
              </div>
        </div>
        <b class="b5"></b><b class="b6"></b><b class="b7"></b><b class="b8"></b>    
    　　　</div>
	
	
	
		
	</div>
</body>
</html>
