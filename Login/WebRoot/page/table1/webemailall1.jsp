
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
		<input value="添加郵箱" type="button" class="btn btn-info" onclick="loadUrl('saveAndUpdate/webemailallSaveOrUpdate.jsp')"/>
	    </s:if>
		<span id="h2_title"></span>
		</h3>
		<thead>
			<tr class="tr_show">
				<th>姓名</th>
				<th>Email</th>	
				<th>類型</th>
				<th>EmailOrCc</th>
				<th>是否發送郵件</th>																											
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
				<tr>
					<td><s:property value="username" /></td>					
					<td><s:property value="email"/></td>
					<td><s:property value="emailTypeFk.ename"/></td>
					<td>
					  <s:if test='emailOrCc=="0"'>
					   Email
					  </s:if>
					  <s:else>
					    Cc
					  </s:else>
					</td>
					<td>
					  <s:if test='emailMk=="Y"'>
					    <img alt="是" src="images/icon/available001.png" title="是">
					  </s:if>
					  <s:else>
					     <img alt="否" src="images/icon/del_file.png" title="否">
					  </s:else>
					</td>																	
					<s:if test='#session.loginUser.userread!="1"'>					
					<td>					   
						<a href="javascript:findById('${eid}')"><img alt="修改" src="images/icon/edit001.png" title="修改"></a>&nbsp;												
						<a href="javascript:mydelete('${eid}','${email}','${emailTypeFk.ename}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>											
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
