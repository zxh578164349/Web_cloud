
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
		<input value="添加新用戶" type="button" class="btn btn-info" onclick="loadUrl('saveAndUpdate/webuserSaveOrUpdate.jsp')"/>
	    </s:if>	
		<span id="h2_title">用戶管理</span>
		</h3>
		<thead>
			<tr class="tr_show">
				<th>用戶ID</th>
					<th>登陸名</th>
					<th>姓名</th>
					<th>密碼</th>
					<th>IP</th>
					<th>工號</th>
					<th>廠別</th>
					<th>狀態</th>
					<th>登錄日期</th>					
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="username" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="pwd" /></td>
					<td><s:property value="ip" /></td>
					<td><s:property value="workno" /></td>
					<td><s:property value="emailpassword" /></td>
					<td>
					<s:if test="available==0"><img alt="可用" src="images/icon/available001.png" title="可用"></s:if> <s:if
							test="available==1"><img alt="禁用" src="images/icon/not_available001.png" title="禁用"></s:if>
					</td>
					<td><s:property value="logdate"/></td>
					<td>
					    <%-- <a href="userjurisdiction?id=${id}&fact=${factno}"><img alt="權限" src="images/icon/keys001.png" title="權限"></a>&nbsp;  --%>
					    <a href="javascript:loadjur(${id },'${factno}')"><img alt="權限" src="images/icon/keys001.png" title="權限"></a>&nbsp;
						<a href="javascript:loaduser(${id})"><img alt="修改" src="images/icon/edit001.png" title="修改"></a>&nbsp;
						<a href="javascript:loadUrl('userupdateKy?id=${id}&available=0')"><img alt="可用" src="images/icon/available001.png" title="可用"></a>&nbsp;
						<a href="javascript:loadUrl('userupdateKy?id=${id}&available=1')"><img alt="禁用" src="images/icon/not_available001.png" title="禁用"></a>&nbsp;
						<a href="javascript:mydelete(${id})"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>	
						<s:if test='weeklyreportMk=="Y"'>
						  <form action="" method="post" id="emailform${x.index}">
						    <input type="hidden" name="webUsers.id" value="${id}"/>
						    <input type="hidden" name="webUsers.username" value="${username}"/>
						    <input type="hidden" name="webUsers.name" value="${name}"/>
						    <input type="hidden" name="webUsers.email" value="${email}"/>
						  </form>
						  <a href="javascript:sendEmail('emailform${x.index}')">Email業務報告填寫</a>	
						</s:if>					
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
    <jsp:include page="pagenation.jsp" flush="true"/>	
	
</body>
</html>
