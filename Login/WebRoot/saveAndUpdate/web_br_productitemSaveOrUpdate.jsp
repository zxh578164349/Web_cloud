<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd-hh");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<style type="text/css">
  .div_border_green{
    width:95%;height:250px;overflow:auto;text-align:left;font-size: 12px
  }
  .div_border_blue{
    width:95%;height:250px;overflow:auto;text-align:left;font-size: 12px
  }
  
#myTabContent_item ul{margin:0px;padding:0px}
.list_item,.list_item2{margin:0px 0px; width:100%;}  
.list_item li.columnhead,.list_item li.columnhead2{font-size: 12px;font-weight:bold;}  
.list_item li,.list_item li.columnhead{  
    width:25%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}
.list_item li.column2,.list_item li.columnhead2{  
    width:25%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
} 
.list_item2 li,.list_item2 li.columnhead{  
    width:20%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}
.list_item2 li.column2,.list_item2 li.columnhead2{  
    width:20%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}  
.list_item li input[type=text],.list_item li select,.list_item2 li input[type=text],.list_item2 li select{  
   width:100% ; 
}
 
</style>
</head>

<body>
	<form action="webbrpro_add2_3" method="post" id="form_main">	
	   <div class="panel panel-default">
	     <div class="panel-heading"><label>廠別</label></div>
	       <div class="panel-body">	  
						<table class="table table-condensed">
							<tbody>															
								    <tr>
									<td>							
									    <s:if test="#session.factNo!='tw'">
									      <input type="text" name="factNo" value="${loginUser.factno}" readonly id="dwr_factno" />
								        </s:if> 
								        <s:if test="#session.factNo=='tw'">
									      <select name="factNo" onchange="checkproduct()" datatype="*" id="dwr_factno">
									      </select>
								        </s:if>		
								        
								        																						
									</td>
									<td>
									<input type="text" name="yymmdd" onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d',opposite:true,disabledDates:['....0228','......30']})" onblur="checkproduct()" class="Wdate" datatype="*" /> 
									<input type="hidden" value="<%=str_date%>" id="createDate"  />
									 <input type="hidden" value="${loginUser.id}" id="createUser" />								
									 								
									</td>
								</tr> 								
							</tbody>
						</table>
						
						<hr />
				<ul id="myTab_item" class="nav nav-tabs">
					<li class="active"><a href="#tab_typeno" data-toggle="tab" id="tab_typeno_a">產品庫存</a></li>																										
				</ul>
				<div id="myTabContent_item" class="tab-content">
					<div class="tab-pane fade in active" id="tab_typeno">
						<div  class="div_border_green">
							<div id="div_namece">														
								<ul class="list_item"></ul>								
							</div>
														
						</div>
					</div>					
				</div>
				
				<ul id="myTab_item2" class="nav nav-tabs">
					<li class="active"><a href="#tab_typeno2" data-toggle="tab" id="tab_typeno_a2">產品預估</a></li>																										
				</ul>
				<div id="myTabContent_item" class="tab-content">
					<div class="tab-pane fade in active" id="tab_typeno2">
						<div  class="div_border_green">
							<div id="div_namece2">																						
								<ul class="list_item2"></ul>
							</div>
														
						</div>
					</div>					
				</div>
			</div>
		</div>
		
        
		<center>
	        <input type="button" id="sub_main" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;			              
			<input type="button" value="返回"  onclick="javascript:back()" class="btn btn-primary" />	
			<span id="msgtip" style="margin-left:30px;"></span>		            
		</center>																								     
	</form>


