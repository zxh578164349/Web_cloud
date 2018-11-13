<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		onclick="loadUrl('saveAndUpdate/webestproSaveOrUpdate.jsp')" />
		
		<s:if test='#session.loginUser.factno=="657"||#session.loginUser.factno=="tw"'>
           <input value="導入" type="button" class="btn btn-info"
		onclick="loadUrl('page/webestproduct-import.jsp')" />
        </s:if>       		
        </s:if>
        
		<span id="h2_title">預計生產</span>
		</h3>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>
				<th>類型</th>
				<th>有效孔位數(孔)</th>
				<th>總機孔</th>
				<th>工程樣品</th>
				<th>輔料</th>
				<th>其它</th>
				<th>機台戰力(模/月)</th>
				<th>預計生產天數(天)</th>
				<th>預計每日上模數(模)</th>
				<th>預計每日人數(人)</th>
				<th>預計生產模數(模)</th>
				<th>預計生產雙數(雙)</th>
				<th>預計請款雙數(雙)</th>
				<th>預計請款金額(USD)</th>
				
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
				<td><s:date name="id.yymm" format="yyyyMM" /></td>
				<td>
				<s:if test="id.type=='zd'">暫訂版</s:if>
				<s:else>調整版</s:else>
				</td>
				<td><s:property value="%{formatDouble(hole)}" /></td>
				
				<td><s:property value="%{formatDouble(totalhole)}" />
				</td>
				<td><s:property value="%{formatDouble(sample)}" />
				</td>
				<td><s:property value="%{formatDouble(accessories)}" />
				</td>
				<td><s:property value="%{formatDouble(other)}" />
				</td>
				
				<td><s:property value="%{formatDouble(machinepower)}" /></td>
				<td><s:property value="%{formatDouble(estdays)}" /></td>
				<td><s:property value="%{formatDouble(esteverymodel)}" /></td>
				<td><s:property value="%{formatDouble(esteverypeople)}" /></td>
				<td><s:property value="%{formatDouble(estmodel)}" />
				</td>
				<td><s:property value="%{formatDouble(estnum)}" />
				</td>
				<td><s:property value="%{formatDouble(estpay)}" />
				</td>
				<td><s:property value="%{formatDouble(estmoney)}" />
				</td>								
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webestpro_findById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" /><input type="hidden" value="<s:property value='id.type'/>"
							name="id.type" />
					</form> <a
					href="javascript:findById_form('subform${x.index}','webestpro_findById')"
					><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>

					<form action="webestpro_delete" method="post"
						id="2subform${x.index}" style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" /><input type="hidden" value="<s:property value='id.type'/>"
							name="id.type" />
					</form> <a href="javascript:isDelete('2subform${x.index}','webestpro_delete')"
					><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
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
