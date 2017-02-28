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
<title>My JSP 'backmat1.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
   <div id="container">
   <div id="content">
	<table class="table table-striped table-hover table-bordered"  >
	<h3>
	<s:if test='#session.loginUser.userread!="1"'>
	<input type="button" class="btn btn-info" value="添加" onclick="loadUrl('saveAndUpdate/fixedSaveOrUpdate.jsp')"/>	
	</s:if>	
	<span id="h2_title">固定資產</span>
	</h3>
		<thead>			
			<tr class="tr_show">
			    <th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>集團財編</th>				
				<th>財產編號</th>
				<th>名稱</th>
				<th>大分類ID</th>
				<th>大分類名稱</th>
				<th>小分類ID</th>
				<th>小分類名稱</th>
				<th>幣別</th>
				<th>購入單價</th>
				<th>型號</th>
				<th>品牌</th>
				<th>產地</th>
				<th>單位ID</th>
				<th>單位名稱</th>				
				<th>增加方式ID</th>
				<th>增加方式名稱</th>				
				<th>折舊計算方式ID</th>
				<th>折舊計算方式名稱</th>				
				<th>進廠日期</th>
				<th>驗收日期</th>
				<th>區域</th>				
				<th>是否可刪除</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody" >		
		  <s:iterator value="bean.list" status="x" id="temp">		   
			<tr >
			    <td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="factNo" /></td>
				<td><s:property value="factcode"/></td>				
				<td><s:property value="fixedId" /></td>												
				<td><s:property value="assetscoding" /></td>			
				<td><s:property value="assetname" /></td>			
				<td><s:property value="majorId" /></td>		
				<td><s:property value="majorName" /></td>				
				<td><s:property value="subId" /></td>				
				<td><s:property value="subName" /></td>
				<td><s:property value="currency" /></td>
				<td><s:property value="%{formatDouble(priceIn)}"/></td>				
				<td><s:property value="model" /></td>				
				<td><s:property value="brand" /></td>				
				<td><s:property value="manufacturer" /></td>				
				<td><s:property value="unitsId" /></td>
				<td><s:property value="unitsName"/></td>												
				<td><s:property value="addwaysId" /></td>				
				<td><s:property value="addwaysName" /></td>						
				<td><s:property value="methodId" /></td>		
				<td><s:property value="methodName" /></td>																				
				<td><s:property value="addTime"/></td>				
				<td><s:property value="checkdate"/></td>
				<td><s:property value="areaName"/></td>
				<td>
				   <s:if test='delMk=="Y"'>
				  是
				</s:if>
				<s:if test='delMk=="N"'>
				  否
				</s:if>	
				</td>
				<s:if test='#session.loginUser.userread!="1"'>			
			    <td>
			     <a href="javascript:loadUrl('fix_findById?id=${fixedassetsId}')" ><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>	
			       <s:if test='delMk=="Y"||delMk==null'>
			          <a href="javascript:isDelete(<s:property value='fixedassetsId'/>)" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
			       </s:if>
			       <s:if test='delMk=="N"'>
			           <a disabled style="color:grey"><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除"></a>
			       </s:if>								
					 <a href="javascript:loadUrl('fix_findById2?id=${fixedassetsId}')"><img alt="調撥" src="images/icon/move001.png" title="調撥"></a>					   					 
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
