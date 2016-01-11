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

<!-- 分頁樣式 -->
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
<script type="text/javascript" src="page/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>

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
    
    var jqr=jQuery.noConflict();
    function showDiv(billNo,factNo){
    jqr.layer({
    type: 1,   //0-4的选择,
    title: '函文內容',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    //fadeIn:300,
    shift:'top',
    offset:['10px',''],
    area: ['820px', '560px'],
    page:{
      url:'bussletter_findById_layer?billNo='+billNo+'& factNo='+factNo    
    }
    
           
});
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
		<caption>人員出差申請書</caption>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>所屬單位</th>
				<th>姓名</th>
				<th>職務</th>
				<th>職務代理人</th>
				<th>廠別</th>
				<th>出差地點</th>
				<th>出差時間</th>
				<th>去程班機時間</th>								
				<th>回程班機時間</th>				
				<s:if test='#session.loginUser.userread!="1"'>				
				<th>操作</th>
				</s:if>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		  
		     <tr onmousemove="click_color(this)" onmouseout="move(this)"> 
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="unit" /></td>
				<td><s:property value="username" /></td>
				<td><s:property value="position" /></td>
				<td><s:property value="GAgent"/></td>
				<td><s:property value="factNo"/></td>				
				<td><s:property value="address" /></td>
				<td>
				<s:date name="dateFrom" format="yyyyMMdd"/>至<s:date name="dateEnd" format="yyyyMMdd"/>
				(<s:property value="%{sumDate(dateFrom,dateEnd)}"/>天)
				</td>
				<td><s:date name="timeFrom" format="yyyyMMdd-HH:mm" /></td>
				<td><s:date name="timeEnd" format="yyyyMMdd-HH:mm"/></td>		
														
				<td>
				
				<s:if test='#session.loginUser.userread!="1"'>
					<form action="bussletter_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='blNo'/>"
							name="billNo" /> 						
					</form> 
					<form action="bussletter_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='blNo'/>"
							name="billNo" /> 						
					</form>
					  <a href="javascript:layer.load(0);document.getElementById('subform${x.index}').submit()" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					  <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>										
					 </s:if>
					 
					  <form action="bussletter_print2" method="post" id="3subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='blNo'/>" name="billNo" />										
						<input type="hidden" value="<s:property value='visaSort'/>" name="visaSort"/>
						<input type="hidden" value="look" name="lookordown"/>											
					  </form>
					   <form action="bussletter_print2" method="post" id="4subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='blNo'/>" name="billNo" />							
						<input type="hidden" value="<s:property value='visaSort'/>" name="visaSort"/>
						<input type="hidden" value="down" name="lookordown"/>											
					  </form>					  
					  <a href="javascript:showDiv('<s:property value='visaSort'/>','<s:property value='factNo'/>')" onclick=""><img alt="查看" src="images/icon/view002.png" title="查看" ></a>					  					  
					 <a href="javascript:document.getElementById('3subform${x.index}').submit()"><img alt="預覽" src="images/icon/view001.png" title="預覽" ></a>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="打印" src="images/icon/print001.png" title="打印" ></a>						 
				</td>
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
