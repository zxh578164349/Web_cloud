
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.net.URLEncoder"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyyMMdd");
	java.util.Date currentTime = new java.util.Date();//得到当前系统时间
	String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'addBackMat.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" /> -->


<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 14px;
	color: #333333;
	border-width: 1px;
	border-style: solid;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	/* padding: 8px; */
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	width: 70px;
}
</style>
<script type="text/javascript">

</script>

</head>

<body>
    <s:if test="kyz!=null">
	<table class="gridtable" align="center">
		<caption style="font-size:30;font-weight:bold">
			函文申請<br>
			<br>
		</caption>
		<tbody id="tb_list_info2">
			<tr>
				<td>標題</td>
				<td colspan="2"><s:property value='kyz.memoSmk' />
				</td>
				<td>電話</td>
				<td colspan="2"><s:property value='kyz.telNo' />
				</td>
				<td>申請單位</td>
				<td colspan="3"><s:property value='kyz.secNo' />
				</td>
			</tr>




			<tr>
				<td>廠別</td>
				<td colspan="2"><s:property value='kyz.factNo2.factSname' />
				</td>
				<td>廠別狀態</td>
				<td colspan="2"><s:property value='kyz.factCode' />
				</td>
				<td>申請日期</td>
				<td colspan="3"><s:date name="kyz.timeCreate" format="yyyyMMdd" /></td>
			</tr>
			<tr>
				<td>申請單號</td>
				<td colspan="2" id="billno"><s:property value='kyz.id.billNo' />
				</td>
				<td>申請者</td>
				<td colspan="2"><s:property value='kyz.userNm' />
				</td>
				<td>是否緊急</td>
				<td colspan="3"><s:if test="kyz.emerWhether==0">
						             是
						   </s:if> <s:if test="kyz.emerWhether==1">
						             否
						   </s:if></td>
			</tr>
			<tr>
				<td>類別</td>
				<td colspan="9">
				<!--  <s:if test='kyz.visaType=="F"'>
				  廠務簽核
				</s:if>
				<s:if test='kyz.visaType=="W"'>
				臺灣簽核
				</s:if>
				<s:if test='kyz.visaType=="G"'>
				  工程簽核
				</s:if>
				<s:if test='kyz.visaType=="I"'>
				IKT簽核
				</s:if>	
				<s:if test='kyz.visaType=="L"'>
				  實驗室簽核
				</s:if>
				<s:if test='kyz.visaType=="P"'>
				品管簽核
				</s:if>	
				<s:if test='kyz.visaType=="Q"'>
				  企劃簽核
				</s:if>
				<s:if test='kyz.visaType=="S"'>
				生管簽核
				</s:if>	
				<s:if test='kyz.visaType=="T"'>
				  整理簽核
				</s:if>
				<s:if test='kyz.visaType=="Y"'>
				油壓簽核
				</s:if>	
				<s:if test='kyz.visaType=="Z"'>
				  總務簽核
				</s:if>
				<s:if test='kyz.visaType=="B"'>
				  備料簽核
				</s:if>
				<s:if test='kyz.visaType=="O"'>
				  業務簽核
				</s:if>
				<s:if test='kyz.visaType.substring(0,2)=="C1"'>
				  其他費用簽核1(<1000元)
				</s:if>
				<s:if test='kyz.visaType.substring(0,2)=="C2"'>
				 其他費用簽核2(>=1000元)
				</s:if>
				<s:if test='kyz.visaType.substring(0,2)=="C3"'>
				  電腦耗材簽核1(<1000元)
				</s:if>
				<s:if test='kyz.visaType.substring(0,2)=="C4"'>
				  電腦耗材簽核2(>=1000元)
				</s:if>	
				<s:if test='kyz.visaType.substring(0,2)=="C5"'>
				  總務費用簽核1(<1000元)
				</s:if>
				<s:if test='kyz.visaType.substring(0,2)=="C6"'>
				  總務費用簽核2(>=1000元)
				</s:if> -->
				<s:property value="kyz.visaType" />
				</td>
			</tr>
			<tr>
				<td>申請內容</td>
				<td colspan="11">
					<div style="height:85px;overflow:auto">
						<s:property value="kyz.memoMk" />
					</div></td>

			</tr>
		</tbody>

		<tbody id="kyzs_body">
			<tr>
				<td>名稱</td>
				<td>項次</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>合計</td>
				<td>使用人數</td>
				<td>單位</td>
				<td>幣種</td>
				<td>備註</td>
			</tr>



			<s:iterator value="kyz.kyzExpectmatses" status="x" id="temp">
				<tr>

					<td><s:property value='itemNm' />
					</td>
					<td><s:property value='id.itemNo' />
					</td>
					<td><s:property value='matNo' />
					</td>
					<td><s:property value='%{formatDouble2(qtyExpect)}' />
					</td>
					<td><s:property value="%{formatDouble(qtyOk)}" />
					</td>
					<td><s:property value='%{formatDouble2(qtyExpect*qtyOk)}' />
					</td>
					<td><s:property value="%{formatDouble(personNo)}" />
					</td>
					<td><s:property value='qtyPair' />
					</td>
					<td><s:property value='moneyType' />
					</td>
					<td><s:property value='memoMk' />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	 <s:if test='kyz.filesYn=="1"'>
	  <hr/>
	  <span style="color:blue;">附檔:</span><br/>
	  <s:iterator value="#session.list_filesexp">
	     <a href="/upload/<s:property value='billno'/>/<s:property value='%{toUrl2(filename)}'/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
	  </s:iterator>	  
	</s:if> 
	
</s:if>
<s:else>
  <br><br><br>
   <h1 style="color:red" align="center">函文已刪除</h1>
</s:else>

</body>
</html>
