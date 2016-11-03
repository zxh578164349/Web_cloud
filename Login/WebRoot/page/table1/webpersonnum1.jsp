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
	<table class="table table-striped table-hover table-bordered">
		<h3>
		<s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/webpersonnumSaveOrUpdate.jsp')" />
		</s:if>	
		<span id="h2_title">人員考勤</span>
		</h3>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>
				<th>直工應到人數(人)</th>
				<th>直工實到人數(人)</th>
				<th>間工應到人數(人)</th>
				<th>間工實到人數(人)</th>
				<th>外掛編應到人數(人)</th>
				<th>外掛編實到人數(人)</th>
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
				<td><s:property value="%{formatDouble(standardnumzg)}" /></td>
				<td><s:property value="%{formatDouble(realnumzg)}" /></td>
				<td><s:property value="%{formatDouble(standardnumjg)}" /></td>
				<td><s:property value="%{formatDouble(realnumjg)}" /></td>
				<td><s:property value="%{formatDouble(outstandardnum)}" /></td>
				<td><s:property value="%{formatDouble(outrealnum)}" />
				</td>
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>				
				<td>
					<form action="webpersonnum_findById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form> <a
					href="javascript:findById_form('subform${x.index}','webpersonnum_findById')"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>

					<form action="webpersonnum_delete" method="post"
						id="2subform${x.index}" style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form> <a href="javascript:isDelete('2subform${x.index}','webpersonnum_delete')"
					><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
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
