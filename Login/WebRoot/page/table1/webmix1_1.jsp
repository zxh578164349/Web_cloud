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
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			tiptype : 1,
		//showAllError : true,
		});

	});
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
	function showalert(){
	  alert("你好,[生產出貨]已整合到[產量資料],[生產出貨]將會刪除");
	}
</script>

</head>

<body onload="showalert()">
  <div id="container">
	<table id="tb" >
		<caption>生產出貨</caption>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>
				<th>上班天數(天)</th>
				<th>當日上模人數(人)</th>
				<th>當日上模數(模)</th>
				<th>標准生產模數(模)</th>
				<th>實際生產模數(雙)</th>
				<th>實際生產雙數(雙)</th>
				<th>客補生產雙數(雙)</th>
				<th>廠補生產雙數(雙)</th>
				<th>樣品生產雙數(雙)</th>
				<th>出貨數(雙)</th>
				<th>退貨數(雙)</th>
				<th>輸入者</th>
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
				<td><s:date name="id.yymmdd" format="yyyyMMdd" /></td>
				<td><s:property value="%{formatDouble(workday)}" /></td>
				<td><s:property value="%{formatDouble(everypeople)}" /></td>
				<td><s:property value="%{formatDouble(everydemo)}" /></td>
				<td><s:property value="%{formatDouble(standarddemo)}" /></td>
				<td><s:property value="%{formatDouble(actualdemo)}" /></td>
				<td><s:property value="%{formatDouble(actualpairs)}" />
				</td>
				<td><s:property value="%{formatDouble(hostpairs)}" />
				</td>
				<td><s:property value="%{formatDouble(factpairs)}" />
				</td>
				<td><s:property value="%{formatDouble(samplepairs)}" />
				</td>
				<td><s:property value="%{formatDouble(outnum)}" />
				</td>
				<td><s:property value="%{formatDouble(backnum)}" />
				</td>
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webmix1_findById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form> <a
					href="javascript:document.getElementById('subform${x.index}').submit()"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>

					<form action="webmix1_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
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
	<hr>

</body>


</html>
