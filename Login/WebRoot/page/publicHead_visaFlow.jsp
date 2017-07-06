<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body >
<form id="public_form" method="post" >
	<table  id="tb_search">
		<tr>
			<td><s:if test="#session.factNo=='tw'">			      
					<select name="factNo" id="factNo" onchange="getType(this.value)">
						<option value="nothing">請選擇廠別</option>
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0] }">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>				  	
				</s:if> 
				<s:else>				  
					<select name="factNo" id="factNo" onchange="getType(this.value)">
					    <option value="nothing">請選擇廠別</option>
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>
				  	
				</s:else></td>
			<td>			 
			 <select name="visaSort" id="visaSort" >
				            <option value="">請選擇類別</option>
				            <%-- <s:iterator value="#session.listkytype">
				              <option value="<s:property value='id.typeSno'/>"><s:property value='typeSname'/></option>
				            </s:iterator> --%>
				            
				         </select>			  	         
			</td>
			<td>
			     分部門<input type="radio" name="trMk" value="Y"/>&nbsp;&nbsp;
			   不分部門<input type="radio" name="trMk" value="N"/>  
			</td>
			<td><!-- <input type="image" onclick="submis();" src="images/search002.gif"/> -->
			<input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" /></td>
		</tr>
	</table>
</form>	
<script type="text/javascript">  
   function getType(factNo){
     document.getElementById("visaSort").length=1;
     webtypejs.findByFactNo(factNo,function(x){
       if(x.length>0){
          dwr.util.addOptions("visaSort",x,"webtypeMk","typeName");
       }
         
     });
   }   
  	
</script>
<script type='text/javascript' src='dwr/interface/webtypejs.js'></script>	
</body>
</html>
