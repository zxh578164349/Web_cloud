<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ypre_null.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="page/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="page/jquerys/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	/*      $(function() {
	 var j2=jQuery.noConflict(); 
	 var demo = j2("#subform").Validform({
	 btnSubmit : "#sub",
	 tiptype : 1,
	 //showAllError : true,
	 });

	 });  */

	function checkNull() {
		var iyymm = document.getElementById("iyymm");
		var subform = document.getElementById("subform");
		var syymm = document.getElementById("syymm");
		if (iyymm.value == "") {
			syymm.innerHTML = "<font color='red'>(日期不能為空)</font>";
			return false;
		} else {
			subform.submit();
		}
	}
</script>
</head>

<body>
	<form action="ypre_findNulFact" method="post" id="subform">
		<table>
			<tr>
				<td><input type="text" name="yymm" onClick="WdatePicker()"
					readonly="readonly" class="Wdate" datatype="*" id="iyymm" /><span
					id="syymm"></span>
				</td>
				<td><input type="button" value="查找" onclick="checkNull()" />&nbsp;&nbsp;&nbsp;
					<input type="button" value="返回"
					onclick="javascript:location.href='ypre_findPageBean'" />
				</td>
			</tr>
		</table>
	</form>
	<hr>

	<%--     <s:if test="nulllist_pre!=null">
          <table bgColor=#97CBFF border=1
		style="margin:5px 0px 25px 0px;border-collapse:collapse;">
      <caption><s:property value="yymm"/></caption>
      <tr><td>序號</td><td class="tr_show">廠別</td><td class="tr_show">廠別狀態</td></tr>
        <s:iterator value="nulllist_pre" id="temp" status="x">
         <tr><td>${x.index+1}</td><td>${temp[1]}(${temp[0]})</td><td>${temp[2]}</td></tr> 
         </s:iterator>
       
    </table>
   </s:if>  --%>


	<s:if test="nulllist_pre!=null">
		<table bgColor=#97CBFF border=1
			style="margin:5px 0px 25px 0px;border-collapse:collapse;float:left">
			<caption style="color:blue">
				<s:property value="yymm" />
			</caption>
			<tr>
				<td>序號</td>
				<td class="tr_show">廠別</td>
				<td class="tr_show">廠別狀態</td>
			</tr>
			<c:forEach items="${nulllist_pre}" var="temp" begin="0" end="14"
				varStatus="x">
				<tr>
					<td>${x.index+1}</td>
					<td>${temp[1]}(${temp[0]})</td>
					<td>${temp[2]}</td>
				</tr>
			</c:forEach>
		</table>

		<s:if test="nulllist_pre.size()>15">
			<table bgColor=#97CBFF border=1
				style="margin:5px 0px 25px 0px;border-collapse:collapse;float:left">
				<caption style="color:blue">
					<s:property value="yymm" />
				</caption>
				<tr>
					<td>序號</td>
					<td class="tr_show">廠別</td>
					<td class="tr_show">廠別狀態</td>
				</tr>
				<c:forEach items="${nulllist_pre}" var="temp" begin="15" end="29"
					varStatus="x">
					<tr>
						<td>${x.index+1}</td>
						<td>${temp[1]}(${temp[0]})</td>
						<td>${temp[2]}</td>
					</tr>
				</c:forEach>
			</table>
		</s:if>

		<s:if test="nulllist_pre.size()>30">
			<table bgColor=#97CBFF border=1
				style="margin:5px 0px 25px 0px;border-collapse:collapse;float:left">
				<caption style="color:blue">
					<s:property value="yymm" />
				</caption>
				<tr>
					<td>序號</td>
					<td class="tr_show">廠別</td>
					<td class="tr_show">廠別狀態</td>
				</tr>
				<c:forEach items="${nulllist_pre}" var="temp" begin="30" end="40"
					varStatus="x">
					<tr>
						<td>${x.index+1}</td>
						<td>${temp[1]}(${temp[0]})</td>
						<td>${temp[2]}</td>
					</tr>
				</c:forEach>
			</table>
		</s:if>
	</s:if>
</body>
</html>
