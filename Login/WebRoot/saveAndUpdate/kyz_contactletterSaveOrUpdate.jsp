<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'addBackMat.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
	$(function() {
		var jj = jQuery.noConflict();
		var demo = jj("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			tipSweep : true,
			datatype : {
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my0-8": /^\d{0,8}(\.[0-9]{1,4})?$/
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});
		
	}
	

function makeBillNo() {
        
		var factno = document.getElementById("dwrFactNo").value;
		var timecreat = document.getElementById("ymExpect").value;
		var factcode=document.getElementById("dwrFactArea").value;
		var billnos = document.getElementById("kyzletter_billno");
		var cbox_length=document.getElementsByName("cbox").length;
		if (factno != "" && timecreat != "" && factcode!="" ) {
			kyzcontactletterjs.makeBillNo(factno, timecreat, function(x) {
				dwr.util.setValue("kyzletter_billno", x);								  			  								
			});				 		
		}
		
	}
							
   function getKyType(){
	   kytypejs.findByTypeNo("VV",function(x){
	         dwr.util.addOptions("dwr_kytype",x,"typeName","typeSname");
	   });
	}
	 
  function checkType(type){
     dwrFactNo=document.getElementById("dwrFactNo").value;
     dwremail=document.getElementById("dwr_email").value;
     if(dwrFactNo!=""&&type!=""){
         kyzvisaflowjs.findByType_Dwr(dwrFactNo,type,function(x){
            if(x==0){
               alert("該類型審核流程不存在，請重新選定!");
               document.getElementById("sub").disabled=true;
               document.getElementById("sub").style.color="red";
               document.getElementById("dwr_kytype").style.color="red";
            }else{              
                kyzvisaflowjs.findVisaSort_dwr(dwrFactNo,type,dwremail,function(y){
                  if(y==null){
                     alert("對不起，你不是該類別函文申請人，請重新選定!");
                     document.getElementById("sub").disabled=true;
                     document.getElementById("sub").style.color="red";
                     document.getElementById("dwr_kytype").style.color="red";                    
                  }else{
                     document.getElementById("sub").disabled=false;
                     document.getElementById("sub").style.color="white";
                     document.getElementById("dwr_kytype").style.color="black";
                     document.getElementById("hidden_kytype").value=y;                    
                  }
                  
               }) 
            }
         });
     }
  }
  var i=0;	
  function addFile(){
      i++;
      if(i<5){
      var divfile=document.getElementById("divfile");
      var inputfile=document.createElement("input");
      var aEle=document.createElement("a");
      inputfile.type="file";
      inputfile.name="files";
      inputfile.style.width="150px";
      aEle.innerHTML="刪除";
      aEle.style.color="red";
      aEle.href="javascript:void(0)";
      aEle.onclick=function(){
         var parentnode=aEle.parentNode;
         if(parentnode){
            parentnode.removeChild(aEle);
            parentnode.removeChild(inputfile);
            if(i>4){
               i=4;
            }
            i--;
         }
      };
      divfile.appendChild(inputfile);
      divfile.appendChild(aEle);  
      }else{
         alert("附檔不能超過5個!");
      }               
  }


</script>
<script type='text/javascript' src='/Login/dwr/interface/kyzcontactletterjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<style type="text/css">
table.gridtable {
	 font-family: verdana,arial,sans-serif;
	 font-size:12px;
	 border-collapse: collapse;
     border:1px solid #d5f3f4;  
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {	
	background-color: #ffffff;	
	font-size:12px;
	letter-spacing:2px;	
	color:#678197;
    border:1px solid #d5f3f4;
    padding:.3em 1em;
}

table caption{
   font-size:30px;
   margin:20px;
}
.bluecss{
   font-color:blue;
   border-color:blue;
   background-color:blue;
}
#table1 input[type="text"],select{
  width:100%;
  background:transparent;
  border-width:1px ;
  border-style:solid;
  border-color:#ffffff;
  margin:0px;
}
#table2 input[type="text"],select{
  width:100%;
  background:transparent;
  border-width:1px ;
  border-style:solid;
  border-color:#ffffff;
  margin:0px;
}
textarea{
  badkground:transparent;
  border-width:1px;
  border-style:solid;
  border-color:#ffffff;
  margin:0px;
}
table.gridtable td.tdcolor {
 background:#f7fbff;
}

