
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>Web系統</title>
<head>
<link href="css/main.css" rel="stylesheet">
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

 <!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->
</head>

<script>
var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load("請稍等...");
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
					
		function switchSysBar() {			
			if (jq("#t_index").text()=="＜＜＜") {
			    jq("#left").hide(100);
			    
			    jq("#t_index").text("＞＞＞");											
			} else {
			    jq("#left").show(100);
			    jq("#t_index").text("＜＜＜") ;
			   
			}
		}
		function showPop() {
			jq("#mydiv").show();						
			//var loadi=layer.load("正在加載....");
			var ifr=document.getElementById("show");
			if (ifr.attachEvent){
			    ifr.attachEvent("onload", function(){
			        //layer.close(loadi);
			        jq("#mydiv").hide();
			    });
			} else {
			    ifr.onload = function(){
			        //layer.close(loadi);
			        jq("#mydiv").hide();
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
    
    
	function showDiv_main(index) {
		var sts = document.getElementById("a" + index).innerHTML;
		if (sts.indexOf("退出管理") == 0) {
			parent.location.href = "judge.jsp";
		}
		var divName = document.getElementById(index);
		var img = document.getElementById("img" + index);
		if (divName.style.display == "none") {
			jq("#"+index).show();
			img.src = "image/folderopen.gif";
		} else {
			jq("#"+index).hide(100);
			img.src = "image/folder.gif";
		}
	}
jq(document).ready(function(){
   jq("a[name='alink']").click(function(){
   jq("a[name='alink']").removeClass("linkbg");
   jq(this).addClass("linkbg");
});
});

function findPageBean(url){
   jq.ajax({
     type:"POST",
     dataType:"html",
     url:url,
     success:function(data){   
        jq("#r_content").html(data);
     },
     error:function(error){
        jq("#r_content").html(error);
     }
   });
}	
</script>
<body >
	<div >
	
		   <div id="top">
		      <h1 >東莞加元鞋材制品有限公司</h1>						    							
						
						 <div style="float:right;color:#99CCCC">  登录人：<s:property value="#session.loginUser.name" />(<s:if test="#attr.factNo=='tw'">所有數據</s:if>								
								 <s:else> <s:property value="#attr.factName" /></s:else> ),欢迎您 ！								
							     <a id="exit" href="javascript:back()" target="_parent" style="color:#FF6666">
							     退出登录</a></div>
						
					    <div id="currentTime" style="font-size:12px;color:#99CCCC;background:#292929"></div> 
		   </div>
		   <div id="left">
					<div class="panel panel-info">
						<div class="panel-heading">
							<a href="right.jsp" style="border-bottom:0px" title="返回首頁"
								target="show" onclick="window.parent.showPop()"> <img
								alt="返回首頁" src="images/files.gif" style="border:0px">
							</a> <a href="right.jsp" style="color:black;" title="返回首頁"
								target="show" onclick="window.parent.showPop()"> 網站首頁</a>
						</div>
						<div class="panel-body">
							<s:iterator value="#session.loginUser.webJurisdictions"
								status="x" id="menu">
								<div>
									<img style="border: 0px;" id="img${x.index}"
										src="image/folder.gif"> <a
										href="javascript:showDiv_main(${x.index})" class="mmenu_font">
										<span id="a${x.index}"><s:property
												value="webMenu.menuname" />
									</span> </a>
									<div id="${x.index}" style="display:none">
										<s:iterator value="webSubmenus" status="x">
											<div style="overflow:hidden">
												<a
													href="<s:property value="address"/>?type=<s:property value='subtype'/>"
													style="border-bottom:0px" target="show"
													onclick="window.parent.showPop()"> <img
													style="border:0px;" src="images/files.gif"> </a> <a
													name="alink"
													href="javascript:findPageBean('<s:property value="address"/>')"
													 class="smenu_font"
													
													title="<s:property value='submenuname'/>">
													(${x.index+1})<s:property value="submenuname" /> </a>
											</div>
										</s:iterator>
									</div>
								</div>
							</s:iterator>
						</div>
					</div>
			</div>
			
			<div id="right">
			   <div id="toolbar">
			   <span id="t_index" style="display:none">＜＜＜</span>
			   <a href="javascript:switchSysBar()"><img src="images/icon/arrow.jpg" border="0"/></a>
			   </div>
			   
			   <div id="r_content">
			      
			   </div>
			</div>
		   
		   <div id="bottom">
		      
		      <jsp:include page="copyright.jsp"/>
		   </div>		   	
</div>
 	
</body>

</html>
