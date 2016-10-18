
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>My JSP 'backmat1.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
  <div id="container">
	<table class="table table-striped table-hover table-bordered">
		<h2 id="h2_title">工廠客戶訂單</h2>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>年月</th>
				<th>品牌</th>
				<th>模型</th>
				<th>客戶</th>
				<th>部件</th>
				<th>數據</th>			
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr >
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="factSname" /></td>
				<td><s:property value="id.factArea"/></td>
				
				<td><s:property value="id.yymm"/></td>			
				
				<td><s:property value="id.brank" /></td>
				
				<td><s:property value="id.modelNo" /></td>
				
				<td><s:property value="id.customer" /></td>
				
				<td><s:property value="id.component" /></td>
				
				<%--<td><s:property value="%{formatDouble(innum)}" /></td>--%>					
				<td><s:property value="%{toThou(orderData)}"/></td>
				
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<%--<form action="webfactOrder_findById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='orderId'/>"
							name="orderid" /> 						
					</form> <a
					href="javascript:layer.load(0);document.getElementById('subform${x.index}').submit()"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					--%>
					<form action="webfactOrder_delete" method="post"
						id="2subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='id.factArea'/>" name="factArea"/>
						<input type="hidden" value="<s:property value='id.yymm'/>" name="yymm"/>
						<input type="hidden" value="<s:property value='id.modelNo'/>" name="model"/>
						<input type="hidden" value="<s:property value='id.customer'/>" name="customer"/>
						<input type="hidden" value="<s:property value='id.brank'/>" name="brank"/>
						<input type="hidden" value="<s:property value='id.component'/>" name="component"/>
													
					</form> 
					<a href="javascript:isDelete('2subform${x.index}','webfactOrder_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
					</td>
				</s:if>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</div>	
	<jsp:include page="pagenation.jsp" flush="true"/>	
	
</body>
</html>
