
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
		onclick="loadUrl('saveAndUpdate/WebMixPersonSaveOrUpdate.jsp')" />
	    </s:if>	
		人數工時(每月輸入)
		</h2>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>年月</th>
				<th>廠別狀態</th>
				<th>直工發薪人數(人)</th>
				<th>間工發薪人數(人)</th>
				<th>直工正班工時(小時)</th>
				<th>間工正班工時(小時)</th>
				<th>直工離職人數(人)</th>
				<th>間工離職人數(人)</th>
				<th>直工加班工時(小時)</th>
				<th>間工加班工時(小時)</th>
				<th>工傷件數(件)</th>
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
				<td><s:property value="id.factNo" />
				</td>
				<td><s:date name="id.yymm" format="yyyyMM" />
				</td>
				<td><s:property value="id.factCode" />
				</td>
				<td><s:property value="%{formatDouble(personzg)}" />
				</td>
				<td><s:property value="%{formatDouble(personjg)}" />
				</td>
				<td><s:property value="%{formatDouble(timezg)}" />
				</td>
				<td><s:property value="%{formatDouble(timejg)}" />
				</td>
				<td><s:property value="%{formatDouble(leavenumbzg)}" />
				</td>
				<td><s:property value="%{formatDouble(leavenumjg)}" />
				</td>
				<td><s:property value="%{formatDouble(addtimezg)}" />
				</td>
				<td><s:property value="%{formatDouble(addtimejg)}" />
				</td>
				<td><s:property value="%{formatDouble(hurtnum)}" />
				</td>
				<td><s:property value="username" />
				</td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webmixPerson_findMixPersonById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a
					href="javascript:findById_form('subform${x.index}','webmixPerson_findMixPersonById')"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					<form action="webmixPerson_delete2" method="post"
						id="2subform${x.index}" style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
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
