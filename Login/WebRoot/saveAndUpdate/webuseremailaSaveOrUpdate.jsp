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
<title>添加知會人</title>
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
</head>
<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 3,
			tipSweep:false,
			showAllError : true			
		});	
	}); 

	function back() {
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			location.href = "../webuseremaila_findPageBean";
		} else {
			location.href = "webuseremaila_findPageBean";
		}
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var email=document.getElementById("email").value;
       var emailPwd=document.getElementById("emailPwd").value;
       var visaSort=document.getElementById("visaSort").value;
       if(factno!=""&&email!=""&&emailPwd!=""){
          webuseremailjs.findById(factno,email,emailPwd,function(x){
              if(x!=null){
              alert("該知會人已存在，請重新添加");
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
   
     function getKyType(){	 
	 var factno=document.getElementById("dwr_factno").value;
	 if(factno!=null&&factno!=""){
	     webtypejs.findByFactNo(factno,function(x){
       if(x.length>0){
          dwr.util.addOptions("visaSort",x,"webtypeMk","typeName");
       }        
     });
	 }    
	}
	
	function getKyType2(factno){
	 document.getElementById("visaSort").length=1;	 	 
	 if(factno!=null&&factno!=""){
	     webtypejs.findByFactNo(factno,function(x){
       if(x.length>0){
          dwr.util.addOptions("visaSort",x,"webtypeMk","typeName");
       }        
     });
	 }    
	}
	
	function check(){
	    var dwr_factno=document.getElementById("dwr_factno").value;	    
	    var email=document.getElementById("email").value;
	    var emailPwd=document.getElementById("emailPwd").value;
	    var visaSort=document.getElementById("visaSort").value;
	    if(dwr_factno!=""&&visaSort!=""&&email!=""&&emailPwd!=""){
	       webuseremailajs.findById(dwr_factno,email,emailPwd,visaSort,function(x){
	              if(x!=null){
	                 alert("該函文類型的知會人已存在，請重新添加");
	                 document.getElementById("sub").disabled=true;
                     document.getElementById("sub").style.color="red";
	              }else{
	                 document.getElementById("sub").disabled=false;
                     document.getElementById("sub").style.color="white";
	              }
	       })
	    }
	}
	
	/**Email自動轉小寫*/
	function getLow(obj){
	    obj.value=obj.value.toLowerCase();
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
<script type='text/javascript' src='/Login/dwr/interface/webtypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webuseremailajs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body onload="getKyType()">
	<form action="webuseremaila_add" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
		　　<caption>新添知會人</caption>			      						
					<tr>
						<td class="td_show_title">廠別</td>
						<s:if test="emailobj==null">						
						<td class="td_input">
						<s:if test="#session.factNo!='tw'">						
						<input type="text" style="color:blue"  value="${factNo}" readonly id="dwr_factno"/>																		
						</s:if>	
						<s:if test="#session.factNo=='tw'">	
						<select style="color:blue"
							name="emailobj.id.factNo" datatype="*"
							onchange="getKyType2(this.value),check()" id="dwr_factno">
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
						   <td><input type="text" name="emailobj.id.factNo" value="<s:property value='emailobj.id.factNo'/>" readonly style="color:blue"/>
						   <span id="error1"></span>
						   </td>
						</s:else>
						<td class="td_show_title">函文類別</td>
				        <td class="td_input">
				          <select name="emailobj.id.visaSort" id="visaSort" datatype="*" onchange="check()">
				             <option value="">請選擇</option>
				          </select>									
				</td>			
				</tr>													
			<tr>
				<td class="td_show_title">主簽人Email</td>
				        <td class="td_input"><input type="text" name="emailobj.id.email" datatype="e" value="<s:property value='emailobj.id.email'/>" id="email" onblur="getLow(this),check()">									
				</td>
				<td class="td_show_title">備簽人Email</td>
				<td class="td_input"><input type="text" name="emailobj.id.emailpassword"
					datatype="e" value="<s:property value='emailobj.id.emailpassword'/>" id="emailPwd" onblur="getLow(this),check()"/>					
					</td>
					
						   
			</tr>						
			<tr>
			<td class="td_show_title">主簽人姓名</td>
					<td class="td_input"><input type="text" name="emailobj.name" value="<s:property value='emailobj.name'/>"/></td>
			    <td class="td_show_title">备签人姓名</td>
			    <td class="td_input"><input type="text" name="emailobj.namePwd" value="<s:property value='emailobj.namePwd'/>"  id="emailpwd"/></td>				 					
					
			</tr>			
		</table>
		<center>
			<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;
			<!-- <s:if test="feed!=null">
				<input type="button" value="返回" id="btn_back"
					onclick="javascript:location.href='webbackfeed_findPageBean'" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</s:if>
			<s:else>
				<input type="button" value="返回" onclick="back()" id="btn_back" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</s:else> -->

		</center>
	</form>

</body>
</html>
