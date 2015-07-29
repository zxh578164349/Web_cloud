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

<title>My JSP 'matstore1.jsp' starting page</title>

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
	<table id="tb" >
		<caption>全廠原料庫存</caption>
		<thead>			
			<tr class="tr_show">
				<th>廠別</th>
				<th>單號</th>
				<th>年月</th>
				<th>廠別狀態</th>
				<th>總原料庫存量（Kg）</th>
				<th>總原料庫存金額</th>
				<th>平均單價</th>
				<th>漲幅</th>
				<th>膠類庫存量（Kg）</th>
				<th>膠類庫存量（噸）</th>
				<th>膠類金額（USD）</th>
				<th>平均單價R</th>
				<th>漲幅R</th>
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
				<td><s:property value="matStore" /></td>
				<td><s:property value="matCash" /></td>
				<td><s:property value="matPrice" /></td>
				<td><s:property value="upMat" /></td>
				<td><s:property value="RWeit" /></td>
				<td><s:property value="RTon" /></td>
				<td><s:property value="RCash" /></td>
				<td><s:property value="RPrice" /></td>
				<td><s:property value="upR" /></td>
				<td><s:property value="dateB" /></td>
				<td><s:property value="dateE" /></td>
				<td><s:property value="lockMk" /></td>
				<td><s:property value="userNo" /></td>
				<td><s:property value="dateTime" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="mastore_findById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" /> <input
							type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="document.getElementById('subform${x.index}').submit()"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					&nbsp;
					<form action="mastore_delete2" method="post"
						id="subform2${x.index}" style="float:left">
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
