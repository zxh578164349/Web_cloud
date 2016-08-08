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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Yield_data.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<link rel="stylesheet" href="css/select/prism.css">
<link rel="stylesheet" href="css/select/chosen.css">
</head>


<body >

	<form action="kyzmat_add" method="post" id="form">
	<h2>物料資料</h2>	 
	  <s:if test="mat!=null">
			<input type="hidden" name="mat.updateMan" value="<s:property value='#attr.loginUser.username'/>"/>
		    <input type="hidden" name="mat.dateUpdate" value="<%=str_date%>"/>	 
	  </s:if>	
		<table class="table table-condensed"
			id="table1" >         
				 <tr>  						
					<td class="td_show_title">廠別</td>
					 <s:if test="mat==null">
					       <s:if test="#session.factNo=='tw'">					
						   <td class="td_input">
						   <select style="color:blue;" 
							name="mat.factNo" datatype="*" >							
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select>						   
						</td>
					</s:if>
					<s:else>
					    <td class="td_input">
					      <select name="mat.factNo" datatype="*" style="color:blue">
					        <option value="">請選擇廠別</option>
					        <option value="${factNo}">${factNo}</option>
					      </select>
						</td>					    
					</s:else>
				</s:if>
				<s:else>
				   <td class="td_input">
				      <input type="text" name="mat.factNo" value="<s:property value='mat.factNo'/>" style="color:blue" readonly/>
				   </td>
				</s:else>
				<s:if test="mat==null">				  
					<td class="td_show_title">大中小分類</td>
					<td class="td_input">
					  <select name="mat.typeBno" datatype="*" id="bNo" onclick="findallMN(this.value), makeMatNo()">
					     <option value="">大分類選擇</option>
					  </select><br/>
					  <select name="mat.typeMno" datatype="*" id="mNo" onclick="findallSN(this.value), makeMatNo()">
					         <option value="">中分類選擇</option>
					  </select><br/>
					  <select name="mat.typeSno" datatype="*" id="sNo" onclick=" makeMatNo()">
					         <option value="">小分類選擇</option>
					  </select>					
					</td>						  															 						
				
				</s:if>
				<s:else>				    
					<td class="td_show_title">大中小分類</td>
					<td class="td_input">					 
					  <input type="text" name="mat.typeBno" value="<s:property value='mat.typeBno'/>" style="color:blue" readonly/><br/>
					  <input type="text" name="mat.typeMno" value="<s:property value='mat.typeMno'/>" style="color:blue" readonly/><br/>
					  <input type="text" name="mat.typeSno" value="<s:property value='mat.typeSno'/>" style="color:blue" readonly/>			
					</td>						  															 										
				</s:else>											
					 <td class="td_show_title">物料編號</td>
						<td class="td_input">
						 <input type="text" name="mat.matNo" id="matNo" value="<s:property value='mat.matNo'/>" style="color:blue" readonly/>					
						</td>   
						
				</tr>
																						　　
				 <tr>  						
					   
						<td class="td_show_title" >物料中文名稱</td>					   
						<td class="td_input">
						<input type="text" name="mat.matCname" datatype="*1-100" value="<s:property value='mat.matCname'/>"/>																	
						</td>
					    <td class="td_show_title">物料英文名稱</td>
					    <td class="td_input"><input type="text" name="mat.matEname"  value="<s:property value='mat.matEname'/>" datatype="s0-50"/></td>
						<td class="td_show_title">會計科目</td>
						<td class="td_input">
						  <%-- <select name="mat.acctNo" id="kyzacctNo">
						     <s:if test="mat!=null">
						        <option value="<s:property value='mat.acctNo'/>">(<s:property value='mat.acctNo'/>)</option>
						     </s:if>
						     <option value="">請選擇</option>						     
						  </select> --%>
						  <input type="text" name="mat.acctNo" value="<s:property value='mat.acctNo'/>" datatype="s0-12"/>					
						</td>																						    					
				</tr>
				 <tr>  
						<td class="td_show_title">資材分類</td>
						<td class="td_input">						 
						  <input type="text" name="mat.matType" value="<s:property value='mat.matType'/>" datatype="*0-12"/>					
						</td>											
					    <td class="td_show_title" >物料規格</td>					    
						<td class="td_input">
						<input type="text" name="mat.matSize" datatype="*0-30" value="<s:property value='mat.matSize'/>"/>																	
						</td>
						<td class="td_show_title">物料顏色</td>
					    <td class="td_input"><input type="text" name="mat.color"  value="<s:property value='mat.color'/>" datatype="*0-12"/></td>
				</tr>
				 <tr>  
						<td class="td_show_title">使用期限</td>
						<td class="td_input">
						  <input type="text" name="mat.dateUse"  value="<s:property value='mat.dateUse'/>" datatype="my0-10"/>					
						</td>											
					    <td class="td_show_title" >安全庫存</td>					    
						<td class="td_input">
						<input type="text" name="mat.saftQty"  value="<s:property value='mat.saftQty'/>" datatype="my0-10"/>																	
						</td>
						<td class="td_show_title">倉儲代號</td>
					    <td class="td_input"><input type="text" name="mat.stockNo"  value="<s:property value='mat.stockNo'/>" datatype="*0-12"/></td>
				</tr>
				<tr>  
						<td class="td_show_title">中文單位</td>
						<td class="td_input">
						  <input type="text" name="mat.cunit" datatype="*0-10" value="<s:property value='mat.cunit'/>"/>						
						</td>											
					    <td class="td_show_title" >英文單位</td>					    
						<td class="td_input">
						<input type="text" name="mat.eunit" datatype="*0-10" value="<s:property value='mat.eunit'/>"/>																	
						</td>
						<td class="td_show_title">幣別</td>
					    <td class="td_input">
					     <select name="mat.punit">
						     <option value="">請選擇</option>
						     <s:if test='mat.punit=="1"'>
						       <option value="1" selected>RMB</option>
						     </s:if>
						     <s:else>
						        <option value="1">RMB</option>
						     </s:else>
						     <s:if test='mat.punit=="2"'>
						       <option value="2" selected>NTD</option>
						     </s:if>
						     <s:else>
						        <option value="2">NTD</option>
						     </s:else>
						     <s:if test='mat.punit=="3"'>
						        <option value="3" selected>HKD</option>
						     </s:if>
						     <s:else>
						        <option value="3">HKD</option>
						     </s:else>
						     <s:if test='mat.punit=="4"'>
						        <option value="4" selected>USD</option>
						     </s:if>
						     <s:else>
						        <option value="4">USD</option>
						     </s:else>						     
						  </select>	
					    </td>
				</tr>
				<tr>
					<td class="td_show_title">商品代號</td>
					<td class="td_input"><input type="text" name="mat.smatNo" datatype="*0-16" value="<s:property value='mat.smatNo'/>"/></td>						  					
					<td class="td_show_title">商品名稱</td>
					<td class="td_input"><input type="text" name="mat.smatName"  value="<s:property value='mat.smatName'/>" datatype="*0-60"/></td>
					<td class="td_show_title">商品單重</td>
					<td class="td_input"><input type="text" name="mat.smatWeit"  value="<s:property value='mat.smatWeit'/>" datatype="my0-10"/></td>
										 						
				</tr>
				<tr>
					<td class="td_show_title">領用換算率</td>
					<td class="td_input"><input type="text" name="mat.clRate" datatype="my0-10" value="<s:property value='mat.clRate'/>"/></td>						  					
					<td class="td_show_title">正式料號</td>
					<td class="td_input"><input type="text" name="mat.formalMk"  value="<s:property value='mat.formalMk'/>" datatype="*0-12"/></td>
					<td class="td_show_title">料號來源</td>
					<td class="td_input"><input type="text" name="mat.fromMk"  value="<s:property value='mat.fromMk'/>" datatype="*0-12"/></td>					 						
				</tr>
				<tr>
					<td class="td_show_title">關務帳</td>
					<td class="td_input"><input type="text" name="mat.gwMk" datatype="*0-12" value="<s:property value='mat.gwMk'/>" /></td>						  					
					<td class="td_show_title">重量單位</td>
					<td class="td_input"><input type="text" name="mat.unitWeit"  value="<s:property value='mat.unitWeit'/>" datatype="*0-12"/></td>
					<td class="td_show_title">海關損耗率</td>
					<td class="td_input"><input type="text" name="mat.rateWaste"  value="<s:property value='mat.rateWaste'/>" datatype="my0-10"/></td>					 						
				</tr>
				
				
				<tr>
				    <td class="td_show_title">領用單位</td>
					<td class="td_input"><input type="text" name="mat.lunit"  value="<s:property value='mat.lunit'/>" datatype="*0-40"/></td>
					<td class="td_show_title">采購單價</td>
					<td class="td_input"><input type="text" name="mat.purPrice"  value="<s:property value='mat.purPrice'/>" datatype="my0-10"/></td>
					<td class="td_show_title" >采購政策</td>					   
					<td class="td_input">
						<input type="text" name="mat.policy" datatype="s0-12" value="<s:property value='mat.policy'/>"/>																	
					</td>
				</tr>											
		</table>
		<!-- 隱藏元素 -->
		<s:if test="mat==null">
		  <input type="hidden" name="mat.builder" value="<s:property value='#attr.loginUser.username'/>"/>
		  <input type="hidden" name="mat.dateB" value="<%=str_date%>"/>
		</s:if>
		<s:else>
		  <input type="hidden" name="mat.builder" value="<s:property value='mat.builder'/>"/>
		  <input type="hidden" name="mat.dateB" value="<s:property value='mat.dateB'/>"/>
		</s:else>

		<input type="hidden" name="mat.yyMk" value="<s:property value='mat.yyMk'/>"/>
		<input type="hidden" name="mat.itemMat" value="<s:property value='mat.itemMat'/>"/>
		<input type="hidden" name="mat.smatUnit" value="<s:property value='mat.smatUnit'/>"/>
		<input type="hidden" name="mat.smatRate" value="<s:property value='mat.smatRate'/>"/>
		<input type="hidden" name="mat.gwTypemk" value="<s:property value='mat.gwTypemk'/>"/>
		<input type="hidden" name="mat.areaMk1" value="<s:property value='mat.areaMk1'/>"/>
		<input type="hidden" name="mat.areaMk2" value="<s:property value='mat.areaMk2'/>"/>
		<input type="hidden" name="mat.areaMk3" value="<s:property value='mat.areaMk3'/>"/>
		<input type="hidden" name="mat.useMk" value="<s:property value='mat.useMk'/>"/>
		<input type="hidden" name="mat.dateStop" value="<s:property value='mat.dateStop'/>"/>
		<input type="hidden" name="mat.costMk" value="<s:property value='mat.costMk'/>"/>
		<input type="hidden" name="mat.matNew" value="<s:property value='mat.matNew'/>"/>
		<input type="hidden" name="mat.matMk" value="<s:property value='mat.matMk'/>"/>
		<input type="hidden" name="mat.ASize" value="<s:property value='mat.ASize'/>"/>
		<input type="hidden" name="mat.BSize" value="<s:property value='mat.BSize'/>"/>
		<input type="hidden" name="mat.HSize" value="<s:property value='mat.HSize'/>"/>
		<input type="hidden" name="mat.weit" value="<s:property value='mat.weit'/>"/>
		<input type="hidden" name="mat.bsmatNo" value="<s:property value='mat.bsmatNo'/>"/>
		<input type="hidden" name="mat.bsmatName" value="<s:property value='mat.bsmatName'/>"/>
		<input type="hidden" name="mat.matType2" value="<s:property value='mat.matType2'/>"/>
		<input type="hidden" name="mat.compNo" value="<s:property value='mat.compNo'/>"/>
		<input type="hidden" name="mat.compNo1" value="<s:property value='mat.compNo1'/>"/>
		<input type="hidden" name="mat.gxMk" value="<s:property value='mat.gxMk'/>"/>
		<input type="hidden" name="mat.zbMk" value="<s:property value='mat.zbMk'/>"/>
		<input type="hidden" name="mat.factMk" value="<s:property value='mat.factMk'/>"/>
		<input type="hidden" name="mat.dateBefroe" value="<s:property value='mat.dateBefroe'/>"/>
		<input type="hidden" name="mat.dateMax" value="<s:property value='mat.dateMax'/>"/>
		<input type="hidden" name="mat.cbzbMk" value="<s:property value='mat.cbzbMk'/>"/>
		<input type="hidden" name="mat.matType3" value="<s:property value='mat.matType3'/>"/>				
		<center>
			<input type="button" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>		
		</center>
	</form>

