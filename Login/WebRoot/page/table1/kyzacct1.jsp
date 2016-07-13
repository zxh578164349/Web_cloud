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
	<table class="table table-striped table-hover table-bordered" >
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
		<input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/kyzacctSaveOrUpdate.jsp')" /></s:if> 
		會計科目
		</h2>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>科目代號</th>
				<th>科目名稱</th>
				<th>分類代號</th>
				<th>分類名稱</th>
				<s:if test='#session.loginUser.userread!="1"'>				
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		 
		        <tr >
				<td>${ 25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="acctNo" />
				</td>
				<td>
				<s:property value="acctName"/>						
				</td>
				<td><s:property value="bacctNo"/>
				</td>
				<td><s:property value="bacctName" />
				</td>
				<s:if test='#session.loginUser.userread!="1"'>											
			 	<td>			 										
					 <form action="kyzacct_findById" method="post" id="form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='acctNo'/>"
							name="acctNo" /> 													
					 </form>
					  <form  method="post" id="2form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='acctNo'/>"
							name="acctNo" />													
					 </form>
					 <%--  <s:if test='#attr.loginUser.username=="admin"'>
					 <a href="javascript:document.getElementById('form${x.index}').submit()">修改</a>
					 <a href="javascript:isDelete('2form${x.index}')">刪除</a>
					 </s:if>
					 <s:else>
					    <a style="color:red" href="javascript:alert('對不起，只有管理員可以操作！')">修改</a>
					    <a style="color:red" href="javascript:alert('對不起，只有管理員可以操作！')">刪除</a>
					 </s:else> --%> 
					 <a href="javascript:findById_form('form${x.index}','kyzacct_findById')"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					 <a href="javascript:isDelete('2form${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>					
				</td>
				</s:if> 
			</tr>	  
		  </s:iterator>		 		
		</tbody>
	</table>
	</div>
 </div>			
	<ul class="pagination" style="padding-left:42%">
		    <li><a href="javascript:pages(0)">首頁</a></li>
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
			<li><a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a></li>			
		</ul>
</body>

</html>
