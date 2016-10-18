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
  <div id="container">
	<table class="table table-striped table-hover table-bordered" >
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/webcashoutSaveOrUpdate.jsp')" />
        </s:if>
		<span id="h2_title">請款資料</span>
		</h2>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>				
				<th>生管實際交單雙數(雙)</th>
				<th>生管交單折算金額(USD)</th>
				<th>企劃實際請款雙數(雙)</th>
				<th>企劃實際請款金額(USD)</th>
							
				<th>輸入者</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr >
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td><s:property value="id.factCode" /></td>
				<td><s:date name="id.yymmdd" format="yyyyMMdd" /></td>
				<td><s:property value="realpairs" /></td>
				<td><s:property value="convertmoney" /></td>
				<td><s:property value="realcashoutpairs" /></td>
				<td><s:property value="realcashoutmoney"/></td>
				
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webcashout_findById" method="post" id="subform${x.index}">					
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode" />							
						<input type="hidden" value="<s:date name='id.yymmdd' format='yyyyMMdd'/>" name="yymm" />							
					</form> 
					<a href="javascript:findById_form('subform${x.index}','webcashout_findById')" >
					<img alt="修改" src="images/icon/edit001.png" title="修改" ></a>

					<form action="webcashout_delete" method="post" id="2subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" /> 
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode" />							
						<input type="hidden" value="<s:date name='id.yymmdd' format='yyyyMMdd'/>" name="yymm" />							
					</form> 					
					<a href="javascript:isDelete('2subform${x.index}','webcashout_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
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
