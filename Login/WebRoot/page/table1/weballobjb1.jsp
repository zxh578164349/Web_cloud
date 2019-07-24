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
						<!--<input type="button" class="btn btn-info" value="添加" onclick="loadUrl('saveAndUpdate/fixedSaveOrUpdate.jsp')"/>-->
					</s:if>
					<span id="h2_title">基本數據導入</span>
				</h3>
				<thead>
					<tr class="tr_show">
						<th>序號</th>
						<th>廠別</th>
						<th>廠別狀態</th>
						<th>日期</th>
						<th>機臺孔位數</th>
                        <th>機台利用率</th>
                        <th>生產數</th>
                        <th>請款雙數</th>
                        <th>實際回轉數</th>
                        <th>直工</th>
                        <th>間工</th>
                        <th>全廠總人數</th>
                        <th>直間比</th>
                        <th>直工人均產能</th>
                        <th>全廠人均產能</th>
                        <th>時產能</th>
                        <th>時迴轉</th>
                        <th>加班費</th>
                        <th>成本率</th>
                        <th>利潤率</th>
                        <th>總損耗</th>
                        <th>平均邊料重</th>
                        <th>邊料率</th>
                        <th>不良重量</th>
                        <th>不良率</th>
                        <th>退貨率</th>
                        <th>成倉庫存</th>
                        <th>用水單耗</th>
                        <th>用電單耗</th>
                        <th>蒸汽單耗</th>
                        <th>蒸汽單耗</th>
                        <th>色料藥品單耗</th>
                        <th>色料藥品單耗</th>
                        <th>生產與請款差異</th>
                        <th>工作天數</th>
                        <th>正批生產雙數</th>
                        <th>廠補</th>
                        <th>客補</th>
                        <th>樣品</th>
                        <th>前倉入庫折算后可請款數</th>
                        <th>平均日產能(雙)</th>
                        <th>庫存(成品倉、整理課)</th>
                        <th>庫存天數</th>
                        <th>無形差异</th>
                        <th>無形差異率</th>
                        <th>廢品重量</th>
                        <th>銷售成本金額</th>
                        <th>平均庫存金額</th>						
						<s:if test='#session.loginUser.userread!="1"'>
							<th>操作</th>
						</s:if>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="bean.list" status="x" id="temp">
						<tr>
							<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
							<td><s:property value="id.fact.factSname" /></td>
							<td><s:property value="id.fact.id.factArea" /></td>
							<td><s:property value="id.yymm" /></td>
							<td><s:property value="objA1" /></td>
							<td><s:property value="objA2" /></td>
							<td><s:property value="objA3" /></td>
							<td><s:property value="objA4" /></td>
							<td><s:property value="objA5" /></td>
							<td><s:property value="objA6" /></td>
							<td><s:property value="objA7" /></td>
							<td><s:property value="objA8" /></td>
							<td><s:property value="objA9" /></td>
							<td><s:property value="objA10" /></td>
							<td><s:property value="objA11" /></td>
							<td><s:property value="objA12" /></td>
							<td><s:property value="objA13" /></td>
							<td><s:property value="objA14" /></td>
							<td><s:property value="objA15" /></td>
							<td><s:property value="objA16" /></td>
							<td><s:property value="objA17" /></td>
							<td><s:property value="objA18" /></td>
							<td><s:property value="objA19" /></td>
							<td><s:property value="objA20" /></td>
							<td><s:property value="objA21" /></td>
							<td><s:property value="objA22" /></td>
							<td><s:property value="objA23" /></td>
							<td><s:property value="objA24" /></td>
							<td><s:property value="objA25" /></td>
							<td><s:property value="objA26" /></td>
							<td><s:property value="objA27" /></td>
							<td><s:property value="objA28" /></td>
							<td><s:property value="objA29" /></td>
							<td><s:property value="objA30" /></td>
							<td><s:property value="objA31" /></td>
							<td><s:property value="objA32" /></td>
							<td><s:property value="objA33" /></td>
							<td><s:property value="objA34" /></td>
							<td><s:property value="objA35" /></td>
							<td><s:property value="objA36" /></td>
							<td>
							<s:property value="%{formatDouble(objA36/objA31)}" />
							</td>							
							<td><s:property value="objA37" /></td>
							<td>
							<s:property value="%{formatDouble(objA37/(objA36/objA31))}" />
							</td>
							<td><s:property value="objA38" /></td>
							<td><s:property value="objA39" /></td>
							<td><s:property value="objA41" /></td>
							<td><s:property value="objA42" /></td>
							<td><s:property value="objA43" /></td>
						

							<s:if test='#session.loginUser.userread!="1"'>
								<td>
									<%--<form action="weballobj_findWloById" method="post" id="subform${x.index}">										
										<input type="hidden" value="<s:property value='id.fact.id.factNo'/>" name="factNo" />
										<input type="hidden" value="<s:property value='id.fact.id.factCode'/>" name="factCode" />											
										<input type="hidden" value="<s:property value='id.yymm'/>" name="yymm" />											
									</form> 
									<a href="javascript:findById('subform${x.index}','webwlo_findWloById')">
									<img alt="修改" src="images/icon/edit001.png" title="修改">										
								    </a>--%>
									<form action="weballobjb_delete" method="post" id="2subform${x.index}" style="float:left">
										<input type="hidden" value="<s:property value='id.fact.id.factNo'/>" name="factNo" /> <input
											type="hidden" value="<s:property value='id.fact.id.factArea'/>" name="factCode" /> <input
											type="hidden" value="<s:property value='id.yymm'/>" name="yymm" />
									</form> <a href="javascript:isDelete('2subform${x.index}','weballobjb_delete')"> <img alt="刪除"
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