<script type="text/javascript">
	jq(function() {	
		var demo = jq("#form_main").Validform({
			btnSubmit : "#sub_main",
			//tiptype : 4,
			//tipSweep:true,
			//showAllError : true,
			tiptype : function(msg,o,cssctl){
				var objtip=jq("#msgtip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			},
			datatype : {
				"*8-4" : /^-?\d{0,8}(\.[0-9]{0,4})?$/,
				"*9-5" : /^-?\d{0,9}(\.[0-9]{0,5})?$/		
			},
			ajaxPost:true,
			beforeSubmit:function(){
			    jq("#dwr_factno").removeAttr("disabled");
			},
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("webbrpro_findPageBean");
					//loadUrl("webbrpro_findById?formulaIndex="+jq("#formulaIndex").val());
				}
				if(data=="1"){
					layer.msg("提交失敗",3,3);
					jq("#msgtip").text("");
				}
				if(data=="3"){
				    layer.msg("保存附檔失敗",3,3);
				    jq("#msgtip").text("");
				}				
			}
		});
		demo.tipmsg.w["*8-4"] = "只能數字且不超過8位數,可保留4位以內小數";
		demo.tipmsg.w["*9-5"] = "只能數字且不超過9位數,可保留5位以內小數";
		
		jq.ajax({
			type:"get",
			dataType:"json",
			url:"webfact_findAllVwebfact",
			success:function(data){
				var item="";
				jq("#dwr_factno").empty();
				jq("#dwr_factno").append("<option value=''>請選擇廠別</option>");
				jq.each(data,function(i,obj){
					item+="<option value='"+obj[0]+"'>"+obj[1]+"("+obj[3]+")</option>";					
				});
				jq("#dwr_factno").append(item);
			}
		});
		
		jq.ajax({
			type:"post",
			dataType:"json",
			url:"weberppf_findItemcategory",
			success:function(data){
				var item="";
				jq("#div_typeno").empty();
				jq("#div_typeno").append("<option value=''>請選擇產品類別</option>");
				jq.each(data,function(i,obj){
					item+="<option value='"+obj[0]+"'>"+obj[0]+"-"+obj[1]+"</option>";					
				});
				jq("#div_typeno").append(item);
			}
			
		});								

	});	
	

	
function loadNamece(itemcategory){
	jq("#div_namece").empty();
	/*var selfchar1s=new Array();
	var list=jq("input[name='typenos']:checked");
	list.each(function(i,typeno){
		selfchar1s.push(typeno.value);
	});*/

		jq.ajax({
			type:"post",
			dataType:"json",
			traditional:true,
			//data:{'selfchar1s':selfchar1s},
			data:{'itemcategory':itemcategory},
			url:"weberppf_findNamece2",
			success:function(data){
				var item="<input type='checkbox' id='all_namece' onclick='checkallItems(),checkbtn()'/>全選<hr/>";
				jq.each(data,function(i,obj){
					item+="<div><input type='checkbox' value='"+obj[0]+"__"+obj[1]+"__"+obj[2]+"' name='itemids' onclick='checkbtn()'/><label>"+obj[1]+"&nbsp;&nbsp;"+obj[2]+"</label></div>";					
				});
				jq("#div_namece").append(item);
			}			
		});	
}

var index=0;
var item_nums=0;

