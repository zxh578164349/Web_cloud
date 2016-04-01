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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
</head>
<script type="text/javascript">
	
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:false,
			showAllError : true	,
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					location.href="/Login/webuseremail_indPageBean";
				}else{
					alert(data.responseText);
				}				
			}
		});	
	}); 

	function back() {
		    
			loadUrl("/Login/webuseremail_findPageBean3?backIndex=1");		
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var email=document.getElementById("email").value;
       var emailPwd=document.getElementById("emailPwd").value;
       if(factno!=""&&email!=""&&emailPwd!=""){
          webuseremailjs.findById(factno,email,emailPwd,function(x){
              if(x!=null){
              alert("該備簽人已存在，請重新添加");
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
  
/*禁止空格輸入*/
/* window.onload=function(){            
            var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            }  
        } */

jq(function(){
   var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            }
});        
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webbackfeetjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webuseremailjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="webuseremail_add" method="post" id="form">
		<table class="table table-condensed">
		　　<h2>新添備簽人</h2>			      						
					<tr>
						<td class="td_show_title">廠別</td>
						<s:if test="useremail==null">						
						<td class="td_input">
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
						<td class="td_show_title">主簽人Email</td>
				        <td class="td_input"><input type="text" name="useremail.id.email" datatype="e" value="<s:property value='useremail.id.email'/>" id="email" onblur="check()">									
				</td>			
				</tr>													
			<tr>
				
				<td class="td_show_title">備簽人Email</td>
				<td class="td_input"><input type="text" name="useremail.id.emailpassword"
					datatype="e" value="<s:property value='useremail.id.emailpassword'/>" id="emailPwd" onblur="check()"/>					
					</td>
					<td class="td_show_title">主簽人姓名</td>
					<td class="td_input"><input type="text" name="useremail.name" value="<s:property value='useremail.name'/>"/></td>
						   
			</tr>						
			<tr>
			    <td class="td_show_title">备签人姓名</td>
			    <td class="td_input"><input type="text" name="useremail.namePwd" value="<s:property value='useremail.namePwd'/>"  id="emailpwd"/></td>				 					
					
			</tr>			
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;					
				<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>		

		</center>
	</form>

</body>
</html>
