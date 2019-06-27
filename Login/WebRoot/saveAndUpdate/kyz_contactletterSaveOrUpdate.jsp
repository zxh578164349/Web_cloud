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
 
      <form action="kyzletter_add" method="post" id="form"  enctype="multipart/form-data" target="frameFile"> 
		<table class="table table-condensed">	    	
			<tbody id="tb_list_info2">
				    <tr>
				        <td class="tdcolor">標題</td>
				        <td><input type="text" name="kyzletter.title" datatype="*1-100"  value="<s:property value='kyzletter.title'/>" /></td>
				        
				        <td class="tdcolor">受文者</td>
				        <td><input type="text" name="kyzletter.toUser" datatype="*0-50"  value="<s:property value='kyzletter.toUser'/>"/></td>
				        <td class="tdcolor">承辦人</td>
				        <td>
				        <s:if test="kyzletter==null">
						   <input type="text" name="kyzletter.userNm"   value="<s:property value='#session.loginUser.name'/>" style="color:blue" readonly/>
						   <input type="hidden" name="kyzletter.userAccount" value="<s:property value='#session.loginUser.username'/>"/>
						   <input type="hidden" name="kyzletter.userId" value="<s:property value='#session.loginUser.id'/>"/>
						   <input type="hidden" name="kyzletter.useremail" value="<s:property value='#session.loginUser.email'/>"/>
						</s:if>
						<s:else>
						   <input type="text" name="kyzletter.userNm"   value="<s:property value='kyzletter.userNm'/>" style="color:blue" readonly/>
						   <input type="hidden" name="kyzletter.userAccount" value="<s:property value='kyzletter.userAccount'/>"/>
						   <input type="hidden" name="kyzletter.userId" value="<s:property value='kyzletter.userId'/>"/>
						   <input type="hidden" name="kyzletter.useremail" value="<s:property value='kyzletter.useremail'/>"/>
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
							 id="dwrFactArea" >
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator> 
						</select></td>
						
						<td class="tdcolor">類別</td>
				        <td >
				         <select  id="dwr_kytype" onchange="loadwebformtypes();checkType()" datatype="*" style="color:blue">
				            <option value="">請選擇</option>
				         </select>				         
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>				         				         				         	
				        </td>
					</tr>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<tr>
						<td class="tdcolor">廠別</td>
						<td ><select style="color:blue"
							name="kyzletter.id.factNo" datatype="*" id="dwrFactNo"
							onchange="getFactArea(this.value);makeBillNo(),getKyType2(this.value);checkType();loadwebformtypes()">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						
						<td class="tdcolor">廠別狀態</td>
						<td><select name="kyzletter.factCode"
							 id="dwrFactArea">
							 <option value="">請選擇廠別狀態</option>
						</select></td>
						
						<td class="tdcolor">類別</td>
				        <td >
				         <select  id="dwr_kytype" onchange="loadwebformtypes();checkType()" datatype="*" style="color:blue">
				            <option value="">請選擇</option>
				         </select>
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>				         				         
				        </td>
					</tr>
					</s:if>																							    
				   </s:if>
				   <s:else>
				      <td class="tdcolor">廠別</td>				      
				      <td>
				      <input type="text" name="kyzletter.id.factNo" value="<s:property value='kyzletter.id.factNo'/>" readonly style="color:blue" id="dwrFactNo"/>
				      <input type="hidden" name="isnull" value="notNull"/><!--判斷變量 -->
				      </td>
				     
				      <td class="tdcolor">廠別狀態</td>
				      <td><input type="text" name="kyzletter.factCode" value="<s:property value='kyzletter.factCode'/>" readonly style="color:blue" /></td>
				     
				      <td class="tdcolor">類別</td>
				      <td><input type="text" value="<s:property value='kyzletter.visaType'/>" name="kyzletter.visaType" style="color:blue"  readonly/></td>				     				     
				   </s:else>				   				    				  			
					<tr>
				        <td class="tdcolor">承辦單位</td>
				        <td><input type="text" name="kyzletter.secNo" datatype="*1-20"  value="<s:property value='kyzletter.secNo'/>" /></td>
				        
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
				          		<input type="text" name="kyzletter.id.billNo" value="自動生成" readonly style="color:blue" id="kyzletter_billno" datatype="*"/>	        
				        </s:if>
				        <s:else>
				               <input type="text" name="kyzletter.id.billNo" value="<s:property value='kyzletter.id.billNo'/>" readonly style="color:blue" />
				        </s:else>				      
				      </td>
				      <td class="tdcolor">附档</td>
				      <td>
				      <div style="width:300px" id="divfile">
				      <input type="file" name="files" style="width:150px"/><a href="javascript:addFile()">添加多個</a>
				      </div>
				      </td>
				      <td class="tdcolor">是否緊急</td>											
						   <s:if test='kyzletter.emerMk=="0"'>
						       <td >是<input type="radio" name="kyzletter.emerMk" value="<s:property value='kyzletter.emerMk'/>" checked/>&nbsp;&nbsp;否<input type="radio" name="kyzletter.emerMk" value="1"/></td>
						   </s:if>
						   <s:if test='kyzletter.emerMk=="1"'>
						       <td >是<input type="radio" name="kyzletter.emerMk" value="0" />&nbsp;&nbsp;否<input type="radio" name="kyzletter.emerMk" value="<s:property value='kyzletter.emerMk'/>" checked/></td>
						   </s:if>
						    <s:if test="kyzletter==null">
						       <td >是<input type="radio" name="kyzletter.emerMk" value="0" />&nbsp;&nbsp;否<input type="radio" name="kyzletter.emerMk" value="1" checked/></td>
						   </s:if>							   			        	        				       																					        
				    </tr>
				    <tr>
					    <td class="tdcolor">CC(呈)</td>	
						<td  colspan="10">
				           <textarea style="width:810px;height:40px" name="kyzletter.chargeList"  wrap="off"   tip="CC(呈)" altercss="gray" class="gray"><s:property value="kyzletter.chargeList"/></textarea>				                                           				         			          
				        </td>
						
					</tr>
					<tr>
					    <td class="tdcolor">申請內容</td>	
						<td  colspan="10">
				           <textarea style="width:810px;height:240px" name="kyzletter.memoMk"  wrap="off"   tip="申請內容" altercss="gray" class="gray" id="memoMk" datatype="*"><s:property value="kyzletter.memoMk"/></textarea>				                                           				         				           				           
				           <input type="hidden" value="<s:property value='kyzletter.filesYn'/>" name="kyzletter.filesYn"/>
				           <input type="hidden" value="<s:property value='kyzletter.firstPage'/>" name="kyzletter.firstPage"/>
				        </td>
						
					</tr>
					<s:if test="kyzletter==null">
					  <tr>
					    <td class="tdcolor">是否分部門</td>
					    <td >
					                   是<input type="radio" name="trMk" value="Y" checked datatype="*" onclick="checkType()"/>&nbsp;&nbsp;
			                                        否<input type="radio" name="trMk" value="N" onclick="checkType()"/> 
					    </td>					    					    
					    <td>
					    <div id="div_depar2" style="display:none">部門</div>
					    <div id="div_webform2" style="display:none">小類別</div>
					    </td>				    
					    <td colspan="10">				      
					      <div id="div_depar" style="display:none"><select id="sel_depar" onchange="checkType()" datatype="*"></select></div>
					      <div id="div_webform" style="display:none"><select name="fid" datatype="*" onchange="checkType()"></select></div>
					      <input type="hidden" name="kyzletter.visaType" id="hidden_kytype"/> 
					    </td>
					 </tr>
					</s:if>
																	
			</tbody>			
			</table>
			<s:if test='kyzletter.filesYn=="1"'>
	          <%--  <hr/>
	          <span style="color:blue;">附檔:</span><br/>
	          <div id="fileJson" style="width:850px">
	           <s:iterator value="#session.list_filesexp">
	             <a href="/upload_letter/<s:property value='billno'/>/<s:property value="%{toUrl2(filename)}"/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>
	             <a href="javascript:lookJson('${billno}',${id},'<s:property value="%{toUrl(filename)}"/>')">
	              <img src="images/icon/del_file.png" alt="刪除" title="刪除" style="border:0px"/>
	           </a>&nbsp;&nbsp;
	         </s:iterator>
	         </div>	--%>  
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
			ignoreHidden:true,
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
		
		if(jq("#dwrFactNo").val()=="YMUS"){
			jq("#memoMk").attr("datatype","*40-2000");
		}else{
			jq("#memoMk").attr("datatype","*");
		}
		
	});

function makeBillNo() {
        
		var factno = document.getElementById("dwrFactNo").value;
		var timecreat = document.getElementById("ymExpect").value;
		if (factno != "" && timecreat != "") {
			kyzcontactletterjs.makeBillNo(factno, timecreat, function(x) {
				dwr.util.setValue("kyzletter_billno", x);								  			  								
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
		loadUrl("kyzletter_findPageBean3?backIndex=1");
	}
 
  
  function gook(){
	  layer.msg("操作成功",3,1);
	  loadUrl("kyzletter_findPageBean");
  }
 
 
</script>
<script type='text/javascript' src='dwr/interface/kyzcontactletterjs.js'></script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
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
