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
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyMMdd");
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
				loadi=layer.load("正在處理,請稍等...");
			}									
		});
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";
		demo.tipmsg.w["my0-12"]="只能數字且不超過12位數,可保留四位以內小數";
	});
			
	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});
		
	}
				
function makeBillNo() {       
		var factno = jq("#dwrFactNo").val();
		var createdate=jq("#createdate").val();
		var billnos = document.getElementById("webremit_billno");
		if (factno != "" && createdate != "") {
			jq.ajax({
				type:"POST",
				dataType:"json",
				data:"factNo="+factno+"&yymm="+createdate,
				url:"webremit_makeBillNo",
				success:function(data){
					jq("#webremit_billno").val(data);
					jq("#webremits_billno").val(data);
				},
				error:function(error){
					alert(error.responseText);
				}
			});
			document.getElementById("addbtn").disabled="";
			document.getElementById("addbtn").style.color="black";				  		
		}
		
	}
	
var j=0;
	function addRow(){
        var billno=document.getElementById("webremit_billno").value;      
        //设置列内容和属性
        var cboxlist=document.getElementsByName("cbox");
        if(cboxlist.length>30){
           alert("對不起,不能超過10條記錄!");
        }else{
    		j++;
     	    //添加一行
             var newTr = webremits_body.insertRow();
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
        newTd00.innerHTML='<input type="checkbox" name="cbox"/>';     
        newTd0.innerHTML = '<input type="text" name="webremit.webremittancelistses['+j+'].currency" value="" size="15"/>'; 
        newTd1.innerHTML = '<input type="text" name="webremit.webremittancelistses['+j+'].Manufacturers" value="" size="15"/>';             
        newTd2.innerHTML='<input type="text" name="webremit.webremittancelistses['+j+'].toBank" value="" size="15"/>';
        newTd3.innerHTML='<input type="text" name="webremit.webremittancelistses['+j+'].toAccount" value="" size="15"/>';
        newTd4.innerHTML='<input type="text" name="webremit.webremittancelistses['+j+'].payment" value="" size="15"/>';
        newTd5.innerHTML='<input type="text" name="webremit.webremittancelistses['+j+'].cost" value="" size="15"/>';
        newTd6.innerHTML='<input type="text" name="webremit.webremittancelistses['+j+'].acAmount" value="" size="15"/>';    
        newTd7.innerHTML='<input type="text" name="webremit.webremittancelistses['+j+'].remark" value="" size="15"/>'+   
        '<input type="text" name="webremit.webremittancelistses['+j+'].id.webremittancelist.billNo" value="'+billno+'"/>'+
        '<input type="text" name="webremit.webremittancelistses['+j+'].id.itemNo" value="'+j+'"/>';     
        }
        
	}
						
	function delRow(){
	   var cboxlist=document.getElementsByName("cbox");
	   for(var k=0;k<cboxlist.length;k++){
	     if(cboxlist[k].checked==true&&k>0){
	        webremits_body.deleteRow(k+1);
	        k=k-1;
	     }
	   }	   
	}
	
	function getFactCode(){
	    document.getElementById("dwrFactArea").value=document.getElementById("webremits_factcode").value;
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
  
  function checkType(type){
     dwrFactNo=document.getElementById("dwrFactNo").value;
     dwremail=document.getElementById("dwr_email").value.toLowerCase(); //登錄人的email要轉化爲小寫,因爲申請人email已全部轉化爲小寫（20151022）  
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
function lookJson(billNo,id,filename){
   jq.ajax({
      type:"get",
      dataType:"json",
      url:"webremitfile_findKyzFileJson",
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

function selall(){
	var allcb=jq("#allbox");
	var list=jq("[name='cbox']")
	if(allcb.prop("checked")){
		for(var i=0;i<list.length;i++){
			list[i].checked=true;
		}
	}else{
		for(var i=0;i<list.length;i++){
			list[i].checked=false;
		}
	}
}

function back(){
	
	loadUrl("/Login/webremit_findPageBean3?backIndex=1");
}
function gook(){
	  layer.msg("操作成功",3,1);
	  loadUrl("webremit_findPageBean");
}
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webtypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<script type="text/javascript">
jq(function(){
	if(jq("[name='saveOrUpdate']").val()=="save"){
		getKyType();makeBillNo();
	}	
});
</script>

</head>
<body >  
    <form action="webremit_add"  method="post" id="form"  enctype="multipart/form-data" target="frameFile">
        <h2>加伟鞋材对公费用汇款清单</h2>
		<table class="table table-condensed" >		    	
			<tbody id="tb_list_info2">				   
				    <s:if test="webremit==null">				   											
					<tr>
					    <s:if test="#session.factNo!='tw'">
						<td class="tdcolor">廠別</td>
						<td ><input type="text" style="color:blue"
							name="webremit.id.factNo" value="${factNo}" readonly id="dwrFactNo" />							
						</td>
						
						<td class="tdcolor">廠別狀態</td>
						<td><select name="webremit.factCode"
							 id="dwrFactArea"  style="color:blue">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select></td>
						</s:if>
						<s:else>
						  <td class="tdcolor">廠別</td>
						<td ><select style="color:blue"
							name="webremit.id.factNo" datatype="*" id="dwrFactNo"
							onchange="getFactArea(this.value),makeBillNo(),getKyType2(this.value)">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						
						<td class="tdcolor">廠別狀態</td>
						<td><select name="webremit.factCode"
							 id="dwrFactArea" >
								<option value="">請選擇廠別狀態</option>
						</select></td>
						</s:else>
						<td class="tdcolor">日期</td>
				        <td >
				        <input type="text" name="webremit.yymm" datatype="*" onclick="WdatePicker({dateFmt:'yyyyMM'})" class="Wdate"/> 
				        <input type="hidden" name="saveOrUpdate" value="save"/><!--判斷變量 -->				        			        
				        <input type="hidden" name="webremit.createdate"  value="<%=str_date%>" id="createdate"/>
				        
				        </td>												
					</tr>																														    
				   </s:if>
				   <s:else>
				   <tr>
				      <td class="tdcolor">廠別</td>				      
				      <td>
				      <input type="text" name="webremit.id.factNo" value="<s:property value='webremit.id.factNo'/>" readonly style="color:blue" />				      
				      </td>
				     
				      <td class="tdcolor">廠別狀態</td>
				      <td><input type="text" name="webremit.factCode" value="<s:property value='webremit.factCode'/>" readonly style="color:blue" /></td>
				     
				      <td class="tdcolor">日期</td>
				      <td>
				       <input type="webremit.yymm" value="<s:property value='webremit.yymm'/>"/>
				       <input type="hidden" name="saveOrUpdate" value="update"/><!--判斷變量 -->			      
				       <input type="hidden" name="webremit.createdate" value="<s:property value='webremit.createdate'/>"/>
				       <input type="hidden" name="webremit.udDate" value="<%=str_date%>" id="createdate"/>				       
				       <input type="hidden" name="webremit.udUsername" value="<s:property value='#session.loginUser.username'/>"/>
				      </td>	
				   </tr>   			     				     
				   </s:else>
				   
				    <tr>			    				    
				      <td class="tdcolor">申請單號</td>				        
				      <td>
				        <s:if test="webremit==null">	
				          		<input type="text" name="webremit.billNo" value="自動生成" readonly style="color:blue" id="webremit_billno" datatype="*"/>	        
				        </s:if>
				        <s:else>				               
				               <input type="text" name="webremit.billNo" value="<s:property value='webremit.billNo'/>" readonly style="color:blue"  datatype="*"/>
				        </s:else>				      
				      </td>
				        	        
				       <td class="tdcolor">申請者</td>
						<td >
						<s:if test="webremit==null">					   
						   <input type="text" name="webremit.username" datatype="*"  value="<s:property value='#session.loginUser.name'/>" style="color:blue" readonly/>
						</s:if>
						<s:else>
						   <input type="text" name="webremit.username" datatype="*"  value="<s:property value='webremit.username'/>" style="color:blue" readonly/>
						</s:else>
						</td>
						
						<td class="tdcolor">類別</td>
				        <td >
				         <s:if test="webremit==null">
				            <select  id="dwr_kytype" onchange="checkType(this.value)" datatype="*" name="webremit.webtype.id.typeNo" style="color:blue">
				            <option value="">請選擇</option>
				         </select>
				         <input type="hidden" id="dwr_email" value="<s:property value='#session.loginUser.email'/>"/>
				         <input type="hidden" name="webremit.visaType" id="hidden_kytype" datatype="*"/>
				         <input type="hidden" id="dwr_username" value="<s:property value='#session.loginUser.username'/>"/>					         
				         </s:if>
				         <s:else>
				            <input type="text" value="<s:property value='webremit.visaType'/>" name="webremit.visaType" style="color:blue"  readonly/>\\\
				            <input type="text" value="<s:property value='webremit.webtype.id.typeNo'/>" name="webremit.webtype.id.typeNo" style="color:blue"  readonly/>
				         </s:else>				         				        				        
				      </td>						
										        
				    </tr>
				    <tr>
				      <td class="tdcolor">匯款帳號</td>
				      <td>
				      <input type="text" value="<s:property value='webremit.fromAccount'/>" name="webremit.fromAccount"/>
				      </td>				     
				      <td class="tdcolor">銀行名稱</td>
				      <td colspan="2">
				      <input type="text" value="<s:property value='webremit.fromBank'/>" name="webremit.fromBank"/>
				      </td>
				      
				      <!--<td >
				      <div style="width:300px" id="divfile">
				      <input type="file" name="files" style="width:150px"/><a href="javascript:addFile()">添加多個</a>
				      </div>
				      </td>	
				      <td class="tdcolor">是否顯示第一頁</td>		       
				       <s:if test="webremit==null">
						   <td >是<input type="radio" name="webremit.firstPage" value="0" checked/>&nbsp;&nbsp;否<input type="radio" name="webremit.firstPage" value="1"/></td>
						</s:if>
						<s:else>
						   <s:if test="webremit.firstPage==0">
						       <td >是<input type="radio" name="webremit.firstPage" value="<s:property value='webremit.firstPage'/>" checked/>&nbsp;&nbsp;否<input type="radio" name="webremit.firstPage" value="1"/></td>
						   </s:if>
						   <s:if test="webremit.firstPage==1">
						       <td >是<input type="radio" name="webremit.firstPage" value="0" />&nbsp;&nbsp;否<input type="radio" name="webremit.firstPage" value="<s:property value='webremit.firstPage'/>" checked/></td>
						   </s:if>
						    <s:if test="webremit.firstPage==null">
						       <td >是<input type="radio" name="webremit.firstPage" value="0" checked/>&nbsp;&nbsp;否<input type="radio" name="webremit.firstPage" value="1" /></td>
						   </s:if>
						</s:else>-->		      
				    </tr>																							
			</tbody>
			</table>	
			<table class="table table-condensed">								 			
			<tbody id="webremits_body" >
			  <tr>
			     <td><input type="checkbox" id="allbox" onclick="selall()"/></td>
			     <td class="tdcolor">貨幣</td>
			     <td class="tdcolor">廠商名稱</td>
			     <td class="tdcolor">收款銀行</td>
			     <td class="tdcolor">授權委託帳號</td>
			     <td class="tdcolor">貨款金額</td>
			     <td class="tdcolor">匯費</td>
			     <td class="tdcolor">實匯金額</td>		     
			     <td class="tdcolor">備註</td>
			 </tr>	
			 <s:if test="webremit==null">
			    <tr>
			     <td><input type="checkbox" name="cbox"/></td>	   
			     <td ><input type="text" name="webremit.webremittancelistses[0].currency"  size="15"/></td>			     
			     <td ><input type="text" name="webremit.webremittancelistses[0].Manufacturers"  datatype="my0-12" size="15" /></td>
			     <td ><input type="text" name="webremit.webremittancelistses[0].toBank"  datatype="my0-8" size="15" /></td>
			     <td ><input type="text" name="webremit.webremittancelistses[0].toAccount"  datatype="my0-8" size="15" /></td>
			     <td ><input type="text" name="webremit.webremittancelistses[0].payment"   size="15"/></td>
			     <td ><input type="text" name="webremit.webremittancelistses[0].cost"   size="15"/></td>
			     <td ><input type="text" name="webremit.webremittancelistses[0].acAmount"   size="15"/></td>
			     <td>
			     <input type="text" name="webremit.webremittancelistses[0].remark"   size="15"/>
			     <input type="text" name="webremit.webremittancelistses[0].id.webremittancelist.billNo" value="" id="webremits_billno"/>
			     <input type="text" name="webremit.webremittancelistses[0].id.itemNo" value="0"/>
			     </td>			      		      		      
			  </tr>		
			 </s:if>
			 <s:else>			  
			    <s:iterator value="webremit.webremittancelistses" status="x" id="temp">
			     <tr class="bluecss">			        		     			    			    			     		    			     			     
			     <td ><input type="text" name="webremit.webremittancelistses[${x.index}].currency" value="<s:property value='currency'/>" size="15"/></td>			     
			     <td ><input type="text" name="webremit.webremittancelistses[${x.index}].Manufacturers" value="<s:property value='Manufacturers'/>" datatype="my0-8" size="15" id="qtyExpect_${x.index}"/></td>
			     <td ><input type="text" name="webremit.webremittancelistses[${x.index}].toBank" value="<s:property value='toBank'/>" datatype="my0-8" size="15" id="qtyOk_${x.index}"/></td>
			     <td ><input type="text" name="webremit.webremittancelistses[${x.index}].toAccount" value="<s:property value='toAccount'/>" datatype="my0-8" size="15" id="personNo_${x.index}"/></td>
			     <td ><input type="text" name="webremit.webremittancelistses[${x.index}].payment" value="<s:property value='payment'/>"  id="qtyPair" size="15"/></td>
			     <td ><input type="text" name="webremit.webremittancelistses[${x.index}].cost" value="<s:property value='cost'/>"  size="15"/></td>
			      <td ><input type="text" name="webremit.webremittancelistses[${x.index}].acAmount" value="<s:property value='acAmount'/>" size="15"/></td>
			      <td>
			      <input type="text" name="webremit.webremittancelistses[${x.index}].remark" value="<s:property value='remark'/>" size="15"/>
			      <input type="hidden" name="webremit.webremittancelistses[${x.index}].id.webremittancelist.billNo" value="<s:property value='id.webremittancelist.billNo'/>" id="webremits_billno"/>
			      <input type="hidden" name="webremit.webremittancelistses[0].id.itemNo" value="<s:property value='id.webremittancelist.itemNo'/>"/>	
			      <input type="hidden" name="cbox"/>
			      </td>			      		      
			     </tr>
			    </s:iterator>			   			    
			 </s:else>		         			  
			 	  			
			</tbody>
			<tfoot><tr>
			<s:if test="webremit==null">
			<td colspan="10">			     			  
			     <input type="button" value="添加行" onclick="addRow()" disabled="disabled" id="addbtn" style="color:grey"/>
			     <input type="button" value="刪除行" onclick="delRow()"  id="delbtn"/>
			 </td>    
			  </s:if>					    			    		   		
			</tr>
			</tfoot>					    
		</table >		  
		<!--  <s:if test='webremit.filesYn=="1"'>
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
	   </s:if>-->
	   <hr/>	  
			  <center style="width:850px;margin-left:50px">			    
				<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
					type="reset" id="reset" value="重置" class="btn btn-primary"/>
				<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>						
			</center>					
	</form>
	<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>
</body>
</html>
