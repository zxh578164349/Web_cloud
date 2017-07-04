<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<title>添加新用户</title>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>



<body>
	<form action="webuseremail_add" method="post" id="form">
		<table class="table table-condensed">		      						
					<tr>
						<td >廠別</td>
						<s:if test="useremail==null">						
						<td >
						<s:if test="#session.factNo!='tw'">						
						<input type="text" style="color:blue" name="useremail.id.factNo" value="${factNo}" readonly id="dwr_factno"/>												
						</s:if>	
						<s:if test="#session.factNo=='tw'">	
						<select style="color:blue"
							name="useremail.id.factNo" datatype="*"
							onchange="check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select>
						</s:if>
						<span id="error1"></span>
						</td>																																					
						</s:if>
						<s:else>
						   <td><input type="text" name="useremail.id.factNo" value="<s:property value='useremail.id.factNo'/>" readonly style="color:blue"/>
						   <span id="error1"></span>
						   </td>
						</s:else>
						<td >主同步人Email</td>
				        <td ><input type="text" name="useremail.id.email" datatype="e" value="<s:property value='useremail.id.email'/>" id="email" onblur="check()">									
				</td>			
				</tr>													
			<tr>
				
				<td >被同步人Email</td>
				<td ><input type="text" name="useremail.id.emailpassword"
					datatype="e" value="<s:property value='useremail.id.emailpassword'/>" id="emailPwd" onblur="check()"/>					
					</td>
					<td >主同步姓名</td>
					<td ><input type="text" name="useremail.name" value="<s:property value='useremail.name'/>" datatype="*"/></td>
						   
			</tr>						
			<tr>
			    <td >被同步人姓名</td>
			    <td ><input type="text" name="useremail.namePwd" value="<s:property value='useremail.namePwd'/>"  id="emailpwd" datatype="*"/></td>				 					
				<td>作用域</td>	
				<td>
				 <div id="email_radio">
				  <s:if test='useremail.id.typeMk==1'>
				       簽核<input type="radio" value="0" name="useremail.id.typeMk"/>&nbsp;&nbsp;
				       知會<input type="radio" value="1" name="useremail.id.typeMk" checked/>
				  </s:if>
				  <s:else>
				       簽核<input type="radio" value="0" name="useremail.id.typeMk" checked/>&nbsp;&nbsp;
				       知會<input type="radio" value="1" name="useremail.id.typeMk"/>
				  </s:else>
				 </div> 
				</td>
			</tr>			
		</table>
		<center>
			<input type="button" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;					
				<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>		

		</center>
	</form>


<script type="text/javascript">
	
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true	,
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					loadUrl("webuseremail_findPageBean");
				}else{
					layer.msg("提交失敗!",3,3);
				}				
			}
		});	
		jq("#email_radio :radio").click(function(){
			check();
		})
		goTrim();
		
	}); 

	function back() {		    
			loadUrl("webuseremail_findPageBean3?backIndex=1");		
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var email=document.getElementById("email").value;
       var emailPwd=document.getElementById("emailPwd").value;
       var typeMk=jq("input[name='useremail.id.typeMk']:checked").val();
       if(factno!=""&&email!=""&&emailPwd!=""){
          webuseremailjs.findById(factno,email,emailPwd,typeMk,function(x){
              if(x!=null){
              alert("該廠已存相同數據，請重新添加");
              document.getElementById("sub").disabled=true;
              document.getElementById("sub").value="已鎖定";
              document.getElementById("sub").style.color="red";
              document.getElementById("error1").innerHTML="<font color='color'>！</font>";
          }else{
            document.getElementById("sub").disabled=false;
            document.getElementById("sub").value="確定";
            document.getElementById("sub").style.color="white";
            document.getElementById("error1").innerHTML="";
          }        
          });               
       }                    
   }       
</script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/webuseremailjs.js'></script>
</body>
</html>
