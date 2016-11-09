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
    width:450px;height:300px;overflow:auto;border:1px dashed green;text-align:left
  }
  .div_border_blue{
    width:1100px;height:300px;overflow:auto;border:1px dashed blue;text-align:left
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
		<table class="table table-condensed" id="tb_main">
			<tbody id="tb_list_info2">
				<s:if test="formula==null">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input">
						    <s:if test="#session.factNo!='tw'">
								<input type="text"  name="formula.factNo.factNo"
									value="${factNo}" readonly id="dwr_factno" />
							</s:if> 
							<s:if test="#session.factNo=='tw'">
								<select  name="formula.factNo.factNo"
									onchange="makeFormulaIndex()" datatype="*"
									id="dwr_factno">									
								</select>
							</s:if>
						</td>
						<td class="td_show_title">配方索引</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="formula.formulaIndex" readonly="readonly" datatype="*" id="formulaIndex"/>
						</td>
					</tr>
					<tr>
						<td class="td_show_title">製程類別</td>
						<td class="td_input">
						<select name="factCode" 
							datatype="*" id="dwrFactArea" onchange="makeFormulaIndex()">								
						</select> </td>
						<td class="td_show_title">配方編號</td>
						<td class="td_input"><input type="text"
							name="formula.formulaNo"
							value="<s:property value='formula.formulaNo' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>
				<s:if test="formula!=null">
					<input type="hidden" value="1" name="bs" />
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="factNo" style="color:blue"
								value="<s:property value='formula.factNo.factNo'/>"
								name="formula.factNo.factNo" readonly /> </font></td>
						<td class="td_show_title">配方索引</td>
						<td class="td_input"><input type="text" style="color:blue"
							value="<s:property value='formula.formulaIndex' />"
							name="formula.formulaIndex" readonly /></td>
					</tr>
					<tr>
						<td class="td_show_title">製程類別</td>
						<td class="td_input"><input type="text"
							value="<s:property value='formula.factCode.id'/>"
							name="formula.factCode.id" style="color:blue" readonly /></td>
						<td class="td_show_title">配方編號</td>
						<td class="td_input"><input type="text"
							name="formula.formulaNo"
							value="<s:property value='formula.formulaNo' />" datatype="*0-6">
							<input type="hidden" value="${loginUser.username}"
							name="formula.modifyName" /> <!--  修改者--> <input type="hidden"
							value="<%=str_date%>" name="formula.modifyDate" /> <!--  修改日期-->
						</td>
					</tr>
				</s:if>

				<tr>
					<td class="td_show_title">配方名稱</td>
					<td class="td_input"><input type="text"
						name="formula.formulaName"
						value="<s:property value='formula.formulaName' />" datatype="*0-6">
					</td>
					<td class="td_show_title">倍率</td>
					<td class="td_input"><input type="text"
						name="formula.magnification"
						value="<s:property value='formula.magnification' />"
						datatype="*0-6"></td>
				</tr>
				<tr>
					<td class="td_show_title">帶皮半成品硬度</td>
					<td class="td_input"><input type="text"
						name="formula.semifinishedProductHardness"
						value="<s:property value='formula.semifinishedProductHardness' />"
						datatype="*0-6"></td>
					<td class="td_show_title">成品硬度</td>
					<td class="td_input"><input type="text"
						name="formula.productHardness"
						value="<s:property value='formula.productHardness' />"
						datatype="*0-6"></td>
				</tr>
				<tr>
					<td class="td_show_title">品牌形體</td>
					<td class="td_input"><input type="text"
						name="formula.brandBody"
						value="<s:property value='formula.brandBody' />" datatype="*0-6">
					</td>
					<td class="td_show_title">顏色</td>
					<td class="td_input"><input type="text" name="formula.color"
						value="<s:property value='formula.color' />" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">發行日期</td>
					<td class="td_input"><input type="text"
						name="formula.issuedDate"
						value="<s:property value='formula.issuedDate' />" datatype="*0-6" />
					</td>
					<td class="td_show_title">品牌指定</td>
					<td class="td_input">
					<s:if test="formula==null">
					     指     定<input type="radio" name="formula.assignBrand" value="1"/>
					     非指定<input type="radio" name="formula.assignBrand" value="0" checked/>
					</s:if>
					<s:else>
					   <s:if test='formula.assignBrand=="0"'>
					             指     定<input type="radio" name="formula.assignBrand" value="1"/>
					             非指定<input type="radio" name="formula.assignBrand" value="0" checked/>
					   </s:if>
					   <s:else>
					             指     定<input type="radio" name="formula.assignBrand" value="1" checked/>
					             非指定<input type="radio" name="formula.assignBrand" value="0" />
					   </s:else>
					</s:else>					 					
					</td>

				</tr>				
				<tr>
				   <td colspan="4" >
							<div id="div_webformalaitem">
								<ul id="myTab" class="nav nav-tabs">
									<li class="active"><a href="#tab_typeno"
										data-toggle="tab" id="tab_typeno_a">配方類別</a>
									</li>
									<li><a href="#tab_namece" data-toggle="tab" id="tab_namece_a">配方原料</a>
									</li>
									<li id="del_li"><img src="images/icon/del_file.png" style="display:none" id="del_img" onclick="removeSection()"/></li>
								</ul>
								
								
								
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active" id="tab_typeno">
										<div id="div_typeno"
											class="div_border_green">
											<div>
												<input type="checkbox" id="all_typeno"
													onclick="" />
												全选
											</div>
											<hr />
											
										</div>
									</div>
									<div class="tab-pane fade" id="tab_namece">
										<div
											class="div_border_green">
											<div>
												<input type="checkbox" id="all_namece"
													onclick="" />全选
											</div>
											<hr />										
											<div id="div_namece"><label style="color:red">請先選擇配方類別</label></div>
										</div>
									</div>
								</div>
							</div>
							<input type="button" value="添加配方階段" onclick="addSection()" class="btn disabled" id="btn_addsec" />								
						</td>
				</tr>
			</tbody>			
		</table>
		

		
		
		<table class="table table-condensed">
		   <tr>
					<td class="td_show_title">備註</td>
					<td colspan="3"><textarea style="width:780px;height:240px"><s:property value="formula.remark" /></textarea> 					
						<input type="hidden"
						value="<s:property value='#session.loginUser.username'/>"
						name="formula.createName" /> <input type="hidden"
						value="<%=str_date%>" name="formula.createDate" id="createDate"/></td>
				</tr>
		</table>
		
		<center>
			<input type="button" id="sub" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;
			<input type="reset" id="reset" value="重置" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" id="btn_back"
				onclick="javascript:back()" class="btn btn-primary" />
		</center>
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
					item="<option value='"+obj[3]+"'>"+obj[1]+"("+obj[3]+")</option>";
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
					item+="<div><input type='checkbox' value='"+obj.itemcategory+"' name='typenos' onclick='loadNamece(),checkbtn()'/><label>"+obj.itemcategoryname+"</label></div>";					
				});
				jq("#div_typeno").append(item);
			}
		});
	});
	
	function makeFormulaIndex(){      
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
	var itemcategorys=new Array();
	var list=jq("input[name='typenos']:checked");
	list.each(function(i,typeno){
		itemcategorys.push(typeno.value);
	});
	if(itemcategorys.length>0){
		jq.ajax({
			type:"post",
			dataType:"json",
			traditional:true,
			data:{'itemcategorys':itemcategorys},
			url:"weberppf_findNameces",
			success:function(data){
				var item="";
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
function addSection(){
	var cvformulaIndex=jq("#formulaIndex").val();
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
	
	var list_items_val=jq("input[name='itemids']:checked");
	var li_content="";
	list_items_val.each(function(i){
		var val=jq(this).val();
		var txt1=jq(this).next().text().split("__")[1];
        var txt2=jq(this).next().text().split("__")[0]
		li_content+="<li>"+txt1+"</li><li>"+txt2+"</li><li><input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].phrVal'/></li>"+
		"<li><input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].weightVal'/></li><li >"+
		"<input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].remark'/>"+
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
	}
	jq("#tab_section_a_"+index).click();
	checkIndex();
}

function checkbtn(){
	var items=jq("input[name='itemids']:checked");
	if(items!=null&&items.length>0){
		jq("#btn_addsec").removeClass("disabled");
	}else{
		jq("#btn_addsec").addClass("disabled");
	}
}

function checkIndex(){
	if(index>0){
		jq("#dwr_factno").attr("disabled","disabled");
		jq("#dwrFactArea").attr("disabled","disabled");
	}else{
		jq("#dwr_factno").removeAttr("disabled");
		jq("#dwrFactArea").removeAttr("disabled");
	}
}

</script>
</body>
</html>
