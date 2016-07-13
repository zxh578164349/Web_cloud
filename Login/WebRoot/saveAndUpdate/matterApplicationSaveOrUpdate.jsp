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
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<script type="text/javascript" src="jquery/jquery-form.js"></script>

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
	});
		
	/*function checkForm(index){
		var memoSmk=jq("#memoSmk").val();//標題 
		var secNo=jq("#secNo").val();//申請單位
		var factNo=jq("#dwrFactNo").val();//廠別
		var visaSort=jq("#dwr_kytype").val();//函文類別
		var telNo=jq("#telNo").val();
		var reg_telNo=/^\d*$/;//電話驗證正則表達式
		if(memoSmk==""){
			layer.alert("標題不能爲空!");
			return false;
		}
		if(secNo==""){
			layer.alert("申請單位不能爲空");
			return false;
		}
		if(factNo==""){
			layer.alert("廠別不能爲空");
			return false;
		}
		if(visaSort==""){
			layer.alert("類別不能空");
			return false;
		}
		if(!reg_telNo.test(telNo)){
			layer.alert("電話要填寫數字");
			return false;
		}
		return true;
	}
	jq(function(){
		var options={
				beforeSubmit:checkForm,  		       		       
		        //resetForm: true, 
		        url:"kyz_add",
		        dataType:'json' ,
		        success:function(data){
		        	if(data=="0"){
		        		layer.msg("函文申請成功!",3,1);
		        		location.href="/Login/kyz_findPageBean";
		        	}else{
		        		alert(data.responseText);
		        	}		        	       	    									
		         }		         
		};
		jq("#form").submit(function(){
			jq(this).ajaxSubmit(options);
			return false;
		})										
	})*/

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
        newTd0.innerHTML = '<input type="text" name="kyz.kyzExpectmatses['+j+'].itemNm" value="" />'; 
        if(j<10){
          newTd1.innerHTML= '<input type="text" name="kyz.kyzExpectmatses['+j+'].id.itemNo" value="00'+j+'" datatype="*" style="color:blue"  readonly/>';
        }else{
          newTd1.innerHTML= '<input type="text" name="kyz.kyzExpectmatses['+j+'].id.itemNo" value="0'+j+'"  datatype="*" style="color:blue"  readonly/>';
        }
        
        newTd2.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].matNo" value="" />';
        newTd3.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].qtyExpect" value="" datatype="my0-8"/><span class="Validform_checktip"></span>';
        newTd4.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].qtyOk" value="" datatype="my0-8"/><span class="Validform_checktip"></span>';
        newTd5.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].personNo"  datatype="n0-8"/><span class="Validform_checktip"></span>';
        if(qtyPair==null&&moneyType==null){
        	newTd6.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].qtyPair"  id="qtyPair"/>';    
            newTd7.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].moneyType"  id="moneyType"/>';
        	
        }else{
        	newTd6.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].qtyPair" value="'+qtyPair.value+'" />';    
            newTd7.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].moneyType" value="'+moneyType.value+'" />';
        }
        newTd8.innerHTML='<input type="text" name="kyz.kyzExpectmatses['+j+'].memoMk" value="" />'+
        '<input type="hidden" name="kyz.kyzExpectmatses['+j+'].id.kyzExpectmatm.id.factNo" value="'+factno+'"'+'/>'+     
        '<input type="hidden" name="kyz.kyzExpectmatses['+j+'].id.kyzExpectmatm.id.billNo" value="'+billno+'"'+'/>';       
        }
        
	}
	
		
	function getFactCode(){
	    document.getElementById("dwrFactArea").value=document.getElementById("kyzs_factcode").value;
	}
   function getKyType(){
	  /*  kytypejs.findByTypeNo("VV",function(x){
	         dwr.util.addOptions("dwr_kytype",x,"typeName","typeSname");
	   }); */
	 
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
  
  function checkType(type){
     dwrFactNo=document.getElementById("dwrFactNo").value;
     dwremail=document.getElementById("dwr_email").value.toLowerCase(); //登錄人的email要轉化爲小寫,因爲申請人email已全部轉化爲小寫（20151022）
     dwr_username=document.getElementById("dwr_username").value;   
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
            
           /* else if(type.charAt(0)=='C'){//如果流程是C类（C1,C2....）  ,则要 根据申请人来选择审核流程的代号        
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
            } else{//如果流程是非C类，则不需要根据申请人选择流程
                document.getElementById("sub").disabled=false;
                document.getElementById("sub").style.color="white";
                document.getElementById("dwr_kytype").style.color="black";
                document.getElementById("hidden_kytype").value=type;
            } */
            
         });
     }
  }
  function getVisaSort(type){
     dwrfactno=document.getElementById("dwrFactNo").value;
     dwremail=document.getElementById("email").value;
     if(dwrfactno!=""){
        
     }
  }

