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

<title>My JSP 'ypre_show1.jsp' starting page</title>


<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 分頁樣式 -->
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
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
    <div id="content">
	<table id="tb" >
		<caption>函文審核</caption>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>類別</th>
				<th>單號</th>
				<!-- <th>最近審核人(Email)</th>
				<th>下一位審核人(Email)</th>
				<th>最近項次</th>
				<th>下一個項次</th> -->
				<th>標題</th>
				<th>最近審核狀態</th>
				<th>當前審核狀態</th>
				<th>重審狀態</th>				
				<th>創建日期</th>
				<s:if test='#session.loginUser.userread!="1"'>			
				<th>操作</th>
				</s:if>											
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		
		        <tr onmousemove="click_color(this)" onmouseout="move(this)">
				<td>${ 25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.kyVisabillm.id.factNo" />
				</td>
				<td>							
				<s:property value="id.kyVisabillm.colTemp"/>									  						
				</td>
				<td><s:property value="id.kyVisabillm.id.billNo"/>
				</td>
				<td><s:property value="memo"/>			
				</td>
				<!-- <td><s:property value="id.kyVisabillm.signerLast" />
				</td>
				<td><s:property value="id.kyVisabillm.signerNext" />
				</td>
				<td><s:property value="id.kyVisabillm.itemLast" />
				</td>
				<td><s:property value="id.kyVisabillm.itemNext" />
				</td> -->
				<td><s:property value="id.kyVisabillm.lastMk" />
				</td>
				<td><s:property value="id.kyVisabillm.visaMk" />
				</td>
				<td><s:property value="id.kyVisabillm.revisaMk" />
				</td>				
				<td><s:property value="%{formatDate(id.kyVisabillm.dateCreate)}" />
				</td>
				<s:if test='#session.loginUser.userread!="1"'>														
			 	<td>
			 	<ul>
			 	    <li>
					<%-- <a href="javascript:check('${temp.id.kyVisabillm.id.visaSort }','${temp.id.kyVisabillm.id.billNo}','${temp.id.kyVisabillm.id.factNo}')">審核</a> --%>
					 <form action="vbm_findById2" method="post" id="1form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
					 </form>
					  <form action="vbm_findById3" method="post" id="2form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
					 </form>
					 <form action="vbm_findById4" method="post" id="3form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
					 </form>
					 <form action="vbm_findById5" method="post" id="3_3form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
					 </form>
					 <form action="kyz_print2" method="post" id="4subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="id.billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
							<input type="hidden" value="look" name="lookordown"/>						
					</form>
					<form action="kyz_print2" method="post" id="5subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="id.billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
							<input type="hidden" value="down" name="lookordown"/>						
					</form>
					
					<form action="kyzletter_print2" method="post" id="6subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
							<input type="hidden" value="look" name="lookordown"/>						
					</form>
					<form action="kyzletter_print2" method="post" id="7subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
							<input type="hidden" value="down" name="lookordown"/>						
					</form>
					<form action="vbm_sendEmail" method="post" id="8subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>													
					</form>
				    </li>	 
					<s:if test='#session.loginUser.userread!="1"'>
					<li>					
					<a href="vbm_findById?visaSort=<s:property value='id.kyVisabillm.id.visaSort'/>& billNo=<s:property value='id.kyVisabillm.id.billNo'/>& factNo=<s:property value='id.kyVisabillm.id.factNo'/>" >
					<img alt="審核" src="images/icon/check24.png" title="審核"><br>審核
					</a>
					</li>
					<s:if test='id.kyVisabillm.visaMk=="N"'>
					<li>
					 <a href="javascript:document.getElementById('1form${x.index}').submit()"><img alt="加簽" src="images/icon/add24.png" title="加簽"><br>加簽</a>
					</li>
					 <s:if test='#session.loginUser.factno=="JW"'>
					<li>
					 <a href="javascript:document.getElementById('3form${x.index}').submit()"><img alt="減簽" src="images/icon/minus24.png" title="減簽"><br>減簽</a>					 
					 </li>
					 </s:if>
					 <li>
					   <a href="javascript:document.getElementById('3_3form${x.index}').submit()"><img alt="減簽(带删除)" src="images/icon/remove.png" title="減簽(带删除)"><br>減簽D</a>
					 </li>
					 </s:if>
					 <s:else>
					 <li>
					   <a><img alt="加簽" src="images/icon/add24_1.jpg" title="加簽"><br>加簽</a>
					 </li>
					 <s:if test='#session.loginUser.factno=="JW"'>
					 <li>
					   <a><img alt="減簽" src="images/icon/minus24_1.jpg" title="減簽"><br>減簽</a>
					 </li>
					 </s:if>
					 <li>
					   <a><img alt="減簽(带删除)" src="images/icon/remove_1.png" title="減簽(带删除)"><br>減簽D</a>
					 </li>    
					 </s:else>											   					 					 						
					 </s:if>
					 <s:if test='id.kyVisabillm.id.billNo.substring(0,2)=="EM"'>			    					 
					 <li>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="預覽" src="images/icon/view24.png" title="預覽"><br>預覽</a>
					 </li>
					 <li>
					 <a href="javascript:document.getElementById('5subform${x.index}').submit()"><img alt="打印" src="images/icon/print24.png" title="打印"><br>打印</a>
				     </li>
				     </s:if>	
				     <s:else>
				      <li>
					 <a href="javascript:document.getElementById('6subform${x.index}').submit()" ><img alt="預覽" src="images/icon/view24.png" title="預覽"><br>預覽</a>
					 </li>
					 <li>
					 <a href="javascript:document.getElementById('7subform${x.index}').submit()"><img alt="打印" src="images/icon/print24.png" title="打印"><br>打印</a>
				     </li>
				     </s:else>
				     <s:if test='#session.loginUser.username=="admin"'>
				     <s:if test='id.kyVisabillm.visaMk=="N"&&id.kyVisabillm.emailMk==null'>
				        <li>
				            <a href="javascript:document.getElementById('8subform${x.index}').submit()">Email</a>
				        </li> 
				     </s:if>
				     <s:else>
				          <font color="grey">Email</font>
				     </s:else>
				     </s:if>
				</ul>
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
