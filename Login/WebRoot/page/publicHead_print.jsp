
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<LINK href="css/list.css" type="text/css" rel="stylesheet"> 

</head>
<body>
  
  <form id="public_form" method="post" >
	
		
			<!-- <td><label class="control-label">廠別</label></td> -->
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
			
			  <!-- 開始日期: --><input type="text" id="yymm" name="yymm" onClick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'yymm2\',{M:-1})||\'%y-%M\'}'})" readonly="readonly" class="Wdate search"/>至
			  <!-- 結束日期: --><input type="text" id="yymm2" name="yymm2" onClick="WdatePicker({minDate:'#F{$dp.$D(\'yymm\',{M:0})}',maxDate:'%y-%M'})" readonly="readonly" class="Wdate search"/>			
			
			</td>
			<td>
			
			   <input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" />
			   <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form','factNo','yymm','yymm2')"/>
		    
		    </td>
		    </tr>
		</table>
	</form>
<script type="text/javascript">
//var jq=jQuery.noConflict();
jq(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});
</script>
</body>
</html>
