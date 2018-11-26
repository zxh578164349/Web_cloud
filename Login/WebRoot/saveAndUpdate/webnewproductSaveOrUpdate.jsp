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
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd_hh:mm");
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
 
      <form action="webnewpro_add" method="post" id="form"  enctype="multipart/form-data" target="frameFile"> 
		<table class="table table-condensed">	    	
			<tbody id="tb_list_info2">
			       <s:if test="obj==null">
				   	<input type="hidden" name="isnull" value="isNull"/><!--判斷變量 -->					
					
					<tr>
					<s:if test="#session.factNo!='tw'">
						<td class="tdcolor">廠別</td>
						<td ><input type="text" style="color:blue"
							name="obj.factNo" value="${factNo}" readonly id="dwrFactNo" />							
						</td>												
						
						<td class="tdcolor">類別</td>
				        <td >
				         <select  id="dwr_kytype" onchange="checkType(this.value)" datatype="*" style="color:blue">
				            <option value="">請選擇</option>
				         </select>	
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>			         			         
				         <input type="hidden" name="obj.visaType" id="hidden_kytype"/>				         
				         <div id="div_depar" style="display:none"><select id="sel_depar" onchange="checkType2()"></select></div>	
				        </td>
				        </s:if>
				        <s:if test="#session.factNo=='tw'">
				          <td class="tdcolor">廠別</td>
						<td ><select style="color:blue"
							name="obj.factNo" datatype="*" id="dwrFactNo"
							onchange="makeBillNo(),getKyType2(this.value)">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
																		
						<td class="tdcolor">類別</td>
				        <td >
				         <select  id="dwr_kytype" onchange="checkType(this.value)" datatype="*" style="color:blue">
				            <option value="">請選擇</option>
				         </select>
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>
				         <input type="hidden" name="obj.visaType" id="hidden_kytype"/>
				         <div id="div_depar" style="display:none"><select id="sel_depar" onchange="checkType2()"></select></div>	
				        </td>
				        </s:if>				        				        
				        <td class="tdcolor">申請單號</td>				        
				        <td>				       	
				          		<input type="text" name="obj.billNo" value="自動生成" readonly style="color:blue" id="billno" datatype="*"/>
				          		<input type="hidden" name="obj.webUserByCreateUserFid.id" value="<s:property value='#session.loginUser.id'/>"/>
				          		<input type="text" name="obj.createDate" value="<%=str_date%>" id="createDate"/>	        				        				               				        				      
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
				      <td><input type="text" value="<s:property value='obj.visaType'/>" name="obj.visaType" style="color:blue"  readonly/></td>				     				     
				      
				       <td class="tdcolor">申請單號</td>
				       <td>				       
				      <input type="text" name="obj.billNo" value="<s:property value='obj.billNo'/>" readonly style="color:blue" />
				      <input type="text" name="obj.webUserByCreateUserFid.id" value="<s:property value='obj.webUserByCreateUserFid.id'/>"/>
				      <input type="text" name="obj.webUserByUpdateUserFid.id" value="<s:property value='#session.loginUser.id'/>"/>
				      <input type="text" name="obj.createDate" value="<s:property value='obj.createDate'/>"/>	
				      <input type="text" name="obj.updateDate" value="<%=str_date %>"/>	
				      </td>
				      </tr>
				   </s:else>
			
				    <tr>
				        <td class="tdcolor">接收日期</td>
				        <td><input type="text" name="obj.receiveDate" datatype="*1-100"  value="<s:property value='obj.receiveDate'/>" onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'})" class="Wdate"/></td>
				        
				        <td class="tdcolor">新材料名稱</td>
				        <td><input type="text" name="obj.PName" datatype="*0-50"  value="<s:property value='obj.PName'/>"/></td>
				        <td class="tdcolor">附档</td>
				      <td>
				      <div style="width:300px" id="divfile">
				      <input type="file" name="files" style="width:150px"/><a href="javascript:addFile()">添加多個</a>
				      </div>
				      </td>	 		         				        				        				        
				    </tr>				    				   				    				  												    
				    <tr>
					    <td class="tdcolor">推銷客戶及詳細用途說明</td>	
						<td  colspan="10">
				           <textarea style="width:810px;height:240px" name="obj.PExp"  wrap="off"   tip="CC(呈)" altercss="gray" class="gray"><s:property value="obj.PExp"/></textarea>				                                           				         			          
				        </td>
						
					</tr>
					<tr>
					    <td class="tdcolor">客人回饋結果</td>	
						<td  colspan="10">
				           <textarea style="width:810px;height:100px" name="obj.PResultGuest"  wrap="off"   tip="申請內容" altercss="gray" class="gray" id="PResultGuest" datatype="*"><s:property value="obj.PResultGuest"/></textarea>				                                           				         				           				           				          
				        </td>
						
					</tr>
					<tr>
					    <td class="tdcolor">結果</td>	
						<td  colspan="10">
				           <textarea style="width:810px;height:100px" name="obj.PResult"  wrap="off"   tip="申請內容" altercss="gray" class="gray" id="PResult" datatype="*"><s:property value="obj.PResult"/></textarea>				                                           				         				           				           
				           <input type="hidden" value="<s:property value='obj.filesYn'/>" name="obj.filesYn"/>
				           <input type="hidden" name="trMk" value="Y"/> 
				        </td>						
					</tr>										    				                  			                                         			                                            																
			</tbody>			
			</table>
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
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my0-8": /^\d{0,8}(\.[0-9]{1,4})?$/
			},
			beforeSubmit:function(curform){
				layer.load("正在處理,請稍等...(系統爲了節省開銷,已取消自動下載函文!)")
			}
			
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";				
		
	});
		

function makeBillNo() {     
		var factNo =document.getElementById("dwrFactNo").value;
		var createDate = document.getElementById("createDate").value;
		if(factNo!=""&&createDate!=""){
		   jq.ajax({
		   type:"post",
		   url:"webnewpro_makeBillNo",
		   data:{factNo:factNo,createDate:createDate},
		   dataType:"json",
		   success:function(data){
		      jq("#billno").val(data);
		   },
		   error:function(error){
		      alert("單號生成錯誤");
		      return false;
		   }
		  });
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
      inputfile.id="fileid"+i;
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
		loadUrl("webnewpro_findPageBean3?backIndex=1");
	}
 
  
  function gook(){
	  layer.msg("操作成功",3,1);
	  loadUrl("webnewpro_findPageBean");
  }
 
 
</script>
<script type='text/javascript' src='dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='dwr/interface/webtypejs.js'></script>
<script type='text/javascript' src='jquery/publicJS.js'></script>
<script type="text/javascript">
jq(function(){
	if(jq("#addorupdate").val()!="update"){
		getKyType();makeBillNo();
	}  
});
</script>	
</body>
</html>
