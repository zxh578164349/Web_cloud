<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>My JSP 'ydata_show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

//沒有提示的修改	
function update_ydata(subform){
   document.getElementById(subform).submit();
}
	
</script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/sumwebydatajs.js'></script>
</head>

<body >
 <div id="container">
 <div id="content">
	<table class="table table-striped table-hover table-bordered" >
		<h3>		
		產量資料未輸入記錄
		</h3>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>
				<th>記錄時間</th>								
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr> 
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.fact.factSname" /></td>
				<td><s:property value="id.fact.id.factArea" /></td>
				<td><s:property value="id.yymmdd"/></td>
				<td><s:property value="datecreate" /></td>								
			</tr>
		</s:iterator>
		</tbody>
	</table>
    </div>
  </div>
   <jsp:include page="pagenation.jsp" flush="true"/>		
	
</body>


</html>
