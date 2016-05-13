
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:useBean id="cookie" class="util.Cookie" scope="page" />

<!DOCTYPE html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Web系統登錄</title>
  <link rel="stylesheet" href="loginpage/css/login_style_ie.css">
  

  
</head>
<body>
  <section class="container">
    <div class="login" >
      <h1>WEB系統登錄</h1>
      <form  method="post" id="subform">
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
		</p>
		<p class="remember_me">
          <label>
            <input type="checkbox" name="remembered" id="remember_me" value="remembered"> 記住賬號與廠別           　　　　　
          </label>
        </p>     
        <p class="submit"><input type="button"  value="登錄" onclick="checkFact()"></p>
        
       <%-- <input type="hidden" value="<%=cookie.clearCookie()%>"/> --%>
      </form>
      <!-- <div id="bgdiv"></div> -->
    </div>  
  </section>		    

<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="loginpage/jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="loginpage/jquery/layer/layer.min.js"></script>
<script type="text/javascript">
  var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load("正在登錄,請稍等...");
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
  function checkFact() {
		var factNO = document.getElementById("factNo");
		var names = document.getElementById("user");
		var password = document.getElementById("pwd");
		var loadi;
		if (factNO.value == 0 || names.value == "" || password.value == "") {		
			alert("請輸入完整的信息");
		} else {		  		    
		    /*注意：要先提交，再顯示加載層，否則gif圖片不會動  */		    
		    //document.getElementById('subform').submit();
		    //layer.load("正在登錄中,請稍等.....");
		    /*jq(document).ajaxStart(function(){
		    	loadi=layer.load("正在登錄,請稍等.....");
		    });
		    jq(document).ajaxStop(function(){
		    	layer.close(loadi);
		    });*/
			jq.ajax({
			  type:"POST",
			  url:"userlogin",
			  dataType:"json",
			  //data:"webUsers.username="+names.value+"&webUsers.pwd="+password.value+"&factNo="+factNO.value,
			  data:jq("#subform").serialize(),
			  success:function(data){
				  if(data=='0'){
					 //location.href="test.jsp"; 
					  location.href="main.jsp"; 
				  }
				  if(data=='1'){
					  alert("當前賬號已註銷!");
				  }
				  if(data=='2'){
					  alert("廠別不正確!");
				  }
				  if(data=='3'){
					  alert("賬號或密碼不正確!");
				  }
			  },			  
			  error:function(err){
			     alert(err.responseText);
			     //document.write(err.responseText);
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
<jsp:include page="copyright_login.jsp"/>	  	
</body>
</html>
