<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyyMMdd-hh");
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
	<table class="table table-condensed table-bordered">
		<tbody>
			<tr>
				<th>廠別</th>
				<th>配方索引</th>
			</tr>
			<tr>
				<td><s:property value='formula.factNo.factNo' />
				</td>
				<td><s:property value='formula.formulaIndex' />
				</td>
			</tr>
			<tr>
				<th>製程類別</th>
				<th>配方編號</th>
			</tr>
			<tr>
				<td><s:property value='formula.factCode.id' /></td>
				<td><s:property value='formula.formulaNo' /></td>
			</tr>
			<tr>
				<th>配方名稱</th>
				<th>倍率</th>
			</tr>
			<tr>
				<td><s:property value='formula.formulaName' /></td>
				<td><s:property value='formula.magnification' /></td>
			</tr>
			<tr>
				<th>帶皮半成品硬度</th>
				<th>成品硬度</th>
			</tr>
			<tr>
				<td><s:property value='formula.semifinishedProductHardness' />
				</td>
				<td><s:property value='formula.productHardness' /></td>
			</tr>
			<tr>
				<th>品牌形體</th>
				<th>顏色</th>
			</tr>
			<tr>
				<td><s:property value='formula.brandBody' /></td>
				<td><s:property value='formula.color' /></td>
			</tr>
			<tr>
				<th>發行日期</th>
				<th>品牌指定</th>
			</tr>
			<tr>
				<td><s:property value='formula.issuedDate' /></td>
				<td><s:if test='formula.assignBrand=="0"'>					                                                          
					                                                            非指定
										</s:if> <s:else>
					                                                             指     定					                                                             
										</s:else></td>
			</tr>
			<tr>
				<th colspan="2">備註</th>
			</tr>
			<tr>
				<td colspan="2"><textarea style="width:100%;height:200px"
						readonly>
						<s:property value="formula.remark" />
					</textarea></td>
			</tr>


			<s:iterator value="formula.vbm.kyVisabillses">
				<s:if test='memo!=null&&memo!=""'>
					<tr>
						<td><s:property value="visaRank" />:</td>
						<td colspan="11" style="color:blue"><b><s:property
									value="memo" />
						</b></td>
					</tr>
				</s:if>
			</s:iterator>
			
			<s:if test='readMk=="N"'>
				<tr>
					<td colspan="12" style="color:red">簽核備註↓↓↓</td>
				</tr>
				<tr>
					<td colspan="12">
						<form id="memo" method="post" action="vbm_add" target="frameFile">
							<textarea name="memo" id="memo_txt" style="width:100%;height:120px;overflow:auto"></textarea>								
							<input type="hidden" name="factNo" value="<s:property value='factNo'/>" /> 
							<input type="hidden" name="billNo" value="<s:property value='billNo'/>" />
							<input type="hidden" name="itemNo" value="<s:property value='itemNo'/>" />								
							<input type="hidden" name="visaSort" value="<s:property value="visaSort"/>" />
							<input type="hidden" name="visa_mk" id="visa_mk" />								
						</form> 
						<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>
					</td>
				</tr>
			</s:if>
		</tbody>
	</table>     
</body>
</html>
