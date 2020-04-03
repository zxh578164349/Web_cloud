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
		<div id="content">
			<table class="table table-striped table-hover table-bordered">
				<h3>
					<s:if test='#session.loginUser.userread!="1"'>
						<input type="button" class="btn btn-info" value="添加" onclick="loadUrl('saveAndUpdate/webobjscSaveOrUpdate.jsp')"/>
					</s:if>
					<span id="h2_title">工廠日報</span>
				</h3>
				<thead>
					<tr class="tr_show">
						<th>序號</th>
						<th>廠別</th>
						<th>廠別狀態</th>
						<th>日期</th>
						<th>生產欠數</th>
                        <th>孔位數</th>
                        <th>上模數</th>
                        <th>日產能</th>
                        <th>回轉數</th>
                        <th>出勤人員數</th>
                        <th>離職、資遺人數</th>
                        <th>每日發生費用</th>                                                                                      					
						<s:if test='#session.loginUser.userread!="1"'>
							<th>操作</th>
						</s:if>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="bean.list" status="x" id="temp">
						<tr>
							<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
							<td><s:property value="id.webFact.factSname" /></td>
							<td><s:property value="id.webFact.id.factArea" /></td>
							<td><s:property value="id.yymmdd" /></td>
							<td><s:property value="objA1" /></td>
							<td><s:property value="objA2"/></td>
							<td><s:property value="objA3" /></td>
							<td><s:property value="objA4" /></td>
							<td><s:property value="objA5" /></td>
							<td><s:property value="objA6" /></td>
							<td><s:property value="objA7" /></td>
							<td><s:property value="objA8" /></td>																																								

							<s:if test='#session.loginUser.userread!="1"'>
								<td>
									<form action="" method="post" id="subform${x.index}">										
										<input type="hidden" value="<s:property value='id.webFact.id.factNo'/>" name="factNo" />
										<input type="hidden" value="<s:property value='id.webFact.id.factArea'/>" name="factCode" />											
										<input type="hidden" value="<s:property value='id.yymmdd'/>" name="yymmdd" />											
									</form> 
									<a href="javascript:findById_form('subform${x.index}','webobjc_findById')">
									<img alt="修改" src="images/icon/edit001.png" title="修改">										
								    </a>
									<form action="" method="post" id="2subform${x.index}" style="float:left">
										<input type="hidden" value="<s:property value='id.webFact.id.factNo'/>" name="factNo" /> <input
											type="hidden" value="<s:property value='id.webFact.id.factArea'/>" name="factCode" /> <input
											type="hidden" value="<s:property value='id.yymmdd'/>" name="yymmdd" />
									</form> <a href="javascript:isDelete('2subform${x.index}','webobjc_delete')"> <img alt="刪除"
										src="images/icon/delete001.png" title="刪除"> </a></td>
							</s:if>

						</tr>

					</s:iterator>

				</tbody>

			</table>
		</div>
	</div>
	<jsp:include page="pagenation.jsp" flush="true" />


</body>
</html>
