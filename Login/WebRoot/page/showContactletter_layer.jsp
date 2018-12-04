
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
    <h3>內部聯絡函申請 </h3>
    <s:if test="kyzletter!=null">
	<table class="table table-condensed table-bordered">		
		<tbody id="tb_list_info2">
			<tr>
				<td>標題</td>
				<td colspan="2"><s:property value='kyzletter.title' />
				</td>
				<td>受文者</td>
				<td colspan="2"><s:property value='kyzletter.toUser' />
				</td>
				<td>承辦人</td>
				<td colspan="3"><s:property value='kyzletter.userNm' />
				</td>
			</tr>




			<tr>
				<td>廠別</td>
				<td colspan="2"><s:property value='kyzletter.factNo2.factSname' />
				</td>
				<td>廠別狀態</td>
				<td colspan="2"><s:property value='kyzletter.factCode' />
				</td>
				<td>類別</td>
				<td colspan="3">
					(<s:property value="kyzletter.visaType" />)
				</td>
			</tr>
			<tr>
				<td>承辦單位</td>
				<td colspan="2" ><s:property value='kyzletter.secNo' />
				</td>
				<td>承辦主管</td>
				<td colspan="2"><s:property value='kyzletter.chargeNo' />
				</td>
				<td>承辦日期</td>
				<td colspan="3"><s:property value='kyzletter.ymExpect' /></td>
			</tr>
			<tr>
				<td>申請單號</td>
				<td colspan="9"><s:property value='kyzletter.id.billNo' /></td>
			</tr>
			<tr>
			  <td>CC(呈)</td>
			  <td colspan="11">
			     <s:property value="kyzletter.chargeList" />		     			     			    
			  </td>
			</tr>
			<tr>
				<td>申請內容</td>
				<td colspan="11">				 
				  <textarea style="width:100%;height:240px" readonly><s:property value="kyzletter.memoMk" /></textarea>				     				 					
				</td>

			</tr>
			<s:iterator value="#session.vbm.kyVisabillses">
			   <s:if test='memo!=null&&memo!=""'>
			     <tr>
			       <td>
			          <s:property value="visaRank"/>:
			       </td>
			       <td colspan="11" style="color:blue">
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
	 <s:if test='kyzletter.filesYn=="1"'>
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
