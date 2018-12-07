
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
    <h3>新產品推銷申請表 </h3>
    <s:if test="obj!=null">
	<table class="table table-condensed table-bordered">		
		<tbody id="tb_list_info2">			
			<tr>			
				<td>申請單號</td>
				<td colspan="2"><s:property value='obj.billNo' />
				</td>			
				<td>接收日期</td>
				<td colspan="2" ><s:property value='obj.receiveDate' />
				</td>
				<td>新材料名稱</td>
				<td colspan="2"><s:property value='obj.PName' />
				</td>				
			</tr>						
			<tr>
				<td>推銷客戶及<br/>詳細用途說明</td>
				<td colspan="8">				 
				  <textarea style="width:100%;height:240px" readonly><s:property value="obj.PExp" /></textarea>				     				 					
				</td>

			</tr>
			<tr>
				<td>客人回饋結果</td>
				<td colspan="8">				 
				  <textarea style="width:100%;height:240px" readonly><s:property value="obj.PResultGuest" /></textarea>				     				 					
				</td>

			</tr>
			<tr>
				<td>结果</td>
				<td colspan="8">				 
				  <textarea style="width:100%;height:240px" readonly><s:property value="obj.PResult" /></textarea>				     				 					
				</td>

			</tr>
			
			<s:iterator value="#session.vbm.kyVisabillses">
			   <s:if test='memo!=null&&memo!=""'>
			     <tr>
			       <td>
			          <s:property value="visaRank"/>:
			       </td>
			       <td colspan="8" style="color:blue">
			          <b><s:property value="memo"/></b>
			       </td>
			     </tr>
			   </s:if>
			</s:iterator>
			<!------------------------- 修改3   20151027---------------   -->
			<s:if test='readMk=="N"'>
			    <tr><td colspan="12"><label style="color:red">簽核備註↓↓↓</label></td></tr>
				<tr><td colspan="12">									
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
   <h1 style="color:red" align="center">內部聯絡函已刪除</h1>
</s:else>


<!--[if lt IE 9]>  
<script src="bootstrap/html5.js"></script>
<script src="bootstrap/respond.min.js"></script>
<![endif]-->
</body>
</html>
