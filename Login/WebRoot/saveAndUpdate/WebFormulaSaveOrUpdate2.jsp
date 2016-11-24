<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<style type="text/css">
  .div_border_green{
    width:100%;height:250px;overflow:auto;border:1px dashed green;text-align:left
  }
  .div_border_blue{
    width:100%;height:250px;overflow:auto;border:1px dashed blue;text-align:left
  }
  
#myTabContent ul{margin:0px;padding:0px}
.list{margin:0px 0px; width:100%;}  
.list li.columnhead{font-size: 12px;font-weight:bold;}  
.list li,.list li.columnhead{  
    width:20%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}  
</style>

</head>

<body>
	<form action="webformula_add" method="post" id="form">
	
	   <ul id="myTab" class="nav nav-tabs">
		 <li class="active"><a href="#tab_webformula" data-toggle="tab" id="a_webformula">配方主表</a></li>			  		 
		 <li><a href="#tab_webformaulaitems" data-toggle="tab" id="a_webformaulaitems">配方階段</a></li>	
		 <li><a href="#tab_webtabpom" data-toggle="tab" id="a_webtabpom">物性資料</a></li>		 
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="tab_webformula">

				<div class="panel panel-default">
					<div class="panel-heading">配方表頭</div>
					<div class="panel-body">
						<table class="table table-condensed" id="tb_main">
							<tbody>
								<tr>
									<th>廠別</th>
									<th>配方索引</th>
								</tr>
								<tr>
									<td><input type="text" id="factNo" style="color:blue" value="<s:property value='formula.factNo.factNo'/>" name="formula.factNo.factNo" readonly /></td>
									<td><input type="text" style="color:blue" value="<s:property value='formula.formulaIndex' />" name="formula.formulaIndex" readonly />
									</td>
								</tr>
								<tr>
									<th>製程類別</th>
									<th>配方編號</th>
								</tr>
								<tr>
									<td><input type="text" value="<s:property value='formula.factCode.id'/>" name="formula.factCode.id" style="color:blue" readonly />
									</td>
									<td><input type="text" name="formula.formulaNo" value="<s:property value='formula.formulaNo' />" datatype="*0-6">
									</td>
								</tr>
								<tr>
									<th>配方名稱</th>
									<th>倍率</th>
								</tr>
								<tr>
									<td><input type="text" name="formula.formulaName" value="<s:property value='formula.formulaName' />" datatype="*0-6">
									</td>
									<td><input type="text" name="formula.magnification" value="<s:property value='formula.magnification' />" datatype="*0-6">
									</td>
								</tr>
								<tr>
									<th>帶皮半成品硬度</th>
									<th>成品硬度</th>
								</tr>
								<tr>
									<td><input type="text" name="formula.semifinishedProductHardness" value="<s:property value='formula.semifinishedProductHardness' />" datatype="*0-6">
									</td>
									<td><input type="text" name="formula.productHardness" value="<s:property value='formula.productHardness' />" datatype="*0-6">
									</td>
								</tr>
								<tr>
									<th>品牌形體</th>
									<th>顏色</th>
								</tr>
								<tr>
									<td><input type="text" name="formula.brandBody" value="<s:property value='formula.brandBody' />" datatype="*0-6">
									</td>
									<td><input type="text" name="formula.color" value="<s:property value='formula.color' />" datatype="*0-6">
									</td>
								</tr>
								<tr>
									<th>發行日期</th>
									<th>品牌指定</th>
								</tr>
								<tr>
									<td><input type="text" name="formula.issuedDate" value="<s:property value='formula.issuedDate' />" onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'})"
										datatype="*0-6" class="Wdate" />
									</td>
									<td><s:if test='formula.assignBrand=="0"'>
					                                                            指     定<input type="radio" name="formula.assignBrand" value="1" />
					                                                            非指定<input type="radio" name="formula.assignBrand" value="0" checked />
										</s:if> 
										<s:else>
					                                                             指     定<input type="radio" name="formula.assignBrand" value="1" checked />
					                                                              非指定<input type="radio" name="formula.assignBrand" value="0" />
										</s:else>
									</td>
								</tr>
								<tr>
									<th colspan="2">備註</th>
								</tr>
								<tr>
									<td colspan="2"><textarea style="width:100%;height:200px" name='formula.remark'><s:property value="formula.remark" /></textarea>
									 <input type="hidden" value="<s:property value='formula.createName'/>" name="formula.createName" /> 
									 <input type="hidden" value="<s:property value='formula.createDate'/>" name="formula.createDate" id="createDate" />
									 <input type="hidden" value="${loginUser.username}" name="formula.modifyName" /> <!--  修改者--> 
									 <input type="hidden" value="<%=str_date%>" name="formula.modifyDate" /> <!--  修改日期--></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="tab-pane fade" id="tab_webformaulaitems">			   
			         <div>
					    <jsp:include page="WebFormulaSaveOrUpdate.jsp" />
				     </div>			    				
			</div>
			<div class="tab-pane fade" id="tab_webtabpom">
				<div>
					<jsp:include page="WebTabpomSaveOrUpdate.jsp" />
				</div>
			</div>
	  </div>
															     
	</form>


