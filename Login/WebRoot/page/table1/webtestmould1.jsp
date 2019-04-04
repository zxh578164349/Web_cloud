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
		<span id="h2_title">每日開發試模登記表</span>
		</h3>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>日期</th>
				<th>客戶</th>								
				<th>品牌</th>				
				<th>季節</th>								
				<th>型體</th>
				<th>部件</th>
				<th>量產工廠</th>								
				<th>型體負責人</th>				
				<th>試模雙數</th>								
				<th>不良數</th>							
				<th>每雙料重</th>	
				<s:if test='#session.loginUser.userread!="1"'>			
				<th>操作</th>
				</s:if>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		  
		     <tr > 
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>																
				<td><s:property value="tdate" /></td>
				<td><s:property value="customer"/></td>
				<td><s:property value="brand" /></td>		        
				<td><s:property value="season"/></td>
				<td><s:property value="shape" /></td>	
				<td><s:property value="modelno" /></td>
				<td><s:property value="factname"/></td>
				<td><s:property value="picMan" /></td>		        
				<td><s:property value="nums"/></td>
				<td><s:property value="unhealthNums" /></td>
				<td><s:property value="weights" /></td>															
				<td>				
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
					         <%-- <a href="javascript:showDivList('${temp.id.factNo}','${temp.billNo}')" ><img alt="函文細項" src="images/icon/list_menu.png" title="函文細項"></a> --%>
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
					  <!-- <a href="javascript:showDiv('<s:property value='billNo'/>','<s:property value='id.factNo'/>')" onclick=""><img alt="查看" src="images/icon/view002.png" title="查看" ></a> -->
					 <a href="javascript:document.getElementById('3subform${x.index}').submit()"><img alt="預覽" src="images/icon/view001.png" title="預覽" ></a>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="打印" src="images/icon/print001.png" title="打印" ></a>
					 
					 <s:if test="#session.loginUser.id==1">
					  <a href="javascript:isDelete('subform${x.index}','webcolpro_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					 </s:if>						 
				</td>
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
