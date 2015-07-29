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
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			tiptype : 1,
		//showAllError : true,
		});

	});
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

	<table id="tb" bgColor=#97CBFF border=1
		style="margin:5px 0px 25px 0px;border-collapse:collapse;">
		<caption>產量資料</caption>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>
				<th>上模數</th>
				<th>人數</th>
				<th>標準產量</th>
				<th>實際產量</th>
				<th>達成率(%)</th>
				<th>輸入者</th>
				<th>操作</th>
			</tr>
		</thead>
		<s:iterator value="bean.list" status="x" id="temp">
			<tr>
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" />
				</td>
				<td><s:property value="id.factCode" />
				</td>
				<td><s:date name="id.yymmdd" format="yyyyMMdd" />
				</td>
				<td><s:property value="%{formatDouble(onModulus)}" />
				</td>
				<td><s:property value="%{formatDouble(personnum)}" />
				</td>
				<td><s:property value="%{formatDouble(standardOutput)}" />
				</td>
				<td><s:property value="%{formatDouble(actualYield)}" />
				</td>
				<td><s:property value="%{formatDouble_percent(achievingRate)}" />
				</td>
				<td><s:property value="username" />
				</td>
				<td>
					<form action="ydata_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form> <a
					href="javascript:document.getElementById('subform${x.index}').submit()"
					onclick="">修改</a>

					<form action="ydata_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}')">刪除</a></td>
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
	<hr>

</body>


</html>
