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
				<th>最近審核人(Email)</th>
				<th>下一位審核人(Email)</th>
				<th>最近審核狀態</th>
				<th>當前審核狀態</th>
				<th>重審狀態</th>
				<th>最近項次</th>
				<th>下一個項次</th>
				<th>創建日期</th>				
				<th>操作</th>								
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
				<td><s:property value="id.kyVisabillm.signerLast" />
				</td>
				<td><s:property value="id.kyVisabillm.signerNext" />
				</td>
				<td><s:property value="id.kyVisabillm.lastMk" />
				</td>
				<td><s:property value="id.kyVisabillm.visaMk" />
				</td>
				<td><s:property value="id.kyVisabillm.revisaMk" />
				</td>
				<td><s:property value="id.kyVisabillm.itemLast" />
				</td>
				<td><s:property value="id.kyVisabillm.itemNext" />
				</td>
				<td><s:property value="id.kyVisabillm.dateCreate" />
				</td>
								
			 	<td>
			 	<s:if test='#session.loginUser.userread!="1"'>					
					<a href="vbm_findById?visaSort=<s:property value='id.kyVisabillm.id.visaSort'/>& billNo=<s:property value='id.kyVisabillm.id.billNo'/>& factNo=<s:property value='id.kyVisabillm.id.factNo'/>" >
					<img alt="審核" src="images/icon/check002.png" title="審核">
					</a>
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
					<s:if test='id.kyVisabillm.visaMk=="N"'>
					 <a href="javascript:document.getElementById('1form${x.index}').submit()"><img alt="加簽" src="images/icon/add001.png" title="加簽"></a>
					 <a href="javascript:document.getElementById('3form${x.index}').submit()"><img alt="減簽" src="images/icon/minus001.png" title="減簽"></a>
					 <%-- <a href="javascript:document.getElementById('5form${x.index}').submit()"><img alt="知會" src="images/icon/email001.png" title="知會"></a> --%>	
					 </s:if>
					 <s:else>
					   <a><img alt="加簽" src="images/icon/add001_1.png" title="加簽"></a>
					   <a><img alt="減簽" src="images/icon/minus001_1.png" title="減簽"></a>
					 </s:else>			
									   					 					 						
					 </s:if>
					 				    					 
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
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="id.billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
							<input type="hidden" value="look" name="lookordown"/>						
					</form>
					<form action="kyzletter_print2" method="post" id="7subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="id.billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
							<input type="hidden" value="down" name="lookordown"/>						
					</form>
					<s:if test='id.kyVisabillm.id.billNo.substring(0,2)=="EM"'>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="預覽" src="images/icon/view001.png" title="預覽"></a>
					 <a href="javascript:document.getElementById('5subform${x.index}').submit()"><img alt="打印" src="images/icon/print001.png" title="打印"></a>
				    </s:if>
				    <s:else>
				       <a href="javascript:document.getElementById('6subform${x.index}').submit()" ><img alt="預覽" src="images/icon/view001.png" title="預覽"></a>
					 <a href="javascript:document.getElementById('7subform${x.index}').submit()"><img alt="打印" src="images/icon/print001.png" title="打印"></a>
				    </s:else>
				</td> 					      
		</tr>		 		  
		</s:iterator>
		</tbody>
	</table>
	</div>
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
