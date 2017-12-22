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
 
　     
    
    <form action="bussletter_add" method="post" id="form"  enctype="multipart/form-data">         
		<table  class="table table-condensed" >	    	
			<tbody >
				    <tr>
				        <td >所屬單位</td>
				        <td>
				        <!-- <input type="text" name="bussletter.unit" datatype="*"  value="<s:property value='bussletter.unit'/>" /> -->
				        <input type="text" id="dep_temp" disabled/>
				        <div id="div_dep" style="display:none">
				           <select name="bussletter.depId.depId" datatype="*" id="depId" onchange="checkvisaflow()"></select>
				         </div>
				        </td>
				        
				        <td >姓名</td>
				        <td>
				        <s:if test="bussletter==null">
						   <input type="text" name="bussletter.username"   value="<s:property value='#session.loginUser.name'/>" style="color:blue" readonly/>
						   <input type="hidden" name="bussletter.userAccount" value="<s:property value='#session.loginUser.username'/>"/>
						   <input type="hidden" name="bussletter.userEmail" value="<s:property value='#session.loginUser.email'/>"/>
						    <input type="hidden" name="bussletter.userId" value="<s:property value='#session.loginUser.id'/>"/>
						    <input type="hidden" name="bussletter.useremail" value="<s:property value='#session.loginUser.email'/>"/>
						</s:if>
						<s:else>
						   <input type="text" name="bussletter.username"   value="<s:property value='bussletter.username'/>" style="color:blue" readonly/>
						   <input type="hidden" name="bussletter.userAccount" value="<s:property value='bussletter.userAccount'/>"/>
						   <input type="hidden" name="bussletter.userId" value="<s:property value='bussletter.userId'/>"/>
						   <input type="hidden" name="bussletter.useremail" value="<s:property value='bussletter.useremail'/>"/>
						   
						</s:else>
						</td>										        				        				        				        
				    </tr>
				    <s:if test="bussletter==null">
				   	<input type="hidden" name="isnull" value="isNull" id="isNull"/><!--判斷變量 -->										
					<tr>
						<td >廠別</td>
						<td >
						<s:if test="#session.factNo!='tw'">
						   <input type="text" style="color:blue"
							name="bussletter.factNo" value="${factNo}" readonly id="dwrFactNo" />	
						</s:if>  
						<s:else>
						   <select style="color:blue"
							name="bussletter.factNo" datatype="*" id="dwrFactNo"
							onchange="checkvisaflow(),makeBillNo(),loaddepments()">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select>
						</s:else>						
						</td>																	
						<td ><font color="grey">類別</font></td>
				        <td >
				        <!--<input type="hidden" name="bussletter.visaSort" value="TR" id="hidden_kytype"/> 類型寫死爲：TR   20160203 -->
				        <input type="hidden" name="bussletter.visaSort"  id="hidden_kytype"/>
				         <select  id="dwr_kytype"  style="color:grey;background-color:#f5f5f5;border-color:grey" disabled>
				            <option value="">請選擇</option>
				         </select>				        	
				        </td>
					</tr>																													    
				   </s:if>
				   <s:else>
				    <tr>
				      <td >廠別</td>				      
				      <td>
				      <input type="text" name="bussletter.factNo" value="<s:property value='bussletter.factNo'/>" readonly style="color:blue" />
				      <input type="hidden" name="isnull" value="notNull"/><!--判斷變量 -->
				      </td>				     				      				     
				      <td >類別</td>
				      <td><input type="text" value="<s:property value='bussletter.visaSort'/>" name="bussletter.visaSort" style="color:blue"  readonly/></td>
				    </tr> 				     				     
				   </s:else>
				   				   				    				  			
					<tr>
				        <td >職務</td>
				        <td><input type="text" name="bussletter.position" datatype="*"  value="<s:property value='bussletter.position'/>" /></td>
				        
				        <td >職務代理人</td>
				        <td><input type="text" name="bussletter.GAgent" datatype="*"  value="<s:property value='bussletter.GAgent'/>"/>
				        				        
				        </td>				        				         				        				        				        
				    </tr>
				    <tr>
				      <td >出差地點</td>				      
				      <td>
				      <input type="text" name="bussletter.address" value="<s:property value='bussletter.address'/>" datatype="*"/>
				      </td>				     				      				     
				      <td >出差開始日期<br/>出差結束日期</td>
				      <td>
				         <input type="text" value="<s:date name='bussletter.dateFrom' format='yyyyMMdd'/>" name="dateFrom" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" datatype="*"/><br/>
				         <input type="text" value="<s:date name='bussletter.dateEnd' format='yyyyMMdd'/>" name="dateEnd" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" datatype="*"/>
				      </td>
				    </tr>
				    <tr>
				      <td >去程班機時間</td>				      
				      <td>
				      <input type="text"  value="<s:date name='bussletter.timeFrom' format='yyyyMMddhhmm'/>" name="timeFrom" onclick="WdatePicker({dateFmt:'yyyyMMddHHmm'})" class="Wdate"/>
				      </td>				     				      				     
				      <td >回程班機時間</td>
				      <td><input type="text" value="<s:date name='bussletter.timeEnd' format='yyyyMMddhhmm'/>" name="timeEnd" onclick="WdatePicker({dateFmt:'yyyyMMddHHmm'})" class="Wdate"/></td>
				    </tr>  
				   				   
					<tr>
					    <td >出差計劃</td>	
						<td  colspan="10">
				           <textarea style="width:778px;height:200px" name="bussletter.planList"  wrap="off"  tip="申請內容" altercss="gray" class="gray" datatype="*"><s:property value="bussletter.planList"/></textarea>				                                           				         				           				           				           
				        </td>						
					</tr>
					 <tr>			    				    
				      <td >申請單號</td>				        
				      <td>
				        <s:if test="bussletter==null">	
				          		<input type="text" name="bussletter.blNo" value="自動生成" readonly style="color:blue" id="bussletter_billno" datatype="*"/>
				          		<input type="hidden" name="bussletter.createDate" datatype="*"  value="<%=str_date%>" style="color:blue" id="ymExpect" readonly/>	        
				        </s:if>
				        <s:else>
				               <input type="text" name="bussletter.blNo" value="<s:property value='bussletter.blNo'/>" readonly style="color:blue" />
				               <input type="hidden" name="bussletter.createDate" datatype="*"  value="<s:property value='bussletter.createDate'/>" style="color:blue" readonly/>
				        </s:else>
				        <input type="hidden" value="<s:property value='bussletter.filesYn'/>" name=""/>
				        <input type="hidden" value="<s:property value='bussletter.firstPage'/>" name=""/>				       				      
				      </td>				     
				      <%--<td >附档</td>
				      <td colspan="3">
				      <div style="width:300px" id="divfile">
				      <input type="file" name="files" style="width:150px"/><a href="javascript:addFile()">添加多個</a>
				      </div>
				      </td>--%>				        	        				       																					        
				    </tr>												
			</tbody>			
			</table>
			<%--<s:if test='bussletter.filesYn=="1"'>
	           <hr/>
	          <span style="color:blue;">附檔:</span><br/>
	           <s:iterator value="#session.list_filesexp">
	             <a href="/upload_letter/<s:property value='billno'/>/<s:property value="%{toUrl2(filename)}"/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
	         </s:iterator>	  
	         </s:if>--%>
	         <hr/> 						 
			  <center style="width:750px;margin-left:50px">			    
				<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
					type="reset" id="reset" value="重置" class="btn btn-primary"/>
					<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>					
			</center>					
	</form>


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
			/* beforeSubmit:function(curform){
				loadi=layer.load("正在處理,請稍等...(系統爲了節省開銷,已取消自動下載函文!)")
			} */
			ajaxPost:true,
			callback:function(data){
			   if(data=="0"){
			      layer.msg("提交成功",3,1);
			      loadUrl("bussletter_findPageBean");
			   }else{
			      //alert(data.responseText);
			      layer.msg("提交失敗",3,3);
			   }
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
		//var billnos = document.getElementById("bussletter_billno");
		if (factno != "" && timecreat != "") {
			webbussletterjs.makeBillNo(factno, timecreat, function(x) {
				dwr.util.setValue("bussletter_billno", x);								  			  								
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

  function back(){		
		loadUrl("bussletter_findPageBean?backIndex=1");
	}
	
function checkWebbussType(fact){
	jq.ajax({
		type:"POST",
		dataType:"json",
		data:"factNo="+fact,
		url:"visaflow_findWebbuss",
		success:function(data){
			if(data=='0'){
				jq("#sub").removeAttr("disabled");
				jq("#sub").val("確定");
				jq("#sub").css("color","white");
			}else{
				jq("#sub").attr("disabled",true);
				jq("#sub").val("鎖定");
				jq("#sub").css("color","red");
				layer.msg("對不起，該廠還沒有創建出差流程，不能申請",3,3);
			}
		}
	});
}

function loaddepments(){		
		var factno=jq("#dwrFactNo").val();
		if(factno!=""){
		   jq.ajax({
				type:"post",
				dateType:"json",
				data:{factNo:factno},
				url:"webdep_findWebDepartmentByFactNo",
				async:false,
				success:function(data){					
					jq("#depId").empty();
					var item="";
					if(data.length>0){
						item+="<option value=''>請選擇部門</option>";
						jq.each(data,function(i,obj){
							item+="<option value='"+obj.depId+"'>"+obj.depName+"</option>";					
						});
						jq("#depId").append(item);
						jq("#div_dep").show();
						jq("#dep_temp").remove();
						unlockbtn();
															
					}else{
					    layer.msg("對不起，該廠還沒有部門資料，不能申請",3,3);				    
					    if(jq("#dep_temp").length==0){
					       jq("#div_dep").before("<input type='text' id='dep_temp' disabled/>");
					    }					    
						jq("#div_dep").hide();
						lockbtn();												
					}				
					
				}
			});
		}						
	}
	
	
function lockbtn(){
    jq("#sub").attr("disabled",true);
	jq("#sub").val("鎖定");
	jq("#sub").css("color","red");
}

function unlockbtn(){
    jq("#sub").removeAttr("disabled");
	jq("#sub").val("確定");
	jq("#sub").css("color","white");
}	


	
	
	function checkvisaflow() {
	    var factno=jq("#dwrFactNo").val();
	    var depId=jq("#depId").val();
		if (factno != ""  && depId != ""&&depId!=null) {		    
			kyzvisaflowjs.findVisaSort_dwr5(factno, "TR", depId, "Y",
					function(x) {
						if (x != null && x.length > 0) {
						    jq("#hidden_kytype").val(x);
							unlockbtn();
						} else {
						    layer.msg("對不起，該廠還沒有創建出差流程，不能申請",3,3);
							lockbtn();
						}
					});
		}
	}
</script>
<script type='text/javascript' src='dwr/interface/webbussletterjs.js'></script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/kyzvisaflowjs.js'></script>
<script type="text/javascript">
jq(function(){
	if(jq("#isNull").val()=="isNull"){
	    makeBillNo();
	    loaddepments();	     
	   }
})
</script>	
</body>
</html>
