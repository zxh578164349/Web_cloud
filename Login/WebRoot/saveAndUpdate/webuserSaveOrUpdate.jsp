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
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
</head>
<script type="text/javascript">
	var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load("正在提交,請稍等...");
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
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
					location.href="/Login/userfindPageBean";
				}else{
					alert(data.responseText);
				}				
			}
		});	
	}); 

	function back() {
		    layer.load("正在返回,請稍等...");
			location.href = "/Login/userfindPageBean";		
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
window.onload=function(){            
            var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            }  
        } 
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webbackfeetjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="useradd" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
		　　<caption>新添帳號</caption>	
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
			<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;						
				<input type="button" value="返回" onclick="back()" id="btn_back" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>			

		</center>
	</form>

</body>
</html>
