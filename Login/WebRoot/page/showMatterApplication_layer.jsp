
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
    <h3>函文申請</h3>
    <s:if test="kyz!=null">
	<table class="table table-condensed table-bordered">		
		<tbody id="tb_list_info2">
			<tr>
				<td>標題</td>
				<td colspan="2"><s:property value='kyz.memoSmk' />
				</td>
				<td>電話</td>
				<td colspan="2"><s:property value='kyz.telNo' />
				</td>
				<td>申請單位</td>
				<td colspan="3"><s:property value='kyz.secNo' />
				</td>
			</tr>




			<tr>
				<td>廠別</td>
				<td colspan="2"><s:property value='kyz.factNo2.factSname' />
				</td>
				<td>廠別狀態</td>
				<td colspan="2"><s:property value='kyz.factCode' />
				</td>
				<td>申請日期</td>
				<td colspan="3"><s:date name="kyz.timeCreate" format="yyyyMMdd" /></td>
			</tr>
			<tr>
				<td>申請單號</td>
				<td colspan="2" id="billno"><s:property value='kyz.id.billNo' />
				</td>
				<td>申請者</td>
				<td colspan="2"><s:property value='kyz.userNm' />
				</td>
				<td>是否緊急</td>
				<td colspan="3"><s:if test="kyz.emerWhether==0">
						             是
						   </s:if> <s:if test="kyz.emerWhether==1">
						             否
						   </s:if></td>
			</tr>
			<tr>
				<td>類別</td>
				<td colspan="9">				
				<s:property value="kyz.visaType" />
				</td>
			</tr>
			<tr>
				<td>申請內容</td>
				<td colspan="9">					
					<textarea style="width:100%;height:180px;overflow:auto" readonly><s:property value="kyz.memoMk" /></textarea>
					</td>
			</tr>
		</tbody>

		<tbody id="kyzs_body">
			<tr>
				<td>名稱</td>
				<td>項次</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>合計</td>
				<td>使用人數</td>
				<td>單位</td>
				<td>幣種</td>
				<td>備註</td>
			</tr>



			<s:iterator value="kyz.kyzExpectmatses" status="x" id="temp">
				<tr>
					<td><s:property value='itemNm' />
					</td>
					<td><s:property value='id.itemNo' />
					</td>
					<td><s:property value='matNo' />
					</td>
					<td><s:property value='%{formatDouble2(qtyExpect)}' />
					</td>
					<td><s:property value="%{formatDouble(qtyOk)}" />
					</td>
					<td><s:property value='%{formatDouble2(qtyExpect*qtyOk)}' />
					</td>
					<td><s:property value="%{formatDouble(personNo)}" />
					</td>
					<td><s:property value='qtyPair' />
					</td>
					<td><s:property value='moneyType' />
					</td>
					<td><s:property value='memoMk' />
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
			         <td colspan="9" style="color:blue">
			            <b><s:property value="memo"/></b>
			         </td>
			      </tr>
			   </s:if>
			</s:iterator>
			<!-- ---------------------顯示所有人的備註信息20151211----------------------------- -->
			<!------------------------- 修改3   20151027---------------   -->
			<s:if test='readMk=="N"'>
			    <tr><td colspan="10"><label style="color:red">簽核備註↓↓↓</label></td></tr>
				<tr><td colspan="10">									
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
	 <s:if test='kyz.filesYn=="1"'>
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
