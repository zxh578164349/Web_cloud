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
	<span id="h2_title">配方系統</span>
	</h3>
				<thead>
					<tr class="tr_show">
						<th>序號</th>
						<th>配方索引</th>
						<th>廠別</th>
						<th>製程類別</th>
						<th>配方編號</th>
						<th>配方名稱</th>
						<th>倍率</th>
						<th>品牌形體</th>
						<th>帶皮半成品硬度</th>
						<th>成品硬度</th>
						<th>顏色</th>
						<th>發行日期</th>
						<th>品牌指定</th>											
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="bean.list" status="x" id="obj">
						<tr>
							<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
							<td><s:property value="formulaIndex" />
							</td>
							<td><s:property value="factNo.factSname" />
							</td>
							<td><s:property value="factCode.name" />
							</td>							
							<td><s:property value="formulaNo" />
							</td>
							<td><s:property value="formulaName" />
							</td>
							<td><s:property value="magnification" />
							</td>
							<td><s:property value="brandBody" />
							</td>
							<td><s:property value="semifinishedProductHardness" />
							</td>
							<td><s:property value="productHardness" />
							</td>
							<td><s:property value="color" />
							</td>
							<td><s:property value="issuedDate" />
							</td>
							<td>
							   <s:if test='assignBrand=="1"'>
							                  指定
							   </s:if>
							   <s:else>
							                  非指定 
							   </s:else>							
							</td>												
								<td>									
									<form  id="4subform${x.index}" action="webformula_print2" method="post" style="float:left" target="_blank">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />
										<input type="hidden" value="<s:property value='factNo.factNo'/>" name="factNo" />
										<input type="hidden" value="look" name="lookordown"/>																														
									</form>
									<form  id="5subform${x.index}" action="webformula_print2" method="post" style="float:left" target="_blank">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />
										<input type="hidden" value="<s:property value='factNo.factNo'/>" name="factNo" />
										<input type="hidden" value="down" name="lookordown"/>																														
									</form>																																											 								    
								    <a href="javascript:document.getElementById('4subform${x.index}').submit()" class="btn btn-xs btn-success">預覽</a>
								    <a href="javascript:document.getElementById('5subform${x.index}').submit()" class="btn btn-xs btn-success">下載</a>
								    <!-- <a href="webtabpom_findByIdfiles?pomNo=${obj.pom.pomNo}" class="btn btn-xs btn-success" target="_blank">
					                                             附檔</a>-->
								</td>							
						</tr>
					</s:iterator>
				</tbody>

			</table>
  </div>	
</div>
<jsp:include page="../../../page/table1/pagenation.jsp" flush="true"/>			
</body>
</html>
