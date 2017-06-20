
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>加伟鞋材对公费用汇款清单細項</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
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
	width:60px;
}
</style>
<script type="text/javascript">

$(document).ajaxStart(function(){
	loadi=layer.load();
});
$(document).ajaxStop(function(){
	layer.close(loadi);
});
 function del(billNo,itemNo){
	 $.ajax({
		 type:"POST",
		 dataType:"html",
		 data:"billNo="+billNo+"&itemNo="+itemNo,
		 url:"webremit_delete_list",
		 success:function(data){
			 $("#bodyid").html(data);
		 },
		 error:function(error){
			 $("#bodyid").html(error.responseText);
		 }
	 });
 }
</script>

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
				 <td><input type="button" value="刪除" onclick="del('${webremit.billNo}','${temp.id.itemNo}')"/></td>
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
