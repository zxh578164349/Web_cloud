
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
<!-- <link rel="stylesheet" type="text/css" href="css/mystyle.css" />-->
	<script type="text/javascript">
	/* var defaultColor="#97CBFF";
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
	}*/
</script>

</head>
<body>
  <div id="container">
	<table class="table table-striped table-hover table-bordered" >
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>	
		<input value="添加新用戶" type="button" class="btn btn-info" onclick="javascript:location.href='saveAndUpdate/webuserSaveOrUpdate.jsp'"/>
	    </s:if>	
		用戶管理
		</h2>
		<thead>
			<tr class="tr_show">
				<th>用戶ID</th>
					<th>登陸名</th>
					<th>姓名</th>
					<th>密碼</th>
					<th>IP</th>
					<th>工號</th>
					<th>廠別</th>
					<th>狀態</th>
					<th>登錄日期</th>					
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="username" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="pwd" /></td>
					<td><s:property value="ip" /></td>
					<td><s:property value="workno" /></td>
					<td><s:property value="emailpassword" /></td>
					<td>
					<s:if test="available==0"><img alt="可用" src="images/icon/available001.png" title="可用"></s:if> <s:if
							test="available==1"><img alt="禁用" src="images/icon/not_available001.png" title="禁用"></s:if>
					</td>
					<td><s:property value="logdate"/></td>
					<td>
					    <%-- <a href="userjurisdiction?id=${id}&fact=${factno}"><img alt="權限" src="images/icon/keys001.png" title="權限"></a>&nbsp;  --%>
					    <a href="javascript:loadjur(${id },'${factno}')"><img alt="權限" src="images/icon/keys001.png" title="權限"></a>&nbsp;
						<a href="javascript:loaduser(${id})"><img alt="修改" src="images/icon/edit001.png" title="修改"></a>&nbsp;
						<a href="userupdateKy?id=${id}&available=0"><img alt="可用" src="images/icon/available001.png" title="可用"></a>&nbsp;
						<a href="userupdateKy?id=${id}&available=1"><img alt="禁用" src="images/icon/not_available001.png" title="禁用"></a>&nbsp;
						<a href="javascript:mydelete(${id})"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>						
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>

<%-- 	<center id="center_page">
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
		    <li style="width:50px"><a href="javascript:pages(0)">首頁</a></li>
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
			<li style="width:50px"><a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a></li>			
		</ul>
</body>
</html>
