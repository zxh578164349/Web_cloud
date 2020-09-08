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
<title></title>
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
	<form action="webuseremaila_add" method="post" id="form">
		<table class="table table-condensed">		      						
					<tr>
					<s:if test="emailobj==null">
						<td class="td_show_title">廠別</td>											
						<td class="td_input">
						<s:if test="#session.factNo!='tw'">						
						<input type="text" style="color:blue"  value="${factNo}" readonly id="dwr_factno" name="emailobj.id.factNo"/>																		
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
						<td class="td_show_title">函文類別</td>
				        <td class="td_input">
				          <select name="emailobj.id.visaSort" id="visaSort" datatype="*" onchange="check()">
				             <option value="">請選擇</option>
				          </select>									
				</td>			
				</tr>													
			<tr>
				<td class="td_show_title">主同步人Email</td>
				<td class="td_input"><input type="text" name="emailobj.id.email" datatype="e" value="<s:property value='emailobj.id.email'/>" id="email" onblur="getLow(this),check()">									
				</td>
				<td class="td_show_title">被同步人Email</td>
				<td class="td_input"><input type="text" name="emailobj.id.emailpassword"
					datatype="e" value="<s:property value='emailobj.id.emailpassword'/>" id="emailPwd" onblur="getLow(this),check()"/>					
					</td>											   
			</tr>
			</s:if>
			<s:else>
			  <tr>
			   <td class="td_show_title">廠別</td>
			   <td class="td_input"><input type="text" name="emailobj.id.factNo" value="<s:property value='emailobj.id.factNo'/>" readonly style="color:blue"/></td>			
			   <td class="td_show_title">函文類別</td>
			   <td class="td_input"><input type="text" name="emailobj.id.visaSort" value="<s:property value='emailobj.id.visaSort'/>" readonly style="color:blue"/></td>
			  </tr>
			  <tr> 
			   <td class="td_show_title">主同步人Email</td>
			   <td class="td_input"><input type="text" name="emailobj.id.email" value="<s:property value='emailobj.id.email'/>" readonly style="color:blue"/></td>
			   <td class="td_show_title">被同步人Email</td>
			   <td class="td_input"><input type="text" name="emailobj.id.emailpassword" value="<s:property value='emailobj.id.emailpassword'/>" readonly style="color:blue"/></td>	
			  </tr>
			</s:else>						
			<tr>
			<td class="td_show_title">主同步人姓名</td>
					<td class="td_input"><input type="text" name="emailobj.name" value="<s:property value='emailobj.name'/>" datatype="*"/></td>
			    <td class="td_show_title">被同步人姓名</td>
			    <td class="td_input"><input type="text" name="emailobj.namePwd" value="<s:property value='emailobj.namePwd'/>"  id="emailpwd" datatype="*"/></td>				 									
			</tr>
			<tr>
			   <td>作用域</td>
			   <td>
			     <div id="emaila_radio">
			      <s:if test='emailobj.id.typeMk==1'>
				       簽核<input type="radio" value="0" name="emailobj.id.typeMk"/>&nbsp;&nbsp;
				       知會<input type="radio" value="1" name="emailobj.id.typeMk" checked/>
				  </s:if>
				  <s:else>
				       簽核<input type="radio" value="0" name="emailobj.id.typeMk" checked/>&nbsp;&nbsp;
				       知會<input type="radio" value="1" name="emailobj.id.typeMk"/>
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

<script type='text/javascript' src='dwr/interface/webtypejs.js'></script>
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
					loadUrl("webuseremaila_findPageBean");
				}else{
					layer.msg("提交失敗!",3,3);
				}				
			}
		});	
		
		jq("#emaila_radio :radio").click(function(){
			check();
		});
		goTrim();
	    getKyType();
	}); 

	function back() {		   
		loadUrl("webuseremaila_findPageBean3?backIndex=1");
	}
	 function check_sub(factno,email,emailPwd,visaSort,typeMk){
       if(factno!=""&&email!=""&&emailPwd!=""&&visaSort!=""){
          webuseremailajs.findById(factno,email,emailPwd,visaSort,typeMk,function(x){        	  
              if(x!=null){
              alert("該類型已存在相同數據，請重新添加");
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
	 
	 function check(){
		   var factno=document.getElementById("dwr_factno").value;
	       var email=document.getElementById("email").value;
	       var emailPwd=document.getElementById("emailPwd").value;
	       var visaSort=document.getElementById("visaSort").value;
	       var typeMk=jq("input[name='emailobj.id.typeMk']:checked").val();
	       if(factno!=""&&email!=""&&emailPwd!=""){
	          webuseremailjs.findById(factno,email,emailPwd,typeMk,function(x){
	              if(x!=null){	            	  
	              alert("該廠已存相同數據，請重新添加");
	              document.getElementById("sub").disabled=true;
	              document.getElementById("sub").value="已鎖定";
	              document.getElementById("sub").style.color="red";
	              document.getElementById("error1").innerHTML="<font color='color'>！</font>";
	          }else{	        	  
	        	  check_sub(factno,email,emailPwd,visaSort,typeMk); 	            
	          }        
	          });               
	       }                    
	   } 	 
   
     function getKyType(){	 
	 var factno=document.getElementById("dwr_factno");
	 if(factno!=null){
		 if(factno.value!=""){
			 webtypejs.findByFactNo(factno.value,function(x){
			       if(x.length>0){
			          dwr.util.addOptions("visaSort",x,"webtypeMk","typeName");
			       }        
			     });
		 }
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
	
	
	
	/**Email自動轉小寫*/
	function getLow(obj){
	    obj.value=obj.value.toLowerCase();
	}
  

</script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/webuseremailajs.js'></script>
<script type='text/javascript' src='dwr/interface/webuseremailjs.js'></script>
</body>
</html>
