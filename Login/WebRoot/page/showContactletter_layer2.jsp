
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
	width:800px;
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
    <s:if test="kyzletter!=null">
	<table class="gridtable" align="center">
		<caption style="font-size:30;font-weight:bold">
		     內部聯絡函申請 
		</caption>
		<tbody id="tb_list_info2">
			<tr>
				<td>標題</td>
				<td colspan="2"><s:property value='kyzletter.title' />
				</td>
				<td>受文者</td>
				<td colspan="2"><s:property value='kyzletter.toUser' />
				</td>
				<td>承辦人</td>
				<td colspan="3"><s:property value='kyzletter.userNm' />
				</td>
			</tr>




			<tr>
				<td>廠別</td>
				<td colspan="2"><s:property value='kyzletter.id.factNo' />
				</td>
				<td>廠別狀態</td>
				<td colspan="2"><s:property value='kyzletter.factCode' />
				</td>
				<td>類別</td>
				<td colspan="3">
				<s:if test='kyzletter.visaType=="F"'>
				  廠務簽核
				</s:if>
				<s:if test='kyzletter.visaType=="W"'>
				臺灣簽核
				</s:if>
				<s:if test='kyzletter.visaType=="G"'>
				  工程簽核
				</s:if>
				<s:if test='kyzletter.visaType=="I"'>
				IKT簽核
				</s:if>	
				<s:if test='kyzletter.visaType=="L"'>
				  實驗室簽核
				</s:if>
				<s:if test='kyzletter.visaType=="P"'>
				品管簽核
				</s:if>	
				<s:if test='kyzletter.visaType=="Q"'>
				  企劃簽核
				</s:if>
				<s:if test='kyzletter.visaType=="S"'>
				生管簽核
				</s:if>	
				<s:if test='kyzletter.visaType=="T"'>
				  整理簽核
				</s:if>
				<s:if test='kyzletter.visaType=="Y"'>
				油壓簽核
				</s:if>	
				<s:if test='kyzletter.visaType=="Z"'>
				  總務簽核
				</s:if>
				<s:if test='kyzletter.visaType=="B"'>
				  備料簽核
				</s:if>
				<s:if test='kyzletter.visaType=="O"'>
				  業務簽核
				</s:if>
				<s:if test='kyzletter.visaType.substring(0,2)=="C1"'>
				  其他費用簽核1(<1000元)
				</s:if>
				<s:if test='kyzletter.visaType.substring(0,2)=="C2"'>
				 其他費用簽核2(>=1000元)
				</s:if>
				<s:if test='kyzletter.visaType.substring(0,2)=="C3"'>
				  電腦耗材簽核1(<1000元)
				</s:if>
				<s:if test='kyzletter.visaType.substring(0,2)=="C4"'>
				  電腦耗材簽核2(>=1000元)
				</s:if>	
				<s:if test='kyzletter.visaType.substring(0,2)=="C5"'>
				  總務費用簽核1(<1000元)
				</s:if>
				<s:if test='kyzletter.visaType.substring(0,2)=="C6"'>
				  總務費用簽核2(>=1000元)
				</s:if>	(<s:property value="kyzletter.visaType" />)
				</td>
			</tr>
			<tr>
				<td>承辦單位</td>
				<td colspan="2" ><s:property value='kyzletter.secNo' />
				</td>
				<td>承辦主管</td>
				<td colspan="2"><s:property value='kyzletter.chargeNo' />
				</td>
				<td>承辦日期</td>
				<td colspan="3"><s:property value='kyzletter.ymExpect' /></td>
			</tr>
			<tr>
				<td>申請單號</td>
				<td colspan="9"><s:property value='kyzletter.id.billNo' /></td>
			</tr>
			<tr>
			  <td>CC(呈)</td>
			  <td colspan="11">
			     <textarea rows="2" cols="115" readonly><s:property value="kyzletter.chargeList" /></textarea>			     			     			    
			  </td>
			</tr>
			<tr>
				<td>申請內容</td>
				<td colspan="11">
				  <textarea rows="15" cols="115" readonly><s:property value="kyzletter.memoMk" /></textarea>				     				 					
				</td>

			</tr>
		</tbody>

	</table>
	 <s:if test='kyzletter.filesYn=="1"'>
	  <hr/>
	  <span style="color:blue;">附檔</span><br/>
	  <s:iterator value="#session.list_filesexp">
	     <a href="/upload_letter/<s:property value='billno'/>/<s:property value='%{toUrl2(filename)}'/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
	  </s:iterator>	  
	</s:if> 
	
</s:if>
<s:else>
  <br><br><br>
   <h1 style="color:red" align="center">內部聯絡函已刪除</h1>
</s:else>

</body>
</html>
