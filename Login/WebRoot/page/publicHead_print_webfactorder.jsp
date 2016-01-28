
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
		url:"webfact_findFactByFactNo",
		dataType:"json",
		success:function(data){
			var item="";
			jq.each(data,function(i,obj){
				item+="<div><input type='checkbox' name='factNos' value='"+obj[0]+"' onclick='init()'/>"+obj[1]+"("+obj[0]+")</div>";				
			});
			jq("#div_factNos").append(item);
		}	
	});
}
function findComponent(){
   jq.ajax({
      type:"POST",
      url:"webfactOrder_findComponent",
      dataType:"json",
      success:function(data){
        var item="";
        if(data!=null){
           jq.each(data,function(i,obj){
          item+="<div><input type='checkbox' name='components' value='"+obj+"'/>"+obj+"</div>";        
        });
           jq("#div_component").append(item);
        }
        
      }
   });
}
function findBrank(){
   jq.ajax({
       type:"POST",
       url:"webfactOrder_findBrank",
       dataType:"json",
       success:function(data){
           var item="";
           if(data!=null){
             jq.each(data,function(i,obj){
              item+="<div><input type='checkbox' name='branks' value='"+obj+"'/>"+obj+"</div>";             
           });
             jq("#div_brank").append(item);
           }
           
       }
   });
}
function findCustomer(){
   jq.ajax({
      type:"POST",
      url:"webfactOrder_findCustomer",
      dataType:"json",
      success:function(data){
         var item="";
         if(data!=null){
           jq.each(data,function(i,obj){
            item+="<div><input type='checkbox' name='customers' value='"+obj+"'/>"+obj+"</div>";           
         });
           jq("#div_customer").append(item);
         }
         
      }
   });
}
function findModel(){
   jq.ajax({
      type:"POST",
      url:"webfactOrder_findModel",
      dataTyep:"json",
      success:function(data){
         var item="";
         if(data!=null){
            jq.each(data,function(i,obj){
           item+="<div><input type='checkbox' name='models' value='"+obj+"'/>"+obj+"</div>";           
         });
            jq("#div_model").append(item);
         }
         
      }
   });
}
function findFactSname(){	
   jq("#div_factSname").html("");
   jq("#all_factSname").prop("checked",false);
	jq.ajax({
		type:"POST",
		url:"webfactOrder_findFactSname",
		dataType:"json",
		success:function(data){
			var item="";
			if(data!=null){
				jq.each(data,function(i,obj){
					item+="<div><input type='checkbox' name='factSnames' value='"+obj+"' onclick='init2()'/>"+obj+"</div>";					
				});
				jq("#div_factSname").append(item);
			}			
		}
	});
}

