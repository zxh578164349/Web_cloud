
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:useBean id="cookie" class="util.Cookie" scope="page" />


<!DOCTYPE html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Web系統登錄</title>
  <link rel="stylesheet" href="css/login_style_ie.css">
  <style type="text/css">
    body{background-image:url(images/bg.jpg);}
  </style>
  <script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="jquery/layer/layer.min.js"></script>
  <script type="text/javascript">
     function checkFact() {
		var factNO = document.getElementById("factNo");
		var names = document.getElementById("user");
		var password = document.getElementById("pwd");
		if (factNO.value == 0 || names.value == "" || password.value == "") {		
			alert("請輸入完整的信息");
		} else {
		    //var img=new Image();
		    //new Image().src="images/loading006.gif";
		    //document.getElementById("mydiv").style.background="url(/images/loading006.gif)";
		    
		    /*注意：要先提交，再顯示加載層，否則gif圖片不會動  */
		    
		    document.getElementById('form').submit();
		    layer.load("正在登錄中,請稍等.....");								
			//document.getElementById('mydiv').style.display='block';	
											
			//img.src="images/loading006.gif";       
						
			 
			/*  $.ajax({
			  type:"POST",
			  url:"userlogin",
			  dataType:"html",
			  data:"webUsers.username="+names.value+"&webUsers.pwd="+password.value+"&factNo="+factNO.value,
			  beforeSend:function(){
			     document.getElementById("mydiv").style.display="block";
			  },
			  success:function(data){
			      $("body").html(data);
			  },
			  complete:function(){
			    document.getElementById("mydiv").style.display="none";
			  },
			  error:function(err){
			     alert(err);
			  }
			
			}); */	 									      	
		}						
	}
	
	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			checkFact();
		}
	});

  </script>
  
</head>
<body>
  <section class="container">
    <div class="login" >
      <h1>WEB系統登錄</h1>
      <form action="userlogin" method="post" id="form">
        <p>
        <label id="label_name">用戶名</label>
        <input type="text" name="webUsers.username" value="<%=cookie.getCookName()%>"  id="user"  >                 
        </p>
       
        <p>
        <label id="label_pwd">密&nbsp;&nbsp;&nbsp;碼</label>
        <input type="password" name="webUsers.pwd" value=""  id="pwd" >
        </p>		
		<p>
		    <label id="label_factno">廠&nbsp;&nbsp;&nbsp;別</label>
		    <select id="factNo" name="factNo" >
		                    <s:if test="factNo!=null">
								<option value="<s:property value='factNo'/>">
									<s:property value="factname" />&nbsp;(<s:property value="factNo" />)																											
								</option>
							</s:if> 
							    <option value="<%=cookie.getCookFactNo()%>"><%=cookie.getCookFactNo()%></option>	
		    　　　　　　　　　　　　<option value="0">請選擇廠別</option>		    　　　　　　　　　　　　																
								<option value="tw">TW</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>
								</s:iterator>
						</select><br>
						<span id="sfact"><s:property value='factError' />&nbsp;</span>									
		</p>
		<p class="remember_me">
          <label>
            <input type="checkbox" name="remembered" id="remember_me" value="remembered"> 記住賬號與廠別           　　　　　
          </label>
        </p>     
        <p class="submit"><input type="button" name="commit" value="登錄" onclick="checkFact()"></p>
        
       <%-- <input type="hidden" value="<%=cookie.clearCookie()%>"/> --%>
      </form>
      <!-- <div id="bgdiv"></div> -->
    </div>
    
  </section>		    
	<!-- <div id="mydiv">      
		<p>		
			<img alt="" src="images/loading006.gif" id="img_loading"><br> Loading....
		</p>		
	</div>	 -->
	   <div class="copyright" >Copyright © 2014,東莞加元鞋材制品有限公司,All Rights Reserved</div>	  	
</body>
</html>
