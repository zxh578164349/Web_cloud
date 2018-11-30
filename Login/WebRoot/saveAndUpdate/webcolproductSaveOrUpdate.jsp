<!DOCTYPE HTML>

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
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>
<body >  
    <form action="kyz_add"  method="post" id="form"  enctype="multipart/form-data" target="frameFile">
		<table class="table table-condensed" >		    	
			<tbody id="tb_list_info2">				   
				    <s:if test="kyz==null">
				   	<input type="hidden" name="isnull" value="isNull"/><!--判斷變量 -->										
					<tr>
						<td class="tdcolor">廠別</td>
						<s:if test="#session.factNo!='tw'">
						<td ><input type="text" style="color:blue"
							name="obj.id.factNo" value="${factNo}" readonly id="dwrFactNo" />							
						</td>
						</s:if>
						<s:if test="#session.factNo=='tw'">
						<td ><select style="color:blue"
							name="obj.id.factNo" datatype="*" id="dwrFactNo"
							onchange="getFactArea(this.value),makeBillNo(),getKyType2(this.value)">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						</s:if>		
						<td class="tdcolor">類別</td>
						<td></td>						
						<td class="tdcolor">單號</td>
				        <td >
				        <input type="text" name="obj.id.billNo" value="自動生成" readonly style="color:blue" id="kyz_billno" datatype="*"/>	
				        </td>
				        <td class="tdcolor">日期</td>
						<td>
						<input type="text" name="yymmdd" id="kyz_timecreate"  readonly  value="<%=str_date%>" style="color:blue">
						</td>
					</tr>																																							    
				   </s:if>
				   <s:else>
				      <td class="tdcolor">廠別</td>				      
				      <td>
				      <input type="text" name="obj.id.factNo" value="<s:property value='obj.id.factNo'/>" readonly style="color:blue" id="dwrFactNo"/>
				      <input type="hidden" name="isnull" value="notNull"/><!--判斷變量 -->
				      </td>
				     
				      <td class="tdcolor">類別</td>
				      <td><input type="text" name="obj.factCode" value="<s:property value='obj.factCode'/>" readonly style="color:blue" id="dwrFactArea"/></td>
				     
				      <td class="tdcolor">單號</td>
				      <td>
				      <input type="text" name="obj.id.billNo" value="<s:property value='obj.id.billNo'/>" id="kyz_billno" readonly style="color:blue" />
				      </td>
				      <td class="tdcolor">日期</td>
					  <td><input type="text" name="yymmdd" value="<%=str_date%>" readonly style="color:blue" /></td>
					  
					  <input type="hidden" name="obj.username" value="<s:property value='#session.loginUser.username'/>"/>
					  <input type="hidden" name="obj.userId" value="<s:property value='#session.loginUser.id'/>"/>
					  <input type="hidden" name="obj.useremail" value="<s:property value='#session.loginUser.email'/>"/>			     				     
				   </s:else>
				   				    				    										
					<s:if test="kyz==null">
					   <tr>
					    <td class="tdcolor">是否分部門</td>
					    <td colspan="10">
					                   是<input type="radio" name="trMk" value="Y" checked datatype="*" onclick="checkType()"/>&nbsp;&nbsp;
			                                           否<input type="radio" name="trMk" value="N" onclick="checkType()"/> 
					    </td>
					   </tr>
					</s:if>																		
			</tbody>
			</table>	
			<table class="table table-condensed">								 			
			<tbody id="kyzs_body" >
			  <tr>
			     <td class="tdcolor"></td>
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
			  
			    <s:iterator value="obj.kyzExpectmatses" status="x" id="temp">
			    <tr class="bluecss">
			     <td><input type="hidden" name="cbox"/></td>			           			          			          			            			          	     
			     <td ><input type="text" name="obj.kyzExpectmatses[${x.index}].itemNm" value="<s:property value='itemNm'/>" /></td>			    
			     <td><input type="text" name="obj.kyzExpectmatses[${x.index}].id.itemNo" value="<s:property value='id.itemNo'/>" readonly style="color:blue" /></td>			    			     			     
			     <td ><input type="text" name="obj.kyzExpectmatses[${x.index}].matNo" value="<s:property value='matNo'/>" /></td>			     
			     <td ><input type="text" name="obj.kyzExpectmatses[${x.index}].qtyExpect" value="<s:property value='qtyExpect'/>" datatype="my0-8"  id="qtyExpect_${x.index}"/></td>
			     <td ><input type="text" name="obj.kyzExpectmatses[${x.index}].qtyOk" value="<s:property value='%{formatDouble(qtyOk)}'/>" datatype="my0-8"  id="qtyOk_${x.index}"/></td>
			     <td ><input type="text" name="obj.kyzExpectmatses[${x.index}].personNo" value="<s:property value='%{formatDouble(personNo)}'/>" datatype="n0-8"  id="personNo_${x.index}"/></td>
			     <td ><input type="text" name="obj.kyzExpectmatses[${x.index}].qtyPair" value="<s:property value='qtyPair'/>"   /></td>
			     <td ><input type="text" name="obj.kyzExpectmatses[${x.index}].moneyType" value="<s:property value='moneyType'/>"  /></td>
			      <td >
			      <input type="text" name="obj.kyzExpectmatses[${x.index}].memoMk" value="<s:property value='memoMk'/>" />			      
			      <input type="hidden" name="obj.kyzExpectmatses[${x.index}].id.kyzExpectmatm.id.factNo" value="<s:property value='id.kyzExpectmatm.id.factNo'/>" />
			      <input type="hidden" name="obj.kyzExpectmatses[${x.index}].id.kyzExpectmatm.id.billNo" value="<s:property value='id.kyzExpectmatm.id.billNo'/>" />
			      <input type="hidden" name="obj.kyzExpectmatses[${x.index}].factCode" value="<s:property value='factCode'/>" />
			      </td>			      		      
			  </tr>
			    </s:iterator>
			    <input type="hidden" value="<s:property value='obj.id.factNo'/>" name="factNo"/>
			    <input type="hidden" value="<s:property value='obj.id.billNo'/>" name="billNo"/>
			    	         			  			 	  			
			</tbody>
			<tfoot><tr>			
			<td colspan="10">			     			  
			     <input type="button" value="添加行" onclick="addRow()" disabled="disabled" id="addbtn" style="color:grey"/>			     
			 </td>    			 					    			    		   		
			</tr>
			</tfoot>					    
		</table >
	
		<s:if test='obj.filesYn=="1"'>	      
	     <jsp:include page="publicKyzFiles.jsp" flush="true" />	     	     	        	       
	   </s:if>
	   <hr/>	  
			  <center style="width:850px;margin-left:50px">			    
				<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
					type="reset" id="reset" value="重置" class="btn btn-primary"/>
				<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>						
			</center>
		<input type="hidden" name="addorupdate" value="<s:property value='addorupdate'/>" id="addorupdate"/>	<!-- 添加或更新標識     -->				
	</form>
	<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>
	
