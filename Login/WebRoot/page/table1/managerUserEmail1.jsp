
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
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>	
		<input value="添加備簽人" type="button" class="btn btn-info" onclick="loadUrl('saveAndUpdate/webuseremailSaveOrUpdate.jsp')"/>
	    </s:if>	
		備簽人管理
		</h2>
		<thead>
			<tr class="tr_show">
				<th>備簽人廠別</th>
					<th>主簽人Email</th>
					<th>備簽人Email</th>
					<th>主簽人姓名 </th>
					<th>備簽人姓名 </th>										
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
					<s:if test='#session.loginUser.userread!="1"'>					
					<td>
						<a href="javascript:findById('${id.factNo}','${id.email}','${id.emailpassword }')">
						<img alt="修改" src="images/icon/edit001.png" title="修改"></a>&nbsp;					
						<a href="javascript:mydelete('${id.factNo}','${id.email}','${id.emailpassword}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>						
					</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>

	 <ul class="pagination" style="padding-left:42%">
		    <li style="width:50px"><a href="javascript:pages(0)">首頁</a></li>
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>-1)">&laquo;</a></li>			
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>)"><s:property value='bean.currentPage'/></a></li>
			<s:if test="bean.currentPage+1==bean.totalPage||bean.currentPage+1<bean.totalPage">			    
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+1)"><s:property value='bean.currentPage+1'/></a></li>			   
			</s:if>
			<s:if test="bean.currentPage+2==bean.totalPage||bean.currentPage+2<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+2)"><s:property value='bean.currentPage+2'/></a></li>
			</s:if>
			<s:if test="bean.currentPage+3==bean.totalPage||bean.currentPage+3<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+3)"><s:property value='bean.currentPage+3'/></a></li>
			</s:if>
			<s:if test="bean.currentPage+4==bean.totalPage||bean.currentPage+4<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+4)"><s:property value='bean.currentPage+4'/></a></li>
			</s:if>									
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>+1)">&raquo;</a></li>
			<li style="width:50px"><a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a></li>			
		</ul>
</body>
</html>
