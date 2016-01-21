
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
	width:80px;
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
					(<s:property value="kyzletter.visaType" />)
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
			     <s:property value="kyzletter.chargeList" />		     			     			    
			  </td>
			</tr>
			<tr>
				<td>申請內容</td>
				<td colspan="11">
				  <div style="height:85px;width:100%;overflow:auto">
				  <s:property value="kyzletter.memoMk" />				  
				  </div>				     				 					
				</td>

			</tr>
			<s:iterator value="#session.vbm.kyVisabillses">
			   <s:if test='memo!=null&&memo!=""'>
			     <tr>
			       <td>
			          <s:property value="visaRank"/>:
			       </td>
			       <td colspan="11" style="color:blue">
			          <b><s:property value="memo"/></b>
			       </td>
			     </tr>
			   </s:if>
			</s:iterator>
			<!------------------------- 修改3   20151027---------------   -->
			<s:if test='readMk=="N"'>
			    <tr><td colspan="12" style="color:red">備註↓↓↓</td></tr>
				<tr><td colspan="12">									
					<form id="memo" method="post" action="vbm_add">
						<textarea  name="memo" id="memo_txt" style="width:540px;height:180px;overflow:auto"></textarea>						
						<input type="hidden" name="factNo" value="<s:property value='factNo'/>"/>
						<input type="hidden" name="billNo" value="<s:property value='billNo'/>"/>
						<input type="hidden" name="itemNo" value="<s:property value='itemNo'/>"/>
						<input type="hidden" name="visaSort" value="<s:property value="visaSort"/>"/>
						<input type="hidden" name="visa_mk"  id="visa_mk"/>
					</form>					
				</td></tr>
			</s:if>
			<!------------------------- 修改3   20151027---------------   -->	
		</tbody>
	</table>
	 <s:if test='kyzletter.filesYn=="1"'>
	  <hr/>
	  <span style="color:blue;">附檔:</span><br/>
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
