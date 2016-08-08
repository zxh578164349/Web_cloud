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
		onclick="loadUrl('saveAndUpdate/webcostSaveOrUpdate.jsp')" />
        </s:if>
		資材資料
		</h2>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>				
				<th>當月實際耗料(KG)</th>
				<th>當月生產膠底總毛重(KG)</th>
				<th>當月生產膠底總淨重(KG)</th>
				<th>非膠底耗用重量(KG)</th>
				<th>生產產值(USD)</th>
				<th>當月生產成本金額(USD)</th>
				<th>總原料庫存量(KG)</th>
				<th>總原料庫存金額(USD)</th>
				<th>膠類庫存量(KG)</th>
				<th>膠類庫存金額(USD)</th>
				<th>色料用量(KG)</th>
				<th>藥品用量(KG)</th>
				<th>離型劑用量金額(USD)</th>
				<th>白色回收粉(KG)</th>
				<th>黑色回收粉(KG)</th>
				<th>生膠回收粉(KG)</th>
				<th>灰色回收粉(KG)</th>
				<th>其它回收粉(KG)</th>				
				<th>輸入者</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr>
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td><s:property value="id.factCode" /></td>
				<td><s:date name="id.yymm" format="yyyyMM" /></td>
				<td><s:property value="%{formatDouble(actlost)}" /></td>
				<td><s:property value="%{formatDouble(avgbuttomweight)}" /></td>
				<td><s:property value="%{formatDouble(avgbuttomweight2)}" /></td>
				<td><s:property value="%{formatDouble(noGlueWeight)}"/></td>
				<td><s:property value="%{formatDouble(productedNum)}"/></td>
				<td><s:property value="%{formatDouble(avgprice)}" /></td>
				<td><s:property value="%{formatDouble(totalstore)}" /></td>
				<td><s:property value="%{formatDouble(totalstoremoney)}" />
				</td>
				<td><s:property value="%{formatDouble(gluestore)}" />
				</td>
				<td><s:property value="%{formatDouble(gluestoremoney)}" />
				</td>

				<td><s:property value="%{formatDouble(colorused)}" />
				</td>
				<td><s:property value="%{formatDouble(drugsused)}" />
				</td>
				<td><s:property value="%{formatDouble(leavemoney)}" />
				</td>
				<td><s:property value="%{formatDouble(whitenum)}" />
				</td>
				<td><s:property value="%{formatDouble(blacknum)}" />
				</td>
				<td><s:property value="%{formatDouble(gluenum)}" />
				</td>
				<td><s:property value="%{formatDouble(greynum)}" />
				</td>
				<td><s:property value="%{formatDouble(othernum)}" />
				</td>
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webcost_findById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a
					href="javascript:findById_form('subform${x.index}','webcost_findById')"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>

					<form action="webcost_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}','webcost_delete')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
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
