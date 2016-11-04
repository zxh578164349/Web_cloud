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
</head>

<body>
	<form action="webformula_add" method="post" id="form">
		<table class="table table-condensed">
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
					<td>備註</td>
					<td><textarea style="width:500px;height:240px"><s:property value="formula.remark" /></textarea> 					
						<input type="hidden"
						value="<s:property value='#session.loginUser.username'/>"
						name="formula.createName" /> <input type="hidden"
						value="<%=str_date%>" name="formula.createDate" id="createDate"/></td>
				</tr>
			</tbody>
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
					loadUrl("/Login/webformula_findPageBean3");
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
</script>
</body>
</html>
