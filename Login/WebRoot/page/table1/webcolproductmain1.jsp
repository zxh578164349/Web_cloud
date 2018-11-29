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
		<s:if test='#session.loginUser.userread!="1"'>
	     <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/matterApplicationSaveOrUpdate.jsp')" /></s:if>
		<span id="h2_title">函文申請</span>
		</h3>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>申請單號</th>				
				<th>日期</th>
				<th>重要性</th>
				<th>型體</th>
				<th>結構</th>
				<th>廠別</th>				
				<th>下單人</th>
				<th>樣品用途</th>				
				<th>數量</th>				
				<th>單重</th>
				<th>留底量</th>				
				<th>不良</th>
				<th>型體負責人</th>				
				<th>可否請款</th>
				<th>量產數量</th>				
				<th>需求料的重量</th>
				<th>備註</th>				
				<s:if test='#session.loginUser.userread!="1"'>				
				<th>操作</th>
				</s:if>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		  
		     <tr > 
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>																
				<td><s:property value="billNo" /></td>
				<td><s:property value="colDate" /></td>
				<td><s:property value="importmant" /></td>
				<td><s:property value="shape"/></td>
				<td><s:property value="CStructure"/></td>				
				<td><s:property value="webColproductMain"/></td>
				<td><s:property value="userNm" /></td>
				<td><s:property value="id.billNo" /></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td><s:property value=""/></td>
				<td>
				  <s:if test="emerWhether==0">
				    　　 是
				  </s:if>
				  <s:if test="emerWhether==1">
				    　　 否
				  </s:if>				  
				</td>
				<td>
				<s:property value="colTemp"/>						
				</td>							
				<td><s:property value="username" /></td>
				<td>				
				<s:if test='#session.loginUser.userread!="1"'>
					<form action="kyz_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" />						
					</form> 
					<form action="kyz_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" />
							<input type="hidden" value="<s:property value='visaType'/>" name="visaSort"/>						
					</form>					
					   <s:if test="vbm.lastMk==null">
					     <a href="javascript:findById_form('subform${x.index}','kyz_findById')" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					     <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}','kyz_delete')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					     <a href="javascript:showDivList('${temp.id.factNo}','${temp.id.billNo}')" ><img alt="函文細項" src="images/icon/list_menu.png" title="函文細項"></a>
					   </s:if>					   				   
					   <s:else>					   
					      <a ><img alt="修改" src="images/icon/edit001_1.png" title="修改" ></a>
					      <s:if test='#session.loginUser.adminMk=="Y"'>				         						  																									
					         <s:if test='vbm.visaMk=="N"'>
					           <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}','kyz_delete')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					         </s:if>
					         <s:else>
					           <a ><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除" ></a>
					         </s:else>
					         <%-- <a href="javascript:showDivList('${temp.id.factNo}','${temp.id.billNo}')" ><img alt="函文細項" src="images/icon/list_menu.png" title="函文細項"></a> --%>
					      </s:if>
					      <s:else>					          						          
					          <a ><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除" ></a>					          				  																														          					          
					      </s:else>
					      	<a><img alt="函文細項" src="images/icon/list_menu_1.png" title="函文細項"></a>			      
					   </s:else>										
					 </s:if>
					 
					  <form action="kyz_print2" method="post" id="3subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="billNo" />
							<input type="hidden" value="<s:property value='factCode'/>" name="factCode"/>
							<input type="hidden" value="<s:property value='visaType'/>" name="visaSort"/>
							<input type="hidden" value="look" name="lookordown"/>											
					  </form>
					   <form action="kyz_print2" method="post" id="4subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="billNo" />
							<input type="hidden" value="<s:property value='factCode'/>" name="factCode"/>
							<input type="hidden" value="<s:property value='visaType'/>" name="visaSort"/>
							<input type="hidden" value="down" name="lookordown"/>											
					  </form>
					  <!-- <a href="javascript:showDiv('<s:property value='id.billNo'/>','<s:property value='id.factNo'/>')" onclick=""><img alt="查看" src="images/icon/view002.png" title="查看" ></a> -->
					 <a href="javascript:document.getElementById('3subform${x.index}').submit()"><img alt="預覽" src="images/icon/view001.png" title="預覽" ></a>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="打印" src="images/icon/print001.png" title="打印" ></a>
					 
					 <s:if test="#session.loginUser.id==1">
					  <a href="javascript:isDelete('2subform${x.index}','kyz_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
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
