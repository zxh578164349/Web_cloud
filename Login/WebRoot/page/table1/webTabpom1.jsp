
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
        <h3>
		<!-- <s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/WebTabpomSaveOrUpdate.jsp')" />
	   </s:if>	 -->
		<span id="h2_title">實驗室形體物性</span>
		</h3>
	<table class="table table-striped table-hover table-bordered" >
		
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>物性編號</th>
				<th>配方索引</th>
				<th>品牌</th>
				<th>硬度</th>
				<th>拉力</th>
				<th>延伸</th>
				<th>C型撕裂</th>
				<th>褲型撕裂</th>
				<th>比重</th>
				<th>AKRON耐磨</th>
				<th>DIN耐磨</th>
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
				<td><s:property value="pomNo" /></td>
				<td><s:property value="formulaId.formulaIndex"/></td>								
				<td><s:property value="webBrank.name"/></td>
				<td><s:property value="hardness" /></td>
				<td><s:property value="forces" /></td>
				<td><s:property value="extend" /></td>
				<td><s:property value="tearingC" /></td>
				<td><s:property value="tearingK" /></td>
				<td><s:property value="proportion" /></td>
				<td><s:property value="wresistingAkron"/></td>
				<td><s:property value="wresistingDin"/></td>	
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webtabpom_findById" method="post" id="subform${x.index}">						
						<input type="hidden" value="<s:property value='pomNo'/>" name="pomNo" />							 
					</form> 
					<form action="webtabpom_delete" method="post" id="2subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='pomNo'/>" name="pomNo" />							
					</form>
					<form action="webtabpom_findByIdfiles" method="post" id="3subform${x.index}" style="float:left" target="_blank">						
						<input type="hidden" value="<s:property value='pomNo'/>" name="pomNo" />							
					</form>
					
					<s:if test="formulaId.vbm==null">
					   <a href="javascript:findById_form('subform${x.index}','webtabpom_findById')" class="btn btn-xs btn-success">					
					             修改</a>
					    <a href="javascript:isDelete('2subform${x.index}','webtabpom_delete')" class="btn btn-xs btn-success">					
					            刪除</a>         
					</s:if>
					<s:else>
					  <s:if test='#session.loginUser.adminMk=="Y"'>
					    <a href="javascript:findById_form('subform${x.index}','webtabpom_findById')" class="btn btn-xs btn-success">					
					             修改</a>
					    <a href="javascript:isDelete('2subform${x.index}','webtabpom_delete')" class="btn btn-xs btn-success">					
					            刪除</a>  
					  </s:if>
					  <s:else>
					    <a href="#" class="btn btn-xs disabled btn-warning"> 鎖定 </a>
					   <a href="#" class="btn btn-xs disabled btn-warning"> 刪除 </a>
					  </s:else>
					   
					</s:else> 
					
					<a href="javascript:document.getElementById('3subform${x.index}').submit()" class="btn btn-xs btn-success" target="_blank">
					附檔</a>					
					
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
