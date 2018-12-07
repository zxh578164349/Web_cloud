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
java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMdd");
java.text.SimpleDateFormat fmt2 = new java.text.SimpleDateFormat("yyyyMMdd_hh:mm");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = fmt.format(currentTime); //将日期时间格式化
String str_date_h = fmt2.format(currentTime); //将时间格式化
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
    <form action="webcolpro_add"  method="post" id="form"  enctype="multipart/form-data" target="frameFile">
		<table class="table table-condensed" >		    	
			<tbody id="tb_list_info2">				   
				    <s:if test="obj==null">
				   	<input type="hidden" name="isnull" value="isNull"/><!--判斷變量 -->										
					<tr>					
						<td class="tdcolor">廠別</td>
						<s:if test="#session.factNo!='tw'">
						<td ><input type="text" style="color:blue"
							name="obj.factNo.factNo" value="${factNo}" readonly id="dwrFactNo" />							
						</td>
						</s:if>
						<s:if test="#session.factNo=='tw'">
						<td ><select style="color:blue"
							name="obj.factNo.factNo" datatype="*" id="dwrFactNo"
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
						<input type="hidden" name="obj.webUserByCreateUserFid.id" value="<s:property value='#session.loginUser.id'/>"/>				        
				        <input type="hidden" name="obj.createDate" value="<%=str_date_h %>" id="createDate"/>
						</s:if>					
						<s:else>
						<tr>
				      <td class="tdcolor">廠別</td>				      
				      <td>
				      <input type="text" name="obj.factNo.factNo" value="<s:property value='obj.factNo.factNo'/>" readonly style="color:blue" id="dwrFactNo"/>
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
					  <input type="hidden" name="obj.webUserByCreateUserFid.id" value="<s:property value='obj.webUserByCreateUserFid.id'/>"/>
				      <input type="hidden" name="obj.webUserByUpdateUserFid.id" value="<s:property value='#session.loginUser.id'/>"/>
				      <input type="hidden" name="obj.createDate" value="<s:property value='obj.createDate'/>"/>	
				      <input type="hidden" name="obj.updateDate" value="<%=str_date_h %>"/>
				      
					  </tr>			     				     
				   </s:else>
				   <tr>
				      <td>標題</td>
				      <td>
				        <input type="text" name="obj.title" datatype="*1-100"  value="<s:property value='obj.title'/>" />
				      </td>				       
				      <td class="tdcolor">日期</td>
					  <td>
					  <s:if test="obj==null">
					  <input type="text" name="obj.colDateMain" value="<%=str_date%>" readonly style="color:blue"/>
					  </s:if>
					  <s:else>
					  <input type="text" name="obj.colDateMain" value="<s:property value='obj.colDateMain'/>" readonly style="color:blue"/>
					  </s:else>
					  </td>
					  <td>下單人</td>
					  <td>
					  <s:if test="obj==null">
					    <input type="text" name="obj.orderManMain" value="<s:property value='#session.loginUser.name'/>" style="color:blue" readonly/>
					  </s:if>
					  <s:else>
					    <input type="text" name="obj.orderManMain" value="<s:property value='obj.orderManMain'/>" style="color:blue" readonly/>
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
				   	<input type="hidden" value="<s:property value='obj.webColproductItemses.size'/>" id="maxNum"/>																																											    				   				   				   				    				    																																
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
			  
			    <s:iterator value="obj.webColproductItemses" status="x" id="temp">
			    <tr class="bluecss">
			     <td><input type="hidden" name="cbox"/></td>			           			          			          			            			          	     
			     <td >
			     <input type="hidden" value="<s:property value='iid'/>" name="obj.webColproductItemses[${x.index}].iid"/>
			     <select name="obj.webColproductItemses[${x.index}].importmant">			        			        
			             <s:if test='importmant=="H"'>
					     <option value="H" selected>高</option>
					     </s:if>
					     <s:else>
					     <option value="H">高</option>
					     </s:else>
					     <s:if test='importmant=="M"'>
					     <option value="M" selected>中</option>
					     </s:if>
					     <s:else>
					     <option value="M">中</option>
					     </s:else>
					     <s:if test='importmant=="L"'>
					     <option value="L" selected>低</option>
					     </s:if>
					     <s:else>
					     <option value="L">低</option>
					     </s:else>	
			     </select>			     
			     </td>			    
			     <td><input type="text" name="obj.webColproductItemses[${x.index}].shape" value="<s:property value='shape'/>" datatype="*0-80" /></td>			    			     			     
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].CStructure" value="<s:property value='CStructure'/>" datatype="*0-80"/></td>			     
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].purpose" value="<s:property value='purpose'/>" datatype="my0-8"  /></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].numbers" value="<s:property value='numbers'/>" datatype="my0-8"  /></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].weight" value="<s:property value='weight'/>" datatype="my0-8"  /></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].remainNum" value="<s:property value='remainNum'/>"  datatype="my0-8"/></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].unhealthNum" value="<s:property value='unhealthNum'/>"  datatype="my0-8"/></td>
			    
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].picMan" value="<s:property value='picMan'/>" datatype="*1-25"  id="picMan_${x.index}"/></td>
			     <td >
			     <select name="obj.webColproductItemses[${x.index}].paymk">			       			       
			       <s:if test='paymk=="Y"'>
					<option value="Y" selected>是</option>
					</s:if>
					<s:else>
					<option value="Y">是</option>
					</s:else>
			        <s:if test='paymk=="N"'>
					<option value="N" selected>否</option>
					</s:if>
					<s:else>
					<option value="N">否</option>
					</s:else>
			     </select>			     
			     </td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].numbersb" value="<s:property value='numbersb'/>" datatype="my0-8"/></td>
			     <td ><input type="text" name="obj.webColproductItemses[${x.index}].weightb" value="<s:property value='weightb'/>" datatype="my0-8"/></td>
			      <td >
			      <input type="text" name="obj.webColproductItemses[${x.index}].remarks" value="<s:property value='remarks'/>" datatype="*0-150"/>			      
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
				"my0-8": /^\d{0,8}(\.[0-9]{1,2})?$/,
				"my0-12": /^\d{0,12}(\.[0-9]{1,4})?$/
			},
			/* beforeSubmit:function(curform){
				loadi=layer.load("正在處理,請稍等...(系統爲了節省開銷,已取消自動下載函文!)");
			}, */
			ajaxPost:true,
			callback:function(data){
			   if(data=="0"){
			      layer.msg("提交成功",3,1);
			      loadUrl("webcolpro_findPageBean3?backIndex=1");
			   }else if(data=="1"){
			      //alert(data.responseText);
			      layer.msg("提交失敗",3,3);
			   }else{
			      layer.msg("函文單號已經存在，請重新添加",3,3);
			   }
			}									
		});
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留2位以內小數";
		demo.tipmsg.w["my0-12"]="只能數字且不超過12位數,可保留4位以內小數";
									
	});
					
	function deleteHtml(id) {
		id.parentNode.removeChild(id);
	}

	function getva1(){
	   alert(document.getElementById("testfile").value);
	}
	
