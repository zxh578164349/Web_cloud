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
				    <s:if test="obj==null">
				   	<input type="hidden" name="isnull" value="isNull"/><!--判斷變量 -->										
					<tr>					
						<td class="tdcolor">廠別</td>
						<s:if test="#session.factNo!='tw'">
						<td ><input type="text" style="color:blue"
							name="obj.factNo" value="${factNo}" readonly id="dwrFactNo" />							
						</td>
						</s:if>
						<s:if test="#session.factNo=='tw'">
						<td ><select style="color:blue"
							name="obj.factNo" datatype="*" id="dwrFactNo"
							onchange="makeBillNo(),getKyType2(this.value)">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						</s:if>		
						<td class="tdcolor">類別</td>
						<td>
						  <select  id="dwr_kytype" onchange="checkType()" datatype="*" style="color:blue">
				            <option value="">請選擇</option>
				         </select>
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>
				         <input type="hidden" name="obj.visaType" id="hidden_kytype" datatype="*"/>					         
				         <div id="div_depar" style="display:none"><select id="sel_depar" onchange="checkType2()"></select></div>
						</td>						
						<td class="tdcolor">單號</td>
				        <td >
				        <input type="text" name="obj.billNo" value="自動生成" readonly style="color:blue" id="obj_billno" datatype="*"/>	
				        </td>				        
						</tr>	
						</s:if>
						<s:else>
						<tr>
				      <td class="tdcolor">廠別</td>				      
				      <td>
				      <input type="text" name="obj.factNo" value="<s:property value='obj.factNo'/>" readonly style="color:blue" id="dwrFactNo"/>
				      <input type="hidden" name="isnull" value="notNull"/><!--判斷變量 -->
				      </td>
				     
				      <td class="tdcolor">類別</td>
				      <td>
				      <input type="text" value="<s:property value='obj.visaType'/>" name="obj.visaType" style="color:blue"  readonly/>
				      </td>
				     
				      <td class="tdcolor">單號</td>
				      <td>
				      <input type="text" name="obj.billNo" value="<s:property value='obj.billNo'/>" id="obj_billno" readonly style="color:blue" />
				      </td>				     					  					  					  
					  <input type="text" name="obj.webUserByCreateUserFid.id" value="<s:property value='obj.webUserByCreateUserFid.id'/>"/>
				      <input type="text" name="obj.webUserByUpdateUserFid.id" value="<s:property value='#session.loginUser.id'/>"/>
				      <input type="text" name="obj.createDate" value="<s:property value='obj.createDate'/>"/>	
				      <input type="text" name="obj.updateDate" value="<%=str_date %>"/>	
					  </tr>			     				     
				   </s:else>
				   <tr>
				      <td>標題</td>
				      <td>
				        <input type="text" name="obj.title" datatype="*1-100"  value="<s:property value='obj.title'/>" />
				      </td>				       
				      <td class="tdcolor">日期</td>
					  <td><input type="text" name="obj.colDateMain" value="<%=str_date%>" readonly style="color:blue" /></td>
					  <td>下單人</td>
					  <td>
					  <s:if test="obj==null">
					    <input type="text" name="obj.orderManMain" value="<s:property value='#session.loginUser.name'/>"/>
					  </s:if>
					  <s:else>
					    <input type="text" name="obj.orderManMain" value="<s:property value='obj.orderManMain'/>"/>
					  </s:else>					 
					  </td>					  
				   </tr>
				   <s:if test="obj==null">
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
			     <td class="tdcolor">重要性</td>
			     <td class="tdcolor">型體</td>
			     <td class="tdcolor">結構</td>
			     <td class="tdcolor">樣品用途</td>
			     <td class="tdcolor">數量</td>
			     <td class="tdcolor">單重</td>
			     <td class="tdcolor">留底量</td>
			     <td class="tdcolor">不良</td>			     
			     <td class="tdcolor">型體負責人</td>
			     <td class="tdcolor">可否請款</td>
			     <td class="tdcolor">量產數量</td>
			     <td class="tdcolor">需求料的重量</td>			    			     			     		     
			     <td class="tdcolor">備註</td>
			 </tr>				
			  
			    <s:iterator value="obj" status="x" id="temp">
			    <tr class="bluecss">
			     <td><input type="hidden" name="cbox"/></td>			           			          			          			            			          	     
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].importmant" value="<s:property value='importmant'/>" /></td>			    
			     <td><input type="text" name="obj.webColproductItemses[${x.index}].shape" value="<s:property value='shape'/>" readonly style="color:blue" /></td>			    			     			     
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].CStructure" value="<s:property value='CStructure'/>" /></td>			     
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].purpose" value="<s:property value='purpose'/>" datatype="my0-8"  id="purpose_${x.index}"/></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].numbers" value="<s:property value='numbers'/>" datatype="my0-8"  id="numbers_${x.index}"/></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].weight" value="<s:property value='weight'/>" datatype="n0-8"  id="weight_${x.index}"/></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].remainNum" value="<s:property value='remainNum'/>"   /></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].unhealthNum" value="<s:property value='unhealthNum'/>"  /></td>
			    
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].picMan" value="<s:property value='picMan'/>" datatype="my0-8"  id="picMan_${x.index}"/></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].paymk" value="<s:property value='paymk'/>" datatype="n0-8"  id="paymk_${x.index}"/></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].numbersb" value="<s:property value='numbersb'/>"/></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].weightb" value="<s:property value='weightb'/>"/></td>
			      <td >
			      <input type="text" name="obj.webColproductItemses[${x.index}].remarks" value="<s:property value='remarks'/>" />			      
			      <input type="hidden" name="obj.webColproductItemses[${x.index}].webColproductMain.billNo" value="<s:property value='webColproductMain.billNo'/>" />		     
			      </td>			      		      
			  </tr>
			    </s:iterator>		    			    	         			  			 	  			
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
		var timecreat = document.getElementById("obj_timecreate").value;		
		var cbox_length=document.getElementsByName("cbox").length;
		if (factno != "" && timecreat != "") {
			kyzjs.makeBillNo(factno, timecreat, function(x) {
				dwr.util.setValue("obj_billno", x);								 			  								
			});
			document.getElementById("addbtn").disabled="";
			document.getElementById("addbtn").style.color="black";					 	 		
		}
		
	}
	
