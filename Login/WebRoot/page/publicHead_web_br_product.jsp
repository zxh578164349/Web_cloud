
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
  
  <form id="public_form" method="post" >
     <div class="well">					
			<table id="tb_search">
			<tr>
			<td>
			
			<s:if test="#session.factNo=='tw'">
					<select name="factNo" id="factNo" class="search">
						<option value="nothing">請選擇廠別</option>						
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					
				</s:if> 
				<s:else>
				
					<select name="factNo" id="factNo" class="search">						
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>
					
				</s:else>
			</td>			
			<td>
			   &nbsp;			
			   <input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" />
			   <!-- <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form','factNo','yymm','yymm2')"/>	 -->	    
		    </td>
		    </tr>
		</table>
		</div>
	</form>
</body>
</html>
