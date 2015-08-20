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
		<caption>函文審核狀況</caption>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>類別</th>
				<th>單號</th>				
				<th>創建日期</th>				
				<th colspan="15">審核狀況</th>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		
		        <tr onmousemove="click_color(this)" onmouseout="move(this)">
				<td>${ 25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.kyVisabillm.id.factNo" />
				</td>
				<td>							
				<s:if test='id.kyVisabillm.id.visaSort=="F"'> 廠務簽核</s:if>				 				
				<s:if test='id.kyVisabillm.id.visaSort=="W"'>臺灣簽核</s:if>								
				<s:if test='id.kyVisabillm.id.visaSort=="G"'>工程簽核</s:if>				  				
				<s:if test='id.kyVisabillm.id.visaSort=="I"'>IKT簽核</s:if>									
				<s:if test='id.kyVisabillm.id.visaSort=="L"'>實驗室簽核</s:if>				  				
				<s:if test='id.kyVisabillm.id.visaSort=="P"'>品管簽核</s:if>									
				<s:if test='id.kyVisabillm.id.visaSort=="Q"'> 企劃簽核</s:if>				 				
				<s:if test='id.kyVisabillm.id.visaSort=="S"'>生管簽核</s:if>									
				<s:if test='id.kyVisabillm.id.visaSort=="T"'>整理簽核</s:if>				  				
				<s:if test='id.kyVisabillm.id.visaSort=="Y"'>油壓簽核</s:if>									
				<s:if test='id.kyVisabillm.id.visaSort=="Z"'>總務簽核</s:if>				  					
				<s:if test='id.kyVisabillm.id.visaSort=="B"'> 備料簽核</s:if>				 				
				<s:if test='id.kyVisabillm.id.visaSort=="O"'>業務簽核</s:if>				  				
				<s:if test='id.kyVisabillm.id.visaSort.substring(0,2)=="C1"'>其他費用簽核1(<1000)</s:if>				  
				<s:if test='id.kyVisabillm.id.visaSort.substring(0,2)=="C2"'>其他費用簽核2(>=1000)</s:if>
				<s:if test='id.kyVisabillm.id.visaSort.substring(0,2)=="C3"'>電腦耗材簽核1(<1000)</s:if>
				<s:if test='id.kyVisabillm.id.visaSort.substring(0,2)=="C4"'>電腦耗材簽核2(>=1000)</s:if>
				<s:if test='id.kyVisabillm.id.visaSort.substring(0,2)=="C5"'>總務費用簽核1(<1000)</s:if>
				<s:if test='id.kyVisabillm.id.visaSort.substring(0,2)=="C6"'>總務費用簽核2(>=1000)</s:if>									  						
				</td>
				<td><s:property value="id.kyVisabillm.id.billNo"/>
				</td>				
				<td><s:property value="%{formatDate(id.kyVisabillm.dateCreate)}" />
				</td>
				<td>											 					
		 <s:iterator value="id.kyVisabillm.kyVisabillses">
	       
	       <!-- <s:property value="vbm.signerNext"/>(<s:property value="visaSigner"/>) -->
	       <s:if test='flowMk=="Y"'>
	       <s:if test='visaMk=="N"'><!-- 1.判斷未審和已審狀態 -->
	         <s:if test='id.itemNo==id.kyVisabillm.itemNext'><!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->	           
	           <s:if test="visaSigner==#session.loginUser.email"><!-- 3.判斷登錄者是否為當前審核人 -->
	              <%-- <a style="color:red" href="javascript:check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">       
	                                     未審核<s:property value="id.itemNo"/>(當前審核人)
	              </a> --%>
	              <a style="color:red"><s:property value="visaRank"/>(當前審核人)</a>&nbsp;|&nbsp;
	           </s:if>	
	            <s:else>
	             <a disabled style="color:grey"><s:property value="visaRank"/>(未審核)</a>&nbsp;|&nbsp;
	           </s:else>             
	         </s:if>
	          <s:else>
	             <a disabled style="color:grey"><s:property value="visaRank"/>(未審核)</a>&nbsp;|&nbsp;
	           </s:else> 	        	       
	       </s:if>
	       <s:if test='visaMk=="Y"'>	       
	        <a style="color:green"><s:property value="visaRank"/>(已審核)</a>&nbsp;|&nbsp;
	       </s:if>
	       <s:if test='visaMk=="T"'>
	         <a style="color:blue"><s:property value="visaRank"/>(未通過)</a>&nbsp;|&nbsp;
	       </s:if>
	       </s:if>
	       <s:else>
	       <a style="color:#b45b3e"><s:property value="visaRank"/>(只知會)</a>&nbsp;|&nbsp;
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
