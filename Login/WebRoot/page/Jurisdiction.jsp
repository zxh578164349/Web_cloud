<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>My JSP 'Jurisdiction.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/form.css" />

</head>

<script type="text/javascript">
	
		
function getSub(){    
   jq.ajax({
	   type:"POST",
	   dataType:"html",
	   url:"userupdateJurisdiction",
	   data:jq("#subform").serialize(),
	   success:function(data){
			   jq("#r_content").html(data);		   
	   },
	   error:function(error){
		   jq("#r_content").html(error.responseText);
	   }
   });
}
function back(){	
	loadUrl("/Login/userfindPageBean3?backIndex=1");
}

jq(function(){
	var list=jq("[name='checkbox']");
	var list_hidden=jq("[name='checkbox_hidden']");
	for(var i=0;i<list.length;i++){
		var attr=list.eq(i).val().split(",");
		for(var j=0;j<list_hidden.length;j++){
			if(attr[1]==list_hidden.eq(j).val()){
				list.eq(i).prop("checked",true);
				break;
			}
		}
	}				
});
</script>
<body>
	<form action="userupdateJurisdiction" method="post" id="subform">
		<table class="table table-striped table-hover table-bordered">
			<h4><s:property value="#session.jurisdiction_user.username"/>&nbsp;&nbsp;
			<s:if test='#session.jurisdiction_user.userread=="1"'>
			   <input type="checkbox" name="userread" value="1" checked/>只查看
			</s:if>
			<s:else>
			   <input type="checkbox" name="userread" value="1"/>只查看
			</s:else>			
			</h4>
			<s:iterator value="#session.login_menus">
			   <tr>
			      <td><s:property value="menuname"/></td>
			      <td>
			        <s:iterator value="submenus">
			            <input type="checkbox" name="checkbox" value="<s:property value='menuname'/>,<s:property value='submenuname'/>,<s:property value='address'/>,<s:property value='submenuid'/>"/><s:property value="submenuname" />
			        </s:iterator>			        			      			        			        
			      </td>
			   </tr>
			</s:iterator>
			
			
			<s:iterator value="#session.jurisdiction_user.webJurisdictions">
			           <s:iterator value="webSubmenus">
			              <input type="hidden" value="<s:property value='submenuname'/>" name="checkbox_hidden"/>
			           </s:iterator>
		   </s:iterator>
		
          <tr>
				<td colspan="2">
				<input  value="確認修改" onclick="getSub()" type="button" class="btn btn-primary">
				<input type="button" onclick="back()" value="返回查看" class="btn btn-primary">					
				</td>
			</tr>
		</table>
	</form>
</body>

</html>