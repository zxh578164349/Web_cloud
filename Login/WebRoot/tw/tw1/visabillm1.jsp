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
				<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
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
			    <a href="javascript:findById_form('0form${x.index}','vbm_findById')">
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
  <jsp:include page="../../page/table1/pagenation.jsp" flush="true"/>				
</body>

</html>
