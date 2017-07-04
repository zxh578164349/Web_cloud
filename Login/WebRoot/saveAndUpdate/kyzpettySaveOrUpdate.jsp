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

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Yield_data.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" href="css/select/prism.css">
 <link rel="stylesheet" href="css/select/chosen.css">
</head>

<body >

	<form action="kyzpetty_add" method="post" id="form">
	  <input type="hidden" value="<s:property value='#session.factNo'/>" id="login_factno"/>
		<table class="table table-condensed"
			id="table1" > 
           <s:if test="kyzpetty==null">							
					<tr>					  
					<td class="td_show_title" >廠別</td>
					<s:if test="#session.factNo=='tw'">					
						<td class="td_input">
						   <select style="color:blue;" 
							name="kyzpetty.id.factNo" datatype="*" id="dwrFactNo"
							onchange="makeItemNo(this.value),maikBillNo(this.value)">
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
					      <select name="kyzpetty.id.factNo" datatype="*" style="color:blue" onchange="makeItemNo(this.value),maikBillNo(this.value)" id="dwrFactNo">
					        <option value="">請選擇廠別</option>
					        <option value="${factNo}">${factNo}</option>
					      </select>
						</td>					    
					</s:else>
					<td class="td_show_title">單號</td>
					<td><input type="text" name="kyzpetty.id.billNo" style="color:blue" id="dwrBillNo"/></td>
				　</tr>	
				</s:if>
				<s:if test="kyzpetty!=null">
				  <tr>
				   <td class="td_show_title" >廠別</td>
				   <td class="td_input"><input type="text" value="<s:property value='kyzpetty.id.factNo'/>" name="kyzpetty.id.factNo" style="color:blue" readonly/></td>
				   <td class="td_show_title">單號</td>
				   <td class="td_input"><input type="text" name="kyzpetty.id.billNo" value="<s:property value='kyzpetty.id.billNo'/>" style="color:blue" readonly/></td>
				  </tr>
				</s:if>	
				    <tr>  
						<td class="td_show_title">作帳幣別</td>
						<td class="td_input">
						  <select name="kyzpetty.currencyNo" datatype="*">
						     <option value="">請選擇</option>
						     <s:if test='kyzpetty.currencyNo=="01"'>
						       <option value="01" selected>RMB</option>
						     </s:if>
						     <s:else>
						        <option value="01">RMB</option>
						     </s:else>
						     <s:if test='kyzpetty.currencyNo=="02"'>
						       <option value="02" selected>NTD</option>
						     </s:if>
						     <s:else>
						        <option value="02">NTD</option>
						     </s:else>
						     <s:if test='kyzpetty.currencyNo=="03"'>
						        <option value="03" selected>HKD</option>
						     </s:if>
						     <s:else>
						        <option value="03">HKD</option>
						     </s:else>
						     <s:if test='kyzpetty.currencyNo=="04"'>
						        <option value="04" selected>USD</option>
						     </s:if>
						     <s:else>
						        <option value="04">USD</option>
						     </s:else>
						  </select>						
						</td>											
					    <td class="td_show_title" >支出日期</td>
						<td class="td_input">
						<%-- <input type="text" name="kyzpetty.datePaybill" value="<s:property value='kyzpetty.datePaybill'/>" datatype="*" onclick="WdatePicker()"  class="Wdate" id="datepaybill"/> --%>
						<s:if test="kyzpetty==null">
						   <input type="text" name="kyzpetty.datePaybill" value="<%=str_date%>"  id="datepaybill" style="color:blue" readonly/>
						</s:if>
						<s:else>
						   <input type="text" name="kyzpetty.datePaybill" value="<s:property value='kyzpetty.datePaybill'/>" style="color:blue" onclick="WdatePicker()"  class="Wdate"/>
						</s:else>
						
						</td>
					</tr>
																			
			　　<tr>
					<td class="td_show_title">流水號</td>
					<s:if test="kyzpetty==null">
					   <td class="td_input"><input type="text" name="kyzpetty.itemNo" datatype="s0-10"  style="color:blue" readonly id="itemno"/></td>
					</s:if>
					<s:else>
					   <td class="td_input"><input type="text" name="kyzpetty.itemNo" datatype="s0-10" value="<s:property value='kyzpetty.itemNo'/>" style="color:blue" readonly /></td>
					</s:else>
											  					
					<td class="td_show_title">結帳日期</td>
					<td class="td_input"><input type="text" name="kyzpetty.dateOver"  value="<s:property value='kyzpetty.dateOver'/>" datatype="*0-40" onclick="WdatePicker()" class="Wdate"/>	</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">歸屬部門</td>
					<td class="td_input"><input type="text" name="kyzpetty.deptNo" datatype="s0-10" value="<s:property value='kyzpetty.deptNo'/>"/></td>						  					
					<td class="td_show_title">歸屬組別</td>
					<td class="td_input">
					  <%-- <select name="kyzpetty.secNo" id="dwrkyzsec" >
					     <s:if test="kyzpetty!=null">
					        <option value="<s:property value='kyzpetty.secNo'/>" selected><s:property value="kyzpetty.secNo"/></option>
					     </s:if>
					     <option value="">請選擇</option>					     
					  </select> --%>
                  <div class="side-by-side clearfix">
					<div>					     					     
						 <select class="chosen-select" style="width:220px;" tabindex="17"  name="kyzpetty.secNo" >
						  <option value="">請選擇</option>
						   <s:iterator value="#attr.list_kyzsec">
						      <s:if test="kyzpetty.secNo==id.secNo">
					              <option value="<s:property value='id.secNo'/>" selected><s:property value="secNm"/></option>
					          </s:if>
					          <s:else>
					              <option value="<s:property value='id.secNo'/>"><s:property value="secNm"/></option>
					          </s:else>
						   </s:iterator>											     					     							
						</select>
					</div>
					</div>
					</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">費用性質</td>
					<td class="td_input">
					<%-- <input type="text" name="kyzpetty.expenseMk" datatype="s0-10" value="<s:property value='kyzpetty.expenseMk'/>"/> --%>
					<s:if test="kyzpetty.expenseMk==1">
					   1.現金<input type="radio" value="1" name="kyzpetty.expenseMk"  checked/>&nbsp;&nbsp;
					</s:if>
					<s:else>
					   1.現金<input type="radio" value="1" name="kyzpetty.expenseMk"  datatype="*"/>&nbsp;&nbsp;
					</s:else>
					<s:if test="kyzpetty.expenseMk==2">
					   2.銀行<input type="radio" value="2" name="kyzpetty.expenseMk" checked/>
					</s:if>
					<s:else>
					   2.銀行<input type="radio" value="2" name="kyzpetty.expenseMk" datatype="*"/>
					</s:else>
					
					</td>						  					
					<td class="td_show_title">借貸注記</td>
					<td class="td_input"><input type="text" name="kyzpetty.dbcdMk"  value="<s:property value='kyzpetty.dbcdMk'/>" datatype="*0-40"/>	</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">會計科目</td>
					<td class="td_input">
					 <%--   <select name="kyzpetty.acctNo" id="dwrkyzacct" >
					      <s:if test="kyzpetty!=null">
					         <option value="<s:property value='kyzpetty.acctNo'/>" selected><s:property value="kyzpetty.acctNo"/></option>
					      </s:if>
					      <option value="">請選擇</option>
					   </select> --%>
					   <select class="chosen-select" style="width:220px;" tabindex="17"  name="kyzpetty.acctNo" >
						  <option value="">請選擇</option>
						   <s:iterator value="#attr.list_kyzacct">
						      <s:if test="kyzpetty.acctNo==acctNo">
					              <option value="<s:property value='acctNo'/>" selected><s:property value="acctName"/></option>
					          </s:if>
					          <s:else>
					              <option value="<s:property value='acctNo'/>"><s:property value="acctName"/></option>
					          </s:else>
						   </s:iterator>											     					     							
						</select>					
					</td>						  					
					<td class="td_show_title">摘要說明</td>
					<td class="td_input"><input type="text" name="kyzpetty.noteMk"  value="<s:property value='kyzpetty.noteMk'/>" datatype="*0-40"/>	</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">費用廠別</td>
					<td class="td_input"><input type="text" name="kyzpetty.factExpense" datatype="s0-10" value="<s:property value='kyzpetty.factExpense'/>"/></td>						  					
					<td class="td_show_title">費用部門</td>
					<td class="td_input"><input type="text" name="kyzpetty.deptExpense"  value="<s:property value='kyzpetty.deptExpense'/>" datatype="*0-40"/>	</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">恁証類別</td>
					<td class="td_input"><input type="text" name="kyzpetty.vouMk" datatype="s0-10" value="<s:property value='kyzpetty.vouMk'/>"/></td>						  					
					<td class="td_show_title">課稅類別</td>
					<td class="td_input"><input type="text" name="kyzpetty.taxMk"  value="<s:property value='kyzpetty.taxMk'/>" datatype="*0-40"/>	</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">恁証日期</td>
					<td class="td_input"><input type="text" name="kyzpetty.dateVou" datatype="s0-10" value="<s:property value='kyzpetty.dateVou'/>" onclick="WdatePicker()" class="Wdate"/></td>						  					
					<td class="td_show_title">管稅帳注記</td>
					<td class="td_input">
					<s:if test="kyzpetty.taxmMk==1">
					  1.稅帳<input type="radio" value="1" name="kyzpetty.taxmMk" checked/>&nbsp;&nbsp;
					</s:if>						
					<s:else>
					   1.稅帳<input type="radio" value="1" name="kyzpetty.taxmMk" datatype="*"/>&nbsp;&nbsp;
					</s:else>
					<s:if test="kyzpetty.taxmMk==2">
					  2.非稅帳<input type="radio" value="2" name="kyzpetty.taxmMk" checked/>
					</s:if>
					<s:else>
					   2.非稅帳<input type="radio" value="2" name="kyzpetty.taxmMk" datatype="*"/>
					</s:else> 
					</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">恁証號碼</td>
					<td class="td_input"><input type="text" name="kyzpetty.vouNo" datatype="s0-10" value="<s:property value='kyzpetty.vouNo'/>"/></td>						  					
					<td class="td_show_title">統一編號</td>
					<td class="td_input"><input type="text" name="kyzpetty.venderCode"  value="<s:property value='kyzpetty.venderCode'/>" datatype="*0-40"/>	</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">支付金額</td>
					<td class="td_input"><input type="text" name="kyzpetty.cashExpense" datatype="s0-10" value="<s:property value='kyzpetty.cashExpense'/>"/></td>						  					
					<td class="td_show_title">支付稅額</td>
					<td class="td_input"><input type="text" name="kyzpetty.taxExpense"  value="<s:property value='kyzpetty.taxExpense'/>" datatype="*0-40"/>	</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">經手人</td>
					<td class="td_input"><input type="text" name="kyzpetty.manHandle" datatype="s0-10" value="<s:property value='kyzpetty.manHandle'/>"/></td>						  					
					<td class="td_show_title">備注1</td>
					<td class="td_input"><input type="text" name="kyzpetty.memoMk1"  value="<s:property value='kyzpetty.memoMk1'/>" datatype="*0-40"/>	</td>					 						
				</tr>
				<tr>
					<td class="td_show_title">備注2</td>
					<td class="td_input"><input type="text" name="kyzpetty.memoMk2" datatype="s0-10" value="<s:property value='kyzpetty.memoMk2'/>"/></td>						  					
					<td class="td_show_title">備注3</td>
					<td class="td_input"><input type="text" name="kyzpetty.memoMk3" datatype="s0-10" value="<s:property value='kyzpetty.memoMk3'/>"/></td>					 						
				</tr>
				<tr>
											  					
					<td class="td_show_title">備注4</td>
					<td class="td_input"><input type="text" name="kyzpetty.memoMk4"  value="<s:property value='kyzpetty.memoMk4'/>" datatype="*0-40"/></td>
					<td class="td_show_title">操作者</td>
					<td class="td_input"><input type="text" name="kyzpetty.userNm" datatype="s0-10" value="<s:property value='kyzpetty.userNm'/>"/></td>						 						
				</tr>
				<tr>									  					
					<td class="td_show_title">建构日期</td>
					<s:if test="kyzpetty==null">
					<td class="td_input"><input type="text" name="kyzpetty.timeCreate"  value="<%=str_date%>" style="color:blue" readonly id="timecreate"/></td>
					</s:if>
					<s:else>
					<td class="td_input"><input type="text" name="kyzpetty.timeCreate"  value="<s:property value='kyzpetty.timeCreate'/>" style="color:blue" readonly/></td>
					</s:else>
					<td class="td_show_title">恁証流水號</td>
					<td class="td_input"><input type="text" name="kyzpetty.itemYm" datatype="s0-10" value="<s:property value='kyzpetty.itemYm'/>"/></td>						 						
				</tr>
				<tr>										  					
					<td class="td_show_title">內部摘要</td>
					<td class="td_input"><input type="text" name="kyzpetty.noteIn"  value="<s:property value='kyzpetty.noteIn'/>" datatype="*0-40"/>	</td>
					<td class="td_show_title">費用類別</td>
					<td class="td_input"><input type="text" name="kyzpetty.expenseType" datatype="s0-10" value="<s:property value='kyzpetty.expenseType'/>"/></td>					 						
				</tr>
				<tr>											  					
					<td class="td_show_title">Time Zd</td>
					<td class="td_input"><input type="text" name="kyzpetty.timeZd"  value="<s:property value='kyzpetty.timeZd'/>" datatype="*0-40"/>	</td>									 						
				</tr>				
		</table>


		<center>
			<input type="button" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;
			<input type="button" id="btn_back" value="返回" onclick="back()" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;
					
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
					loadUrl("kyzpetty_findPageBean");
				}else{
					layer.msg("操作失敗",3,3);
				}
			},
			datatype : {
				"my0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my1-6" : /^[1-9]{1}\d{0,8}(\.[0-9]{1,3})?$/,
				"my0-7" : /^\d{0,7}(\.[0-9]{1})?$/

			}
		});
		demo.tipmsg.w["my0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-7"] = "只能數字且不超過7位數,可保留一位以內小數";

	});
	
	function makeSecno(obj){
	   var factno=document.getElementById("dwrFactNo").value;
	   if(factno!=""){
	      kyzsecjs.makeSecNo(obj,function(x){
	         dwr.util.setValue("secno",x);
	      });
	   }
	}
	
	function checkAcctNo(obj){
	   kyzacctjs.checkAcctNo(obj,function(x){
	          if(x==false){
	             alert("科目代號  "+obj+" 已存在,請重新輸入!");	             	            
	           } 
	   });
	}
	
	
	
	function makeItemNo(factno){
	//var timecreate=document.getElementById("timecreate").value;	
	var datepaybill=document.getElementById("datepaybill").value;	   
	    kyzpettyjs.makeItemNo(factno,datepaybill,function(x){
	        if(factno!=""&&datepaybill!=""){
	           dwr.util.setValue("itemno",x);
	        }	          
	    });
	}
	function maikBillNo(factno){
	//var datepaybills=document.getElementById("timecreate").value;
	var datepaybill=document.getElementById("datepaybill").value;	
	  kyzpettyjs.makeBillNo(factno,datepaybill,function(x){
	      if(factno!=""&&datepaybill!=""){
	         dwr.util.setValue("dwrBillNo",x);
	      }
	  }); 
	}
	
	function getSecNoByFactNo(factno){
	   var bb=jQuery.noConflict();	   
	    bb.post("kyzsec_getSecNoByFactNo",                 /*要進入的action*/
                   {"factNo":factno},
                   function(date){alert(date)});/*要傳遞的數據*/                  
                  
	}
	
function back() {			
	loadUrl("kyzpetty_findPageBean3?backIndex=1");
}
</script>
  <script src="jquery/select/chosen.jquery.js" type="text/javascript"></script>
  <script src="jquery/select/prism.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    var jjqq=jQuery.noConflict();
    for (var selector in config) {
      jjqq(selector).chosen(config[selector]);
    }
  </script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/kyzsecjs.js'></script>
<script type='text/javascript' src='dwr/interface/kyzacctjs.js'></script>
<script type='text/javascript' src='dwr/interface/kyzpettyjs.js'></script>
</body>

</html>
