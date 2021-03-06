
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<style type="text/css">


</style>


<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/style.css" rel="stylesheet">	
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<!--[if lt IE 9]>  
  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body>
    <s:if test="bussletter!=null">
    <div class="container-fluid" id="LG">
    <div class="row-fluid">
		<div class="span12">
	    <table class="table table-hover table-condensed table-bordered">
		<!--<caption style="font-size:30;font-weight:bold">
		     人員出差申請書 
		</caption>-->
		<tbody>
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
				  <textarea style="width:500px;height:240px"><s:property value="bussletter.planList" /></textarea>				     				 					
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
						<textarea style="width:566px;height:150px" name="memo" id="memo_txt"></textarea>						
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
	 <%-- <s:if test='bussletter.filesYn=="1"'>
	  <hr/>
	  <span style="color:blue;">附檔:</span><br/>
	  <s:iterator value="#session.list_filesexp">
	     <a href="/upload_letter/<s:property value='billno'/>/<s:property value='%{toUrl2(filename)}'/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
	  </s:iterator>	  
	</s:if> 
	--%>
	</div>
	</div>
</div>	
</s:if>
<s:else>
  <br><br><br>
   <h1 style="color:red" align="center">內部聯絡函已刪除</h1>
</s:else>

</body>
</html>
