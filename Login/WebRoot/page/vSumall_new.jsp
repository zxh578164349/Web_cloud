<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'kpiFactAndTW.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">


</head>
  
  <body>
    <br/>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">全年報表</h3>
		</div>
		<div class="panel-body">
			<form action="vwebprolossfact_print_fact" method="post" id='subform1'
				target="_blank">
				<table class="tb_search">
					<tr>
						<td class="td_input"><span><input type="text"
								name="year" class="Wdate"
								onclick="WdatePicker({dateFmt:'yyyy'})" datatype="*" />
						</span> <span> <s:if test="#session.factNo=='tw'">
									<select name="factNo" datatype="*">
										<option value="">請選擇工廠</option>
										<s:iterator value="#session.facts" id="temp">
											<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>
										</s:iterator>
									</select>
								</s:if> <s:else>
									<select name="factNo" datatype="*">
										<option value="<s:property value="#session.factNo"/>">
											<s:property value="#session.factName" />
											(
											<s:property value="#session.factNo" />
											)
										</option>
									</select>

								</s:else> </span> <input type="button" id="btn1" class="btn btn-primary"
							value="確定" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<br/><br/><br/>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">月份報表</h3>
		</div>
		<div class="panel-body">
			<form action="vwebprolossfact_print_month" method="post"
				id="subform2" target="_blank">
				<table class="tb_search">
					<tr>
						<td><span> <input type="text" id="begin" name="yymm"
								datatype="*"
								onclick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'end\',{M:-1})||\'%y-{%M-1}\'}'})"
								class="Wdate"> </span>至 <span> <input type="text"
								id="end" name="yymm2" datatype="*"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'begin\',{M:1})}',maxDate:'%y-%M'})"
								class="Wdate"> </span> <span> <s:if
									test="#session.factNo=='tw'">
									<select name="factNo" datatype="*">
										<option value="">請選擇工廠</option>
										<s:iterator value="#session.facts" id="temp">
											<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>
										</s:iterator>
									</select>
								</s:if> <s:else>
									<select name="factNo" datatype="*">
										<option value="<s:property value="#session.factNo"/>">
											<s:property value="#session.factName" />
											(
											<s:property value="#session.factNo" />
											)
										</option>
									</select>
								</s:else> </span> <input type="button" id="btn2" class="btn btn-primary"
							value="確定" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<br/><br/><br/>
	<s:if test='#session.factNo=="tw"'>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">全廠損益表-台灣</h3>
		</div>
		<div class="panel-body">
			<form action="vwebprolossfact_print_tw" method="post" id="subform3"
				target="_blank">
				<table class="tb_search">
					<tr>
						<td><span><input type="text" name="yymm" datatype="*"
								onclick="WdatePicker()" class="Wdate" id="yymm_tw" />
						</span> <input type="button" id="btn3" class="btn btn-primary" value="確定"
							onclick="print_tw()" /></td>
					</tr>
				</table>
				<hr>
				<table class="table table-striped  table-bordered">
					<tr>
						<td>全選<input type="checkbox" id="all" onclick="checkAll()"
							style="width:18px;height:18px" />
						</td>

						<td><s:iterator value="#session.facts" id="temp" status="x">
								<!-- value=" _${temp[0]}_${temp[1]}" 第一位的是空格，代表factcode,不能省略-->
								<input type="checkbox" value=" _${temp[0]}_${temp[1]}"
									id="fact_no_${x.index}" name="list_factno"
									onclick="clickOne(this,'font_${x.index}')" />
								<font id="font_${x.index}">${temp[1]}(${temp[0]})</font>
							</s:iterator></td>

					</tr>
				</table>
			</form>
		</div>
	</div>
	</s:if>
  
    
  
<script type="text/javascript">
function tips(){
	   window.parent.layer.alert("出現無數據時,請檢查<br/>【產量資料(盤點)】【預計生產】【基本數據導入】是否同時具備數據",0); 
}
   jq(
      function(){
         jq("#subform1").Validform({
             btnSubmit : "#btn1",
             tiptype:3,
             tipSweep:true,
             showAllError:true,
             beforeSubmit:tips
         });
         jq("#subform2").Validform({
             btnSubmit:"#btn2",
             tiptype:3,
             tipSweep:true,
             showAllError:true,
             beforeSubmit:tips
         });
          /*jq("#subform3").Validform({
             btnSubmit:"#btn3",
             tiptype:3,
             tipSweep:true,
             showAllError:true
         })*/
      }  
   )
/**
 全選與反選
*/  	
function checkAll() {
	var allcheckboxs=jq("input[name='list_factno']");
	var the_all= document.getElementById("all");	
		//遍歷所選廠別狀態對應的所有checkbox
		for ( var i = 0; i < allcheckboxs.length; i++) {
			var checkbox = allcheckboxs[i];
			var font = document.getElementById("font_" + i);
			if (checkbox.checked === false && the_all.checked === true) {					
				// 正选  
				checkbox.checked = true;
				font.style.color = "red";

			} else if (checkbox.checked === true && the_all.checked == false) {
				// 反选  
				checkbox.checked = false;
				font.style.color = "";
			}
		}
	}
	
/**
選擇單個,如果一個也選中,則全部退回
*/	
	function clickOne(checkbox_obj,font){	   
	   var cb_factcode=document.getElementById("all");  
	   if(checkbox_obj.checked==true){
	      document.getElementById(font).style.color="red";
	   }else{
	       document.getElementById(font).style.color="";
	       //全部退回
	       //通過樣式 class獲取對應factCode的所有廠別
	       allcheckboxs=jq("input[name='list_factno']");
	       var index=0;
	       for(var i=0;i<allcheckboxs.length;i++){
	           var checkbox=allcheckboxs[i];
	           if(checkbox.checked==false){
	               index++;	              
	           }
	       }
	       if(index==allcheckboxs.length){	           
	           cb_factcode.checked=false;
	       }
	   } 	  
	}
/*function test(factcode_obj){  //jquery获取复选框值    
  var factcode=factcode_obj.value;
  var chk_value =[];    
  jq('input[id='+factcode+'_factno]:checked').each(function(){    
   chk_value.push(jq(this).val());    
  });    
  alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value);    
}*/
function print_tw(){
	if(jq("#yymm_tw").val()==""){
		layer.alert("請選擇日期");
		return false;
	}
	if(jq("[name='list_factno']:checked").length==0){
		layer.alert("請選擇廠別");
		return false;
	}
	tips();
	jq("#subform3").submit();
}
</script>    
  </body>
</html>
