
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>My JSP 'backmat1.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
  <div id="container">
        <h2>
		<s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/WebTabpomSaveOrUpdate.jsp')" />
	   </s:if>	
		實驗室形體物性
		</h2>
	<table class="table table-striped table-hover table-bordered" >
		
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>物性編號</th>
				<th>物料名稱</th>
				<th>指定料</th>
				<th>部件</th>
				<th>生產工廠</th>
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
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="pomNo" /></td>
				<td><s:property value="pomName"/></td>
				<td>
				  <s:if test='spematerial=="0"'>
				     是
				  </s:if>
				  <s:else>
				  否
				  </s:else>
				</td>
				<td><s:property value="component" /></td>
				<td>
				   <s:iterator value="webfacts">
				       <s:property value="factSname"/>&nbsp;
				   </s:iterator>
				</td>
				<td><s:property value="webBrank.BName"/></td>
				<td><s:property value="hardness" /></td>
				<td><s:property value="forces" /></td>
				<td><s:property value="extends_" /></td>
				<td><s:property value="tearingC" /></td>
				<td><s:property value="tearingK" /></td>
				<td><s:property value="proportion" /></td>
				<td><s:property value="wresistingAkron"/></td>
				<td><s:property value="wresistingDin"/></td>	
				<td><s:property value="userName" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webtabpom_findById" method="post" id="subform${x.index}">						
						<input type="hidden" value="<s:property value='pomNo'/>" name="pomNo" />							 
					</form> 
					<form action="webtabpom_delete" method="post" id="2subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='pomNo'/>" name="pomNo" />							
					</form>
					<form action="webtabpom_printOne" method="post" id="3subform${x.index}" style="float:left" target="_blank">						
						<input type="hidden" value="<s:property value='pomNo'/>" name="pomNo" />							
						<input type="hidden" value="look" name="lookordown"/>
					</form>
					<form action="webtabpom_printOne" method="post" id="4subform${x.index}" style="float:left" target="_blank">						
						<input type="hidden" value="<s:property value='pomNo'/>" name="pomNo" />							
						<input type="hidden" value="down" name="lookordown"/>
					</form> 
					<a href="javascript:findById('subform${x.index}','webtabpom_findById')" >					
					<img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					<a href="javascript:document.getElementById('3subform${x.index}').submit()" >
					<img alt="預覽" src="images/icon/view24.png" title="預覽"></a>
					<a href="javascript:document.getElementById('4subform${x.index}').submit()" >
					<img alt="下載" src="images/icon/print24.png" title="下載"></a>
					<a href="javascript:void(0)" onclick="isDelete('2subform${x.index}')">					
					<img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
				</td>
				</s:if>
			</tr>
		</s:iterator>
		</tbody>
	</table>
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
</body>
</html>
