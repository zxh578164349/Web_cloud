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
	<table class="table table-striped table-hover table-bordered">
		<h2>函文審核狀況</h2>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>類別</th>
				<th>單號</th>				
				<th>標題</th>
				<th>操作</th>			
				<th colspan="15">審核狀況</th>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		
		        <tr >
				<td>${ 25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.kyVisabillm.id.factNo" />
				</td>
				<td>											
				<s:property value="id.kyVisabillm.colTemp"/>									  						
				</td>
				<td>
				<form action="vbm_findById" method="post" id="0form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
			    </form>
			    <a href="javascript:findById('0form${x.index}','vbm_findById')">
				<s:property value="id.kyVisabillm.id.billNo"/>
				</a>
				</td>				
				<td>
				<s:property value="memo" />
				</td> 
				<td>
				  <form  method="post" id="4subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaType"/><!-- 湘威費用清單函文 -->
							<input type="hidden" value="look" name="lookordown"/>						
					</form>
					<form  method="post" id="5subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaType"/><!-- 湘威費用清單函文 -->
							<input type="hidden" value="down" name="lookordown"/>						
					</form>
				   <a href="javascript:goPreviewOrPrint('4subform${x.index}','${temp.id.kyVisabillm.id.billNo}')"><img alt="預覽" src="images/icon/view001.png" title="預覽" ></a>
				   <a href="javascript:goPreviewOrPrint('5subform${x.index}','${temp.id.kyVisabillm.id.billNo}')" ><img alt="打印" src="images/icon/print001.png" title="打印" ></a>
				</td>
				   	
				<td>											 					
		 <s:iterator value="id.kyVisabillm.kyVisabillses"  status="y">
	       
	       <!-- <s:property value="vbm.signerNext"/>(<s:property value="visaSigner"/>) -->
	       <s:if test='flowMk=="Y"'>
	       <s:if test='visaMk=="N"'><!-- 1.判斷未審和已審狀態 -->
	         <s:if test='id.itemNo==id.kyVisabillm.itemNext'><!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->	           
	           <s:if test="visaSigner==#session.loginUser.email"><!-- 3.判斷登錄者是否為當前審核人 -->
	              <%-- <a style="color:red" href="javascript:check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">       
	                                     未審核<s:property value="id.itemNo"/>(當前審核人)
	              </a> --%>
	              <a style="color:red"><s:property value="visaRank"/>(當前審核人)</a>&nbsp;||&nbsp;
	           </s:if>	
	            <s:else>
	             <a disabled style="color:grey"><s:property value="visaRank"/>(未審核)</a>&nbsp;||&nbsp;
	           </s:else>             
	         </s:if>
	          <s:else>
	             <a disabled style="color:grey"><s:property value="visaRank"/>(未審核)</a>&nbsp;||&nbsp;
	           </s:else> 	        	       
	       </s:if>
	       <s:if test='visaMk=="Y"'>	       
	        <a style="color:green" href="javascript:tips('${memo}','index${x.index}${y.index}')" id="index${x.index}${y.index}"><s:property value="visaRank"/>(已審核)<s:property value="dateVisa"/></a>&nbsp;||&nbsp;
	       </s:if>
	       <s:if test='visaMk=="T"'>
	         <a style="color:blue" href="javascript:tips('${memo}','index${x.index}${y.index}')" id="index${x.index}${y.index}"><s:property value="visaRank"/>(未通過)<s:property value="dateVisa"/></a>&nbsp;||&nbsp;
	       </s:if>
	       </s:if>
	       <s:else>
	       <a style="color:#b45b3e"><s:property value="visaRank"/>(只知會)</a>&nbsp;||&nbsp;
	       </s:else>	       
	      </s:iterator>
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
</body>

</html>
