
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>My JSP 'backmat1.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<body>
  <div id="container">
	<table class="table table-striped table-hover table-bordered" >
		<h3>
		<s:if test='#session.loginUser.userread!="1"'>	
		<input value="添加" type="button" class="btn btn-info" onclick="loadUrl('saveAndUpdate/webnewproductSaveOrUpdate.jsp')"/>
	    </s:if>
		<span id="h2_title">新產品推銷申請表</span>
		</h3>
		<thead>
			<tr class="tr_show">
			    <th>單號</th>
				<th>接收日期</th>
				<th>新材料名稱</th>																											
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
				<tr>
					<td><s:property value="billNo" /></td>					
					<td><s:property value="receiveDate"/></td>
					<td><s:property value="PName"/></td>													
					<s:if test='#session.loginUser.userread!="1"'>					
					<td>					   
						<a href="javascript:findById('${billNo}')"><img alt="修改" src="images/icon/edit001.png" title="修改"></a>&nbsp;
						<a href="javascript:mydelete('${billNo}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>											
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