function lookJson(billNo,id,filename){
//var jQ = jQuery.noConflict();

   jq.ajax({
      type:"get",
      dataType:"json",
      url:"kyzfile_findKyzFileJson",
      data:"billNo="+billNo+"&id="+id+"&filename="+filename,
      success:function(files){
         jq("#fileJson").html("");
          var item;
          var item_url;
         jq.each(files,function(i,file){
            item_url="javascript:lookJson('"+file.billno+"',"+file.id+",'"+file.filename+"')";
            item="<a href='/upload/"+file.billno+"/"+file.filename+"' target='_blank' title='點擊查看'>"+file.filename+            
            "</a>"+
            "<a href="+item_url+"><img src='images/icon/del_file.png' alt='刪除' title='刪除' style='border:0px'/></a>&nbsp;";
            jq("#fileJson").append(item);
         }) 
      }
   })
}


function back(){
	
	loadUrl("/Login/kyz_findPageBean3?backIndex=1");
}
function gook(){
	  layer.msg("操作成功",3,1);
	  loadUrl("kyz_findPageBean");
}
</script>
<script type='text/javascript' src='/Login/dwr/interface/kyzjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webtypejs.js'></script>
<!-- <script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script> -->

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

</head>
<body >  
    <form action="kyz_add"  method="post" id="form"  enctype="multipart/form-data" target="frameFile">
        <h2>函文申請</h2>
		<table class="table table-condensed" >		    	
			<tbody id="tb_list_info2">
				    <tr>
				        <td class="tdcolor">標題</td>
				        <td><input type="text" name="kyz.memoSmk" datatype="*"  value="<s:property value='kyz.memoSmk'/>" id="memoSmk"/></td>
				        
				        <td class="tdcolor">電話</td>
				        <td><input type="text" name="kyz.telNo" datatype="n0-11"  value="<s:property value='kyz.telNo'/>" id="telNo"/></td> 				        
				        <td class="tdcolor">申請單位</td>
				        <td><input type="text" name="kyz.secNo"  value="<s:property value='kyz.secNo'/>" datatype="*1-10" id="secNo"/></td>
				        
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
							 id="dwrFactArea"  style="color:blue">
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
							onchange="getFactArea(this.value),makeBillNo(),getKyType2(this.value)">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						
						<td class="tdcolor">廠別狀態</td>
						<td><select name="kyz.factCode"
							 id="dwrFactArea" onchange="makeBillNo()">
								<option value="">請選擇廠別狀態</option>
						</select></td>
						
						<td class="tdcolor">建立日期</td>
				        <td ><input type="text" name="yymmdd" id="kyz_timecreate"  readonly  value="<%=str_date%>" style="color:blue"></td>
					</tr>
					</s:if>																							    
				   </s:if>
				   <s:else>
				      <td class="tdcolor">廠別</td>				      
				      <td>
				      <input type="text" name="kyz.id.factNo" value="<s:property value='kyz.id.factNo'/>" readonly style="color:blue" id="dwrFactNo"/>
				      <input type="hidden" name="isnull" value="notNull"/><!--判斷變量 -->
				      </td>
				     
				      <td class="tdcolor">廠別狀態</td>
				      <td><input type="text" name="kyz.factCode" value="<s:property value='kyz.factCode'/>" readonly style="color:blue" id="dwrFactArea"/></td>
				     
				      <td class="tdcolor">修改日期</td>
				      <td><input type="text" name="yymmdd" value="<%=str_date%>" readonly style="color:blue" /></td>				     				     
				   </s:else>
				   
				    <tr>			    				    
				      <td class="tdcolor">申請單號</td>				        
				      <td>
				        <s:if test="kyz==null">	
				          		<input type="text" name="kyz.id.billNo" value="自動生成" readonly style="color:blue" id="kyz_billno" datatype="*"/>	        
				        </s:if>
				        <s:else>
				               <input type="text" name="kyz.id.billNo" value="<s:property value='kyz.id.billNo'/>" id="kyz_billno" readonly style="color:blue" />
				        </s:else>				      
				      </td>
				        	        
				       <td class="tdcolor">申請者</td>
						<td >
						<s:if test="kyz==null">
						   <input type="text" name="kyz.userNm" datatype="*"  value="<s:property value='#session.loginUser.name'/>" style="color:blue" readonly/>
						   <input type="hidden" name="kyz.username" value="<s:property value='#session.loginUser.username'/>"/>
						</s:if>
						<s:else>
						   <input type="text" name="kyz.userNm" datatype="*"  value="<s:property value='kyz.userNm'/>" style="color:blue" readonly/>
						   <input type="hidden" name="kyz.username" value="<s:property value='kyz.username'/>"/>
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
				         <input type="hidden" name="kyz.visaType" id="hidden_kytype" datatype="*"/>
				         <input type="hidden" id="dwr_username" value="<s:property value='#session.loginUser.username'/>"/>					         
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
				           <textarea style="width:100%;height:120px" name="kyz.memoMk"  wrap="off"   tip="申請內容" altercss="gray" class="gray"><s:property value="kyz.memoMk"/></textarea>				                                           				         
				           
				           
				           <input type="hidden" value="<s:property value='kyz.filesYn'/>" name="kyz.filesYn"/>
				           <input type="hidden" value="<s:property value='maxNum'/>" id="maxNum"/>
				        </td>
						
					</tr>													
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
			  
			    <s:iterator value="kyz.kyzExpectmatses" status="x" id="temp">
			    <tr class="bluecss">
			     <td><input type="hidden" name="cbox"/></td>			           			          			          			            			          	     
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].itemNm" value="<s:property value='itemNm'/>" /></td>			    
			     <td><input type="text" name="kyz.kyzExpectmatses[${x.index}].id.itemNo" value="<s:property value='id.itemNo'/>" readonly style="color:blue" /></td>			    			     			     
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].matNo" value="<s:property value='matNo'/>" /></td>			     
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].qtyExpect" value="<s:property value='qtyExpect'/>" datatype="my0-8"  id="qtyExpect_${x.index}"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].qtyOk" value="<s:property value='%{formatDouble(qtyOk)}'/>" datatype="my0-8"  id="qtyOk_${x.index}"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].personNo" value="<s:property value='%{formatDouble(personNo)}'/>" datatype="n0-8"  id="personNo_${x.index}"/></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].qtyPair" value="<s:property value='qtyPair'/>"   /></td>
			     <td ><input type="text" name="kyz.kyzExpectmatses[${x.index}].moneyType" value="<s:property value='moneyType'/>"  /></td>
			      <td >
			      <input type="text" name="kyz.kyzExpectmatses[${x.index}].memoMk" value="<s:property value='memoMk'/>" />			      
			      <input type="hidden" name="kyz.kyzExpectmatses[${x.index}].id.kyzExpectmatm.id.factNo" value="<s:property value='id.kyzExpectmatm.id.factNo'/>" />
			      <input type="hidden" name="kyz.kyzExpectmatses[${x.index}].id.kyzExpectmatm.id.billNo" value="<s:property value='id.kyzExpectmatm.id.billNo'/>" />
			      <input type="hidden" name="kyz.kyzExpectmatses[${x.index}].factCode" value="<s:property value='factCode'/>" />
			      </td>			      		      
			  </tr>
			    </s:iterator>
			    <input type="hidden" value="<s:property value='kyz.id.factNo'/>" name="factNo"/>
			    <input type="hidden" value="<s:property value='kyz.id.billNo'/>" name="billNo"/>
			    	         			  			 	  			
			</tbody>
			<tfoot><tr>			
			<td colspan="10">			     			  
			     <input type="button" value="添加行" onclick="addRow()" disabled="disabled" id="addbtn" style="color:grey"/>			     
			 </td>    			 					    			    		   		
			</tr>
			</tfoot>					    
		</table >
	
		<s:if test='kyz.filesYn=="1"'>
	       <hr/>
	       <div style="color:blue;">附檔:</div><br/>
	       <div id="fileJson" style="width:850px">
	      <s:iterator value="#session.list_filesexp">	        
	           <a href="/upload/<s:property value='billno'/>/<s:property value="%{toUrl2(filename)}"/>" target="_blank" title="點擊查看">
	                 <s:property value="%{toUrl(filename)}"/>
	           </a>           
	           <a href="javascript:lookJson('${billno}',${id},'<s:property value="%{toUrl(filename)}"/>')">
	              <img src="images/icon/del_file.png" alt="刪除" title="刪除" style="border:0px"/>
	           </a>&nbsp;&nbsp;	        	        	        
	     </s:iterator>
	     </div>		     	     	        	       
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
</body>
</html>
