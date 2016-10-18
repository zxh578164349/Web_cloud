<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		onclick="loadUrl('saveAndUpdate/kyzmatSaveOrUpdate.jsp')" /></s:if>
		<span id="h2_title">物料資料</span>
		</h2>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>物料編號</th>
				<th>物料中文名稱</th>
				<th>資材分類</th>
				<th>物料規格</th>
				<th>物料顏色</th>
				<th>會計科目</th>
				<th>采購政策</th>
				<th>采購單價</th>
				<th>中文單位</th>
				<th>幣別</th>
				<th>商品名稱</th>
				<th>商品單重</th>
				<th>大分類</th>
				<th>中分類</th>
				<th>小分類</th>				
				<th>創建人</th>
				<th>創建日期</th>
				<th>第一廠別</th>
				<th>當前使用廠別</th>
				
								
				<!-- <s:if test='#session.loginUser.userread!="1"'>								
				<th>操作</th>
				</s:if> -->
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		 
		        <tr >
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.kyzMat.matNo" /></td>
				<td><s:property value="id.kyzMat.matCname"/></td>														
				<td><s:property value="id.kyzMat.matType"/></td>				
				<td><s:property value="id.kyzMat.matSize" /></td>				
				<td><s:property value="id.kyzMat.color"/></td>
				<td><s:property value="id.kyzMat.acctNo"/></td>
				<td><s:property value="id.kyzMat.policy"/></td>
				<td><s:property value="id.kyzMat.purPrice"/></td>	
				<td><s:property value="id.kyzMat.cunit"/></td>	
				<td><s:property value="id.kyzMat.punit"/></td>
				<td><s:property value="id.kyzMat.smatName"/></td>
				<td><s:property value="id.kyzMat.smatWeit"/></td>
				<td><s:property value="id.kyzMat.typeBno"/></td>	
				<td><s:property value="id.kyzMat.typeMno"/></td>	
				<td><s:property value="id.kyzMat.typeSno"/></td>
				<td><s:property value="id.kyzMat.builder"/></td>
				<td><s:property value="id.kyzMat.dateB"/></td>
				<td><s:property value="id.kyzMat.factNo"/></td>
				<td><s:property value="id.factNo"/></td>				
				<%-- <s:if test='#session.loginUser.userread!="1"'>														
			 	<td>			 															
					  <form action="subkyzmat_delete" method="post" id="form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyzMat.matNo'/>" name="matNo" />
					   <input type="hidden" value="<s:property value='id.username'/>" name="username" />
					   <input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />																				
					 </form>
					   <s:if test='#attr.loginUser.username=="admin"'>					 
					  <a href="javascript:showDiv2('<s:property value='id.factNo'/>','<s:property value='id.billNo'/>')">修改</a>
					 <a href="javascript:isDelete('2form${x.index}')">刪除</a>
					 </s:if>
					 <s:else>
					    <a style="color:red" href="javascript:alert('對不起，只有管理員可以操作！')">修改</a>
					    <a style="color:red" href="javascript:alert('對不起，只有管理員可以操作！')">刪除</a>
					 </s:else>  
					 
					 暫用"修改"與"刪除"功能 
					 <a href="javascript:location.href='kyzmat_findById?matNo=${temp.id.kyzMat.matNo}'"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					 <a href="javascript:isDelete('form${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>	 				
				</td>
				</s:if> --%>
			</tr>	  
		  </s:iterator>		 		
		</tbody>
	</table>
	</div>
 </div>		
	<jsp:include page="pagenation.jsp" flush="true"/>	
	
</body>

</html>
