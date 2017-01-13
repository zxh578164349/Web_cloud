
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
	<table class="table table-striped table-hover table-bordered" >
		<h3>
		<s:if test='#session.loginUser.userread!="1"'>	
		<input value="添加同步人" type="button" class="btn btn-info" onclick="loadUrl('saveAndUpdate/webuseremailSaveOrUpdate.jsp')"/>
	    </s:if>	
		<span id="h2_title"></span>
		</h3>
		<thead>
			<tr class="tr_show">
				<th>廠別</th>
					<th>主同步人Email</th>
					<th>被同步人Email</th>
					<th>主同步人姓名 </th>
					<th>被同步人姓名 </th>
					<th>作用域</th>										
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
				<tr >
					<td><s:property value="id.factNo" /></td>
					<td><s:property value="id.email" /></td>
					<td><s:property value="id.emailpassword"/></td>
					<td><s:property value="name" /></td>
					<td><s:property value="namePwd" /></td>	
					<td>					
					   <s:if test='id.typeMk=="0"'>
					                 簽核
					   </s:if>
					   <s:else>
					                知會
					   </s:else>
					</td>									
					<s:if test='#session.loginUser.userread!="1"'>					
					<td>
						<a href="javascript:findById('${id.factNo}','${id.email}','${id.emailpassword }','${id.typeMk}')">
						<img alt="修改" src="images/icon/edit001.png" title="修改"></a>&nbsp;					
						<a href="javascript:mydelete('${id.factNo}','${id.email}','${id.emailpassword}','${id.typeMk}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>						
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
