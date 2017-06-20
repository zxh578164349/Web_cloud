
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
	<table class="table table-striped table-hover table-bordered"  >
		<h3>		
		<s:if test='#session.loginUser.userread!="1"'>
	      <input value="添加" type="button" class="btn btn-info" onclick="loadUrl('saveAndUpdate/webphonebookSaveOrUpdate.jsp')"/> 	      
	   </s:if>
		<span id="h2_title">用戶聯系資料</span>
		</h3>
		<thead>
			<tr class="tr_show">
			        <th>序號</th>					
					<th>廠別</th>
					<th>單位</th>
					<th>姓名</th>					
					<th>職務</th>
					<th>內線</th>
					<th>手機</th>
					<th>短號</th>
					<th>Email</th>					
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
				<tr >
				    <td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				    <td><s:property value="id.fact.factSname" /></td>					
					<td><s:property value="id.department" /></td>					
					<td><s:property value="id.username" /></td>					
					<td><s:property value="id.post" /></td>
					<td><s:property value="id.phoneA" /></td>
					<td><s:property value="id.phoneB" /></td>
					<td><s:property value="id.phoneC"/></td>
					<td><s:property value="id.email"/></td>					
					<td>
					<form action="webphonebook_findById" method="post" id="subform${x.index}">
					    <input type="hidden" value="<s:property value='id.fact.factNo'/>" name="factNo"/>
					    <input type="hidden" value="<s:property value='id.department'/>" name="department"/>
					    <input type="hidden" value="<s:property value='id.post'/>" name="post"/>
					    <input type="hidden" value="<s:property value='id.username'/>" name="userName"/>
					    <input type="hidden" value="<s:property value='id.phoneA'/>" name="phoneA"/>
					    <input type="hidden" value="<s:property value='id.phoneB'/>" name="phoneB"/>
					    <input type="hidden" value="<s:property value='id.phoneC'/>" name="phoneC"/>
					    <input type="hidden" value="<s:property value='id.email'/>" name="email"/>
					</form>
					<form  method="post" id="2subform${x.index}" style="float:left">
					    <input type="hidden" value="<s:property value='id.fact.factNo'/>" name="factNo"/>
					    <input type="hidden" value="<s:property value='id.department'/>" name="department"/>
					    <input type="hidden" value="<s:property value='id.post'/>" name="post"/>
					    <input type="hidden" value="<s:property value='id.username'/>" name="userName"/>
					    <input type="hidden" value="<s:property value='id.phoneA'/>" name="phoneA"/>
					    <input type="hidden" value="<s:property value='id.phoneB'/>" name="phoneB"/>
					    <input type="hidden" value="<s:property value='id.phoneC'/>" name="phoneC"/>
					    <input type="hidden" value="<s:property value='id.email'/>" name="email"/>
					</form>					  
						<a href="javascript:findById_form('subform${x.index}','webphonebook_findById')" ><img alt="修改" src="images/icon/edit001.png" title="修改"></a>&nbsp;						
						<a href="javascript:mydelete('2subform${x.index}')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>						
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
     <jsp:include page="pagenation.jsp" flush="true"/>	
	 
</body>
</html>
