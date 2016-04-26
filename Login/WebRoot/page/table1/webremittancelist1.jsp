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

<!-- 分頁樣式 -->
<!-- <link rel="stylesheet" type="text/css" href="css/mystyle.css" />-->


<script type="text/javascript">

</script>
</head>
<body>
  <div id="container">
  <div id="content">
	<table class="table table-striped table-hover table-bordered" >
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
	     <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/webremittancelistSaveOrUpdate.jsp')" /></s:if>
		加伟鞋材对公费用汇款清单
		</h2>
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
		     <tr > 
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="factNo" /></td>
				<td><s:property value="factCode" /></td>
				<td><s:property value="billNo" /></td>
				<td><s:property value="webtype.typeName"/></td>
				<td><s:property value="fromAccount"/></td>				
				<td><s:property value="fromBank" /></td>
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
					  <a href="javascript:findById('subform${x.index}','kyz_findById')" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					  <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>										
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
		
	<ul class="pagination" style="padding-left:42%">
		    <li><a href="javascript:pages(0)">首頁</a></li>
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>-1)">&laquo;</a></li>			
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>)"><s:property value='bean.currentPage'/></a></li>
			<s:if test="bean.currentPage+1==bean.totalPage||bean.currentPage+1<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+1)"><s:property value='bean.currentPage+1'/></a></li>
			</s:if>
			<s:if test="bean.currentPage+2==bean.totalPage||bean.currentPage+2<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+2)"><s:property value='bean.currentPage+2'/></a></li>
			</s:if>
			<s:if test="bean.currentPage+3==bean.totalPage||bean.currentPage+3<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+3)"><s:property value='bean.currentPage+3'/></a></li>
			</s:if>
			<s:if test="bean.currentPage+4==bean.totalPage||bean.currentPage+4<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+4)"><s:property value='bean.currentPage+4'/></a></li>
			</s:if>									
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>+1)">&raquo;</a></li>
			<li><a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a></li>			
		</ul>	
				
	<hr>
</body>
</html>
