
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.net.URLEncoder"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'addBackMat.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <h3>生產領料申請</h3>
    <s:if test="obj!=null">
	<table class="table table-condensed table-bordered">		
		<tbody id="tb_list_info2">
			<tr>
				<td>廠別</td>
				<td colspan="4"><s:property value='obj.factNo.factSname' />
				</td>
				<td>類別</td>
				<td colspan="4"><s:property value='obj.visaType' />
				</td>
				<td>單號</td>
				<td colspan="5"><s:property value='obj.billNo' />
				</td>
			</tr>




			<tr>
				<td>標題</td>
				<td colspan="4"><s:property value='obj.title' />
				</td>
				<td>日期</td>
				<td colspan="4"><s:property value='obj.colDateMain' />
				</td>
				<td>下單人</td>
				<td colspan="5"><s:property value='obj.orderManMain' /></td>
			</tr>			
		</tbody>

		<tbody id="kyzs_body">
			<tr>
				<td >重要性</td>
			     <td >型體</td>
			     <td >結構</td>
			     <td >鞋廠及下單人</td>
			     <td >樣品用途</td>
			     <td >數量</td>
			     <td >單重(G)</td>
			     <td >留底量</td>
			     <td >不良</td>			     
			     <td >型體負責人</td>
			     <td >可否請款</td>
			     <td >階段</td>
			     <td >需求料的重量</td>			    			     			     		     
			     <td >備註</td>
			</tr>



			<s:iterator value="obj.webColproductItemses" status="x" id="temp">
				<tr>
					<td>					
					<s:property value='importmant' />				
					</td>
					<td><s:property value='shape' />
					</td>
					<td><s:property value='CStructure' />
					</td>
					<td><s:property value='orderFactoryAndMan' />
					</td>
					<td><s:property value='purpose' />
					</td>
					<td><s:property value="numbers" />
					</td>
					<td><s:property value='weight' />
					</td>
					<td><s:property value="remainNum" />
					</td>
					<td><s:property value='unhealthNum' />
					</td>
					<td><s:property value='picMan' />
					</td>
					<td>
					<s:if test='paymk=="Y"'>
					是
					</s:if>
					<s:if test='paymk=="N"'>
					否
					</s:if>					
					</td>					
					<td><s:property value='stage' />
					</td>
					<td><s:property value='weightb' />
					</td>
					<td><s:property value='remarks' />
					</td>					
				</tr>
			</s:iterator>
			<!-- ---------------------顯示所有人的備註信息20151211----------------------------- -->
			<s:iterator value="#session.vbm.kyVisabillses">
			   <s:if test='memo!=null&&visaRank!=""'>			      
			      <tr>
			         <td>
			            <s:property value="visaRank"/>:
			         </td>
			         <td colspan="16" style="color:blue">
			            <b><s:property value="memo"/></b>
			         </td>
			      </tr>
			   </s:if>
			</s:iterator>
			<!-- ---------------------顯示所有人的備註信息20151211----------------------------- -->
			<!------------------------- 修改3   20151027---------------   -->
			<s:if test='readMk=="N"'>
			    <tr><td colspan="16"><label style="color:red">簽核備註↓↓↓</label></td></tr>
				<tr><td colspan="16">									
					<form id="memo" method="post" action="vbm_add" target="frameFile">
						<textarea  name="memo" id="memo_txt" style="width:100%;height:120px;overflow:auto"></textarea>						
						<input type="hidden" name="factNo" value="<s:property value='factNo'/>"/>
						<input type="hidden" name="billNo" value="<s:property value='billNo'/>"/>
						<input type="hidden" name="itemNo" value="<s:property value='itemNo'/>"/>
						<input type="hidden" name="visaSort" value="<s:property value="visaSort"/>"/>
						<input type="hidden" name="visa_mk"  id="visa_mk"/>
					</form>
					<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>					
				</td></tr>
			</s:if>
			<!------------------------- 修改3   20151027---------------   -->	
			</tbody>		
	</table>
	 <s:if test='obj.filesYn=="1"'>
	  <hr/>
	  <span style="color:blue;">附檔:</span><br/>
	  <s:iterator value="#session.list_filesexp">
	     <a href="/<s:property value='fileurl'/>/<s:property value='billno'/>/<s:property value='%{toUrl2(filename)}'/>" target="_blank"><s:property value="%{toUrl(filename)}"/></a>&nbsp;
	  </s:iterator>	  
	</s:if> 
	
</s:if>
<s:else>
  <br><br><br>
   <h1 style="color:red" align="center">函文已刪除</h1>
</s:else>



<!--[if lt IE 9]>  
<script src="bootstrap/html5.js"></script>
<script src="bootstrap/respond.min.js"></script>
<![endif]-->


</body>
</html>