function makeBillNo() {     
		var factNo =document.getElementById("dwrFactNo").value;
		var createDate = document.getElementById("createDate").value;
		if(factNo!=""&&createDate!=""){
		   jq.ajax({
		   type:"post",
		   url:"webcolpro_makeBillNo",
		   data:{factNo:factNo,createDate:createDate},
		   dataType:"json",
		   success:function(data){
		      jq("#obj_billno").val(data);
		      document.getElementById("addbtn").disabled="";
			  document.getElementById("addbtn").style.color="black";	
		   },
		   error:function(error){
		      alert("單號生成錯誤");
		      return false;
		   }
		  });
		}
											
	}
	
var j=0;
	function addRow(){	    
        var factno=document.getElementById("dwrFactNo").value;
        var billno=document.getElementById("obj_billno").value;                 
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
        newTd0.innerHTML = '<select  name="obj.webColproductItemses['+j+'].importmant" >'+
        '<option value="H">高</option><option value="M">中</option><option value="L">低</option></select>';                       
        newTd1.innerHTML= '<input type="text" name="obj.webColproductItemses['+j+'].shape"  datatype="*0-80"/>';               
        newTd2.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].CStructure"  datatype="*0-80"/>';
        newTd3.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].purpose"  datatype="*0-100"/><span class="Validform_checktip"></span>';
        newTd4.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].numbers"  datatype="my0-8"/><span class="Validform_checktip"></span>';           
        newTd5.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].weight" datatype="my0-8"/>';    
        newTd6.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].remainNum" datatype="my0-8"/>'; 
        newTd7.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].unhealthNum" datatype="my0-8"/>';
        newTd8.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].picMan" datatype="*0-15"/>';
        newTd9.innerHTML='<select name="obj.webColproductItemses['+j+'].paymk">'+
        '<option value="Y">是</option><option value="N">否</option></select>';
        newTd10.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].numbersb" datatype="my0-8"/>'; 
        newTd11.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].weightb" datatype="my0-8"/>';                           	     
        newTd12.innerHTML='<input type="text" name="obj.webColproductItemses['+j+'].remarks" datatype="*0-150"/>'+          
        '<input type="hidden" name="obj.webColproductItemses['+j+'].webColproductMain.billNo" value="'+billno+'"'+'/>';       
        }
        
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
	loadUrl("webcolpro_findPageBean3?backIndex=1");
}
function gook(){
	  layer.msg("操作成功",3,1);
	  loadUrl("webcolpro_findPageBean");
}

function lookPic(url){
	window.location.href=url;
}



</script>
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
	j=jq("#maxNum").val();
	if(isNaN(j)){
		j=0;
	}
});
</script>	
</body>
</html>
