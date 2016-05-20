

<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="dao.IKyFactDao"%>
<%@page import="services.impl.KyFactServicesImpl"%>
<%@page import="services.IKyFactServices"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="entity.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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


<style type="text/css">

</style>

<script type="text/javascript">
  //dwr加載函文類別
/*    function getType(){
       kytypejs.findByTypeNo("VV",function(x){
	         dwr.util.addOptions("visaSort",x,"typeName","typeSname");
	   });
   } */
   
   jq(document).keyup(function(event){
       if(event.keyCode==13){
          submis();
       }
   })
   
   function getType(factNo){
     document.getElementById("visaSort").length=1;
     webtypejs.findByFactNo(factNo,function(x){
       if(x.length>0){
          dwr.util.addOptions("visaSort",x,"webtypeMk","typeName");
       }
         
     });
   }
</script>
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webtypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>
<body >
	<table  id="tb_search" >
		<tr>
			<td><s:if test="#session.factNo=='tw'">			   
					<select name="factNo" id="factNo" onchange="getType(this.value)">
						<option value="nothing">請選擇廠別</option>
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
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
				</s:else>
			
			 <select name="visaSort" id="visaSort" >
				            <option value="">請選擇類別</option>				            				            				            
				         </select>         
							
			單號<input type="text" name="billNo" value="" id="billNo"/>
			</td> 						
		</tr>
		<tr>
			<td><input type="text" id="yymmdd" name="yymmdd" 
				onClick="WdatePicker()" readonly="readonly" class="Wdate"/>至
			<input type="text" id="yymmdd2" name="yymmdd2" 
				onClick="WdatePicker()" readonly="readonly" class="Wdate"/>
			<input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />
			</td>
		</tr>
	</table>
</body>
</html>

