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

</head>

<body>
	<form action="webformula_add" method="post" id="form_main">	
	   <div class="panel panel-default">
	     <div class="panel-heading"><label>配方表頭</label></div>
	       <div class="panel-body">	  
						<table class="table table-condensed">
							<tbody>
								<tr>
									<th>廠別</th>
									<th>配方索引</th>
								</tr>							
								    <tr>
									<td>							
									    <s:if test="#session.factNo!='tw'">
									      <input type="text" name="formula.factNo.factNo" value="${loginUser.factno}__${loginUser.erpfactno}" readonly id="dwr_factno" />
								        </s:if> 
								        <s:if test="#session.factNo=='tw'">
									      <select name="formula.factNo.factNo" onchange="makeFormulaIndex()" datatype="*" id="dwr_factno">
									      </select>
								        </s:if>																								
									</td>
									<td>
									<input type="text" style="color:blue" name="formula.formulaIndex" readonly="readonly" datatype="*" id="formulaIndex" placeholder="自動生成"/>
									<input type="hidden" value="${loginUser.username}" name="formula.createName" /> 
									 <input type="hidden" value="<%=str_date%>" name="formula.createDate" id="createDate" />
									 <input type="hidden" name="isnull" value="isnull" />
									 <input type="hidden" name="formula.visaSort" value="PF"/>
									 <input type="hidden" value="${loginUser.id}" name="formula.userId.id" />
									 <input type="hidden" value="${loginUser.email}" name="formula.useremail" /> 									
									</td>
								</tr> 
								
														
								<tr>
									<th>製程類別</th>
									<th>配方編號</th>
								</tr>
								<tr>
									<td>
									<s:if test="formula==null">
									  <select name="factCode" datatype="*" id="dwrFactArea" onchange="makeFormulaIndex()"></select>
									</s:if>
									<s:else>
									  <input type="text" value="<s:property value='formula.factCode.id'/>" name="formula.factCode.id" style="color:blue" readonly />
									</s:else>
									</td>
									<td><input type="text" name="formula.formulaNo" value="<s:property value='formula.formulaNo' />" datatype="*1-30">
									</td>
								</tr>
								<tr>
									<th>配方名稱</th>
									<th>倍率</th>
								</tr>
								<tr>
									<td><input type="text" name="formula.formulaName" value="<s:property value='formula.formulaName' />" datatype="*1-50">
									</td>
									<td><input type="text" name="formula.magnification" value="<s:property value='formula.magnification' />" datatype="*8-4">
									</td>
								</tr>
								<tr>
									<th>帶皮半成品硬度</th>
									<th>成品硬度</th>
								</tr>
								<tr>
									<td>
									<input type="text" name="formula.semifinishedProductHardness" value="<s:property value='formula.semifinishedProductHardness' />" datatype="*0-30">
									</td>
									<td><input type="text" name="formula.productHardness" value="<s:property value='formula.productHardness' />" datatype="*0-30">
									</td>
								</tr>
								<tr>
									<th>品牌形體</th>
									<th>顏色</th>
								</tr>
								<tr>
									<td><input type="text" name="formula.brandBody" value="<s:property value='formula.brandBody' />" datatype="*0-30">
									</td>
									<td><input type="text" name="formula.color" value="<s:property value='formula.color' />" datatype="*0-30">
									</td>
								</tr>
								<tr>
									<th>發行日期</th>
									<th>品牌指定</th>
								</tr>
								<tr>
									<td><input type="text" name="formula.issuedDate" value="<s:property value='formula.issuedDate' />" onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'})"
										datatype="*0-30" class="Wdate" />
									</td>
									<td><s:if test='formula.assignBrand=="0"'>
					                                                            指     定<input type="radio" name="formula.assignBrand" value="1" datatype="*"/>
					                                                            非指定<input type="radio" name="formula.assignBrand" value="0" checked />
										</s:if> 
										<s:else>
					                                                             指     定<input type="radio" name="formula.assignBrand" value="1" checked  datatype="*"/>
					                                                              非指定<input type="radio" name="formula.assignBrand" value="0" />
										</s:else>
									</td>
								</tr>								
							</tbody>
						</table>
					</div>
		</div>
		<div class="panel panel-default" id="div_webformalaitem"
			style="display:none">
			<div class="panel-heading">
				<label>配方階段</label>&nbsp;&nbsp;&nbsp;
				 <input type="button" value="添加配方階段"
					onclick="check_addSection()" class="btn btn-primary disabled"
					id="btn_addsec" />
			</div>
			<div class="panel-body">
				<ul id="myTab_item" class="nav nav-tabs">
					<li class="active"><a href="#tab_typeno" data-toggle="tab"
						id="tab_typeno_a">配方類別</a></li>
					<li><a href="#tab_namece" data-toggle="tab" id="tab_namece_a">配方原料</a>
					</li>
					<li id="del_li"><img src="images/icon/del_file.png"
						style="display:none" id="del_img" onclick="removeSection()" />
					</li>
				</ul>



				<div id="myTabContent_item" class="tab-content">
					<div class="tab-pane fade in active" id="tab_typeno">
						<div id="div_typeno" class="div_border_green">
							<div>
								<!-- <input type="checkbox" id="all_typeno" onclick="" /> 全选 -->
							</div>
							<hr />

						</div>
					</div>
					<div class="tab-pane fade" id="tab_namece">
						<div class="div_border_green">
							<div id="div_namece">
								<label style="color:red">請先選擇配方類別</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
        <div class="panel panel-default" id="div_remark">
          <div class="panel-heading"><label>配方備註</label></div>
          <div class="panel-body">
               <textarea style="width:100%;height:150px" name='formula.remark' datatype="*0-200"><s:property value="formula.remark"/></textarea>                  
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
		        jq("#dwrFactArea").removeAttr("disabled");
			},
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("webformula_findPageBean");
					//loadUrl("webformula_findById?formulaIndex="+jq("#formulaIndex").val());
				}
				if(data=="1"){
					layer.msg("提交失敗",3,3);
				}
				if(data=="3"){
				    layer.msg("保存附檔失敗",3,3);
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
					item+="<option value='"+obj[0]+"__"+obj[3]+"'>"+obj[1]+"("+obj[3]+")</option>";					
				});
				jq("#dwr_factno").append(item);
			}
		});
		
		jq.ajax({
			type:"post",
			dataType:"json",
			url:"weberpbp_findObjOp1",
			success:function(data){
				var item="";
				jq("#dwrFactArea").empty();
				jq("#dwrFactArea").append("<option value=''>請選擇製程類別</option>");
				jq.each(data,function(i,obj){
					item+="<option value='"+obj[0]+"__"+obj[1]+"'>"+obj[2]+"</option>";					
				});
				jq("#dwrFactArea").append(item);
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
					jq("#formulaIndex2").val(data);
					jq("#div_webformalaitem").css("display","block");
					jq("#li_webtabpom").css("display","block");
					checkbtn();
				},
				error:function(){
					layer.msg("生成配方索引失敗",3,3);
					jq("#div_webformalaitem").css("display","none");
					jq("#btn_addsec").addClass("disabled");	
					jq("#li_webtabpom").css("display","none");
				}
			});
		}		
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
					item+="<div><input type='checkbox' value='"+obj[0]+"' name='itemids' onclick='checkbtn()'/><label>"+obj[2]+"&nbsp;&nbsp;"+obj[3]+"__("+obj[1]+")</label></div>";					
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
	if(index>1){
		layer.msg("達到配方階段上限",3,3);
	}else{		
		var list_items_val=jq("input[name='itemids']:checked");
		if(list_items_val.length>100){
			layer.msg("所選配方原料超過上限",3,3);
		}else{
			addSection(list_items_val);
		}
	}	
}
function addSection(list_items_val){
	var cvformulaIndex=jq("#formulaIndex").val();
	//var list_items_val=jq("input[name='itemids']:checked");	
	index++;
	jq("#btn_addsec").val("添加配方階段("+(index+1)+")");
	var section="<li id='tab_section_"+index+"'><a href='#div_section_"+index+"' data-toggle='tab' id='tab_section_a_"+index+"'>配方階段("+index+")</a></li>";	
	var div_section="<div id='div_section_"+index+"' class='tab-pane fade div_border_blue'></div>";
	var ul="<ul class='list_item' id='ul_"+index+"'></ul>";
	var li_head="<li class='columnhead'>類別</li><li class='columnhead2'>原料名稱</li><li class='columnhead'>PHR</li>"+
		"<li class='columnhead'>重量(KG)</li><li class='columnhead2'>備註</li>";
		
	jq("#del_li").before(section);
	jq("#myTabContent_item").append(div_section);
	jq("#div_section_"+index).append(ul);
	jq("#ul_"+index).append(li_head);
	
	
	var li_content="";	
	list_items_val.each(function(i){
			var val=jq(this).val();
			var txt1=jq(this).next().text().split("__")[1].replace("(","").replace(")","");
	        var txt2=jq(this).next().text().split("__")[0];
			li_content+="<li>"+txt1+"</li><li class='column2'>"+txt2+"</li>"+
			"<li><input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].phrVal' datatype='*9-5'/></li>"+
			"<li><input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].weightVal' datatype='*9-5'/></li>"+
			"<li class='column2'><input type='text' name='formula.webFormulaItemses["+(i+item_nums)+"].remark' datatype='*0-200'/>"+
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
		jq("#tab_namece_a").click();
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
	
</script>
</body>
</html>