function checkproduct(){
	var factno=jq("#dwr_factno").val();
	jq.ajax({
		type:"post",
		dataType:"json",
		data:{factNo:factno},
		url:"webbrpro_findByFactNo2",
		success:function(data){
			if(data=="0"){
				layer.alert("請設定BR產品",0);	
				jq(".list_item").empty();
				jq(".list_item2").empty();
			}else{
				addSection();
			}
		}
	});
}
function addSection(){
	var li_content="";
	var li_content2="";
	var factno=jq("#dwr_factno").val();
	var yymmdd=jq("input[name='yymmdd']").val();
	var createUser=jq("#createUser").val();
	var createDate=jq("#createDate").val();
	if(factno!=""&&yymmdd!=""){
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:factno,yymmdd:yymmdd},
			url:"webbrpro_findByfactNoAndyymmdd",
			success:function(data){
				if(data=="1"){
					layer.alert("注意:該廠當前月份部分BR產品庫存已存在,繼續添加會覆蓋舊數據",0);
				}
			}
		});
		
		jq(".list_item").empty();	
		jq(".list_item").append("<li>產品名稱</li><li>庫存數(KG)</li>");
		jq(".list_item").append("<li>已訂購未入廠(KG)</li><li>當月耗用(KG)</li>");
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:factno,yymmdd:yymmdd},
			url:"webbrpro_findByFactno",
			success:function(data){				
				jq.each(data,function(i,obj){
					li_content+="<li>"+obj[1]+"&nbsp;"+obj[2]+"</li>"+
					"<li><input type='text' name='listitem["+i+"].inventory' datatype='*'/></li>"+
					"<li><input type='text' name='listitem["+i+"].orderNotin' datatype='*'/></li>"+
					"<li><input type='text' name='listitem["+i+"].actualUsed' datatype='*'/>"+
					"<input type='hidden' name='listitem["+i+"].id.webBrProduct.id.webErpProductinFormation.itemid' value='"+obj[0]+"'/>"+
					"<input type='hidden' name='listitem["+i+"].id.webBrProduct.id.factNo' value='"+factno+"'/>"+
					"<input type='hidden' name='listitem["+i+"].id.yymmdd' value='"+yymmdd+"'/>"+
					"<input type='hidden' name='listitem["+i+"].createUser.id' value='"+createUser+"'/>"+
					"<input type='hidden' name='listitem["+i+"].createDate' value='"+createDate+"'/>"+
					"</li>"
				})	
				jq(".list_item").append(li_content);
			}			
		});
		
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:factno,yymmdd:yymmdd},
			url:"webbrpro_findByfactNoAndyymmdd2",
			success:function(data){
				if(data=="1"){
					layer.alert("注意:該廠當前月份BR產品預訂已存在,繼續添加會覆蓋舊數據",0);
				}
			}
		});
		
		jq(".list_item2").empty();	
		jq(".list_item2").append("<li>製程</li><li>當月實際生產雙數(含不良)</li>");
		jq(".list_item2").append("<li>次一月預估生產雙數</li><li>次二月預估生產雙數</li><li>次三月預估生產雙數</li>");
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:factno},
			url:"webfact_findByFactNo_show_order",
			success:function(data){				
				jq.each(data,function(i,obj){
					li_content2+="<li>"+obj[0]+"</li>"+
					"<li><input type='text' name='listest["+i+"].actualPairs' datatype='*'/></li>"+
					"<li><input type='text' name='listest["+i+"].estimatingPairs1' datatype='*'/></li>"+
					"<li><input type='text' name='listest["+i+"].estimatingPairs2' datatype='*'/></li>"+
					"<li><input type='text' name='listest["+i+"].estimatingPairs3' datatype='*'/>"+
					"<input type='hidden' name='listest["+i+"].id.factNo' value='"+factno+"'/>"+
					"<input type='hidden' name='listest["+i+"].id.yymmdd' value='"+yymmdd+"'/>"+
					"<input type='hidden' name='listest["+i+"].id.factCode' value='"+obj[0]+"'/>"+
					"<input type='hidden' name='listest["+i+"].createUser.id' value='"+createUser+"'/>"+
					"<input type='hidden' name='listest["+i+"].createDate' value='"+createDate+"'/>"+
					"</li>"
				})	
				jq(".list_item2").append(li_content2);
			}			
		});
		
		
	}						    	    	    	    	   	    
}

function removeOneItem(img){
	    img.parent().prev().remove();
		img.parent().remove();
		if(jq("[name='img_temp']").length==0){
			layer.msg("請添加產品",3,3);
			jq("#sub_main").addClass("disabled");
		}
}

function checkbtn(){
	var items=jq("input[name='itemids']:checked");
	if(items!=null&&items.length>0){
		jq("#btn_addsec").removeClass("disabled");
	}else{
		jq("#btn_addsec").addClass("disabled");
	}
}



function checkallItems(){
	var one=jq("#all_namece");
	if(one.prop("checked")){
		jq("input[name='itemids']").each(function(){
			jq(this).prop("checked",true);
		});
	}else{
		jq("input[name='itemids']").each(function(){
			jq(this).prop("checked",false);
		});
	}
}
	
</script>
</body>
</html>
