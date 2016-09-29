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
	<table class="table table-striped table-hover table-bordered" >
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
	     <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/matterApplicationSaveOrUpdate.jsp')" /></s:if>
		函文申請
		</h2>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>標題</th>
				<th>電話</th>
				<th>申請單位</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>建立日期</th>
				<th>申請者</th>
				<th>申請單號</th>
				<th>是否緊急</th>
				<th>類別</th>				
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
				<td><s:property value="memoSmk" /></td>
				<td><s:property value="telNo" /></td>
				<td><s:property value="secNo" /></td>
				<td><s:property value="id.factNo"/></td>
				<td><s:property value="factCode"/></td>				
				<td><s:date name="timeCreate" format="yyyyMMdd" /></td>
				<td><s:property value="userNm" /></td>
				<td><s:property value="id.billNo" /></td>
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
					      <s:if test="#session.loginUser.username=='admin'">
					         <a href="javascript:findById_form('subform${x.index}','kyz_findById')" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					         <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}','kyz_delete')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					         <a href="javascript:showDivList('${temp.id.factNo}','${temp.id.billNo}')" ><img alt="函文細項" src="images/icon/list_menu.png" title="函文細項"></a>
					      </s:if>
					      <s:else>
					          <a ><img alt="修改" src="images/icon/edit001_1.png" title="修改" ></a>	
					          <s:if test='vbm.itemLast=="01"'>
					            <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}','kyz_delete')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					          </s:if>
					          <s:else>
					            <a ><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除" ></a>
					          </s:else>					  																									
					          
					          <a ><img alt="函文細項" src="images/icon/list_menu_1.png" title="函文細項"></a>
					      </s:else>				      
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
