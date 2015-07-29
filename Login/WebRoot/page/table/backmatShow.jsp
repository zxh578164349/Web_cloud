<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>My JSP 'backmatShow.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />


</head>

<script>
	function pages(page) {
		$.ajax({
			type : "POST",
			dataType : "Html",
			url : "backmat_getList1",
			data : "page=" + page,
			success : function(msg) {
				$("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	var defaultColor = "#97CBFF";
	var clickColor = "#CCFFFF";
	function click_color(obj) {
		var tbody = document.getElementById("tbody");
		var length = document.getElementById("tbody").rows.length;
		for ( var i = 0; i < length; i++) {
			tbody.rows[i].style.backgroundColor = defaultColor;
		}
		obj.style.backgroundColor = clickColor;
	}
	function move(obj) {
		obj.style.backgroundColor = defaultColor;
	}
	function showPage(){
	    var j=jQuery.noConflict();
	    j("#divpage").toggle(200,function(){
	        if(document.getElementById("a_page").innerHTML=="▽"){
	          document.getElementById("a_page").innerHTML="△";
	        }else{
	           document.getElementById("a_page").innerHTML="▽";
	        }
	    });	   
	}
</script>
<body>
	<div id="bodyid">
		<table id="tb" bgColor=#97CBFF border=1
			style="margin:5px 0px 25px 0px;border-collapse:collapse;">
			<caption>回收粉使用統計</caption>
			<thead>				
				<tr class="tr_show">
					<td>廠別</td>
					<td>單號</td>
					<td>年月</td>
					<td>廠別狀態</td>
					<td>回收粉（黑）</td>
					<td>回收粉（白）</td>
					<td>生膠回收粉</td>
					<td>回收粉（灰）</td>
					<td>日期起</td>
					<td>日期止</td>
					<td>鎖定</td>
					<td>輸入者工號</td>
					<td>輸入時間</td>
				</tr>
			</thead>
			<tbody id="tbody">
				<s:iterator value="#attr.backmatList">
					<tr onmousemove="click_color(this)" onmouseout="move(this)">
						<td><s:property value="id.factNo" /></td>
						<td><s:property value="id.billNo" /></td>
						<td><s:date name="id.yymm" format="yyyyMM" /></td>
						<td><s:property value="factCode" /></td>
						<td><s:property value="backMat1" /></td>
						<td><s:property value="backMat2" /></td>
						<td><s:property value="backMat3" /></td>
						<td><s:property value="backMat4" /></td>
						<td><s:property value="dateB" /></td>
						<td><s:property value="dateE" /></td>
						<td><s:property value="lockMk" /></td>
						<td><s:property value="userNo" /></td>
						<td><s:property value="dateTime" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<hr />
		<center id="center_page">
	　　<a href="javascript:pages(1)">首頁</a>
	    <a href="javascript:pages(<s:property value="pages.page"/>-1)">上一頁</a>	    
	        (第<s:property value="pages.page" />頁 <a href="javascript:void(0)" onclick="showPage()" id="a_page">▽</a>|共<s:property value="pages.pageSize" />頁)
	           <div id="divpage">
	               <c:forEach begin="1"  end="${pages.pageSize}" var="id">
	                   <a href="javascript:pages(${id})">${id}</a>
	               </c:forEach>
	           </div>	  
	    <a href="javascript:pages(<s:property value="pages.page"/>+1)">下一頁</a>
	    <a href="javascript:pages(<s:property value="pages.pageSize"/>)">尾頁</a>		
	</center>
	</div>
</body>
</html>
