
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
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>


 <script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>	
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />

<script type="text/javascript">
   
jq(document).keyup(function(event){
    if(event.keyCode==13){
       submis();
    }
})   
</script>

</head>
<body onload="getKyType()">
   <form>
	<table  id="tb_search">
		<tr>
			<td><s:if test="#attr.factNo=='tw'">
					<select name="factNo" id="factNo" onchange="getType2(this.value)">
						<option value="nothing">請選擇廠別</option>						
						<s:iterator value="#attr.facts" id="temp">
							<option value="${temp[0] }">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
				</s:if>
				 <s:else>				 
					<select name="factNo" id="factNo" ">
						<option value="<s:property value="#attr.factNo"/>">
							<s:property value="#attr.factName" />(<s:property value="#attr.factNo"/>)
						</option>
					</select>				 	
				</s:else>
			
			     <select name="visaSort" id="dwr_kytype" >
				            <option value="nothing">請選擇類別</option>
				            <s:iterator value="#attr.listkytype">
				              <option value="<s:property value='id.typeSno'/>"><s:property value='typeSname'/></option>
				            </s:iterator>
				         </select>
			
							
			
			<input type="radio" value="Y" name="visaMk" />已審核|
			<input type="radio" value="N" name="visaMk" checked="checked"/>未審核|
			<input type="radio" value="T" name="visaMk"  />未通過
			<input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />
			</td>								
		</tr>
		<tr>
			<td><input type="text" id="yymmdd" name="yymmdd" 
				onClick="WdatePicker()" readonly="readonly" class="Wdate"/>
				至
			<input type="text" id="yymmdd2" name="yymmdd2" 
				onClick="WdatePicker()" readonly="readonly" class="Wdate"/>				
				單號:<input type="text" name="billNo" value="" id="billNo" />																
				</td>
		</tr>
	</table>
	</form>
</body>
</html>

