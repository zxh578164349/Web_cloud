<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ypre_show1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <link rel="stylesheet" type="text/css" href="css/mystyle.css" />-->
<script type="text/javascript">

	
	/*var defaultColor="#97CBFF";
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
    <div id="content">
	<table class="table table-striped table-hover table-bordered" >
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
		<input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('kyzpetty_findAllSecNoAndAcctNo')" /></s:if>
		零用金支出
		</h2>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>單號</th>
				<th>作帳幣別</th>
				<th>支出日期</th>
				<th>流水號</th>
				<th>結帳日期</th>
				<th>歸屬部門</th>
				<th>歸屬組別</th>
				<th>費用性質</th>
				<th>借貸注記</th>
				<th>會計科目</th>
				<th>摘要說明</th>
				<th>費用廠別</th>
				<th>費用部門</th>
				<th>恁証類別</th>
				<th>課稅類別</th>
				<th>恁証日期</th>
				<th>管稅帳注記</th>
				<th>恁証號碼</th>
				<th>統一編號</th>
				<th>支付金額</th>
				<th>支付稅額</th>
				<th>經手人</th>
				<th>備注1</th>
				<th>備注2</th>
				<th>備注3</th>
				<th>備注4</th>
				<th>操作者</th>
				<th>建构日期</th>
				<th>恁証流水號</th>
				<th>內部摘要</th>
				<th>費用類別</th>
				<th>Time Zd</th>
				<s:if test='#session.loginUser.userread!="1"'>								
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		 
		        <tr >
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td><s:property value="id.billNo"/></td>														
				<td><s:property value="currencyNo"/></td>				
				<td><s:property value="datePaybill" /></td>				
				<td><s:property value="itemNo"/></td>
				<td><s:property value="dateOver"/></td>
				<td><s:property value="deptNo"/></td>
				<td><s:property value="secNo"/></td>	
				<td><s:property value="expenseMk"/></td>	
				<td><s:property value="dbcdMk"/></td>
				<td><s:property value="acctNo"/></td>
				<td><s:property value="noteMk"/></td>
				<td><s:property value="factExpense"/></td>	
				<td><s:property value="deptExpense"/></td>	
				<td><s:property value="vouMk"/></td>
				<td><s:property value="taxMk"/></td>
				<td><s:property value="dateVou"/></td>
				<td><s:property value="taxmMk"/></td>	
				<td><s:property value="vouNo"/></td>	
				<td><s:property value="venderCode"/></td>
				<td><s:property value="cashExpense"/></td>
				<td><s:property value="taxExpense"/></td>
				<td><s:property value="manHandle"/></td>	
				<td><s:property value="memoMk1"/></td>	
				<td><s:property value="memoMk2"/></td>
				<td><s:property value="memoMk3"/></td>
				<td><s:property value="memoMk4"/></td>
				<td><s:property value="userNm"/></td>	
				<td><s:property value="timeCreate"/></td>	
				<td><s:property value="itemYm"/></td>
				<td><s:property value="noteIn"/></td>
				<td><s:property value="expenseType"/></td>
				<td><s:property value="timeZd"/></td>
				<s:if test='#session.loginUser.userread!="1"'>														
			 	<td>			 										
					 <form action="kyzpetty_findById" method="post" id="form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
					   <input type="hidden" value="<s:property value='id.billNo'/>" name="billNo"/> 																				
					 </form>
					  <form action="kyzpetty_delete" method="post" id="2form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
					   <input type="hidden" value="<s:property value='id.billNo'/>" name="billNo"/>																				
					 </form>
					  <%-- <s:if test='#attr.loginUser.username=="admin"'>					 
					  <a href="javascript:showDiv2('<s:property value='id.factNo'/>','<s:property value='id.billNo'/>')">修改</a>
					 <a href="javascript:isDelete('2form${x.index}')">刪除</a>
					 </s:if>
					 <s:else>
					    <a style="color:red" href="javascript:alert('對不起，只有管理員可以操作！')">修改</a>
					    <a style="color:red" href="javascript:alert('對不起，只有管理員可以操作！')">刪除</a>
					 </s:else>  --%>
					 <a href="javascript:showDiv2('<s:property value='id.factNo'/>','<s:property value='id.billNo'/>')"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
					 <a href="javascript:isDelete('2form${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>					
				</td>
				</s:if>
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
	</center>--%>
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
