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
  <div id="content">
	<table class="table table-striped table-hover table-bordered" >
		<h3>
		<!-- <s:if test='#session.loginUser.userread!="1"'>
	     <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/webcolproductSaveOrUpdate.jsp')" /></s:if> -->
		<span id="h2_title">原物料&粗胚進耗存登記表</span>
		</h3>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>類別</th>
				<th>物料名稱</th>
				<th>物性</th>								
				<th>單位</th>				
				<th>期初庫存</th>								
				<th>第1周進料</th>
				<th>第1周發料</th>
				<th>第2周進料</th>
				<th>第2周發料</th>
				<th>第3周進料</th>
				<th>第3周發料</th>
				<th>第4周進料</th>
				<th>第4周發料</th>
				<th>第5周進料</th>
				<th>第5周發料</th>
				<th>月底庫存</th>	
				<th>備註</th>										
				<!-- <s:if test='#session.loginUser.userread!="1"'>			
				<th>操作</th>
				</s:if>	 -->			
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		  
		     <tr > 
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="webMaterialregistrationform.mtype" /></td>																
				<td><s:property value="materielname" /></td>
				<td><s:property value="characteristic"/></td>
				<td><s:property value="unit" /></td>		        
				<td><s:property value="stocka"/></td>
				<td><s:property value="inumsa" /></td>	
				<td><s:property value="onumsa" /></td>
				<td><s:property value="inumsb"/></td>
				<td><s:property value="onumsb" /></td>
				<td><s:property value="inumsc"/></td>
				<td><s:property value="onumsc" /></td>
				<td><s:property value="inumsd"/></td>
				<td><s:property value="onumsd" /></td>
				<td><s:property value="inumse"/></td>
				<td><s:property value="onumse" /></td>		        
				<td><s:property value="stockb"/></td>
				<td><s:property value="remark" /></td>																		
				<%-- <td>				
				<s:if test='#session.loginUser.userread!="1"'>
					<form  method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='billNo'/>" name="billNo" /> 					
					</form> 									
					   <s:if test="vbm.lastMk==null">
					     <a href="javascript:findById_form('subform${x.index}','webcolpro_findByBillNo')" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					     <a href="javascript:void(0)" onclick="isDelete('subform${x.index}','webcolpro_delete')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>					     
					   </s:if>					   				   
					   <s:else>					   
					      <a ><img alt="修改" src="images/icon/edit001_1.png" title="修改" ></a>
					      <s:if test='#session.loginUser.adminMk=="Y"'>				         						  																									
					         <s:if test='vbm.visaMk=="N"'>
					           <a href="javascript:void(0)" onclick="isDelete('subform${x.index}','webcolpro_delete')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					         </s:if>
					         <s:else>
					           <a ><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除" ></a>
					         </s:else>
					        
					      </s:if>
					      <s:else>					          						          
					          <a ><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除" ></a>					          				  																														          					          
					      </s:else>					      				      
					   </s:else>										
					 </s:if>
					 
					  <form action="webcolpro_print2" method="post" id="3subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='visaType'/>" name="visaSort"/>
							<input type="hidden" value="look" name="lookordown"/>											
					  </form>
					   <form action="webcolpro_print2" method="post" id="4subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='billNo'/>" name="billNo" />							
							<input type="hidden" value="<s:property value='visaType'/>" name="visaSort"/>
							<input type="hidden" value="down" name="lookordown"/>											
					  </form>
					  
					 <a href="javascript:document.getElementById('3subform${x.index}').submit()"><img alt="預覽" src="images/icon/view001.png" title="預覽" ></a>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="打印" src="images/icon/print001.png" title="打印" ></a>
					 
					 <s:if test="#session.loginUser.id==1">
					  <a href="javascript:isDelete('subform${x.index}','webcolpro_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					 </s:if>
					 
					 						 
				</td> --%>
			</tr>
		  
		 			
		</s:iterator>
		
		</tbody>
	</table>
	</div>
</div>	
<jsp:include page="pagenation.jsp" flush="true"/>		
		
	<hr>
</body>
</html>
