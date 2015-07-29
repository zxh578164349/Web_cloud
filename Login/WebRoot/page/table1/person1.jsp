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

<title>My JSP 'person1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 分頁樣式 -->
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
	<table id="tb">
		
		<caption>人員效能</caption>
		<thead>		
			<tr class="tr_show">
				<th>廠別</th>
				<th>單號</th>
				<th>年月</th>
				<th>廠別狀態</th>
				<th>標準產量</th>
				<th>實際產量</th>
				<th>直工人數</th>
				<th>間工人數</th>
				<th>全廠總人數</th>
				<th>直間比</th>
				<th>直工人均產能(模人)</th>
				<th>全廠人均產能(模人)</th>
				<th>全廠人均產能(模h)</th>
				<th>職工離職率(%)</th>
				<th>全廠離職率(%)</th>
				<th>直工薪資</th>
				<th>間工薪資</th>
				<th>總薪資</th>
				<th>總工時</th>
				<th>時產能</th>
				<th>每雙單耗</th>
				<th>直工加班費</th>
				<th>間工加班費</th>
				<th>總加班費</th>
				<th>直工人均薪資</th>
				<th>間工人均薪資</th>
				<th>直工占薪資比例</th>
				<th>間工占薪資比例</th>
				<th>全廠人均薪資</th>
				<th>全廠人均產能</th>
				<th>日期起</th>
				<th>日期止</th>
				<th>鎖定</th>
				<th>輸入者工號</th>
				<th>輸入時間</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr onmousemove="click_color(this)" onmouseout="move(this)">
				<td><s:property value="id.factNo" /></td>
				<td><s:property value="id.billNo" /></td>
				<td><s:date name="id.yymm" format="yyyyMM" /></td>
				<td><s:property value="factCode" /></td>
				<td><s:property value="demoStand" /></td>
				<td><s:property value="demoAct" /></td>
				<td><s:property value="personZg" /></td>
				<td><s:property value="personJg" /></td>
				<td><s:property value="personCount" /></td>
				<td><s:property value="personZjg" /></td>
				<td><s:property value="personZgavg" /></td>
				<td><s:property value="personAvg" /></td>
				<td><s:property value="personAvgh" /></td>
				<td><s:property value="leaveZg" /></td>
				<td><s:property value="leaveCount" /></td>
				<td><s:property value="wageZg" /></td>
				<td><s:property value="wageJg" /></td>
				<td><s:property value="wageCount" /></td>
				<td><s:property value="timeCount" /></td>
				<td><s:property value="outputHr" /></td>
				<td><s:property value="profitPair" /></td>
				<td><s:property value="addZg" /></td>
				<td><s:property value="addJg" /></td>
				<td><s:property value="addCount" /></td>
				<td><s:property value="wageZgavg" /></td>
				<td><s:property value="wageJgavg" /></td>
				<td><s:property value="wageZgexp" /></td>
				<td><s:property value="wageJgexp" /></td>
				<td><s:property value="wageAvg" /></td>
				<td><s:property value="personAvghz" /></td>
				<td><s:property value="dateB" /></td>
				<td><s:property value="dateE" /></td>
				<td><s:property value="lockMk" /></td>
				<td><s:property value="userNo" /></td>
				<td><s:property value="dateTime" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="person_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" /> <input
							type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="document.getElementById('subform${x.index}').submit()"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					&nbsp;
					<form action="person_delete2" method="post" id="subform2${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" /> <input
							type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('subform2${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
				</td>
				</s:if>
			</tr>
		</s:iterator>
				</tbody>
	</table>
	</div>
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
</body>
</html>
