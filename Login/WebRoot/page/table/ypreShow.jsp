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
			url : "ypre_findPageBean3_print",
			data : "page=" + page,
			success : function(msg) {
				$("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
	var defaultColor="#97CBFF";
	 var clickColor="#CCFFFF";
	 function click_color(obj){
        var tbody=document.getElementById("tbody");
        var length=document.getElementById("tbody").rows.length;
        for(var i=0;i<length;i++){
            tbody.rows[i].style.backgroundColor=defaultColor;
        }
        obj.style.backgroundColor=clickColor;        
    }
      function move(obj){
     obj.style.backgroundColor=defaultColor;
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
			<thead>
				<tr>
					<td class="tr_show" colspan="13"><span
						style="margin-left:370px;">產量預估</span>
					</td>
				</tr>
				<tr class="tr_show">
					<td>序號</td>
					<td>廠別</td>
					<td>廠別狀態</td>
					<td>年月</td>
					<td>戰力分析模數</td>
					<td>預計生產模數</td>
					<td>預計請款雙數</td>
					<td>總機孔</td>
					<td>正批</td>
					<td>工程/樣品</td>
					<td>輔料</td>
					<td>其他</td>
					<td>輸入者</td>
				</tr>
			</thead>
			<tbody id="tbody">
			<s:iterator value="bean.list" status="x">
				<tr onmousemove="click_color(this)" onmouseout="move(this)">
					<td>${ 12*(bean.currentPage-1)+x.index+1}</td>
					<td><s:property value="id.factNo" /></td>
					<td><s:property value="id.factCode" /></td>
					<td><s:date name="id.yymm" format="yyyyMM" /></td>
					<td><s:property value="%{formatDouble(forceAnalysis)}" /></td>
					<td><s:property value="%{formatDouble(expectedProduction)}" />
					</td>
					<td><s:property value="%{formatDouble(expectedPayment)}" /></td>
					<td><s:property value="%{formatDouble(hole)}" /></td>
					<td><s:property value="%{formatDouble(positiveNumber)}" /></td>
					<td><s:property value="%{formatDouble(sample)}" /></td>
					<td><s:property value="%{formatDouble(accessories)}" /></td>
					<td><s:property value="%{formatDouble(other)}" /></td>
					<td><s:property value="username" />
					</td>
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