#mydiv{
    padding:4px;
	top: -8px;
	left:-8px;
	position: absolute;
	/* background-color:rgb(100,200,300);  */
	filter: alpha(opacity=50);
	/* background-color:rgba(100,200,300,0.5); */
	background-color: black;
	opacity: 0.5;
	-moz-opacity:0.5;   
    -khtml-opacity: 0.5; 
	 display: block; 
	 
}


</style>

</head>
<body onload="getKyType()">  
 
　     <s:if test="kyzletter==null">
      <form action="kyzletter_add" method="post" id="form" target="_blank" enctype="multipart/form-data">
    </s:if>
    <s:else>
       <form action="kyzletter_add" method="post" id="form" enctype="multipart/form-data">
    </s:else>
    <div style="overflow-y:auto;height:700px;width:100%">

		<table class="gridtable" id="table1" style="width:900px" >
		    <caption >內部聯絡函申請</caption>		    	
			<tbody id="tb_list_info2">
				    <tr>
				        <td class="tdcolor">標題</td>
				        <td><input type="text" name="kyzletter.title" datatype="*"  value="<s:property value='kyzletter.title'/>" /></td>
				        
				        <td class="tdcolor">受文者</td>
				        <td><input type="text" name="kyzletter.toUser" datatype="*0-50"  value="<s:property value='kyzletter.toUser'/>"/></td>
				        <td class="tdcolor">承辦人</td>
				        <td>
				        <s:if test="kyzletter==null">
						   <input type="text" name="kyzletter.userNm"   value="<s:property value='#session.loginUser.name'/>" style="color:blue" readonly/>
						</s:if>
						<s:else>
						   <input type="text" name="kyzletter.userNm"   value="<s:property value='kyzletter.userNm'/>" style="color:blue" readonly/>
						</s:else>
				        </td>  				        				        				        
				    </tr>
				    <s:if test="kyzletter==null">
				   	<input type="hidden" name="isnull" value="isNull"/><!--判斷變量 -->					
					<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="tdcolor">廠別</td>
						<td ><input type="text" style="color:blue"
							name="kyzletter.id.factNo" value="${factNo}" readonly id="dwrFactNo" />							
						</td>
						
						<td class="tdcolor">廠別狀態</td>
						<td><select name="kyzletter.factCode"
							datatype="*" id="dwrFactArea" onchange="makeBillNo()" style="color:blue">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select></td>
						
						<td class="tdcolor">類別</td>
				        <td >
				         <select  id="dwr_kytype" onchange="checkType(this.value)" datatype="*" style="color:blue">
				            <option value="">請選擇</option>
				         </select>
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>
				         <input type="hidden" name="kyzletter.visaType" id="hidden_kytype"/>	
				        </td>
					</tr>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<tr>
						<td class="tdcolor">廠別</td>
						<td ><select style="color:blue"
							name="kyzletter.id.factNo" datatype="*" id="dwrFactNo"
							onchange="getFactArea(this.value),makeBillNo()">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						
						<td class="tdcolor">廠別狀態</td>
						<td><select name="kyzletter.factCode"
							datatype="*" id="dwrFactArea" onchange="makeBillNo()">
								<option value="">請選擇廠別狀態</option>
						</select></td>
						
						<td class="tdcolor">類別</td>
				        <td >
				         <select  id="dwr_kytype" onchange="checkType(this.value)" datatype="*" style="color:blue">
				            <option value="">請選擇</option>
				         </select>
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>
				         <input type="hidden" name="kyzletter.visaType" id="hidden_kytype"/>	
				        </td>
					</tr>
					</s:if>																							    
				   </s:if>
				   <s:else>
				      <td class="tdcolor">廠別</td>				      
				      <td>
				      <input type="text" name="kyzletter.id.factNo" value="<s:property value='kyzletter.id.factNo'/>" readonly style="color:blue" />
				      <input type="hidden" name="isnull" value="notNull"/><!--判斷變量 -->
				      </td>
				     
				      <td class="tdcolor">廠別狀態</td>
				      <td><input type="text" name="kyzletter.factCode" value="<s:property value='kyzletter.factCode'/>" readonly style="color:blue" /></td>
				     
				      <td class="tdcolor">類別</td>
				      <td><input type="text" value="<s:property value='kyzletter.visaType'/>" name="kyzletter.visaType" style="color:blue"  readonly/></td>				     				     
				   </s:else>				   				    				  			
					<tr>
				        <td class="tdcolor">承辦單位</td>
				        <td><input type="text" name="kyzletter.secNo" datatype="*0-20"  value="<s:property value='kyzletter.secNo'/>" /></td>
				        
				        <td class="tdcolor">承辦主管</td>
				        <td><input type="text" name="kyzletter.chargeNo" datatype="*0-50"  value="<s:property value='kyzletter.chargeNo'/>"/></td>
				        <td class="tdcolor">承辦日期</td>
				        <td>
				        <s:if test="kyzletter==null">
						   <input type="text" name="kyzletter.ymExpect" datatype="*"  value="<%=str_date%>" style="color:blue" id="ymExpect" readonly/>
						</s:if>
						<s:else>
						   <input type="text" name="kyzletter.ymExpect" datatype="*"  value="<s:property value='kyzletter.ymExpect'/>" style="color:blue" readonly/>					   
						</s:else>
				        </td>  				        				        				        
				    </tr>
				    <tr>			    				    
				      <td class="tdcolor">申請單號</td>				        
				      <td>
				        <s:if test="kyzletter==null">	
				          		<input type="text" name="kyzletter.id.billNo" value="自動生成" readonly style="color:blue" id="kyzletter_billno"/>	        
				        </s:if>
				        <s:else>
				               <input type="text" name="kyzletter.id.billNo" value="<s:property value='kyzletter.id.billNo'/>" readonly style="color:blue" />
				        </s:else>				      
				      </td>
				      <td class="tdcolor">附档</td>
				      <td colspan="3">
				      <div style="width:300px" id="divfile">
				      <input type="file" name="files" style="width:150px"/><a href="javascript:addFile()">添加多個</a>
				      </div>
				      </td>				        	        				       																					        
				    </tr>
				    <tr>
					    <td class="tdcolor">CC(呈)</td>	
						<td  colspan="10">
				           <textarea rows="2" cols="115" name="kyzletter.chargeList" autofocus="autofocus" wrap="hard" wrap="physical"  tip="CC(呈)" altercss="gray" class="gray"><s:property value="kyzletter.chargeList"/></textarea>				                                           				         			          
				        </td>
						
					</tr>
					<tr>
					    <td class="tdcolor">申請內容</td>	
						<td  colspan="10">
				           <textarea rows="15" cols="115" name="kyzletter.memoMk" autofocus="autofocus" wrap="hard" wrap="physical"  tip="申請內容" altercss="gray" class="gray"><s:property value="kyzletter.memoMk"/></textarea>				                                           				         				           				           
				           <input type="hidden" value="<s:property value='kyzletter.filesYn'/>" name="kyzletter.filesYn"/>
				           <input type="hidden" value="<s:property value='kyzletter.firstPage'/>" name="kyzletter.firstPage"/>
				        </td>
						
					</tr>												
			</tbody>			
			</table>
			<s:if test='kyzletter.filesYn=="1"'>
	           <hr/>
	          <span style="color:blue;">附檔:</span>
	           <s:iterator value="#session.list_filesexp">
	             <a href="/upload_letter/<s:property value='billno'/>/<s:property value='filename'/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
	         </s:iterator>	  
	         </s:if> 						 
			  <center style="width:850px;margin-left:50px">			    
				<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
					type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
										
			</center>
				
	</div>		
	</form>
	
</body>
</html>
