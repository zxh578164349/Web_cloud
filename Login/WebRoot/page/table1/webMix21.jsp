
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
		<caption>營收損益(每月輸入)</caption>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>年月</th>
				<th>廠別狀態</th>
				<th>請款雙數(雙)</th>
				<th>雙數請款金額(USD)</th>
				<th>客補樣品請款雙數(雙)</th>
				<th>銷售收入(USD)</th>
				<th>成本合計(USD)</th>
				<th>直接應領薪資(USD)</th>
				<th>間接應領薪資(USD)</th>
				<th>直工加班費(USD)</th>
				<th>間工加班費(USD)</th>
				<th>費用合計(USD)</th>
				<th>其它(USD)</th>
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
				<td><s:property value="%{formatDouble(invcount)}" />
				</td>
				<td><s:property value="%{formatDouble(payPairs)}"/></td>
				<td><s:property value="%{formatDouble(hostinvcount)}" />
				</td>
				<td><s:property value="%{formatDouble(sellcount)}" />
				</td>
				<td><s:property value="%{formatDouble(costcount)}" />
				</td>
				<td><s:property value="%{formatDouble(wagezgUsd)}" />
				</td>
				<td><s:property value="%{formatDouble(wagejgUsd)}" />
				</td>
				<td><s:property value="%{formatDouble(addmoneyzg)}" />
				</td>
				<td><s:property value="%{formatDouble(addmoneyjg)}" />
				</td>
				<td><s:property value="%{formatDouble(cashcount)}" />
				</td>
				<td><s:property value="%{formatDouble(other)}" />
				</td>
				<td><s:property value="username" />
				</td>
				<s:if test='#session.loginUser.userread!="1"'>				
				<td>
					<form action="webmix2_findMix2ById" method="post"
						id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a
					href="javascript:document.getElementById('subform${x.index}').submit()"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					<form action="webmix2_delete2" method="post"
						id="subform2${x.index}" style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('subform2${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
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