function findFactArea(){
	jq("#div_factArea").html("");
	jq("#all_factArea").prop("checked",false);
	jq.ajax({
		type:"POST",
		url:"webfactOrder_findFactArea",
		dataType:"json",
		success:function(data){
			var item="";
			if(data!=null){
				jq.each(data,function(i,obj){
					item+="<div><input type='checkbox' name='factAreas' value='"+obj+"' onclick='init2()'/>"+obj+"</div>";					
				});
				jq("#div_factArea").append(item);
			}			
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
	//findFactSname();
	/* findComponent();findBrank();findCustomer();findModel(); */
	getAllFact_json();
};

function init(){
	clearAll();
var factNos=new Array();
var checkbox_fact=jq("input[name='factNos']:checked");
checkbox_fact.each(function(i,checkbox){
	factNos.push(checkbox.value);
});
   jq.ajax({
      type:"POST",
      traditional:true,
      url:"webfactOrder_init",
      data:{'factNos':factNos},
      success:function(){
        findFactArea();
        //findComponent();findBrank();findCustomer();findModel(); 
      }
   });
}

function init2(){
    var checkbox_factarea=jq("input[name='factAreas']:checked");
    if(checkbox_factarea.length>0){
    	clearAll();
    }
	var factAreas=new Array();
	checkbox_factarea.each(function(i,checkbox){
		factAreas.push(checkbox.value);
	});
	   jq.ajax({
	      type:"POST",
	      traditional:true,
	      url:"webfactOrder_init2",
		  data:{'factAreas':factAreas},
	      success:function(){
	        findComponent();findBrank();findCustomer();findModel(); 
	      }
	   });
	}
function clearAll(){
	jq("#div_component").html("");
	jq("#div_brank").html("");
	jq("#div_customer").html("");
	jq("#div_model").html("");
	
	jq("#all_brank,#all_customer,#all_model,#all_component").prop("checked",false);
	
	jq("#div_factNos").css("border","1px dashed blue");
	jq("#div_factSname").css("border","");
	jq("#div_brank").css("border","");
	jq("#div_customer").css("border","");
}
</script>


</head>
<body>
  <div style="width:850px;border:1px solid grey;text-align:center">
  <font><b>綜合搜索</b></font>
  <form id="public_form" method="post">
	<table >
		<tr>
		    <td class="td_right">工廠</td>
			<td>
			   <div id="div_factNos" style="width:400px;height:160px;overflow:auto;border:1px dashed blue;vertical-align:middle">			    
				  <div><input type="checkbox" id="all_factno" onclick="selectAll('all_factno','factNos'),init()"/>全选</div><hr/>				   						    			    				              
			   </div>			   	  			
			</td>
			<td>廠別狀態</td>
		      <td>
		      <!--  <div style="width:400px;height:160px;overflow:auto;border:1px dashed green">
		      <div><input type="checkbox" id="all_factSname" onclick="selectAll('all_factSname','factSnames'),init2()"/> 全选</div><hr/>				  
				<div id="div_factSname">
				  			 			   
				</div>
			  </div>-->
			  
			  <div style="width:400px;height:160px;overflow:auto;border:1px dashed green">
		      <div><input type="checkbox" id="all_factArea" onclick="selectAll('all_factArea','factAreas'),init2()"/> 全选</div><hr/>				  
				<div id="div_factArea">
				  			 			   
				</div>
			  </div>							
			</td>															
			</tr>
						
		    <tr>
		    <td>品牌</td>
		    <td>
		       <div style="width:400px;height:160px;overflow:auto;border:1px dashed green">
		       <div><input type="checkbox" id="all_brank" onclick="selectAll('all_brank','branks')"/>全选</div><hr/>
		       <div id="div_brank">
		         
			   </div>
			   </div>				
		    </td>
		    <td class="td_right">客户</td>
		    <td>
		      <div style="width:400px;height:160px;overflow:auto;border:1px dashed green">
		      <div><input type="checkbox" id="all_customer" onclick="selectAll('all_customer','customers')"/>全选</div><hr/>
		       <div id="div_customer">
		         
			   </div>
			  </div> 				
		    </td>
		    </tr>
		    
		    <tr>
		    <td>模型</td>
		    <td>
		       <div style="width:400px;height:160px;overflow:auto;border:1px dashed #A0522D">
		       <div><input type="checkbox" id="all_model" onclick="selectAll('all_model','models')"/>全选</div><hr/>
		       <div id="div_model">
		         
			   </div>
			   </div>
		    </td>
		    <td class="td_right">部件</td>
		    <td>	
		       <div style="width:400px;height:160px;overflow:auto;border:1px dashed #A0522D">
		         <div><input type="checkbox" id="all_component" onclick="selectAll('all_component','components')"/>全选</div><hr/>	       
				<div id="div_component">
				  	
				</div>
			   </div>					
		    </td>
		    </tr>
		    <tr>
		       <td colspan="4" >
		        <div id="div_yymm" style="width:440px">
		                        開始<input type="text" id="yymm" name="yymm" onClick="WdatePicker({minDate:'2008',maxDate:'#F{$dp.$D(\'yymm2\')||\'%y-{%M-1}\'}',dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>
			             結束<input type="text" id="yymm2" name="yymm2" onClick="WdatePicker({minDate:'#F{$dp.$D(\'yymm\')}',maxDate:'%y-%M',dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>
			    
			     </div>
			     <input value="搜索" type="button" id="addbtn" onclick="javascript:submis('public_form')" />
			     <input value="導出Excel" type="button" id="search_forday" onclick="print('public_form')"/>	 
		       </td>
		    </tr>
			
		
	</table>
	</form>
	</div>
</body>
</html>
