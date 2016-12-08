
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
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <h3>人員出差申請書 </h3>
    <s:if test="bussletter!=null">
	<table class="table table-condensed table-bordered">		
		<tbody id="tb_list_info2">
			<tr>
				<td>申請單號</td>
				<td colspan="2"><s:property value='bussletter.blNo' />
				</td>
				<td>所屬單位</td>
				<td colspan="2"><s:property value='bussletter.unit' />
				</td>
				<td>姓名</td>
				<td colspan="3"><s:property value='bussletter.username' />
				</td>
			</tr>




			<tr>
				<td>廠別</td>
				<td colspan="2"><s:property value='bussletter.factNo' />
				</td>
				<td>職務</td>
				<td colspan="2"><s:property value='bussletter.position' />
				</td>
				<td>職務代理人</td>
				<td colspan="3">
					<s:property value="bussletter.GAgent" />
				</td>
			</tr>
			<tr>
				<td>出差地點</td>
				<td colspan="2" ><s:property value='bussletter.address' />
				</td>
				<td>出差時間</td>
				<td colspan="2">
				<s:date name='bussletter.dateFrom' format='yyyy/MM/dd'/>至
				<s:date name='bussletter.dateEnd' format='yyyy/MM/dd'/>共計
				<s:property value="%{sumDate(bussletter.dateFrom,bussletter.dateEnd)}"/>日
				</td>
				<td>去程班機時間<br/>回程班機時間</td>
				<td colspan="3">
				<s:date name='bussletter.timeFrom' format="yyyy/MM/dd HH:mm"/><br/>
				<s:date name="bussletter.timeEnd" format="yyyy/MM/dd HH:mm"/>
				</td>
			</tr>						
			<tr>
				<td>出差計劃</td>
				<td colspan="11">
				  <textarea style="width:500px;height:240px" readonly><s:property value="bussletter.planList" /></textarea>				     				 					
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
					<form id="memo" method="post" action="vbm_add" target="frameFile">
						<textarea style="width:100%;height:150px" name="memo" id="memo_txt"></textarea>						
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
	 <%-- <s:if test='bussletter.filesYn=="1"'>
	  <hr/>
	  <span style="color:blue;">附檔:</span><br/>
	  <s:iterator value="#session.list_filesexp">
	     <a href="/upload_letter/<s:property value='billno'/>/<s:property value='%{toUrl2(filename)}'/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
	  </s:iterator>	  
	</s:if> 
	--%>
</s:if>
<s:else>
  <br><br><br>
   <h1 style="color:red" align="center">函文已刪除</h1>
</s:else>

<script>window.jQuery|| document.write('<script src="jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script src="bootstrap/js/bootstrap.min.js"></script>	
<!--[if lt IE 9]>  
<script src="bootstrap/html5.js"></script>
<script src="bootstrap/respond.min.js"></script>
<![endif]-->
</body>
</html>
