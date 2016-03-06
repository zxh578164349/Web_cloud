
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
<link href="css/validate.css" rel="stylesheet" type="text/css" />
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

jq(function(){
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
});


function getAllFact_json(){	
alert("dfdf");
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
  <div>
  <h2>綜合搜索<h2>
   
  <form id="public_form" method="post">
    <div>
 <ul id="myTab" class="nav nav-tabs">
   <li class="active"><a href="#tab_factno" data-toggle="tab">工廠</a></li>         
   <li><a href="#tab_factarea" data-toggle="tab">廠別狀態</a></li>
   <li><a href="#tab_brank" data-toggle="tab">品牌</a></li>
   <li><a href="#tab_customer" data-toggle="tab">客戶</a></li>
   <li><a href="#tab_model" data-toggle="tab">模型</a></li>
   <li><a href="#tab_component" data-toggle="tab">部件</a></li>
   <!--  <li class="dropdown">
      <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">Java 
         <b class="caret"></b>
      </a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
         <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
         <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
      </ul>
   </li>-->
</ul>
<div id="myTabContent" class="tab-content">
   <div class="tab-pane fade in active" id="tab_factno">
       <div id="div_factNos" style="width:400px;height:160px;overflow:auto;border:1px dashed blue;text-align:left">			    
				  <div><input type="checkbox" id="all_factno" onclick="selectAll('all_factno','factNos'),init()"/>全选</div><hr/>				   						    			    				              
	</div>
   </div>
   <div class="tab-pane fade" id="tab_factarea">
      <div style="width:400px;height:160px;overflow:auto;border:1px dashed green;;text-align:left">
		       <div><input type="checkbox" id="all_factArea" onclick="selectAll('all_factArea','factAreas'),init2()"/> 全选</div><hr/>				  
				<div id="div_factArea">
				  			 			   
				</div>
	  </div>	
   </div>
   <div class="tab-pane fade" id="tab_brank">
      <div style="width:400px;height:160px;overflow:auto;border:1px dashed green;text-align:left">
		       <div><input type="checkbox" id="all_brank" onclick="selectAll('all_brank','branks')"/>全选</div><hr/>
		       <div id="div_brank">
		         
			   </div>
	  </div>	
   </div>
   <div class="tab-pane fade" id="tab_customer">
      <div style="width:400px;height:160px;overflow:auto;border:1px dashed green;text-align:left">
		      <div><input type="checkbox" id="all_customer" onclick="selectAll('all_customer','customers')"/>全选</div><hr/>
		       <div id="div_customer">
		         
			   </div>
	 </div> 	
   </div>
   <div class="tab-pane fade" id="tab_model">
      <div style="width:400px;height:160px;overflow:auto;border:1px dashed #A0522D;text-align:left">
		       <div><input type="checkbox" id="all_model" onclick="selectAll('all_model','models')"/>全选</div><hr/>
		       <div id="div_model">
		         
			   </div>
	 </div>	
   </div>
   <div class="tab-pane fade" id="tab_component">
      <div style="width:400px;height:160px;overflow:auto;border:1px dashed #A0522D;text-align:left">
		         <div><input type="checkbox" id="all_component" onclick="selectAll('all_component','components')"/>全选</div><hr/>	       
				<div id="div_component">
				  	
	  </div>
	 </div>	
   </div>
   <!--  <div class="tab-pane fade" id="jmeter">
      <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
   </div>
   <div class="tab-pane fade" id="ejb">
      <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
      </p>
   </div>-->
</div>
</div>
  
  
	<table id="tb_search">
		<%--<tr>
		    <td class="td_right">工廠</td>
			<td>
			   <!--  <div id="div_factNos" style="width:400px;height:160px;overflow:auto;border:1px dashed blue;vertical-align:middle">			    
				  <div><input type="checkbox" id="all_factno" onclick="selectAll('all_factno','factNos'),init()"/>全选</div><hr/>				   						    			    				              
			   </div>-->			   			  			   			   	  			
			</td>
			<td>廠別狀態</td>
		      <td>		      			  
			  <!-- <div style="width:400px;height:160px;overflow:auto;border:1px dashed green">
		       <div><input type="checkbox" id="all_factArea" onclick="selectAll('all_factArea','factAreas'),init2()"/> 全选</div><hr/>				  
				<div id="div_factArea">
				  			 			   
				</div>
			  </div>-->							
			</td>															
			</tr>
						
		    <tr>
		    <td>品牌</td>
		    <td>
		       <!--  <div style="width:400px;height:160px;overflow:auto;border:1px dashed green">
		       <div><input type="checkbox" id="all_brank" onclick="selectAll('all_brank','branks')"/>全选</div><hr/>
		       <div id="div_brank">
		         
			   </div>
			   </div>-->				
		    </td>
		    <td class="td_right">客户</td>
		    <td>
		      <!--  <div style="width:400px;height:160px;overflow:auto;border:1px dashed green">
		      <div><input type="checkbox" id="all_customer" onclick="selectAll('all_customer','customers')"/>全选</div><hr/>
		       <div id="div_customer">
		         
			   </div>
			  </div> -->				
		    </td>
		    </tr>
		    
		    <tr>
		    <td>模型</td>
		    <td>
		       <!--  <div style="width:400px;height:160px;overflow:auto;border:1px dashed #A0522D">
		       <div><input type="checkbox" id="all_model" onclick="selectAll('all_model','models')"/>全选</div><hr/>
		       <div id="div_model">
		         
			   </div>
			   </div>-->
		    </td>
		    <td class="td_right">部件</td>
		    <td>	
		       <!--  <div style="width:400px;height:160px;overflow:auto;border:1px dashed #A0522D">
		         <div><input type="checkbox" id="all_component" onclick="selectAll('all_component','components')"/>全选</div><hr/>	       
				<div id="div_component">
				  	
				</div>
			   </div>-->					
		    </td>
</tr>--%>

<tr>
		       <td >
		        <div id="div_yymm" style="width:440px;float:left">
		          <input type="text" id="yymm" name="yymm" onClick="WdatePicker({minDate:'2008',maxDate:'#F{$dp.$D(\'yymm2\')||\'%y-{%M-1}\'}',dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>至
			      <input type="text" id="yymm2" name="yymm2" onClick="WdatePicker({minDate:'#F{$dp.$D(\'yymm\')}',maxDate:'%y-%M',dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>			    
			     </div>
			     <input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis('public_form')" />
			     <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form')"/>	 
		       </td>
</tr>
			
</table>
</form>
</div>
</body>
</html>
