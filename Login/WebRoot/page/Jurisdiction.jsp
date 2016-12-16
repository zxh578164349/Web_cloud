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
</head>


<body>
	<form action="userupdateJurisdiction" method="post" id="subform">
		<table class="table table-striped table-hover table-bordered">
			<h4><s:property value="#session.jurisdiction_user.username"/>
			    <input type="hidden" value="<s:property value='#session.jurisdiction_user.userType'/>" id="userType"/>
			&nbsp;&nbsp;&nbsp;
			<s:if test='#session.jurisdiction_user.userread=="1"'>
			   <input type="checkbox" name="userread" value="1" checked/>只查看
			</s:if>
			<s:else>
			   <input type="checkbox" name="userread" value="1"/>只查看
			</s:else>			
			</h4>
			<s:iterator value="#session.login_menus_all">
			   <tr>
			      <td>
			         <s:if test='typeMk=="0"'>
			             <s:property value="menuname"/>
			         </s:if>
			         <s:else>
			             <s:property value="menuname"/><label style="color:red">(訪客)</label>
			         </s:else>
			      </td>
			      <td>
			        <s:iterator value="submenus">
			            <input type="checkbox" name="checkbox" value="<s:property value='menuname'/>,<s:property value='submenuname'/>,<s:property value='address'/>,<s:property value='menuid'/>,<s:property value='typeMk'/>"/>            
			            <s:property value="submenuname" />
			        </s:iterator>			        			      			        			        
			      </td>
			   </tr>
			</s:iterator>
			
			
			<s:iterator value="#session.jurisdiction_user.webJurisdictions">
			           <s:iterator value="webSubmenus">
			              <input type="hidden" value="<s:property value='address'/>" name="checkbox_hidden"/>
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
	var userType=jq("#userType").val();
	var list_hidden=jq("[name='checkbox_hidden']");
	for(var i=0;i<list.length;i++){
		var attr=list.eq(i).val().split(",");
		if(attr[4]==userType){
			for(var j=0;j<list_hidden.length;j++){
				if(attr[2]==list_hidden.eq(j).val()){
					list.eq(i).prop("checked",true);
					break;
				}
			}
		}else{
			list.eq(i).prop("disabled","disabled");
			list.eq(i).css("background","grey");
		}
		
		
	}				
});
</script>	
</body>

</html>