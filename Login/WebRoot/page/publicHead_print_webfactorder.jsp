
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
				item="<div><input type='checkbox' name='factNos' value='"+obj[0]+"'/>"+obj[1]+"</div>";
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
          item="<div><input type='checkbox' name='components' value='"+obj+"'/>"+obj+"</div>";
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
              item="<div><input type='checkbox' name='branks' value='"+obj+"'/>"+obj+"</div>";
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
            item="<div><input type='checkbox' name='customers' value='"+obj+"'/>"+obj+"</div>";
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
           item="<div><input type='checkbox' name='models' value='"+obj+"'/>"+obj+"</div>";
           jq("#div_model").append(item);
         });
      }
   });
}
function findFactSname(){
	jq.ajax({
		type:"POST",
		url:"webfactOrder_findFactSname",
		dataType:"json",
		success:function(data){
			var item;
			jq.each(data,function(i,obj){
				item="<div><input type='checkbox' name='factSnames' value='"+obj+"'/>"+obj+"</div>";
				jq("#div_factSname").append(item);
			});
		}
	});
}

function selectAll(str,str2){
    var allobj=jq("#"+str); 
        if(allobj.is(":checked")){
          jq("input[name='"+str2+"']").prop("checked",true);
        }else{
        	jq("input[name='"+str2+"']").prop("checked",false);
        }     
}

window.onload=function(){
	findFactSname();findComponent();findBrank();findCustomer();findModel();
}
</script>


</head>
<body>
  <div style="width:1000px">
  <form id="public_form" method="post">
	<table  border="0px">
		<tr>
			<td>廠別</td>
			<%--<td>
				<div id="div_factNos" style="width:400px;height:250px;overflow:auto;border:1px dashed green">
				  <div><input type="checkbox" id="all_factno" onclick="selectAll('all_factno','factNos')"/>全选</div><hr/>				   
				</div>				
			</td>--%>
			<td>
				<div id="div_factSname" style="width:400px;height:250px;overflow:auto;border:1px dashed green">
				  <div><input type="checkbox" id="all_factSname" onclick="selectAll('all_factSname','factSnames')"/>全选</div><hr/>				   
				</div>				
			</td>
			<td>部件</td>
		    <td>		       
				<div id="div_component" style="width:400px;height:250px;overflow:auto;border:1px dashed green">
				  <div><input type="checkbox" id="all_component" onclick="selectAll('all_component','components')"/>全选</div><hr/>	
				</div>				
		    </td>
			</tr>			
		    <tr>
		    <td>品牌</td>
		    <td>
		       <div id="div_brank" style="width:400px;height:250px;overflow:auto;border:1px dashed green">
		         <div><input type="checkbox" id="all_brank" onclick="selectAll('all_brank','branks')"/>全选</div><hr/>
			   </div>				
		    </td>
		    <td>客户</td>
		    <td>
		       <div id="div_customer" style="width:400px;height:250px;overflow:auto;border:1px dashed green">
		         <div><input type="checkbox" id="all_customer" onclick="selectAll('all_customer','customers')"/>全选</div><hr/>
			   </div>				
		    </td>
		    </tr>
		    <tr>
		    
		    </tr>
		    <tr>
		    <td>模型</td>
		    <td>
		       <div id="div_model" style="width:400px;height:250px;overflow:auto;border:1px dashed green">
		         <div><input type="checkbox" id="all_model" onclick="selectAll('all_model','models')"/>全选</div><hr/>
			   </div>
		    </td>
		    <td>年份</td>
			<td>
			  <select name="year" >
			     <option value="2015">2015</option>
			     <option value="2014">2014</option>
			  </select>			  			
			</td>
		    </tr>
			<%--<td>年月</td>
			<td>
			  開始日期:<input type="text" id="year" name="yymm" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/></br>
			  結束日期:<input type="text" id="year" name="yymm2" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>			
			</td>--%>
			<tr>
			<td colspan="6">
			   <input value="搜索" type="button" id="addbtn" onclick="javascript:submis('public_form')" />
			   <input value="導出Excel" type="button" id="search_forday" onclick="print('public_form')"/>	
		    </td>
		    </tr>
		
	</table>
	</form>
	</div>
</body>
</html>
