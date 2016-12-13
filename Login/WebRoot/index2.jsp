<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:useBean id="cookie" class="util.Cookie" scope="page" />
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Web系統登錄</title>
</head>
<body>
  <section class="container">
    <div class="login" >
      <h1>WEB系統登錄</h1>
      <form  method="post" id="loginform">
        <p>
        <label id="label_name">用戶名</label>
        <input type="text" name="webUsers.username" value="<%=cookie.getCookName()%>"  id="url_username_input"  >                 
        </p>
       
        <p>
        <label id="label_pwd">密&nbsp;&nbsp;&nbsp;碼</label>
        <input type="password" name="webUsers.pwd" value=""  id="url_pwd_input" >
        </p>		
		<p>
		    <label id="label_factno">廠&nbsp;&nbsp;&nbsp;別</label>
		    <select id="url_factno" name="factNo" >
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
        <p class="submit"><input type="button"  value="登錄" id="submit1" onclick="checkFact('userlogin')"></p>
        
       <%-- <input type="hidden" value="<%=cookie.clearCookie()%>"/> --%>
      </form>
    </div>  
  </section>		     	  	
</body>
</html>