<script type="text/javascript">

jq(function() {		
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			tipSweep : true,
			datatype : {
				"my0-8": /^\d{0,8}(\.[0-9]{1,4})?$/,
				"my0-12": /^\d{0,12}(\.[0-9]{1,4})?$/
			},
			beforeSubmit:function(curform){
				loadi=layer.load("正在處理,請稍等...(系統爲了節省開銷,已取消自動下載函文!)");
			}									
		});
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";
		demo.tipmsg.w["my0-12"]="只能數字且不超過12位數,可保留四位以內小數";
		
		
		if(jq("#dwrFactNo").val()=="YMUS"){
			jq("#memoMk").attr("datatype","*40-2000");
		}else{
			jq("#memoMk").attr("datatype","*");
		}
		jq(":radio").click(function(){			
			if(jq(this).val()=="0"){
				if(jq("#dwrFactNo").val()=="YMUS"){
					jq("#memoMk").attr("datatype","*40-2000");
				}else{
					jq("#memoMk").attr("datatype","*");
				}
			}else{
				jq("#memoMk").removeAttr("datatype");
			}
		});				
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
	
function makeBillNo() {        
		var factno = document.getElementById("dwrFactNo").value;
		var timecreat = document.getElementById("kyz_timecreate").value;		
		var cbox_length=document.getElementsByName("cbox").length;
		if (factno != "" && timecreat != "") {
			kyzjs.makeBillNo(factno, timecreat, function(x) {
				dwr.util.setValue("kyz_billno", x);								 			  								
			});
			document.getElementById("addbtn").disabled="";
			document.getElementById("addbtn").style.color="black";					 	 		
		}
		
	}
	
