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
						<input type="button" class="btn btn-info" value="添加" onclick="loadUrl('saveAndUpdate/web_objs_bSaveOrUpdate.jsp')"/>
					</s:if>
					<span id="h2_title">工廠訊息每日更新</span>
				</h3>
				<thead>
					<tr class="tr_show">
						<th>序號</th>
						<th>廠別</th>
						<th>廠別狀態</th>
						<th>日期</th>
						<th>上模數</th>
                        <th>人數(拉模手)</th>
                        <th>標準產量</th>
                        <th>實際產量</th>
                        <th>正批生產雙數</th>
                        <th>客補生產雙數</th>
                        <th>廠補生產雙數</th>
                        <th>樣品生產雙數</th>
                        <th>出貨數</th>
                        <th>退貨數</th>
                        <th>上模總工時</th>                        
                        <th>天數</th>
                        <th>工作日/假日</th>
                        <th>成型不良雙數</th>
                        <th>慢單狀況(張)</th>
                        <th>慢單狀況(雙)</th>
                        <th>訂單欠數</th>
                        <th>直工人數</th>
                        <th>間工人數</th>
                        <th>招工數</th>
                        <th>離職數</th>
                        <th>請假數</th>
                        <th>品質問題與客訴 </th>
                        <th>扣款訊息 </th>
                        <th>機台狀況/異常 </th>
                        <th>客人來訪訊息</th>
                        <th>其他提報事項</th>                                                               					
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
							<td><s:property value="onModulus" /></td>
							<td><s:property value="%{formatDouble(personnum)}"/></td>
							<td><s:property value="standardOutput" /></td>
							<td><s:property value="actualYield" /></td>
							<td><s:property value="zpObja" /></td>
							<td><s:property value="hostpairs" /></td>
							<td><s:property value="factpairs" /></td>
							<td><s:property value="samplepairs" /></td>
							<td><s:property value="outnum" /></td>
							<td><s:property value="backnum" /></td>
							<td><s:property value="workhours" /></td>
							<td><s:property value="daycount" /></td>
							<td>
							<s:if test="workorholiday==1">
						       <font color="red">假日</font>
					        </s:if>
					        <s:if test="workorholiday==2">
						       <font color="green">未排產</font>
					        </s:if> 
					        <s:if test="workorholiday==0">
				                                               工作日
				            </s:if>
							</td>
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

							<s:if test='#session.loginUser.userread!="1"'>
								<td>
									<form action="" method="post" id="subform${x.index}">										
										<input type="hidden" value="<s:property value='id.webFact.id.factNo'/>" name="factNo" />
										<input type="hidden" value="<s:property value='id.webFact.id.factArea'/>" name="factCode" />											
										<input type="hidden" value="<s:property value='id.yymmdd'/>" name="yymmdd" />											
									</form> 
									<a href="javascript:findById_form('subform${x.index}','webobjb_findById')">
									<img alt="修改" src="images/icon/edit001.png" title="修改">										
								    </a>
									<form action="" method="post" id="2subform${x.index}" style="float:left">
										<input type="hidden" value="<s:property value='id.webFact.id.factNo'/>" name="factNo" /> <input
											type="hidden" value="<s:property value='id.webFact.id.factArea'/>" name="factCode" /> <input
											type="hidden" value="<s:property value='id.yymmdd'/>" name="yymmdd" />
									</form> <a href="javascript:isDelete('2subform${x.index}','webobjb_delete')"> <img alt="刪除"
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
