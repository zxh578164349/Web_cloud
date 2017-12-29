<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<title>添加新用户</title>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>

<body>
	<form action="webdep_add" method="post" id="form">
		<table class="table table-condensed">		      						
					<tr>
						<td class="td_show_title">廠別</td>
						<s:if test="dep==null">						
						<td class="td_input">
						<s:if test="#session.factNo!='tw'">						
						<input type="text" style="color:blue" name="dep.factNo" value="${factNo}" readonly id="dwr_factno"/>												
						</s:if>	
						<s:if test="#session.factNo=='tw'">	
						<select style="color:blue"
							name="dep.factNo" datatype="*"
							onchange="check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select>
						</s:if>						
						</td>																																					
						</s:if>
						<s:else>
						   <td>
						   <input type="text" name="dep.factNo" value="<s:property value='dep.factNo'/>" readonly style="color:blue"/>
						   <input type="hidden" name="dep.depId" value="<s:property value='dep.depId'/>" readonly style="color:blue"/>
						   </td>
						</s:else>
										
				        <td class="td_show_title">部門名稱</td>
				        <td class="td_input">
				           	<input type="text" name="dep.depName" value="<s:property value='dep.depName'/>" datatype="s1-50"/>	             				            								
				         </td>							
				</tr>																   											
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;			
            <input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>
					 
		</center>
	</form>

<script type="text/javascript">

	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true	,
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("webdep_findPageBean");
				}else{
					layer.msg("提交失敗!",3,3);
				}				
			}
		});	
	}); 

	    	       
 /*禁止空格輸入*/        
jq(function(){
        goTrim();
      });
function back(){	
	loadUrl("webdep_findPageBean3?backIndex=1");
	
}

</script>
</body>
</html>
