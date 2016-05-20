
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>加伟鞋材对公费用汇款清单細項</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body id="bodyid">
    <h3>加伟鞋材对公费用汇款清单細項</h3>
	<table class="gridtable" align="center">		
		<tbody id="tb_list_info2">						
			<tr>
			    <td>申請單號</td>
				<td colspan="2"><s:property value='webremit.billNo' />
				</td>
				<td>匯款帳號</td>
				<td colspan="2" ><s:property value='webremit.fromAccount' />
				</td>
				<td>銀行名稱</td>
				<td colspan="2"><s:property value='webremit.fromBank' />
				</td>				
			</tr>			
		</tbody>

		<tbody id="webremits_body">
			<tr> <td>操作</td>
				<td class="tdcolor">貨幣</td>
			     <td class="tdcolor">廠商名稱</td>
			     <td class="tdcolor">收款銀行</td>
			     <td class="tdcolor">授權委託帳號</td>
			     <td class="tdcolor">貨款金額</td>
			     <td class="tdcolor">匯費</td>
			     <td class="tdcolor">實匯金額</td>		     
			     <td class="tdcolor">備註</td>
			</tr>

			<s:iterator value="webremit.webremittancelistses" status="x" id="temp">
				<tr>
				 <td><input type="button" value="刪除" onclick="del('${webremit.billNo}','${temp.id.itemNo}')"</td>
				 <td ><s:property value='currency'/></td>				 	     
			     <td ><s:property value='Manufacturers'/></td>
			     <td ><s:property value='toBank'/></td>
			     <td ><s:property value='toAccount'/></td>
			     <td ><s:property value='payment'/></td>
			     <td ><s:property value='cost'/></td>
			     <td ><s:property value='acAmount'/></td>
			     <td><s:property value='remark'/></td>				     			     			     
				</tr>
			</s:iterator>			
			</tbody>		
	</table>	


</body>
</html>
