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

<title>My JSP 'ypre_show1.jsp' starting page</title>

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
		<h2>
		<s:if test='#session.loginUser.userread!="1"'> 
		<input value="添加" type="button" class="btn btn-info" onclick="loadUrl('saveAndUpdate/kyzsecSaveOrUpdate.jsp')"/></s:if>
		費用組別
		</h2>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>組別代號</th>
				<th>組別名稱</th>
				<th>區域</th>
				<th>所屬部門</th>
				<th>是否使用</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		 
		        <tr >
				<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" />
				</td>
				<td>
				<s:property value="id.secNo"/>						
				</td>
				<td><s:property value="secNm"/>
				</td>
				<td><s:property value="areaMk" />
				</td>
				<td><s:property value="deptNo" />
				</td>
				<td><s:property value="useMk" />
				</td>
				<s:if test='#session.loginUser.userread!="1"'>							
			 	<td>			 										
					 <form action="kyzsec_findById" method="post" id="form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.secNo'/>" name="secNo" />													
					 </form>
					  <form action="kyzsec_delete" method="post" id="2form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.secNo'/>" name="secNo" />													
					 </form>
					  <%-- <s:if test='#attr.loginUser.username=="admin"'>
					 <a href="javascript:document.getElementById('form${x.index}').submit()">修改</a>
					 <a href="javascript:isDelete('2form${x.index}')">刪除</a>
					 </s:if>
					 <s:else>
					    <a style="color:red" href="javascript:alert('對不起，只有管理員可以操作！')">修改</a>
					    <a style="color:red" href="javascript:alert('對不起，只有管理員可以操作！')">刪除</a>
					 </s:else> --%>
					  <a href="javascript:findById_form('form${x.index}','kyzsec_findById')"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					 <a href="javascript:isDelete('2form${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>					
				</td>
				</s:if> 
			</tr>	  
		  </s:iterator>		 		
		</tbody>
	</table>
	</div>
 </div>
 <jsp:include page="pagenation.jsp" flush="true"/>					
	
</body>

</html>
