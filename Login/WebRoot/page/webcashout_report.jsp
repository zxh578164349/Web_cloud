<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>My JSP 'ypre_null.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript">

jq(function(){
    jq("#subform").Validform({
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true
    })
})
</script>
</head>

<body>
<br/>
	<form action="webcashout_report" method="post" id="subform" target="_blank">
		<table id="tb_search">
			<tr>
				<td>
				<span>
				 <input type="text" name="yymm" onClick="WdatePicker({dateFmt:'yyyyMM'})"
					readonly="readonly" class="Wdate" datatype="*" id="iyymm" />
				</span>
				<span>
				 <s:if test="#session.factNo=='tw'">
					<select name="factNo" id="factNo" datatype="*">
					    <option value="">請選擇工廠</option>
						<option value="all">全部</option>						
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
				</s:if> 
				<s:else>
					<select name="factNo" id="factNo">						
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>
				</s:else>
				</span>
				 <input type="submit" value="下載"  class="btn btn-primary"/>&nbsp;&nbsp;
				 <input type="button" value="返回" onclick="javascript:location.href='webestpro_findPageBean'" class="btn btn-primary"/>	
				</td>			    
				
			</tr>
		</table>
	</form>
	<hr>





</body>
</html>
