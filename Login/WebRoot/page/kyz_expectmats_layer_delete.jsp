
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
 function del(factNo,billNo,itemNo){
	 $.ajax({
		 type:"POST",
		 dataType:"html",
		 data:"factNo="+factNo+"&billNo="+billNo+"&itemNo="+itemNo,
		 url:"kyz_delete_lists",
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
    <h3>函文申請細項</h3>
	<table class="gridtable" align="center">		
		<tbody id="tb_list_info2">						
			<tr>
			    <td>申請單號</td>
				<td colspan="2"><s:property value='kyz.id.billNo' />
				</td>
				<td>標題</td>
				<td colspan="2" ><s:property value='kyz.memoSmk' />
				</td>
				<td>申請人</td>
				<td colspan="2"><s:property value='kyz.userNm' />
				</td>				
			</tr>			
		</tbody>

		<tbody id="kyzs_body">
			<tr> <td>操作</td>
				<td class="tdcolor">名稱</td>
			     <td class="tdcolor">規格</td>
			     <td class="tdcolor">單價</td>
			     <td class="tdcolor">數量</td>
			     <td class="tdcolor">使用人數</td>
			     <td class="tdcolor">單位</td>
			     <td class="tdcolor">幣種</td>		     
			     <td class="tdcolor">備註</td>
			</tr>

			<s:iterator value="kyz.kyzExpectmatses" status="x" id="temp">
				<tr>
				 <td><input type="button" value="刪除" onclick="del('${kyz.id.factNo}','${kyz.id.billNo}','${temp.id.itemNo}')"/></td>
				 <td ><s:property value='itemNm'/></td>				 	     
			     <td ><s:property value='matNo'/></td>
			     <td ><s:property value='qtyExpect'/></td>
			     <td ><s:property value='qtyOk'/></td>
			     <td ><s:property value='personNo'/></td>
			     <td ><s:property value='qtyPair'/></td>
			     <td ><s:property value='moneyType'/></td>
			     <td><s:property value='memoMk'/></td>				     			     			     
				</tr>
			</s:iterator>			
			</tbody>		
	</table>	


</body>
</html>
