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
	
</head>
<body>
  <div id="container">
  <div id="content">
	<table class="table table-striped table-hover table-bordered" >
		<h3>
		<s:if test='#session.loginUser.userread!="1"'>
	    <input type="button" value="添加" class="btn btn-info"
		onclick="javascript:location.href='saveAndUpdate/Yield_data.jsp'" />
	    </s:if>	
		產量資料(月統計)
		</h3>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>年月</th>
				<th>上模數(月合計)</th>
				<th>人數(月合計)</th>
				<th>標準產量(月合計)</th>
				<th>實際產量(月合計)</th>
				<th>天數(月合計)</th>
				<th>實際生產雙數(月合計)</th>
				<th>客補生產雙數(月合計)</th>
				<th>廠補生產雙數(月合計)</th>
				<th>樣品生產雙數(月合計)</th>
				<th>出貨數(月合計)</th>
				<th>退貨數(月合計)</th>
				<th>上模總工時(月合計)</th>				
				<th>達成率(%)</th>

			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr >
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td>${temp[0]}</td>
				<td>${temp[1]}</td>
				<td>${temp[2]}</td>
				<td><s:property value="%{formatDouble(#attr.temp[3])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[4])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[5])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[6])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[7])}" />
				</td>
				
				<td><s:property value="%{formatDouble(#attr.temp[8])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[9])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[10])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[11])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[12])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[13])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[14])}" />
				</td>				
				<td><s:property
						value="%{formatDouble(100*#attr.temp[6]/#attr.temp[5])}" />%</td>				
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>	
	 <jsp:include page="pagenation.jsp" flush="true"/>	
	

</body>


</html>