<script type="text/javascript">
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			ajaxPost:true,
			callback : function(data) {
				if(data=="0"){
					layer.msg("操作成功",3,1);
					loadUrl("kyzmat_findPageBean");
				}else{
					layer.msg("操作失敗",3,3);
				}
			},
			datatype : {
				"my0-10" : /^-?\d{0,10}(\.[0-9]{1,2})?$/,
				"my1-10" : /^-?[1-9]{1}\d{0,9}(\.[0-9]{1,2})?$/

			}
		});
		demo.tipmsg.w["my0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-7"] = "只能數字且不超過7位數,可保留一位以內小數";

	});
		
	function makeMatNo(){
	var bNo=document.getElementById("bNo").value;
	var mNo=document.getElementById("mNo").value;
	var sNo=document.getElementById("sNo").value;
	if(bNo!=""&&mNo!=""&&sNo!=""){
	   kyzmatjs.makeMatNo(bNo,mNo,sNo,function(x){	      
	         dwr.util.setValue("matNo",x);	      
	  }); 
	}	
	  
	}
	
	function findallBN(){
	      kyzscmjs.findBN(function(x){
	          dwr.util.addOptions("bNo",x);
	      });                             
	}
	function findallMN(bNo){
	     document.getElementById("mNo").length=1;
	     kyzscmjs.findMN(bNo,function(x){
	     if(bNo!=""){
	        dwr.util.addOptions("mNo",x);
	     }	         
	     });
	}
	function findallSN(mNo){
	     document.getElementById("sNo").length=1;
	     kyzscmjs.findSN(mNo,function(x){
	     if(mNo!=""){
	        dwr.util.addOptions("sNo",x);
	     }	         
	     });
	}
	function findAllAcct(){
	  kyzacctjs.findAll(function(x){
	     dwr.util.addOptions("kyzacctNo",x,"acctNo","acctName");
	  });
	}

/*禁止空格輸入*/
function noNull(){            
            var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            }  
        }	
	function back(){
		loadUrl("/Login/kyzmat_findPageBean3?backIndex=1");
	}
</script>
<script type='text/javascript' src='/Login/dwr/interface/kyzscmjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzmatjs.js'></script>
<script type="text/javascript">
  jq(function(){
	  //findAllAcct();
	   findallBN();
	   noNull(); 
  });
</script>	

</body>

</html>
