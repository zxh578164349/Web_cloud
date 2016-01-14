
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
<title>My JSP 'backmat1.jsp' starting page</title>
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
		<caption>廢品邊料(每月輸入)</caption>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>年月</th>
				<th>廠別狀態</th>
				<th>邊料重量(KG)</th>
				<th>不良雙數(雙)</th>
				<th>不良重量(KG)</th>
				<th>其它報廢重量(KG)</th>
				<th>輸入者</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>

		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr onmousemove="click_color(this)" onmouseout="move(this)">
				<td>${25*(pages.page-1)+x.index+1}</td>
				<td><s:property value="id.factNo" />
				</td>
				<td><s:date name="id.yymm" format="yyyyMM" />
				</td>
				<td><s:property value="id.factCode" />
				</td>
				<td><s:property value="%{formatDouble(sideweit)}" />
				</td>
				<td><s:property value="%{formatDouble(badcount)}" />
				</td>
				<td><s:property value="%{formatDouble(badweit)}" />
				</td>
				<td><s:property value="%{formatDouble(otherbadweight)}" />
				</td>
				<td><s:property value="username" />
				</td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="webScrapt_findScraptById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a
					href="javascript:layer.load(0);document.getElementById('subform${x.index}').submit()"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					<form action="webScrapt_delete2" method="post"
						id="2subform${x.index}" style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
					</td>
					</s:if>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</div>	
<%-- 	<hr />
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
</body>
</html>
