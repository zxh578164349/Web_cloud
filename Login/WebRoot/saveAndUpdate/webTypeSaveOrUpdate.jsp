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

	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var typeno=document.getElementById("typeno").value;
       if(factno!=""&&typeno!=""){
          webtypejs.findById(factno,typeno,function(x){
              if(x!=null){
              alert("該類別已存在");
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
	   kytypejs.findByTypeNo("VV",function(x){
	         dwr.util.addOptions("typeno",x,"typeName","typeSname");
	   });
	}
	function getTypeName(){
	    var typeno=document.getElementById("typeno").value;
	    if(typeno!=null||typeno!=""){
	       kytypejs.getTypeSname("VV",typeno,function(x){
	         if(x!=null){
	             document.getElementById("typeName").value=x;
	         }else{
	              document.getElementById("typeName").value="";
	         }
	    })
	    }	    	   
	}
  
  	function checkType(type){
	   if(type.value.charAt(0)=='C'||type.value.charAt(0)=='c'){
	      alert("不可以使用C字母开头");
	      type.value='';
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
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webtypejs'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="webtype_add" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
		　　<caption>各廠函文類別管理</caption>			      						
					<tr>
						<td class="td_show_title">廠別</td>
						<s:if test="webtype==null">						
						<td class="td_input">
						<s:if test="#session.factNo!='tw'">						
						<input type="text" style="color:blue" name="webtype.id.factNo" value="${factNo}" readonly id="dwr_factno"/>												
						</s:if>	
						<s:if test="#session.factNo=='tw'">	
						<select style="color:blue"
							name="webtype.id.factNo" datatype="*"
							onchange="check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select>
						</s:if>						
						</td>																																					
						</s:if>
						<s:else>
						   <td><input type="text" name="webtype.id.factNo" value="<s:property value='webtype.id.factNo'/>" readonly style="color:blue"/>
						   </td>
						</s:else>
						<td class="td_show_title">函文類別</td>
				        <td class="td_input">
				          <s:if test="webtype==null">
				             <!-- <select name="webtype.id.typeNo" id="typeno" datatype="*" onchange="check(),getTypeName()" >
				                  <option value="">請選擇類別</option>
				             </select> -->
				             <input type="text" name="webtype.id.typeNo" datatype="s1-4" onblur="check(),checkType(this)" id="typeno"/>
				             <span id="error1"></span>	
				          </s:if>
				          <s:else>
				              <input type="text" name="webtype.id.typeNo" value="<s:property value='webtype.id.typeNo'/>" readonly style="color:blue"/>
				          </s:else>			             				            								
				         </td>			
				</tr>													
			<tr>
				
				<td class="td_show_title">類別名称  </td>
				<td class="td_input"><input type="text" name="webtype.typeName"
					 value="<s:property value='webtype.typeName'/>" id="typeName"  datatype="*1-60"/>
					<input type="hidden" name="webtype.webtypeMk" value="Y"/> 					
				</td>
				<!-- <td class="td_show_title">是否可用</td>
				<td class="td_input">				
				          可用<input type="radio" name="webtype.webtypeMk"
					 value="0" checked/>
					 <s:if test="webtype.webtypeMk==null||webtype.webtypeMk==''">
					    不可用<input type="radio" name="webtype.webtypeMk"
					 value="1"/> 
					 </s:if>
					 <s:else>
					    不可用<input type="radio" name="webtype.webtypeMk"
					 value="1" checked/>
					 </s:else>					
				</td>	 -->   
			</tr>											
		</table>
		<center>
			<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;			

		</center>
	</form>

</body>
</html>
