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
    <h3 id="h2_title">函文審核</h3>
	<table class="table table-striped table-hover table-bordered" >		
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>類別</th>
				<th>單號</th>				
				<th>標題</th>
				<th>最近審核狀態</th>
				<th>當前審核狀態</th>
				<th>審核時間</th>				
				<th>創建日期</th>
				<s:if test='#session.loginUser.userread!="1"'>			
				<th>操作</th>
				</s:if>											
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">	
		  <s:if test='id.kyVisabillm.delMk=="1"'>
		     <tr class="danger">
		  </s:if>
		  <s:else>
		     <tr>
		  </s:else>	
				<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.kyVisabillm.id.factNo" />
				</td>
				<td>							
				<s:property value="id.kyVisabillm.colTemp"/>									  						
				</td>
				<td><s:property value="id.kyVisabillm.id.billNo"/>
				</td>
				<td><s:property value="memo"/>			
				</td>				
				<td><s:property value="id.kyVisabillm.lastMk" />
				</td>
				<td><s:property value="id.kyVisabillm.visaMk" />
				</td>
				<td><s:property value="dateVisa" />
				</td>				
				<td><s:property value="%{formatDate(id.kyVisabillm.dateCreate)}" />
				</td>
				<s:if test='#session.loginUser.userread!="1"'>														
			 	<td>
			 	<table>
			 	  <tr>
			 	    <td>
					 <form action="vbm_findById" method="post" id="0form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
					 </form>
					 <form action="vbm_findById2" method="post" id="1form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
					 </form>
					 
					  <!--  <form action="vbm_findById3" method="post" id="2form${x.index}" style="float:left">
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
					 </form>-->
					 
					 <form action="vbm_findById5" method="post" id="3_3form${x.index}" style="float:left">
					   <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />						
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
					 </form>
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
										
					<form action="vbm_sendEmail" method="post" id="8subform${x.index}" style="float:left"  target="frameFile">
						<input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>"
							name="factNo" /> <input type="hidden"
							value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo" />
							<input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>													
					</form>
					<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>
				    </td>	 
					<s:if test='#session.loginUser.userread!="1"'>
					<td>					
					<a href="javascript:findById_form('0form${x.index}','vbm_findById')" >
					<img alt="審核" src="images/icon/check24.png" title="審核"><br>審核
					</a>
					</td>
					<s:if test='id.kyVisabillm.visaMk=="N"'>
					<td>
					 <a href="javascript:findById_form('1form${x.index}','vbm_findById2')"><img alt="加簽" src="images/icon/add24.png" title="加簽"><br>加簽</a>
					</td>					
					 <td>
					   <a href="javascript:findById_form('3_3form${x.index}','vbm_findById5')"><img alt="減簽(带删除)" src="images/icon/remove.png" title="減簽(带删除)"><br>減簽</a>
					 </td>
					 </s:if>
					 <s:else>
					 <td>
					   <a><img alt="加簽" src="images/icon/add24_1.jpg" title="加簽"><br>加簽</a>
					 </td>					 
					 <td>
					   <a><img alt="減簽(带删除)" src="images/icon/remove_1.png" title="減簽(带删除)"><br>減簽</a>
					 </td>    
					 </s:else>											   					 					 						
					 </s:if>
								    					 
					 <td>
					 <a href="javascript:goPreviewOrPrint('4subform${x.index}','${temp.id.kyVisabillm.id.billNo}')" ><img alt="預覽" src="images/icon/view24.png" title="預覽"><br>預覽</a>
					 </td>
					 <td>
					 <a href="javascript:goPreviewOrPrint('5subform${x.index}','${temp.id.kyVisabillm.id.billNo}')"><img alt="打印" src="images/icon/print24.png" title="打印"><br>打印</a>
				     </td>
				    	
				     
				     <s:if test='#session.loginUser.username=="admin"'>
				     <s:if test='id.kyVisabillm.visaMk=="N"&&id.kyVisabillm.emailMk==null'>
				        <td>
				            <a href="javascript:layer.load('請稍等...');document.getElementById('8subform${x.index}').submit()">Email</a>
				        </td> 
				     </s:if>
				     <s:else>
				          <font color="grey">Email</font>
				     </s:else>
				     </s:if>
				     </tr>
				</table>
				</td> 
				</s:if>	
			</tr>		 		  
		</s:iterator>
		</tbody>
	</table>
	</div>
 </div>			
	<jsp:include page="pagenation.jsp" flush="true"/>	
		
</body>

</html>
