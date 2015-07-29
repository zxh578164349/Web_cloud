<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'ydataShow.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />

<script>
	function pages(page) {
		$.ajax({
			type : "POST",
			dataType : "Html",
			url : "webpersonnum_findPageBean3_print",
			data : "page=" + page,
			success : function(msg) {
				$("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
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

</head>

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
					<td class="tr_show" colspan="11"><span
						style="margin-left:370px;">回頭料(每月輸入)</span>
					</td>
				</tr>
				<tr class="tr_show">
					<td>序號</td>
					<td>廠別</td>
					<td>廠別狀態</td>
					<td>日期</td>
					<td>直工應到人數</td>
					<td>直工實到人數</td>
					<td>間工應到人數</td>
					<td>間工實到人數</td>
					<td>外掛編應到人數</td>
					<td>外掛編實到人數</td>
					<td>輸入者</td>
				</tr>
			</thead>
			<s:iterator value="bean.list" status="x" id="temp">
				<tr>
					<td>${2*(bean.currentPage-1)+x.index+1}</td>
					<td><s:property value="id.factNo" /></td>
					<td><s:property value="id.factCode" /></td>
					<td><s:date name="id.yymmdd" format="yyyyMMdd" /></td>
					<td><s:property value="%{formatDouble(standardnumzg)}" /></td>
					<td><s:property value="%{formatDouble(realnumzg)}" /></td>
					<td><s:property value="%{formatDouble(standardnumjg)}" /></td>
					<td><s:property value="%{formatDouble(realnumjg)}" /></td>
					<td><s:property value="%{formatDouble(outstandardnum)}" /></td>
					<td><s:property value="%{formatDouble(outrealnum)}" />
					</td>
					<td><s:property value="username" /></td>
				</tr>
			</s:iterator>
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
