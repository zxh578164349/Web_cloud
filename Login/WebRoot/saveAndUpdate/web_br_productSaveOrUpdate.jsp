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
.list_item{margin:0px 0px; width:100%;}  
.list_item li.columnhead,.list_item li.columnhead2{font-size: 12px;font-weight:bold;}  
.list_item li,.list_item li.columnhead{  
    width:30%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}
.list_item li.column2,.list_item li.columnhead2{  
    width:45%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}  
.list_item li input[type=text],.list_item li select{  
   width:100% ; 
}
.list_item li.column2 input[type=text]{
   width:93%;
}   
</style>
</head>

<body>
	<form action="webbrpro_add" method="post" id="form_main">		   
		<div class="panel panel-default" id="div_webformalaitem">
			<div class="panel-heading">
				<label>BR產品設定</label>			 
			</div>
			<div class="panel-body">
			   <table class="table table-condensed">
			       <tr>
						<td>
						廠別:
						<s:if test="#session.factNo!='tw'">
								<input type="text" name="factNo" value="${loginUser.factno}" readonly id="dwr_factno" />
							</s:if> 
							<s:if test="#session.factNo=='tw'">
								<select name="factNo" onchange="" datatype="*" id="dwr_factno">
								</select>
							</s:if>
						</td>
						<td>
						  類別:
						  <select id="div_typeno" onchange="loadNamece(this.value)" datatype="*" name="itemcategory"></select>
						  <input type="hidden" value="<%=str_date%>" name="createDate"  />
						  <input type="hidden" value="${loginUser.id}" name="createUser" />
						</td>
						<td>
						   <input type="button" value="添加產品" onclick="check_addSection()" class="btn btn-primary disabled" id="btn_addsec" />
						</td>
					</tr>
			   </table>
			   
			   
			<hr />
				<ul id="myTab_item" class="nav nav-tabs">
					<li class="active"><a href="#tab_typeno" data-toggle="tab" id="tab_typeno_a">產品名稱</a></li>						
					<li><a href="#tab_namece" data-toggle="tab" id="tab_namece_a">產品列表</a></li>																					
				</ul>
				<div id="myTabContent_item" class="tab-content">
					<div class="tab-pane fade in active" id="tab_typeno">
						<div  class="div_border_green">
							<div id="div_namece">								
								<label style="color:red">請先選擇產品類別</label>
							</div>
														
						</div>
					</div>
					<div class="tab-pane fade" id="tab_namece">
						<div class="div_border_green">
							<div id="div_list">
								<ul class='list_item'>
								   
								</ul>
								
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
		
		/*jq.ajax({
			type:"post",
			dataType:"json",
			url:"weberppf_findTypeNo",
			success:function(data){
				//jq("#div_typeno").html("");			
				var item="";
				jq.each(data,function(index,obj){
					item+="<div><input type='checkbox' value='"+obj.selfchar1+"' name='typenos' onclick='loadNamece(),checkbtn()'/><label>"+obj.selfchar1name+"</label></div>";					
				});
				jq("#div_typeno").append(item);
			}
		});	*/			

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
function check_addSection(){		
		var list_items_val=jq("input[name='itemids']:checked");
		if(list_items_val.length>100){
			layer.msg("所選配方原料超過上限",3,3);
		}else{
			addSection(list_items_val);
		}
		
}
function addSection(list_items_val){
	//var list_items_val=jq("input[name='itemids']:checked");	
	/*index++;
	jq("#btn_addsec").val("添加配方階段("+(index+1)+")");
	var section="<li id='tab_section_"+index+"'><a href='#div_section_"+index+"' data-toggle='tab' id='tab_section_a_"+index+"'>配方階段("+index+")</a></li>";	
	var div_section="<div id='div_section_"+index+"' class='tab-pane fade div_border_blue'></div>";
	var ul="<ul class='list_item' id='ul_"+index+"'></ul>";
	var li_head="<li class='columnhead'>類別</li><li class='columnhead2'>原料名稱</li><li class='columnhead'>PHR</li>"+
		"<li class='columnhead'>重量(KG)</li><li class='columnhead2'>備註</li>";
		
	jq("#del_li").before(section);
	jq("#myTabContent_item").append(div_section);
	jq("#div_section_"+index).append(ul);
	jq("#ul_"+index).append(li_head);*/
	
	
	jq(".list_item").empty();
	
	jq(".list_item").append("<li>類別</li><li class='columnhead2'>名稱</li>");
	var li_content="";
	var div_typeno=jq("#div_typeno").val();
	var div_typeno_txt=jq("#div_typeno").find("option:selected").text();
	var dwr_factno=jq("#dwr_factno").val();
	list_items_val.each(function(i){
			var val=jq(this).val();
			var txt1=val.split("__")[0];
	        var txt2=val.split("__")[1];
	        var txt3=val.split("__")[2];
			li_content+="<li>"+div_typeno_txt+"</li><li class='column2'>"+txt2+"&nbsp;&nbsp;"+txt3+
			"<img src='images/icon/del_file.png' style='border:0' onclick='removeOneItem(jq(this))' name='img_temp'/>"+
			"<input type='hidden' value='"+txt2+"' name='listbrpro["+i+"].namec1'/>"+
			"<input type='hidden' value='"+txt3+"' name='listbrpro["+i+"].namec2'/>"+
			"<input type='hidden' value='"+dwr_factno+"' name='listbrpro["+i+"].id.factNo'/>"+
			"<input type='hidden' value='"+div_typeno+"' name='listbrpro["+i+"].itemcategory'/>"+
			"<input type='hidden' value='"+txt1+"' name='listbrpro["+i+"].id.webErpProductinFormation.itemid'/>"+
			"</li>"			
		});	
		jq(".list_item").append(li_content);		
		jq("#tab_namece_a").click();				    
	    jq("#sub_main").removeClass("disabled");
	    
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
