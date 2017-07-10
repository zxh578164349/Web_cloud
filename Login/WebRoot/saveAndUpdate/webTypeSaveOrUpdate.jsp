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
	<form action="webtype_add" method="post" id="form">
		<table class="table table-condensed">		      						
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
										
				        <td class="td_show_title">類別代號</td>
				        <td class="td_input">
				          <s:if test="webtype==null">
				             <div id="div_typeno">
				              <input type="radio" value="TR" name="typeNo" onclick="checkRadio(),check(this)" id="typeno_tr"/>出差類&nbsp;
				              <input type="radio" value="PF" name="typeNo" onclick="checkRadio(),check(this)" id="typeno_tr"/>配方類&nbsp;
				              <input type="radio" value="0" name="typeNo" checked onclick="checkRadio()"/>其它類				             
				             <input type="text" name="webtype.id.typeNo" datatype="s2-2" onblur="check(this),checkType(this)" id="typeno"/>
				            <span id="error1"></span>
				             </div>				              
				          </s:if>
				          <s:else>
				              <input type="text" name="webtype.id.typeNo" value="<s:property value='webtype.id.typeNo'/>" readonly style="color:blue"/>
				          </s:else>			             				            								
				         </td>							
				</tr>													
			    <tr>
 				          <td class="td_show_title">類別名称  </td>
				          <td class="td_input"><input type="text" name="webtype.typeName"
					           value="<s:property value='webtype.typeName'/>" id="typeName"  datatype="*1-60" />
					         <input type="hidden" name="webtype.webtypeMk" value="Y"/>
					         <input type="hidden" name="webtype.delMk" value="0"/><!-- 默認未刪除 -->
					         <input type="hidden" name="typeNo" value="<s:property value='webtype.typeMk'/>"/>					
				          </td>
				          
				         <!--  <td class="td_show_title">是否分部門</td>
				          <td class="td_input">
				           <s:if test='webtype.trMk=="Y"'>
				                                       是<input type="radio" name="webtype.trMk" value="Y" checked datatype="*"/>				                                      
				           </s:if>
				           <s:else>
				                                       是<input type="radio" name="webtype.trMk" value="Y" datatype="*"/>
				           </s:else>
				           <s:if test='webtype.trMk=="N"'>
				                                       否<input type="radio" name="webtype.trMk" value="N" checked/>
				           </s:if>
				           <s:else>
				                                       否<input type="radio" name="webtype.trMk" value="N"/>
				           </s:else>                                                     
				          </td>	 -->				                             				  
			</tr>											
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
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
					//location.href="webtype_findPageBean";
					loadUrl("webtype_findPageBean");
				}else{
					//alert(data.responseText);
					layer.msg("提交失敗!",3,3);
				}				
			}
		});	
	}); 

	 function check(typeno){
       var factno=document.getElementById("dwr_factno").value;           
       if(factno!=""&&typeno.value!=""){
          webtypejs.findById(factno,typeno.value,function(x){
              if(x!=null){
            	  /*if(typeno.value=="TR"){
            		alert("出差類已存在");  
            	  }else{
            		  alert("該類別已存在");
            	  }*/
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
	    });
	    }	    	   
	}
  
  	function checkType(type){
	   if(type.value.charAt(0).toUpperCase()=='C'||type.value.toUpperCase()=='TR'||type.value.toUpperCase()=='PF'){
	      alert("不可以使用C字母开头或TR,PF");
	      type.value='';
	   }
	}
/*禁止空格輸入*/
/*window.onload=function(){            
            var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text"){                
                 inputs[i].onkeyup=function(){
                	 if(this.value.indexOf(" "!=-1)){
                		this.value=this.value.trim();
                	 }
                    //this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 };                
                }  
            }  
        };*/
        
 /*禁止空格輸入*/        
jq(function(){
        	goTrim();
      });
function back(){
	
	loadUrl("webtype_findPageBean3?backIndex=1");
	
}
function checkRadio(){
	var item=jq("input[type='radio']:checked").val();
	if(item=="0"){
		jq("#typeno").removeAttr("readonly");
		jq("#typeno").attr("datatype","s2-2");
		jq("#typeno").css({"border":"2px solid #B6CDDC","background-color":"white"});
		jq("#typeno").focus();
	}else{
		jq("#typeno").val("");
		jq("#typeno").attr("readonly","readonly");
		jq("#typeno").removeAttr("datatype");
		jq("#typeno").css({"border":"2px solid #DDD","background-color":"#f5f5f5"});
	}
}
</script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='dwr/interface/webtypejs.js'></script>
</body>
</html>
