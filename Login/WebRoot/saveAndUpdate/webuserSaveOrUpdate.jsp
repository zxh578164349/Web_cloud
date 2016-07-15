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

<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
</head>
<script type="text/javascript">
	
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="/Login/userfindPageBean";
					loadUrl("/Login/userfindPageBean");
				}else{
					alert(data.responseText);
				}				
			}
		});	
	}); 

	function back() {		    
			loadUrl("/Login/userfindPageBean");		
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var username=document.getElementById("username").value;
       if(factno!=""&&username!=""){
          userjs.findByIdDwr2(factno,username,function(x){
              if(x!=null){
              alert("该厂已存在("+username+")");
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
 function checkEmail(){
       var factno=document.getElementById("dwr_factno").value;
       var email=document.getElementById("email").value;
       if(factno!=""&&email!=""){
          userjs.findUserByFactNoAEmail(factno,email,function(x){
              if(x!=null){
              alert("当前Email("+email+")已被使用");
              document.getElementById("sub").disabled=true;
              document.getElementById("sub").value="已鎖定";
              document.getElementById("sub").style.color="red";
              document.getElementById("error2").innerHTML="<font color='color'>！</font>";
          }else{
            document.getElementById("sub").disabled=false;
            document.getElementById("sub").value="確定";
            document.getElementById("sub").style.color="white";
            document.getElementById("error2").innerHTML="";
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
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<!-- <script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script> -->


<body>
	<form action="useradd" method="post" id="form">
		<table class="table table-condensed">
		　　<h2>新添帳號</h2>	
		        <tr>
					<td class="td_show_title">帳號</td>
					<td class="td_input"><input type="text" name="webUsers.username"
						 datatype="*1-50" id="username" onblur="check()"/><span id="error1"></span></td>
					<td class="td_show_title">密碼</td>
					<td class="td_input"><input type="text" name="webUsers.pwd"
						value="" datatype="*3-20">
						</td>
				</tr>			
				
					<tr>
						<td class="td_show_title">廠別</td>
						<s:if test="#session.factNo!='tw'">
						<td class="td_input"><input type="text" style="color:blue"
							name="webUsers.factno" value="${factNo}" readonly id="dwr_factno"/></td>
						</s:if>	
						<s:if test="#session.factNo=='tw'">	
						<td class="td_input"><select style="color:blue"
							name="webUsers.factno" datatype="*"
							onchange="check(),checkEmail()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<option value="tw">TW</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>	
						</s:if>	
						<td class="td_show_title">姓名</td>
				        <td class="td_input"><input type="text" name="webUsers.name" datatype="*1-50" value="">									
				</td>			
				</tr>													
			<tr>
				
				<td class="td_show_title">Email</td>
				<td class="td_input"><input type="text" name="webUsers.email"
					datatype="e" value="" id="email" onblur="checkEmail()"/><span id="error2"></span>					
					</td>
					<td class="td_show_title">工號</td>
					<td class="td_input"><input type="text" name="webUsers.workno"
						 datatype="*1-20"  /></td>
			</tr>			
			<tr>
					
					<td class="td_show_title">IP</td>
					<td class="td_input"><input type="text" name="webUsers.ip" value="" >						
					</td>
					<td class="td_show_title">是否只讀</td>
					<td class="td_input">
					  <select name="webUsers.userread">
					      <option value="0">否</option>
					      <option value="1">是</option>
					  </select>
						<input type="hidden" name="webUsers.available" value="0"/>
						</td>					
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
