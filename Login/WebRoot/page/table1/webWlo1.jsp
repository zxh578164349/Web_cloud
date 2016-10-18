
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
	      <input value="添加" type="button" class="btn btn-info"
		  onclick="loadUrl('saveAndUpdate/WebWLOSaveOrUpdate.jsp')" />
	     </s:if>
	     <span id="h2_title"> 水電油(每月輸入)</span>
	     </h2>
	     
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>年月</th>
				<th>廠別狀態</th>
				<th>用水量(噸)</th>
				<th>用水金額(USD)</th>
				<th>用電量(度)</th>
				<th>用電金額(USD)</th>
				<th>用蒸汽量(噸)</th>
				<th>用蒸汽金額(USD)</th>
				<th>用柴油量(噸)</th>
				<th>用柴油金額(USD)</th>
				<th>修繕金額(USD)</th>
				<th>輸入者</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr>
				<td>${bean.pageSize*(pages.page-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td><s:date name="id.yymm" format="yyyyMM" /></td>
				<td><s:property value="id.factCode" /></td>
				<td><s:property value="%{formatDouble(waterton)}" /></td>
				<td><s:property value="%{formatDouble(waterusd)}" /></td>
				<td><s:property value="%{formatDouble(electricdu)}" /></td>
				<td><s:property value="%{formatDouble(electricusd)}" /></td>
				<td><s:property value="%{formatDouble(gaston)}" /></td>
				<td><s:property value="%{formatDouble(gasusd)}" /></td>
				<td><s:property value="%{formatDouble(oilton)}" /></td>
				<td><s:property value="%{formatDouble(oilusd)}" /></td>
				<td><s:property value="%{formatDouble(repiarMoney)}"/></td>
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webwlo_findWloById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a
					href="javascript:findById_form('subform${x.index}','webwlo_findWloById')"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					<form action="webwlo_delete2" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}','webwlo_delete2')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
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
