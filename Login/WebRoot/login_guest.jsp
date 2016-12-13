<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:useBean id="cookie" class="util.Cookie" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<!-- General meta information -->
<title>Web系統登錄</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="robots" content="index, follow" />
<meta charset="utf-8" />
<!-- Load stylesheets -->	
</head>
<body >
	 <div id="wrapper">
		<div id="wrappertop"></div>

		<div id="wrappermiddle">
			<form  method="post" id="loginform">
				<h2>Web系統登錄  <s:property value='userName'/></h2>
				<div id="username_input">
					<div id="username_inputleft"></div>
					<div id="username_inputmiddle">
						<input type="text" name="webUsers.username" id="url_username_input" value="<%=cookie.getCookName() %>" onclick="this.value = ''" />
						<img id="url_user" src="loginpage/images/login2/username_icon.jpg" alt="user" title="賬號" />							
					</div>
					<div id="username_inputright"></div>
				</div>
				<div id="password_input">
					<div id="password_inputleft"></div>
					<div id="password_inputmiddle">
						<input type="password" name="webUsers.pwd" id="url_pwd_input" value="" onclick="this.value = ''" />
						<img id="url_password" src="loginpage/images/login2/pwd_icon.jpg" alt="pwd" title="密碼"/>							
					</div>
					<div id="password_inputright"></div>
				</div>
				<!--<div id="factno_select">
					<div id="factno_selectleft"></div>
					<div id="factno_selectmiddle">
						<select id="url_factno" name="factNo">
						    <s:if test="factNo!=null">
								<option value="<s:property value='factNo'/>">
									<s:property value="factname" />&nbsp;(<s:property value="factNo" />)																											
								</option>
							</s:if>
							<option value="<%=cookie.getCookFactNo() %>"><%=cookie.getCookFactNo() %></option> 
							<option value=0>請選擇廠別</option>
							<option value="tw">TW</option>
							<s:iterator value="#session.facts" id="temp">
								<option value="${temp[0]}">
									${temp[1]}&nbsp;(${temp[0]})</option>
							</s:iterator>
						</select>
						<img id="url_password" src="loginpage/images/login2/factno1.png" alt="fa" title="廠別" />																			
					</div>
					<div id="factno_selectright"></div>
					<br/>
					<span id="sfact"><s:property value='factError' /></span>
				</div>-->
				<input type="hidden" name="factNo" value="1" id="url_factno"/>
				<br/>
				<div id="submit">
					
					<input type="button" value="登  錄" id="submit1" onclick="checkFact('userlogin_guest')"/>
					<input type="button" value="登  錄" id="submit2" />						
				</div>
				<div id="remembered"><input type="checkbox" value="remembered" name="remembered"/>記住賬號與廠別</div>
			</form>		
		</div>
		<div id="wrapperbottom"></div>	
	</div>
	
	
	



		
</body>
</html>