var j=0;
	function addRow(){	    
        var factno=document.getElementById("dwrFactNo").value;
        var billno=document.getElementById("obj_billno").value;
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
             
             var newTd9=newTr.insertCell();
             var newTd10=newTr.insertCell();
             var newTd11=newTr.insertCell();
             var newTd12=newTr.insertCell();
        	
        newTd00.innerHTML='<input type="hidden" name="cbox"/><input type="image" src="images/del.gif" onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode)"/>'; 
        newTd0.innerHTML = '<input type="text" name="obj['+j+'].importmant" value="" />';                       
        newTd1.innerHTML= '<input type="text" name="obj['+j+'].id.itemNo" value="0'+j+'"  datatype="*" style="color:blue"  readonly/>';
        
        
        newTd2.innerHTML='<input type="text" name="obj['+j+'].matNo" value="" />';
        newTd3.innerHTML='<input type="text" name="obj['+j+'].qtyExpect" value="" datatype="my0-8"/><span class="Validform_checktip"></span>';
        newTd4.innerHTML='<input type="text" name="obj['+j+'].qtyOk" value="" datatype="my0-8"/><span class="Validform_checktip"></span>';
        newTd5.innerHTML='<input type="text" name="obj['+j+'].personNo"  datatype="n0-8"/><span class="Validform_checktip"></span>';
        if(qtyPair==null&&moneyType==null){
        	newTd6.innerHTML='<input type="text" name="obj['+j+'].qtyPair"  id="qtyPair"/>';    
            newTd7.innerHTML='<input type="text" name="obj['+j+'].moneyType"  id="moneyType"/>';
        	
        }else{
        	newTd6.innerHTML='<input type="text" name="obj['+j+'].qtyPair" value="'+qtyPair.value+'" />';    
            newTd7.innerHTML='<input type="text" name="obj['+j+'].moneyType" value="'+moneyType.value+'" />';
        }
        newTd8.innerHTML='<input type="text" name="obj['+j+'].memoMk" value="" />'+
        '<input type="hidden" name="obj['+j+'].id.kyzExpectmatm.id.factNo" value="'+factno+'"'+'/>'+     
        '<input type="hidden" name="obj['+j+'].id.kyzExpectmatm.id.billNo" value="'+billno+'"'+'/>';       
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
