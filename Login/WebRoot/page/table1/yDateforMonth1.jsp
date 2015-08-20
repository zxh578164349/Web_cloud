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
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
	<script type="text/javascript">
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
</head>
<body>
  <div id="container">
  <div id="content">
	<table id="tb" >
		<caption>產量資料(月統計)</caption>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>年月</th>
				<th>上模數(月合計)</th>
				<th>人數(月合計)</th>
				<th>標準產量(月合計)</th>
				<th>實際產量(月合計)</th>
				<th>天數(月合計)</th>
				<th>實際生產雙數(月合計)</th>
				<th>客補生產雙數(月合計)</th>
				<th>廠補生產雙數(月合計)</th>
				<th>樣品生產雙數(月合計)</th>
				<th>出貨數(月合計)</th>
				<th>退貨數(月合計)</th>
				<th>上模總工時(月合計)</th>				
				<th>達成率(%)</th>

			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr onmousemove="click_color(this)" onmouseout="move(this)">
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td>${temp[0]}</td>
				<td>${temp[1]}</td>
				<td>${temp[2]}</td>
				<td><s:property value="%{formatDouble(#attr.temp[3])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[4])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[5])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[6])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[7])}" />
				</td>
				
				<td><s:property value="%{formatDouble(#attr.temp[8])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[9])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[10])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[11])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[12])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[13])}" />
				</td>
				<td><s:property value="%{formatDouble(#attr.temp[14])}" />
				</td>				
				<td><s:property
						value="%{formatDouble(100*#attr.temp[6]/#attr.temp[5])}" />%</td>				
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>	
	<%-- <hr />
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
	</center> --%>
	
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
