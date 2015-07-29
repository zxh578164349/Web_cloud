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
			url : "webcost_findPageBean3_print",
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
					<td class="tr_show" colspan="21"><span
						style="margin-left:370px;">資材資料(每月輸入)</span></td>
				</tr>
				<tr class="tr_show">
					<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>				
				<th>當月實際耗料(KG)</th>
				<th>當月生產膠底總毛重(KG)</th>
				<th>當月生產膠底總淨重(KG)</th>
				<th>非膠底耗用重量(KG)</th>
				<th>生產產值(USD)</th>
				<th>當月生產成本金額(USD)</th>
				<th>總原料庫存量(KG)</th>
				<th>總原料庫存金額(USD)</th>
				<th>膠類庫存量(KG)</th>
				<th>膠類庫存金額(USD)</th>
				<th>色料用量(KG)</th>
				<th>藥品用量(KG)</th>
				<th>離型劑用量金額(USD)</th>
				<th>白色回收粉(KG)</th>
				<th>黑色回收粉(KG)</th>
				<th>生膠回收粉(KG)</th>
				<th>灰色回收粉(KG)</th>
				<th>其它回收粉(KG)</th>				
				<th>輸入者</th>
				</tr>
			</thead>
			<s:iterator value="bean.list" status="x" id="temp">
				<tr>
					<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td><s:property value="id.factCode" /></td>
				<td><s:date name="id.yymm" format="yyyyMM" /></td>
				<td><s:property value="%{formatDouble(actlost)}" /></td>
				<td><s:property value="%{formatDouble(avgbuttomweight)}" /></td>
				<td><s:property value="%{formatDouble(avgbuttomweight2)}" /></td>
				<td><s:property value="%{formatDouble(noGlueWeight)}"/></td>
				<td><s:property value="%{formatDouble(productedNum)}"/></td>
				<td><s:property value="%{formatDouble(avgprice)}" /></td>
				<td><s:property value="%{formatDouble(totalstore)}" /></td>
				<td><s:property value="%{formatDouble(totalstoremoney)}" />
				</td>
				<td><s:property value="%{formatDouble(gluestore)}" />
				</td>
				<td><s:property value="%{formatDouble(gluestoremoney)}" />
				</td>

				<td><s:property value="%{formatDouble(colorused)}" />
				</td>
				<td><s:property value="%{formatDouble(drugsused)}" />
				</td>
				<td><s:property value="%{formatDouble(leavemoney)}" />
				</td>
				<td><s:property value="%{formatDouble(whitenum)}" />
				</td>
				<td><s:property value="%{formatDouble(blacknum)}" />
				</td>
				<td><s:property value="%{formatDouble(gluenum)}" />
				</td>
				<td><s:property value="%{formatDouble(greynum)}" />
				</td>
				<td><s:property value="%{formatDouble(othernum)}" />
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
