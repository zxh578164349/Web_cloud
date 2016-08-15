<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'ydata_show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>		
	<ul class="pagination" style="padding-left:42%">
	        <s:if test="bean.currentPage==1">
	          <li class="disabled"><a >首頁</a></li>
			  <li class="disabled"><a >&laquo;</a></li>
	        </s:if>
	        <s:else>
	          <li><a href="javascript:pages(0)">首頁</a></li>
			  <li><a href="javascript:pages(<s:property value='bean.currentPage'/>-1)">&laquo;</a></li>						
	        </s:else>
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
			<s:if test="bean.currentPage==bean.totalPage">
			  <li class="disabled"><a >&raquo;</a></li>
			  <li class="disabled"><a >尾頁</a></li>	
			</s:if>
			<s:else>
			  <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+1)">&raquo;</a></li>
			  <li><a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a></li>	
			</s:else>														
		</ul>
	<hr>
</body>
</html>
