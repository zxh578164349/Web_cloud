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
	<table class="table table-striped table-hover table-bordered">
		<h3>
		  <s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info" onclick="loadUrl('page/sum_yiele_data.jsp')"/>
	    </s:if>
		<span id="h2_title">產量資料(盤點統計)</span>				
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
				<th>開始日期</th>
				<th>結束日期</th>
				<th>達成率(%)</th>				
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr>
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo.factNo"/></td>
				<td><s:property value="id.factCode"/></td>
				<td><s:property value="id.yymm"/></td>
				<td><s:property value="%{formatDouble(sumEverydemo)}" />
				</td>
				<td><s:property value="%{formatDouble(sumEverypeople)}" />
				</td>
				<td><s:property value="%{formatDouble(sumStandarddemo)}" />
				</td>
				<td><s:property value="%{formatDouble(sumActualdemo)}" />
				</td>
				<td><s:property value="%{formatDouble(sumWorkdays)}" />
				</td>
				
				<td><s:property value="%{formatDouble(sumActualpairs)}" />
				</td>
				<td><s:property value="%{formatDouble(sumHostpairs)}" />
				</td>
				<td><s:property value="%{formatDouble(sumFactpairs)}" />
				</td>
				<td><s:property value="%{formatDouble(sumSamplepairs)}" />
				</td>
				<td><s:property value="%{formatDouble(sumOutnum)}" />
				</td>
				<td><s:property value="%{formatDouble(sumBacknum)}" />
				</td>
				<td><s:property value="%{formatDouble(sumWorkhours)}" />
				</td>
				<td><s:property value="startDate"/></td>
				<td><s:property value="endDate"/></td>
				<td><s:property value="%{formatPer(sumActualdemo,sumStandarddemo)}"/></td>				
				<td>
				   <form action="sumwebydata_delete" method="post" id="subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='id.factNo.factNo'/>" name="factNo" />							
						<input type="hidden" value="<s:property value='id.yymm'/>" name="yymm" />
						<input type="hidden" value="<s:property value='startDate'/>" name="startDate"/>
						<input type="hidden" value="<s:property value='endDate'/>" name="endDate"/>							
					</form>
					<form action="sumwebydata_findById" method="post" id="2subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='id.factNo.factNo'/>" name="factNo" />							
						<input type="hidden" value="<s:property value='id.yymm'/>" name="yymm" />
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode"/>
																	
					</form>									   			    
				   <s:if test='#session.loginUser.userread!="1"'>
				   <s:if test='#session.loginUser.username=="admin"'>
				   <a href="javascript:findById_form('2subform${x.index}','sumwebydata_findById')">
				   <img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
				   </s:if>
				   <a href="javascript:isDelete('subform${x.index}','sumwebydata_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>	
				   </s:if>
				</td>															
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>	
		 <jsp:include page="pagenation.jsp" flush="true"/>
	

</body>


</html>
