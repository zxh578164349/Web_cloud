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
	
	function deleteHtml(id) {
		id.parentNode.removeChild(id);
	}

	function getva1(){
	   alert(document.getElementById("testfile").value);
	}
var j=0;	
function makeBillNo() {
        
		var factno = document.getElementById("dwrFactNo").value;
		var timecreat = document.getElementById("kyz_timecreate").value;
		var factcode=document.getElementById("dwrFactArea").value;
		var billnos = document.getElementById("kyz_billno");
		var cbox_length=document.getElementsByName("cbox").length;
		if (factno != "" && timecreat != "" && factcode!="" ) {
			kyzjs.makeBillNo(factno, timecreat, function(x) {
				dwr.util.setValue("kyz_billno", x);				
				  dwr.util.setValue("kyzs_billno", x);				  								
			});
			document.getElementById("addbtn").disabled="";
			document.getElementById("addbtn").style.color="white";
			document.getElementById("kyzs_factno").value=factno;
			 document.getElementById("kyzs_factcode").value=factcode;			
			 if(cbox_length>1){
			 j=0;
			   for(var k=cbox_length+1;k>2;k--){
			       kyzs_body.deleteRow(k-1);
			   }
			 }	 		
		}
		
	}
	
	
	function addRow(){
	     j=j+1;
	    //添加一行
        var newTr = kyzs_body.insertRow();
        //添加列
        var newTd00=newTr.insertCell();
        var newTd0 = newTr.insertCell();
        var newTd1 = newTr.insertCell();
        var newTd2=newTr.insertCell();
        var newTd3=newTr.insertCell();
        var newTd4=newTr.insertCell();
        var newTd5=newTr.insertCell();
        var newTd6=newTr.insertCell();
        var newTd7=newTr.insertCell();
        var newTd8=newTr.insertCell();
        var factno=document.getElementById("kyzs_factno").value;
        var billno=document.getElementById("kyzs_billno").value;
        var qtyPair=document.getElementById("qtyPair").value;
        var moneyType=document.getElementById("moneyType").value;
        var factcode=document.getElementById("dwrFactArea").value;
        //设置列内容和属性
        var cboxlist=document.getElementsByName("cbox");
        if(cboxlist.length>9){
           alert("對不起,不能超過10條記錄!");
        }else{
        newTd00.innerHTML='<input type="checkbox" name="cbox"/>';
        newTd0.innerHTML = '<input type="text" name="kyz.kyzExpectmatses['+j+'].itemNm" value="" size="15"/>'; 
        if((j+1)<10){
          newTd1.innerHTML= '<input type="text" name="kyz.kyzExpectmatses['+j+'].id.itemNo" value="00'+(j+1)+'" datatype="*" style="color:blue" size="15" readonly/>';
        }else{
          newTd1.innerHTML= '<input type="text" name="kyz.kyzExpectmatses['+j+'].id.itemNo" value="0'+(j+1)+'"  datatype="*" style="color:blue" size="15" readonly/>';
        }
        
        newTd2.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].matNo" value="" size="15"/>';
        newTd3.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].qtyExpect" value="" size="15"/>';
        newTd4.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].qtyOk" value="" size="15"/>';
        newTd5.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].personNo" value="" size="15"/>';
        newTd6.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].qtyPair" value="'+qtyPair+'" size="15"/>';    
        newTd7.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].moneyType" value="'+moneyType+'" size="15"/>';  
        newTd8.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].memoMk" value="" size="15"/>'+
        '<input type="hidden" name="kyz.kyzExpectmatses['+j+'].id.kyzExpectmatm.id.factNo" value="'+factno+'"'+'id="kyzs_factno"/>'+     
        '<input type="hidden" name="kyz.kyzExpectmatses['+j+'].id.kyzExpectmatm.id.billNo" value="'+billno+'"'+'id="kyzs_billno"/>'+
        '<input type="hidden" name="kyz.kyzExpectmatses['+j+'].factCode" value="'+factcode+'"'+'id="kyzs_billno"/>';       
        }
        
	}
	
	var jj=0;
	function addRow_update(){	  	  	     	    
	     var maxNum=parseInt(document.getElementById("maxNum").value);
	    //添加一行
        var newTr = kyzs_body.insertRow();
        //添加列
        var newTd00=newTr.insertCell();
        var newTd0 = newTr.insertCell();
        var newTd1 = newTr.insertCell();
        var newTd2=newTr.insertCell();
        var newTd3=newTr.insertCell();
        var newTd4=newTr.insertCell();
        var newTd5=newTr.insertCell();
        var newTd6=newTr.insertCell();
        var newTd7=newTr.insertCell();
        var newTd8=newTr.insertCell();
        var factno=document.getElementById("kyzs_factno").value;
        var billno=document.getElementById("kyzs_billno").value;
        var qtyPair=document.getElementById("qtyPair").value;
        var moneyType=document.getElementById("moneyType").value;
        var factcode=document.getElementById("kyzs_factcode").value;
        //设置列内容和属性
        var cboxlist=document.getElementsByName("cbox");
        if(cboxlist.length>9){
           alert("對不起,不能超過10條記錄!");
        }else{
        newTd00.innerHTML='<input type="checkbox" name="cbox" disabled/><input type="image" src="images/del.gif" onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode)"/>';
        newTd0.innerHTML = '<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].itemNm" value="" size="15"/>'; 
        if((jj+maxNum+1)<10){
          newTd1.innerHTML= '<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].id.itemNo" value="00'+(jj+maxNum+1)+'" datatype="*" style="color:blue"  readonly size="15"/>';
        }else{
          newTd1.innerHTML= '<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].id.itemNo" value="0'+(jj+maxNum+1)+'"  datatype="*" style="color:blue" readonly size="15"/>';
        }
        
        newTd2.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].matNo" value="" size="15"/>';
        newTd3.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].qtyExpect" value="" size="15"/>';
        newTd4.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].qtyOk" value="" size="15"/>';
        newTd5.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].personNo" value="" size="15"/>';
        newTd6.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].qtyPair" value="'+qtyPair+'" size="15"/>';
        newTd7.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].moneyType" value="'+moneyType+'" size="15"/>';    
        newTd8.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].memoMk" value="" size="15"/>'+
        '<input type="hidden" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].id.kyzExpectmatm.id.factNo" value="'+factno+'"'+'id="kyzs_factno"/>'+    
        '<input type="hidden" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].id.kyzExpectmatm.id.billNo" value="'+billno+'"'+'id="kyzs_billno"/>'+
        '<input type="hidden" name="kyz.kyzExpectmatses['+(jj+maxNum)+'].factCode" value="'+factcode+'"'+'id="kyzs_factcode"/>';       
        }
        jj=jj+1;
	}
				
	function delRow(){
	   var cboxlist=document.getElementsByName("cbox");
	   for(var k=0;k<cboxlist.length;k++){
	     if(cboxlist[k].checked==true&&k>0){
	        kyzs_body.deleteRow(k+1);
	        k=k-1;
	     }
	   }
	   
	}
	function checkAll(){
	  var allbox=document.getElementById("cboxall");
	  var cboxlist=document.getElementsByName("cbox");
	  if(allbox.checked==true){
	       for(var kk=0;kk<cboxlist.length;kk++){
	           cboxlist[kk].checked=true;
	       }
	  }else{
	         for(var kk=0;kk<cboxlist.length;kk++){
	           cboxlist[kk].checked=false;
	       }
	  }
	}
	function getFactCode(){
	    document.getElementById("dwrFactArea").value=document.getElementById("kyzs_factcode").value;
	}
   function getKyType(){
	   kytypejs.findByTypeNo("VV",function(x){
	         dwr.util.addOptions("dwr_kytype",x,"typeName","typeSname");
	   });
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
  function getVisaSort(type){
     dwrfactno=document.getElementById("dwrFactNo").value;
     dwremail=document.getElementById("email").value;
     if(dwrfactno!=""){
        
     }
  }


</script>
<script type='text/javascript' src='/Login/dwr/interface/kyzjs.js'></script>
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
<%@ include file="../saveAndUpdate/publicHead2.jsp"%>
<body onload="getKyType()">  
 
　     <s:if test="kyz==null">
      <form action="kyz_add" method="post" id="form" target="_blank" enctype="multipart/form-data">
    </s:if>
    <s:else>
       <form action="kyz_add" method="post" id="form" enctype="multipart/form-data">
    </s:else>
    <div style="overflow-y:auto;height:700px;width:100%">

		<table class="gridtable" id="table1" style="width:850px" >
		    <caption >函文申請</caption>		    	
			<tbody id="tb_list_info2">
				    <tr>
				        <td class="tdcolor">標題</td>
				        <td><input type="text" name="kyz.memoSmk" datatype="*"  value="<s:property value='kyz.memoSmk'/>" /></td>
				        
				        <td class="tdcolor">電話</td>
				        <td><input type="text" name="kyz.telNo" datatype="n0-11"  value="<s:property value='kyz.telNo'/>"/></td> 				        
				        <td class="tdcolor">申請單位</td>
				        <td><input type="text" name="kyz.secNo"  value="<s:property value='kyz.secNo'/>"/></td>
				        
				    </tr>
				    <s:if test="kyz==null">
				   	<input type="hidden" name="isnull" value="isNull"/><!--判斷變量 -->					
					<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="tdcolor">廠別</td>
						<td ><input type="text" style="color:blue"
							name="kyz.id.factNo" value="${factNo}" readonly id="dwrFactNo" />							
						</td>
						
						<td class="tdcolor">廠別狀態</td>
						<td><select name="kyz.factCode"
							datatype="*" id="dwrFactArea" onchange="makeBillNo()" style="color:blue">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select></td>
						
						<td class="tdcolor">建立日期</td>
				        <td ><input type="text" name="yymmdd" id="kyz_timecreate"  readonly  value="<%=str_date%>" style="color:blue">
					</tr>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<tr>
						<td class="tdcolor">廠別</td>
						<td ><select style="color:blue"
							name="kyz.id.factNo" datatype="*" id="dwrFactNo"
							onchange="getFactArea(this.value),makeBillNo()">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						
						<td class="tdcolor">廠別狀態</td>
						<td><select name="kyz.factCode"
							datatype="*" id="dwrFactArea" onchange="makeBillNo()">
								<option value="">請選擇廠別狀態</option>
						</select></td>
						
						<td class="tdcolor">建立日期</td>
				        <td ><input type="text" name="yymmdd" id="kyz_timecreate"  readonly  value="<%=str_date%>" style="color:blue">
					</tr>
					</s:if>																							    
				   </s:if>
				   <s:else>
				      <td class="tdcolor">廠別</td>				      
				      <td>
				      <input type="text" name="kyz.id.factNo" value="<s:property value='kyz.id.factNo'/>" readonly style="color:blue" />
				      <input type="hidden" name="isnull" value="notNull"/><!--判斷變量 -->
				      </td>
				     
				      <td class="tdcolor">廠別狀態</td>
				      <td><input type="text" name="kyz.factCode" value="<s:property value='kyz.factCode'/>" readonly style="color:blue" /></td>
				     
				      <td class="tdcolor">修改日期</td>
				      <td><input type="text" name="yymmdd" value="<%=str_date%>" readonly style="color:blue" /></td>				     				     
				   </s:else>
				   
				    <tr>			    				    
				      <td class="tdcolor">申請單號</td>				        
				      <td>
				        <s:if test="kyz==null">	
				          		<input type="text" name="kyz.id.billNo" value="自動生成" readonly style="color:blue" id="kyz_billno"/>	        
				        </s:if>
				        <s:else>
				               <input type="text" name="kyz.id.billNo" value="<s:property value='kyz.id.billNo'/>" readonly style="color:blue" />
				        </s:else>				      
				      </td>
				        	        
				       <td class="tdcolor">申請者</td>
						<td >
						<s:if test="kyz==null">
						   <input type="text" name="kyz.userNm" datatype="*"  value="<s:property value='#session.loginUser.name'/>" style="color:blue" readonly/>
						</s:if>
						<s:else>
						   <input type="text" name="kyz.userNm" datatype="*"  value="<s:property value='kyz.userNm'/>" style="color:blue" readonly/>
						</s:else>
						</td>
						
						<td class="tdcolor">是否緊急</td>
						<s:if test="kyz==null">
						   <td >是<input type="radio" name="kyz.emerWhether" value="0" checked/>&nbsp;&nbsp;否<input type="radio" name="kyz.emerWhether" value="1"/></td>
						</s:if>
						<s:else>
						   <s:if test="kyz.emerWhether==0">
						       <td >是<input type="radio" name="kyz.emerWhether" value="<s:property value='kyz.emerWhether'/>" checked/>&nbsp;&nbsp;否<input type="radio" name="kyz.emerWhether" value="1"/></td>
						   </s:if>
						   <s:if test="kyz.emerWhether==1">
						       <td >是<input type="radio" name="kyz.emerWhether" value="0" />&nbsp;&nbsp;否<input type="radio" name="kyz.emerWhether" value="<s:property value='kyz.emerWhether'/>" checked/></td>
						   </s:if>
						    <s:if test="kyz.emerWhether==null">
						       <td >是<input type="radio" name="kyz.emerWhether" value="0" checked/>&nbsp;&nbsp;否<input type="radio" name="kyz.emerWhether" value="1" /></td>
						   </s:if>
						</s:else>					
										        
				    </tr>
				    <tr>
				      <td class="tdcolor">類別</td>
				      <td >
				         <s:if test="kyz==null">
				            <select  id="dwr_kytype" onchange="checkType(this.value)" datatype="*" style="color:blue">
				            <option value="">請選擇</option>
				         </select>
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>
				         <input type="hidden" name="kyz.visaType" id="hidden_kytype"/>				         
				         </s:if>
				         <s:else>
				            <input type="text" value="<s:property value='kyz.visaType'/>" name="kyz.visaType" style="color:blue"  readonly/>
				         </s:else>				         				        				        
				      </td>					     
				      <td class="tdcolor">附檔</td>
				      <td >
				      <div style="width:300px" id="divfile">
				      <input type="file" name="files" style="width:150px"/><a href="javascript:addFile()">添加多個</a>
				      </div>
				      </td>	
				      <td class="tdcolor">是否顯示第一頁</td>		       
				       <s:if test="kyz==null">
						   <td >是<input type="radio" name="kyz.firstPage" value="0" checked/>&nbsp;&nbsp;否<input type="radio" name="kyz.firstPage" value="1"/></td>
						</s:if>
						<s:else>
						   <s:if test="kyz.firstPage==0">
						       <td >是<input type="radio" name="kyz.firstPage" value="<s:property value='kyz.firstPage'/>" checked/>&nbsp;&nbsp;否<input type="radio" name="kyz.firstPage" value="1"/></td>
						   </s:if>
						   <s:if test="kyz.firstPage==1">
						       <td >是<input type="radio" name="kyz.firstPage" value="0" />&nbsp;&nbsp;否<input type="radio" name="kyz.firstPage" value="<s:property value='kyz.firstPage'/>" checked/></td>
						   </s:if>
						    <s:if test="kyz.firstPage==null">
						       <td >是<input type="radio" name="kyz.firstPage" value="0" checked/>&nbsp;&nbsp;否<input type="radio" name="kyz.firstPage" value="1" /></td>
						   </s:if>
						</s:else>		      
				    </tr>					
					<tr>
					    <td class="tdcolor">申請內容</td>	
						<td  colspan="10">
				           <textarea rows="8" cols="105" name="kyz.memoMk" autofocus="autofocus" wrap="hard" wrap="physical"  tip="申請內容" altercss="gray" class="gray"><s:property value="kyz.memoMk"/></textarea>				                                           				         
				           <input type="hidden" name="kyz.username" value="<s:property value='#session.loginUser.username'/>"/>
				           
				           <input type="hidden" value="<s:property value='kyz.filesYn'/>" name="kyz.filesYn"/>
				        </td>
						
					</tr>													
			</tbody>
			</table>	
			<table class="gridtable" id="table2" style="width:850px">								 			
			<tbody id="kyzs_body" >
			  <tr>
			     <td class="tdcolor"><input type="checkbox" id="cboxall" onclick="checkAll()"/></td>
			     <td class="tdcolor">名稱</td>
			     <td class="tdcolor">項次</td>
			     <td class="tdcolor">規格</td>
			     <td class="tdcolor">單價</td>
			     <td class="tdcolor">數量</td>
			     <td class="tdcolor">使用人數</td>
			     <td class="tdcolor">單位</td>
			     <td class="tdcolor">幣種</td>		     
			     <td class="tdcolor">備註</td>
			 </tr>	
			 <s:if test="kyz==null">
			    <tr>
			     <td><input type="checkbox" name="cbox" disabled/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[0].itemNm" value=""  size="15"/></td> 
			     <td><input type="text" name="kyz.kyzExpectmatses[0].id.itemNo" value="001" readonly style="color:blue" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[0].matNo" value="" size="15"/></td>			     
			     <td ><input type="text" name="kyz.kyzExpectmatses[0].qtyExpect" value="" datatype="my0-8" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[0].qtyOk" value="" datatype="my0-8" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[0].personNo" value="" datatype="my0-8" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[0].qtyPair" value="" id="qtyPair" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[0].moneyType" value="" id="moneyType" size="15"/></td>
			      <td >
			      <input type="text" name="kyz.kyzExpectmatses[0].memoMk" value="" size="15"/>
			      <input type="hidden" name="kyz.kyzExpectmatses[0].id.kyzExpectmatm.id.factNo" value="" id="kyzs_factno"/>
			      <input type="hidden" name="kyz.kyzExpectmatses[0].id.kyzExpectmatm.id.billNo" value="" id="kyzs_billno"/>
			      <input type="hidden" name="kyz.kyzExpectmatses[0].factCode" value="" id="kyzs_factcode"/>
			      </td>			      		      
			  </tr>		
			 </s:if>
			 <s:else>
			  
			    <s:iterator value="kyz.kyzExpectmatses" status="x" id="temp">
			       <tr class="bluecss">
			         <td>
			          <s:if test="#attr.x.index==0">
			            <input type="checkbox" name="cbox" value="<s:property value='id.itemNo'/>" disabled/>
			          </s:if>
			          <s:else>
			            <input type="checkbox" name="cbox" value="<s:property value='id.itemNo'/>"/>
			          </s:else>			          			          			            			          
			         </td>			     
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].itemNm" value="<s:property value='itemNm'/>" size="15"/></td>			    
			     <td><input type="text" name="kyz.kyzExpectmatses[${x.index}].id.itemNo" value="<s:property value='id.itemNo'/>" readonly style="color:blue" size="15"/></td>			    			     			     
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].matNo" value="<s:property value='matNo'/>" size="15"/></td>			     
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].qtyExpect" value="<s:property value='qtyExpect'/>" datatype="my0-8" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].qtyOk" value="<s:property value='%{formatDouble(qtyOk)}'/>" datatype="my0-8" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].personNo" value="<s:property value='%{formatDouble(personNo)}'/>" datatype="my0-8" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].qtyPair" value="<s:property value='qtyPair'/>"  id="qtyPair" size="15"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].moneyType" value="<s:property value='moneyType'/>" id="moneyType" size="15"/></td>
			      <td >
			      <input type="text" name="kyz.kyzExpectmatses[${x.index}].memoMk" value="<s:property value='memoMk'/>" size="15"/>			      
			      <input type="hidden" name="kyz.kyzExpectmatses[${x.index}].id.kyzExpectmatm.id.factNo" value="<s:property value='id.kyzExpectmatm.id.factNo'/>" id="kyzs_factno"/>
			      <input type="hidden" name="kyz.kyzExpectmatses[${x.index}].id.kyzExpectmatm.id.billNo" value="<s:property value='id.kyzExpectmatm.id.billNo'/>" id="kyzs_billno"/>
			      <input type="hidden" name="kyz.kyzExpectmatses[${x.index}].factCode" value="<s:property value='factCode'/>" id="kyzs_factcode"/>
			      </td>			      		      
			  </tr>
			    </s:iterator>
			    <input type="hidden" value="<s:property value='kyz.id.factNo'/>" name="factNo"/>
			    <input type="hidden" value="<s:property value='kyz.id.billNo'/>" name="billNo"/>
			    
			 </s:else>		         			  
			 	  			
			</tbody>
			<tfoot><tr>
			<s:if test="kyz==null">
			<td colspan="10">			     			  
			     <input type="button" value="添加行" onclick="addRow()" disabled="disabled" id="addbtn" style="color:grey"/>
			     <input type="button" value="刪除行" onclick="delRow()"  id="delbtn"/>
			 </td>    
			  </s:if>					    			    		   		
			</tr>
			</tfoot>					    
		</table >
		  <s:if test="kyz!=null">			    			  　　
		     	   <input type="hidden" value="<s:property value='maxNum'/>" id="maxNum"/> <!-- 用於添加行的maxNum變量 -->		     	 
		     	   <s:submit value="刪除數據" onclick="javascript:alert('注意!若有選中將從數據庫中刪除所選數據')" action="kyz_deleteMore" align="left" cellspacing="0"  id="delbtn"
		     	    style="width:60px;margin:0px">
		     	   　<input type="button" value="添加行" onclick="addRow_update()" id="addbtn"/>
		     	   </s:submit>
		     	   		     	   			      
		</s:if>
		<s:if test='kyz.filesYn=="1"'>
	       <hr/>
	       <span style="color:blue;">附檔:</span>
	      <s:iterator value="#session.list_filesexp">
	        <a href="/upload/<s:property value='billno'/>/<s:property value='filename'/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
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