var j=0;
	function addRow(){	    
        var factno=document.getElementById("dwrFactNo").value;
        var billno=document.getElementById("kyz_billno").value;
        //var factcode=document.getElementById("dwrFactArea").value;
        var qtyPair=document.getElementById("qtyPair");
        var moneyType=document.getElementById("moneyType");
        
        //设置列内容和属性
        var cboxlist=document.getElementsByName("cbox");
        if(cboxlist.length>29){
           layer.alert("對不起,不能超過30條記錄!");
        }else{
        	 j++;
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
        	
        newTd00.innerHTML='<input type="hidden" name="cbox"/><input type="image" src="images/del.gif" onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode)"/>'; 
        newTd0.innerHTML = '<input type="text" name="obj.kyzExpectmatses['+j+'].itemNm" value="" />'; 
        if(j<10){
          newTd1.innerHTML= '<input type="text" name="obj.kyzExpectmatses['+j+'].id.itemNo" value="00'+j+'" datatype="*" style="color:blue"  readonly/>';
        }else{
          newTd1.innerHTML= '<input type="text" name="obj.kyzExpectmatses['+j+'].id.itemNo" value="0'+j+'"  datatype="*" style="color:blue"  readonly/>';
        }
        
        newTd2.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].matNo" value="" />';
        newTd3.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].qtyExpect" value="" datatype="my0-8"/><span class="Validform_checktip"></span>';
        newTd4.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].qtyOk" value="" datatype="my0-8"/><span class="Validform_checktip"></span>';
        newTd5.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].personNo"  datatype="n0-8"/><span class="Validform_checktip"></span>';
        if(qtyPair==null&&moneyType==null){
        	newTd6.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].qtyPair"  id="qtyPair"/>';    
            newTd7.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].moneyType"  id="moneyType"/>';
        	
        }else{
        	newTd6.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].qtyPair" value="'+qtyPair.value+'" />';    
            newTd7.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].moneyType" value="'+moneyType.value+'" />';
        }
        newTd8.innerHTML='<input type="text" name="obj.kyzExpectmatses['+j+'].memoMk" value="" />'+
        '<input type="hidden" name="obj.kyzExpectmatses['+j+'].id.kyzExpectmatm.id.factNo" value="'+factno+'"'+'/>'+     
        '<input type="hidden" name="obj.kyzExpectmatses['+j+'].id.kyzExpectmatm.id.billNo" value="'+billno+'"'+'/>';       
        }
        
	}
	
		
	function getFactCode(){
	    document.getElementById("dwrFactArea").value=document.getElementById("kyzs_factcode").value;
	}
   function getKyType(){	 	 
	 var factno=document.getElementById("dwrFactNo").value;
	 if(factno!=null&&factno!=""){
	     webtypejs.findByFactNo3(factno,function(x){//過濾出差類"TR"20160203
       if(x.length>0){
          dwr.util.addOptions("dwr_kytype",x,"webtypeMk","typeName");
       }
         
     });
	 }
    
	}
	
function getKyType2(factno){
	 document.getElementById("dwr_kytype").length=1;	 
	 if(factno!=null&&factno!=""){
	     webtypejs.findByFactNo3(factno,function(x){//過濾出差類"TR"20160203
       if(x.length>0){
          dwr.util.addOptions("dwr_kytype",x,"webtypeMk","typeName");
       }
         
     });
	 }    
	}
	
	
	
  var i=0;	
  function addFile(){
      i++;
      if(i<15){
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
            if(i>14){
               i=14;
            }
            i--;
         }
      };
      divfile.appendChild(inputfile);
      divfile.appendChild(aEle);  
      }else{
         alert("附檔不能超過15個!");
      }               
  }
  
  
 
function back(){	
	loadUrl("kyz_findPageBean3?backIndex=1");
}
function gook(){
	  layer.msg("操作成功",3,1);
	  loadUrl("kyz_findPageBean");
}

function lookPic(url){
	window.location.href=url;
}



</script>
<script type='text/javascript' src='dwr/interface/kyzjs.js'></script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='dwr/interface/webtypejs.js'></script>
<script type='text/javascript' src='jquery/publicJS.js'></script>
<script type="text/javascript">
jq(function(){
	if(jq("#addorupdate").val()!="update"){
		getKyType();makeBillNo();
	}else{
		jq("#addbtn").removeAttr("style").removeAttr("disabled");
	}
	j=jq("#maxNum").val()
	if(isNaN(j)){
		j=0;
	}
});
</script>	
</body>
</html>