<script type="text/javascript">

	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-6" : /^-?\d{1,12}(\.[0-9]{1,3})?$/
			},
			ajaxPost:true,
			beforeSubmit:function(){
			    jq("#dwr_factno").removeAttr("disabled");
		        jq("#dwrFactArea").removeAttr("disabled");
			},
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("/Login/webformula_findPageBean");
				}else{
					layer.msg("提交失敗",3,3);
				}				
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過12位數,可保留三位以內小數";
		
		jq.ajax({
			type:"get",
			dataType:"json",
			url:"webfact_findAllVwebfact",
			success:function(data){
				var item;
				jq("#dwr_factno").empty();
				jq("#dwr_factno").append("<option value=''>請選擇廠別</option>");
				jq.each(data,function(i,obj){
					item="<option value='"+obj[0]+"__"+obj[3]+"'>"+obj[1]+"("+obj[3]+")</option>";
					jq("#dwr_factno").append(item);
				});
			}
		});
		
		jq.ajax({
			type:"post",
			dataType:"json",
			url:"weberpbp_findObjOp1",
			success:function(data){
				var item;
				jq("#dwrFactArea").empty();
				jq("#dwrFactArea").append("<option value=''>請選擇製程類別</option>");
				jq.each(data,function(i,obj){
					item="<option value='"+obj[0]+"__"+obj[1]+"'>"+obj[2]+"</option>";
					jq("#dwrFactArea").append(item);
				});
			}
		});	
		
		jq.ajax({
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
		});
	});
	
	function makeFormulaIndex(){
		jq("#formulaIndex").val("");
		jq("#btn_addwebtabpom").addClass("disabled");
		jq("#btn_addsec").addClass("disabled");
		var dwr_factno=jq("#dwr_factno");
		var dwrFactArea=jq("#dwrFactArea");
		var createDate=jq("#createDate");
		if(dwr_factno.val()!=""&&dwrFactArea.val()!=""&&createDate.val()!=""){
			jq.ajax({
				type:"get",
				dataType:"json",
				url:"webformula_makeFormulaIndex",
				data:{factNo:dwr_factno.val(),factCode:dwrFactArea.val(),createDate:createDate.val()},
				success:function(data){
					jq("#formulaIndex").val(data);
					jq("#div_webformalaitem").css("display","block");
					jq("#btn_addwebtabpom").removeClass("disabled");
					checkbtn();
				},
				error:function(){
					layer.msg("生成配方索引失敗",3,3);
					jq("#div_webformalaitem").css("display","none");
					jq("#btn_addwebtabpom").addClass("disabled");
					jq("#btn_addsec").addClass("disabled");
				}
			});
		}		
   }
	
/*禁止空格輸入*/
jq(function(){
	goTrim();
});
function back(){	
	loadUrl("/Login/webformula_findPageBean3?backIndex=1");
}




