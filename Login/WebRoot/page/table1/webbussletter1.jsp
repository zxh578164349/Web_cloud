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
  <div id="content">
	<table class="table table-striped table-hover table-bordered">
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/webbussletterSaveOrUpdate.jsp')" /></s:if>	
		<span id="h2_title">人員出差申請書</span>
		</h2>
		<thead>			
			<tr class="tr_show">
			    <th>序號</th>
				<th>單號</th>
				<th>所屬單位</th>
				<th>姓名</th>
				<th>職務</th>
				<th>職務代理人</th>
				<th>廠別</th>
				<th>出差地點</th>
				<th>出差時間</th>
				<th>去程班機時間</th>								
				<th>回程班機時間</th>				
				<s:if test='#session.loginUser.userread!="1"'>				
				<th>操作</th>
				</s:if>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		  
		     <tr> 
		        <td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="blNo"/></td>
				<td><s:property value="unit" /></td>
				<td><s:property value="username" /></td>
				<td><s:property value="position" /></td>
				<td><s:property value="GAgent"/></td>
				<td><s:property value="factNo"/></td>				
				<td><s:property value="address" /></td>
				<td>
				<s:date name="dateFrom" format="yyyyMMdd"/>至<s:date name="dateEnd" format="yyyyMMdd"/>
				(<s:property value="%{sumDate(dateFrom,dateEnd)}"/>天)
				</td>
				<td><s:date name="timeFrom" format="yyyyMMdd-HH:mm" /></td>
				<td><s:date name="timeEnd" format="yyyyMMdd-HH:mm"/></td>		
														
				<td>
				
				<s:if test='#session.loginUser.userread!="1"'>
					<form action="bussletter_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='blNo'/>"
							name="billNo" /> 						
					</form> 
					<form action="bussletter_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='blNo'/>"
							name="billNo" /> 						
					</form>
					   <s:if test="vbm.lastMk==null">
					      <a href="javascript:findById_form('subform${x.index}','bussletter_findById')" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					      <a href="javascript:isDelete('2subform${x.index}','bussletter_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					   </s:if>
					   <s:else>					      
					      <s:if test="#session.loginUser.username=='admin'">
					          <a href="javascript:findById_form('subform${x.index}','bussletter_findById')" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>	
					          <a href="javascript:isDelete('2subform${x.index}','bussletter_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					      </s:if>
					      <s:else>
					         <a ><img alt="修改" src="images/icon/edit001_1.png" title="修改" ></a>
					         <s:if test='vbm.itemLast=="01"'>
					           <a href="javascript:isDelete('2subform${x.index}','bussletter_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					         </s:if>
					         <s:else>
					           <a ><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除" ></a>
					         </s:else>
					          		           
					      </s:else>						  																									
					      
					   </s:else>										
					 </s:if>
					 
					  <form action="bussletter_print2" method="post" id="3subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='blNo'/>" name="billNo" />										
						<input type="hidden" value="<s:property value='visaSort'/>" name="visaSort"/>
						<input type="hidden" value="look" name="lookordown"/>											
					  </form>
					   <form action="bussletter_print2" method="post" id="4subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='blNo'/>" name="billNo" />							
						<input type="hidden" value="<s:property value='visaSort'/>" name="visaSort"/>
						<input type="hidden" value="down" name="lookordown"/>											
					  </form>					  
					  <!--<a href="javascript:showDiv('<s:property value='blNo'/>','<s:property value='factNo'/>')" onclick=""><img alt="查看" src="images/icon/view002.png" title="查看" ></a>  -->					  					  
					 <a href="javascript:document.getElementById('3subform${x.index}').submit()"><img alt="預覽" src="images/icon/view001.png" title="預覽" ></a>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="打印" src="images/icon/print001.png" title="打印" ></a>						 
				</td>
			</tr>
		  
		 			
		</s:iterator>
		
		</tbody>
	</table>
	</div>
</div>	
<jsp:include page="pagenation.jsp" flush="true"/>	
	
	
</body>
</html>
