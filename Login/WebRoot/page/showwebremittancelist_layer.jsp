
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'addBackMat.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

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

</head>

<body>
    <h3>加伟鞋材对公费用汇款清单 </h3>
    <s:if test="webremit!=null">
	<table class="gridtable" align="center">		
		<tbody id="tb_list_info2">
			<tr>
				<td>廠別</td>
				<td colspan="2"><s:property value='webremit.webtype.id.factNo' />
				</td>
				<td>廠別狀態</td>
				<td colspan="2"><s:property value='webremit.factCode' />
				</td>
				<td>日期</td>
				<td ><s:property value='webremit.yymm' />
				</td>
			</tr>

			<tr>
				<td>申請單號</td>
				<td colspan="2"><s:property value='webremit.billNo' />
				</td>
				<td>申請人</td>
				<td colspan="2"><s:property value='webremit.username' />
				</td>
				<td>類別</td>
				<td ><s:property value="webremit.webtype.typeName"  /></td>
			</tr>
			<tr>
				<td>匯款帳號</td>
				<td colspan="2" ><s:property value='webremit.fromAccount' />
				</td>
				<td>銀行名稱</td>
				<td colspan="4"><s:property value='webremit.fromBank' />
				</td>				
			</tr>			
		</tbody>

		<tbody id="webremits_body">
			<tr>
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
			<!-- ---------------------顯示所有人的備註信息20151211----------------------------- -->
			<s:iterator value="#session.vbm.kyVisabillses">
			   <s:if test='memo!=null&&visaRank!=""'>			      
			      <tr>
			         <td>
			            <s:property value="visaRank"/>:
			         </td>
			         <td colspan="7" style="color:blue">
			            <b><s:property value="memo"/></b>
			         </td>
			      </tr>
			   </s:if>
			</s:iterator>
			<!-- ---------------------顯示所有人的備註信息20151211----------------------------- -->
			<!------------------------- 修改3   20151027---------------   -->
			<s:if test='readMk=="N"'>
			    <tr><td colspan="8" style="color:red">備註↓↓↓</td></tr>
				<tr><td colspan="8">									
					<form id="memo" method="post" action="vbm_add" target="frameFile">
						<textarea  name="memo" id="memo_txt" style="width:620px;height:120px;overflow:auto"></textarea>						
						<input type="hidden" name="factNo" value="<s:property value='factNo'/>"/>
						<input type="hidden" name="billNo" value="<s:property value='billNo'/>"/>
						<input type="hidden" name="itemNo" value="<s:property value='itemNo'/>"/>
						<input type="hidden" name="visaSort" value="<s:property value="visaSort"/>"/>
						<input type="hidden" name="visa_mk"  id="visa_mk"/>
					</form>
					<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>					
				</td></tr>
			</s:if>
			<!------------------------- 修改3   20151027---------------   -->	
			</tbody>		
	</table>
	 <!--<s:if test='webremit.filesYn=="1"'>
	  <hr/>
	  <span style="color:blue;">附檔:</span><br/>
	  <s:iterator value="#session.list_filesexp">
	     <a href="/upload/<s:property value='billno'/>/<s:property value='%{toUrl2(filename)}'/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
	  </s:iterator>	  
	</s:if> -->
	
</s:if>
<s:else>
  <br><br><br>
   <h1 style="color:red" align="center">函文已刪除</h1>
</s:else>

</body>
</html>
