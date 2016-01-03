
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />	
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<script type="text/javascript">
 
//var jq=jQuery.noConflict();
jq(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});

function getAllFact_json(){	
	jq.ajax({
		type:"POST",
		url:"webfact_findAllFact_obj",
		dataType:"json",
		success:function(data){
			var item;
			jq.each(data,function(i,obj){
				item="<input type='checkbox' name='factNos' value='"+obj[0]+"'/>"+obj[1]+"&nbsp;";
				jq("#div_factNos").append(item);
			});
		}	
	});
}
function findComponent(){
   jq.ajax({
      type:"POST",
      url:"webfactOrder_findComponent",
      dataType:"json",
      success:function(data){
        var item;
        jq.each(data,function(i,obj){
          item="<input type='checkbox' name='components' value='"+obj+"'/>"+obj+"&nbsp;";
          jq("#div_component").append(item);
        });
      }
   });
}
function findBrank(){
   jq.ajax({
       type:"POST",
       url:"webfactOrder_findBrank",
       dataType:"json",
       success:function(data){
           var item;
           jq.each(data,function(i,obj){
              item="<input type='checkbox' name='branks' value='"+obj+"'/>"+obj+"&nbsp;";
              jq("#div_brank").append(item);
           });
       }
   });
}
function findCustomer(){
   jq.ajax({
      type:"POST",
      url:"webfactOrder_findCustomer",
      dataType:"json",
      success:function(data){
         var item;
         jq.each(data,function(i,obj){
            item="<input type='checkbox' name='customers' value='"+obj+"'/>"+obj+"&nbsp;";
            jq("#div_customer").append(item);
         });
      }
   });
}
function findModel(){
   jq.ajax({
      type:"POST",
      url:"webfactOrder_findModel",
      dataTyep:"json",
      success:function(data){
         var item;
         jq.each(data,function(i,obj){
           item="<option  value='"+obj+"'>"+obj+"<option/>";
           jq("#sel_models").append(item);
         });
      }
   });
}
window.onload=getAllFact_json(),findComponent(),findBrank(),findCustomer(),findModel();
</script>


</head>
<body>
  <div style="width:2000px">
  <form id="public_form" method="post">
	<table  border="0px">
		<tr>
			<td>廠別</td>
			<td>
			  <!--<s:if test="#attr.factNo=='tw'">
			    <div id="uboxstyle">
					<select name="factNo" id="factNo">
						<option value="nothing">請選擇</option>						
						<s:iterator value="#attr.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					</div>
				</s:if> 
				<s:else>
				  <div id="uboxstyle">
					<select name="factNo" id="factNo">						
						<option value="<s:property value="#attr.factNo"/>">
							<s:property value="#attr.factName" />(<s:property value="#attr.factNo"/>)
						</option>
					</select>
					</div>
				</s:else>
				-->
				
				<div id="div_factNos" style="width:400px;border:1px dashed green">				   
				</div>				
			</td>
			</tr>
			<tr>
			<td>部件</td>
		    <td>		       
				<div id="div_component" style="width:400px;border:1px dashed green">
				</div>				
		    </td>
		    </tr>
		    <tr>
		    <td>品牌</td>
		    <td>
		       <div id="div_brank" style="width:400px;border:1px dashed green">
				</div>				
		    </td>
		    </tr>
		    <tr>
		    <td>客户</td>
		    <td>
		       <div id="div_customer" style="width:400px;border:1px dashed green">
				</div>				
		    </td>
		    </tr>
		    <tr>
		    <td>模型</td>
		    <td>
		       <div id="div_model" style="width:400px;border:1px dashed green">
		          <select name="models" id="sel_models"></select> 
				</div>
		    </td>
		    </tr>
			<%--<td>年月</td>
			<td>
			  開始日期:<input type="text" id="year" name="yymm" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/></br>
			  結束日期:<input type="text" id="year" name="yymm2" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>			
			</td>--%>
			<tr>
			<td>
			   <input value="搜索" type="button" id="addbtn" onclick="javascript:submis('public_form')" />
			   <input value="導出Excel" type="button" id="search_forday" onclick="print('public_form')"/>	
		    </td>
		    </tr>
		
	</table>
	</form>
	</div>
</body>
</html>
