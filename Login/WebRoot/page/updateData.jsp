<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>My JSP 'updateData.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>
<style>
.td1 {
	width: 110px;
	text-align: right;
	background-color: #ebf2f9;
}
</style>
<script type="text/javascript">
   function checkUserName(){
      var factNo=document.getElementById("factNo").value;
      var userName=document.getElementById("userName").value;
      var userName2=document.getElementById("hide_userName").value;
      if(factNo!=""&&userName!=""&&userName!=userName2){
         userjs.findByIdDWR(factNo,userName,function(x){
              if(x!=null){
                 alert("該登錄名已存在!");
                 return false;                              
              }
         });
      }
      return true;
   }

/* 去除 輸入框所有空格*/  
function trim(string) {
var temp = "";
string = '' + string;
splitstring = string.split(" ");
for(i = 0; i < splitstring.length; i++)
temp += splitstring[i];
return temp;
}

</script>
<body>
	
	<form action="userupdateUesr" method="post" onSubmit="return checkUserName()" id="form">
		<input type="hidden" name="updateU.id"
			value="<s:property value="#attr.webU.id"/>"> <input
			type="hidden" name="updateU.available"
			value="<s:property value="#attr.webU.available"/>">
		<%-- <s:submit value="提交" name="action:sds"></s:submit>--%>
		<table border="1" width="70%" height="220px;"
			style="margin-top:5px; border-collapse:collapse;">
			<caption style="font-size:30px">個人資料修改</caption>
			<tr>
				<td class="td1">工號:</td>
				<td ><input name="updateU.workno" style="border:0px;color:blue"
					readonly="readonly" value="<s:property value="#attr.webU.workno"/>">
				</td>
			</tr>
			<tr>
				<td class="td1">密碼:</td>
				<td ><input name="updateU.pwd"
					value="<s:property value="#attr.webU.pwd"/>"></td>
			</tr>
			<tr>
				<td class="td1">姓名:</td>
				<td ><input name="updateU.name"
					value="<s:property value="#attr.webU.name"/>"></td>
			</tr>
			<tr>
				<td class="td1">登錄名:</td>
				<td >
				<input name="updateU.username"
					value="<s:property value="#attr.webU.username"/>" id="userName" style="border:0px;color:blue" readonly>
				<input type="hidden" id="hide_userName" value="<s:property value="#attr.webU.username"/>"/>	
				</td>
			</tr>
			<tr>
				<td class="td1">IP:</td>
				<td class="td_input"><input name="updateU.ip"
					value="<s:property value="#attr.webU.ip"/>"></td>
			</tr>
			<tr>
				<td class="td1">廠別:</td>
				<td ><input name="updateU.factno"
					value="<s:property value="#attr.webU.factno"/>" readonly id="factNo" style="color:blue;border:0px"></td>
			</tr>
			<tr>
				<td colspan="2"><span style="margin-left: 300px;"><input
						type="submit" value="確認修改" /> <input type="button"
						onclick="location.href='userrecoveryData'" value="恢復默認">  </span></td>
			</tr>
		</table>
	</form>
</body>
</html>
