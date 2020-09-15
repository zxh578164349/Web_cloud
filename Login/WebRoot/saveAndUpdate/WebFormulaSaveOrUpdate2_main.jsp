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
						<table class="table table-condensed">
							<tbody>
								<tr>
									<th>廠別</th>
									<th>配方索引</th>
								</tr>
								<tr>
									<td><input type="text" id="factNo" style="color:blue" value="<s:property value='formula.factNo.factNo'/>"  name="formula.factNo.factNo" readonly /></td>
									<td><input type="text" style="color:blue" value="<s:property value='formula.formulaIndex' />"  name="formula.formulaIndex" id="formulaIndex" readonly />
									</td>
								</tr>
								<tr>
									<th>製程類別</th>
									<th>配方編號</th>
								</tr>
								<tr>
									<td>
									<input type="text" value="<s:property value='formula.factCode.sysno'/>"  style="color:blue" readonly />
									<input type="hidden" value="<s:property value='formula.factCode.id'/>" name="formula.factCode.id" style="color:blue" readonly />
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
									<td><input type="text" name="formula.semifinishedProductHardness" value="<s:property value='formula.semifinishedProductHardness' />" datatype="*0-30">
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
								<tr>
									<th colspan="2">備註</th>
								</tr>
								<tr>
									<td colspan="2"><textarea style="width:100%;height:200px" name='formula.remark' datatype="*0-200"><s:property value="formula.remark"/></textarea>
									 <input type="hidden" value="<s:property value='formula.createName'/>" name="formula.createName" /> 
									 <input type="hidden" value="<s:property value='formula.createDate'/>" name="formula.createDate" id="createDate" />
									 <input type="hidden" value="${loginUser.username}" name="formula.modifyName" /> <!--  修改者--> 
									 <input type="hidden" value="<%=str_date%>" name="formula.modifyDate" /> <!--  修改日期-->
									 <input type="hidden" value="<s:property value='formula.visaSort'/>" name="formula.visaSort">
									 <input type="hidden" value="<s:property value='formula.userId'/>" name="formula.userId" />
									 <input type="hidden" value="<s:property value='formula.useremail'/>" name="formula.useremail" />
									 </td>
								</tr>
							</tbody>
						</table>
						<br>
						<center>
			              <input type="button" id="sub_main" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;			              
			              <input type="button" value="返回"  onclick="javascript:back()" class="btn btn-primary" />			            
		                </center>																								     
	</form>


<script type="text/javascript">

	jq(function() {
		var demo = jq("#form_main").Validform({
			btnSubmit : "#sub_main",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*8-4" : /^-?\d{0,8}(\.[0-9]{0,4})?$/
			},
			ajaxPost:true,			
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

	});	
</script>
</body>
</html>
