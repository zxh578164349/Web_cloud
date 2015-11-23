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
	
<%--$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			tiptype : 1,
		//showAllError : true,
		});
	});
	--%>
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
	<table id="tb" >
		<caption>KPI年度目標</caption>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>年份</th>
				<th>當月產量(模)</th>				
				<th>月均回轉(回)</th>
				<th>時迴轉(模/H)</th>
				<th>廠補率(%)</th>
				<th>產能達成率(%)</th>
				<th>成倉庫存(雙)</th>
				<th>已出未請(雙)</th>
				<th>生產與請款差異率(%)</th>
				<th>全廠人均時產能(模/H)</th>
				<th>直工人均产能(模/人)</th>
				<th>全厂人均产能(模/人)</th>
				<th>人均產值(USD/人)</th>
				<th>人薪產值</th>
				<th>水用量单耗(噸/模)</th>
				<th>电度数单耗(度/模)</th>
				<th>蒸汽单耗(噸/模)</th>
				<th>修繕單耗(USD/模)</th>
				<th>主材料成本比率(%)</th>
				<th>邊料率(%)</th>
				<th>報廢率(%)</th>
				<th>全廠總損耗(%)</th>
				<th>無形損耗(%)</th>
				<th>直間比</th>
				<th>工傷件數(件)</th>
				<th>直工離職率(%)</th>
				<th>全廠離職率(%)</th>								
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr onmousemove="click_color(this)" onmouseout="move(this)">
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td><s:property value="id.factCode" /></td>
				<td><s:property value="id.yyyy" /></td>
				<td><s:property value="%{formatDouble(thisYield)}" /></td>
				<td><s:property value="%{formatDouble(avgCircle)}" /></td>
				<td><s:property value="%{formatDouble(avgCirclehour)}" /></td>
				<td><s:property value="%{formatPercent(factaddRate)}"/></td>
				<td><s:property value="%{formatPercent(productRate)}"/></td>
				<td><s:property value="%{formatDouble(storeNum)}" /></td>
				<td><s:property value="%{formatDouble(outRequest)}" /></td>
				<td><s:property value="%{formatPercent(outrequestRate)}" /></td>				
				<td><s:property value="%{formatDouble(avgFactpro)}" /></td>				
				<td><s:property value="%{formatDouble(avgZgpro)}" /></td>				
				<td><s:property value="%{formatDouble(avgPerpro)}" /></td>				
				<td><s:property value="%{formatDouble(avgPermoney)}" /></td>				
				<td><s:property value="%{formatDouble(permoney)}" /></td>				
				<td><s:property value="%{formatDouble(waterTon)}" /></td>				
				<td><s:property value="%{formatDouble(lightDu)}" /></td>				
				<td><s:property value="%{formatDouble(gasUsd)}" /></td>				
				<td><s:property value="%{formatDouble(wasteUsd)}" /></td>				
				<td><s:property value="%{formatPercent(mainRate)}" /></td>				
				<td><s:property value="%{formatPercent(sideRate)}" /></td>
				<td><s:property value="%{formatPercent(wasteRate)}" /></td>				
				<td><s:property value="%{formatPercent(wasteFact)}" /></td>				
				<td><s:property value="%{formatPercent(wasteNo)}" /></td>				
				<td><s:property value="%{formatDouble(zjRate)}" /></td>				
				<td><s:property value="%{formatDouble(hurtNum)}" /></td>				
				<td><s:property value="%{formatPercent(zgleaveRate)}" /></td>				
				<td><s:property value="%{formatPercent(factleaveRate)}" /></td>												
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="kpifact_findById" method="post" id="subform${x.index}">						
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode" />							
						<input type="hidden" value="<s:property value='id.yyyy'/>" name="yyyy" />							
					</form> 
					<a href="javascript:document.getElementById('subform${x.index}').submit()"					
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>

					<form action="kpifact_delete" method="post" id="2subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode" />							
						<input type="hidden" value="<s:property value='id.yyyy'/>" name="yyyy" />							
					</form> 
					<a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					
					<form action="kpifact_findById_copy" method="post" id="3subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode" />							
						<input type="hidden" value="<s:property value='id.yyyy'/>" name="yyyy" />							
					</form> 
					<a href="javascript:document.getElementById('3subform${x.index}').submit()"					
					onclick=""><img alt="複製" src="images/icon/copy.png" title="複製" ></a>
				</td> 
				</s:if>
			</tr>
		</s:iterator>
		</tbody>
	</table>
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
