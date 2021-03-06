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
		onclick="loadUrl('saveAndUpdate/webremittancelistSaveOrUpdate.jsp')" /></s:if>
		<span id="h2_title">加伟鞋材对公费用汇款清单</span>
		</h3>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>
				<th>函文單號</th>
				<th>函文類型</th>
				<th>匯款帳號</th>
				<th>銀行名稱</th>
				<th>申請者</th>				
				<s:if test='#session.loginUser.userread!="1"'>				
				<th>操作</th>
				</s:if>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		  
		     <tr> 
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>							
				<td><s:property value="factNo" /></td>
				<td><s:property value="factCode" /></td>
				<td><s:property value="yymm"/></td>
				<td><s:property value="billNo" /></td>				
				<td><s:property value="webtype.typeName"/></td>
				<td><s:property value="fromAccount"/></td>				
				<td><s:property value="fromBank" /></td>
				<td><s:property value="username" /></td>															
				<td>				
				<s:if test='#session.loginUser.userread!="1"'>
					<form action="webremit_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='billNo'/>" name="billNo" />													
					</form> 
					<form action="webremit_delete" method="post" id="2subform${x.index}" style="float:left">						
						 <input type="hidden" value="<s:property value='billNo'/>" name="billNo" />													 						
					</form>
					<s:if test="vbm.lastMk==null">
					    <a href="javascript:findById_form('subform${x.index}','webremit_findById')" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					    <a href="javascript:isDelete('2subform${x.index}','webremit_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>	
					</s:if>
					<s:else>
					   <s:if test="#session.loginUser.username=='admin'">
					         <a href="javascript:findById_form('subform${x.index}','webremit_findById')" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					         <a href="javascript:isDelete('2subform${x.index}','webremit_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					      </s:if>
					      <s:else>
					        <a ><img alt="修改" src="images/icon/edit001_1.png" title="修改" ></a>	
					        <s:if test='vbm.itemLast=="01"'>
					           <a href="javascript:isDelete('2subform${x.index}','webremit_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					        </s:if>
					        <s:else>
					          <a ><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除" ></a>
					        </s:else>
					          					  																									
					          
					      </s:else>
					</s:else> 									
				</s:if>
					 
					  <form action="webremit_print" method="post" id="3subform${x.index}" style="float:left" target="_blank">
						 <input type="hidden" value="<s:property value='billNo'/>" name="billNo" />
						 <input type="hidden" value="<s:property value='webtype.id.factNo'/>" name="factNo"/>
						 <input type="hidden" value="<s:property value='visaType'/>" name="visaType"/>
						 <input type="hidden" value="look" name="lookordown"/>											
					  </form>
					   <form action="webremit_print" method="post" id="4subform${x.index}" style="float:left" target="_blank">
						 <input type="hidden" value="<s:property value='billNo'/>" name="billNo" />	
						 <input type="hidden" value="<s:property value='webtype.id.factNo'/>" name="factNo"/>
						 <input type="hidden" value="<s:property value='visaType'/>" name="visaType"/>													
						 <input type="hidden" value="down" name="lookordown"/>											
					  </form>					  
					 <a href="javascript:document.getElementById('3subform${x.index}').submit()"><img alt="預覽" src="images/icon/view001.png" title="預覽" ></a>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="打印" src="images/icon/print001.png" title="打印" ></a>	
					 <a href="javascript:showDivList('${temp.billNo}')" ><img alt="清單內容" src="images/icon/list_menu.png" title="清單內容"></a>					 
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
