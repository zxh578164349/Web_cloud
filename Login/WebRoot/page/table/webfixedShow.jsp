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
			url : "fix_findPageBean3_print",
			data : "page=" + page,
			success : function(msg) {
				$("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}

	function click_color(obj) {
		var defaultColor = "#97CBFF";
		var clickColor = "#CCFFFF";
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
		<!-- <form id="selectForm" action="backmat_exportexcel" method="post">
			<select id="select" name="printType">
				<option value="0">請選擇打印格式</option>
				<option value="pdf">PDF</option>
				<option value="excel">EXCEL</option>
				<option value="word">WORD</option>
				<option value="html">HTML</option>
			</select> <input value="打印" type="button" onclick="check()" />
		</form> -->
		<table id="tb" bgColor=#97CBFF border=1
			style="margin:5px 0px 25px 0px;border-collapse:collapse;">
			<thead>
				<tr>
					<td class="tr_show" colspan="23"><span
						style="margin-left:370px;">固定資產</span>
					</td>
				</tr>
				<tr class="tr_show">
					<td>序號</td>
					<td>廠別</td>
					<td>廠別狀態</td>
					<td>廠內財編</td>
					<td>財產編號</td>
					<td>名稱</td>
					<td>大分類ID</td>
					<td>大分類名稱</td>
					<td>小分類ID</td>
					<td>小分類名稱</td>
					<td>購入單價</td>
					<td>型號</td>
					<td>品牌</td>
					<td>產地</td>
					<td>單位ID</td>
					<td>單位名稱</td>
					<td>增加方式ID</td>
					<td>增加方式名稱</td>
					<td>折舊計算方式ID</td>
					<td>折舊計算方式名稱</td>
					<td>區域</td>
					<td>進廠日期</td>
					<td>是否可刪除</td>
				</tr>
			</thead>
			<tbody id="tbody">
				<s:iterator value="bean.list" status="x" id="temp">

					<tr onclick="click_color(this)">
						<td>${ 25*(bean.currentPage-1)+x.index+1}</td>
						<td><s:property value="factNo" />
						</td>
						<td><s:property value="factcode" />
						</td>
						<td><s:property value="fixedId" />
						</td>
						<td><s:property value="assetscoding" />
						</td>
						<td><s:property value="assetname" />
						</td>
						<td><s:property value="majorId" />
						</td>
						<td><s:property value="majorName" />
						</td>
						<td><s:property value="subId" />
						</td>
						<td><s:property value="subName" />
						</td>
						<td><s:property value="%{formatDouble(priceIn)}" />
						</td>
						<td><s:property value="model" />
						</td>
						<td><s:property value="brand" />
						</td>
						<td><s:property value="manufacturer" />
						</td>
						<td><s:property value="unitsId" />
						</td>
						<td><s:property value="unitsName" />
						</td>
						<td><s:property value="addwaysId" />
						</td>
						<td><s:property value="addwaysName" />
						</td>
						<td><s:property value="methodId" />
						</td>
						<td><s:property value="methodName" />
						</td>
						<td><s:property value="areaName" />
						</td>
						<td><s:property value="addTime" />
						</td>
						<td><s:if test='delMk=="Y"'>
				  是
				</s:if> <s:if test='delMk=="N"'>
				  否
				</s:if></td>

					</tr>

				</s:iterator>
			</tbody>
		</table>
		<hr />
		<center id="center_page">
	　　<a href="javascript:pages(0)">首頁</a>
	    <a href="javascript:pages(<s:property value='bean.currentPage'/>-1)">上一頁</a>	    
	        (第<s:property value="bean.currentPage" />頁 <a href="javascript:void(0)" onclick="showPage()" id="a_page">▽</a>|共<s:property value="bean.totalPage" />頁)
	           <div id="divpage">
	               <c:forEach begin="1"  end="${bean.totalPage}" var="id">
	                   <a href="javascript:pages(${id })">${id}</a>
	               </c:forEach>
	           </div>	  
	    <a href="javascript:pages(<s:property value='bean.currentPage'/>+1)">下一頁</a>
	    <a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a>		
	</center>
	</div>
</body>
</html>
