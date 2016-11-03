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
    <form action="" method="post" theme="simple"  id="subkyzmat_form">
    
	<table class="table table-striped table-hover table-bordered">
		<h3>
		<s:if test='#session.loginUser.userread!="1"'>
		<input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/kyzmatSaveOrUpdate.jsp')" /></s:if>
		<span id="h2_title">物料資料管理</span>
		</h3>
		<thead>			
			<tr class="tr_show">
			    <th><input type="checkbox" value="wwww" id="cb_all" onclick="selectAll()"/></th>
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
				<!-- <th>大分類</th>
				<th>中分類</th>
				<th>小分類</th> -->
				<th>創建人</th>
				<th>創建日期</th>
				<th>第一廠別</th>
				<th>正在使用的廠別</th>
				<th>操作</th>								
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		 
		        <tr >
		        <td><input type="checkbox" value="<s:property value='matNo'/>" name="cb_list"/></td>
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="matNo" /></td>
				<td><s:property value="matCname"/></td>														
				<td><s:property value="matType"/></td>				
				<td><s:property value="matSize" /></td>				
				<td><s:property value="color"/></td>
				<td><s:property value="acctNo"/></td>
				<td><s:property value="policy"/></td>
				<td><s:property value="purPrice"/></td>	
				<td><s:property value="cunit"/></td>	
				<td><s:property value="punit"/></td>
				<td><s:property value="smatName"/></td>
				<td><s:property value="smatWeit"/></td>
				<!-- <td><s:property value="typeBno"/></td>	
				<td><s:property value="typeMno"/></td>	
				<td><s:property value="typeSno"/></td> -->
				<td><s:property value="builder"/></td>
				<td><s:property value="dateB"/></td>
				<td><s:property value="factNo"/></td>
				<td>
				   <s:iterator value="subKyzmats">
				      <s:if test="id.factNo==#session.factNo">
				          <font color="red"><s:property value="id.factNo"/>,</font>
				      </s:if>
				      <s:else>
				          <s:property value="id.factNo"/>,
				      </s:else>				      
				   </s:iterator>
				</td>
				<td>
				<form id="form${x.index}">
				   <input type="hidden" value="${temp.matNo}" name="matNo"/>
				</form>				
				<a href="javascript:findById_form('form${x.index}','kyzmat_findById')"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
				<a href="javascript:isDelete('${temp.matNo}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
				<a href="javascript:window.location.href='kyzmat_print?matNo=${temp.matNo}'" ><img alt="導出" src="images/icon/print001.png" title="導出" ></a>
				</td> 							
			</tr>	  
		  </s:iterator>		 		
		</tbody>
	</table>
	<!-- <s:submit value="導出所選物料" action="kyzmat_print_select"></s:submit>
    <s:submit value="添加所選物料" action="subkyzmat_addSubKyzmat"></s:submit>-->
    <input type="button" value="導出所選物料" class="btn btn-info" onclick="kyzmat_print_select()"/>&nbsp;
    <input type="button" value="添加所選物料" class="btn btn-info" onclick="subkyzmat_addSubKyzmat()"/>
	</form>
	</div>
 </div>		
	<jsp:include page="pagenation.jsp" flush="true"/>	
	
</body>

</html>
