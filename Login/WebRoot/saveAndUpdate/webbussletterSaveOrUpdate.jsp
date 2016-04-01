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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
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
			      layer.mag("提交失敗",3,3);
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
		var billnos = document.getElementById("bussletter_billno");
		if (factno != "" && timecreat != "") {
			webbussletterjs.makeBillNo(factno, timecreat, function(x) {
				dwr.util.setValue("bussletter_billno", x);								  			  								
			});				 		
		}
		
	}								
function getKyType(){
	 var factno=document.getElementById("dwrFactNo").value;
	 if(factno!=null&&factno!=""){
	     webtypejs.findByFactNo(factno,function(x){
       if(x.length>0){
          dwr.util.addOptions("dwr_kytype",x,"webtypeMk","typeName");
       }
         
     });
	 }   
	}
	
function getKyType2(factno){
	 document.getElementById("dwr_kytype").length=1;	 
	 if(factno!=null&&factno!=""){
	     webtypejs.findByFactNo(factno,function(x){
       if(x.length>0){
          dwr.util.addOptions("dwr_kytype",x,"webtypeMk","typeName");
       }
         
     });
	 }
    
	}
	 
  function checkType(type){
     dwrFactNo=document.getElementById("dwrFactNo").value;
     dwremail=document.getElementById("dwr_email").value.toLowerCase(); //登錄人的email要轉化爲小寫,因爲申請人email已全部轉化爲小寫（20151022）;
     if(dwrFactNo!=""&&type!=""){
         kyzvisaflowjs.findByType_Dwr(dwrFactNo,type,function(x){
            if(x==0){//流程不存在
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
                  
               }); 
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

  function back(){		
		loadUrl("/Login/bussletter_findPageBean?backIndex=1");
	}
	
/*jq(function(){
   if(jq("#isNull").val()=="isNull"){
     window.onload=makeBillNo();
     if(jq("#dwrFactNo").val()!="tw"){
    	 checkWebbussType(jq("#dwrFactNo").val());
     }
   }
});	
window.onload=function(){
	if(jq("#isNull").val()=="isNull"){
	    makeBillNo();
	     if(jq("#dwrFactNo").val()!="tw"&&jq("#dwrFactNo").val()!=""){
	    	 checkWebbussType(jq("#dwrFactNo").val());
	     }
	   }
}*/
jq(function(){
	if(jq("#isNull").val()=="isNull"){
	    makeBillNo();
	     if(jq("#dwrFactNo").val()!="tw"&&jq("#dwrFactNo").val()!=""){
	    	 checkWebbussType(jq("#dwrFactNo").val());
	     }
	   }
})

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
</script>
<script type='text/javascript' src='/Login/dwr/interface/webbussletterjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webtypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>



</head>
<body >  
 
　     
    
    <form action="bussletter_add" method="post" id="form"  enctype="multipart/form-data">   
        <h2>人員出差申請書</h2>
		<table  class="table table-condensed" >	    	
			<tbody >
				    <tr>
				        <td >所屬單位</td>
				        <td><input type="text" name="bussletter.unit" datatype="*"  value="<s:property value='bussletter.unit'/>" /></td>
				        
				        <td >姓名</td>
				        <td>
				        <s:if test="bussletter==null">
						   <input type="text" name="bussletter.username"   value="<s:property value='#session.loginUser.name'/>" style="color:blue" readonly/>
						   <input type="hidden" name="bussletter.userAccount" value="<s:property value='#session.loginUser.username'/>"/>
						</s:if>
						<s:else>
						   <input type="text" name="bussletter.username"   value="<s:property value='bussletter.username'/>" style="color:blue" readonly/>
						   <input type="hidden" name="bussletter.userAccount" value="<s:property value='bussletter.userAccount'/>"/>
						</s:else>
						</td>										        				        				        				        
				    </tr>
				    <s:if test="bussletter==null">
				   	<input type="hidden" name="isnull" value="isNull" id="isNull"/><!--判斷變量 -->					
					<s:if test="#session.factNo!='tw'">
					<tr>
						<td >廠別</td>
						<td >
						  
						<input type="text" style="color:blue"
							name="bussletter.factNo" value="${factNo}" readonly id="dwrFactNo" />							
						</td>																	
						<td ><font color="grey">類別</font></td>
				        <td >
				        <input type="hidden" name="bussletter.visaSort" value="TR" id="hidden_kytype"/><!-- 類型寫死爲：TR   20160203 -->
				         <select  id="dwr_kytype" onchange="checkType(this.value)" style="color:grey;background-color:#f5f5f5;border-color:grey" disabled>
				            <option value="">請選擇</option>
				         </select>
				         <!--  <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>
				         <input type="hidden" name="bussletter.visaSort" id="hidden_kytype"/>-->	
				        </td>
					</tr>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<tr>
						<td >廠別</td>
						<td ><select style="color:blue"
							name="bussletter.factNo" datatype="*" id="dwrFactNo"
							onchange="checkWebbussType(this.value),makeBillNo()">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>																		
						<td ><font color="grey">類別</font></td>
				        <td >
				         <select  id="dwr_kytype" onchange="checkType(this.value)"  style="color:grey;background-color:#f5f5f5;border-color:grey" disabled>
				            <option value="">不可用</option>
				         </select>
				         <input type="hidden" name="bussletter.visaSort" value="TR" id="hidden_kytype"/><!-- 類型寫死爲：TR   20160203 -->
				         <!--  <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>
				         <input type="hidden" name="bussletter.visaSort" id="hidden_kytype"/>
				         -->	
				        </td>
					</tr>
					</s:if>																							    
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
				           <textarea style="width:778px;height:200px" name="bussletter.planList" autofocus="autofocus" wrap="hard" wrap="physical"  tip="申請內容" altercss="gray" class="gray" datatype="*"><s:property value="bussletter.planList"/></textarea>				                                           				         				           				           				           
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
	
</body>
</html>
