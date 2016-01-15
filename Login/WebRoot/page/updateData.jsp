<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'updateData.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
</head>
<style>
.td1 {
	width: 110px;
	text-align: right;
	background-color: #ebf2f9;
}
input[type=text],input[type=password]{width:250px}
</style>
<script type="text/javascript">
   function checkUserName(){
      var factNo=document.getElementById("factNo").value;
      var userName=document.getElementById("userName").value;
      var userName2=document.getElementById("hide_userName").value;
      var email=document.getElementById("email").value;
      var email2=document.getElementById("hidden_email").value;
      if(factNo!=""&&userName!=""&&userName!=userName2){
         userjs.findByIdDWR(factNo,userName,function(x){
              if(x!=null){
                 alert("該登錄名已存在!");                           
              }
         });
      }
      if(factNo!=""&&email!=""&&email!=email2){
         userjs.findUserByFactNoAEmail(factNo,email,function(x){
              if(x!=null){
                 alert("該Email已存在!");                             
              }else{
                document.getElementById("form").submit();
              }
         });
      }else{
         document.getElementById("form").submit();
      } 
   }


/*禁止空格輸入*/
window.onload=function(){            
            var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            }  
        } 
function back(){
	layer.load("正在返回,请稍等...");
	location.href="/Login/userfindPageBean"
}
</script>
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
<body>
	
	<form action="userupdateUesr" method="post"  id="form">
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
					readonly="readonly" value="<s:property value="#attr.webU.workno"/>" type="text">
				</td>
			</tr>
			<tr>
				<td class="td1">登錄名:</td>
				<td >
				<input name="updateU.username"
					value="<s:property value="#attr.webU.username"/>" id="userName" style="border:0px;color:blue" readonly type="text">
				<input type="hidden" id="hide_userName" value="<s:property value="#attr.webU.username"/>"/>	
				</td>
			</tr>
			<tr>
				<td class="td1">廠別:</td>
				<td ><input name="updateU.factno"
					value="<s:property value="#attr.webU.factno"/>" readonly id="factNo" style="color:blue;border:0px" type="text"></td>
			</tr>
			<tr>
				<td class="td1">密碼:</td>
				<td ><input name="updateU.pwd"
					value="<s:property value="#attr.webU.pwd"/>" type="password"></td>
			</tr>
			<tr>
				<td class="td1">姓名:</td>
				<td ><input name="updateU.name"
					value="<s:property value="#attr.webU.name"/>" type="text"></td>
			</tr>
			
			<tr>
				<td class="td1">IP:</td>
				<td class="td_input"><input name="updateU.ip"
					value="<s:property value="#attr.webU.ip"/>" type="text"></td>
			</tr>
			
			<tr>
			     <td class="td1">Email:</td>
			     <td><input name="updateU.email" value="<s:property value="#attr.webU.email"/>" type="text" id="email" onblur="checkUserName()"/>
			       <input  value="<s:property value="#attr.webU.email"/>" type="hidden" id="hidden_email"/>
			     </td>
			</tr>			
			<tr>
			     <td class="td1">只讀/修改</td>
			     <td>
			             只讀:
			     <s:if test='#attr.webU.userread=="1"'>
			     <input type="radio" value="1" name="updateU.userread" checked/>
			     </s:if>
			     <s:else>
			      <input type="radio" value="1" name="updateU.userread"/>
			     </s:else>
			     &nbsp;
			               修改:
			      <s:if test='#attr.webU.userread=="0"||#attr.webU.userread==null'>         
			       <input type="radio" value="0" name="updateU.userread" checked/>
			      </s:if>
			      <s:else>
			        <input type="radio" value="0" name="updateU.userread"/>
			      </s:else>
			     <%-- <input type="hidden" name="updateU.logdate" value="<s:property value="#attr.webU.logdate"/>"/> --%>
			     </td>
			</tr>
			<tr>
			  <td class="td1">部門</td>
			  <td>
			       <input type="text" value="<s:property value='#attr.webU.department'/>" name="updateU.department"/>
			  </td>
			</tr> 
			<tr>
				<td colspan="2"><span style="margin-left: 300px;">
				  <input type="button" value="確認修改" onclick="checkUserName()"/>&nbsp;
				  <input type="button" onclick="location.href='userrecoveryData'" value="恢復默認">&nbsp;
				  <input type="button" onclick="back()" value="返回"/>			
				  </span></td>
						
			</tr>
		</table>
	</form>
</body>
</html>