function loadNamece(){
	jq("#div_namece").empty();
	var selfchar1s=new Array();
	var list=jq("input[name='typenos']:checked");
	list.each(function(i,typeno){
		selfchar1s.push(typeno.value);
	});
	if(selfchar1s.length>0){
		jq.ajax({
			type:"post",
			dataType:"json",
			traditional:true,
			data:{'selfchar1s':selfchar1s},
			url:"weberppf_findNameces",
			success:function(data){
				var item="<input type='checkbox' id='all_namece' onclick='checkallItems(),checkbtn()'/>全選<hr/>";
				jq.each(data,function(i,obj){
					item+="<div><input type='checkbox' value='"+obj[0]+"' name='itemids' onclick='checkbtn()'/><label>"+obj[2]+"&nbsp;&nbsp;"+obj[3]+"__"+obj[1]+"</label></div>";					
				});
				jq("#div_namece").append(item);
			}
			
		});
	}else{
		jq("#div_namece").append("<label style='color:red'>請先選擇配方類別</label>");
	}	
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
	var cvformulaIndex=jq("#formulaIndex").val();
	//var list_items_val=jq("input[name='itemids']:checked");	
	index++;
	jq("#btn_addsec").val("添加配方階段("+(index+1)+")");
	var section="<li id='tab_section_"+index+"'><a href='#div_section_"+index+"' data-toggle='tab' id='tab_section_a_"+index+"'>配方階段("+index+")</a></li>";	
	var div_section="<div id='div_section_"+index+"' class='tab-pane fade div_border_blue'></div>";
	var ul="<ul class='list' id='ul_"+index+"'></ul>";
	var li_head="<li class='columnhead'>類別</li><li class='columnhead'>原料名稱</li><li class='columnhead'>PHR</li>"+
		"<li class='columnhead'>重量(KG)</li><li class='columnhead'>備註</li>";
		
	jq("#del_li").before(section);
	jq("#myTabContent").append(div_section);
	jq("#div_section_"+index).append(ul);
	jq("#ul_"+index).append(li_head);
	
	
	var li_content="";	
	list_items_val.each(function(i){
			var val=jq(this).val();
			var txt1=jq(this).next().text().split("__")[1];
	        var txt2=jq(this).next().text().split("__")[0];
			li_content+="<li>"+txt1+"</li><li>"+txt2+"</li>"+
			"<li><input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].phrVal'/></li>"+
			"<li><input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].weightVal'/></li><li >"+
			"<input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].remark'/>"+
			"<img src='images/icon/del_file.png' style='border:0' onclick='removeOneItem(jq(this),\"img"+item_nums+"\")' id='img_temp' name='img"+item_nums+"'/>"+
			"<input type='hidden' value='"+val+"' name='formula.webFormulaItemses["+(i+item_nums)+"].fk_weberp_pf.itemid'/>"+
			"<input type='hidden' value='"+index+"' name='formula.webFormulaItemses["+(i+item_nums)+"].sectionNo'/>"+
			"<input type='hidden' value='"+cvformulaIndex+"' name='formula.webFormulaItemses["+(i+item_nums)+"].webFormula.formulaIndex'/></li>";
		});	
		jq("#ul_"+index).append(li_content);		
		jq("#del_img").css("display","block");
		jq("#tab_section_a_"+index).click();		
		item_nums+=list_items_val.length;		
	    checkIndex();
}



function removeSection(){
	jq("#tab_section_"+index).remove();
	jq("#div_section_"+index).remove();
	index--;
	jq("#btn_addsec").val("添加配方階段("+(index+1)+")");
	if(index==0){
		jq("#del_img").css("display","none");
		jq("#tab_webformaulaitems_a").click();
	}else{
		jq("#tab_section_a_"+index).click();
	}
	checkIndex();
}
function removeOneItem(img,img_name){
	    img.parent().prev().prev().prev().prev().remove();
	    img.parent().prev().prev().prev().remove();
	    img.parent().prev().prev().remove();
	    img.parent().prev().remove();
		img.parent().remove();
		if(jq("[name='"+img_name+"']").length==0){
			removeSection();
		}
	
	
}

function checkbtn(){
	var items=jq("input[name='itemids']:checked");
    var formulaIndex=jq("#formulaIndex");
	if(items!=null&&items.length>0&&formulaIndex.val()!=""){
		jq("#btn_addsec").removeClass("disabled");
	}else{
		jq("#btn_addsec").addClass("disabled");
	}
}

function checkIndex(){
	if(index>0){
		jq("#dwr_factno").attr("disabled","disabled");
		jq("#dwrFactArea").attr("disabled","disabled");
		
		jq("#dwr_factno").css("color","grey");
		jq("#dwrFactArea").css("color","grey");
		
	}else{
		jq("#dwr_factno").removeAttr("disabled");
		jq("#dwrFactArea").removeAttr("disabled");
		
		jq("#dwr_factno").css("color","");
		jq("#dwrFactArea").css("color","");					
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

 function addWebtabtom(){ 
    var formulaIndex=jq("#formulaIndex").val();  	    	
    jq.layer({
    type: 2,   //0-4的选择,
    title: '物性資料',
    //border: [0],
    border: [10, 0.3, '#000'],
    closeBtn: [1,true],
    shade: [0],
    //shade: [0.5, '#000'],
    shadeClose: false,
    // btns:2,
    // btn:['通過','不通過'],
    //fadeIn:300,
    //shift:'top',
    offset:['10px',''],
    area: ['750px', '600px'],
    maxmin:true,
    //page:{url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo},
    //iframe:{src:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo,scrolling:'auto'},   
    iframe:{src:'saveAndUpdate/WebTabpomSaveOrUpdate_layer.jsp?formulaIndex='+formulaIndex,scrolling:'auto'},
    
    yes:function(){},
    no:function(){}              
});
}







</script>
</body>
</html>
