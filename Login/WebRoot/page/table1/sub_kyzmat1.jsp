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
<!--<link rel="stylesheet" type="text/css" href="css/mystyle.css" />-->
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
    <s:form action="" method="post" theme="simple">
    
	<table class="table table-striped table-hover table-bordered">
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
		<input value="添加" type="button" class="btn btn-info"
		onclick="javascript:location.href='saveAndUpdate/kyzmatSaveOrUpdate.jsp'" /></s:if>
		物料資料管理
		</h2>
		<thead>			
			<tr class="tr_show">
			    <th><input type="checkbox" value="wwww" id="cb_all" onclick="selectAll()"/></th>
				<th>序號</th>
				<th>物料編號</th>
				<th>物料中文名稱</th>
				<th>資材分類</th>
				<th>物料規格</th>
				<th>物料顏色</th>
				<th>會計科目</th>
				<th>采購政策</th>
				<th>采購單價</th>
				<th>中文單位</th>
				<th>幣別</th>
				<th>商品名稱</th>
				<th>商品單重</th>
				<!-- <th>大分類</th>
				<th>中分類</th>
				<th>小分類</th> -->
				<th>創建人</th>
				<th>創建日期</th>
				<th>第一廠別</th>
				<th>正在使用的廠別</th>
				<th>操作</th>								
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		 
		        <tr >
		        <td><input type="checkbox" value="<s:property value='matNo'/>" name="cb_list"/></td>
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="matNo" /></td>
				<td><s:property value="matCname"/></td>														
				<td><s:property value="matType"/></td>				
				<td><s:property value="matSize" /></td>				
				<td><s:property value="color"/></td>
				<td><s:property value="acctNo"/></td>
				<td><s:property value="policy"/></td>
				<td><s:property value="purPrice"/></td>	
				<td><s:property value="cunit"/></td>	
				<td><s:property value="punit"/></td>
				<td><s:property value="smatName"/></td>
				<td><s:property value="smatWeit"/></td>
				<!-- <td><s:property value="typeBno"/></td>	
				<td><s:property value="typeMno"/></td>	
				<td><s:property value="typeSno"/></td> -->
				<td><s:property value="builder"/></td>
				<td><s:property value="dateB"/></td>
				<td><s:property value="factNo"/></td>
				<td>
				   <s:iterator value="subKyzmats">
				      <s:if test="id.factNo==#session.factNo">
				          <font color="red"><s:property value="id.factNo"/>,</font>
				      </s:if>
				      <s:else>
				          <s:property value="id.factNo"/>,
				      </s:else>				      
				   </s:iterator>
				</td>
				<td>				
				<a href="javascript:location.href='kyzmat_findById?matNo=${temp.matNo}'"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>
				<a href="javascript:isDelete('${temp.matNo}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
				<a href="javascript:window.location.href='kyzmat_print?matNo=${temp.matNo}'" ><img alt="導出" src="images/icon/print001.png" title="導出" ></a>
				</td> 							
			</tr>	  
		  </s:iterator>		 		
		</tbody>
	</table>
	<s:submit value="導出所選物料" action="kyzmat_print_select"></s:submit>
    <s:submit value="添加所選物料" action="subkyzmat_addSubKyzmat"></s:submit>
	</s:form>
	</div>
 </div>		
	<!--<hr />
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
	</center>-->
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
